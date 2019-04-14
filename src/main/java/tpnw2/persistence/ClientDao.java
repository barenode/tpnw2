package tpnw2.persistence;

import java.io.Serializable;
import java.util.function.Function;

import tpnw2.domain.Client;
import tpnw2.domain.Company;
import tpnw2.domain.Individual;

public interface ClientDao extends Dao<Client, Serializable> {

	Function<ClientEntity, Client> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		if (e instanceof IndividualEntity) {
			return IndividualDao.toValueObject.apply((IndividualEntity)e);
		} else {
			return CompanyDao.toValueObject.apply((CompanyEntity)e);
		}
	};
	
	Function<Client, ClientEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		if (v instanceof Individual) {
			return IndividualDao.toEntity.apply((Individual)v);
		} else {
			return CompanyDao.toEntity.apply((Company)v);
		}
	};
}
