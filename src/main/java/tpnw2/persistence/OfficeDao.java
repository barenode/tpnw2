package tpnw2.persistence;

import java.util.function.Function;

import tpnw2.domain.Office;
import tpnw2.domain.OfficeCriteria;

public interface OfficeDao extends Dao<Office, OfficeCriteria> {

	Function<OfficeEntity, Office> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Office valueObject = new Office();
		valueObject.setId(e.getId());
		valueObject.setCity(e.getCity());
		return valueObject;
	};
	
	Function<Office, OfficeEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		OfficeEntity entity = new OfficeEntity();
		entity.setId(v.getId());
		entity.setCity(v.getCity());
		return entity;
	};
}
