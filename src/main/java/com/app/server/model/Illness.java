package com.app.server.model;

import lombok.Data;

@Data
public class Illness {

    // id
    private String id;

    // 疾病名称
    private String name;

    // 药品id
    private String medicineId;

}
