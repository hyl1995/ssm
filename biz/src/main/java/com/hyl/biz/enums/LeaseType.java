package com.hyl.biz.enums;

/**
 * 出租方式
 */
public enum LeaseType {

    WHOLERENT("wholeRent", "整租"),
    JOINTRENT("jointRent", "合租")
    ;

    private String code;
    private String desc;

    private LeaseType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举名称获取枚举
     *
     * @param code                     枚举code
     * @return GenderEnum        对应的枚举值
     */
    public static LeaseType byCode(final String code) {
        for (LeaseType element : values()) {
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
