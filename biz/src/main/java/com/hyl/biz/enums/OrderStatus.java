package com.hyl.biz.enums;

public enum OrderStatus {

    WAITINGPAY("waitingPay", "待付款"),
    EXECUTING("executing", "执行中"),
    SUCCESS("success", "已完成"),
    CANCEL("cancel", "已中止")
    ;

    private String code;
    private String desc;

    private OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举名称获取枚举
     *
     * @param code                     枚举code
     * @return GenderEnum        对应的枚举值
     */
    public static OrderStatus byCode(final String code) {
        for (OrderStatus element : values()) {
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
