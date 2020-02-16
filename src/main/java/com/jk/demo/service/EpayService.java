package com.jk.demo.service;

import com.jk.demo.sto.OrderSTO;

import java.util.List;

public interface EpayService {
    OrderSTO generateOrder(String uid,String sid,String pid);
}
