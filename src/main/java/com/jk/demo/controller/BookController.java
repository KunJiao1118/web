package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
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

    @PostMapping("/book/detail")
    @ResponseBody
    public void bookDetail(HttpSession session,@RequestBody int id){
        session.setAttribute("index",id);
    }

    /**
     * 书籍详情页面
     */
    @GetMapping("/bookIndex")
    public String bookIndex(){
        return "book_detail";
    }
}
