package tpnw2.persistence;

import java.io.Serializable;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Contract;

@Repository
@Transactional
public class ContractDaoImpl extends DaoBase<Contract, Serializable, ContractEntity> implements ContractDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<ContractEntity> root, Serializable criteria) {
		return null;
	}

	@Override
	protected Class<ContractEntity> getEntityClass() {
		return ContractEntity.class;
	}

	@Override
	protected Function<ContractEntity, Contract> valueObjectConversion() {
		return toValueObject;
	}

	@Override
	protected Function<Contract, ContractEntity> entityConversion() {
		return toEntity;
	}

	@Override
	protected Object getKey(Contract item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Contract item) {
		return item.getId()==null;
	}	
}
