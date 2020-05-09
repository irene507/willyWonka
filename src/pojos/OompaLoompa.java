package pojos;

import java.sql.Date;

public class OompaLoompa {
	
	private Integer id;
	private String name;
	private Integer cellphone;
	private String email;
	private String address;
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
	
	
	

}
