package db.interfaces;
/* 
 * Our main object is the DBManager. Because the rest you create them from this one. Then, we have 
 * "AnimalManager", "ChocolateManager".... those are like 'sub-objects'. Then we have "userManager" 
 * which  has the ones related to the users and roles, kind of "management", like createUser, createRole... 
 */
public interface DBManager {
	    //is already static
	
	    //functions that connect and disconnect our database
        public void connect();
        public void disconnect();
        //functions to get the objects of our entities 
        public OompaLoompaManager getOompaLoompaManager();
        public WarehouseManager getWarehouseManager();
        public ChocolateManager getChocolateManager();
        public ClientManager getClientManager();
        public AnimalManager getAnimalManager();
<<<<<<< HEAD
        public MilkManager getMilkmanager();
=======
        public MilkManager getMilkManager();
>>>>>>> branch 'master' of https://github.com/irene507/willyWonka
        public void createTables();
        
        //por qu�? 
        public int getLastId();
		
}
