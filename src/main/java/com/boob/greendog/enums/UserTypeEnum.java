package com.boob.greendog.enums;

public enum UserTypeEnum {

    CUSTOMER(1),
    ADMIN(2),
    ;

    private int type;

    UserTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
