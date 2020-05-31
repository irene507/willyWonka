package db.interfaces;

import java.util.List;


import pojos.Client;
import pojos.users.*;

public interface UserManager {
       

	
	
    
	public void createUser(User user);
	public void createRole(Role role);
	
	
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String username, String password);
	public void disconnect();
	public void connect();
	public List<User> getUsers();
	public void deleteUser(int id);
	public void deleteRole(int id);
	public void updateUser(User user, int id);
	
	
	
	
	
}
