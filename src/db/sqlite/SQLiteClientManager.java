package db.sqlite;

	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.List;

	import db.interfaces.ClientManager;
	import pojos.Client;

	public class SQLiteClientManager implements ClientManager {
		
		private Connection c;
		
		public SQLiteClientManager(SQLiteManager manager) {//duda sqlite manager????
			this.c=c;
			
			
		}

		@Override
		public void add(Client client)  {
			
			try {
				String sql = "INSERT INTO clients (id, name , cellphone , email, adress, dob) "
						+ "VALUES (?,?,?,?,?);";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setInt(1, client.getId());
				prep.setString(2, client.getName());
				prep.setInt(3, client.getCellphone());
				prep.setString(4, client.getEmail());
				prep.setString(3,  client.getAdress());
				prep.setDate(2, client.getDob());//CHEATSHEETS PARA PASARLO A SQL
				
				prep.executeUpdate();
				prep.close();
				
				
			
			}catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}// con esto he insert un client

		@Override
		public void delete(Client client) {
			// TODO Auto-generated method stub

		}

		@Override
		public void select(Client client) {
			// TODO Auto-generated method stub

		}

		@Override
		public void update(Client client) {
			// TODO Auto-generated method stub

		}

		@Override
		public List<Client> searchByName(String name) {
			//create an empty list of clients
			List<Client> clientsList =newArrayList<Client>();// NO ENTIENDO ESTE EERRROR
			// search for all clients that fit the name
			
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
				Client newClient =new Client(id,clientName,cellphone,email,address,dob);//crrate a new client
				//add it to the list
				clientsList.add(newClient);
				
			}
		}catch( Exception e) {
			e.printStackTrace();
			
		}
		return clientsList;
				
		}

		@Override
		public List<Client> searchBydob(Date dob) {
			// TODO Auto-generated method stub
			return null;
		}

	}



