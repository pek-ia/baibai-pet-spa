import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.time.LocalDate;

public class BaiBaiPetSpa {
    public static void main(String[] args) {

        System.out.println("\nUsing java Pet and Owner objects:");
        /*
         * In Java, we can create objects and use setters and getters, like this:
         */
        Owner paul = new Owner("Paul", "Kimball", "123 Cherry Lane", "1-800-555-1212");
        Pet baibai = new Pet("Bai-Bai", "Cat", null, paul);

        /* bai-bai's full name */
        System.out.println("My pet's full name is " + baibai.getName() + " " + paul.getLastName());

        /* set his birthday to 14 years ago */
        baibai.setBirthday(LocalDate.now().minusYears(14));

        /* bai-bai's birthday */
        System.out.println("His Birthday is " + baibai.getBirthday());

        /*
         * HOWEVER, WE CAN ALSO DO THE SAME SORTS OF THINGS USING A DATABASE
         */

        BasicDataSource dataSource = getBaiBaiDataSource();

        int baibaiPetId = 1;

        /*
         * OPTION 1 - Select values from a database
         */
        System.out.println("\nSelecting COLUMN VALUES from a database:");
        DataManager dm = new DataManagerMySQLImpl(dataSource);
        String petName = dm.getPetFullName(baibaiPetId);
        dm.setPetBirthday(baibaiPetId, Date.valueOf(LocalDate.now().minusYears(14)));

        Date petBirthday = dm.getPetBirthday(baibaiPetId);

        System.out.println("My pet's full name is " + petName);
        System.out.println("His birthday is " + petBirthday);

        /*
         * OPTION 2 - Select Pet and Owner objects from the database
         */
        System.out.println("\nSelecting OBJECTS from a database:");
        PetSpaDAO psd = new PetSpaDaoMySQLImpl(dataSource);
        Pet p = psd.getPetById(baibaiPetId);
        System.out.println("My pet's full name is " + p.getFullName());
        System.out.println("His birthday is " + p.getBirthday());


    }

    private static BasicDataSource getBaiBaiDataSource() {
        BasicDataSource dataSource;
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/baibai_petspa");
        dataSource.setUsername("user");
        dataSource.setPassword("user");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
