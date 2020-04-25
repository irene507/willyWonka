package ui;

import java.io.BufferedReader;
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
	
	//Used for parsing dates
		private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//Let me read, for my whole code
	private static BufferedReader reader; 
	
	//Menu
	public static void main(String[] args) throws Exception{
	    //Connect with the database
		dbManager = new SQLiteManager();
		dbManager.connect();
		chocolateManager = dbManager.getChocolateManager();
		clientManager = dbManager.getClientManager();
		
		
		//Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
        //Welcome screen
		System.out.print("-------------WELCOME!--------- ");
		//offer the user to create tables 
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
				updateChocolate();
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
			default: 
				break;
				
			
			}
			
		}}
	
	
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
    
  
    private static boolean updateChocolate() throws Exception {
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
    

	private static void OompaLoompaMenu()throws Exception {
		while (true) {
		
		//CHOCOLATE
		System.out.println("What do you wanna do?     ");
		System.out.println("1. Create Chocolate       ");
		System.out.println("4. Search By Name         ");
		System.out.println("5. Search By Type         ");
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
		
		default: 
			break;

		}	}

		
	}//OompaLoompaMenu

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
		
	}
	
	
	
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



	private static void searchChocolateByName() throws Exception{
		      System.out.println("Name");
		      String name = reader.readLine();
		    //why does he ask for the type if he is searching by name ?
		      List<Chocolate> chocolates = chocolateManager.searchByName(name);
		      for (Chocolate chocolate : chocolates) {
				System.out.println(chocolate);
			}
	}
	
	private static void searchChocolateByType() throws Exception{
		     System.out.println("Type");
	         String type = reader.readLine();
		     List<Chocolate> chocolates = chocolateManager.searchByType(type);
		     for (Chocolate chocolate : chocolates) {
		    	 System.out.println(chocolate);
				
			}
	}
	

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

	