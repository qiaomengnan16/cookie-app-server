package com.app.server.util;

// 鉴权方式
public enum AuthTypes {

    COOKIE("cookie", "cookie模式鉴权"),
    TOKEN("token", "token模式鉴权"),
    JWT("jwt", "jwt模式鉴权");

    private String type;

    private String desc;

    AuthTypes(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

}
