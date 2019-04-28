package tpnw2.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Client;
import tpnw2.domain.Individual;
import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;

@Repository
@Transactional
public class OrderDaoImpl extends DaoBase<Order, OrderCriteria, OrderEntity> implements OrderDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Autowired
	private IndividualDao individualDao;
	
	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<OrderEntity> root, OrderCriteria criteria) {
		Predicate p = null;
		if (criteria.getEmployee()!=null) {
			p = builder.or(
				builder.equal(root.get("administrator").<String>get("id"), criteria.getEmployee().getId()),
				builder.equal(root.get("driver").<String>get("id"), criteria.getEmployee().getId())
			);
		} 
		if (criteria.getOffice()!=null) {
			Predicate officePredicate = builder.equal(root.get("office").<String>get("id"), criteria.getOffice().getId());	
			if (p==null) {
				p = officePredicate;
			} else {
				p = builder.and(p, officePredicate);
			}
		}
		if (criteria.getClient()!=null) {
			Predicate clientPredicate = builder.equal(root.get("client").<String>get("id"), criteria.getClient().getId());	
			if (p==null) {
				p = clientPredicate;
			} else {
				p = builder.and(p, clientPredicate);
			}
		}
		return p;
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

	@Transactional
	@Override
	public Order save(Order item) {
		Client client = item.getClient();
		if (client instanceof Individual) {
			Individual i = (Individual)client;
			if (i.getId()==null) {
				item.setClient(individualDao.save(i));
			}
		}
		return super.save(item);
	}	
}
