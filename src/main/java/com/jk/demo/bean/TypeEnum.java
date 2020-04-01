package com.jk.demo.bean;

/**
 * @author billxkk
 * @date 2020/4/1 15:58
 * 搜索关键字的类型
 */
public enum TypeEnum {
    ITEM("商品"),
    SHOP("店铺"),
    BOOK("图书条目");

    private final String type;

    private TypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
