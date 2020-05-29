package db.interfaces;
import java.util.List;

import pojos.Animal;
import pojos.Milk;

public interface MilkManager{
	
	public void give(int chocoId, int milkId);
	public void add(Milk milk);
	public List<Milk> searchMilk(int milkId);
	public void delete(int milkId);
	public void update(Milk milk);
	public List<Milk> searchByName(String name);
	public List<Milk> searchByType(String type);
	public Milk getMilk(int MilkId);
	public List<Milk> showMilk();
}