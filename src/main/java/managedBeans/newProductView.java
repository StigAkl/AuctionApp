package managedBeans;

import java.io.Serializable;
import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ejb.AuctionDAO;
import entities.Product;

public class newProductView implements Serializable{
	
	@Named(value="newProductView")
	@SessionScoped
	private static final long serialVersionUID = 1685823449195612778L;
	
	@EJB
	AuctionDAO  dao;
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	private String description;
	private String price;
	private String category;
	private boolean published;
	
	public String submit() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal(); 
			String email = principal.getName(); 
			
		
		Product prod = new Product();
		prod.setCategory(dao.findCategory("Kalkuner"));
		prod.setName(name);
		prod.setPrice(Integer.parseInt(price));
		prod.setDescription(description);
		prod.setPerson(dao.findPerson(email));
		
		dao.addProduct(prod);
		
		return "auction";
		
		
		
	}

}
