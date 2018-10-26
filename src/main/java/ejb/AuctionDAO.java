package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Bid;
import entities.Category;
import entities.Person;
import entities.Product;

@Stateless
public class AuctionDAO {
	
	@PersistenceContext(unitName="AuctionApp")
    private EntityManager em;
	
	public Product findProduct(int id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> findProducts(){
		Query q = em.createQuery("SELECT p FROM Product p WHERE p.sold = FALSE AND p.published = TRUE", Product.class);
		return q.getResultList();
	}
	public String addBid(String email, int bid, int productid) {
		Query q = em.createQuery("SELECT b FROM Bid b WHERE b.product.id = :id ORDER BY b.price DESC").setParameter("id", productid);
		q.setFirstResult(0);
		q.setMaxResults(1);
		Bid max = (Bid) q.getSingleResult();
		if (max.getPrice() >= bid) {
			return "Bid to low, highest is: " + max.getPrice();
		}
		else {
			Product p = em.find(Product.class, productid);
			Person pers = em.find(Person.class, email);
			
			Bid b1 = new Bid();
			b1.setPrice(bid);
			
			p.addBid(b1);
			pers.addBid(b1);
			em.persist(p);
			em.persist(pers);
			
			return "Bid is the highest at " + bid;
		}
		
	}
	
	public Category findCategory(String cat) {
		return em.find(Category.class, cat);
	}
	
	public Person findPerson(String email) {
		return em.find(Person.class, email);
	}
	
	public void addProduct(Product prod) {
		em.persist(prod);
	}

}
