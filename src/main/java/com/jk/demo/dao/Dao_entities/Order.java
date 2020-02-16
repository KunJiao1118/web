package com.jk.demo.dao.Dao_entities;

public class Order {
    private String oid; //用户订单唯一id，一个用户订单只有一个id
    private String ordertime;
    private String sid;
    private String sname;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    private String pid;
    private  String pname;
    private int number;
    private float price;
    private String state;
    public Order(String oid, String ordertime, String pid, String pname,String sid,String sname ,int number, float price, String state) {
        this.oid = oid;
        this.ordertime = ordertime;
        this.pid = pid;
        this.pname = pname;
        this.number = number;
        this.price = price;
        this.state = state;
        this.sid=sid;
        this.sname=sname;
    }
    public Order() {
        this.oid = null;
        this.ordertime = null;
        this.pid = null;
        this.pname = null;
        this.number = -1;
        this.price = -1;
        this.state = null;
    }
    public String getPanme() {
        return pname;
    }

    public void setPanme(String panme) {
        this.pname = panme;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public float getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPrice(float price) {
        this.price = price;
    }



}
