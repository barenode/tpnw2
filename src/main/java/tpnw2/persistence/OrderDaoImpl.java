package tpnw2.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;

@Repository
@Transactional
public class OrderDaoImpl extends DaoBase<Order, OrderCriteria, OrderEntity> implements OrderDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<OrderEntity> root, OrderCriteria criteria) {
		return null;
	}

	@Override
	protected Class<OrderEntity> getEntityClass() {
		return OrderEntity.class;
	}

	@Override
	protected Function<OrderEntity, Order> valueObjectConversion() {
		return OrderDao.toValueObject;
	}

	@Override
	protected Function<Order, OrderEntity> entityConversion() {
		return OrderDao.toEntity;
	}

	@Override
	protected Object getKey(Order item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Order item) {
		return item.getId()==null;
	}
}
