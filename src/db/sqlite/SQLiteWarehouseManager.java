package db.sqlite;

	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.WarehouseManager;
import pojos.Client;
import pojos.Warehouse;
	

	public class SQLiteWarehouseManager implements WarehouseManager {
		
		private Connection c;
		
		public SQLiteWarehouseManager(SQLiteManager manager) {//duda sqlite manager????
			this.c=c;
			
			
		}
		public SQLiteWarehouseManager(Connection c2) {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void addWarehouse(Warehouse warehouse)  {

		}
			
			try {
				String sql = "INSERT INTO clients (id, name , cellphone , email, adress, dob) "
						+ "VALUES (?,?,?,?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, warehouse.getId());
				prep.setString(2, warehouse.getName());
				prep.setInt(1, warehouse.getCorridor());
				prep.setInt(1, warehouse.getShelve());
				//CHEATSHEETS PARA PASARLO A SQL
				
				prep.executeUpdate();
				prep.close();
			
				
				
			
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}// con esto he insert un client