package db.interfaces;
import java.util.List;

import pojos.*;

public interface ChocolateManager {

   public void create(Chocolate chocolate);
   public void select(Chocolate chocolate);
   public void changeCharacteristics(Chocolate chocolate);
   public void delete(Chocolate chocolate);
   public void update(Chocolate chocolate);
   public List<Chocolate> searchByName(String name);
   public List<Chocolate> searchByType(String type);
}

