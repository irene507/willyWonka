package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import db.interfaces.AnimalManager;
import pojos.Animal;

public class SQLiteAnimalManager implements AnimalManager {

	private Connection c;
	
	public SQLiteAnimalManager (Connection c) {
		this.c=c;
	}
	
	@Override
	public void add(Animal animal) {
		// TODO Auto-generated method stub
		//Añadir un animal
		try {
			String sql = "INSERT INTO Animal (name, country, colour , specie, dob) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, animal.getName());
			prep.setString(2, animal.getCountry());
			prep.setString(3,  animal.getColour());
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
	public void delete(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Animal> searchByNameAnimal(String name_a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Animal> serachBySpecieAnimal(String specie_a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Animal animal) {
		// TODO Auto-generated method stub

	}

	@Override
	public Animal getAnimal() {
		// TODO Auto-generated method stub
		return null;
	}

}
