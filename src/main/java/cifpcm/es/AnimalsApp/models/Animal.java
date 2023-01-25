package cifpcm.es.AnimalsApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
@Entity
public class Animal {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "El nombre es obligatorio")
    @NotNull
    @Size(min = 2,max=20,message = "El tamaño debe estar entre 2 y 20 caracteres")
    private String name;
    @NotBlank(message = "La especie es obligatoria")
    @NotNull
    @Size(min = 2,max=20,message = "El tamaño debe estar entre 2 y 20 caracteres")
    private String species;
    @NotNull(message = "Hay que especificar una esperanza de vida")
    @Min(value = 1,message = "El valor debe ser al menos de 1 año")
    private int lifeExpectancy;
    private boolean extinct;
    @ManyToOne
    private AnimalGroup group;

    public Animal(){}
    public Animal(int id, String newName, String newSpecies, int newLifeExp, boolean isExtinct) {
        this.id = id;
        this.name = newName;
        this.species = newSpecies;
        this.lifeExpectancy = newLifeExp;
        this.extinct = isExtinct;
    }
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

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }
    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public boolean isExtinct() {
        return extinct;
    }
    public void setExtinct(boolean extinct) {
        this.extinct = extinct;
    }

}
