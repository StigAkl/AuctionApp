package managedBeans;

import java.security.Principal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ejb.AuctionDAO;

@Named(value="bidView")
@RequestScoped
public class BidView {

	
	@EJB
	AuctionDAO ejb; 
	
	private int bid; 
	private int pid; 
	
	public String placeBid() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			Principal principal = request.getUserPrincipal(); 
			String email = principal.getName(); 
			
			String message = ejb.addBid(email, bid, pid); 
			
			Flash flash = context.getExternalContext().getFlash();
			flash.setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	
	
		return "/auction"; 
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
}
