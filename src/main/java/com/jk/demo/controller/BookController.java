package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/book/info")
    public ResultBean<Book> findBookInfoById(String shopId,String bookId){
        ResultBean<Book> re=new ResultBean<>();
        Book bookInfoById = bookService.getBookInfoById(shopId, bookId);
        re.setData(bookInfoById);
        return re;
    }
}
