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

import tpnw2.domain.Individual;

@Repository
@Transactional
public class IndividualDaoImpl extends DaoBase<Individual, Serializable, IndividualEntity> implements IndividualDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}	

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<IndividualEntity> root, Serializable criteria) {	
		return null;
	}

	@Override
	protected Class<IndividualEntity> getEntityClass() {
		return IndividualEntity.class;
	}

	@Override
	protected Function<IndividualEntity, Individual> valueObjectConversion() {
		return toValueObject;
	}

	@Override
	protected Function<Individual, IndividualEntity> entityConversion() {
		return toEntity;
	}

	@Override
	protected Object getKey(Individual item) {
		// TODO Auto-generated method stub
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Individual item) {
		return item.getId()==null;
	}

}
