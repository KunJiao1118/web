package com.jk.demo.sto;

import com.jk.demo.dao.Dao_entities.Book;
import lombok.Data;

@Data
public class OrderSTO {
    private String oid;
    private String username;
    private String ordertime;
    private String number;
    private String price;
    private String state;
    private String sname;
    private String sid;
    private Book book;
}
