package org.baibai.data;

import org.baibai.model.Owner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDAOMySqlImpl implements OwnerDAO {

    DataSource dataSource;

    Connection connection;

    public OwnerDAOMySqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Owner getById(int ownerId) {
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
