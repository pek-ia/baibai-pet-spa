import java.time.LocalDate;
import java.sql.*;

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

        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection =
                     DriverManager.getConnection(
                             "jdbc:mysql://localhost:3306/baibai_petspa", "poweruser", "poweruser")) {
            statement = connection.createStatement();

            String sql = "SELECT * FROM pet";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                String s1 = resultSet.getString("pet_id");
                String s2 = resultSet.getString("name");
                String s3 = resultSet.getString("owner_id");
                System.out.println(s1 + " " + s2 + " " + s3);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Owner paul = new Owner("Paul", "Kimball", "123 Cherry Lane", "1-800-555-1212");

        Pet baibai = new Pet("Bai-Bai", "Cat", null, paul);

        System.out.println("My pet's name is " + baibai.getName() + " " + paul.getLastName());

        baibai.setBirthday(LocalDate.now().minusYears(14));

        System.out.println("His Birthday is " + baibai.getBirthday());

    }
}
