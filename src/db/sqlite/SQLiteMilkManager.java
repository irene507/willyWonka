
package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Animal;
import pojos.Client;
import pojos.Milk;

public class SQLiteMilkManager {
      
	 private Connection c;
	 private Milk milk;
	 
	  //in all classes that uses a connection
	 public SQLiteMilkManager(Connection c) {
			
		 // TODO Auto-generated constructor stub
			this.c=c;
   	 }
	 
		public void give(int chocoId, int milkId) {
			// Link a chocolate with a milk
			try {
				String sql = "INSERT INTO chocolateMilks (chocoId, milkId) "
						+ "VALUES (?,?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, chocoId);
				prep.setInt(2, milkId);
				prep.executeUpdate();
				prep.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	 

     public void addMilk(){
	    try {
			String sql = "INSERT INTO milk (id_m, name_m , type_m) "
					+ "VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, milk.getId());
			prep.setString(2, milk.getName());
			prep.setString(3, milk.getType());
			
			prep.executeUpdate();
			prep.close();
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	    
	
    }//function addMilk
     
     public List<Milk> searchMilk(int milkId) {
    	List<Milk> milk = new ArrayList<Milk>();
    	Milk newMilk;
    	 
 		try{
 		String sq1 = "SELECT * FROM milk WHERE id= ? ";
 		PreparedStatement stmt = c.prepareStatement(sq1);
 		stmt.setInt(1, milkId);
 		ResultSet rs = stmt.executeQuery();
 		while(rs.next()){
 			int id = rs.getInt("id");
 			String milkName = rs.getString("name");
 			String milkType = rs.getString("type");
 			
 			
 			newMilk = new Milk(id, milkName, milkType);
 			milk.add(newMilk);
 			
 			rs.close();
 			stmt.close();
 						
 			
 		}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		 return milk;
 		
 	}//functionSELECT
     
     
 	public List<Milk> searchByName(String name) {
		// TODO Auto-generated method stub

		// Create an empty list of animals
		List<Milk> MilkList = new ArrayList<Milk>();

		// Search for all animals that fit the name
		try {
			String sql = "SELECT * FROM milk WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();

			// For each result
			while (rs.next()) {
				int id = rs.getInt("id");
				String name_m = rs.getString("name");
				String type = rs.getString("type");
				// I create a new milk and..
				Milk newMilk = new Milk(id, name_m, type);
				// Add it to the list
		        MilkList.add(newMilk);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception

		}
		return MilkList;
 	}
     
 	
 	public List<Milk> searchByType(String type) {
 			// TODO Auto-generated method stub
 			// Create an empty list of animals
 			List<Milk> MilkList = new ArrayList<Milk>();

 			// Search for all animals that fit the name
 			try {
 				String sql = "SELECT * FROM milk WHERE type LIKE ?";
 				PreparedStatement prep = c.prepareStatement(sql);
 				prep.setString(1, "%" + type + "%");
 				ResultSet rs = prep.executeQuery();

 				// For each result
 				while (rs.next()) {
 					int id = rs.getInt("id");
 					String name = rs.getString("name");
 					String type_m=  rs.getString("type");
 					// I create a new animal and..
 					Milk newMilk = new Milk(id, name, type_m);
 					// Add it to the list
 					MilkList.add(newMilk);
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 				// TODO: handle exception

 			}

 			return MilkList;
 		
 	}//function searchByType
 	
 	
 	public void update(Milk milk) {
		try {
			// TODO Auto-generated method stub
			// Update every aspect of a particular animal
			String sql = "UPDATE milk SET name=?, country=?, colour=?, specie=?, dob=?, WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, milk.getName());
			s.setString(2, milk.getType());
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}//update
 	
	public void delete(int milkId) {
		try{
		String sq1 = "DELETE * FROM milk WHERE ID= ? ";
		PreparedStatement p = c.prepareStatement(sq1);
		p.setInt(1, milkId); 

		//Statement s = c.prepareStatement("DELETE * FROM milk WHERE ID= ?");
		//s.execute(sq1);
		
		p.executeQuery();
		p.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}//function 
	
	public Milk getMilk(int MilkId) {
		Milk newMilk = null; 		
		try{
			String sq1 = "SELECT * FROM milk WHERE ID= ? "; 
			PreparedStatement p = c.prepareStatement(sq1);
			p.setInt(1,  MilkId);
			//Because we are going to do it just once
			ResultSet rs = p.executeQuery();
			
		    rs.next();
		    int id = rs.getInt("id");
			String MilkName = rs.getString("name");
			String MilkType= rs.getString("type");
				
			//Create a new chocolate 
			 newMilk = new Milk(id, MilkName, MilkType);
			 
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		return newMilk; 
	}

	
	
	public List<Milk> showMilk(){
		//Create an empty list of chocolates 
		List<Milk> milkList = new ArrayList<Milk>();
		//Get all the chocolates 
		try{
			String sq1 = "SELECT * FROM milk";
			PreparedStatement prep= c.prepareStatement(sq1);
			ResultSet rs = prep.executeQuery();
			//For each result 
			while(rs.next()){
				int id = rs.getInt("id");
				String MilkName = rs.getString("name");
				String MilkType= rs.getString("type");
				
			//Create a new chocolate 
				Milk newMilk = new Milk(id, MilkName, MilkType );
			//Add it to the list
				milkList.add(newMilk);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return milkList;

		
}
	
	
}
 	

   
