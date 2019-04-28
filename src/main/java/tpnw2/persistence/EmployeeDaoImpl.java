package tpnw2.persistence;

import java.util.List;
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
		Predicate p = null;
		if (criteria.getOffice()!=null) {
			p = builder.equal(root.get("office").<String>get("id"), criteria.getOffice().getId());	
		}
		if (criteria.getLastname()!=null) {
			Predicate lastnamePredicate = builder.like(root.get("lastname"), criteria.getLastname() + "%");
			if (p==null) {
				p = lastnamePredicate;
			} else {
				p = builder.and(p, lastnamePredicate);
			}
		}			
		if (criteria.getEmail()!=null) {
			Predicate emailPredicate = builder.equal(root.get("email"), criteria.getEmail());
			if (p==null) {
				p = emailPredicate;
			} else {
				p = builder.and(p, emailPredicate);
			}
		}					
		return p;
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

	@Override
	public Employee findByEmail(String email) {
		List<Employee> employees = this.findAll(new EmployeeCriteria(email));
		if (!employees.isEmpty()) {
			return employees.get(0);
		} else {
			return null;
		}		
	}
}
