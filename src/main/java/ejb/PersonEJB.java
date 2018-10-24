package ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Person;

public class PersonEJB {

	@PersistenceContext(unitName="AuctionApp")
	EntityManager em; 
	
	public Person findById(String id) {
		TypedQuery<Person> query = em.createNamedQuery("findByEmail", Person.class); 
		query.setParameter("email", id); 
		
		Person p = null; 
		try {
			p = query.getSingleResult(); 
		} catch(Exception e) {
			
			return null; 
		}
		
		return p; 
	}
}
