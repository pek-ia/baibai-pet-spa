import java.sql.*;
import java.time.LocalDate;
import java.sql.*;

public class BaiBaiPetSpa {
    public static void main(String[] args) {

        /*
         * In Java, we can create objects and use setters and getters, like this:
         */
        Owner paul = new Owner("Paul", "Kimball", "123 Cherry Lane", "1-800-555-1212");
        Pet baibai = new Pet("Bai-Bai", "Cat", null, paul);

        /* bai-bai's full name */
        System.out.println("My pet's name is " + baibai.getName() + " " + paul.getLastName());

        /* set his birthday to 14 years ago */
        baibai.setBirthday(LocalDate.now().minusYears(14));

        /* bai-bai's birthday */
        System.out.println("His Birthday is " + baibai.getBirthday());


        DataManager dm = new DataManagerMySQLImpl();
        int baibaiPetId = 1;
        String petName = dm.getPetFullName(baibaiPetId);
        dm.setPetBirthday(baibaiPetId, Date.valueOf(LocalDate.now()));

        Date petBirthday = dm.getPetBirthday(baibaiPetId);

        System.out.println(petName);
        System.out.println(petBirthday);


    }
}
