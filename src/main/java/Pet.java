import java.time.LocalDate;

public class Pet {
    private int petId;
    private String name;
    private String species;
    private LocalDate birthday;
    private Owner owner;

    public Pet(String name, String species, LocalDate birthday, Owner owner) {
        this.name = name;
        this.species = species;
        this.birthday = birthday;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getFullName(){
        String fullName = getName();
        if (owner != null){
            fullName += " ";
            fullName += owner.getLastName();
        }
        return fullName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
