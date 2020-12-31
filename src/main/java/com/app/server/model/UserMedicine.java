package com.app.server.model;

import lombok.Data;

// 用户领药历史表
@Data
public class UserMedicine {

    // 用户ID
    private String userId;

    // 药品ID
    private String medicineId;

    // 是否领取 0. 否 1. 是
    private String status;

}
