package com.app.server.model;

import lombok.Data;

@Data
public class User {

    // 真实姓名
    private String realName;

    // 身份证号
    private String cardNo;

    // 年龄
    private Integer age;

    // 性别
    private String gender;

}
