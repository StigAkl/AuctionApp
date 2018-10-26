package managedBeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ejb.ProductEJB;
import entities.Product;

@Named(value="productView")
@RequestScoped
public class ProductView {

	
	@EJB
	ProductEJB ejb; 
	
	private Integer id = -1; 
	private int highestBid; 
	private Product product; 

	
	public void findProductById() {
		
		if(this.id == -1) {
			return; 
		}
		
		product = ejb.findById(this.id);
		highestBid = ejb.findHighestBidOnProduct(this.product); 
	}
	
	public String bid() {
		
		return "user/bid?faces-redirect=true&pid=" + id; 
		
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
