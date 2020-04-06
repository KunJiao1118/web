package com.jk.demo.controller;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    private final static Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    /**
     * 该方法查询某商店的某本书的信息，对应具体某个商品的页面
     * @param shopId 店铺id
     * @param bookId 商品id
     * @return
     */
    @GetMapping("/book/info")
    public String findBookInfoById(Model model, String shopId, String bookId){
        Book bookInfoById = bookService.getBookInfoById(shopId, bookId);

        /////////////////////////////////
        logger.info(bookInfoById.toString());
        model.addAttribute("book",bookInfoById);//该书在shop表中的数据；
        model.addAttribute("shopInfo",null);//该商店的信息
        model.addAttribute("shopBook",null);//该书在shopbook表中的信息
        model.addAttribute("commentList", null);//书店对应的评价信息
        return "item_detail";
    }


    /**
     * 图书条目页面 ，参考 http://item.kongfz.com/book/51172336.html
     * 根据pid，查询该图书条目的信息， 以及所有在shopbook表中存在的信息
     * @param pid  图书条目的id
     */
    @GetMapping("/book/detail")
    public String bookDetail(Model model, int pid){



        model.addAttribute("book",null);//pid对应的图书条目的信息
        model.addAttribute("itemList",null);//所有该图书对应商品的列表
        return "book_detail";
    }

//    /**
//     * 图书条目详情页面
//     */
//    @GetMapping("/bookIndex")
//    public String bookIndex(){
//        return "book_detail";
//    }
}
