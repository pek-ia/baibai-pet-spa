import java.time.LocalDate;

public class BaiBaiPetSpa {
    public static void main(String[] args) {

        /*
         *  1. Add dependencies to pom.xml file for driver
         *
         *  2. Load driver class with Class.forName()
         *
         *  3. Create Connection with DriverManager
         *
         *  4. Create statement
         *
         *  5. Execute statement
         *
         *  6. Loop through result set
         */










        Owner paul = new Owner("Paul", "Kimball", "123 Cherry Lane", "1-800-555-1212");

        Pet baibai = new Pet("Bai-Bai", "Cat", null, paul);

        System.out.println("My pet's name is " + baibai.getName() + " " + paul.getLastName());

        baibai.setBirthday(LocalDate.now().minusYears(14));

        System.out.println("His Birthday is " + baibai.getBirthday());

    }
}
