package db.sqlite;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.AnimalManager;
import pojos.Animal;



public class SQLiteAnimalManager implements AnimalManager {

	private Connection c;

	public SQLiteAnimalManager(Connection c) {
		this.c = c;
	}

	@Override
	public void add(Animal animal) {
		// TODO Auto-generated method stub

		// Insert the provider animal
		try {
			String sql = "INSERT INTO animal (name, country, colour , specie, dob) " + "VALUES (?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, animal.getName());
			prep.setString(2, animal.getCountry());
			prep.setString(3, animal.getColour());
			prep.setString(3, animal.getSpecie());
			prep.setDate(4, animal.getDob());
			prep.executeUpdate();
			prep.close();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	
	@Override
	public List<Animal> searchAnimal(int AnimalID) {
		List<Animal> animal = new ArrayList<Animal>(); 
		Animal newAnimal;
		
		try{
		String sql = "SELECT * FROM animal WHERE id= ? ";
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, AnimalID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String animalName = rs.getString("name");
			String country = rs.getString("country");
			String colour = rs.getString("colour");
			String specie = rs.getString("specie");
			Date dob= rs.getDate("dob");
			
			newAnimal = new Animal(id, animalName, country, colour, specie, dob);
			animal.add(newAnimal);
			rs.close();
			stmt.close();
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return animal;
		
	}//functionSELECT
	
	
	public void delete (int AnimalID) {
		try {
			String sq1 = "DELETE * FROM animal WHERE ID= ? ";
			PreparedStatement p = c.prepareStatement(sq1);
			p.setInt(1, AnimalID); 
			p.executeQuery();
			p.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public List<Animal> searchByNameAnimal(String name_a) {
		// TODO Auto-generated method stub

		// Create an empty list of animals
		List<Animal> AnimalList = new ArrayList<Animal>();

		// Search for all animals that fit the name
		try {
			String sql = "SELECT * FROM Animal WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name_a + "%");
			ResultSet rs = prep.executeQuery();

			// For each result
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				String colour = rs.getString("colour");
				String specie = rs.getString("specie");
				Date dob = rs.getDate("dob");
				// I create a new animal and..
				Animal newAnimal = new Animal(id, name, country, colour, specie, dob);
				// Add it to the list
				AnimalList.add(newAnimal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception

		}

		// Return the list
		return AnimalList;

	}
	
	

	@Override
	public List<Animal> searchBySpecieAnimal(String specie_a) {
		// TODO Auto-generated method stub
		// Create an empty list of animals
		List<Animal> AnimalList = new ArrayList<Animal>();

		// Search for all animals that fit the name
		try {
			String sql = "SELECT * FROM animal WHERE specie LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + specie_a + "%");
			ResultSet rs = prep.executeQuery();

			// For each result
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String country = rs.getString("country");
				String colour = rs.getString("colour");
				String specie = rs.getString("specie");
				Date dob = rs.getDate("dob");
				// I create a new animal and..
				Animal newAnimal = new Animal(id, name, country, colour, specie, dob);
				// Add it to the list
				AnimalList.add(newAnimal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception

		}

		return AnimalList;
	}

	
	
	@Override
	public void update(Animal animal) {
		try {
			// TODO Auto-generated method stub
			// Update every aspect of a particular animal
			String sql = "UPDATE animal SET name=?, country=?, colour=?, specie=?, dob=?, WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, animal.getName());
			s.setString(2, animal.getCountry());
			s.setString(3, animal.getColour());
			s.setString(4, animal.getSpecie());
			s.setDate(5, animal.getDob());
			s.setInt(6, animal.getId());
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Animal getAnimal(int AnimalId) {
		Animal newAnimal = null; 		
		try{
			String sq1 = "SELECT * FROM animal WHERE ID= ? "; 
			PreparedStatement p = c.prepareStatement(sq1);
			p.setInt(1,  AnimalId);
			
			//Because we are going to do it just once
			ResultSet rs = p.executeQuery();
			
		    rs.next();
		    int id = rs.getInt("id");
			String AnimalName = rs.getString("name");
			String AnimalCountry= rs.getString("country");
			String AnimalColour = rs.getString("colour");
			String AnimalSpecie = rs.getString("specie");
			Date dob = rs.getDate("dob");
				
			//Create a new animal 
			 newAnimal = new Animal(id, AnimalName, AnimalCountry, AnimalColour, AnimalSpecie, dob);
			 
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		return newAnimal; 
	}
	
	
	public List<Animal> showAnimals(){
		//Create an empty list of animals 
		List<Animal> animalList = new ArrayList<Animal>();
		//Get all the animals
		try{
			String sq1 = "SELECT * FROM animal";
			PreparedStatement prep= c.prepareStatement(sq1);
			ResultSet rs = prep.executeQuery();
			//For each result 
			while(rs.next()){
				int id = rs.getInt("id");
				String AnimalName = rs.getString("name");
				String AnimalCountry= rs.getString("country");
				String AnimalColour = rs.getString("colour");
				String AnimalSpecie = rs.getString("specie");
				Date dob = rs.getDate("dob");
				
			//Create a new chocolate 
				Animal newAnimal = new Animal(id, AnimalName, AnimalCountry, AnimalColour, AnimalSpecie, dob);
			//Add it to the list
				animalList.add(newAnimal);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return animalList;

		
}

}
