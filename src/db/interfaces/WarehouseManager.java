package db.interfaces;
import java.util.List;
import pojos.Warehouse;

public interface WarehouseManager{
	public void add(Warehouse warehouse);
	public Warehouse select(String Wname);
	public void delete(int Wid);
	public void update(Warehouse warehouse);
	public List<Warehouse> searchByName(String name);
	public List<Warehouse> searchByCorridor(Integer corridor);
	
}
