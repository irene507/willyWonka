/*package db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces.ClientManager;
import db.interfaces.UserManager;
import pojos.Client;
import pojos.users.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class JPAClient implements ClientManager {
	
private EntityManager em;
	
	@Override
	public boolean stablish_connection() {
		try {
		em = Persistence.createEntityManagerFactory("chocolate-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
		//instead of doing a void I like to do a boolean so I can see if It has been stablished
		
	}
	
	
	@Override
	public Integer insertNewClient (User user) {
	
	try{
		System.out.println("New client: " + em.getTransaction().isActive());
		Client client = new Client();
		client.setName(client.getName());
		client.setCellphone(client.getCellphone());
		client.setEmail(client.getEmail());
		client.setAdress(client.getAdress());
		client.setDob(client.getDob());
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		return client.getId();
	} catch(EntityNotFoundException new_benefits_error) {
		new_benefits_error.printStackTrace();
		return null;
	}
}

@Override
public boolean UpdateClient(Client client) {
	try {
		Query q = em.createNativeQuery("SELECT * FROM client WHERE client_id = ?", Client.class);
		q.setParameter(1, client.getId());
		Client c = (Client) q.getSingleResult();
		em.getTransaction().begin();
		c.setName(client.getName());
		c.setCellphone(client.getCellphone());
		c.setEmail(client.getEmail());
		c.setAdress(client.getAdress());
		c.setDob(client.getDob());
		em.getTransaction().commit();
		return true;
	} catch (EntityNotFoundException update_client_error) {
		update_client_error.printStackTrace();
		return false;
	}
}

@Override
public boolean DeleteClient(Client client) {
	try {
		em.getTransaction().begin();
		em.remove(client);
		em.getTransaction().commit();
		return true;
	} catch (EntityNotFoundException delete_client_error) {
		delete_client_error.printStackTrace();
		return false;
	}
	
	
}


@Override
public Client SearchClient(User user) {
	try {
		Query query_client = em.createNativeQuery("SELECT * FROM client WHERE user_id LIKE ?", Client.class);
		query_client.setParameter(1, user.getId());
		Client client = (Client) query_client.getSingleResult();
		return client;
	} catch (EntityNotFoundException search_client_error) {
		search_client_error.printStackTrace();
		return null;
	}
}



@Override
public Client SearchClientById(Integer clientId) {
	try {
		Query query_client = em.createNativeQuery("SELECT * FROM client WHERE client_id LIKE ?", Client.class);
		query_client.setParameter(1, clientId);
		Client client = (Client) query_client.getSingleResult();
		return client;
	} catch (EntityNotFoundException search_client_error) {
		search_client_error.printStackTrace();
		return null;
	}
}

@Override
public boolean closeConnection( ){
	try{
		em.close();
		return true;
	}catch(Exception e ){
		e.printStackTrace();
		return false;
	}
	
}


	
}*/
