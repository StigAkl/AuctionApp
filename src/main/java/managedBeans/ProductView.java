package managedBeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ejb.ProductEJB;
import entities.Product;

@Named(value="productView")
@RequestScoped
public class ProductView {

	
	@EJB
	ProductEJB ejb; 
	
	private int id; 
	private int highestBid; 
	private Product product; 

	
	public void findProductById() {
		product = ejb.findById(this.id);
		highestBid = ejb.findHighestBidOnProduct(this.product); 
	}
	
	public void setId(int id) {
		this.id = id; 
	}
	
	public int getId() {
		return this.id; 
	}
	
	public Product getProduct() {
		return this.product; 
	}
	
	public void setProduct(Product product) {
		this.product = product; 
	}
	
	public void setHighestBid(int highestBid) {
		this.highestBid = highestBid; 
	}
	
	public int getHighestBid() {
		return this.highestBid; 
	}
	
}
