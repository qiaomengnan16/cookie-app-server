package com.app.server.util;

import java.util.UUID;

public class UUIDUtils {

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
