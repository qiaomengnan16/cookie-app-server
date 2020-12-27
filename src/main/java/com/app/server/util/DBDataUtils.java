package com.app.server.util;

import com.app.server.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBDataUtils {

    public static final Map<String, User> USER_TABLE = new ConcurrentHashMap<>();

    public static void saveUser(User user) {
        USER_TABLE.put(user.getCardNo(), user);
    }

    public static User getUser(User user) {
        return USER_TABLE.get(user.getCardNo());
    }

}
