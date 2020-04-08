package com.jk.demo.dao.Dao_entities;

import lombok.Data;

@Data
public class ShopBook {
    private String pid;
    private String sid;
    private String pname;
    private String image;
    private String category;
    private float price;
    private float express;
    private String quality;
    private int remain;
    private Book bookInfo;
    private Shop shopInfo;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    public ShopBook() {
        this.sid=null;
        this.pid = null;
        this.pname = null;
        this.image = null;
        this.category = null;
        this.price = -1;
        this.express = -1;
        this.quality = null;
        this.remain = -1;
    }
    public ShopBook(String sid,String pid, String pname, String image, String category, float price, float express, String quality, int remain) {
        this.sid=sid;
        this.pid = pid;
        this.pname = pname;
        this.image = image;
        this.category = category;
        this.price = price;
        this.express = express;
        this.quality = quality;
        this.remain = remain;
    }



    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getExpress() {
        return express;
    }

    public void setExpress(float express) {
        this.express = express;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "ShopBook{" +
                "pid='" + pid + '\'' +
                ", sid='" + sid + '\'' +
                ", pname='" + pname + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", express=" + express +
                ", quality='" + quality + '\'' +
                ", remain=" + remain +
                ", bookInfo=" + bookInfo +
                ", shopInfo=" + shopInfo +
                '}';
    }
}
