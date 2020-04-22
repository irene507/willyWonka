package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import pojos.*;
import db.interfaces.*;
import db.sqlite.*;
import java.util.*;

public class Menu {

	
	//DB Managers
	private static DBManager dbManager;
	private static ChocolateManager chocolateManager;
	
	//Let me read, for my whole code
	private static BufferedReader reader; 
	
	//Menu
	public static void main(String[] args) throws Exception{
	    //Connect with the database
		dbManager = new SQLiteManager();
		dbManager.connect();
		chocolateManager = dbManager.getChocolateManager();
		
		
		//Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
        //Welcome screen
		System.out.print("-------------WELCOME!--------- ");
		//offer the user to create tables 
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
			OompaLoompaMenu();
			break;
		default: 
			break;
		}
		
		}
	
		private static void willyWonkaMenu() throws Exception{
			System.out.print("What do you wanna do?       ");
			System.out.println("1. Create Chocolate       ");
			System.out.println("2. Select Chocolate       ");
			System.out.println("3. Delete Chocolate       ");
			System.out.println("4. Update Chocolate       ");
			System.out.println("5. Search By Name         ");
			System.out.println("6. Search By Type         ");
			System.out.println()
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
				searchByName();
				break;
			case 6: 
				searchByType();
				break; 
			default: 
				break;
				
			
			}
			
		}
	
	
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
                 indice = i; //guardamos el indice donde se encuentra el raton
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
		// TODO Auto-generated method stub
		//CHOCOLATE
		System.out.println("What do you wanna do?     ");
		System.out.println("1. Create Chocolate       ");
		System.out.println("2. Delete Chocolate       ");
		System.out.println("3. Select Chocolate       ");//lo necesitamos?????
		System.out.println("4. Search By Name         ");
		System.out.println("5. Search By Type         ");
		//CLIENT
		System.out.println("5. Add client             ");
		System.out.println("7. Erase client           ");
		System.out.println("8. Select client          ");
		System.out.println("9. Search client by name  ");
		System.out.println("10. Search client by name  ");
	
		int choice = Integer.parseInt(reader.readLine());
		switch(choice){
		case 1: 
			createChocolate();
			break;
		case 2: 
			deleteChocolate();
			break;	
		case 3: 
			selectChocolate();
			break;
		case 4: 
			searchChocolateByName();
			break;
		case 5: 
			searchChocolateByType();
			break; 
		case 6: 
			addClient();
			break;
		case 7: 
			deleteClient();
			break;	
		case 8: 
			selectClient();
			break;
		case 9: 
			searchClientByName();
			break;	
		case 10: 
			updateClient();
			break;	
		default: 
			break;

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
	
	private static void searchClientByName() throws Exception{
	      System.out.println("Name");
	      String name = reader.readLine();
	    //why does he ask for the type if he is searching by name ?
	      List<Client> clients = clientManager.searchByName(name);
	      for (Client client : clients) {
			System.out.println(client);
		}
}
	private static void addClient() throws Exception {
		System.out.println("Name");
		String name = reader.readLine();
		System.out.println("Cellphone");
		Integer cellphone =Integer.parseInt( reader.readLine());
		System.out.println("email");
		String email = reader.readLine();
		System.out.println("adress");
		String adress = reader.readLine();
		System.out.println("dob");
		String dob = reader.readLine();//COMO SE HACE EL DATE??
		
        Chocolate chocolate =new Client(name, cellphone, email, adress, dob);
	   // to do insert the dog 
		chocolateManager.create(chocolate);
	}
		
	
		
	}
	