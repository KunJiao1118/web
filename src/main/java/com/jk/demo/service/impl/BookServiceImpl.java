package com.jk.demo.service.impl;

import com.jk.demo.bean.TypeStr;
import com.jk.demo.dao.BookDao;
import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.dao.ShopDao;
import com.jk.demo.entities.Type;
import com.jk.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    BookDao bookDao = BookDao.getInstance();
    ShopDao shopDao = ShopDao.getInstance();

    @Override
    public ShopBook getShopBookInfoById(String sid, String pid) {
        ShopBook shopBookById = bookDao.findShopBookById(sid, pid);
        return shopBookById;
    }

    @Override
    public List<ShopBook> getAllShopBooks(String bookId) {
        Book bookById = bookDao.findBookById(bookId);
        return bookDao.findAllShopBooks(bookById.getName());
    }

    public List<Book> getAllBookByRecommend() {
        // BookDao bookDao =BookDao.getInstance();
        ArrayList<Book> bookByRecommend = bookDao.findAllBookByRecommend();
        return bookByRecommend;
    }
    public List<Book> getBookByRecommend(String start,String count) {
        // BookDao bookDao =BookDao.getInstance();
        ArrayList<Book> bookByRecommend = bookDao.findBookByRecommend(start,count);
        return bookByRecommend;
    }

    @Override
    public Book getBookInfoById(String pid) {
        Book bookById = bookDao.findBookById(pid);
        return bookById;
    }

    @Override
    public List<ShopBook> getBookByRecommendInShop(String sid) {
        List<ShopBook> bookByRecommendInShop = shopDao.findBookByRecommendInShop(sid);
        return bookByRecommendInShop;
    }

    @Override
    public List<ShopBook> getBooksByShop(String content, String shopId) {
        List<ShopBook> shopBookInShop = shopDao.findShopBookInShop(content, shopId);
        return shopBookInShop;
    }

    public List<Type> getTypes() {
        //List<TypeStr> list=typeMapper.getTypes();
        //假数据
        List<Type> list = new ArrayList<>();
        Type t1 = new Type();
        t1.setType("小说");
        list.add(t1);
        return list;
    }

    public List<Book> getGoodbooks() {
        List<Book> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<Book> getBookByRecommend() {
        // BookDao bookDao =BookDao.getInstance();
        ArrayList<Book> bookByRecommend = bookDao.findBookByRecommend();
        return bookByRecommend;
    }

    public List<ShopBook> fuzzySearch(String content, String type, Integer pageId, String quality) {
        List<ShopBook> list = new ArrayList<>();
        if (quality == null) {
            switch (type) {
                case TypeStr.BOOK:
                    List<Book> books = bookDao.findBookByName(content);
                    for (Book book:books) {
                        ShopBook shopBook = new ShopBook();
                        shopBook.setBookInfo(book);
                        list.add(shopBook);
                    }
                    break;
                case TypeStr.ITEM:
                case TypeStr.WRITER:
                case TypeStr.PRESS:
                    list = shopDao.findShopBookByType(content,type);
                    break;
            }
        } else {
            // TODO: 2020/4/8 品相搜索
            list = shopDao.findShopBookByType(content,TypeStr.ITEM);
        }
        return list;
    }
}
