package com.jk.demo.service.impl;

import com.jk.demo.dao.BookDao;
import com.jk.demo.dao.Dao_entities.*;
import com.jk.demo.dao.OrderDao;
import com.jk.demo.dao.ShopDao;
import com.jk.demo.dao.UserDao;
import com.jk.demo.service.EpayService;
import com.jk.demo.sto.OrderSTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EpayServiceImpl implements EpayService {
    OrderDao orderDao=OrderDao.getInstance();
    ShopDao shopDao=ShopDao.getInstance();
    BookDao bookDao=BookDao.getInstance();
    UserDao userDao=UserDao.getInstance();
    @Override
    public OrderSTO generateOrder(String uid, String sid, String pid) {
        //shopDao
        Shop shop = shopDao.findShopInfo(sid);
        Book book = bookDao.findBookById(pid, sid);
        User user = userDao.findUserByName(uid);
        OrderSTO orderSTO=new OrderSTO();
        String uuid= UUID.randomUUID().toString();
        orderSTO.setOid(uuid);
        orderSTO.setSname(shop.getName());
        orderSTO.setSid(sid);
        orderSTO.setPrice("");//
        orderSTO.setNumber("");//自己填写？？
        orderSTO.setState("未支付");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        orderSTO.setOrdertime(str);
        orderSTO.setUsername(user.getUsername());
        orderSTO.setBook(book);
        Order order=new Order();
        order.setNumber(1);//补上
        order.setOid(uuid);
        order.setOrdertime(str);
        order.setPanme(book.getName());
        order.setPid(pid);
        order.setState("未支付");
        order.setPrice(1);//------------------

        orderDao.addDetailOrder(order);
        Userorder userorder=new Userorder();
        userorder.setOid(uuid);
        userorder.setOrdertime(str);
        userorder.setState("未支付");
        userorder.setUsername(user.getUsername());
        orderDao.addUserOrder(userorder);
        return orderSTO;
    }
}
