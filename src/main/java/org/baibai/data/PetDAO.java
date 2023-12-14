package org.baibai.data;

import org.baibai.model.Pet;

public interface PetDAO {
    Pet getById(int petId);
}
