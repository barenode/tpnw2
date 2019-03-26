package tpnw2.persistence;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Office;

@Repository
@Transactional
public class OfficeDaoImpl implements OfficeDao {

	@PersistenceContext
	private EntityManager em;
	
	public long count() {
		return (Long)em.createQuery("select count(*) from OfficeEntity").getSingleResult();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Office> findAll() {
		List<OfficeEntity> entitites = em.createQuery("from OfficeEntity").getResultList();		
		return entitites.stream().map(toValueObject).collect(toList());
	}
}
