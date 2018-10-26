package managedBeans;

import java.io.Serializable;
import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ejb.AuctionDAO;
import entities.Product;

@Named(value="newProductView")
@RequestScoped
public class NewProductView {
	
	
	@EJB
	AuctionDAO  dao;
	
	private String name;
	private String description;
	private String price;
	private boolean published;
	
	
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

	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}

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
		prod.setSold(false);
		prod.setPublished(true);
		
		dao.addProduct(prod);
		
		return "/auction";
		
		
		
	}

}
