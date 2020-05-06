package db.sqlite;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojos.Chocolate;


import db.interfaces.ChocolateManager;

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
			String sql = "INSERT INTO chocolate(name, cocoa, type, flavors, units, shape, hash)"
				    + "VALUES (?,?,?,?,?,?,?);";
			
			PreparedStatement prep = c.prepareStatement(sql);
		    prep.setString(1,chocolate.getName());
		    prep.setFloat(2,chocolate.getCocoa());
		    prep.setString(3,chocolate.getType());
			prep.setString(4,chocolate.getFlavors());
			prep.setFloat(5,chocolate.getUnits());
			prep.setString(6,chocolate.getShape());
			prep.setInt(7, chocolate.hashCode());
		    
		    prep.executeUpdate();
			prep.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void create(Chocolate chocolate) {
		//en menu  no se si neecesito preguntarle tambien el id?
		
		try{
			String sq1 = "INSERT INTO chocolates(name, type, cocoa, flavors, units, shape)"
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement prep = c.prepareStatement(sq1);
			
			prep.setString(1, chocolate.getName());
			prep.setString(2, chocolate.getType());
			prep.setFloat(3, chocolate.getCocoa());
			prep.setString(4, chocolate.getFlavors());
			prep.setFloat(5, chocolate.getUnits());
			prep.setString(6, chocolate.getShape());
    //prep.setInt(7, chocolate.getId());
	//por qué executeUpdate y no solo execute?		
			prep.executeUpdate();
			prep.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void changeCharacteristics(Chocolate chocolate) {
		// TODO Auto-generated method stub
//creo que seria igual pero modificando todas las variables 
//pero no sé como tendría que pasarle los datos, porque le pasamos un chocolate 

	}

	@Override
	public void select(Chocolate chocolate) {
		
		
		try{
		Statement stmt = c.createStatement();
		String sq1 = "SELECT * FROM chocolates";
		ResultSet rs = stmt.executeQuery(sq1);
		while(rs.next()){
			int id = rs.getInt("id");
			String chocoName = rs.getString("name");
			String type = rs.getString("type");
			float cocoa = rs.getFloat("cocoa");
			String flavors = rs.getString("flavors");
			float units = rs.getFloat("units");
			String shape = rs.getString("shape");
 //todo esto dentro del while? para qué sirve? 
			
			Chocolate newChocolate = new Chocolate(id, chocoName, type, cocoa, flavors, units, shape);
			
			rs.close();
			stmt.close();
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//functionSELECT

	@Override
	public void delete(Chocolate chocolate) {
		
		try{
		String sq1 = "DELETE * FROM chocolates WHERE ID= ? ";
		PreparedStatement p = c.prepareStatement(sq1);
		p.setInt(1,  chocolate.getId()); 
//necesito un statement ???
		ResultSet rs = p.executeQuery();
		while(rs.next()){
			// creo que no haria falta cambiar nada dentro de la tabla 
			//o si que cambian porque la hacen ser nulos ? 
		}
		
	    rs.close();
//si necesiatra el statement cierro 
		p.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//function 

	@Override
	public boolean update(Chocolate chocolate) {
		boolean error =false;
		try{
		//Update every aspect of a particular chocolate
		String sq1 = "UPDATE chocolate SET name=?, type=?, cocoa=?, flavors=?, units=?, shape=? WHERE id=? ";
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
			error = true;
		}
		
		return error;
	}

	@Override
	public Chocolate getChocolate(int chocoId){
		
//no se si necesito unirlo ocn el resto de tablas?? 		
		Chocolate newChoco = null;
		try{
			String sq1 = "SELECT * FROM chocolates WHERE ID= ? "; 
			PreparedStatement p = c.prepareStatement(sq1);
			p.setInt(1,  chocoId);
			//Because we are going to do it just once
			ResultSet rs = p.executeQuery();
			
		    rs.next();
				int id= rs.getInt("id");
				String chocoName = rs.getString("name");
				String type = rs.getString("type");
				float cocoa = rs.getFloat("cocoa");
				String flavors = rs.getString("flavors");
				float units = rs.getFloat("units");
				String shape = rs.getString("shape");
				
			//Create a new chocolate 
				Chocolate newChocolate = new Chocolate(id, chocoName, type, cocoa, flavors, units, shape);
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		return chocolate; 
		}//function 
	
	
	@Override
	public List<Chocolate> showChocolates(){
		//Create an empty list of chocolates 
		List<Chocolate> chocolatesList = new ArrayList<Chocolate>();
		//Get all the chocolates 
		try{
			String sq1 = "SELECT * FROM chocolates ";
			PreparedStatement prep= c.prepareStatement(sq1);
			ResultSet rs = prep.executeQuery();
			//For each result 
			while(rs.next()){
				int id= rs.getInt("id");
				String chocoName = rs.getString("name");
				
			//Create a new chocolate 
				Chocolate newChocolate = new Chocolate(id, chocoName);
			//Add it to the list
				chocolatesList.add(newChocolate);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return chocolatesList;

		
	}//showChocolates
	
	
	
	
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

