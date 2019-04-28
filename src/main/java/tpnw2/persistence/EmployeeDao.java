package tpnw2.persistence;

import java.util.function.Function;
import java.util.stream.Collectors;

import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;

public interface EmployeeDao extends Dao<Employee, EmployeeCriteria> {
	
	Employee findByEmail(String email);

	Function<EmployeeEntity, Employee> toValueObjectUltraLight = e -> {
		if (e==null) {
			return null;
		}
		Employee valueObject = new Employee();
		valueObject.setId(e.getId());
		valueObject.setFirstname(e.getFirstname());
		valueObject.setLastname(e.getLastname());
		valueObject.setEmail(e.getEmail());
		return valueObject;
	};
	
	Function<EmployeeEntity, Employee> toValueObjectLight = e -> {
		if (e==null) {
			return null;
		}
		Employee valueObject = new Employee();
		valueObject.setId(e.getId());
		valueObject.setFirstname(e.getFirstname());
		valueObject.setLastname(e.getLastname());
		valueObject.setEmail(e.getEmail());
		valueObject.setPassword(e.getPassword());
		valueObject.setAdministrator(e.getAdministrator());
		valueObject.setOffice(OfficeDao.toValueObject.apply(e.getOffice()));
		return valueObject;
	};
	
	Function<EmployeeEntity, Employee> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Employee valueObject = toValueObjectLight.apply(e);
		if (e.getCars()!=null) {
			valueObject.setCars(e.getCars().stream().map(CarDao.toValueObject).collect(Collectors.toList()));
		}
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
		entity.setEmail(v.getEmail());
		entity.setPassword(v.getPassword());
		entity.setAdministrator(v.isAdministrator());
		entity.setOffice(OfficeDao.toEntity.apply(v.getOffice()));
		if (v.getCars()!=null) {
			entity.setCars(v.getCars().stream().map(CarDao.toEntity).collect(Collectors.toList()));
		}		
		return entity;
	};
}
