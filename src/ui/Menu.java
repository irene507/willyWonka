package ui;

import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pojos.*;
import db.interfaces.*;
import db.sqlite.*;
import java.util.*;

public class Menu {

	
	//DB Managers
	private static DBManager dbManager;
	private static ChocolateManager chocolateManager;
	private static ClientManager clientManager;
	private static AnimalManager animalManager;
	private static WarehouseManager warehouseManager;
	private static OompaLoompaManager oompaloompaManager;
	

	// Used for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//Buffered reader for my whole code
	private static BufferedReader reader; 
	
	//Menu
	public static void main(String[] args) throws Exception{
		
	    //Connects with the database
		dbManager = new SQLiteManager();
		dbManager.connect();
		
		
		//chocolateManager = dbManager.getChocolateManager();
		//clientManager = dbManager.getClientManager();
		//animalManager= dbManager.getAnimalManager();
		
		
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
		System.out.println("WHO ARE YOU? ");
		System.out.println("1.Willy Wonka");
		System.out.println("2.Oompa Loompa CEO");
		System.out.println("3.Exit");
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
		case 0:
			break;
		case 1: 
			willyWonkaMenu();
			break;
		
		case 2: 
			OompaLoompaCeoMenu();
			break;
		case 3: 
			System.exit(0);
			break;
		default: 
			break;
		}
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

		
		/*	private static void createChocolate() throws Exception {
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
			
			
			*/
			
			
			
			
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
			System.out.println("4. Search client by name  ");
			System.out.println("5. Update client          ");
		

			
			int choice = Integer.parseInt(reader.readLine());
			switch(choice){
			case 1: 
				addClient();
				break;
			case 2: 
				
				break;

			case 3:
				selectClient();
				break;
			case 4:

				searchClientByName();
				break;
			case 5: 
				updateClient();
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
				
				int num;
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
		 boolean conn = true;
	        
	        try{
	        System.out.println("Introduce the ID of the warehouse you want to remove from the table");
	        int id = Integer.parseInt(reader.readLine());
	        
	        
	        }catch(Exception e){
	       	 e.printStackTrace();
	       	 conn = false; 
	        }
	        
	   	
	   	
	   	return conn;
	   	
	}
//----------------------------------------------------------------
		// SELECT/SHOW WAREHOUSE
//----------------------------------------------------------------
	private static void ShowWarehouse() throws Exception{
		
	}

//----------------------------------------------------------------
			//UPDATE  WAREHOUSE
//----------------------------------------------------------------
	private static void UpdateWarehouse()throws Exception{
		
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
		System.out.print("5. Date of Birth: ");
		LocalDate dob = LocalDate.parse(reader.readLine(), formatter); 
		OompaLoompa oompaloompa = new OompaLoompa (name,cellphone,email,address, Date.valueOf(dob));
		oompaloompaManager.add(oompaloompa);

}

//----------------------------------------------------------------
//DELETE WORKER
//----------------------------------------------------------------
private static boolean DeleteWorker() throws Exception{
 boolean conn = true;
    
    try{
    System.out.println("Introduce the ID of the worker you want to remove from the table");
    int id = Integer.parseInt(reader.readLine());
    
    
    }catch(Exception e){
   	 e.printStackTrace();
   	 conn = false; 
    }
    
	
	
	return conn;
	
}
//----------------------------------------------------------------
// SELECT/SHOW WORKER
//----------------------------------------------------------------
private static void ShowWorker() throws Exception{

}

//----------------------------------------------------------------
	//UPDATE WORKER
//----------------------------------------------------------------
private static void UpdateWorker()throws Exception{

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
        
        try{
        System.out.println("Introduce the ID of the chocolate you want to remove from the table");
        String id = reader.readLine();
        int AnimalId = Integer.parseInt(id);
        
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

    	
    	//If the user doesn�t type anything, the colour is not changed 
    	System.out.println("Type the new colour or press enter to leave it as is: ");
    	String newColour = reader.readLine();
    	if(newColour.equals("")){
    		newColour = toBeModified.getColour();
    	} 
    
        
    	//If the user doesn�t type anything, the country is not changed 
    	System.out.println("Type the new country or press enter to leave it as is: ");
    	String newCountry = reader.readLine();
    	if(newCountry.equals("")){
    		newCountry = toBeModified.getCountry();
    	}
    	
    	//If the user doesn�t type anything, the specie is not changed 
    	System.out.println("Type the new specie or press enter to leave it as is: ");
    	String newSpecie = reader.readLine();
    	if(newSpecie.equals("")){
    		newSpecie = toBeModified.getSpecie();
    	}
    	
    	
    	//If the user doesn�t type anything, the date of birth is not changed 
    	System.out.println("Type the new date of birth in this format (year-month day)"
    			+ " or press enter to leave it as is: ");

		String newdate= reader.readLine();
		LocalDate dob = LocalDate.parse(newdate, formatter); 
    	if(dob.equals("")){
    		Date.valueOf(dob) = toBeModified.getDob();
    	}
    	
    	Animal updatedAnimal = new Animal( newName, newColour, newCountry, newSpecie, Date.valueOf(dob) );
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
	
	
	
	private static String selectClient() throws Exception{// select client by name
		   
		       String resultado = "hola";
		        return resultado;
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

                                                 //UPDATE CLIENT

//--------------------------------------------------------------------------------------------------------------------------------
	private static boolean updateClient() throws Exception {
    	boolean exito= false;
    	
    	return exito; 
    	
    }
}//main

	