package com.boob.greendog.enums;

public enum DefaultPicEnum {

    PET("/petimage/default.jpg"),

    MALE_DOCTOR("/doctorimage/1.jpg"),//男医生
    FEMALE_DOCTOR("/doctorimage/2.jpg"),//女医生
    ;

    private String pic;

    DefaultPicEnum(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
}
