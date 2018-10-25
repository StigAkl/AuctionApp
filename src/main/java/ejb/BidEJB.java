package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Bid;
import entities.Product;

@Stateless
public class BidEJB {

	@PersistenceContext(unitName="AuctionApp")
	EntityManager em; 
	
	public Bid findById(int id) {
		TypedQuery<Bid> query = em.createNamedQuery("findById", Bid.class); 
		query.setParameter("id", id); 
		
		Bid b = null; 
		try {
			b = query.getSingleResult(); 
		} catch(Exception e) {
			
			return null; 
		}
		
		return b; 
	}
	
	
	
	public int findHighestBidOnProduct(Product product) {
		
		Query q = em.createQuery("SELECT b FROM Bid b WHERE b.product.id = :id ORDER BY b.price DESC").setParameter("id", product.getId());
		q.setFirstResult(0);
		q.setMaxResults(1); 
		
		int highestBid = product.getPrice(); 
		
		try {
		Bid b = (Bid) q.getSingleResult(); 
		if(b.getPrice() > highestBid)
			highestBid = b.getPrice(); 
		} catch(Exception e) {
			return highestBid; 
		}
		
		return highestBid; 
	}
	
}
