package com.jk.demo.service;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.entities.Type;

import java.util.List;

public interface BookService {
    List<Type> getTypes();

    List<Book> getGoodbooks();

    /**
     * 首页全网搜索
     *
     * @param content 搜索内容
     * @param type    搜索类型
     * @param pageId  分页 页数
     * @param quality 品相
     */
    List<ShopBook> fuzzySearch(String content, String type, Integer pageId, String quality);

    //首页好书推荐
    List<Book> getBookByRecommend();

    /**
     * 商店搜索
     *
     * @param content 搜索内容
     * @param shopId  商店id
     */
    List<ShopBook> getBooksByShop(String content, String shopId);

    //获取商店 推荐书籍
    List<ShopBook> getBookByRecommendInShop(String sid);

    //根据pid获取图书条目信息
    Book getBookInfoById(String pid);

    /**
     * 获取商品信息
     *
     * @param sid 商店id
     * @param pid 商品id
     */
    ShopBook getShopBookInfoById(String sid, String pid);

    /**
     * 获取全网有bookId这本书 的商店的商品信息
     *
     * @param bookId 图书的bookId
     */
    List<ShopBook> getAllShopBooks(String bookId);
    List<Book> getAllBookByRecommend();
    List<Book> getBookByRecommend(String start,String count);
}
