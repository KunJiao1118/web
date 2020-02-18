package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.BookCategory;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.service.BookService;
import com.jk.demo.service.ShopService;
import com.jk.demo.sto.CommentSTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    BookService bookService;
    @Autowired
    ShopService shopService;
    @GetMapping("/shop/search")
    public ResultBean<List<ShopBook>> searchInShop(String content,String shopId){
        ResultBean<List<ShopBook>> re=new ResultBean<>();
        List<ShopBook> booksByShop = bookService.getBooksByShop(content, shopId);
        re.setData(booksByShop);
        return re;
    }


    @GetMapping("/recommendBooks")
    public String recommendBooksInShop(String shopId, Model model){
        ResultBean<List<ShopBook>> re=new ResultBean<>();
        List<ShopBook> bookByRecommendInShop = bookService.getBookByRecommendInShop(shopId);
        re.setData(bookByRecommendInShop);
        model.addAttribute("lists",re);
        return "first_page";
    }

    @GetMapping("/shop/info")
    public ResultBean<Shop> findShopInfo(String shopId){
        Shop shopInfo = shopService.findShopInfo(shopId);
        ResultBean<Shop> re=new ResultBean<>();
        re.setData(shopInfo);
        return re;
    }
    @GetMapping("/shop/category")
    public ResultBean<List<BookCategory>> findShopBookCategory(String shopId){
        ResultBean<List<BookCategory>> re=new ResultBean();
        List<BookCategory> shopBookCategory = shopService.findShopBookCategory(shopId);
        re.setData(shopBookCategory);
        return re;
    }
    @GetMapping("/shop/comment")
    public ResultBean<List<CommentSTO>> findShopComment(String shopId){
        ResultBean<List<CommentSTO>> re=new ResultBean<>();
        List<CommentSTO> shopComment = shopService.findShopComment(shopId);
        re.setData(shopComment);
        return re;
    }
}
