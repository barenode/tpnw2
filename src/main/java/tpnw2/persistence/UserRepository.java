package tpnw2.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class UserRepository {

	@PersistenceContext
	private EntityManager em;
	
	public UserRepository() {
		super();		
	}
	
	public void findAll() {
		em.createQuery("from UserEntity").getResultList();
	}
}
