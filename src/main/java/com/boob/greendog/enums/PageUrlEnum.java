package com.boob.greendog.enums;

public enum PageUrlEnum {

    ALL_PETS("/pet/pets"),
    MY_PETS("/pet/pets?term=my"),
    ;

    private String url;

    PageUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
