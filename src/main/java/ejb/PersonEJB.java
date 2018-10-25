package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Person;
import entities.User_Group;

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
		
		User_Group group = new User_Group(); 
		group.setEmail(person.getEmail());
		group.setGroupname(User_Group.USER_GROUP);
		
		
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
