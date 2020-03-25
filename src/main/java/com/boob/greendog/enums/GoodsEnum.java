package com.boob.greendog.enums;

/**
 * 处理状态enum
 */
public enum GoodsEnum {

    DOCTOR(1),
    MEDICINE(2),
    ;

    private int type;

    GoodsEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
