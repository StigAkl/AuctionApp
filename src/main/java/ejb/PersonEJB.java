package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Group;
import entities.Person;

@Stateless
public class PersonEJB {

	@PersistenceContext(unitName="AuctionApp")
	EntityManager em; 
	
	
	public Person createPerson(Person person) {
		try {
			person.setPassword(person.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Group group = new Group(); 
		group.setEmail(person.getEmail());
		group.setGroupname(Group.USER_GROUP);
		
		
		em.persist(person);
		em.persist(group);
		
		return person; 
	}
	
	
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
