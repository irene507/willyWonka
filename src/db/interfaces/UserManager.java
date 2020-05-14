package db.interfaces;

import java.util.List;

import pojos.Client;
import pojos.users.*;

public interface UserManager {
       
	
	public boolean disconnect(); 
	public void createUser(User user);
	public void createRole(Role role);
	public Role getRole(int id);
	public List<Role> getRoles();
	public User checkPassword(String username, String password);
	boolean stablish_connection();
	public Integer insertNewClient (User user);
	public boolean UpdateClient(Client client);
	public boolean DeleteClient(Client client);
	public Client SearchClient(User user);
	public Client SearchClientById(Integer clientId);
	
	
	
	
	
}
