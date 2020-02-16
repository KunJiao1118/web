package com.jk.demo.entities;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String bookName;
    private String author;
    private String type;
    private String price;
    private Integer evaluate;//评分
}
