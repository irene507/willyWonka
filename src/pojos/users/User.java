package pojos.users;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
//if you cannot do this import that means that the libraries that we have imported ("eclipselink" and "javaxpersistence")
//are not in the correct lib so they are not link 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name= "users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -41326542348884837L;
	
	@Id
	@GeneratedValue(generator="users")
	@TableGenerator(name="users", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="users")
	private Integer id;
	//we want the username to be unique 
	@Column(unique = true)
	private String username;
	@Lob //the anotation for blob
	private byte[] password;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;// porque user esta conectado con role en el uml
	

	
	public User() {
		super();
	} 
	
	public User(String username, byte[] password, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + Arrays.toString(password) + ", role="
				+ role + "]";
	}
	
	
	
	

}
