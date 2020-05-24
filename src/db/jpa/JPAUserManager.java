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
		// en lugar de hacer un void hago un boolean para comprobar que se ha establecido la conexion

	}
	
	//------------------------------------------------------------------
	//                         DISCONNECT JPA
	//------------------------------------------------------------------


	@Override
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		//YA ESTA

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
	
	
	

	

}
