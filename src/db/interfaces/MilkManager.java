package db.interfaces;
import java.util.List;
import pojos.Milk;

public interface MilkManager{
	public void add(Milk milk);
	public List<Milk> searchMilk(int milkId);
	public void delete(Milk milk);
	public void update(Milk milk);
	public List<Milk> searchByType(String type);
	
}