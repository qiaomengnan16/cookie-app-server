package com.app.server.model;

import lombok.Data;

// 用户看病历史
@Data
public class UserIllness {

    // id
    private String id;

    // 用户ID
    private String userId;

    // 药品ID
    private String illnessId;

    // 药品名称
    private String illnessName;

}
