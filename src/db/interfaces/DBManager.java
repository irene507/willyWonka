package db.interfaces;

public interface DBManager {
	    //ya es estatico
        public void connect();
        public void disconnect();
      
        
        public void createChocolateTables();
        public void createClientTables();
        
        public ChocolateManager getChocolateManager();
		public ClientManager getClientManager();
        public AnimalManager getAnimalManager();
		public void createTables();
}
