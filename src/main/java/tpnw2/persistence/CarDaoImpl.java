package tpnw2.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Car;
import tpnw2.domain.CarCriteria;

@Repository
@Transactional
public class CarDaoImpl extends DaoBase<Car, CarCriteria, CarEntity> implements CarDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<CarEntity> root, CarCriteria criteria) {
		if (criteria.getNumberplate()==null && criteria.getOwner()==null) {
			return null;
		}
		Predicate p = null;
		if (criteria.getNumberplate()!=null) {
			p = builder.like(root.get("numberplate"), criteria.getNumberplate() + "%");
		}
		if (criteria.getOwner()!=null) {
			if (p==null) {
				p = builder.equal(root.get("owner").<String>get("id"), criteria.getOwner().getId());
			} else {
				p = builder.and(
					p, builder.equal(root.get("owner").<String>get("id"), criteria.getOwner().getId())
				);
			}			
		}
		return p;
	}

	@Override
	protected Class<CarEntity> getEntityClass() {
		return CarEntity.class;
	}

	@Override
	protected Function<CarEntity, Car> valueObjectConversion() {
		return toValueObject;
	}

	@Override
	protected Function<Car, CarEntity> entityConversion() {
		return toEntity;
	}

	@Override
	protected Object getKey(Car item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Car item) {
		return item.getId()==null;
	}	
}
