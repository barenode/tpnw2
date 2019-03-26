package tpnw2.persistence;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Office;

//@Repository
//@Transactional
@Service
public class OfficeDaoImpl implements OfficeDao {

	List<Office> offices =  Arrays.asList(
			new Office(1, "Paraha"),
			new Office(2, "Brno"),
			new Office(3, "Hradec"),
			new Office(4, "Ostrava"),
			new Office(5, "Jicin"),
			new Office(6, "Nova Paka"),
			new Office(7, "Decin"),
			new Office(8, "Litomerice"),
			new Office(9, "Paraha"),
			new Office(10, "Hradec"),
			new Office(11, "Brno"),
			new Office(12, "Ostrava"),
			new Office(13, "Jicin"),
			new Office(14, "Nova Paka"),
			new Office(15, "Decin"),
			new Office(16, "Litomerice"));
	
//	@PersistenceContext
//	private EntityManager em;
	
	public long count() {
		//return (Long)em.createQuery("select count(*) from OfficeEntity").getSingleResult();		
		return offices.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Office> findAll() {
//		List<OfficeEntity> entitites = em.createQuery("from OfficeEntity").getResultList();		
//		return entitites.stream().map(toValueObject).collect(toList());
//		
		return offices;
	}
}
