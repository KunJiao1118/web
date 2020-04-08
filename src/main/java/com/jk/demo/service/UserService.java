package com.jk.demo.service;

import com.jk.demo.entities.User;
import com.jk.demo.sto.OrderSTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
     User getByName(String username);
     boolean doRegister(User user);
     boolean doLogin(String userId,String password);
     List<OrderSTO> getUserOrder(String userId);
     List<OrderSTO> getUserOrderByState(String userId,String state);
     List<OrderSTO> getUserOrder(String userId,String start,String count);
}
