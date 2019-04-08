package tpnw2.persistence;

import java.util.function.Function;

import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;

public interface EmployeeDao extends Dao<Employee, EmployeeCriteria> {

	Function<EmployeeEntity, Employee> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Employee valueObject = new Employee();
		valueObject.setId(e.getId());
		valueObject.setFirstname(e.getFirstname());
		valueObject.setLastname(e.getLastname());
		return valueObject;
	};
	
	Function<Employee, EmployeeEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(v.getId());
		entity.setFirstname(v.getFirstname());
		entity.setLastname(v.getLastname());
		return entity;
	};
}
