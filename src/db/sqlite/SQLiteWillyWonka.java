package db.sqlite;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import db.interfaces.ChocolateManager;
import pojos.Chocolate;

public class SQLiteWillyWonka implements ChocolateManager {
    private Connection c;
    private Chocolate chocolate;
    
    //en todas las clases que usen a connection
	public void SQLiteChocolateManager(Connection c){
	    this.c = c; 	
	}
    
	@Override
	public void admit(Chocolate chocolate){
		
		//por qué no ID?
		
		try {
			String sql = "INSERT INTO chocolate(name, cocoa, type, flavors, units, shape)"
				    + "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
		    prep.setString(1,chocolate.getName());
		    prep.setFloat(2,chocolate.getCocoa());
		    prep.setString(3,chocolate.getType());
			prep.setString(4,chocolate.getFlavors());
			prep.setFloat(5,chocolate.getUnits());
			prep.setString(6,chocolate.getShape());
		    
		    prep.executeUpdate();
			prep.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void create(Chocolate chocolate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeCharacteristics(Chocolate chocolate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void select(Chocolate chocolate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Chocolate chocolate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Chocolate chocolate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Chocolate> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chocolate> searchByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
