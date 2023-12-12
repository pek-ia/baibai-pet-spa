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
        Date petBirthday = dm.getPetBirthday(baibaiPetId);



        /*
         *  BUT, WE CAN DO THE SAME SORTS OF THINGS WITH A DATABASE,
         *  USING JDBC
         */

        /*
         *  1. Add dependencies to pom.xml file for driver
         *
         *  2. Load driver class with Class.forName()
         *
         *  3. Create Connection with DriverManager
         *
         *  4. Create a SQL statement
         *
         *  5. Execute the SQL statement
         *
         *  6. Loop through result set and print result(s)
         */

        Statement statement;
        ResultSet resultSet;

        // We will need a Connection, a Statement, and a ResultSet
        // NOTE: These are all INTERFACES


        java.sql.Date birthday = null;
        // The JDBC driver classes are specific to a particular database
        // This class will be pulled from Maven Central when maven compiles our code

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Each database has its own form of URL and credentials
        // This is the right way to connect to a MySql database on the default port 3306
        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/baibai_petspa", "poweruser", "poweruser")) {

            // Finally, we can create a statement.  But it doesn't have any SQL yet...
            statement = connection.createStatement();

            // Execute some SQL
            resultSet = statement.executeQuery("""
                    SELECT `name`, last_name
                    FROM pet AS p
                    JOIN owner AS o
                    ON p.owner_id = o.owner_id
                    WHERE p.pet_id = 1;
                    """);


            // AT LAST:  Loop through the result set and print out the answers!
            while (resultSet.next()) {

                String s1 = resultSet.getString("name");
                String s2 = resultSet.getString("last_name");
                petName = s1 + " " + s2;
            }

            // Plain old statement is really clumsy to work with!!!

/*
            Statement update = connection.createStatement();
            String paramValue = java.sql.Date.valueOf(LocalDate.now().minusYears(14)).toString();
            update.executeUpdate("""
                    UPDATE pet 
                    SET birthday = """
                    + "\"2023-10-10\"" + """
                    WHERE pet_id = 1;
                    """);
*/

            // Using a prepared statement is better!!!

            PreparedStatement ps = connection.prepareStatement("""
                    UPDATE pet 
                    SET birthday = ?
                    WHERE pet_id = 1;
                    """);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusYears(14)));
            ps.executeUpdate();

            Statement statement1 = connection.createStatement();
            ResultSet birthdayRS = statement1.executeQuery("""
                    SELECT birthday 
                    FROM pet
                    WHERE pet_id = 1;
                    """);
            birthdayRS.next();
            birthday = birthdayRS.getDate("birthday");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("My pet's name is " + petName);
        System.out.println("His birthday is " + birthday);
    }
}
