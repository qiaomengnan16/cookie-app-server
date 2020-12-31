package com.app.server.dao;

import com.app.server.model.UserIllness;
import com.app.server.model.UserMedicine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserIllnessDao {

    private final Map<String, List<UserIllness>> USER_MEDICINE_TABLE = new ConcurrentHashMap<>();

    // 保存用户看病历史
    public void saveUserIllness(UserIllness userIllness) {
        if (USER_MEDICINE_TABLE.get(userIllness.getUserId()) == null) {
            USER_MEDICINE_TABLE.put(userIllness.getUserId(),new ArrayList<>());
        }
        USER_MEDICINE_TABLE.get(userIllness.getUserId()).add(userIllness);
    }

    // 返回用户看病历史
    public List<UserIllness> getUserIllness(UserIllness userIllness) {
        return USER_MEDICINE_TABLE.get(userIllness.getUserId());
    }

}
