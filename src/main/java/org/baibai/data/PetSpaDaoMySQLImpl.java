package org.baibai.data;

import org.baibai.model.Owner;
import org.baibai.model.Pet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetSpaDaoMySQLImpl implements PetSpaDAO {

    private DataSource dataSource;
    private Connection connection;
    private OwnerDAO ownerDAO;
    private PetDAO petDao;

    public PetSpaDaoMySQLImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        petDao = new PetDaoMySqlImpl(dataSource);
        ownerDAO = new OwnerDAOMySqlImpl(dataSource);

    }

    @Override
    public Pet getPetById(int petId) {
        return petDao.getById(petId);
    }


    @Override
    public Owner getOwnerById(int ownerId) {
        return ownerDAO.getById(ownerId);
    }
}
