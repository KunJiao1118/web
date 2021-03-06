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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    BookService bookService;
    @Autowired
    ShopService shopService;

    /**
     * 商店页面
     * @param shopId  店铺id
     * @return
     */
    @GetMapping("/shop/{shopId}")
    public String shopPage(Model model,@PathVariable String shopId){
        Shop shopInfo = shopService.findShopInfo(shopId);
        List<BookCategory> shopBookCategory = shopService.findShopBookCategory(shopId);
        List<ShopBook> bookByRecommendInShop = bookService.getBookByRecommendInShop(shopId);

        model.addAttribute("shopInfo", shopInfo);//商店信息
        model.addAttribute("category", shopBookCategory);//商店书籍分类信息
        model.addAttribute("recommendBooks",bookByRecommendInShop);//本商店推荐书籍信息
        return "shop";
    }

    /**
     * 商店搜索结果页面
     * @param content  搜索内容
     * @param shopId  商店id
     */
    @GetMapping("/shop/search")
    public String searchInShop(Model model, String content,String shopId){
        List<ShopBook> booksByShop = bookService.getBooksByShop(content, shopId);
        Shop shopInfo = shopService.findShopInfo(shopId);

        model.addAttribute("shopInfo", shopInfo);//商店信息
        model.addAttribute("bookList",booksByShop);//搜索结果信息
        return "shop";
    }



    // 该方法转移到了 HomeController 的mainPage()方法位置;
    //@GetMapping("/recommendBooks")
    //public String recommendBooksInShop(String shopId, Model model){
    //    ResultBean<List<ShopBook>> re=new ResultBean<>();
    //    List<ShopBook> bookByRecommendInShop = bookService.getBookByRecommendInShop(shopId);
    //    re.setData(bookByRecommendInShop);
    //    model.addAttribute("lists",re);
    //    return "first_page";
    //}

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
