package db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces.UserManager;
import pojos.Client;
import pojos.users.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class JPAUserManager implements UserManager {

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
	
	//------------------------------------------------------------------
	//                         DISCONNECT JPA
	//------------------------------------------------------------------


	@Override
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

	}

	@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();

	}

	@Override
	public Role getRole(int id) {
	    Query q = em.createNativeQuery("SELECT * FROM roles WHERE id=? ", Role.class);
		q.setParameter(1, id);
		Role role = (Role) q.getSingleResult(); //trnafosmar un objeto en un role
		return role;
	}
	
	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List <Role> roles =(List <Role>) q.getResultList();
		return roles;
	}

	

	@Override
	public User checkPassword(String username, String password) {
		User user= null;
		
		try {
			//Create a MessageDirect 
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			//Create the query 
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username= ? AND password= ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, hash);
			user =(User) q.getSingleResult();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(NoResultException nre){
			//when no result is retrieved... 
			return null;
		}
	    
		return user;
		
		
	}

	
	public void deleteUser(int id) {
	     // Begin transaction
	     em.getTransaction().begin();
	     // Store the object
	     em.remove(id);
	     // End transaction
	     em.getTransaction().commit();

	}
	
	public void updateUser(){
		  // Begin transaction
	      em.getTransaction().begin();
		  // Make changes
       	  dep.setAddress(newLocation);
		 // End transaction
		 em.getTransaction().commit();
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
public boolean disconnect() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void connect() {
	// TODO Auto-generated method stub
	
}


	

}
