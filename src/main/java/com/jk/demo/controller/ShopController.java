package com.jk.demo.controller;

import com.jk.demo.bean.ResultBean;
import com.jk.demo.dao.Dao_entities.BookCategory;
import com.jk.demo.dao.Dao_entities.Comment;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.service.BookService;
import com.jk.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @GetMapping("/shop/recommendBooks")
    public ResultBean<List<ShopBook>> recommendBooksInShop(String shopId){
        ResultBean<List<ShopBook>> re=new ResultBean<>();
        List<ShopBook> bookByRecommendInShop = bookService.getBookByRecommendInShop(shopId);
        re.setData(bookByRecommendInShop);
        return re;
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
    public ResultBean<List<Comment>> findShopComment(String shopId){
        ResultBean<List<Comment>> re=new ResultBean<>();
        List<Comment> shopComment = shopService.findShopComment(shopId);
        re.setData(shopComment);
        return re;
    }
}
