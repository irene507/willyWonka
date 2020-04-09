package db.sqlite;

import java.sql.Connection;

import db.interfaces.ChocolateManager;
import pojos.Chocolate;

public class SQLiteWillyWonka implements ChocolateManager {
    private Connection c;
    //en todas las clases que usen a connection
	public SQLiteChocolateManager(Connection c){
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
		    prep.setString(2,chocolate.getCocoa());
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

}
