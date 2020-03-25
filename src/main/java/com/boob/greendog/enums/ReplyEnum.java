package com.boob.greendog.enums;

/**
 * 请求类型enum
 */
public enum ReplyEnum {

    CONSENT(1),
    REJECT(2),
    ;
    private int type;

    ReplyEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
