package rest;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSSessionMode;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Address;
import entities.Bid;
import entities.Product;
import jms.Publisher2;



@Path("/auctions")
@Stateless
public class KakeService {
	
	@PersistenceContext(unitName = "AuctionApp")
	EntityManager em;
	
    private Publisher2 topic = new Publisher2();
    

	
	@SuppressWarnings("unchecked")
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Product> getProductt() throws Exception {
	 
		Query query = em.createQuery("Select p from Product p"); 
		topic.activateTopic(query.getResultList());
		return query.getResultList(); 
		
	}
	
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Product getProduct(@PathParam("id") Long id) {
		
		int pid = id.intValue(); 
		Product p = em.find(Product.class, pid); 
		
		return p; 
	}
	
	@GET
	@Path("{id}/bids")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Bid> getBidsFromPId(@PathParam("id") Long id) {
		int pid = id.intValue(); 
		
		Product p = em.find(Product.class, pid);
		
		return p.getBid(); 
	}
	
	@GET
	@Path("{aid}/bids/{bid}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Bid getBidFromProduct(@PathParam("aid") Long aid, @PathParam("bid") Long bid) {
		int pid = aid.intValue(); 
		int bidid = bid.intValue(); 
		Product p = em.find(Product.class, pid); 
		
		List<Bid> bids = p.getBid(); 
		
		for(int i = 0; i < bids.size(); i++) {
			if (bids.get(i).getId() == bidid) {
				return bids.get(i); 
			}
		}
		
		return null; 
	}
}
