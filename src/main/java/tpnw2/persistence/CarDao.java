package tpnw2.persistence;

import java.util.function.Function;

import tpnw2.domain.Car;
import tpnw2.domain.CarCriteria;

public interface CarDao extends Dao<Car, CarCriteria> {

	Function<CarEntity, Car> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Car valueObject = new Car();
		valueObject.setId(e.getId());
		valueObject.setNumberplate(e.getNumberplate());
		valueObject.setOwner(EmployeeDao.toValueObjectLight.apply(e.getOwner()));
		return valueObject;
	};
	
	Function<Car, CarEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		CarEntity entity = new CarEntity();
		entity.setId(v.getId());
		entity.setNumberplate(v.getNumberplate());
		entity.setOwner(EmployeeDao.toEntity.apply(v.getOwner()));
		return entity;
	};
}
