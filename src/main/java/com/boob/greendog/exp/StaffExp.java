package com.boob.greendog.exp;

import com.boob.greendog.model.Customer;
import com.boob.greendog.model.Staff;
import lombok.Data;

/**
 * staff增强类
 */
@Data
public class StaffExp {

    private Staff staff;
    private Customer customer;
}
