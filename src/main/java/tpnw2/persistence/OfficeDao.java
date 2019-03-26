package tpnw2.persistence;

import java.util.function.Function;

import tpnw2.domain.Office;

public interface OfficeDao {

	Function<OfficeEntity, Office> toValueObject = e -> {
		Office valueObject = new Office();
		valueObject.setId(e.getId());
		valueObject.setCity(e.getCity());
		return valueObject;
	};
	
	Function<Office, OfficeEntity> toEntity = v -> {
		OfficeEntity entity = new OfficeEntity();
		entity.setId(v.getId());
		entity.setCity(v.getCity());
		return entity;
	};
}
