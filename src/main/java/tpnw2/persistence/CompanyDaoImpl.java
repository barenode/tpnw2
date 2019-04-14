package tpnw2.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Company;
import tpnw2.domain.CompanyCriteria;

@Repository
@Transactional
public class CompanyDaoImpl extends DaoBase<Company, CompanyCriteria, CompanyEntity> implements CompanyDao {

	@PersistenceContext
	private EntityManager em;	
	
	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	protected Predicate buildCriteria(CriteriaBuilder builder, Root<CompanyEntity> root, CompanyCriteria criteria) {
		if (criteria.getName()!=null) {
			return builder.like(root.get("name"), criteria.getName() + "%");
		} else {
			return null;
		}
	}

	@Override
	protected Class<CompanyEntity> getEntityClass() {
		return CompanyEntity.class;
	}

	@Override
	protected Function<CompanyEntity, Company> valueObjectConversion() {
		return CompanyDao.toValueObject;
	}

	@Override
	protected Function<Company, CompanyEntity> entityConversion() {
		return CompanyDao.toEntity;
	}

	@Override
	protected Object getKey(Company item) {
		return item.getId();
	}

	@Override
	protected boolean isNewItem(Company item) {
		return item.getId() == null;
	}	
}
