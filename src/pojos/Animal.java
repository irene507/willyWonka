package pojos;

import java.sql.Date;

public class Animal {
	
	private Integer id;
	private String name;
	private String country;
	private String colour;
	private String specie;
	private Date dob;
	
	//Constructor
	public Animal(Integer id, String name, String specie) {
		super();
		this.id = id;
		this.name = name;
		this.specie = specie;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSpecie() {
		return specie;
	}
	public void setSpecie(String specie) {
		this.specie = specie;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}


	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", country=" + country + ", colour=" + colour + ", specie="
				+ specie + ", dob=" + dob + "]";
	}
	
	//tostring
	
	

}
