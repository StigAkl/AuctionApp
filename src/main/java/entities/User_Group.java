package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_group")
public class User_Group implements Serializable {

	private static final long serialVersionUID = 5806834199889041928L;
	
	public static final String USER_GROUP = "user"; 
	
	@Id
	@Column(nullable=false, length=255)
	private String email; 
	
	@Column(nullable=false, length=32)
	private String groupname; 
	
	
	public User_Group() {}
	
	public User_Group(String email, String groupname) {
		this.email = email;
		this.groupname = groupname; 
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	
}
