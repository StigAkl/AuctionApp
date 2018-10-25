package managedBeans;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ejb.PersonEJB;
import entities.Person;

@Named(value="loginView")
@SessionScoped
public class LoginView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PersonEJB ejb; 
	
	private String email; 
	private String password;  
	
	private Person person; 
	
	public String login() { 
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
		
		try {
			request.login(email,  password);
		} catch(ServletException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
			
			return "auction"; 
		}
		
		Principal principal = request.getUserPrincipal(); 
		
		this.person = ejb.findById(principal.getName()); 
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		
		sessionMap.put("User", person); 
		
		if(request.isUserInRole("user")) {
			return "/user/loggedin"; 
		} else {
			return "login"; 
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
