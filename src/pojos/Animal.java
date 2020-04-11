package pojos;

import java.io.Serializable;
import java.sql.Date;

public class Animal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2133854860664818383L;


	private Integer id_a;
	private String name_a;
	private Date dob_a;
	private String specie_a;
	private String colour;
	private String country;
	
	
	//Contructors calling variables, one by default, one with id_a & one without it
	public Animal () {
		super();
	}
	
	public Animal (Integer id_a, String name_a, Date dob_a, String specie_a, String colour, String country) {
		super();
		this.id_a= id_a;
		this.name_a = name_a;
		this.dob_a = dob_a;
		this.specie_a =specie_a;
		this.colour = colour;
		this.country = country;
		
	}
	
	public Animal (String name_a, Date dob_a, String specie_a, String colour, String country) {
		super();
		this.name_a = name_a;
		this.dob_a = dob_a;
		this.specie_a =specie_a;
		this.colour = colour;
		this.country = country;
		
	}
	
	//Gets and Sets of Animals
	public Integer getId_a() {
		return id_a;
	}
	public void setId_a(Integer id_a) {
		this.id_a = id_a;
	}
	public String getName_a() {
		return name_a;
	}
	public void setName_a(String name_a) {
		this.name_a = name_a;
	}
	public Date getDob_a() {
		return dob_a;
	}
	public void setDob_a(Date dob_a) {
		this.dob_a = dob_a;
	}
	public String getSpecie_a() {
		return specie_a;
	}
	public void setSpecie_a(String specie_a) {
		this.specie_a = specie_a;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	//What the console shows if we call Animals
	public String toString() {
		return "Animal id= " +id_a +"/s"
			+"Animal name= " +name_a +"/s"
			+"Animal date of birth= "+dob_a +"/s"
			+"Kind of animal= " +specie_a +"/s"
			+"Colour of the animal= " +colour +"/s"
			+"Country from which it comes= " +country;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_a == null) ? 0 : id_a.hashCode());
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
		Animal other = (Animal) obj;
		if (id_a == null) {
			if (other.id_a != null)
				return false;
		} else if (!id_a.equals(other.id_a))
			return false;
		return true;
	}
	
}
