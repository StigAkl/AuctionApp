package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="address")
@Entity
@Table(name="address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String street;
	
	private String city;

	private int postnumber;

	
	public Address() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostnumber() {
		return postnumber;
	}

	public void setPostnumber(int postnumber) {
		this.postnumber = postnumber;
	}
	
	@Override
	public String toString() {
		return "Tweet [id=" + id + ", City=" + city + ", Street=" + street + ", pstnumber=" + postnumber + "]";
	}
	
 
}