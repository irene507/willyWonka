package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;


//For the XML
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "Milk")

@XmlType(propOrder= {"name, type"})


public class Milk implements Serializable {
	//<element attribute = "value">text or other elements</element> 
	
	 //It is something that does not persist,so is not stored.
    //--->So this field is going to be ignore by the XML 
	
	
	/**
	 * 
	 */
	
	//Necessary to implement the Serializable class
	private static final long serialVersionUID = -1546844702548025820L;

	
	//Creation of variables
    @XmlAttribute
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String type;
	@XmlTransient
	
	//For the many to many relation
	private List<Chocolate> chocolates;
	
	
	
	//Constructor with all variables
	public Milk(Integer id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		chocolates = new ArrayList<Chocolate>();
	}
	
	
	
	//Constructor without the id
	public Milk(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		chocolates = new ArrayList<Chocolate>();
	}

	
	//Constructor with only id
	public Integer getId() {
		return id;
	}

	
	
	//Getters y Setters
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//ToString method
	@Override
	public String toString() {
		return "Milk [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	//Hashcode method (generates a unique variable for each entity)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	//Equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Milk other = (Milk) obj;
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
	
	

}
