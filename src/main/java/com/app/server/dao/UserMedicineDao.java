package com.app.server.dao;

import com.app.server.model.User;
import com.app.server.model.UserMedicine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserMedicineDao {

    private final Map<String, List<UserMedicine>> USER_MEDICINE_TABLE = new ConcurrentHashMap<>();

    public void saveUserMedicine(UserMedicine userMedicine) {
        if (USER_MEDICINE_TABLE.get(userMedicine.getUserId()) == null) {
            USER_MEDICINE_TABLE.put(userMedicine.getUserId(),new ArrayList<>());
        }
        USER_MEDICINE_TABLE.get(userMedicine.getUserId()).add(userMedicine);
    }

    public List<UserMedicine> getUserMedicine(String userId) {
        return USER_MEDICINE_TABLE.get(userId);
    }

    public UserMedicine getUserMedicineById(String userId, String id) {
        List<UserMedicine> userMedicines = USER_MEDICINE_TABLE.get(userId);
        if (userMedicines != null) {
            for (UserMedicine userMedicine : userMedicines) {
                if (userMedicine.getId().equals(id)) {
                    return userMedicine;
                }
            }
        }
        return null;
    }

}
