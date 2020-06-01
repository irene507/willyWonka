package db.interfaces;

import java.util.List;


import pojos.Client;
import pojos.users.*;
/* 
 * All this part has been done with JPA... So first of all we need to create an Entity Manager which 
 *fulfills the same role as a Connection in JDBC
 */
public interface UserManager {
    //CONNEC
	public void disconnect();
	public void connect();
	
	public void createUser(User user);
	public void createRole(Role role);
	
	
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String username, String password);
	
	public List<User> getUsers();
	public void deleteUser(int id);
	public void deleteRole(int id);
	public void updateUser(User user, int id);
	
	
	
	
	
}
