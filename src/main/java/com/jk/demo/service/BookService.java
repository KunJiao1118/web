package com.jk.demo.service;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.entities.Type;

import java.util.List;

public interface BookService {
    List<Type> getTypes();
    List<Book> getGoodbooks();
    List<ShopBook> fuzzySerach(String content, String type);
    List<Book> getBookByRecommend();
    List<ShopBook> getBooksByShop(String content,String shopId);
    List<ShopBook> getBookByRecommendInShop(String sid);
    Book getBookInfoById(String pid);
    ShopBook getShopBookInfoById(String sid,String pid);
    List<ShopBook> getAllShopBooks(String bookId);
    List<Book> getAllBookByRecommend();
    List<Book> getBookByRecommend(String start,String count);
}
