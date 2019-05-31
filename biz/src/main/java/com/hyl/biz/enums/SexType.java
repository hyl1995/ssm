package com.hyl.biz.enums;

public enum SexType {

    MALE("male", "男性"),
    FEMALE("female", "女性")
    ;

    private String code;
    private String desc;

    private SexType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举名称获取枚举
     *
     * @param code                     枚举code
     * @return GenderEnum        对应的枚举值
     */
    public static SexType byCode(final String code) {
        for (SexType element : values()) {
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
