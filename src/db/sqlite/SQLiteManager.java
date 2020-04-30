package db.sqlite;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.interfaces.ChocolateManager;
import db.interfaces.ClientManager;
import db.interfaces.DBManager;
import db.interfaces.AnimalManager;


public class SQLiteManager implements DBManager {
    
	private Connection c;
//	private ChocolateManager chocolate;
//	private ClientManager client;
//	private AnimalManager animal;
	
	public SQLiteManager(){
	//with an empty constructor
		super();
	}
	@Override
	public void connect() {
		 
		//try-catch because it can create exceptions 
		try{
			// Open database connection
		Class.forName("org.sqlite.JDBC");
		this.c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
		
		Statement sta = c.createStatement();
		
		
		
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		//Create ChocolateManager 
		//chocolate = new SQLiteChocolateManager(c);
		
		//Create others like ClientManager... 
	//	client = new SQLiteClientManager(c);
		
		//Create AnimalManager
	//	animal= new SQLiteAnimalManager(c);
		
   // we could initialize other managers here		
		
		}catch(Exception e){  //general exception 
			e.printStackTrace();
		}
	

	}
	
/*We don�t use it
 	protected Connection getConnection(){
		return c;
	}*/

	public Connection getConnection(){
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
	
	/*
	@Override
	public ChocolateManager getChocolateManager(){
		
		return chocolate;
	}
	
	@Override
	public ClientManager getClientManager() {
		return client;
	}
	
	@Override
	public AnimalManager getAnimalManager() {
		return animal;
	}
	*/
	public void createAnimalTables() {
		Statement stmt3;

		try{
		   stmt3 = c.createStatement();
		   String sql1 = "CREATE TABLE chocolate"
				+ "(id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "country     TEXT    NOT NULL,"
				+ "colour     TEXT    NOT NULL,"
				+ "specie   TEXT    NOT NULL,"
				+ "dob     DATE   NOT NULL)";
		   stmt3.executeUpdate(sql1);
		   stmt3.close();
		   
		}catch(SQLException e){ 
			//if there are exception of type "SQLException" we are not doing nothing 
			if(e.getMessage().contains("already exists")){ //we are not going to do anything, or we can type {} or ; (and its the same) 
			}else{
				e.printStackTrace();
			}
		}

	}

	@Override
	public void createTables() {
		Statement stmt1;
		Statement stmt2;

		try{
		   //CHOCO TABLE 
		   stmt1 = c.createStatement();
		   String sql1 = "CREATE TABLE IF NOT EXISTS chocolate"
				+ "(id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "cocoa     TEXT    NOT NULL,"
				+ "type      TEXT    NOT NULL,"
				+ "flavors   TEXT    NOT NULL,"
				+ "units     FLOAT   NOT NULL,"
				+ "shape     TEXT    NOT NULL"
				+ "hash		 INT	 NULL)";
		   stmt1.executeUpdate(sql1);
		   
		  
		   
		   //CLIENT TABLE 
		   stmt2 = c.createStatement();
		   String sql2 = "CREATE TABLE client"
				+ "(id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "cellphone    NUMERIC   NOT NULL UNIQUE,"
				+ "email     TEXT    NOT NULL  UNIQUE,"
				+ "adress   TEXT    NOT NULL,"
				+ "dob     DATE    NOT NULL)";
		   stmt2.executeUpdate(sql2);
		   
///COMO DEBO INSERTAR ESTO? PARA QUE SE UNAN? 
		   stmt2.close();
		   stmt1.close();
		   
		}catch(SQLException e){ 
			//if there are exception of type "SQLException" we are not doing nothing 
			if(e.getMessage().contains("already exists")){ //we are not going to do anything, or we can type {} or ; (and its the same) 
			}else{
				e.printStackTrace();
			}
		}

	}
	

	

	
	//??falta una funcion getLastID() 

}
