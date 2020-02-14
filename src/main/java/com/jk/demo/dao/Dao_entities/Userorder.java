package com.jk.demo.dao.Dao_entities;

public class Userorder {
    private String oid;//oid=ordertime+一个随机数字？
    private String username;
    private String ordertime;
    private String state;
    public String getOrdertime() {
        return ordertime;
    }

    public  Userorder(){
        this.oid=null;
        this.username=null;
        this.state=null;
        this.ordertime=null;
    }
    public  Userorder(String oid,String username,String state,String ordertime){
        this.oid=oid;
        this.username=username;
        this.state=state;
        this.ordertime=ordertime;
    }
    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
