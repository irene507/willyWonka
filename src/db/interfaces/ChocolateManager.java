package db.interfaces;
import java.util.List;

import pojos.*;

public interface ChocolateManager {

   public void create(Chocolate chocolate);
   public List<Chocolate> searchChocolate(int chocoId);
   public void delete(int chocoId);
   public boolean update(Chocolate chocolate);
   public List<Chocolate> searchByName(String name);
   public List<Chocolate> searchByType(String type);
   public void admit(Chocolate chocolate);
   public Chocolate getChocolate(int chocoId);
   public List<Chocolate> showChocolates();
}

