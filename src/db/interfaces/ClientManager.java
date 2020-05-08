package db.interfaces;

import java.util.List;

import pojos.Client;

public interface ClientManager{
	public void addClient(Client client);
	public void delete(int clientId);

	public void update(Client client);
	public List<Client> searchByName(String name);
	public List<Client> searchByEmail(String email);
	List<Client> searchClient(int clientId);
	List<Client> showClients();
	Client getClient(int clientId);
	
	
	
}