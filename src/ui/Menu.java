package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import pojos.*;
import db.interfaces.*;
import db.sqlite.*;

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
			System.out.println("6. Search By Breed        ");
			int choice = Integer.parseInt(reader.readLine());
			switch(choice){
			case 1: 
				createChocolate();
				break;
			case 2: 
				selectChocolate();
				break;
			case 3:
				changeCharacteristics();
				break;
			case 4:
				deleteChocolate();
				break;
			case 5:
				updateChocolate();
				break;
			case 6: 
				searchByName();
				break;
			case 7: 
				searchByBreed();
				break; 
			default: 
				break;
				
			
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
////dudaaaaaaaa about constructor 
				Chocolate chocolate = new Chocolate(id, name, cocoa, type, flavors, units, shape);
			   // to do insert the dog 
				chocolateManager.create(chocolate);
			}
			
			
		}
		
	}
}
