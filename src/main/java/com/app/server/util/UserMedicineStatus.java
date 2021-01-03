package com.app.server.util;

/**
 * @author qiaomengnan
 * @ClassName: UserMedicineStatus
 * @Description:
 * @date 2021/1/3
 */
public enum UserMedicineStatus {

    NO("0", "未领取"),
    YES("1", "已领取");

    private String type;

    private String desc;

    UserMedicineStatus(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

}
