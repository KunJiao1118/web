package com.jk.demo.controller;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.service.BookService;
import com.jk.demo.service.EpayService;
import com.jk.demo.service.ShopService;
import com.jk.demo.sto.CommentSTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {
    private final static Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @Autowired
    ShopService shopService;

    /**
     * 该方法查询某商店的某本书的信息，对应具体某个商品的页面
     *
     * @param shopId 店铺id
     * @param bookId 商品id
     * @return
     */
    @GetMapping("/book/info/{shopId}/{bookId}")
    public String findBookInfoById(Model model, @PathVariable String shopId, @PathVariable String bookId) {
        Book bookInfoById = bookService.getBookInfoById(bookId);
        Shop shopInfo = shopService.findShopInfo(shopId);
        ShopBook shopBookInfoById = bookService.getShopBookInfoById(shopId, bookId);
        List<CommentSTO> shopComment = shopService.findShopComment(shopId);

        /////////////////////////////////
        logger.info(bookInfoById.toString());
        model.addAttribute("book", bookInfoById);//该书在shop表中的数据；
        model.addAttribute("shopInfo", shopInfo);//该商店的信息
        model.addAttribute("shopBook", shopBookInfoById);//该书在shopbook表中的信息
        model.addAttribute("commentList", shopComment);//书店对应的评价信息
        return "item_detail";
    }


    /**
     * 图书条目页面 ，参考 http://item.kongfz.com/book/51172336.html
     * 根据pid，查询该图书条目的信息， 以及所有在shopbook表中存在的信息
     *
     * @param bookId 图书条目的id
     */
    @GetMapping("/book/detail/{bookId}")
    public String bookDetail(Model model, @PathVariable String bookId) {
        Book bookInfoById = bookService.getBookInfoById(bookId);
        List<ShopBook> allShopBooks = bookService.getAllShopBooks(bookId);

        if (bookInfoById == null) {
            model.addAttribute("errorMsg", "图书条目不存在");
            return "error";
        }
        model.addAttribute("book", bookInfoById);//pid对应的图书条目的信息
        model.addAttribute("itemList", allShopBooks);//所有该图书对应商品的列表
        return "book_detail";
    }
}
