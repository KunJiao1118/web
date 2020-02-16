package com.jk.demo.entities;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
    private String orderId;
    private Date orderTime;
    private String orderState;


}
