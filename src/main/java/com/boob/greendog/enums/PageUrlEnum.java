package com.boob.greendog.enums;

public enum PageUrlEnum {

    ALL_PETS("/pet/allPets"),
    MY_PETS("/pet/myPets"),

    ALL_APPLIES("/apply/allApplies"),
    MY_APPLIES("/apply/myApplies"),

    ALL_BULLETINS("/bulletin/allBulletins"),

    ALL_CUSTOMERS("/user/allCustomers"),

    ;

    private String url;

    PageUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
