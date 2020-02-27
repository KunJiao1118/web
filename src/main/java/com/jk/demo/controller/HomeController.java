package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.service.BookService;
import com.jk.demo.sto.BookSTO;
import com.jk.demo.sto.RecommendSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BookService bookService;

    @GetMapping("/home/search")
    public ResultBean<BookSTO> search(String content, Integer pageId,String type){
        ResultBean<BookSTO> re=new ResultBean<>();
        List<ShopBook> books = bookService.fuzzySerach(content,type);
        BookSTO bookSTO = new BookSTO();
        bookSTO.setRecords(books);
        re.setData(bookSTO);
        return re;
    }
    /**
    好书推荐，暂时随便推荐12本书，暂时未确定好书标准
     */
    @GetMapping("/home/recommendBooks")
    public String recommendBooks(HttpSession session){
        ResultBean<List<Book>> re=new ResultBean<>();
        List<Book> bookByRecommend = bookService.getBookByRecommend();
        re.setData(bookByRecommend);
        session.setAttribute("lists",re);
        return "redirect:/index";
    }

    /**
     * 首页页面
     */
    @GetMapping("/index")
    public String index(){
        return "first_page";
    }

    /**
     * 入口
     */
    @GetMapping("/home")
    public String home(){
        return "redirect:/home/recommendBooks";
    }

}
