package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojos.Chocolate;
import pojos.Client;
import pojos.Warehouse;


import db.interfaces.WarehouseManager;

public class SQLiteWarehouseManager implements WarehouseManager {
    private Connection c;
    private Warehouse warehouse;
    
    //en todas las clases que usen a connection
    
	public SQLiteWarehouseManager(Connection c) {
		// TODO Auto-generated constructor stub
		this.c=c;
	}

    


	@Override
	public void add(Warehouse warehouse) {
		try {
			String sql = "INSERT INTO chocolate (id, name , corridor, shelve) "
					+ "VALUES (?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, warehouse.getId());
			prep.setString(2, warehouse.getName());
			prep.setInt(3, warehouse.getCorridor());
			prep.setInt(4, warehouse.getShelve());
			
			
			prep.executeUpdate();
			prep.close();
			
			
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}


	@Override
	public void select(Warehouse warehouse) {
		try{
			Statement stmt = c.createStatement();
			String sq1 = "SELECT * FROM warehouse";
			ResultSet rs = stmt.executeQuery(sq1);
			while(rs.next()){
				int id = rs.getInt("id");
				String warehouseName = rs.getString("name");
				int corridor = rs.getInt("corridor");
				int shelve = rs.getInt("shelve");
				
	 
				
				Warehouse newWarehouse = new Warehouse(id, warehouseName, corridor, shelve);
				
				rs.close();
				stmt.close();
				int s;
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}


	@Override
	public void delete(String warehouseName) {
		String sq1 = "DELETE * FROM chocolates WHERE name= ? ";
		PreparedStatement p = c.prepareStatement(sq1);
		p.setString(1,  warehouseName);

		

		p.close();
		
	}


	@Override
	public void update(Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Warehouse> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Warehouse> searchByCorridor(Integer corridor) {
		//create an empty list of warehouse
		List<Warehouse> warehousesList =new ArrayList<Warehouse>();
		
		
	try {
	
		String sql = "SELECT * FROM warehouses WHERE name LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, "%"+corridor+ "%");
		ResultSet rs = prep.executeQuery();//PORQUE SELECT ES UNA QUERY
		//for each result...
		while (rs.next()) {//VAMOS AVANZANDO REGISTRO A REGISTRO
			int id = rs.getInt("id");
			String warehouseName = rs.getString("name");
			int corridor = rs.getInt("corridor");
			int shelve = rs.getInt("shelve");
			//create a new warehouse
			
			Warehouse newWarehouse = new wareHouse(id, warehouseName,corridor, shelve);
			//crrate a new warehouse
			//add it to the list
			warehousesList.add(newWarehouse);
			
		}
	}catch( Exception e) {
		e.printStackTrace();
		
	}
	return warehousesList;
			
	}
}
