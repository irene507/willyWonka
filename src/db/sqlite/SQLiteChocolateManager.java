package db.sqlite;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.Chocolate;


import db.interfaces.ChocolateManager;
import pojos.Chocolate;

public class SQLiteChocolateManager implements ChocolateManager {
    private Connection c;
    private Chocolate chocolate;
    
    //en todas las clases que usen a connection
    
	public SQLiteChocolateManager(Connection c) {
		// TODO Auto-generated constructor stub
		this.c=c;
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
		try{
		//Update every aspect of a particular chocolate
		String sq1 = "UPDATE dogs SET name=?, type=?, cocoa=?, flavors=?, units=?, shape=? WHERE id=? ";
		PreparedStatement s = c.prepareStatement(sq1);
		s.setString(1, chocolate.getName());
		s.setString(2, chocolate.getType());
		s.setFloat(3, chocolate.getCocoa());
		s.setString(4, chocolate.getFlavors());
		s.setFloat(5, chocolate.getUnits());
		s.setString(6, chocolate.getShape());
		s.setInt(7, chocolate.getId());
		s.execute();
		s.close();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Chocolate> searchByName(String name) {
		//Create an empty list of chocolates 
		List<Chocolate> chocolatesList = new ArrayList<Chocolate>();
		//Search for all chocolates that "fit" the name
		try{
			String sq1 = "SELECT * FROM chocolates WHERE name LIKE ? ";
			PreparedStatement prep= c.prepareStatement(sq1);
			//ANY CHARACTERS +name + ANY CHARACTERS
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			//For each result 
			while(rs.next()){
				int id= rs.getInt("id");
				String chocoName = rs.getString("name");
				String type = rs.getString("type");
				float cocoa = rs.getFloat("cocoa");
				String flavors = rs.getString("flavors");
				float units = rs.getFloat("units");
				String shape = rs.getString("shape");
				
			//Create a new chocolate 
				Chocolate newChocolate = new Chocolate(id, chocoName, type, cocoa, flavors, units, shape);
			//Add it to the list
				chocolatesList.add(newChocolate);
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return chocolatesList;
	}

	@Override
	public List<Chocolate> searchByType(String type) {
		//Create an empty list of chocolates 
				List<Chocolate> chocolatesList = new ArrayList<Chocolate>();
		//Search for all chocolates that "fit" the name
		 try{
			String sq1 = "SELECT * FROM chocolates WHERE type LIKE ? ";
			PreparedStatement prep= c.prepareStatement(sq1);
			//ANY CHARACTERS +name + ANY CHARACTERS
			prep.setString(1, "%" + type + "%");
			ResultSet rs = prep.executeQuery();
			//For each result 
    		while(rs.next()){
	     		int id= rs.getInt("id");
			    String chocoName = rs.getString("name");
				String chocoType = rs.getString("type");
				float cocoa = rs.getFloat("cocoa");
				String flavors = rs.getString("flavors");
				float units = rs.getFloat("units");
				String shape = rs.getString("shape");
						
			//Create a new chocolate 
			Chocolate newChocolate = new Chocolate(id, chocoName, chocoType, cocoa, flavors, units, shape);
			//Add it to the list
			chocolatesList.add(newChocolate);
						
		}
				
		}catch(Exception e){
			e.printStackTrace();
		}
				
				
			return chocolatesList;
		
	}

}

