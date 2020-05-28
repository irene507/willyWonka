package db.interfaces;
import java.util.List;
import pojos.OompaLoompa;
import java.sql.Date;


public interface OompaLoompaManager{
	public void add(OompaLoompa oompaloompa);
	public OompaLoompa select(String OLname);
	public void update(OompaLoompa oompaloompa);
	public void delete(int Wid);
	public List<OompaLoompa> searchByName(String name);
	public List<OompaLoompa> searchBydob(Date dob);
}
