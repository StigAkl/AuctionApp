package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Address;
import entities.Bid;
import entities.Category;
import entities.Feedback;
import entities.Person;
import entities.Product;


@Startup
@Singleton
public class RestPopulate {
	
    // Injected database connection:
	@PersistenceContext(unitName="AuctionApp")
    private EntityManager em;
	
	@PostConstruct
	public void Start() {
		
		System.out.print("KAKE!");
		
		Address add1 = new Address();
		add1.setCity("Bergen");
		add1.setStreet("Tordenskjoldsgate 2");
		add1.setPostnumber(5031);
	
		Address add2 = new Address();
		add2.setCity("Drobak");
		add2.setStreet("Skorkeberg alle 4");
		add2.setPostnumber(1447);
		
		Category cat1 = new Category();
		cat1.setName("Kalkuner");
		
		Category cat2 = new Category();
		cat2.setName("Computer");
		
		Feedback f1 = new Feedback();
		f1.setDescription("Bra");
		f1.setRating(4);
		
		Feedback f2 = new Feedback();
		f2.setDescription("Dårlig");
		f2.setRating(1);
		
		Bid b1 = new Bid();
		b1.setPrice(100);
		
		Bid b2 = new Bid();
		b2.setPrice(500);
		
		Product prod1 = new Product();
		prod1.setCategory(cat1);
		prod1.setName("Bronsekalkun");
		prod1.setDescription("Vakker og fin");
		prod1.setPrice(800);
		prod1.setPublished(true);
		prod1.setSold(false);
		prod1.addBid(b1);
		
		Product prod2 = new Product();
		prod2.setCategory(cat2);
		prod2.setName("Alienware");
		prod2.setDescription("lekker");
		prod2.setPrice(2000);
		prod2.setPublished(true);
		prod2.setSold(true);
		prod2.addBid(b2);
		
		Person p1 = new Person();
		p1.setAddress(add1);
		p1.setEmail("haakon@hotmail");
		p1.setFname("Haakon");
		p1.setLname("Reme");
		p1.setPassword("hunter2");
		p1.setRating(3);
		p1.getFeedback().add(f1);
		p1.setPhonenr("84736283");
		p1.addBid(b1);
		p1.addProduct(prod2);
		
		Person p2 = new Person();
		p2.setAddress(add2);
		p2.setEmail("stg@hotmail.no");
		p2.setFname("Stig");
		p2.setLname("Tomat");
		p2.setPassword("FB8C2E2B85CA81EB4350199FADDD983CB26AF3064614E737EA9F479621CFA57A");
		p2.setRating(2);
		p2.getFeedback().add(f2);
		p2.setPhonenr("99999999");
		p2.addBid(b2);
		p2.addProduct(prod1);
		
		em.persist(add1);
		em.persist(cat1);
		em.persist(p1);
		em.persist(p2);
		em.persist(prod1);
		em.persist(prod2);
		
	}

}