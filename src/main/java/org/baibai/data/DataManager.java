package org.baibai.data;

import java.sql.Date;
public interface DataManager {
    Date getPetBirthday(int petId);
    void setPetBirthday(int petId, Date birthday );
    String getPetFullName(int petId);
}
