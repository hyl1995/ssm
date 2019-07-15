package com.hyl.core.util.template.company;

public class EnumObject {
    private String name;
    private String memo;

    public EnumObject(String name, String memo) {
        this.name = name;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
