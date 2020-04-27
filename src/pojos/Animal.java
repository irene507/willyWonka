package pojos;

import java.sql.Date;

public class Animal {
	
	private Integer id;
	private String name;
	private String country;
	private String colour;
	private String specie;
	private Date dob;
	
//Constructors
	
	public Animal(Integer id, String name, String specie) {
		super();
		this.id = id;
		this.name = name;
		this.specie = specie;
	}
	
	
	//Constructor used in SearchByName
	
	public Animal(Integer id, String name, String country, String colour, String specie, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.colour = colour;
		this.specie = specie;
		this.dob = dob;
	}

	//Constructor used in addAnimal
	
	public Animal(String name, String country, String colour, String specie, Date dob) {
		super();
		this.name = name;
		this.country = country;
		this.colour = colour;
		this.specie = specie;
		this.dob = dob;
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

   
	//tostring
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", country=" + country + ", colour=" + colour + ", specie="
				+ specie + ", dob=" + dob + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((specie == null) ? 0 : specie.hashCode());
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
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
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
		if (specie == null) {
			if (other.specie != null)
				return false;
		} else if (!specie.equals(other.specie))
			return false;
		return true;
	}
	
	
	
	

}
