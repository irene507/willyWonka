package db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


import db.interfaces.UserManager;
import pojos.Animal;
import pojos.Chocolate;
import pojos.Client;
import pojos.Milk;
import pojos.users.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class JPAUserManager implements UserManager {

	private EntityManager em;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public JPAUserManager() {
		super();
		connect();
	}
	
	
	//---------------------- CONNECT --------------------//
	@Override
	public void connect() {
		try {
		em = Persistence.createEntityManagerFactory("chocolate-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
	} catch (Exception e) {
		
		e.printStackTrace();
		
	}
		//instead of doing a void I like to do a boolean so I can see if It has been stablished
		
	}
	
	 //---------------------  DISCONNECT JPA  -------------------------//
	

	public List<User> getUsers(){
		
		Query q = em.createNativeQuery("SELECT * FROM users",User.class);
		List<User> users = (List<User>) q.getResultList();
		
		return users;
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
	     em.getTransaction().begin();
		 Query q = em.createNativeQuery("DELETE FROM users WHERE id= ?");
		 q.setParameter(1, id);
		 q.executeUpdate();
	     em.getTransaction().commit();
	     
	}
	public void deleteRole(int id) {
		em.getTransaction().begin();
		 Query q = em.createNativeQuery("DELETE FROM roles WHERE id= ?");
		 q.setParameter(1, id);
		 q.executeUpdate();
	     em.getTransaction().commit();
	}

	//Ya va 
	public void updateUser(User user, int id){
		  // Begin transaction
	      em.getTransaction().begin();
		  // Make changes
	      Query q = em.createNativeQuery("UPDATE users SET username = ?, password=? WHERE id= ?");
	      q.setParameter(1, user.getUsername());
	      q.setParameter(2, user.getPassword());
	     
	      q.setParameter(3, id);
	      q.executeUpdate();
		 // End transaction
		 em.getTransaction().commit();
	}

	
	@Override
	public void disconnect( ){
		try{
			em.close();
			
		}catch(Exception e ){
			e.printStackTrace();
			
		}
		
	}
	
	//------------------------	CREATE  ------------------------------//
	
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
	
public Integer createChocolate (Chocolate chocolate) {
		
		try{
			System.out.println("Insert new chocolate " + em.getTransaction().isActive());
			//Create the object: chocolate
			Chocolate choco= new Chocolate();
			chocolate.setName(chocolate.getName());
			chocolate.setType(chocolate.getType());
			chocolate.setCocoa(chocolate.getCocoa());
			chocolate.setFlavors(chocolate.getFlavors());
			chocolate.setUnits(chocolate.getUnits());
			chocolate.setShape(chocolate.getShape());
			
			//Begin transaction
			em.getTransaction().begin();
			//Store Object 
			em.persist(chocolate);
			//End transaction 
			em.getTransaction().commit();
			return chocolate.getId();
		} catch(EntityNotFoundException new_benefits_error) {
			new_benefits_error.printStackTrace();
			return null;
		}
	}

//------------------------	GETS  ------------------------------//

        public Chocolate getChocolate(int id){
	        Query q = em.createNativeQuery("SELECT * FROM Chocolate WHERE id = ?", Chocolate.class);
	        q.setParameter(1,id );
	        Chocolate c = (Chocolate) q.getSingleResult();
	        return c;
        }


        public Client getClient(int id){
	        Query q = em.createNativeQuery("SELECT * FROM Client WHERE id = ?", Client.class);
	        q.setParameter(1,id );
	        Client c = (Client) q.getSingleResult();
	        return c;
        }
        

        public Animal getAnimal(int id){
	        Query q = em.createNativeQuery("SELECT * FROM Animal WHERE id = ?", Animal.class);
	        q.setParameter(1,id );
	        Animal a = (Animal) q.getSingleResult();
	        return a;
        }
        

        public Milk getMilk(int id){
	        Query q = em.createNativeQuery("SELECT * FROM Milk WHERE id = ?", Milk.class);
	        q.setParameter(1,id );
	        Milk m = (Milk) q.getSingleResult();
	        return m;
        }
        
        
      //------------------------	READ  ------------------------------//
              
        
      		public void readChocolate() {
      			try {
      				System.out.print("Write the chocolate�s ID: ");
      				int choco_id = Integer.parseInt(reader.readLine());
      				Chocolate chocolate = getChocolate(choco_id);
      				System.out.println(chocolate.toString());				
      			}catch(IOException e) {
      				e.printStackTrace();}
      		}
      		public void readClient() {
      			try {
      				System.out.print("Write the client�s ID: ");
      				int client_id = Integer.parseInt(reader.readLine());
      				Client client = getClient(client_id);
      				System.out.println(client.toString());				
      			}catch(IOException e) {
      				e.printStackTrace();}
      		}
      		public void readAnimal() {
      			try {
      				System.out.print("Write the animal�s ID: ");
      				int animal_id = Integer.parseInt(reader.readLine());
      				Animal animal  = getAnimal(animal_id);
      				System.out.println(animal.toString());				
      			}catch(IOException e) {
      				e.printStackTrace();}
      		}
      		public void readMilk() {
      			try {
      				System.out.print("Write the milk�s ID: ");
      				int milk_id = Integer.parseInt(reader.readLine());
      				Milk milk = getMilk(milk_id);
      				System.out.println(milk.toString());				
      			}catch(IOException e) {
      				e.printStackTrace();}
      		}
      		
      		
      	//------------------------	LIST  ------------------------------//
    		public void listChocolates() {
    			
    			Query q1 = em.createNativeQuery("SELECT * FROM Chocolates", Chocolate.class);
    			List<Chocolate> chocolates = (List<Chocolate>) q1.getResultList();
    			for (Chocolate chocolate : chocolates) {
    				System.out.println(chocolates );
    			}
    		}
    		
    		
    		
          	//------------------------  DELETE  ------------------------------//
    //AQUI ALGO MAL		
    		public void deleteChocolate(){		
    			System.out.println("Chocolates list:");
    			listChocolates();
    			try {
    				System.out.print("Choose a chocolate to delete. Type its ID:");
    				String id=reader.readLine();
    				while(!onlyContainsNumbers(id)) {
    					System.out.println("    Select a valid chocolate ID: ");
    					id = reader.readLine();
    				}
    				int choco_id = Integer.parseInt(id);	
    				if(!chocolatesIds().contains(choco_id)) {
    					System.out.println("This chocolate does not exist");
    				}
    				else {		
    				Chocolate chocoDelete = getChocolate(choco_id);
    				em.getTransaction().begin();
    				//chocoDelete.getName().
    				//chocoDelete.getChocolate().setChocolate(null);
    				em.remove(chocoDelete);
    				em.getTransaction().commit();}
    			}catch(IOException e) {
    				e.printStackTrace();}	
    		}
    		
    		
    		public List<Integer> chocolatesIds (){
    			List<Integer> listId= new ArrayList<Integer>();
    			try {	
    			List<Chocolate> chocolateList= new ArrayList<Chocolate>();
    			Query q =em.createNativeQuery("SELECT * FROM Chocolate ", Chocolate.class);
    			chocolateList= (List<Chocolate>) q.getResultList();
    			for (Chocolate c: chocolateList) {
    				listId.add(c.getId());
    			}
    			}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    			 	return listId;
    		}
    		
    		
    		private boolean onlyContainsNumbers(String id) {
				// TODO Auto-generated method stub
				return false;
			}


			//------------------------	UPDATE  ------------------------------//
    		
    		public boolean updateChocolate(Chocolate chocolate) throws Exception{
    			try {
    				
    				System.out.println("Choose a chocolate to modify. Type its ID:");
    				String id=reader.readLine();
    				
    				int choco_id=Integer.parseInt(id);
    				while(!onlyContainsNumbers(id)|| !chocolatesIds().contains(choco_id)) {
    					System.out.println(" Select a valid chocolate id: ");
    					id=reader.readLine();
    					choco_id = Integer.parseInt(id);
    					}
    				
    				Query q = em.createNativeQuery("SELECT * FROM chocolate WHERE chocolate_id = ?", Chocolate.class);
    				q.setParameter(1, chocolate.getId());
    				Chocolate choco = (Chocolate) q.getSingleResult();
    				em.getTransaction().begin();
    				choco.setName(chocolate.getName());
    				choco.setType(chocolate.getType());
    				choco.setCocoa(chocolate.getCocoa());
    				choco.setFlavors(chocolate.getFlavors());
    				choco.setUnits(chocolate.getUnits());
    				choco.setShape(chocolate.getShape());
    				
    				em.getTransaction().commit();
    				return true;
    			} catch (EntityNotFoundException update_client_error) {
    				update_client_error.printStackTrace();
    				return false;
    			}
    		}
    		
    		
        
        







	

	

}
