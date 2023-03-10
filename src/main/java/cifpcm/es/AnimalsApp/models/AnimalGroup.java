package cifpcm.es.AnimalsApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class AnimalGroup {
    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2,max=20,message = "El tamaño debe estar entre 2 y 20 caracteres")
    private String name;
    @OneToMany(mappedBy = "group",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Animal> animals;

    public AnimalGroup(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
