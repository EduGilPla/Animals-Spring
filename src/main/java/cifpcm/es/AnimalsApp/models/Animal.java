package cifpcm.es.AnimalsApp.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Animal {
    private int id;
    @NotBlank(message = "El nombre es obligatorio")
    @NotNull
    @Size(min = 2,max=20)
    private String name;
    @NotBlank(message = "La especie es obligatoria")
    @NotNull
    @Size(min = 2,max=20)
    private String species;
    @NotNull(message = "Hay que especificar una esperanza de vida")
    private int lifeExpectancy;
    private boolean extinct;

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
