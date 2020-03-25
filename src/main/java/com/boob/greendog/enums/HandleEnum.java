package com.boob.greendog.enums;

/**
 * 处理状态enum
 */
public enum HandleEnum {

    WAITING(1),//等待处理
    PROCESSED(2),//已处理
    ;

    private int type;

    HandleEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
