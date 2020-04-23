package pojos;

import java.sql.Date;

public class Client {
	
	private Integer id;
	private String name;
	private Integer cellphone;
	private String email;
	private String adress;
	private Date dob;
	
	//constructor con id y name
	
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
	
	

}
