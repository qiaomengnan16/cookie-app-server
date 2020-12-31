package com.app.server.model;

import lombok.Data;

// 用户看病历史
@Data
public class UserIllness {

    // 用户ID
    private String userId;

    // 药品ID
    private String illnessId;

}
