package tpnw2.persistence;

import java.util.function.Function;

import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;
import tpnw2.domain.OrderStatus;

public interface OrderDao extends Dao<Order, OrderCriteria> {
	
	Function<OrderEntity, Order> toValueObject = e -> {
		Order valueObject = new Order();
		valueObject.setId(e.getId());
		valueObject.setStatus(OrderStatus.forCode(e.getStatus()));
		valueObject.setDepartureAddress(e.getDepartureAddress());
		valueObject.setDepartureDate(e.getDepartureDate());
		valueObject.setDestinationAddress(e.getDestinationAddress());
		valueObject.setOffice(OfficeDao.toValueObject.apply(e.getOffice()));
		valueObject.setDriver(EmployeeDao.toValueObject.apply(e.getDriver()));
		valueObject.setAdministrator(EmployeeDao.toValueObjectLight.apply(e.getAdministrator()));
		valueObject.setClient(ClientDao.toValueObject.apply(e.getClient()));
		valueObject.setDistance(e.getDistance());
		valueObject.setPrice(e.getPrice());
		valueObject.setCar(CarDao.toValueObject.apply(e.getCar()));
		return valueObject;
	};
	
	Function<Order, OrderEntity> toEntity = v -> {
		OrderEntity entity = new OrderEntity();
		entity.setId(v.getId());
		if (v.getStatus()!=null) {
			entity.setStatus(v.getStatus().getCode());
		}	
		entity.setDepartureAddress(v.getDepartureAddress());
		entity.setDepartureDate(v.getDepartureDate());
		entity.setDestinationAddress(v.getDestinationAddress());
		entity.setOffice(OfficeDao.toEntity.apply(v.getOffice()));
		entity.setDriver(EmployeeDao.toEntity.apply(v.getDriver()));
		entity.setAdministrator(EmployeeDao.toEntity.apply(v.getAdministrator()));
		entity.setClient(ClientDao.toEntity.apply(v.getClient()));
		entity.setDistance(v.getDistance());
		entity.setPrice(v.getPrice());
		entity.setCar(CarDao.toEntity.apply(v.getCar()));
		return entity;
	};
}
