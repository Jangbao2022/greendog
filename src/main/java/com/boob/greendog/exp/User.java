package com.boob.greendog.exp;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String nickname;
    private String account;
    private String password;
    private Integer type;
}
