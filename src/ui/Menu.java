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
		
		
		chocolateManager = dbManager.getChocolateManager();
		clientManager = dbManager.getClientManager();
		animalManager= dbManager.getAnimalManager();
		
		
		
		
		//Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
        //Welcome screen
		System.out.print("-------------WELCOME!--------- ");
		
		//we offer the user to create tables 
		System.out.println("Do you want to create the tables? (Y/N)");
		String yn = reader.readLine();
		if(yn.equalsIgnoreCase("y")){
			dbManager.createTables();// aqui hay q hacer algo para cambiralo pero el q?
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
			OompaLoompaMenu();
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
			System.out.print("What do you wanna do?       ");
			System.out.println("1. Create Chocolate       ");
			System.out.println("2. Select Chocolate       ");
			System.out.println("3. Delete Chocolate       ");
			System.out.println("4. Update Chocolate       ");
			System.out.println("5. Search By Name         ");
			System.out.println("6. Search By Type         ");
			//CLIENT
			System.out.println("7. Add client             ");
			System.out.println("8. Erase client           ");
			System.out.println("9. Select client          ");
			System.out.println("10. Search client by name  ");
			System.out.println("11. Update client  ");
			System.out.println("12. Add an animal");
			System.out.println("13. See an animal info");
			System.out.println("14. Update an animal information");
			
			int choice = Integer.parseInt(reader.readLine());
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
				updateChocolate(chocoId);
				break;
			case 5: 
				searchChocolateByName();
				break;
			case 6: 
				searchChocolateByType();
				break; 
			case 7: 
				addClient();
				break;
			case 8: 
				deleteClient();
				break;	
			case 9: 
				selectClient();
				break;
			case 10: 
				searchClientByName();
				break;	
			case 11: 
				updateClient();
				break;
			case 12:
				 AddAnimal();
				break;
			case 13:
				 SearchAnimalByName();
					System.out.println("introduce the id of the selected animal");
					int id=Integer.parseInt(reader.readLine());
				break;
			case 14:
					SearchAnimalBySpecie();
					System.out.println("introduce the id of the selected animal");
					int id2=Integer.parseInt(reader.readLine());
				break;
			default: 
				break;
				
			
			}
			
		}}
	
		
		
		
		
	    
//---------------------------------------------------------------------	
			
		  		//OMOMPALOOMPA MENU
		  	
//-----------------------------------------------------------------------------	

		    

			private static void OompaLoompaMenu()throws Exception {
				while (true) {
				
				int num;
				//CHOCOLATE
				System.out.println("What do you wanna do?     ");
				System.out.println("1. Create chocolate       ");
				System.out.println("2. Search chocolate by name         ");
				System.out.println("3. Search chocolate by type         ");
				System.out.println("4. See an animal information");
				//CLIENT
				//olceo no tiene acceso a los clientes, solo willy wonka
				
			
				int choice = Integer.parseInt(reader.readLine());
				switch(choice){
				case 1: 
					createChocolate();
					break;
				
				case 2: 
					searchChocolateByName();
					System.out.println("Write the selected dog´s id");
					int chocoId = Integer.parseInt(reader.readLine());
					OLSubMenu();

					break;
				case 3: 
					searchChocolateByType();
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


			private static void OLSubMenu(chocoId) throws Exception{
				while(true) {//CHOCOLATE
					
				}
						System.out.println("What do you wanna do Oompa Loompa?     ");
						System.out.println("1. Create Chocolate       ");
						System.out.println("2. Search By Name         ");
						System.out.println("3. Search By Type         ");
						//CLIENT
						
					
						int choice = Integer.parseInt(reader.readLine());
						switch(choice){
						case 1: 
							createChocolate();
							break;
						
						case 2: 
							searchChocolateByName();
							System.out.println("Write the selected dog´s id");
							int chocoId = Integer.parseInt(reader.readLine());
							OLSubMenu(chocoId);

							break;
						case 3: 
							searchChocolateByType();
							break; 
						
						default: 
							break;

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
	
	
	
//---------------------------------------------------------------------	
	
			//DELETE CHOCOLATE 
		
//-----------------------------------------------------------------------------	

	
	
    private static boolean deleteChocolate() throws Exception{
    	ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
    	 boolean conexito = true;
         boolean encontrado = false;
         int indice = 0;
         //lo inicializamos como un indice no valido
         //buscamos el indice que queremos eliminar
         
         System.out.println("Name");
		 String name = reader.readLine();
         
         for (int i = 0; i < chocolates.size(); i++) {
             if (chocolates.get(i).getName().equals(name)) {
                 indice = i; //guardamos el indice donde se encuentra el chocolate
                 encontrado = true;
                 break;
             }
         }

         if (encontrado) {
             chocolates.remove(indice); //el indice encontrado lo eliminamos

         } else {
             conexito = false;
         }
    	
    	
    	return conexito;
    	
    	
    }
    
 //---------------------------------------------------------------------	
	
  		//UPDATE CHOCOLATE 
  	
 //-----------------------------------------------------------------------------	

  ///boolean creo que ya no va a ser 
    private static boolean updateChocolate(int chocoId) throws Exception {
    	
        //I get the chocolate
    	Chocolate toBeModified = chocolateManager.getChocolate(chocoId);
    	System.out.println("Actual name: " +toBeModified.getName());
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new name or press enter to leave it as is: ");
    	String newName = reader.readLine();
    	if(newName.equals("")){
    		newName = toBeModified.getName();
    	}
   ////lo hace para el breed tambien , yo tambien ? 
    	
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new type or press enter to leave it as is: ");
    	String newType = reader.readLine();
    	if(newType.equals("")){
    		newType = toBeModified.getType();
    	}
        
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new cocoa or press enter to leave it as is: ");
    	String newCocoa = reader.readLine();
    	Float floatNewCocoa;
    	if(newCocoa.equals("")){
    		floatNewCocoa = toBeModified.getCocoa();
    	}else{
    		floatNewCocoa = Float.parseFloat(newCocoa);
    	}
    	
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new flavors or press enter to leave it as is: ");
    	String newFlavors = reader.readLine();
    	if(newFlavors.equals("")){
    		newFlavors = toBeModified.getFlavors();
    	}
    	
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new units or press enter to leave it as is: ");
    	String newUnits = reader.readLine();
    	Float floatNewUnits;
    	if(newUnits.equals("")){
    		floatNewUnits = toBeModified.getUnits();
    	}else{
    		floatNewUnits = Float.parseFloat(newUnits);
    	}
    	
    	//If the user doesn´t type anything, the name is not changed 
    	System.out.println("Type the new shape or press enter to leave it as is: ");
    	String newShape = reader.readLine();
    	if(newShape.equals("")){
    		newShape = toBeModified.getShape();
    	}
    	
    	Chocolate updatedChocolate = new Chocolate(chocoId, newName,newType, floatNewCocoa, newFlavors, floatNewUnits, newShape );
       //At the very end... 
    	chocolateManager.update(updatedChocolate);

    	
    	
    	
  /// esto va aqui? 
    	
    	ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
    	boolean exito = true;
    	int indice = -1; //es decir falso
    	
    	if((Chocolate.class.getName() == null) || (Chocolate.class.getName().compareTo("") == 0)){
    		exito = false;
    	}
    	
    	if(exito){
    		//fallo creo 
            for(int i=0; i<chocolates.size(); i++){
    			if(chocolates.get(i).getName().compareTo(chocolates.getName()) == 0){
    				indice = i; 
    				break; 
    			}
    		}
            
            if(indice>=0){
            	chocolates.add();


            	
            }else{
            	exito = false;
            }
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
		   ArrayList<Client> clients = new ArrayList<Client>();
		        System.out.println("Name");
		        String name = reader.readLine();
		        
		        String resultado = ""; 
		        
		        if ((name == null) || (name.compareTo("") == 0)) {
		            resultado = "Ha habido un error";
		            return resultado;
		        }

		        for (int i = 0; i < clients.size(); i++) {

		            if (clients.get(i).getName().compareTo(name) == 0) { 
		                resultado = "El resultado de la busqueda es " + clients.get(i).toString(); 
		                break;
		            }
		         ///no tenemos ningun metodo tostring   

		        }
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
    	ArrayList<Client> clients = new ArrayList<Client>();
    	boolean exito = true;
    	int indice = -1; //es decir falso
    	
    	if((Client.class.getName() == null) || (Client.class.getName().compareTo("") == 0)){
    		exito = false;
    	}
    	
    	if(exito){
    		//fallo creo 
            for(int i=0; i<clients.size(); i++){
    			if(clients.get(i).getName().compareTo(clients.getName()) == 0){
    				indice = i; 
    				break; 
    			}
    		}
            
            if(indice>=0){
            	clients.add();


            	
            }else{
            	exito = false;
            }
    	}
    	
    	return exito; 
    	
    }

	