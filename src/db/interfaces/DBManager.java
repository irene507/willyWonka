package db.interfaces;

public interface DBManager {
	    //ya es estatico
        public void connect();
        public void disconnect();
        
        public ChocolateManager getChocolateManager();
        public ClientManager getClientManager();
        public AnimalManager getAnimalManager();
        public void createTables();
        public int getLastId();
}
