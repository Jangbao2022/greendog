package com.boob.greendog.enums;

/**
 * 宠物寄养领养enum
 */
public enum PetEnum {
    ADOPT(1),//领养
    SEND(2),//寄养
    NONE(3),//无
    ;

    private int type;

    PetEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
