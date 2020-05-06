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
		System.out.print("-------------WELCOME!--------- ");
		
		//we offer the user to create tables 
		System.out.println("Do you want to create the tables? (Y/N)");
		String yn = reader.readLine();
		if(yn.equalsIgnoreCase("y")){
			dbManager.createTables();
		}
		//Ask the user his/her role 
		System.out.println("WHO ARE YOU? ");
		System.out.println("1.Willy Wonka");
		System.out.println("2.Oompa Loompa CEO");
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
		default: 
			break;
		}
		
		}

	
//-----------------------------------------------------------------------------------
	
	//WILLY WONKA MENU
	
//--------------------------------------------------------------------------
	
	private static void willyWonkaMenu() throws Exception{
		while(true) {
		System.out.println("Which are do you wanna manage?   ");
		System.out.println("1. CHOCOLATE   ");
		System.out.println("2. CLIENTS      ");
		System.out.println("3. ANIMALS       ");
				
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
		
		
		}
}
	
	private static void willyWonkaChocolate() throws Exception{
			int choice = Integer.parseInt(reader.readLine());
			
			
			System.out.println("You are going to manage the chocolate. ");
			System.out.println("What do you wanna do?     ");
			System.out.println("1. Create Chocolate       ");
			System.out.println("2. Select Chocolate       ");
			System.out.println("3. Delete Chocolate       ");
			System.out.println("4. Update Chocolate       ");
			System.out.println("5. Search By...        "); 
			System.out.println("6. Show info              ");
			
	    switch(choice){	
		case 1: 
			createChocolate();
			break;
		case 2: 
			selectChocolate();
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
			num = Integer.parseInt(reader.readLine());
			if(num == 1) {
				SearchAnimalByName();
				searchChocolateByName();
				System.out.println("Write the selected chocolate�s id");
				int chocoId = Integer.parseInt(reader.readLine());

			}
			else {
			  searchChocolateByType();
			}
			
			break;
			searchChocolateByName();
			break;
			
		//case 6: 
			//searchChocolateByType();
			//7break; 
			
		case 7: 
			showInformation();
			break; 
			
			}
}//function chocolate
		
		
		
	
	
		private static void willyWonkaClients() throws Exception{
			while(true) {
			
			//CLIENT
			System.out.println("1. Add client             ");
			System.out.println("2. Erase client           ");
			System.out.println("3. Select client          ");
			System.out.println("4. Search client by name  ");
			System.out.println("5. Update client          ");
			System.out.println("6. Add an animal          ");
			System.out.println("7. See an animal info     ");
			System.out.println("8. Update an animal information");
			
			int choice = Integer.parseInt(reader.readLine());
			switch(choice){
			case 1: 
				AddClient();
				break;
			case 2: 
				
				break;

			case 3:
				
				break;
			case 4:
				updateChocolate();
				break;
			case 5: 
				searchChocolateByName();
				break;
			
			case 6:
				 SearchAnimalByName();
					System.out.println("Introduce the id of the selected animal");
					int id=Integer.parseInt(reader.readLine());
				break;
			case 7:
					SearchAnimalBySpecie();
					System.out.println("Introduce the id of the selected animal");
					int id2=Integer.parseInt(reader.readLine());
				break;
			default: 
				break;
				
			
			}
			
		}}
	
		
		
		private static void willyWonkaAnimals() throws Exception{
			
		   while(true){
			 System.out.println("Add animal ");
			 System.out.println("Erase animal ");
			 System.out.println("Update");
			 System.out.println("Search by name ");
		     
			
			
			
			
			
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
					createChocolate();
					break;
				
				case 2: 
					searchChocolateByName();
					System.out.println("Write the selected chocolate�s id");
					chocoId = Integer.parseInt(reader.readLine());
					OLSubMenu();

					break;
				case 3: 
					searchChocolateByType();
					System.out.println("Write the selected chocolate�s id");
					int chocoId = Integer.parseInt(reader.readLine());
					break;
					
				case 4:
					System.out.println("Search animal by....");
					System.out.println("1. Name");
					System.out.println("2. Specie");
					num= Integer.parseInt(reader.readLine());
					if(num== 1) {
						SearchAnimalByName();

					}
					else {
						SearchAnimalBySpecie();
					}
					
					break;
				
				default: 
					break;

				}	}

				
			}//OompaLoompaMenu
			
			
//---------------------------------------------------------------------	
			
					//SUBMENU OL 
				
//-----------------------------------------------------------------------------	


			private static void OLSubMenu() throws Exception{
				while(true) {//CHOCOLATE
					
				
						System.out.println("What do you wanna do Oompa Loompa?     ");
						System.out.println("1. Create Chocolate       ");
						System.out.println("2. Search By Name         ");
						System.out.println("3. Search By Type         ");
						//CLIENT
						
					
						int choice = Integer.parseInt(reader.readLine());
						switch(choice){
						case 1:
					    	willyWonkaMilk();
						break;
					
						case 2: 
							willyWonkaWarehouse();
							break;
						case 3: 
							willyWonkaWorkers();
							break;
									
						
						case 2: 
							searchChocolateByName();
							System.out.println("Write the selected dog�s id");
							int chocoId = Integer.parseInt(reader.readLine());
							OLSubMenu();

							break;
						 
						
						default: 
							break;

						}
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
	
		//SEARCH ANIMAL
	
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
	
	
	
	
	
	
//------------------------------------------------------------------------------------------	
	
//CHOCOLATE PART
	
	
	public void changeCharacteristics(Chocolate chocolate){
		
		
		
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
		   // to do insert the dog 
			chocolateManager.create(chocolate);
		}
		
		
		
		
		
		
//---------------------------------------------------------------------	
		
			//SELECT CHOCOLATE
		
//-----------------------------------------------------------------------------	


//la entiendo como buscar. Decirme si me equivoco porfi 
		
private static String selectChocolate() throws Exception{
	   ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
	        System.out.println("Type");
	        String type = reader.readLine();
	        
	        String resultado = ""; 
	        
	        if ((type == null) || (type.compareTo("") == 0)) {
	            resultado = "Ha habido un error";
	            return resultado;
	        }

	        for (int i = 0; i < chocolates.size(); i++) {

	            if (chocolates.get(i).getName().compareTo(type) == 0) { //como comparamos texto utilizamos la funcion toString
	                resultado = "El resultado de la busqueda es " + chocolates.get(i).toString(); //ahora consigo el elemnto con get(i)
	                break;
	            }
	         ///no tenemos ningun metodo tostring   

	        }
	        return resultado;
	
}

     public List<Chocolate> showChocolates(){
    	 
    	 //no se si es necesario 
    	 
    	 return chocolate;
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
				System.out.println(chocolate);
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
		    	 System.out.println(chocolate);
				
			}
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

	