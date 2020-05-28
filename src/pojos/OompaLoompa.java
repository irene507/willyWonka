package pojos;

import java.io.Serializable;

import java.sql.Date;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.*;


@XmlAccessorType(XmlAccessType.FIELD) //put annotations in the fields of the class
@XmlRootElement(name="oompaloompa")
@XmlType(propOrder = {"name", "cellphone","email","address","dob"})
public class OompaLoompa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8733922470714588273L;
	@XmlTransient
	private Integer id;
	@XmlAttribute 
	private String name;
	@XmlElement
	private Integer cellphone;
	@XmlElement
	private String email;
	@XmlElement
	private String address;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	
	
	//Constructor usando id y name solo
	
	public OompaLoompa(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public OompaLoompa(Integer id, String name, Integer cellphone, String email, String address, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.cellphone=cellphone;
		this.email=email;
		this.address=address;
		this.dob = dob;
	}
	public OompaLoompa(String name, Integer cellphone, String email, String address, Date dob) {
		super();
		this.name = name;
		this.cellphone=cellphone;
		this.email=email;
		this.address=address;
		this.dob = dob;
	}
	public OompaLoompa() {
		super();
	}
	
	
	//Getters y setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCellphone() {
		return cellphone;
	}
	public void setCellphone(Integer cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	//hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OompaLoompa other = (OompaLoompa) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	//Tostring
	@Override
	public String toString() {
		return "OompaLoompa [id=" + id + ", name=" + name + ", cellphone=" + cellphone + ", email=" + email
				+ ", address=" + address + ", dob=" + dob + "]";
	}
	
	
	
	
	

}
