package com.boob.greendog.enums;

public enum MedicineStatusEnum {

    OPEN(1),//继续出售
    CLOSE(2),//不再出售
    ;

    private int type;

    MedicineStatusEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
