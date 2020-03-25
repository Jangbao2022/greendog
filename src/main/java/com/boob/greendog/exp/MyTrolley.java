package com.boob.greendog.exp;

import lombok.Data;

import java.util.List;

/**
 * 我的购物车
 */
@Data
public class MyTrolley {

    private List<TrolleyExp> trolleyExps;
    private float price;

}
