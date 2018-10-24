package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="person")
@Entity
@NamedQueries({
	@NamedQuery(name="findByEmail", query="SELECT p FROM Person p WHERE p.email = :email")
})

@Table(name="Person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	private String email;

	private String fname;
	
	private String lname;

	private String password;
	
	private String phonenr;
	
	private float rating;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="PERSON_ID")
	private List<Feedback> feedback = new ArrayList<>();
	
	@OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
	@JoinColumn(name="PERSON_ID")
	private List<Bid> bid = new ArrayList<>();
	
	@OneToMany(mappedBy="person",cascade = CascadeType.ALL)
	@JoinColumn(name="PERSON_ID")
	private List<Product> product = new ArrayList<>();
	
	public Person() {
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhonenr() {
		return phonenr;
	}

	public void setPhonenr(String phonenr) {
		this.phonenr = phonenr;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	@XmlTransient
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@XmlTransient
	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback.add(feedback);
	}
	
	public List<Bid> getBid() {
		return bid;
	}

	public void setBid(List<Bid> bid) {
		this.bid = bid;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public void addProduct(Product prod) {
		product.add(prod);
		prod.setPerson(this);
	}
	
	public void addBid(Bid bids) {
		bid.add(bids);
		bids.setPerson(this);
	}

	
	
 
}