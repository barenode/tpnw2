package tpnw2.persistence;

import java.io.Serializable;
import java.util.function.Function;

import tpnw2.domain.Individual;

public interface IndividualDao extends Dao<Individual, Serializable> {

	Function<IndividualEntity, Individual> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Individual valueObject = new Individual();
		valueObject.setId(e.getId());
		valueObject.setPhone(e.getPhone());
		valueObject.setFirstname(e.getFirstname());
		valueObject.setLastname(e.getLastname());
		return valueObject;
	};
	
	Function<Individual, IndividualEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		IndividualEntity entity = new IndividualEntity();
		entity.setId(v.getId());
		entity.setPhone(v.getPhone());
		entity.setFirstname(v.getFirstname());
		entity.setLastname(v.getLastname());
		return entity;
	};
}
