package db.interfaces;
import java.sql.Date;
import java.util.List;

import pojos.Client;

public interface ClientManager{
	public void addClient(Client client);
	public void delete(Client client);
	public void select(Client client);
	public void update(Client client);
	public List<Client> searchByName(String name);
	public List<Client> searchByEmail(String email);
	
	
	
}