package pojos;




public class Chocolate {
	
	private Integer id;
	private String name;
	private String type;
	private Float cocoa_perc;
	private String flavour;
	private Integer unit;
	private String shape;
	
	
	//CONSTRUCTOR
	
	public Chocolate(Integer id, String name, String type, Float cocoa_perc, String flavour, Integer unit,
			String shape) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.cocoa_perc = cocoa_perc;
		this.flavour = flavour;
		this.unit = unit;
		this.shape = shape;
	}
	//GETTERS Y SETTERS
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Float getCocoa_perc() {
		return cocoa_perc;
	}
	public void setCocoa_perc(Float cocoa_perc) {
		this.cocoa_perc = cocoa_perc;
	}
	public String getFlavour() {
		return flavour;
	}
	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	@Override
	public String toString() {
		return "Chocolate [id=" + id + ", name=" + name + ", type=" + type + ", cocoa_perc=" + cocoa_perc + ", flavour="
				+ flavour + ", unit=" + unit + ", shape=" + shape + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Chocolate other = (Chocolate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}



