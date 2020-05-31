package db.sqlite;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.interfaces.ChocolateManager;
import db.interfaces.ClientManager;
import db.interfaces.DBManager;
import db.interfaces.MilkManager;
import db.interfaces.OompaLoompaManager;
import db.interfaces.WarehouseManager;
import db.interfaces.AnimalManager;


public class SQLiteManager implements DBManager {
    
	private Connection c;
	private ChocolateManager chocolate;
	private ClientManager client;
	private AnimalManager animal;
	private WarehouseManager warehouse;
	private OompaLoompaManager oompaLoompa;
	private MilkManager milk;
	
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
		this.c = DriverManager.getConnection("jdbc:sqlite:./db/chocolate.db");
		
		Statement stmt1 = c.createStatement();
		
		stmt1.execute("PRAGMA foreign_keys=ON");
		
		//c.createStatement().execute("PRAGMA foreign_keys=ON");
		//Create ChocolateManager 
		chocolate = new SQLiteChocolateManager(c);
		
		//Create others like ClientManager... 
		client = new SQLiteClientManager(c);
		oompaLoompa = new SQLiteOompaLoompaManager(c);
		warehouse = new SQLiteWarehouseManager(c);
		
		//Create AnimalManager
	     animal= new SQLiteAnimalManager(c);
		 //milk = new SQLiteMilkManager(c);
   // we could initialize other managers here		
		
		}catch(Exception e){  //general exception 
			e.printStackTrace();
		}
	

	}
	


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
	
	
	@Override
	public ChocolateManager getChocolateManager(){
		
		return chocolate;
	}
	@Override
	public OompaLoompaManager getOompaLoompaManager() {
		return oompaLoompa;
	}
	
	@Override
	public WarehouseManager getWarehouseManager() {
		return warehouse;
	}
	
	@Override
	public ClientManager getClientManager() {
		return client;
	}
	
	@Override
	public AnimalManager getAnimalManager() {
		return animal;
	}
	


	@Override
	public void createTables() {
		Statement stmt1, stmt2, stmt3, stmt4, stmt5,stmt6,stmt7;
 
		try{
		   //CHOCO TABLE 
		   stmt1 = c.createStatement();
		   String sql1 = "CREATE TABLE IF NOT EXISTS chocolate("
				+ "id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "cocoa     TEXT    NOT NULL,"
				+ "type      TEXT    NOT NULL,"
				+ "flavors   TEXT    NOT NULL,"
				+ "units     FLOAT   NOT NULL,"
				+ "shape     TEXT    NOT NULL)";
		   stmt1.executeUpdate(sql1);
		   
		  //CLIENT TABLE 
		   stmt2 = c.createStatement();
		   String sql2 = "CREATE TABLE client("
				+ "id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "cellphone    NUMERIC   NOT NULL UNIQUE,"
				+ "email     TEXT    NOT NULL  UNIQUE,"
				+ "adress   TEXT    NOT NULL,"
				+ "dob     DATE    NOT NULL)";
		   stmt2.executeUpdate(sql2);
		   
		   //ANIMAL TABLE 
		   stmt3 = c.createStatement();
		   String sql3 = "CREATE TABLE animal("
				+ "id       INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name      TEXT    NOT NULL,  "
				+ "country     TEXT    NOT NULL,"
				+ "colour     TEXT    NOT NULL,"
				+ "specie   TEXT    NOT NULL,"
				+ "dob     DATE   NOT NULL)";
		   stmt3.executeUpdate(sql3);
		   
		   //MILK TABLE 
		   stmt4 = c.createStatement();
		   String sql4 = "CREATE TABLE milk"
				   + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
				   + "name      TEXT    NOT NULL,  "
				   + "type      TEXT    NOT NULL  "
				   + "photo     BLOB )";
		   stmt4.executeUpdate(sql4);
		   
		   //OOMPA LOOMPA TABLE
		   stmt6=c.createStatement();
		   String sql6="CREATE TABLE oompaLoompa("
		   		+ "id 			INTEGER PRIMARY KEY AUTOINCREMENT,"
		   		+ "name 		TEXT NOT NULL,"
		   		+ "cellphone 	INTEGER NOT NULL,"
		   		+ "email 		TEXT NOT NULL,"
		   		+ "address 		TEXT NOT NULL,"
		   		+ "dob 			DATE NOT NULL)";
	   		stmt6.executeUpdate(sql6);
		   
	   		//WAREHOUSE TABLE	
	   		
	   		stmt7=c.createStatement();
			   String sql7="CREATE TABLE warehouse("
			   		+ "id 			INTEGER PRIMARY KEY AUTOINCREMENT,"
			   		+ "name 		TEXT NOT NULL,"
			   		+ "corridor 	INTEGER NOT NULL,"
			   		+ "shelve 		INTEGER NOT NULL)";
		   		stmt7.executeUpdate(sql7);
	   		
	   		
	   		
		   //TABLE OF THE MANY-TOMANY RELATIONSHIP BETWEEN CHOCOLATE & MILK
		   stmt5= c.createStatement();
			String sql5 = "CREATE TABLE chocolateMilks " 
			+ "(chocoId     INTEGER  REFERENCES chocolate(id), "
			+ "milkId     INTEGER  REFERENCES milk(id), " 
			+ "PRIMARY KEY(chocoid, milkId))";
		   stmt5.executeUpdate(sql5);

		   stmt1.close();
		   stmt2.close();
		   stmt3.close();
		   stmt4.close();
		   stmt5.close();
		   stmt6.close();
		   stmt7.close();
		   
		}catch(SQLException e){ 
			//if there are exception of type "SQLException" we are not doing nothing 
			if(e.getMessage().contains("already exists")){ //we are not going to do anything, or we can type {} or ; (and its the same) 
			}else{
				e.printStackTrace();
			}
		}

	}
	@Override
	public int getLastId() {
		int result = 0;
		try {
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			result = rs.getInt("lastId");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	
	

	

}
