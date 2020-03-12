package com.boob.greendog.exp;

import com.boob.greendog.model.Apply;
import com.boob.greendog.model.Customer;
import com.boob.greendog.model.Pet;
import lombok.Data;

@Data
public class ApplyExp {

    private Apply apply;//申请
    private Customer customer;//申请人
    private Pet pet;//申请宠物

}
