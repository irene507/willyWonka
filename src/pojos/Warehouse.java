package pojos;

public class Warehouse {
	private Integer id;
	private String name;
	private Integer corridor;
	private Integer shelve;
	
	//CONSTRUCTOR CON ID Y NAME SOLO
	public Warehouse(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	
	
	public Warehouse(Integer id, String name, Integer corridor, Integer shelve) {
		super();
		this.id = id;
		this.name = name;
		this.corridor = corridor;
		this.shelve = shelve;
	}
	
	//CREACION DEL CONSTRUCTOR 
			public Warehouse() {
				super();
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
	public Integer getCorridor() {
		return corridor;
	}
	public void setCorridor(Integer corridor) {
		this.corridor = corridor;
	}
	public Integer getShelve() {
		return shelve;
	}
	public void setShelve(Integer shelve) {
		this.shelve = shelve;
	}
	





	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", corridor=" + corridor + ", shelve=" + shelve + "]";
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corridor == null) ? 0 : corridor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shelve == null) ? 0 : shelve.hashCode());
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
		Warehouse other = (Warehouse) obj;
		if (corridor == null) {
			if (other.corridor != null)
				return false;
		} else if (!corridor.equals(other.corridor))
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
		if (shelve == null) {
			if (other.shelve != null)
				return false;
		} else if (!shelve.equals(other.shelve))
			return false;
		return true;
	}
	
	
	
	
	
	

}
