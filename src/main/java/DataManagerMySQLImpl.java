import java.sql.*;
import java.time.LocalDate;

public class DataManagerMySQLImpl implements DataManager {
    private Connection connection;

    public DataManagerMySQLImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
           connection = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/baibai_petspa", "user", "user");
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public Date getPetBirthday(int petId) {
        Date d = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet birthdayRS = statement.executeQuery("""
                    SELECT birthday 
                    FROM pet
                    WHERE pet_id = 1;
                    """);
            birthdayRS.next();
            d = birthdayRS.getDate("birthday");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return d;
    }

    @Override
    public void setPetBirthday(int petId, Date birthday) {
        try (PreparedStatement ps = connection.prepareStatement("""
                    UPDATE pet 
                    SET birthday = ?
                    WHERE pet_id = ?;
                    """)) {

            // ps.setDate(1, java.sql.Date.valueOf(LocalDate.now().minusYears(14)));
            ps.setDate(1, birthday);
            ps.setInt(2, petId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPetFullName(int petId) {
        String fullName = null;
        String sql = """
                    SELECT `name`, last_name
                    FROM pet AS p
                    JOIN owner AS o
                    ON p.owner_id = o.owner_id
                    WHERE p.pet_id = ?;
                    """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            fullName = resultSet.getString("name").concat(" ").concat(resultSet.getString("last_name"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fullName;

    }
}
