/*package db.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import db.interfaces.OompaLoompaManager;
import pojos.OompaLoompa;

public class JPAOompaLoompa implements OompaLoompaManager  {

	private EntityManager em;
	@Override
	
	public void add(OompaLoompa oompaloompa) {
		em.getTransaction().begin();
		em.persist(oompaloompa);
		em.getTransaction().commit();
		
	}

	@Override
	public OompaLoompa select(int OLid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OompaLoompa oompaloompa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String OLname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OompaLoompa> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OompaLoompa> searchBydob(Date dob) {
		// TODO Auto-generated method stub
		return null;
	}

}*/
