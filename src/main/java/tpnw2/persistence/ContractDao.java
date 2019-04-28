package tpnw2.persistence;

import java.io.Serializable;
import java.util.function.Function;

import tpnw2.domain.Contract;

public interface ContractDao extends Dao<Contract, Serializable> {

	Function<ContractEntity, Contract> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Contract valueObject = new Contract();
		valueObject.setId(e.getId());
		valueObject.setPricePerKm(e.getPricePerKm());
		return valueObject;
	};
	
	Function<Contract, ContractEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		ContractEntity entity = new ContractEntity();
		entity.setId(v.getId());
		entity.setPricePerKm(v.getPricePerKm());
		return entity;
	};
}
