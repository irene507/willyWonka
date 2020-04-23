package db.interfaces;

public interface DBManager {
	    //ya es estatico
        public void connect();
        public void disconnect();
        public void createTables();
        
        public ChocolateManager getChocolateManager();
		public ClientManager getClientManager();
        
}
