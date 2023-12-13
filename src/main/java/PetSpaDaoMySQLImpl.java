import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetSpaDaoMySQLImpl implements PetSpaDAO {

    private DataSource dataSource;
    private Connection connection;

    public PetSpaDaoMySQLImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Pet getPetById(int petId) {

        Pet pet = null;

        // create prepared statement
        //    define the SQL SELECT to bring back one record from pet table

        try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM pet
                    WHERE pet_id = ?;
                    """) ) {

            statement.setInt(1, petId);

            ResultSet rs = statement.executeQuery();

            rs.next();
            pet = new Pet( rs.getString("name"), rs.getString("species"),
                    (rs.getDate("birthday")  == null) ? null: rs.getDate("birthday").toLocalDate(),
                    getOwnerById( rs.getInt("owner_id")) );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pet;
    }

    @Override
    public Owner getOwnerById(int ownerId) {
        Owner owner = null;

        // create prepared statement
        //    define the SQL SELECT to bring back one record from pet table

        try (PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM owner
                    WHERE owner_id = ?;
                    """) ) {

            statement.setInt(1, ownerId);

            ResultSet rs = statement.executeQuery();

            rs.next();
            owner = new Owner( rs.getString("first_name"), rs.getString("last_name"),
                    (rs.getString("address")  == null) ? null: rs.getString("address"),
                    (rs.getString("phone")  == null) ? null: rs.getString("phone"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return owner;
    }
}
