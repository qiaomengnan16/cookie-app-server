package com.app.server.dao;

import com.app.server.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDao {

    private final Map<String, User> USER_TABLE;

    public UserDao() {
        USER_TABLE = new ConcurrentHashMap<>();
    }

    public void saveUser(User user) {
        USER_TABLE.put(user.getCardNo(), user);
    }

    public User getUser(String cardNo) {
        return USER_TABLE.get(cardNo);
    }

}
