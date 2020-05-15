package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pojos.*;
import pojos.users.*;
import xml.utils.CustomErrorHandler;
import db.interfaces.*;
import db.jpa.JPAUserManager;
import db.sqlite.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Menu {

	
	//DB Managers
	private static DBManager dbManager;
	private static ChocolateManager chocolateManager;
	private static ClientManager clientManager;
	private static AnimalManager animalManager;
	private static WarehouseManager warehouseManager;
	private static OompaLoompaManager oompaloompaManager;
	private static UserManager userManager; //JPA
	

	// Used for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//Buffered reader for my whole code
	private static BufferedReader reader; 
	
	//Menu
	public static void main(String[] args) throws Exception{
		
	    //Connects with the database
		dbManager = new SQLiteManager();
		dbManager.connect();
		//Need to create the tables with JDBC before using JPA
		dbManager.createTables();
		userManager = new JPAUserManager(); //we dont hace a constructor but we dont need it
		userManager.connect();
		
		
		chocolateManager = dbManager.getChocolateManager();
		clientManager = dbManager.getClientManager();
		animalManager= dbManager.getAnimalManager();
		
		
		//Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
        //Welcome screen
		System.out.print("-------------WELCOME!---------\n ");
		
		//we offer the user to create tables 
		System.out.println("Do you want to create the tables? (Y/N)");
		String yn = reader.readLine();
		if(yn.equalsIgnoreCase("y")){
			dbManager.createTables();
		}
		while(true){
		//Ask the user his/her role 
		System.out.println("What do you want to do? ");
		System.out.println("1.Create a new role");
		System.out.println("2.Create a new user");
		System.out.println("3.Login");
		System.out.println("0.Exit");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
		case 0:
			System.exit(0);
			dbManager.disconnect();
			userManager.disconnect();
			break;
		case 1: 
			//Create a new role
			newRole();
			break;
		
		case 2: 
			//Create a new user
			newUser();
			break;
		case 3: 
			//Login
			break;
		default: 
			break;
		}
	}
}

	private static void newRole() throws Exception{
		System.out.println("Please type the new role information ");
		System.out.println("Role name:");
		String roleName = reader.readLine();
		Role role = new Role(roleName);
		userManager.createRole(role);
		
		
		
	}
	
	private static void newUser() throws Exception{
		System.out.println("Please type the new user information ");
		System.out.println("Username: ");
		String username = reader.readLine();
		System.out.println("Password: ");
		String password = reader.readLine(); 
		//Create the password�s hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		//Show all the roles and let the user choose one
		List<Role> roles = userManager.getRoles();
		for (Role role : roles) {
			System.out.println(role);
		}
		
		System.out.println("Type the chosen role id: ");
		int roleId = Integer.parseInt(reader.readLine());
		//Get the chosen role from the database
		Role chosenRole = userManager.getRole(roleId);
		//Create the user and store it 
		User user = new User(username, hash, chosenRole);
		userManager.createUser(user);
		
		
		
	}
	
	
	private static void login() throws Exception{
		System.out.println("Please input your credentials ");
		System.out.println("Username: ");
		String username = reader.readLine();
		System.out.println("Password: ");
		String password = reader.readLine();
		User user = userManager.checkPassword(username, password);
		//check if the user/password combination was right
		if( user == null){
			System.out.println("Wrong credentials, please try again!");
		}
		//check the role 
		else if(user.getRole().getRole().equalsIgnoreCase("Willy Wonka")){
			System.out.println("Welcome Willy Wonka� " +username+ "!");
			willyWonkaMenu();
		}
		else if(user.getRole().getRole().equalsIgnoreCase("Oompa Loompa")){
			System.out.println("Welcome Oompa Loompa� " +username+ "!");
			OompaLoompaCeoMenu();
			
		}
		else{
			System.out.println("Invalid Role ");
		}
		
	}
//-----------------------------------------------------------------------------------
	
	//WILLY WONKA MENU
	
//--------------------------------------------------------------------------
	
	private static void willyWonkaMenu() throws Exception{
		boolean permanecer = true;
		
		while(permanecer) {
		System.out.println("Which are do you wanna manage?   ");
		System.out.println("1. CHOCOLATE   ");
		System.out.println("2. CLIENTS      ");
		System.out.println("3. ANIMALS       ");
		System.out.println("4. BACK");
		 
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
	
		case 1: 
			willyWonkaChocolate();
		    break;
		 
	    case 2: 
	    	willyWonkaClients();
		    break;

		case 3:
			willyWonkaAnimals();
		    break;
		    
		case 4:
			permanecer = false; 
		    break;
		default:
			break;
		
			}
		}
	}
	

	

	
//--------------------------------------------------------------------------------------------------------
	
	//WILLYWONKA CHOCOLATE
	
//------------------------------------------------------------------------------------------------
	
	
	private static void willyWonkaChocolate() throws Exception{
			int choice = Integer.parseInt(reader.readLine());
			
			
			System.out.println("You are going to manage the chocolate. ");
			System.out.println("What do you wanna do?     ");
			System.out.println("1. Create Chocolate       ");
			System.out.println("2. Show Chocolate       ");
			System.out.println("3. Delete Chocolate       ");
			System.out.println("4. Update Chocolate       ");
			System.out.println("5. Search By...           "); 
			System.out.println("6. Show chocolate         ");
			
			//revisar estas 
			//System.out.println("6. Admit chocolate ");
			System.out.println("7.get chocolate ");
            System.out.println(" Back");
			
	    switch(choice){	
		case 1: 
			//createChocolate();
			break;
		case 2: 
			showChocolate();
			break;

		case 3:
			deleteChocolate();
			break;
		case 4:
			updateChocolate();
			break;
		case 5: 
			System.out.println("Search animal by....");
			System.out.println("1. Name");
			System.out.println("2. Specie");
			int num = Integer.parseInt(reader.readLine());
			if(num == 1) {
			  searchChocolateByName();

			}
			else {
			  searchChocolateByType();
			}
			
			break;
		
		case 6: 
			showInformation();
			break; 
			
			}
}//function chocolate
		
//------------------------------------------------------------------------------------------	
	
	//CHOCOLATE PART
	
	
//------------------------------------------------------------------------------------
	
	
	
	
	   private static void showInformation() throws Exception{
		   List<Chocolate> chocolates = chocolateManager.showChocolates();
		   System.out.println("You we see all chocolates on the table ");
		   for (Chocolate chocolate : chocolates) {
				System.out.println(chocolate.toString());
			}
		  
		   
	   }
		
		
		
//---------------------------------------------------------------------	
	   
	//DELETE CHOCOLATE 
	   
	   
//-----------------------------------------------------------------------------	

		
		
	    private static boolean deleteChocolate() throws Exception{
	    	
	    	 boolean conexito = true;
	         int chocoId = 0;
	         
	         try{
	         System.out.println("Introduce the ID of the chocolate you want to remove from the table");
	         String id = reader.readLine();
	         chocoId = Integer.parseInt(id);
	         chocolateManager.delete(chocoId);
	         
	         }catch(Exception e){
	        	 e.printStackTrace();
	        	 conexito = false; 
	         }
	         
	    	
	    	
	    	return conexito;
	    	
	    	
	    }
	    
	 //---------------------------------------------------------------------	
		
	  		//UPDATE CHOCOLATE 
	  	
	 //-----------------------------------------------------------------------------	

	 
	    private static boolean updateChocolate() throws Exception {
	    	boolean exito= true; 
	        int chocoId= 0;
	    	try{
	    	System.out.println("Introduce the id of the chocolate ");
	        String id = reader.readLine();
	        chocoId = Integer.parseInt(id);
	        //I get the chocolate
	    	Chocolate toBeModified = chocolateManager.getChocolate(chocoId);
	    	System.out.println("Actual name: " +toBeModified.getName());
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new name or press enter to leave it as is: ");
	    	String newName = reader.readLine();
	    	if(newName.equals("")){
	    		newName = toBeModified.getName();
	    	}

	    	
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new type or press enter to leave it as is: ");
	    	String newType = reader.readLine();
	    	if(newType.equals("")){
	    		newType = toBeModified.getType();
	    	} 
	    
	        
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new cocoa or press enter to leave it as is: ");
	    	String newCocoa = reader.readLine();
	    	Float floatNewCocoa;
	    	if(newCocoa.equals("")){
	    		floatNewCocoa = toBeModified.getCocoa();
	    	}else{
	    		floatNewCocoa = Float.parseFloat(newCocoa);
	    	}
	    	
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new flavors or press enter to leave it as is: ");
	    	String newFlavors = reader.readLine();
	    	if(newFlavors.equals("")){
	    		newFlavors = toBeModified.getFlavors();
	    	}
	    	
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new units or press enter to leave it as is: ");
	    	String newUnits = reader.readLine();
	    	Float floatNewUnits;
	    	if(newUnits.equals("")){
	    		floatNewUnits = toBeModified.getUnits();
	    	}else{
	    		floatNewUnits = Float.parseFloat(newUnits);
	    	}
	    	
	    	//If the user doesn�t type anything, the name is not changed 
	    	System.out.println("Type the new shape or press enter to leave it as is: ");
	    	String newShape = reader.readLine();
	    	if(newShape.equals("")){
	    		newShape = toBeModified.getShape();
	    	}
	    	
	    	Chocolate updatedChocolate = new Chocolate( newName,newType, floatNewCocoa, newFlavors, floatNewUnits, newShape );
	       //At the very end... 
	    	chocolateManager.update(updatedChocolate);

	    	}catch(Exception e){
	    		e.printStackTrace();
	    		exito = false;
	    	}
	    	 return exito; 
	    	
	  
	    	
	    	
	    }

		
	//---------------------------------------------------------------------	

			//CREATE CHOCOLATE
		
	//-----------------------------------------------------------------------------	

		
		private static void createChocolate() throws Exception {
				//id lo crea el solo por ser autoincrement 
				
				System.out.println("Name");
				String name = reader.readLine();
				System.out.println("Type");
				String type = reader.readLine();
				System.out.println("Cocoa");
				Float cocoa = Float.parseFloat(reader.readLine());
				System.out.println("Flavors");
				String flavors = reader.readLine();
				System.out.println("Units");
				Float units = Float.parseFloat(reader.readLine());
				System.out.println("Shape");
				String shape = reader.readLine();
	            Chocolate chocolate =new Chocolate(name, type, cocoa, flavors, units, shape);
			   // to do insert the chocolate 
				chocolateManager.create(chocolate);
			}
			
			
			
			
			
			
			
	//---------------------------------------------------------------------	
			
				//SELECT CHOCOLATE
			
	//-----------------------------------------------------------------------------	

	
	     private static void showChocolate(){
	    	 
	     //cambiar para pedir id  y el nombre de la funcon 
	    	 //pedir id haciendo parseint por ser entero 
	    	 //llamar a la funcion 
	    	 try{
	    	 System.out.println("Introduce the chocolate�s ID");
		      int chocoId = Integer.parseInt(reader.readLine());
		      
		      List<Chocolate> chocolates = chocolateManager.searchChocolate(chocoId);
		      for (Chocolate chocolate : chocolates) {
				System.out.println(chocolate.toString());
		      }
	    	 }catch(Exception e){
	    		 e.printStackTrace();
	    	 }
	    	 
	     }


	//---------------------------------------------------------------------	

			//SEARCH CHOCOLATE BY NAME
		
	//-----------------------------------------------------------------------------	


		private static void searchChocolateByName() throws Exception{
			      System.out.println("Name");
			      String name = reader.readLine();
			    //why does he ask for the type if he is searching by name ?
			      List<Chocolate> chocolates = chocolateManager.searchByName(name);
			      for (Chocolate chocolate : chocolates) {
					System.out.println(chocolate.toString());
				}
		}
		
	//---------------------------------------------------------------------	
		
			//SEARCH CHOCOLATE BY TYPE
		
	//-----------------------------------------------------------------------------	

		private static void searchChocolateByType() throws Exception{
			     System.out.println("Type");
		         String type = reader.readLine();
			     List<Chocolate> chocolates = chocolateManager.searchByType(type);
			     for (Chocolate chocolate : chocolates) {
			    	 System.out.println(chocolate.toString());
					
				}
		}
		
		
		
		
	
//--------------------------------------------------------------------------------------------------------
		
		//WILLYWONKA CLIENTS
		
//------------------------------------------------------------------------------------------------		
	
	
		private static void willyWonkaClients() throws Exception{
			while(true) {
			
			//CLIENT
			System.out.println("1. Add client             ");
			System.out.println("2. Erase client           ");
			System.out.println("3. Select client          ");
			System.out.println("4. Search client by ...  ");
			System.out.println("5. Update client          ");
			System.out.println("6. generate XML         ");
			
		

			
			int choice = Integer.parseInt(reader.readLine());
			switch(choice){
			case 1: 
				addClient();
				break;
			case 2: 
				deleteClient();
				break;

			case 3:
				selectClient();
				break;
			case 4:
				System.out.println("Search client by....");
				System.out.println("1. Name");
				System.out.println("2. email");
				int num = Integer.parseInt(reader.readLine());
				if(num == 1) {
				  searchClientByName();

				}
				else {
				  searchClientByEmail();
				}
				
				break;

				
			case 5: 
				updateClient();
				break;
			case 6: 
				generateClientXML();
				break;	
			

			default: 
				break;
				
			
			}
			
		}
}
		
		
		
		
		
		
//--------------------------------------------------------------------------------------------------------
			
			//WILLYWONKA ANIMALS
			
//------------------------------------------------------------------------------------------------
	
		
		
		private static void willyWonkaAnimals() throws Exception{
			
		   while(true){
			   
			   
		   //ANIMAL
			 System.out.println("1. Add animal ");
			 System.out.println("2. Erase animal ");
			 System.out.println("3. Update");
			 System.out.println("4. Search by name ");
			 System.out.println("5. Search by species");
			 int choice = Integer.parseInt(reader.readLine());
			 switch(choice){
				
				
				case 1: 
					AddAnimal();
					break;
				case 2: 
					DeleteAnimal();
					break;
				case 3:
					UpdateAnimal();
					break;
				case 4:
					SearchAnimalByName();
					break;
				case 5: 
					SearchAnimalBySpecie();
					break;

				default: 
					break;
					
				
				}
		     
			
			
			
			
			
		}
	}
		
	    
//---------------------------------------------------------------------	
			
		  		//OMOMPALOOMPA MENU
		  	
//-----------------------------------------------------------------------------	

		    

			private static void OompaLoompaCeoMenu()throws Exception {
				
			   
				while (true) {
				
				
				System.out.println("Which part do you wanna modify? ");
				System.out.println("1. MILK             ");
				System.out.println("2. WAREHOUSE        ");
				System.out.println("3. WORKERS          ");
				
			
				int choice = Integer.parseInt(reader.readLine());
				switch(choice){
				case 1: 
					OompaLoompaMilk();
					break;
				
				case 2: 
					OompaLoompaWarehouse();
					break;
				case 3: 
					OompaLoompaWorkers();
					break;
	
				
				default: 
					break;

				}	}

				
			}//OompaLoompaMenu
			
			
//---------------------------------------------------------------------	
			
			//OOMPALOOMPA MILK 
				
//-----------------------------------------------------------------------------	


			public static void OompaLoompaMilk () throws Exception {
				
			}
			
			
			
			
			
			
//---------------------------------------------------------------------	
			
			//OOMPALOOMPA WAREHOUSE 
		
//-----------------------------------------------------------------------------	

			
			
			public static void OompaLoompaWarehouse () throws Exception {
				System.out.println("1. Add Warehouse ");
				System.out.println("2. Delete Warehouse ");
				System.out.println("3. Update Warehouse ");
				System.out.println("4. Select Warehouse ");
				System.out.println("5. Search by Name ");
				System.out.println("6. Search by Corridor");
				
				int choice = Integer.parseInt(reader.readLine());
				switch(choice) {
				case 1:
					AddWarehouse();
					break;
				case 2: 
					DeleteWarehouse();
					break;
				case 3:
					UpdateWarehouse();
					break;
				case 4:
					ShowWarehouse();
					break;
				case 5:
					SearchWarehouseByName();
					break;
				case 6:
					SearchWarehouseByCorridor();
					break;
				default:
					break;
				
				}
			}	


			
			
			
			
			
			
			
//---------------------------------------------------------------------	
			
			//OOMPALOOMPA WORKERS
		
//-----------------------------------------------------------------------------	

			
			public static void OompaLoompaWorkers () throws Exception {
				System.out.println("1. Add Worker ");
				System.out.println("2. Delete Worker ");
				System.out.println("3. Update Worker ");
				System.out.println("4. Select Worker ");
				System.out.println("5. Search by Name ");
				System.out.println("6. Search by Date of Birth ");
				
				int choice = Integer.parseInt(reader.readLine());
				switch(choice) {
				case 1:
					AddWorker();
					break;
				case 2: 
					DeleteWorker();
					break;
				case 3:
					UpdateWorker();
					break;
				case 4:
					ShowWorker();
					break;
				case 5:
					SearchWorkerByName();
					break;
				case 6:
					SearchWorkerByDOB();
					break;
				default:
					break;
				
				}
				
			}
	
			
			
			
			
			
		
			
			
			
//----------------------------------------------------------------------------------------------
			
			 //WAREHOUSE PART 
				
//----------------------------------------------------------------
			//ADD WAREHOUSE
//----------------------------------------------------------------
	private static void AddWarehouse() throws Exception{
		System.out.print("Please introduce the new Warehouse");
		System.out.print("1. Name of the warehouse: ");
		String name = reader.readLine();
		System.out.print("2. Corridor number: ");
		int corridor = Integer.parseInt(reader.readLine());
		System.out.print("3. Shelve number: ");
		int shelve = Integer.parseInt(reader.readLine());
		Warehouse warehouse = new Warehouse (name,corridor, shelve);
		warehouseManager.add(warehouse);
		
	}
	
//----------------------------------------------------------------
		//DELETE WAREHOUSE
//----------------------------------------------------------------
	private static boolean DeleteWarehouse() throws Exception{
		
	    	ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
	    	 boolean success = true;
	         boolean found = false;
	         int index = 0;
	         
	         System.out.println("Name");
			 String name = reader.readLine();
	         
	         for (int i = 0; i < warehouses.size(); i++) {
	             if (warehouses.get(i).getName().equalsIgnoreCase(name)) {
	                 index = i; 
	                 found = true;
	                 break;
	             }
	         }

	         if (found == true) {
	             warehouses.remove(index); 
	             warehouseManager.delete(name);

	         } else {
	             success = false;
	         }
	    	
	    	
	    	return success;
	    		
	    }
	   	
	
//----------------------------------------------------------------
		// SELECT/SHOW WAREHOUSE
//----------------------------------------------------------------
	private static void ShowWarehouse() throws Exception{
		 try{
	    	 System.out.println("Introduce id of warehouse: ");
		      int WHid = Integer.parseInt(reader.readLine());
		      Warehouse warehouse = warehouseManager.select(WHid);
		      warehouse.toString();
	    	 }catch(Exception e){
	    		 e.printStackTrace();
	    	 }
		
	}

//----------------------------------------------------------------
			//UPDATE  WAREHOUSE
//----------------------------------------------------------------
	private static void UpdateWarehouse()throws Exception{
		System.out.print("Introduce the id of the warehouse you want to update: " );
		int WHid = Integer.parseInt(reader.readLine());
		Warehouse oldWarehouse = warehouseManager.select(WHid);
		System.out.print("Actual name: "+oldWarehouse.getName());
		System.out.print("Type new name or enter to continue: ");
		String name = reader.readLine();
		if(name.equals("")) {
			name = oldWarehouse.getName();
		}
		System.out.print("Actual corridor: "+oldWarehouse.getCorridor());
		System.out.print("Type ner corridor or enter to continue: ");
		String scorridor = reader.readLine();
		int corridor;
		if (scorridor.equals("")) {
			corridor = oldWarehouse.getCorridor();
		}
		else {
			corridor = Integer.parseInt(scorridor);
		}
		System.out.print("Actual shelve: "+oldWarehouse.getCorridor());
		System.out.print("Type ner corridor or enter to continue: ");
		String sshelve = reader.readLine();
		int shelve;
		if (sshelve.equals("")) {
			shelve = oldWarehouse.getShelve();
		}
		else {
			shelve = Integer.parseInt(sshelve);
		}
		
		Warehouse warehouse = new Warehouse(WHid, name, corridor, shelve);
		warehouseManager.update(warehouse);
		
		
	}
//----------------------------------------------------------------
	//SEARCH WAREHOUSE BY NAME
//----------------------------------------------------------------
	private static void SearchWarehouseByName() throws Exception{
		System.out.print("Please, introduce the name of the warehouse you want to look for");
		String name= reader.readLine();
		List <Warehouse> warehouses= warehouseManager.searchByName(name);
		for (Warehouse warehouse : warehouses) {
			System.out.println(warehouse);
		}
	}
//----------------------------------------------------------------
	//SEARCH WAREHOUSE BY CORRIDOR
//----------------------------------------------------------------
	private static void SearchWarehouseByCorridor() throws Exception{
		System.out.print("Please, introduce the corridor of the warehouse you want to look for");
		int corridor = Integer.parseInt(reader.readLine());
		List <Warehouse> warehouses= warehouseManager.searchByCorridor(corridor);
		for (Warehouse warehouse : warehouses) {
			System.out.println(warehouse);
		}
	}
	
	
	
	
	
	
	
//----------------------------------------------------------------------------------------------
	
	 //WORKERS PART 
		
//----------------------------------------------------------------
	//ADD WORKER
//----------------------------------------------------------------
	private static void AddWorker() throws Exception{
		System.out.print("Please introduce the new Worker");
		System.out.print("1. Name of the worker: ");
		String name = reader.readLine();
		System.out.print("2. Cellphone: ");
		int cellphone = Integer.parseInt(reader.readLine());
		System.out.print("3. Email: ");
		String email = reader.readLine();
		System.out.print("4. Address: ");
		String address = reader.readLine();
		System.out.print("5. Date of Birth (yyyy-mm-dd): ");
		LocalDate dob = LocalDate.parse(reader.readLine(), formatter); 
		OompaLoompa oompaloompa = new OompaLoompa (name,cellphone,email,address, Date.valueOf(dob));
		oompaloompaManager.add(oompaloompa);

}

//----------------------------------------------------------------
//DELETE WORKER
//----------------------------------------------------------------
private static boolean DeleteWorker() throws Exception{
	ArrayList<OompaLoompa> workers = new ArrayList<OompaLoompa>();
	 boolean success = true;
    boolean found = false;
    int index = 0;
    
    System.out.println("Name");
	 String name = reader.readLine();
    
    for (int i = 0; i < workers.size(); i++) {
        if (workers.get(i).getName().equalsIgnoreCase(name)) {
            index = i; 
            found = true;
            break;
        }
    }

    if (found == true) {
        workers.remove(index); 
        oompaloompaManager.delete(name);

    } else {
        success = false;
    }
	
	
	return success;
		

	
}
//----------------------------------------------------------------
// SELECT/SHOW WORKER
//----------------------------------------------------------------
private static void ShowWorker() throws Exception{
	
		 try{
	    	 System.out.println("Introduce id of worker: ");
		      int Wid = Integer.parseInt(reader.readLine());
		      OompaLoompa worker = oompaloompaManager.select(Wid);
		      worker.toString();
	    	 }catch(Exception e){
	    		 e.printStackTrace();
	    	 }
		
	}




//----------------------------------------------------------------
	//UPDATE WORKER
//----------------------------------------------------------------
private static void UpdateWorker()throws Exception{
	
		System.out.print("Introduce the id of the worker you want to update: " );
		int Wid = Integer.parseInt(reader.readLine());
		OompaLoompa oldWorker = oompaloompaManager.select(Wid);
		System.out.print("Actual name: "+oldWorker.getName());
		System.out.print("Type new name or enter to continue: ");
		String name = reader.readLine();
		if(name.equals("")) {
			name = oldWorker.getName();
		}
		System.out.print("Actual cellphone: "+oldWorker.getCellphone());
		System.out.print("Type new cellphone or enter to continue: ");
		String scellphone = reader.readLine();
		int cellphone;
		if (scellphone.equals("")) {
			cellphone = oldWorker.getCellphone();
		}
		else {
			cellphone = Integer.parseInt(scellphone);
		}
		System.out.print("Actual email: "+oldWorker.getEmail());
		System.out.print("Type new email or enter to continue: ");
		String email = reader.readLine();
		if(email.equals("")) {
			email = oldWorker.getEmail();
		}
		System.out.print("Actual address: "+oldWorker.getAddress());
		System.out.print("Type new address or enter to continue: ");
		String address = reader.readLine();
		if(address.equals("")) {
			address = oldWorker.getAddress();
		}
		System.out.print("Actual date of birth: "+oldWorker.getDob());
		System.out.print("Type new date of birth (yyyy-mm-dd) or enter to continue: ");
		String sdob = reader.readLine();
		Date dob;
		if(sdob.equals("")) {
			dob = oldWorker.getDob();
		}
		else {
			dob = Date.valueOf(LocalDate.parse(sdob, formatter));
		}
		
		OompaLoompa oompaloompa = new OompaLoompa(Wid, name, cellphone, email, address, dob);
		oompaloompaManager.update(oompaloompa);
		
		
	}

//----------------------------------------------------------------
//SEARCH WORKER BY NAME
//----------------------------------------------------------------
private static void SearchWorkerByName() throws Exception{
System.out.print("Please, introduce the name of the worker you want to look for");
String name= reader.readLine();
List <OompaLoompa> workers= oompaloompaManager.searchByName(name);
for (OompaLoompa oompaloompa : workers) {
	System.out.println(oompaloompa);
}
}
//----------------------------------------------------------------
//SEARCH WORKER BY DOB
//----------------------------------------------------------------
private static void SearchWorkerByDOB() throws Exception{
System.out.print("Please, introduce the Date of Birth of the worker you want to look for");
LocalDate dob = LocalDate.parse(reader.readLine(), formatter); 
List <OompaLoompa> workers= oompaloompaManager.searchBydob(Date.valueOf(dob));
for (OompaLoompa oompaloompa : workers) {
	System.out.println(oompaloompa);
}
}
	

			
//----------------------------------------------------------------------------------------------
		
			 //ANIMALS PART
				
//----------------------------------------------------------------
		
				//ADD ANIMAL
	
//--------------------------------------------------
		
		
	private static void AddAnimal() throws Exception{
		
		System.out.print("Please, introduce the new animal");
		System.out.print("1. Name of the animal: ");
		String name;
		name= reader.readLine();
		System.out.print("2. Country of the animal:");
		String country;
		country= reader.readLine();
		System.out.print("3. Colour/s of the animal: ");
		String colour;
		colour= reader.readLine();
		System.out.print("4. Specie of the animal: ");
		String specie;
		specie= reader.readLine();
		System.out.print("5. Date of birth of the animal in this format (year-month-day)      ");
		String date= reader.readLine();
		LocalDate dob = LocalDate.parse(date, formatter); 
		Animal animal= new Animal (name, country, colour, specie, Date.valueOf(dob));
		animalManager.add(animal);
		//TODO insert the poor animal
		
		}
	
	
	
	
	
//----------------------------------------------------
	
		//SEARCH ANIMAL BY NAME
	
//--------------------------------------------------
	
	

	
	
	private static void SearchAnimalByName() throws Exception{
		System.out.print("Please, introduce the name of the animal you want to look for");
		String name= reader.readLine();
		List <Animal> animals= animalManager.searchByNameAnimal(name);
		for (Animal animal : animals) {
			System.out.println(animal);
		}
	}
	
		
		
		
//--------------------------------------------------------------
		
	
	//SEARCH ANIMAL BY SPECIE
	
	
//--------------------------------------------------------------
	
	
	private static void SearchAnimalBySpecie() throws Exception{
		System.out.print("Please, introduce the specie of the animal you want to look for");
		String specie= reader.readLine();
		List <Animal> animals= animalManager.searchBySpecieAnimal(specie);
		for (Animal animal : animals) {
			System.out.println(animal);
		}
	}
	
	
	
	
//----------------------------------------------------------------------------------
	
	
	//DELETE ANIMAL
	

//------------------------------------------------------------------------------------------	

	
	
    private static boolean DeleteAnimal() throws Exception{
    	
   	 boolean conexito = true;
   	 int AnimalId;
        
        try{
        System.out.println("Introduce the ID of the chocolate you want to remove from the table");
        String id = reader.readLine();
        AnimalId = Integer.parseInt(id);
        animalManager.delete(AnimalId);
        
        }catch(Exception e){
       	 e.printStackTrace();
       	 conexito = false; 
        }
        
   	
   	
   	return conexito;
   	
   	
   }
	
//----------------------------------------------------------------------------------------------------
    
    //UPDATE ANIMAL
    
//-----------------------------------------------------------------------------------------------
    
 
    private static boolean UpdateAnimal() throws Exception {
    	boolean exito= true; 
        int AnimalId= 0;
    	try{
    	System.out.println("Introduce the id of the animal ");
        String id = reader.readLine();
        AnimalId = Integer.parseInt(id);
        //I get the animal
    	Animal toBeModified = animalManager.getAnimal(AnimalId);
    	System.out.println("Actual name: " +toBeModified.getName());
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new name or press enter to leave it as is: ");
    	String newName = reader.readLine();
    	if(newName.equals("")){
    		newName = toBeModified.getName();
    	}

    	//If the user doesn�t type anything, the country is not changed 
    	System.out.println("Type the new country or press enter to leave it as is: ");
    	String newCountry = reader.readLine();
    	if(newCountry.equals("")){
    		newCountry = toBeModified.getCountry();
    	}
    	
    	//If the user doesn�t type anything, the colour is not changed 
    	System.out.println("Type the new colour or press enter to leave it as is: ");
    	String newColour = reader.readLine();
    	if(newColour.equals("")){
    		newColour = toBeModified.getColour();
    	} 
    
    	
    	//If the user doesn�t type anything, the specie is not changed 
    	System.out.println("Type the new specie or press enter to leave it as is: ");
    	String newSpecie = reader.readLine();
    	if(newSpecie.equals("")){
    		newSpecie = toBeModified.getSpecie();
    	}
    	
    	
    	//If the user doesn�t type anything, the date of birth is not changed 
    	System.out.println("Type the new date of birth in this format (year-month-day)"
    			+ " or press enter to leave it as is: ");

		String newDob = reader.readLine();
		Date dateNewDob; 
    	if(newDob.equals("")){
    		dateNewDob = toBeModified.getDob();
    	}
    	else {
    		dateNewDob = Date.valueOf(LocalDate.parse(newDob, formatter));  
    	}
    	Animal updatedAnimal = new Animal( newName, newCountry, newColour, newSpecie, dateNewDob );
       //At the very end... 
    	animalManager.update(updatedAnimal);

    	}catch(Exception e){
    		e.printStackTrace();
    		exito = false;
    	}
    	 return exito; 
    	
    	
  
    	
    	
    }

	


//--------------------------------------------------------------------------------------------------------------
	
	
	
		//CLIENT PART
	

//--------------------------------------------------------------------------------------------------------------------------------
	
	                                                   //ADD CLIENT
	
//--------------------------------------------------------------------------------------------------------------------------------	
	private static void addClient() throws Exception {
		System.out.println("Type!");
		System.out.println("Name");
		String name = reader.readLine();
		System.out.println("Cellphone");
		Integer cellphone =Integer.parseInt( reader.readLine());
		System.out.println("email");
		String email = reader.readLine();
		System.out.println("adress");
		String adress = reader.readLine();
		System.out.println("date of birth (yyyy-MM-dd");
		String dob= reader.readLine();
		LocalDate dateOfBirth = LocalDate.parse(dob,formatter);
		
		
        Client client = new Client(name, cellphone, email, adress, Date.valueOf(dateOfBirth));
	   // creo q este add esta bien pero no estoy segura
		clientManager.addClient(client);
	}	
	
//--------------------------------------------------------------------------------------------------------------------------------

                                                       //DELETE CLIENT

//--------------------------------------------------------------------------------------------------------------------------------
	private static boolean deleteClient() throws Exception{
    	ArrayList<Client> clients = new ArrayList<Client>();
    	 boolean conexito = true;
         boolean encontrado = false;
         int indice = 0;
         //lo inicializamos como un indice no valido
         //buscamos el indice que queremos eliminar
         
         System.out.println("Name");
		 String name = reader.readLine();
         
         for (int i = 0; i < clients.size(); i++) {
             if (clients.get(i).getName().equals(name)) {
                 indice = i; //guardamos el indice donde se encuentra el cliente
                 encontrado = true;
                 break;
             }
         }

         if (encontrado) {
             clients.remove(indice); //el indice encontrado lo eliminamos

         } else {
             conexito = false;
         }
    	
    	
    	return conexito;
    		
    }
	
//--------------------------------------------------------------------------------------------------------------------------------

                                                 //SELECT CLIENT

//--------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	private static void selectClient() throws Exception{// select client by name
		   
		
	    	 
		     //cambiar para pedir id  y el nombre de la funcon 
		    	 //pedir id haciendo parseint por ser entero 
		    	 //llamar a la funcion 
		    	 try{
		    	 System.out.println("Introduce the client ID");
			      int clientId = Integer.parseInt(reader.readLine());
			      
			      List<Client> clients = clientManager.searchClient(clientId);
			      for (Client client : clients) {
					System.out.println(client.toString());
			      }
		    	 }catch(Exception e){
		    		 e.printStackTrace();
		    	 }
		    	 
		     
	}
	
//--------------------------------------------------------------------------------------------------------------------------------

                                                 //SEARCH CLIENT BY NAME

//--------------------------------------------------------------------------------------------------------------------------------	
	private static void searchClientByName() throws Exception{
		  System.out.println("Type!");
	      System.out.println("Name");
	      String name = reader.readLine();
	      List<Client> clients = clientManager.searchByName(name);
	      for (Client client : clients) {
			System.out.println(client);
		}
}
//--------------------------------------------------------------------------------------------------------------------------------

    //SEARCH CLIENT BY EMAIL

//--------------------------------------------------------------------------------------------------------------------------------	
private static void searchClientByEmail() throws Exception{
System.out.println("Type!");
System.out.println("email");
String email = reader.readLine();
List<Client> clients = clientManager.searchByEmail(email);
for (Client client : clients) {
System.out.println(client);
}
}	

//--------------------------------------------------------------------------------------------------------------------------------

                                                 //UPDATE CLIENT

//--------------------------------------------------------------------------------------------------------------------------------
	private static boolean updateClient() throws Exception {
		boolean exito= true; 
        int clientId= 0;
    	try{
    	System.out.println("Introduce the id of the client ");
        String id = reader.readLine();
        clientId = Integer.parseInt(id);
        //I get the chocolate
    	Client toBeModified = clientManager.getClient(clientId);
    	System.out.println("Actual name: " +toBeModified.getName());
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new name or press enter to leave it as is: ");
    	String newName = reader.readLine();
    	if(newName.equals("")){
    		newName = toBeModified.getName();
    	}

    	
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new cellphone or press enter to leave it as is: ");
    	String newCellphone = reader.readLine();
    	Integer intNewCellphone;
    	if(newCellphone.equals("")){
    		intNewCellphone = toBeModified.getCellphone();
    	}else{
    		intNewCellphone = Integer.parseInt(newCellphone);
    	}
    
        
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new email or press enter to leave it as is: ");
    	String newEmail = reader.readLine();
    	if(newEmail.equals("")){
    		newEmail = toBeModified.getEmail();
    	}
    	
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new adress or press enter to leave it as is: ");
    	String newAdress = reader.readLine();
    	if(newAdress.equals("")){
    		newAdress = toBeModified.getAdress();
    	}
    	
    	
    	//If the user doesn�t type anything, the name is not changed 
    	System.out.println("Type the new shape or press enter to leave it as is: ");
    	String newDob = reader.readLine();
    	Date dateNewDob;
    	if(newDob.equals("")){
    		dateNewDob = toBeModified.getDob();
    	}else{
    		dateNewDob =  Date.valueOf(LocalDate.parse(newDob, formatter));
    	}
    	
    	Client updatedClient = new Client(newName, intNewCellphone, newEmail, newAdress, dateNewDob);
       //At the very end... 
    	clientManager.update(updatedClient);

    	}catch(Exception e){
    		e.printStackTrace();
    		exito = false;
    	}
    	 return exito;  
    	
    }
	
//--------------------------------------------------------------------------------------------------------------------------------

    //GENERATE CLIENT XML

//--------------------------------------------------------------------------------------------------------------------------------	
	
private static void generateClientXML() throws Exception {
		System.out.print("Please introduce the id of the Client");
		System.out.print("1. client id ");
		Integer clientId = Integer.parseInt( reader.readLine());
		Client client = clientManager.getClient(clientId);
		// Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Client.class);
		// Get the marshaller
		Marshaller marshal = context.createMarshaller();
		// Pretty formatting
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Marshall the dog to a file
		File file = new File("./xmls/Output-Client.xml");
		marshal.marshal(client, file);
		// Marshall the dog to the screen
		marshal.marshal(client, System.out);
	}	
//--------------------------------------------------------------------------------------------------------------------------------

                    //CREATE A CLIENT WITH XML

//--------------------------------------------------------------------------------------------------------------------------------
	
/*
 * private static void admitClientXML() throws Exception {
		// Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Client.class);
		// Get the unmarshaller
		Unmarshaller unmarshal = context.createUnmarshaller();
		// Open the file
		File file = null;
		boolean incorrectClient = false;
		do {
			System.out.println("Type the filename for the XML document (expected in the xmls folder):");
			String fileName = reader.readLine();
			file = new File("./xmls/" + fileName);
			try {
				// Create a DocumentBuilderFactory
				DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
				// Set it up so it validates XML documents
				dBF.setValidating(true);
				// Create a DocumentBuilder and an ErrorHandler (to check validity)
				DocumentBuilder builder = dBF.newDocumentBuilder();
				CustomErrorHandler customErrorHandler = new xml.utils.CustomErrorHandler();
				builder.setErrorHandler(customErrorHandler);
				// Parse the XML file and print out the result
				Document doc = builder.parse(file);
				if (!customErrorHandler.isValid()) {
					incorrectClient = true;
				}
			} catch (ParserConfigurationException ex) {
				System.out.println(file + " error while parsing!");
				incorrectClient = true;
			} catch (SAXException ex) {
				System.out.println(file + " was not well-formed!");
				incorrectClient = true;
			} catch (IOException ex) {
				System.out.println(file + " was not accesible!");
				incorrectClient = true;
			}
			
		} while (incorrectClient);
		// Unmarshall the dog from a file
		Client client = (Client) unmarshal.unmarshal(file);
		// Print the client
		System.out.println("Added to the database: " + client);
		clientManager.admit(client);
		// Get the dogId from the database because the XML file doesn't have it
		int clientId = dbManager.getLastId();
		
		}
	}	
 */
	
}//main

	