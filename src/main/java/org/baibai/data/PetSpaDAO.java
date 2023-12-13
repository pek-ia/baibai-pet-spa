package org.baibai.data;

import org.baibai.model.Owner;
import org.baibai.model.Pet;

public interface PetSpaDAO {
    Pet getPetById(int petId);
    Owner getOwnerById(int ownerId);
}
