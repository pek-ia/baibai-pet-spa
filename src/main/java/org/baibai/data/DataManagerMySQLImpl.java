package org.baibai.data;

import javax.sql.DataSource;
import java.sql.*;

public class DataManagerMySQLImpl implements DataManager {
    private Connection connection;

    public DataManagerMySQLImpl(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Date getPetBirthday(int petId) {
        Date d = null;

        try (PreparedStatement statement = connection.prepareStatement( """
                    SELECT birthday AS b
                    FROM pet
                    WHERE pet_id = ?;
                    """)) {

            statement.setInt(1, petId);
            ResultSet birthdayRS = statement.executeQuery();
            birthdayRS.next();
            d = birthdayRS.getDate("b");

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
