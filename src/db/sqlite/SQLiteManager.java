package db.sqlite;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import db.interfaces.DBManager;

public class SQLiteManager implements DBManager {
    
	private Connection c;
	private ChocolateManager chocolate;
	
	public SQLiteManager(){
	//with an empty constructor
		super();
	}
	@Override
	public void connect() {
		// Open database connection 
		//try-catch because it can create exceptions 
		try{
		Class.forName("org.sqlite.JDBC");
		this.c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		chocolate = new SQLiteChocolateManager(c);
		
		}catch(Exception e){//excepcion general 
			e.printStackTrace();
		}
	

	}
	
	protected Connection getConnection(){
		return c;
	}

	@Override
	public void disconnect() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void createTables() {
		Statement stmt1;

		try{
		   stmt1 = c.createStatement();
		   String sql1 = "CREATE TABLE chocolate"
				+ "(id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "cocoa     TEXT    NOT NULL,"
				+ "type      TEXT    NOT NULL,"
				+ "flavors   TEXT    NOT NULL,"
				+ "units     FLOAT   NOT NULL,"
				+ "shape     TEXT    NOT NULL)";
		   stmt1.executeUpdate(sql1);
		   stmt1.close();
		   
		}catch(SQLException e){
			e.printStackTrace();
		}

	}
	
	public ChocolateManager getChocolateManager(){
		return chocolate;
	}

}
