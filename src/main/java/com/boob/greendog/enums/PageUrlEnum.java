package com.boob.greendog.enums;

public enum PageUrlEnum {

    ALL_PETS("/pet/allPets"),
    MY_PETS("/pet/myPets"),

    ALL_APPLIES("/apply/allApplies"),
    MY_APPLIES("/apply/myApplies"),

    ALL_APPOINTMENTS("/appointment/allAppointments"),
    MY_APPOINTMENTS("/appointment/myAppointments"),

    ALL_BULLETINS("/bulletin/allBulletins"),

    ALL_CUSTOMERS("/user/allCustomers"),

    ALL_MEDICINES("/medicine/allMedicines"),

    ALL_INSTANCES("/instance/allInstances"),

    ALL_DISEASES("/disease/allDiseases"),

    ALL_BILLS("/bill/allBills"),
    MY_BILLS("/bill/myBills"),

    ALL_DOCTORS("/doctor/allDoctors"),
    MY_DOCTORS("/doctor/myDoctors"),


    ;

    private String url;

    PageUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
