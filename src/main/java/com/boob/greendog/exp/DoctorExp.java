package com.boob.greendog.exp;

import com.boob.greendog.model.Customer;
import com.boob.greendog.model.Doctor;
import com.boob.greendog.model.Pet;
import lombok.Data;

/**
 * staff增强类
 */
@Data
public class DoctorExp {

    private Doctor doctor;
    private Customer customer;
    private Pet pet;
}
