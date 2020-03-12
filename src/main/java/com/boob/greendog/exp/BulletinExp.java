package com.boob.greendog.exp;

import com.boob.greendog.model.Administrator;
import com.boob.greendog.model.Bulletin;
import lombok.Data;

@Data
public class BulletinExp {

    private Administrator publisher;
    private Bulletin bulletin;
}
