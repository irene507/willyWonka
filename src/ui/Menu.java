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
		chocolateManager = dbManager.getChocolateManager();
		dbManager.connect();
		
		//Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
        //Welcome screen
		System.out.print("-------------WELCOME!--------- ");
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
	
		private static void willyWonkaMenu() throws Exception{
			System.out.print("What do you wanna do?       ");
			System.out.println("1. Create Chocolate       ");
			System.out.println("2. Select Chocolate       ");
			System.out.println("3. Change Characteristics ");
			System.out.println("4. Delete Chocolate       ");
			System.out.println("5. Update Chocolate       ");
			System.out.println("6. Search By Name         ");
			System.out.println("6. Search By Type         ");
			int choice = Integer.parseInt(reader.readLine());
			switch(choice){
			case 1: 
				createChocolate();
				break;
			case 2: 
				selectChocolate();
				break;
/* la quitamos    case 3:
				changeCharacteristics();
				break;*/
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
	private static void createChocolate() throws Exception {
				System.out.println("ID");
				int id = Integer.parseInt(reader.readLine());
				System.out.println("Name");
				String name = reader.readLine();
				System.out.println("Cocoa");
				///tipos o cantidad? 
				Float cocoa = Float.parseFloat(reader.readLine());
				System.out.println("Type");
				String type = reader.readLine();
				System.out.println("Flavors");
				String flavors = reader.readLine();
				System.out.println("Units");
				Float units = Float.parseFloat(reader.readLine());
				System.out.println("Shape");
				String shape = reader.readLine();
                Chocolate chocolate = new Chocolate(name, cocoa, type, flavors, units, shape);
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
	
    private static boolean deleteChocolate() throws Exception{
    	ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
    	 boolean conexito = true;
         boolean encontrado = false;
         int indice = 0;//lo inicializamos como un indice no valido
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
    
    
    private static boolean updateChocolate() throws Exception{
    	ArrayList<Chocolate> chocolates = new ArrayList<Chocolate>();
    	boolean exito = true;
    	int indice = -1; //es decir falso
    	
    	if((Chocolate.class.getName() == null) || (Chocolate.class.getName().compareTo("") == 0)){
    		exito = false;
    	}
    	
    	if(exito){
    		//fallo creo 
            for(int i=0; i<Chocolate.size(); i++){
    			if(Chocolate.class.get(i).getName().compareTo(Chocolate.class.getName()) == 0){
    				indice = i; 
    				break; 
    			}
    		}
            
            if(indice>=0){
            	Chocolate.class.add(indice,raton);
            	
            }else{
            	exito = false;
            }
    	}
    	
    	return exito; 
    	
    }
    
    
    
    
	
	private static void searchByName() throws Exception{
		      System.out.println("Name");
		      String name = reader.readLine();
		    //why does he ask for the type if he is searching by name ?
		      List<Chocolate> chocolates = chocolateManager.searchByName(name);
		      for (Chocolate chocolate : chocolates) {
				System.out.println(chocolate);
			}
	}
	
	private static void searchByType() throws Exception{
		     System.out.println("Type");
	         String type = reader.readLine();
		     List<Chocolate> chocolates = chocolateManager.searchByType(type);
		     for (Chocolate chocolate : chocolates) {
		    	 System.out.println(chocolate);
				
			}
	}
	
	   
	
	
	
	
	
			
	}

	private static void willyWonkaMenu() {
		// TODO Auto-generated method stub
		
	}
		
	}

