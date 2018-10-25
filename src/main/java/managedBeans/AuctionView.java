package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.AuctionDAO;
import entities.Product;

@Named(value="AuctionView")
@SessionScoped
public class AuctionView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private AuctionDAO dao = new AuctionDAO();
	
	private List<Product> products;
	
	
	public List<Product> getProducts() {
		
		products = dao.findProducts();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
