package pojos;

import java.sql.Date;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import xml.utils.SQLDateAdapter;

import java.io.Serializable;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)// we are going to put annotations into a class (en los atributos)
@XmlRootElement(name = "client")
@XmlType(propOrder = {"name", "cellphone", "email", "adress", "dob"})
public class Client implements Serializable {
	
	
	private static final long serialVersionUID =6891296751142184360L;
	
	@XmlTransient //doesnt persit, isnt stored, this field is going to be ignored in xml.
	private int id;
	@XmlAttribute//
	private String name;
	@XmlElement
	private Integer cellphone;
	@XmlElement
	private String email;
	@XmlElement
	private String adress;

	@XmlJavaTypeAdapter(SQLDateAdapter.class)// a class that gets a date and returns a string(marshall)vs(unmarshall)

	private Date dob;
	
	
	//CONSTRUCTOR WITH ID AND NAME 
	public Client(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public Client(Integer id, String name, Integer cellphone, String email, String adress, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.adress = adress;
		this.dob = dob;
	}




	public Client(String name, Integer cellphone, String email, String adress, Date dob) {
		super();
		this.name = name;
		this.cellphone = cellphone;
		this.email = email;
		this.adress = adress;
		this.dob = dob;
	}
	//CREACION DEL CONSTRUCTOR 
			public Client() {
				super();
			}


	//getters y setters
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", cellphone=" + cellphone + ", email=" + email + ", adress="
				+ adress + ", dob=" + dob + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	
	
	
	
	

}
