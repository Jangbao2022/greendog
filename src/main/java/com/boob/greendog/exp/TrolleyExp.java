package com.boob.greendog.exp;

import com.boob.greendog.model.Medicine;
import com.boob.greendog.model.Trolley;
import lombok.Data;

/**
 * trolley包装类
 */
@Data
public class TrolleyExp {

    private Trolley trolley;
    private Medicine medicine;

}
