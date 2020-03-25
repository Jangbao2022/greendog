package com.boob.greendog.exp;

import com.boob.greendog.model.*;
import lombok.Data;

@Data
public class AppointmentExp {

    private Appointment appointment;
    private Pet pet;
    private Doctor doctor;
    private Disease disease;
    private Customer customer;
}
