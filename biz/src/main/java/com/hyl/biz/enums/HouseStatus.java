package com.hyl.biz.enums;

public enum HouseStatus {

    WAITINGRENT("waitingRent", "待出租"),
    RENTED("rented", "已出租"),
    NORENT("noRent", "禁止出租")
    ;

    private String code;
    private String desc;

    private HouseStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举名称获取枚举
     *
     * @param code                     枚举code
     * @return GenderEnum        对应的枚举值
     */
    public static HouseStatus byCode(final String code) {
        for (HouseStatus element : values()) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
