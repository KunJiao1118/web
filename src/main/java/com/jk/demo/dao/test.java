package com.jk.demo.dao;

import com.jk.demo.dao.Dao_entities.*;

import java.util.ArrayList;

public class test {
    public  static  void main(String args[]){
        UserDao ud= UserDao.getInstance();
        BookDao bd=BookDao.getInstance();
        OrderDao od=OrderDao.getInstance();
        User user=new User("xia","123","8888111@qq.com");
        float price=10;
        float express=10;
        Userorder userorder=new Userorder("123133","xia","B","2020-02-06 19:22:22");
        Order order= new Order("123133","2020-02-06 19:22:22","0000000","足球训练：荷兰足球协会青少年足球训练指定教材",1,price,"A");


//        ArrayList<Book> books=bd.findBookByCategory("体育");

//        bd.addBookToShop("0001089","1",price,express,"上品",8);
        System.out.println(od.findDetailOrder("123133").get(0).getPanme());
    }

}
