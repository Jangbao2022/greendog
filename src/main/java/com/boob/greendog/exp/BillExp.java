package com.boob.greendog.exp;

import com.boob.greendog.model.Bill;
import com.boob.greendog.model.Customer;
import com.boob.greendog.model.Medicine;
import lombok.Data;

@Data
public class BillExp {

    private Bill bill;
    private Medicine medicine;
    private InstanceExp instanceExp;
    private Customer customer;

}
