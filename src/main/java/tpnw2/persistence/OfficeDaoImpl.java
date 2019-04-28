package tpnw2.persistence;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Office;
import tpnw2.domain.OfficeCriteria;

@Repository
@Transactional
public class OfficeDaoImpl extends DaoBase<Office, OfficeCriteria, OfficeEntity> implements OfficeDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Office> findByManager(Integer managerId) {
		//return findAll(new OfficeCriteria(managerId));
		Query query = em.createQuery("from OfficeEntity e where e.manager.id=:managerId");
		query.setParameter("managerId", managerId);
		List<OfficeEntity> entitites = query.getResultList();	
		return entitites.stream().map(toValueObject).collect(Collectors.toList());
	}

	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<OfficeEntity> root, OfficeCriteria criteria) {
		if (criteria.getManagerId()!=null && criteria.getCity()!=null) {
		return builder.and(
			builder.equal(root.get("manager").<Integer>get("id"), criteria.getManagerId()),
			builder.like(root.get("city"), criteria.getCity() + "%")
		);
		} else if (criteria.getManagerId()!=null) {
			return builder.equal(root.get("manager").<Integer>get("id"), criteria.getManagerId());
		} else if (criteria.getCity()!=null) {
			return builder.like(root.get("city"), criteria.getCity() + "%");
		} else {
			return null;
		}
	}

	@Override
	protected Class<OfficeEntity> getEntityClass() {
		return OfficeEntity.class;
	}

	@Override
	protected Function<OfficeEntity, Office> valueObjectConversion() {
		return toValueObject;
	}

	@Override
	protected Function<Office, OfficeEntity> entityConversion() {
		return toEntity;
	}

	@Override
	protected Object getKey(Office item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Office item) {
		return item.getId()==null;
	}	
	
	
//	@Override
//	public List<Office> findByManager(Integer managerId) {
//		//return findAll(new OfficeCriteria(managerId));
//		Query query = em.createQuery("from OfficeEntity e where e.manager.id=:managerId");
//		query.setParameter("managerId", managerId);
//		List<OfficeEntity> entitites = query.getResultList();	
//		return entitites.stream().map(toValueObject).collect(toList());
//	}	
//	
//	//overrides
//	private Predicate buildCriteria(CriteriaBuilder builder, Root<OfficeEntity> root, OfficeCriteria criteria) {
//		System.out.println("criteria: " + criteria);
//		if (criteria.getManagerId()!=null && criteria.getCity()!=null) {
//			return builder.and(
//				builder.equal(root.get("manager").<Integer>get("id"), criteria.getManagerId()),
//				builder.like(root.get("city"), criteria.getCity() + "%")
//			);
//		} else if (criteria.getManagerId()!=null) {
//			return builder.equal(root.get("manager").<Integer>get("id"), criteria.getManagerId());
//		} else if (criteria.getCity()!=null) {
//			return builder.like(root.get("city"), criteria.getCity() + "%");
//		} else {
//			return null;
//		}
//	}
//	
//	public long count(OfficeCriteria criteria) {		
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Long> query = builder.createQuery(Long.class);
//		Root<OfficeEntity> root = query.from(OfficeEntity.class);
//		query.select(builder.count(root));
//		Predicate predicate = buildCriteria(builder, root, criteria);
//		if (predicate!=null) {
//			query.where(predicate);
//		}		
//		return em.createQuery(query).getSingleResult();	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Office> page(long first, long count, OfficeCriteria criteria, SortParam<String> sort) {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<OfficeEntity> criteriaQuery = builder.createQuery(OfficeEntity.class);
//		Root<OfficeEntity> root = criteriaQuery.from(OfficeEntity.class);		
//		criteriaQuery.select(root);
//		Predicate predicate = buildCriteria(builder, root, criteria);
//		if (predicate!=null) {
//			criteriaQuery.where(predicate);
//		}		
//		if (sort.isAscending()) {
//			criteriaQuery.orderBy(builder.asc(root.get(sort.getProperty())));
//		} else {
//			criteriaQuery.orderBy(builder.desc(root.get(sort.getProperty())));
//		}	
//		Query query = em.createQuery(criteriaQuery);
//		query.setFirstResult((int)first);
//		query.setMaxResults((int)count);		
//		List<OfficeEntity> entitites = query.getResultList();	
//		return entitites.stream().map(toValueObject).collect(toList());	
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Office> findAll(OfficeCriteria criteria) {
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
//		Root<OfficeEntity> root = criteriaQuery.from(OfficeEntity.class);		
//		Predicate predicate = buildCriteria(builder, root, criteria);
//		if (predicate!=null) {
//			criteriaQuery.where(predicate);
//		}	
//		Query query = em.createQuery(criteriaQuery);
//		List<OfficeEntity> entitites = query.getResultList();	
//		return entitites.stream().map(toValueObject).collect(toList());
//	}
//	
//	public Office load(Office item) {
//		OfficeEntity entity = em.find(OfficeEntity.class, item.getId());
//		if (entity!=null) {
//			return toValueObject.apply(entity);
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public void remove(Office item) {
//		OfficeEntity entity = em.find(OfficeEntity.class, item.getId());
//		if (entity!=null) {
//			em.remove(entity);		
//		}	
//	}
//	
//	protected boolean isNewItem(Office item) {
//		return item.getId()==null;
//	}
//
//	@Override
//	public void save(Office item) {
//		if (isNewItem(item)) {
//			em.persist(toEntity.apply(item));		
//		} else {
//			em.merge(toEntity.apply(item));		
//		}		
//	}
}
