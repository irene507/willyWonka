package db.interfaces;

import java.util.List;
import pojos.Animal;
import pojos.Client;
import pojos.OompaLoompa;

public interface AnimalManager {
	
	 public void add (Animal animal);
	 public void delete (int AnimalId);
	 public List<Animal> searchAnimal(int AnimalID);
	 public List<Animal> searchByNameAnimal(String name_a);
	 public List<Animal> searchBySpecieAnimal(String specie_a);
	 public void update (Animal animal);
	 public Animal getAnimal(int AnimalId);
	 public List<Animal> showAnimals();
}
