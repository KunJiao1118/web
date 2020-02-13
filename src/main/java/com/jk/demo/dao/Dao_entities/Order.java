package com.jk.demo.dao.Dao_entities;

public class Order {
    private String oid; //用户订单唯一id，一个用户订单只有一个id
    private String opid; //用户订单唯一id，后跟物品id，每个order只有一个opid
    private String ordertime;
    private String pid;
    private  String panme;
    private int number;

    public String getPanme() {
        return panme;
    }

    public void setPanme(String panme) {
        this.panme = panme;
    }

    private int totalnumber;
    private String state;
    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
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


    public int getTotalnumber() {
        return totalnumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTotalnumber(int totalnumber) {
        this.totalnumber = totalnumber;
    }



}
