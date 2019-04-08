package tpnw2.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;

@Repository
@Transactional
public class EmployeeDaoImpl extends DaoBase<Employee, EmployeeCriteria, EmployeeEntity> implements EmployeeDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}
	
	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<EmployeeEntity> root, EmployeeCriteria criteria) {
		if (criteria.getLastname()!=null) {
			return builder.like(root.get("lastname"), criteria.getLastname() + "%");
		} else {
			return null;
		}
	}

	@Override
	protected Class<EmployeeEntity> getEntityClass() {
		return EmployeeEntity.class;
	}

	@Override
	protected Function<EmployeeEntity, Employee> valueObjectConversion() {
		return toValueObject;
	}

	@Override
	protected Function<Employee, EmployeeEntity> entityConversion() {
		return toEntity;
	}

	@Override
	protected Object getKey(Employee item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Employee item) {
		return item.getId()==null;
	}
}
