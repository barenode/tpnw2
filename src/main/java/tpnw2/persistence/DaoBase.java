package tpnw2.persistence;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.springframework.transaction.annotation.Transactional;

public abstract class DaoBase<T extends Serializable, C extends Serializable, E> implements Dao<T, C> {

	protected abstract EntityManager em();
	
	protected abstract Predicate buildCriteria(CriteriaBuilder builder, Root<E> root, C criteria);
	
	protected abstract Class<E> getEntityClass();
	
	protected abstract Function<E, T> valueObjectConversion();
	
	protected abstract Function<T, E> entityConversion();
	
	protected abstract Object getKey(T item);
	
	protected abstract boolean isNewItem(T item);
	
	@Override
	@Transactional
	public final long count(C criteria) {
		CriteriaBuilder builder = em().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<E> root = query.from(getEntityClass());
		query.select(builder.count(root));
		Predicate predicate = buildCriteria(builder, root, criteria);
		if (predicate!=null) {
			query.where(predicate);
		}		
		return em().createQuery(query).getSingleResult();	
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> findAll(C criteria) {
		CriteriaBuilder builder = em().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = builder.createQuery(getEntityClass());
		Root<E> root = criteriaQuery.from(getEntityClass());		
		Predicate predicate = buildCriteria(builder, root, criteria);
		if (predicate!=null) {
			criteriaQuery.where(predicate);
		}	
		Query query = em().createQuery(criteriaQuery);
		List<E> entitites = query.getResultList();	
		return entitites.stream().map(valueObjectConversion()).collect(toList());
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> page(long first, long count, C criteria, SortParam<String> sort) {
		CriteriaBuilder builder = em().getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = builder.createQuery(getEntityClass());
		Root<E> root = criteriaQuery.from(getEntityClass());		
		criteriaQuery.select(root);
		Predicate predicate = buildCriteria(builder, root, criteria);
		if (predicate!=null) {
			criteriaQuery.where(predicate);
		}		
		if (sort.isAscending()) {
			criteriaQuery.orderBy(builder.asc(root.get(sort.getProperty())));
		} else {
			criteriaQuery.orderBy(builder.desc(root.get(sort.getProperty())));
		}	
		Query query = em().createQuery(criteriaQuery);
		query.setFirstResult((int)first);
		query.setMaxResults((int)count);		
		List<E> entitites = query.getResultList();	
		return entitites.stream().map(valueObjectConversion()).collect(toList());	
	}

	@Override
	@Transactional
	public void remove(T item) {
		E entity = em().find(getEntityClass(), getKey(item));
		if (entity!=null) {
			em().remove(entity);		
		}		
	}

	@Override
	@Transactional
	public T save(T item) {
		if (isNewItem(item)) {
			E e = entityConversion().apply(item);
			em().persist(e);		
			return valueObjectConversion().apply(e);
		} else {
			E e = entityConversion().apply(item);
			em().merge(e);		
			return valueObjectConversion().apply(e);
		}
	}
}
