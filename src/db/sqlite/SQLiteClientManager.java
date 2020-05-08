package db.sqlite;

	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

	import db.interfaces.ClientManager;
import pojos.Chocolate;
import pojos.Client;

	public class SQLiteClientManager implements ClientManager {
		
		private Connection c;
		
		public SQLiteClientManager(SQLiteManager manager) {                                                 //duda sqlite manager????
			this.c=c;
			
			
		}

		public SQLiteClientManager(Connection c2) {
			// TODO Auto-generated constructor stub
		}
		


		@Override
		public void addClient(Client client)  {
			
			try {
				String sql = "INSERT INTO clients (id, name , cellphone , email, adress, dob) "
						+ "VALUES (?,?,?,?,?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, client.getId());
				prep.setString(2, client.getName());
				prep.setInt(3, client.getCellphone());
				prep.setString(4, client.getEmail());
				prep.setString(3,  client.getAdress());
				prep.setDate(2, client.getDob());                                                  //CHEATSHEETS PARA PASARLO A SQL
				
				prep.executeUpdate();
				prep.close();
				
				
			
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}// con esto he insert un client
		
		
		//SELECT CLIENT
		@Override
		public List<Chocolate> searchClient(int clientId) {
			List<Chocolate> clients = new ArrayList<Client>(); 
			Client newClient;
			
			try{
			String sql = "SELECT * FROM clients WHERE id= ? ";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, clientId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String chocoName = rs.getString("name");
				Integer cellphone = rs.getInt("cellphone");
				String email = rs.getString("email");
				String adress = rs.getString("adress");
				Date dob = rs.getDate("dob");
				
				
				newClient = new Client(id, chocoName, cellphone, email, adress,dob);
				clients.add(newClient);
				rs.close();
				stmt.close();
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return clients;
			
		}
		

		@Override
		public void delete(Client client) {
			try {
				String sq1= "DELETE * FROM clients WHERE ID= ?";
				PreparedStatement p= c.prepareStatement(sq1);
				p.setInt(1, clientId);
				p.executeQuery();
				p.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			

		

		@Override
		public void update(Client client) {
			try {
			//update every aspect of a particular client
			String sql = "UPDATE clients SET name=?, cellphone=?, email=?, adress=?, dob=? WHERE id=?";
			PreparedStatement s= c.prepareStatement(sql);
			s.setString(1, client.getName());
			s.setInt(2, client.getCellphone());
			s.setString(3, client.getEmail());
			s.setString(4, client.getAdress());
			s.setDate(5, client.getDob());
			s.setInt(6, client.getId());
			s.executeUpdate();
			s.close();
			
			}
			
			catch( Exception e){
				e.printStackTrace();
				
			}
			

		}

		@Override
		public void select(Client client) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<Client> searchByName(String name) {
			//create an empty list of clients
			List<Client> clientsList =new ArrayList<Client>();
			// search for clients that fit the name
			
		try {
		
			String sql = "SELECT * FROM clients WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+name+ "%");
			ResultSet rs = prep.executeQuery();//PORQUE SELECT ES UNA QUERY
			//for each result...
			while (rs.next()) {//VAMOS AVANZANDO REGISTRO A REGISTRO
				int id = rs.getInt("id");
				String clientName = rs.getString("name");
				int cellphone= rs.getInt("cellphone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date dob = rs.getDate("dob");
				//create a new client
				Client newClient =new Client(id,clientName,cellphone,email,address,dob);//crrate a new client
				//add it to the list
				clientsList.add(newClient);
				
			}
		}catch( Exception e) {
			e.printStackTrace();
			
		}
		return clientsList;
				
		}
		
//--------------------------------------------------------------------------------------------------------------------------------

                          //SEARCH CLIENT BY EMAIL

//--------------------------------------------------------------------------------------------------------------------------------

		@Override
		public List<Client> searchByEmail(String email) {
			//create an empty list of clients
			List<Client> clientsList =new ArrayList<Client>();
			// search for clients that fit the name
			
		try {
		
			String sql = "SELECT * FROM clients WHERE email LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+email+ "%");
			ResultSet rs = prep.executeQuery();//PORQUE SELECT ES UNA QUERY
			//for each result...
			while (rs.next()) {//VAMOS AVANZANDO REGISTRO A REGISTRO
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int cellphone= rs.getInt("cellphone");
				String clientEmail = rs.getString("email");
				String address = rs.getString("address");
				Date dob = rs.getDate("dob");
				//create a new client
				Client newClient =new Client(id,name,cellphone,clientEmail,address,dob);//crrate a new client
				//add it to the list
				clientsList.add(newClient);
				
			}
		}catch( Exception e) {
			e.printStackTrace();
			
		}
		return clientsList;
				
		}
		
	
//--------------------------------------------------------------------------------------------------------------------------------

                                 //SEARCH CLIENT BY EMAIL

//--------------------------------------------------------------------------------------------------------------------------------

	@Override
	public List<Chocolate> showClients(){
		//Create an empty list of chocolates 
		List<Chocolate> clientsList = new ArrayList<Chocolate>();
		//Get all the chocolates 
		try{
			String sq1 = "SELECT * FROM clients";
			PreparedStatement prep= c.prepareStatement(sq1);
			ResultSet rs = prep.executeQuery();
			//For each result 
			while(rs.next()){
				int id = rs.getInt("id");
				String clientName = rs.getString("name");
				int cellphone= rs.getInt("cellphone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date dob = rs.getDate("dob");
	
			//Create a new chocolate 
				Client newClient = new Client(id, clientName, cellphone, email, address, dob );
			//Add it to the list
				clientsList.add(newClient);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return clientsList;

		
	}//showallthelcients
	
//--------------------------------------------------------------------------------------------------------------------------------

               //SEARCH CLIENT BY EMAIL

//--------------------------------------------------------------------------------------------------------------------------------
	
	
	
	@Override
	public Chocolate getClient(int clientId){
	
		Client newClient = null; 		
		try{
			String sq1 = "SELECT * FROM clients WHERE ID= ? "; 
			PreparedStatement p = c.prepareStatement(sq1);
			p.setInt(1,  clientId);
			//Because we are going to do it just once
			ResultSet rs = p.executeQuery();
			
		    rs.next();
		    int id = rs.getInt("id");
			String clientName = rs.getString("name");
			int cellphone= rs.getInt("cellphone");
			String email = rs.getString("email");
			String address = rs.getString("address");
			Date dob = rs.getDate("dob");
				
			//Create a new chocolate 
				Client newClient = new Client(id, clientName, cellphone, email, address, dob);
		  }catch(SQLException e){
			  e.printStackTrace();
		  }
		return client; 
		}//function
	}
	
	
	
	
	



