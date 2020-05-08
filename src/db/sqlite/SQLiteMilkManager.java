
package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.Animal;
import pojos.Chocolate;
import pojos.Milk;

public class SQLiteMilkManager {
      
	 private Connection c;
	 private Milk milk;
	 
	  //in all classes that uses a connection
	 public SQLiteMilkManager(Connection c) {
			
		 // TODO Auto-generated constructor stub
			this.c=c;
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
     
     
 	public List<Milk> searchByType(String type) {
 		//Create an empty list of milk
 	    List<Milk> milkList = new ArrayList<Milk>();
 		//Search for all milk that "fit" the name
 		 try{
 			String sq1 = "SELECT * FROM milk WHERE type LIKE ? ";
 			PreparedStatement prep= c.prepareStatement(sq1);
 			//ANY CHARACTERS +name + ANY CHARACTERS
 			prep.setString(1, "%" + type + "%");
 			ResultSet rs = prep.executeQuery();
 			//For each result 
     		while(rs.next()){
 	     		int id= rs.getInt("id");
 			    String milkName = rs.getString("name");
 				String milkType = rs.getString("type");
 				
 			//Create a new milk
 		    Milk newMilk = new Milk(id, milkName, milkType);
 			//Add it to the list
 		    milkList.add(newMilk);
 						
 		}
 				
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 			return milkList;
 		
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
 	
	public void delete(Milk milk) {
		String sq1 = "DELETE * FROM chocolates WHERE ID= ? ";
		
		try{
		PreparedStatement p = c.prepareStatement(sq1);
		p.setInt(1,  milk.getId());
		
		p.executeQuery();
        p.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//function 
	
	

}
 	

   
