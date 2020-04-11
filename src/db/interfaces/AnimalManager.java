package db.interfaces;

import java.awt.List;
import pojos.Animal;

public interface AnimalManager {
	
	 public void add (Animal animal);
	 public void delete (Animal animal);
	 public List<Animal> searchByNameAnimal(String name_a);
	 public List<Animal> serachBySpecieAnimal(String specie_a);
	 public void update (Animal animal);
	 public Animal getAnimal();
}
