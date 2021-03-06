package tpnw2.persistence;

import java.util.Arrays;
import java.util.function.Function;

import tpnw2.domain.Company;
import tpnw2.domain.CompanyCriteria;

public interface CompanyDao extends Dao<Company, CompanyCriteria> {

	Function<CompanyEntity, Company> toValueObject = e -> {
		if (e==null) {
			return null;
		}
		Company valueObject = new Company();
		valueObject.setId(e.getId());
		valueObject.setPhone(e.getPhone());
		valueObject.setName(e.getName());
		valueObject.setContract(ContractDao.toValueObject.apply(e.getContracts().get(0)));
		return valueObject;
	};
	
	Function<Company, CompanyEntity> toEntity = v -> {
		if (v==null) {
			return null;
		}
		CompanyEntity entity = new CompanyEntity();
		entity.setId(v.getId());
		entity.setPhone(v.getPhone());
		entity.setName(v.getName());
		entity.setContracts(Arrays.asList(ContractDao.toEntity.apply(v.getContract())));
		return entity;
	};
}
