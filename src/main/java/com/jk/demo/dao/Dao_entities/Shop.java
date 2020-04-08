package com.jk.demo.dao.Dao_entities;

public class Shop {
    private String sid;
    private String name;
    private String address;

    public Shop() {
        this.sid = null;
        this.name = null;
        this.address = null;
    }

    public Shop(String sid, String name, String address) {
        this.sid = sid;
        this.name = name;
        this.address = address;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
