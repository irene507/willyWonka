package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.interfaces.OompaLoompaManager;
import pojos.OompaLoompa;

public class SQLiteOompaLoompaManager implements OompaLoompaManager{
	private Connection c;
	
	
	public SQLiteOompaLoompaManager (Connection c) {
		this.c=c;
	}
	
	
	@Override
	public void add(OompaLoompa oompaloompa) {
		try {
			String sql = "INSERT INTO oompaLoompa (name, cellphone, email, address, dob)"
					+"VALUES(?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, oompaloompa.getName());
			prep.setInt(2, oompaloompa.getCellphone());
			prep.setString(3, oompaloompa.getEmail());
			prep.setString(4, oompaloompa.getAddress());
			prep.setDate(5, oompaloompa.getDob());
			
			prep.executeUpdate();
			prep.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public OompaLoompa select(String OLname) {
		OompaLoompa newOompaLoompa = null;
		try {
			String sql = "SELECT * FROM oompaLoompa WHERE name = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, OLname);
			ResultSet rs = prep.executeQuery();
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int cellphone = rs.getInt("cellphone");
			String email = rs.getString("email");
			String address = rs.getString("address");
			Date dob = rs.getDate("dob");
		
			newOompaLoompa = new OompaLoompa(id,name,cellphone,email,address,dob);
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return newOompaLoompa;
		
	}

	@Override
	public void update(OompaLoompa oompaloompa) {
		try{
			String sql = "UPDATE oompaLoompa SET name = ?, cellphone = ?, email = ?, address = ?, dob = ? WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,oompaloompa.getName());
			prep.setInt(2, oompaloompa.getCellphone());
			prep.setString(3, oompaloompa.getEmail());
			prep.setString(4, oompaloompa.getAddress());
			prep.setDate(5, oompaloompa.getDob());
			prep.setInt(6, oompaloompa.getId());
			prep.executeUpdate();
			prep.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public void delete(int Wid) {
		try {
			String sql = "DELETE FROM oompaLoompa WHERE id = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, Wid);
			prep.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	@Override
	public List<OompaLoompa> searchByName(String name) {
		List<OompaLoompa> OLList = new ArrayList<OompaLoompa>();
		try{
			String sql = "SELECT * FROM oompaLoompa WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,"%"+name+"%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String OLname = rs.getString("name");
				int cellphone = rs.getInt("cellphone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date dob = rs.getDate("dob");
				
				OompaLoompa newOompaLoompa = new OompaLoompa(id,OLname,cellphone,email,address,dob);
				OLList.add(newOompaLoompa);
				
			}
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return OLList;
	}

	@Override
	public List<OompaLoompa> searchBydob(Date dob) {
		List<OompaLoompa> OLList = new ArrayList<OompaLoompa>();
		try{
			String sql = "SELECT * FROM oompaLoompa WHERE dob = ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, dob);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int cellphone = rs.getInt("cellphone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Date OLdob = rs.getDate("dob");
				
				OompaLoompa newOompaLoompa = new OompaLoompa(id,name,cellphone,email,address,OLdob);
				OLList.add(newOompaLoompa);
				
			}
			prep.close();
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return OLList;
	}
	
}
