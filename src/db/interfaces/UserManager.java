package db.interfaces;

import java.util.List;


import pojos.Client;
import pojos.users.*;

public interface UserManager {
       

	
	boolean stablish_connection();
    public boolean closeConnection();
    
    
	public void createUser(User user);
	public void createRole(Role role);
	
	
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String username, String password);
	void disconnect();
	void connect();
	 
	
	
	
	
	
	
}
