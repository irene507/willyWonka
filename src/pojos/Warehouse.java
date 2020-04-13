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
	
	
	
	

}
