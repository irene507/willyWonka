package db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Chocolate;
import pojos.Client;
import pojos.users.User;

public class JPAChocolate {
	EntityManager em = Persistence.createEntityManagerFactory("chocolate-provider").createEntityManager();
	
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
	
	public boolean updateChocolate(Chocolate chocolate) {
		try {
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
