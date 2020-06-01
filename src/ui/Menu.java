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

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Menu {

	// DB Managers
	private static DBManager dbManager;
	private static ChocolateManager chocolateManager;
	private static ClientManager clientManager;
	private static AnimalManager animalManager;
	private static MilkManager milkManager;
	private static WarehouseManager warehouseManager;
	private static OompaLoompaManager oompaloompaManager;
	private static UserManager userManager; // JPA

	// Used for parsing dates
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Buffered reader for my whole code
	private static BufferedReader reader;

	// Menu
	public static void main(String[] args) throws Exception {

		// Connects with the databasey
	
		dbManager = new SQLiteManager();
		dbManager.connect();
		// Need to create the tables with JDBC before using JPA
		dbManager.createTables();
		userManager = new JPAUserManager(); // we dont hace a constructor but we dont need it
		userManager.connect();
		chocolateManager = dbManager.getChocolateManager();
		clientManager = dbManager.getClientManager();
		animalManager = dbManager.getAnimalManager();
		oompaloompaManager = dbManager.getOompaLoompaManager();
		warehouseManager = dbManager.getWarehouseManager();

		// Initialize BufferedReader
		reader = new BufferedReader(new InputStreamReader(System.in));
		// Welcome screen
		System.out.print("-------------WELCOME!------------\n ");

		// we offer the user to create tables (don´t need to as they have already been created)
		/*System.out.println("Do you want to create the tables? (Y/N)");
		String yn = reader.readLine();
		if (yn.equalsIgnoreCase("y")) {
			dbManager.createTables();
		}*/
		while (true) {
			// Ask the user his/her role
			System.out.println("What do you want to do? ");
			System.out.println("1.Login");
			System.out.println("2.Create a User");
			System.out.println("3.Delete a User");
			System.out.println("4.Update a User");
			System.out.println("5.Create a new role");
			System.out.println("6.Delete a Role");
			System.out.println("0.Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 0:
				System.exit(0);
				dbManager.disconnect();
				userManager.disconnect();
				break;
			case 1:
				//Login
				login();
				break;
			case 2:
				//Create new User
				newUser();
				break;
			case 3:
				//Delete User
				deleteUser();
				break;
			case 4: 
				//Update user
				updateUser(); 
				break;
			case 5 : 
				//Create new Role
				newRole();
				break;
			case 6:
				//Delete Role
				deleteRole();
				break;
			default:
				break;
			}
		}
	}

	private static void newRole() throws Exception {
		boolean incorrect=true;
		System.out.println("Please choose between the available roles: ");
		System.out.println("1.Willy Wonka");
		System.out.println("2.OompaLoompa CEO");
		int option = Integer.parseInt(reader.readLine());
		String roleName;
		Role role;
		while(incorrect) {
		if (option==1) {
			roleName = "Willy Wonka";
			role = new Role(roleName);
			userManager.createRole(role);
			incorrect = false;
		}else if(option==2) {
			roleName = "Oompa Loompa";
			role = new Role(roleName);
			userManager.createRole(role);
			incorrect = false;
		}else {
			System.out.println("Wrong option introduced");
			
		}
		}
		
		

	}

	private static void newUser() throws Exception {
		boolean incorrect=true,errfound=true;
		System.out.println("Please type the new user information ");
		List<User> users = userManager.getUsers();
		String username;
		System.out.println("Username: ");
		username = reader.readLine();
		if(!users.isEmpty()) {
			do{
				
				for(User user:users) {
					if(user.getUsername().equalsIgnoreCase(username)) {
						System.out.println("That Username already exists, introduce a new username");
						username = reader.readLine();
						errfound=true;
						
					}else {
						errfound=false;
					}
				}
				if(errfound) {
					incorrect=true;
				}else {
					incorrect=false;
				}
			}while(incorrect);
		
		}
		
		System.out.println("Password: ");
		String password = reader.readLine();
		// Create the password�s hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		// Show all the roles and let the user choose one
		
		List<Role> roles = userManager.getRoles();
		for (Role role : roles) {
			System.out.println(role);
		}

		System.out.println("Type the chosen role id: ");
		int roleId = Integer.parseInt(reader.readLine());
		// Get the chosen role from the database
		Role chosenRole = userManager.getRole(roleId);
		// Create the user and store it
		User user = new User(username, hash, chosenRole);
		userManager.createUser(user);
		

	}

	private static void login() throws Exception {
		System.out.println("Please input your credentials ");
		System.out.println("Username: ");
		String username = reader.readLine();
		System.out.println("Password: ");
		String password = reader.readLine();
		User user = userManager.checkPassword(username, password);
		// check if the user/password combination was right
		if (user == null) {
			System.out.println("Wrong credentials, please try again!");
		}
		// check the role
		else if (user.getRole().getRole().equalsIgnoreCase("Willy Wonka")) {
			System.out.println("Welcome Willy Wonka� " + username + "!");
			willyWonkaMenu();
		} else if (user.getRole().getRole().equalsIgnoreCase("Oompa Loompa")) {
			System.out.println("Welcome Oompa Loompa� " + username + "!");
			OompaLoompaCeoMenu();

		} else {
			System.out.println("Invalid Role ");
		}

	}
	private static void updateUser() throws Exception{
		byte[]hash;
		Role chosenRole;
		Boolean incorrect = true,errfound=true;
		System.out.println("Please input your credentials in order to update your user ");
		System.out.println("Username: ");
		String username = reader.readLine();
		System.out.println("Password: ");
		String password = reader.readLine();
		User user = userManager.checkPassword(username, password);
		// check if the user/password combination was right
		if (user == null) {
			System.out.println("Wrong credentials, please try again!");
		}
		// check the role
		else if (user.getRole().getRole().equalsIgnoreCase("Willy Wonka")||user.getRole().getRole().equalsIgnoreCase("Oompa Loompa")) {
			
			System.out.println("Current username: "+user.getUsername());
			System.out.println("Type new username or enter to keep unchanged");
			username = reader.readLine();
			if(username.equalsIgnoreCase("")) {
				username = user.getUsername();
			}else {
				List<User> users = userManager.getUsers();
				if(!users.isEmpty()) {
					do{
					
						for(User use:users) {
							if(use.getUsername().equalsIgnoreCase(username)) {
								System.out.println("That Username already exists, introduce a new username");
								username = reader.readLine();
								errfound=true;
							
							}else {
								errfound=false;
							}
						}
						if(errfound) {
							incorrect=true;
						}else {
							incorrect=false;
						}
					}while(incorrect);
			
				}
			}
			System.out.println("Current password: "+user.getPassword());
			System.out.println("Type new password or enter to keep unchanged");
			password = reader.readLine();
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(password.equalsIgnoreCase("")) {
				hash = user.getPassword();
			}else {
			md.update(password.getBytes());
			hash = md.digest();
			
			}
			
			System.out.println("Current user: "+user.getRole());
			System.out.println("Type new role id or enter to keep unchanged");
			List<Role> roles = userManager.getRoles();
			for (Role role : roles) {
				System.out.println(role);
			}
			String linein = reader.readLine();
			int roleId;
			if(linein.equalsIgnoreCase("")) {
				roleId = user.getRole().getId();
				 chosenRole = userManager.getRole(roleId);
			}else {
			roleId = Integer.parseInt(linein);
			
			chosenRole = userManager.getRole(roleId);
			}
			
			User newuser = new User(username, hash, chosenRole);
			
			
			userManager.updateUser(newuser, user.getId());
		} else {
			System.out.println("Invalid Role ");
		}
	}
	
	//CREo que est� bien ?
	private static void deleteUser()throws Exception {
		        System.out.println("What�s the Id of the user you want to delete? ");
		        List<User> users = userManager.getUsers();
				for (User user : users) {
					System.out.println("ID: "+user.getId()+"  Name: "+user.getUsername());
				}
		        int Id = Integer.parseInt(reader.readLine());
		        userManager.deleteUser(Id);
		       
				
				// gettransaction, set , y commmit 
	
		
	}
	
	private static void deleteRole()throws Exception{
		System.out.println("What�s the Id of the user you want to delete? ");
        List<Role> roles = userManager.getRoles();
		for (Role role : roles) {
			System.out.println(role);
		}
        int Id = Integer.parseInt(reader.readLine());
        userManager.deleteRole(Id);
	}
	
//-----------------------------------------------------------------------------------

	// WILLY WONKA MENU

//--------------------------------------------------------------------------

	private static void willyWonkaMenu() throws Exception {
		boolean permanecer = true;

		while (permanecer) {
			System.out.println("Which are do you wanna manage?   ");
			System.out.println("1. CHOCOLATE   ");
			System.out.println("2. CLIENTS      ");
			System.out.println("3. ANIMALS       ");
			System.out.println("4. BACK");

			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {

			case 1:
				willyWonkaChocolate();
				break;

			case 2:
				willyWonkaClients();
				break;

			case 3:
				//ywillyWonkaAnimals();
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

	// WILLYWONKA CHOCOLATE

//------------------------------------------------------------------------------------------------

	private static void willyWonkaChocolate() throws Exception {
		
		System.out.println("You are going to manage the chocolate. ");
		System.out.println("What do you wanna do?     ");
		System.out.println("1. Create Chocolate       ");
		System.out.println("2. Show Chocolate       ");
		System.out.println("3. Delete Chocolate       ");
		System.out.println("4. Update Chocolate       ");
		System.out.println("5. Search By...           ");
		System.out.println("6. Show chocolate         ");
		System.out.println("7. Generate XML   ");
		System.out.println("8. Admit chocolate through XML");
		System.out.println("0. Back                   ");

		// revisar estas
		// System.out.println("6. Admit chocolate ");
		System.out.println("7.get chocolate ");
		System.out.println(" Back");
		int choice = Integer.parseInt(reader.readLine());
		int chocoId = 0;

		switch (choice) {
		case 0:
			System.exit(0);
			break;

		case 1:
			// createChocolate();
			createChocolate();
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
			System.out.println("Search chocolate by....");
			System.out.println("1. Name");
			System.out.println("2. Type");
			int num = Integer.parseInt(reader.readLine());
			if (num == 1) {
				searchChocolateByName();

			} else {
				searchChocolateByType();
			}

			break;

		case 6:
			showInformation();
			break;
		case 7:
			System.out.println("For ths first you have to search a specific chocolate");
			System.out.println("Search chocolate by....");
			System.out.println("1. Name");
			System.out.println("2. Type");
			num = Integer.parseInt(reader.readLine());
			if (num == 1) {
				searchChocolateByName();

			} else {
				searchChocolateByType();
			}
			System.out.println("Type the selected chocolate's id");
			int AnimalId = Integer.parseInt(reader.readLine());
			generateChocolateXML(chocoId);
			break;
		case 8:
			admitChocolateXML();
			break;

		}
	}// function chocolate

//--------------------------------------------------------------------------------------------------------

	// WILLYWONKA CLIENTS

//------------------------------------------------------------------------------------------------		

	private static void willyWonkaClients() throws Exception {
		while (true) {

			// CLIENT
			System.out.println("1. Add client             ");
			System.out.println("2. Erase client           ");
			System.out.println("3. Select client          ");
			System.out.println("4. Search client by ...  ");
			System.out.println("5. Show all clients          ");
			System.out.println("6. Update client          ");
			System.out.println("7. generate XML         ");
			System.out.println("8. Create client through XML");

			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
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
				if (num == 1) {
					searchClientByName();

				} else {
					searchClientByEmail();
				}

				break;
			case 5:
				showClient();
				break;	

			case 6:
				updateClient();
				break;
			case 7:
				generateClientXML();
				break;
			case 8:
				CreateAClientXML();
				break;

			default:
				break;

			}

		}
	}



//--------------------------------------------------------------------------------------------------------

	// WILLYWONKA ANIMALS

//------------------------------------------------------------------------------------------------

	/*private static void willyWonkaAnimals() throws Exception {

		while (true) {
<<<<<<< HEAD
=======
			
>>>>>>> branch 'master' of https://github.com/irene507/willyWonka.git
			
			// ANIMAL
			System.out.println("1. Add animal ");
			System.out.println("2. Erase animal ");
			System.out.println("3. Update");
			System.out.println("4. Search by name ");
			System.out.println("5. Search by species");
			System.out.println("6. Select an animal");
			System.out.println("7. Show an animal");
			System.out.println("8. Generate XML");
		    System.out.println("9. Create animal through XML");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
  
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
				
			case 6:
				System.out.println("For this first you have to search a specific animal");
				System.out.println("Search animal by....");
				System.out.println("1. Name");
				System.out.println("2. Specie");
				int k = Integer.parseInt(reader.readLine());
				if (k == 1) {
					SearchAnimalByName();

				} else {
					SearchAnimalBySpecie();
				}
				SelectAnimal();
				break;
				
			case 7:
				ShowAnimal();
				break;
				
			case 8:
				System.out.println("For this first you have to search a specific animal");
				System.out.println("Search animal by....");
				System.out.println("1. Name");
				System.out.println("2. Specie");
				int j = Integer.parseInt(reader.readLine());
				if (j == 1) {
					SearchAnimalByName();

				} else {
					SearchAnimalBySpecie();
				}

				System.out.println("Type the selected animal's id");
				int AnimalId = Integer.parseInt(reader.readLine());
				GenerateXML(AnimalId);
				break;
				
			case 9:
				CreateAnimalXML();
				break;
				
			default:
				break;

			}

		}
	}*/

//---------------------------------------------------------------------	

	// OMOMPALOOMPA MENU

//-----------------------------------------------------------------------------	

	private static void OompaLoompaCeoMenu() throws Exception {

		while (true) {

			System.out.println("Which part do you wanna modify? ");
			System.out.println("1. MILK             ");
			System.out.println("2. WAREHOUSE        ");
			System.out.println("3. WORKERS          ");

			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
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

			}
		}

	}// OompaLoompaMenu

//---------------------------------------------------------------------	

	// OOMPALOOMPA MILK

//-----------------------------------------------------------------------------	

	public static void OompaLoompaMilk() throws Exception {
		System.out.println("1. Add a new milk ");
		System.out.println("2. Delete milk ");
		System.out.println("3. Update milk ");
		System.out.println("4. Show milk ");
		System.out.println("5. Search by Name ");
		System.out.println("6. Search by type");
		System.out.println("7. Give milk to chocolate");

		int choice = Integer.parseInt(reader.readLine());
		switch (choice) {
		case 1:
			AddMilk();
			break;
		case 2:
			DeleteMilk();
			break;
		case 3:
			UpdateMilk();
			break;
		case 4:
			ShowMilk();
			break;
		case 5:
			SearchMilkByName();
			break;
		case 6:
			SearchMilkByType();
			break;
		case 7:
			System.out.println("For this first you have to search a specific chocolate");
			System.out.println("Search a chocolate by....");
			System.out.println("1. Name");
			System.out.println("2. Type");
			int num = Integer.parseInt(reader.readLine());
			if (num == 1) {
				searchChocolateByName();

			} else {
				searchChocolateByType();
			}

			System.out.println("Type the selected chocolate's id");
			int chocoId = Integer.parseInt(reader.readLine());
			giveMilk(chocoId);
			break;
		default:
			break;

		}
	}

//---------------------------------------------------------------------	

	// OOMPALOOMPA WAREHOUSE

//-----------------------------------------------------------------------------	

	public static void OompaLoompaWarehouse() throws Exception {
		System.out.println("1. Add Warehouse ");
		System.out.println("2. Delete Warehouse ");
		System.out.println("3. Update Warehouse ");
		System.out.println("4. Select Warehouse ");
		System.out.println("5. Search by Name ");
		System.out.println("6. Search by Corridor");
		System.out.println("7. Generate XML");
		System.out.println("8. Add Warehouse through XML");
		System.out.println("9. Back");
		

		int choice = Integer.parseInt(reader.readLine());
		switch (choice) {
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
		case 7:
			GenerateWarehouseXML();
			break;
		case 8:
			AddWarehouseXML();
			break;
		case 9:
			return;
		default:
			break;

		}
	}

//---------------------------------------------------------------------	

	// OOMPALOOMPA WORKERS

//-----------------------------------------------------------------------------	

	public static void OompaLoompaWorkers() throws Exception {
		System.out.println("1. Add Worker ");
		System.out.println("2. Delete Worker ");
		System.out.println("3. Update Worker ");
		System.out.println("4. Select Worker ");
		System.out.println("5. Search by Name ");
		System.out.println("6. Search by Date of Birth ");
		System.out.println("7. Generate XML");
		System.out.println("8. Add Worker through XML");
		System.out.println("9. Back");
		
		int choice = Integer.parseInt(reader.readLine());
		switch (choice) {
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
		case 7:
			GenerateWorkerXML();
			break;
		case 8:
			AddWorkerXML();
			break;
		case 9:
			return;
		default:
			break;

		}

	}
	
	
	
	
	
	

	
	
	

	
	
// ------------------------------------------------------------------------------------------

	// CHOCOLATE PART

// ------------------------------------------------------------------------------------

	//ADMIT CHOCOLATE XML
	
//--------------------------------------------------------------------------------

	// ??we are going to get the marshall
	//input.dog.xml como lo saca??????
	//not valid cause its missing an attribute 
	//well-formed por qu�? 
	private static void admitChocolateXML() throws Exception {
       boolean incorrectChocolate = true;
		// Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Chocolate.class);
		// Get the unmarshaller
		Unmarshaller unmarshall = context.createUnmarshaller();
		while(incorrectChocolate){
			
		// Now, we are going to unmarshall the chocolate(object) by reading from a file
		System.out.println("Type the filename for the XML document(expected in the XMLS folder)");
		String fileName = reader.readLine();
		File file = new File("./xmls/" + fileName);
		
		 try {
	        	// Create a DocumentBuilderFactory
	            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
	            // Set it up so it validates XML documents
	            dBF.setValidating(true);
	            // Create a DocumentBuilder and an ErrorHandler (to check validity)
	            DocumentBuilder builder = dBF.newDocumentBuilder();
	            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
	            builder.setErrorHandler(customErrorHandler);
	            // Parse the XML file and print out the result
	            Document doc = builder.parse(file);
	            incorrectChocolate = false;
	           
	        } catch (ParserConfigurationException ex) {
	            System.out.println(file + " error while parsing!");
	           
	        } catch (SAXException ex) {
	            System.out.println(file + " was not well-formed!");
	            
	        } catch (IOException ex) {
	            System.out.println(file + " was not accesible!");
	            
	        }
		// Create the object by reading from a file
		Chocolate chocolate = (Chocolate) unmarshall.unmarshal(file);
		// Printout
		System.out.println("Added to the database: " + chocolate);
		// Get the chocolateID
		int chocoId = dbManager.getLastId();
		// For each milk of the chocolates
		List<Milk> milks= chocolate.getMilks();
		for(Milk milk: milks) {
			milkManager.give(chocoId, milk.getId());
		}
		
		
		
		}
		

	}
		
		
		
		
//----------------------------------------------------------------------------------------
		
		//GENERATE CHOCOLATE XML 
		
//---------------------------------------------------------------------------------------

	private static void generateChocolateXML(int chocoId) throws Exception {
		//Create the object
		Chocolate chocolate = chocolateManager.getChocolate(chocoId);
		// Throw into an XML, so we start...
		// Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Chocolate.class);
		// Get the marshaller
		Marshaller marshall = context.createMarshaller();
		// Formatting
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Write the marshall to a file, but first we need to create the file
		File file = new File("./xmls/Output-Chocolate.xml");
		marshall.marshal(chocolate, file);
		// Printout
		marshall.marshal(chocolate, System.out);
		//aqui coge el ultimo id y lo junta con medicines.... nosotras? Que hacemos? Por que??
		
		
		
	

	}
	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------
	
	// SHOW INFO CHOCOLATE

// ---------------------------------------------------------------------------------

	private static void showInformation() throws Exception {
		List<Chocolate> chocolates = chocolateManager.showChocolates();
		System.out.println("You will see all chocolates on the table ");
		for (Chocolate chocolate : chocolates) {
			System.out.println(chocolate.toString());
		}

	}

// ---------------------------------------------------------------------

	// DELETE CHOCOLATE

// -----------------------------------------------------------------------------

	private static boolean deleteChocolate() throws Exception {

		boolean conexito = true;
		int chocoId = 0;

		try {
			System.out.println("Introduce the ID of the chocolate you want to remove from the table");
			String id = reader.readLine();
			chocoId = Integer.parseInt(id);
			chocolateManager.delete(chocoId);

		} catch (Exception e) {
			e.printStackTrace();
			conexito = false;
		}

		return conexito;

	}

// ---------------------------------------------------------------------

	// UPDATE CHOCOLATE

// -----------------------------------------------------------------------------

	private static boolean updateChocolate() throws Exception {
		boolean exito = true;
		int chocoId = 0;
		try {
			System.out.println("Introduce the id of the chocolate ");
			String id = reader.readLine();
			chocoId = Integer.parseInt(id);
			// I get the chocolate
			Chocolate toBeModified = chocolateManager.getChocolate(chocoId);
			System.out.println("Actual name: " + toBeModified.getName());
			// If the user does not type anything, the name is not changed
			System.out.println("Type the new name or press enter to leave it as is: ");
			String newName = reader.readLine();
			if (newName.equals("")) {
				newName = toBeModified.getName();
			}

			// If the user does not type anything, the name is not changed
			System.out.println("Type the new type or press enter to leave it as is: ");
			String newType = reader.readLine();
			if (newType.equals("")) {
				newType = toBeModified.getType();
			}

			// If the user does not type anything, the name is not changed
			System.out.println("Type the new cocoa or press enter to leave it as is: ");
			String newCocoa = reader.readLine();
			Float floatNewCocoa;
			if (newCocoa.equals("")) {
				floatNewCocoa = toBeModified.getCocoa();
			} else {
				floatNewCocoa = Float.parseFloat(newCocoa);
			}

			// If the user does not type anything, the name is not changed
			System.out.println("Type the new flavors or press enter to leave it as is: ");
			String newFlavors = reader.readLine();
			if (newFlavors.equals("")) {
				newFlavors = toBeModified.getFlavors();
			}

			// If the user does not type anything, the name is not changed
			System.out.println("Type the new units or press enter to leave it as is: ");
			String newUnits = reader.readLine();
			Float floatNewUnits;
			if (newUnits.equals("")) {
				floatNewUnits = toBeModified.getUnits();
			} else {
				floatNewUnits = Float.parseFloat(newUnits);
			}

			// If the user does not type anything, the name is not changed
			System.out.println("Type the new shape or press enter to leave it as is: ");
			String newShape = reader.readLine();
			if (newShape.equals("")) {
				newShape = toBeModified.getShape();
			}

			Chocolate updatedChocolate = new Chocolate(newName, newType, floatNewCocoa, newFlavors, floatNewUnits,
					newShape);
			// At the very end...
			chocolateManager.update(updatedChocolate);

		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;

	}

// ---------------------------------------------------------------------

	// CREATE CHOCOLATE

// -----------------------------------------------------------------------------

	private static void createChocolate() throws Exception {
		// id lo crea el solo por ser autoincrement

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
		Chocolate chocolate = new Chocolate(name, type, cocoa, flavors, units, shape);
		// to do insert the chocolate
		chocolateManager.create(chocolate);
	}

// ---------------------------------------------------------------------

	// SELECT CHOCOLATE

// -----------------------------------------------------------------------------

	private static void showChocolate() {

		try {
			System.out.println("Introduce the chocolate�s ID");
			int chocoId = Integer.parseInt(reader.readLine());

			List<Chocolate> chocolates = chocolateManager.searchChocolate(chocoId);
			for (Chocolate chocolate : chocolates) {
				System.out.println(chocolate.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

// ---------------------------------------------------------------------

	// SEARCH CHOCOLATE BY NAME

// -----------------------------------------------------------------------------

	private static void searchChocolateByName() throws Exception {
		System.out.println("Name");
		String name = reader.readLine();
		// why does he ask for the type if he is searching by name ?
		List<Chocolate> chocolates = chocolateManager.searchByName(name);
		for (Chocolate chocolate : chocolates) {
			System.out.println(chocolate.toString());
		}
	}

// ---------------------------------------------------------------------

	// SEARCH CHOCOLATE BY TYPE

// -----------------------------------------------------------------------------

	private static void searchChocolateByType() throws Exception {
		System.out.println("Type");
		String type = reader.readLine();
		List<Chocolate> chocolates = chocolateManager.searchByType(type);
		for (Chocolate chocolate : chocolates) {
			System.out.println(chocolate.toString());

		}
	}

	
	
	
	
	
	
//----------------------------------------------------------------------------------------------

	// WAREHOUSE PART

//----------------------------------------------------------------
	// ADD WAREHOUSE
//----------------------------------------------------------------
	private static void AddWarehouse() throws Exception {
		System.out.println("Please introduce the new Warehouse");
		System.out.println("1. Name of the warehouse: ");
		String name = reader.readLine();
		System.out.println("2. Corridor number: ");
		int corridor = Integer.parseInt(reader.readLine());
		System.out.println("3. Shelve number: ");
		int shelve = Integer.parseInt(reader.readLine());
		Warehouse warehouse = new Warehouse(name, corridor, shelve);
		warehouseManager.add(warehouse);

	}

//----------------------------------------------------------------
	// DELETE WAREHOUSE
//----------------------------------------------------------------
	private static void DeleteWarehouse() throws Exception {
		System.out.println("Introduce id of warehouse: ");
		int Wid = Integer.parseInt(reader.readLine());
		warehouseManager.delete(Wid);
	}

//----------------------------------------------------------------
	// SELECT/SHOW WAREHOUSE
//----------------------------------------------------------------
	private static void ShowWarehouse() throws Exception {
		try {
			System.out.println("Introduce name of warehouse: ");
			String Wname = reader.readLine();
			Warehouse warehouse = warehouseManager.select(Wname);
			System.out.println(warehouse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	

//----------------------------------------------------------------
	// UPDATE WAREHOUSE
//----------------------------------------------------------------
	private static void UpdateWarehouse() throws Exception {
		System.out.println("Introduce the name of the warehouse you want to update: ");
		String Wname = reader.readLine();
		Warehouse oldWarehouse = warehouseManager.select(Wname);
		System.out.println("Actual name: " + oldWarehouse.getName());
		System.out.println("Type new name or enter to continue: ");
		String name = reader.readLine();
		if (name.equals("")) {
			name = oldWarehouse.getName();
		}
		System.out.println("Actual corridor: " + oldWarehouse.getCorridor());
		System.out.println("Type new corridor or enter to continue: ");
		String scorridor = reader.readLine();
		int corridor;
		if (scorridor.equals("")) {
			corridor = oldWarehouse.getCorridor();
		} else {
			corridor = Integer.parseInt(scorridor);
		}
		System.out.println("Actual shelve: " + oldWarehouse.getShelve());
		System.out.println("Type new corridor or enter to continue: ");
		String sshelve = reader.readLine();
		int shelve;
		if (sshelve.equals("")) {
			shelve = oldWarehouse.getShelve();
		} else {
			shelve = Integer.parseInt(sshelve);
		}
		int WHid = oldWarehouse.getId();
		Warehouse warehouse = new Warehouse(WHid, name, corridor, shelve);
		warehouseManager.update(warehouse);

	}

//----------------------------------------------------------------
	// SEARCH WAREHOUSE BY NAME
//----------------------------------------------------------------
	private static void SearchWarehouseByName() throws Exception {
		System.out.println("Please, introduce the name of the warehouse you want to look for");
		String name = reader.readLine();
		List<Warehouse> warehouses = warehouseManager.searchByName(name);
		for (Warehouse warehouse : warehouses) {
			System.out.println(warehouse);
		}
	}
	
//----------------------------------------------------------------
	// SEARCH WAREHOUSE BY CORRIDOR
//----------------------------------------------------------------
	private static void SearchWarehouseByCorridor() throws Exception {
		System.out.println("Please, introduce the corridor of the warehouse you want to look for");
		int corridor = Integer.parseInt(reader.readLine());
		List<Warehouse> warehouses = warehouseManager.searchByCorridor(corridor);
		for (Warehouse warehouse : warehouses) {
			System.out.println(warehouse);
		}
	}
	
//----------------------------------------------------------------
	// GENERATE WAREHOUSE XML
//----------------------------------------------------------------
	
	private static void GenerateWarehouseXML() throws Exception{
		System.out.println("Introduce name of warehouse: ");
		String Wname = reader.readLine();
		Warehouse warehouse = warehouseManager.select(Wname);
		JAXBContext context = JAXBContext.newInstance(Warehouse.class);
		Marshaller marshal = context.createMarshaller();
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File file= new File("./xmls/Output-Warehouse.xml");
		marshal.marshal(warehouse, file);
		marshal.marshal(warehouse,System.out);
	}
	
//----------------------------------------------------------------
	// ADD WAREHOUSE THROUGH XML
//----------------------------------------------------------------
	private static void AddWarehouseXML() throws Exception{
		boolean incorrectWarehouse = true;
		
		JAXBContext context = JAXBContext.newInstance(Warehouse.class);
		Unmarshaller unmarshal = context.createUnmarshaller();
		while(incorrectWarehouse) {
		System.out.println("Type File for the XML folder (expected in the XMLS folder): ");
		String filename = reader.readLine();
		File file= new File("./xmls/"+filename+".xml");
		try {
        	// Create a DocumentBuilderFactory
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            // Set it up so it validates XML documents
            dBF.setValidating(true);
            // Create a DocumentBuilder and an ErrorHandler (to check validity)
            DocumentBuilder builder = dBF.newDocumentBuilder();
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            builder.setErrorHandler(customErrorHandler);
            // Parse the XML file and print out the result
            Document doc = builder.parse(file);
            incorrectWarehouse = false;
           
        } catch (ParserConfigurationException ex) {
            System.out.println(file + " error while parsing!");
            return;
        } catch (SAXException ex) {
            System.out.println(file + " was not well-formed!");
            return;
        } catch (IOException ex) {
            System.out.println(file + " was not accesible!");
            return;
        }
		Warehouse warehouse = (Warehouse)unmarshal.unmarshal(file);
		System.out.println("Added to the database: "+warehouse);
		warehouseManager.add(warehouse);
		
	}
	
	}
	
	

//----------------------------------------------------------------------------------------------

	// WORKERS PART

//----------------------------------------------------------------
	// ADD WORKER
//----------------------------------------------------------------
	private static void AddWorker() throws Exception {
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
		OompaLoompa oompaloompa = new OompaLoompa(name, cellphone, email, address, Date.valueOf(dob));
		oompaloompaManager.add(oompaloompa);

	}

//----------------------------------------------------------------
//DELETE WORKER
//----------------------------------------------------------------
	private static void DeleteWorker() throws Exception {
		System.out.println("Introduce id of worker: ");
		int Wid = Integer.parseInt(reader.readLine());
		oompaloompaManager.delete(Wid);
	}

//----------------------------------------------------------------
// SELECT/SHOW WORKER
//----------------------------------------------------------------
	private static void ShowWorker() throws Exception {

		try {
			
			System.out.println("Introduce name of worker: ");
			String Wname = reader.readLine();
			OompaLoompa worker = oompaloompaManager.select(Wname);
			System.out.println(worker.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//----------------------------------------------------------------
	// UPDATE WORKER
//----------------------------------------------------------------
	private static void UpdateWorker() throws Exception {

		System.out.print("Introduce the name of the worker you want to update: ");
		String Wname = reader.readLine();
		OompaLoompa oldWorker = oompaloompaManager.select(Wname);
		System.out.print("Actual name: " + oldWorker.getName());
		System.out.print("Type new name or enter to continue: ");
		String name = reader.readLine();
		if (name.equals("")) {
			name = oldWorker.getName();
		}
		System.out.print("Actual cellphone: " + oldWorker.getCellphone());
		System.out.print("Type new cellphone or enter to continue: ");
		String scellphone = reader.readLine();
		int cellphone;
		if (scellphone.equals("")) {
			cellphone = oldWorker.getCellphone();
		} else {
			cellphone = Integer.parseInt(scellphone);
		}
		System.out.print("Actual email: " + oldWorker.getEmail());
		System.out.print("Type new email or enter to continue: ");
		String email = reader.readLine();
		if (email.equals("")) {
			email = oldWorker.getEmail();
		}
		System.out.print("Actual address: " + oldWorker.getAddress());
		System.out.print("Type new address or enter to continue: ");
		String address = reader.readLine();
		if (address.equals("")) {
			address = oldWorker.getAddress();
		}
		System.out.print("Actual date of birth: " + oldWorker.getDob());
		System.out.print("Type new date of birth (yyyy-mm-dd) or enter to continue: ");
		String sdob = reader.readLine();
		Date dob;
		int Wid = oldWorker.getId();
		if (sdob.equals("")) {
			dob = oldWorker.getDob();
		} else {
			dob = Date.valueOf(LocalDate.parse(sdob, formatter));
		}

		OompaLoompa oompaloompa = new OompaLoompa(Wid, name, cellphone, email, address, dob);
		oompaloompaManager.update(oompaloompa);

	}

//----------------------------------------------------------------
//SEARCH WORKER BY NAME
//----------------------------------------------------------------
	private static void SearchWorkerByName() throws Exception {
		System.out.print("Please, introduce the name of the worker you want to look for");
		String name = reader.readLine();
		List<OompaLoompa> workers = oompaloompaManager.searchByName(name);
		for (OompaLoompa oompaloompa : workers) {
			System.out.println(oompaloompa);
		}
	}

//----------------------------------------------------------------
//SEARCH WORKER BY DOB
//----------------------------------------------------------------
	private static void SearchWorkerByDOB() throws Exception {
		System.out.print("Please, introduce the Date of Birth of the worker you want to look for");
		LocalDate dob = LocalDate.parse(reader.readLine(), formatter);
		List<OompaLoompa> workers = oompaloompaManager.searchBydob(Date.valueOf(dob));
		for (OompaLoompa oompaloompa : workers) {
			System.out.println(oompaloompa);
		}
	}

//----------------------------------------------------------------
	// GENERATE WORKER XML
//----------------------------------------------------------------
	private static void GenerateWorkerXML() throws Exception{
		System.out.println("Introduce name of Oompa Loompa: ");
		String Wname = reader.readLine();
		OompaLoompa ol = oompaloompaManager.select(Wname);
		JAXBContext context = JAXBContext.newInstance(OompaLoompa.class);
		Marshaller marshal = context.createMarshaller();
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File file= new File("./xmls/Output-OompaLoompa.xml");
		marshal.marshal(ol, file);
		marshal.marshal(ol,System.out);
	}
	
//----------------------------------------------------------------
	// ADD WORKER THROUGH XML
//----------------------------------------------------------------
	
	private static void AddWorkerXML() throws Exception {
		boolean incorrectWorker = true;
		JAXBContext context = JAXBContext.newInstance(OompaLoompa.class);
		Unmarshaller unmarshal = context.createUnmarshaller();
		while(incorrectWorker) {
		System.out.println("Type File for the XML folder (expected in the XMLS folder): ");
		String filename = reader.readLine();
		File file= new File("./xmls/"+filename+".xml");
		try {
        	// Create a DocumentBuilderFactory
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            // Set it up so it validates XML documents
            dBF.setValidating(true);
            // Create a DocumentBuilder and an ErrorHandler (to check validity)
            DocumentBuilder builder = dBF.newDocumentBuilder();
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            builder.setErrorHandler(customErrorHandler);
            // Parse the XML file and print out the result
            Document doc = builder.parse(file);
            incorrectWorker = false;
           
        } catch (ParserConfigurationException ex) {
            System.out.println(file + " error while parsing!");
           
        } catch (SAXException ex) {
            System.out.println(file + " was not well-formed!");
            
        } catch (IOException ex) {
            System.out.println(file + " was not accesible!");
            
        }
		OompaLoompa worker = (OompaLoompa)unmarshal.unmarshal(file);
		System.out.println("Added to the database: "+ worker);
		oompaloompaManager.add(worker);
		
		
	}
	}
	
	
	
//--------------------------------------------------------------------------------
	
	//MILK PART
	
//----------------------------------------------------------------------------------
	
	
	//ADD MILK

//------------------------------------------------------------------------------
	
	
	
	private static void AddMilk() throws Exception {
		System.out.print("Please introduce the new milk");
		System.out.print("1. Name of the milk: ");
		String name = reader.readLine();
		System.out.print("2. type of the milk (free, low fat, blue, pink, chocolate, almond, coconut.....): ");
		String type = reader.readLine();
		Milk milk = new Milk(name, type);
		milkManager.add(milk);

	}
	
	
//----------------------------------------------------------------------------------
	
	//DELETE MILK
	
//------------------------------------------------------------------------------------
	
	
	private static boolean DeleteMilk() throws Exception {
		boolean conexito = true;
		int milkId = 0;

		try {
			System.out.println("Introduce the ID of the milk you want to remove from the table");
			String id = reader.readLine();
			milkId = Integer.parseInt(id);
			milkManager.delete(milkId);

		} catch (Exception e) {
			e.printStackTrace();
			conexito = false;
		}

		return conexito;


	}
	
	
//-------------------------------------------------------------------------------------------
	
	//UPDATE MILK

	
//------------------------------------------------------------------------------------------
	
	private static boolean UpdateMilk() throws Exception {
		boolean exito = true;
		int MilkId = 0;
		try {
			System.out.println("Introduce the id of the milk ");
			String id = reader.readLine();
			MilkId = Integer.parseInt(id);
			// I get the milk
			Milk toBeModified = milkManager.getMilk(MilkId);
			System.out.println("Actual name: " + toBeModified.getName());
			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new name or press enter to leave it as is: ");
			String newName = reader.readLine();
			if (newName.equals("")) {
				newName = toBeModified.getName();
			}
			// If the user doesn�t type anything, the country is not changed
			System.out.println("Type the new type or press enter to leave it as is: ");
			String newType = reader.readLine();
			if (newType.equals("")) {
				newType = toBeModified.getType();
			}

			
			Milk updatedMilk = new Milk(newName, newType);
			// At the very end...
			milkManager.update(updatedMilk);

		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;


	}
	
	
//----------------------------------------------------------------------------------
	
	//SHOW MILK
	
//-------------------------------------------------------------------------------
	
	private static void ShowMilk() throws Exception {
		
			List<Milk> milks = milkManager.showMilk();
			System.out.println("You'll see all the milks of the table ");
			for (Milk milk : milks) {
				System.out.println(milk.toString());
			}

	}
	
//----------------------------------------------------------------------------
	
	//SEARCH BY NAME
	
//-------------------------------------------------------------------------
	
	private static void SearchMilkByName() throws Exception {
		System.out.print("Please, introduce the name of the milk you want to look for");
		String name = reader.readLine();
		List<Milk> milks = milkManager.searchByName(name);
		for (Milk milk : milks) {
			System.out.println(milk);
		}
	}

//------------------------------------------------------------------------------
	
	//SEARCH BY TYPE
	
//-------------------------------------------------------------------------------
	 
	private static void SearchMilkByType() throws Exception {
		System.out.print("Please, introduce the type of the milk you want to look for");
		String type = reader.readLine();
		List<Milk> milks = milkManager.searchByType(type);
		for (Milk milk : milks) {
			System.out.println(milk);
		}
	}
	
//------------------------------------------------------------------------------
	
	//GIVE MILK TO CHOCOLATE
	
//----------------------------------------------------------------------------------
	
	private static void giveMilk(int chocoId) throws Exception {
		// Show all the available milks
		List<Milk> milkList = milkManager.showMilk();
		for (Milk milk : milkList) {
			System.out.println(milk);
		}
		// Ask the oompaloompa the milk ID
		System.out.println("Please, type the milk ID:");
		int milkId = Integer.parseInt(reader.readLine());
		// Give the medicine to the dog
		milkManager.give(chocoId, milkId);

	}
	
	
	
//----------------------------------------------------------------------------------------------

	// ANIMALS PART

//----------------------------------------------------------------

	// ADD ANIMAL

//--------------------------------------------------

	private static void AddAnimal() throws Exception {

		System.out.print("Please, introduce the new animal");
		System.out.print("1. Name of the animal: ");
		String name;
		name = reader.readLine();
		System.out.print("2. Country of the animal:");
		String country;
		country = reader.readLine();
		System.out.print("3. Colour/s of the animal: ");
		String colour;
		colour = reader.readLine();
		System.out.print("4. Specie of the animal: ");
		String specie;
		specie = reader.readLine();
		System.out.print("5. Date of birth of the animal in this format (year-month-day)      ");
		String date = reader.readLine();
		LocalDate dob = LocalDate.parse(date, formatter);
		Animal animal = new Animal(name, country, colour, specie, Date.valueOf(dob));
		animalManager.add(animal);
		// TODO insert the poor animal

	}

//----------------------------------------------------

	// SEARCH ANIMAL BY NAME

//--------------------------------------------------

	private static void SearchAnimalByName() throws Exception {
		System.out.print("Please, introduce the name of the animal you want to look for");
		String name = reader.readLine();
		List<Animal> animals = animalManager.searchByNameAnimal(name);
		for (Animal animal : animals) {
			System.out.println(animal);
		}
	}

//--------------------------------------------------------------

	// SEARCH ANIMAL BY SPECIE

//--------------------------------------------------------------

	private static void SearchAnimalBySpecie() throws Exception {
		System.out.print("Please, introduce the specie of the animal you want to look for");
		String specie = reader.readLine();
		List<Animal> animals = animalManager.searchBySpecieAnimal(specie);
		for (Animal animal : animals) {
			System.out.println(animal);
		}
	}

//----------------------------------------------------------------------------------

	// DELETE ANIMAL

//------------------------------------------------------------------------------------------	

	private static boolean DeleteAnimal() throws Exception {

		boolean conexito = true;
		int AnimalId;

		try {
			System.out.println("Introduce the ID of the chocolate you want to remove from the table");
			String id = reader.readLine();
			AnimalId = Integer.parseInt(id);
			animalManager.delete(AnimalId);

		} catch (Exception e) {
			e.printStackTrace();
			conexito = false;
		}

		return conexito;

	}

//----------------------------------------------------------------------------------------------------

	// UPDATE ANIMAL

//-----------------------------------------------------------------------------------------------

	private static boolean UpdateAnimal() throws Exception {
		boolean exito = true;
		int AnimalId = 0;
		try {
			System.out.println("Introduce the id of the animal ");
			String id = reader.readLine();
			AnimalId = Integer.parseInt(id);
			// I get the animal
			Animal toBeModified = animalManager.getAnimal(AnimalId);
			System.out.println("Actual name: " + toBeModified.getName());
			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new name or press enter to leave it as is: ");
			String newName = reader.readLine();
			if (newName.equals("")) {
				newName = toBeModified.getName();
			}

			// If the user doesn�t type anything, the country is not changed
			System.out.println("Type the new country or press enter to leave it as is: ");
			String newCountry = reader.readLine();
			if (newCountry.equals("")) {
				newCountry = toBeModified.getCountry();
			}

			// If the user doesn�t type anything, the colour is not changed
			System.out.println("Type the new colour or press enter to leave it as is: ");
			String newColour = reader.readLine();
			if (newColour.equals("")) {
				newColour = toBeModified.getColour();
			}

			// If the user doesn�t type anything, the specie is not changed
			System.out.println("Type the new specie or press enter to leave it as is: ");
			String newSpecie = reader.readLine();
			if (newSpecie.equals("")) {
				newSpecie = toBeModified.getSpecie();
			}

			// If the user doesn�t type anything, the date of birth is not changed
			System.out.println("Type the new date of birth in this format (year-month-day)"
					+ " or press enter to leave it as is: ");

			String newDob = reader.readLine();
			Date dateNewDob;
			if (newDob.equals("")) {
				dateNewDob = toBeModified.getDob();
			} else {
				dateNewDob = Date.valueOf(LocalDate.parse(newDob, formatter));
			}
			Animal updatedAnimal = new Animal(newName, newCountry, newColour, newSpecie, dateNewDob);
			// At the very end...
			animalManager.update(updatedAnimal);

		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;

	}

// ------------------------------------------------------------------------------

	// SELECT ANIMAL
	
// ------------------------------------------------------------------------------

	private static void SelectAnimal() throws Exception {

		try {
			System.out.println("Introduce id of the animal: ");
			int AnimalId = Integer.parseInt(reader.readLine());
			Animal animal = animalManager.getAnimal(AnimalId);
			animal.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//------------------------------------------------------------------------------------
	
	//SHOW AN ANIMAL
	
//---------------------------------------------------------------------------------
	
	private static void ShowAnimal () throws Exception {
		
		List<Animal> animals = animalManager.showAnimals();
		System.out.println("You'll see all the animals of the table ");
		for (Animal animal : animals) {
			System.out.println(animal.toString());
		}
		
		
	}
	
//---------------------------------------------------------------------------------------
	
	// GENERATE XML FOR ANIMAL
	
//------------------------------------------------------------------------------------------
	
	private static void GenerateXML (int AnimalId) throws Exception{
		Animal animal = animalManager.getAnimal(AnimalId); //we get the dog from the data base
		//Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Animal.class);//We specify the class we want for the XML
		//Get the marshaller
		Marshaller marshal = context.createMarshaller(); // we call the create a marshaller method from the context class
		//Pretty formating
		marshal.setProperty(marshal.JAXB_FORMATTED_OUTPUT, true);
		//Marshal the Animal to a file
		File file= new File("_/xmls/Output-Animal.xml");
		marshal.marshal(animal, file);
		//Marshal the animal to the screen
		marshal.marshal(animal, System.out); //calling the method but instead with file with system.out
	}

	
//---------------------------------------------------------------------------------------------------
	
	//CREATE ANIMAL THROUGH XML
	
//--------------------------------------------------------------------------------------------------
	
	public static void CreateAnimalXML() throws Exception{
		//Create a JAXBContext
		JAXBContext context = JAXBContext.newInstance(Animal.class);//We specify the class we want for the XML
		//Get the unmarshaller
		Unmarshaller unmarshal = context.createUnmarshaller(); // we call the create a marshaller method from the context class
		//Unmarshal the Animal from a file
		System.out.println("Type the filename for the XML document (expected in the xmls folder): ");
		String fileName= reader.readLine();
		File file= new File("_/xmls/"+ fileName);
		Animal animal= (Animal) unmarshal.unmarshal(file); //we do a cast to animal
		// now we need to output the dog to the data base
		//Print the animal
		System.out.println("Added to the data base: "+ animal); //to see what It's added to the data base
		//Insert it
		animalManager.add(animal);
		//We have the animal created
}
	
	
	
	
	

//--------------------------------------------------------------------------------------------------------------

	// CLIENT PART

//--------------------------------------------------------------------------------------------------------------------------------

	// ADD CLIENT

//--------------------------------------------------------------------------------------------------------------------------------	
	private static void addClient() throws Exception {
		System.out.println("Type!");
		System.out.println("Name");
		String name = reader.readLine();
		System.out.println("Cellphone");
		Integer cellphone = Integer.parseInt(reader.readLine());
		System.out.println("email");
		String email = reader.readLine();
		System.out.println("adress");
		String adress = reader.readLine();
		System.out.println("date of birth (yyyy-MM-dd");
		String dob = reader.readLine();
		LocalDate dateOfBirth = LocalDate.parse(dob, formatter);

		Client client = new Client(name, cellphone, email, adress, Date.valueOf(dateOfBirth));
		// creo q este add esta bien pero no estoy segura
		clientManager.addClient(client);
	}

//--------------------------------------------------------------------------------------------------------------------------------

	// DELETE CLIENT

//--------------------------------------------------------------------------------------------------------------------------------
	private static boolean deleteClient() throws Exception {
		ArrayList<Client> clients = new ArrayList<Client>();
		boolean conexito = true;
		boolean encontrado = false;
		int indice = 0;
		// lo inicializamos como un indice no valido
		// buscamos el indice que queremos eliminar

		System.out.println("Name");
		String name = reader.readLine();

		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getName().equals(name)) {
				indice = i; // guardamos el indice donde se encuentra el cliente
				encontrado = true;
				break;
			}
		}

		if (encontrado) {
			clients.remove(indice); // el indice encontrado lo eliminamos

		} else {
			conexito = false;
		}

		return conexito;

	}

//--------------------------------------------------------------------------------------------------------------------------------

	// SELECT CLIENT

//--------------------------------------------------------------------------------------------------------------------------------	

	private static void selectClient() throws Exception {// select client by name

		// cambiar para pedir id y el nombre de la funcon
		// pedir id haciendo parseint por ser entero
		// llamar a la funcion
		try {
			System.out.println("Introduce the client ID");
			int clientId = Integer.parseInt(reader.readLine());

			List<Client> clients = clientManager.searchClient(clientId);
			for (Client client : clients) {
				System.out.println(client.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//--------------------------------------------------------------------------------------------------------------------------------

	// SEARCH CLIENT BY NAME

//--------------------------------------------------------------------------------------------------------------------------------	
	private static void searchClientByName() throws Exception {
		System.out.println("Type!");
		System.out.println("Name");
		String name = reader.readLine();
		List<Client> clients = clientManager.searchByName(name);
		for (Client client : clients) {
			System.out.println(client);
		}
	}
//--------------------------------------------------------------------------------------------------------------------------------

	// SEARCH CLIENT BY EMAIL

//--------------------------------------------------------------------------------------------------------------------------------	
	private static void searchClientByEmail() throws Exception {
		System.out.println("Type!");
		System.out.println("email");
		String email = reader.readLine();
		List<Client> clients = clientManager.searchByEmail(email);
		for (Client client : clients) {
			System.out.println(client);
		}
	}

//--------------------------------------------------------------------------------------------------------------------------------

	// UPDATE CLIENT

//--------------------------------------------------------------------------------------------------------------------------------
	private static boolean updateClient() throws Exception {
		boolean exito = true;
		int clientId = 0;
		try {
			System.out.println("Introduce the id of the client ");
			String id = reader.readLine();
			clientId = Integer.parseInt(id);
			// I get the chocolate
			Client toBeModified = clientManager.getClient(clientId);
			System.out.println("Actual name: " + toBeModified.getName());
			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new name or press enter to leave it as is: ");
			String newName = reader.readLine();
			if (newName.equals("")) {
				newName = toBeModified.getName();
			}

			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new cellphone or press enter to leave it as is: ");
			String newCellphone = reader.readLine();
			Integer intNewCellphone;
			if (newCellphone.equals("")) {
				intNewCellphone = toBeModified.getCellphone();
			} else {
				intNewCellphone = Integer.parseInt(newCellphone);
			}

			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new email or press enter to leave it as is: ");
			String newEmail = reader.readLine();
			if (newEmail.equals("")) {
				newEmail = toBeModified.getEmail();
			}

			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new adress or press enter to leave it as is: ");
			String newAdress = reader.readLine();
			if (newAdress.equals("")) {
				newAdress = toBeModified.getAdress();
			}

			// If the user doesn�t type anything, the name is not changed
			System.out.println("Type the new shape or press enter to leave it as is: ");
			String newDob = reader.readLine();
			Date dateNewDob;
			if (newDob.equals("")) {
				dateNewDob = toBeModified.getDob();
			} else {
				dateNewDob = Date.valueOf(LocalDate.parse(newDob, formatter));
			}

			Client updatedClient = new Client(newName, intNewCellphone, newEmail, newAdress, dateNewDob);
			// At the very end...
			clientManager.update(updatedClient);

		} catch (Exception e) {
			e.printStackTrace();
			exito = false;
		}
		return exito;

	}

//--------------------------------------------------------------------------------------------------------------------------------

	// GENERATE CLIENT XML

//--------------------------------------------------------------------------------------------------------------------------------	

	private static void generateClientXML() throws Exception {
		System.out.print("Please introduce the id of the Client");
		System.out.print("1. client id ");
		Integer clientId = Integer.parseInt(reader.readLine());
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

	// CREATE A CLIENT WITH XML

//--------------------------------------------------------------------------------------------------------------------------------

		
		public static void CreateAClientXML() throws Exception{
			//Create a JAXBContext
			JAXBContext context = JAXBContext.newInstance(Client.class);//We specify the class we want for the XML
			//Get the unmarshaller
			Unmarshaller unmarshal = context.createUnmarshaller(); // we call the create a marshaller method from the context class
			//Unmarshal the Animal from a file
			System.out.println("Type the filename for the XML document (expected in the xmls folder): ");
			String fileName= reader.readLine();
			File file= new File("_/xmls/"+ fileName);
			Client client= (Client) unmarshal.unmarshal(file); //we do a cast to animal
			// now we need to output the dog to the data base
			//Print the animal
			System.out.println("Added to the data base: "+ client); //to see what It's added to the data base
			//Insert it
			clientManager.addClient(client);
			//We have the animal created
			
		
		}
		
// ---------------------------------------------------------------------

		// SELECT CHOCOLATE

// -----------------------------------------------------------------------------

		private static void showClient() {

			try {
				System.out.println("Introduce the chocolate�s ID");
				int chocoId = Integer.parseInt(reader.readLine());

				List<Chocolate> chocolates = chocolateManager.searchChocolate(chocoId);
				for (Chocolate chocolate : chocolates) {
					System.out.println(chocolate.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}	
		
		
		
	

}// main
