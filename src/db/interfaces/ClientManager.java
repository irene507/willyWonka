package db.interfaces;

import java.util.List;

import pojos.Client;
import pojos.users.User;

public interface ClientManager{
	public void addClient(Client client);
	public void delete(int clientId);

	public void update(Client client);
	public List<Client> searchByName(String name);
	public List<Client> searchByEmail(String email);
	List<Client> searchClient(int clientId);
	List<Client> showClients();
	Client getClient(int clientId);
	
	//JPA
	public Integer insertNewClient (User user);
	public boolean UpdateClient(Client client);
	public boolean DeleteClient(Client client);
	public Client SearchClient(User user);
	public Client SearchClientById(Integer clientId);
	boolean stablish_connection();
	boolean closeConnection();
	
	
	
	
}