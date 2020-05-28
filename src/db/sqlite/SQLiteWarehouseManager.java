package db.sqlite;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.WarehouseManager;

import pojos.Warehouse;



public class SQLiteWarehouseManager implements WarehouseManager {
	private Connection c;
	public Warehouse warehouse = null;
	
	public SQLiteWarehouseManager (Connection c) {
		this.c=c;
	}
	
	
	public void add(Warehouse warehouse){
		try {
		String sql = "INSERT INTO warehouse (name, corridor, shelve)"
				+"VALUES(?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, warehouse.getName());
		prep.setInt(2, warehouse.getCorridor());
		prep.setInt(3, warehouse.getShelve());
		
		
		prep.executeUpdate();
		prep.close();
	}catch (Exception ex) {
		ex.printStackTrace();
	}
	
}
	public Warehouse select(String Wname) {
		try {
			String sql = "SELECT * FROM warehouse WHERE name = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, Wname);
			ResultSet rs = prep.executeQuery();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int corridor = rs.getInt("corridor");
			int shelve = rs.getInt("shelve");
			
		
			warehouse = new Warehouse (id,name,corridor,shelve);
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return warehouse;
		
	}

	
	public void delete(int Wid) {
		try {
			String sql = "DELETE FROM warehouse WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, Wid);
			prep.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	public void update(Warehouse warehouse) {
		try{
			String sql = "UPDATE warehouse SET name = ?, corridor = ?, shelve = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,warehouse.getName());
			prep.setInt(2, warehouse.getCorridor());
			prep.setInt(3, warehouse.getShelve());
			prep.setInt(4, warehouse.getId());
			prep.executeUpdate();
			prep.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	};
	public List<Warehouse> searchByName(String name){
		List<Warehouse> WHList = new ArrayList<Warehouse>();
		try{
			String sql = "SELECT * FROM warehouse WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,"%"+name+"%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String WHname = rs.getString("name");
				int corridor = rs.getInt("corridor");
				int shelve = rs.getInt("shelve");
				
				
				warehouse = new Warehouse(id,WHname,corridor,shelve);
				WHList.add(warehouse);
				
			}
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return WHList;
	};
	public List<Warehouse> searchByCorridor(Integer corridor){
		List<Warehouse> WHList = new ArrayList<Warehouse>();
		try{
			String sql = "SELECT * FROM warehouse WHERE corridor = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, corridor);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int WHcorridor = rs.getInt("corridor");
				int shelve = rs.getInt("shelve");
				
				
				warehouse = new Warehouse(id,name,WHcorridor,shelve);
				WHList.add(warehouse);
				
				
			}
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return WHList;
	}
	

}
