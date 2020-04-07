package com.jk.demo.service.impl;

import com.jk.demo.bean.TypeEnum;
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
    BookDao bookDao =BookDao.getInstance();
    ShopDao shopDao = ShopDao.getInstance();

    @Override
    public ShopBook getShopBookInfoById(String sid, String pid) {
        ShopBook shopBookById = bookDao.findShopBookById(sid, pid);
        return shopBookById;
    }

    @Override
    public List<ShopBook> getAllShopBooks(String bookId) {
        Book bookById = bookDao.findBookById(bookId);
        ArrayList<ShopBook> allShopBooks = bookDao.findAllShopBooks(bookById.getName());
        return allShopBooks;
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

    public List<Type> getTypes(){
        //List<Type> list=typeMapper.getTypes();
        //假数据
        List<Type> list=new ArrayList<>();
        Type t1=new Type();
        t1.setType("小说");
        list.add(t1);
        return list;
    }

    public List<Book> getGoodbooks() {
        //
        List<Book> list=new ArrayList<>();
        return list;
    }

    @Override
    public List<Book> getBookByRecommend() {
       // BookDao bookDao =BookDao.getInstance();
        ArrayList<Book> bookByRecommend = bookDao.findBookByRecommend();
        return bookByRecommend;
    }

    public List<ShopBook> fuzzySerach(String content, String type){
        //List<Book> list=bookMapper.fuzzySerach();
        //BookDao bookDao =BookDao.getInstance();

        List<ShopBook> list=new ArrayList<>();
        if(type.equals(TypeEnum.SHOP.getType())){//
            list=shopDao.findShopBookBySid(content);
        }else if(type.equals(TypeEnum.ITEM.getType())){
            list=shopDao.findShopBookByPname(content);
        }else if(type.equals(TypeEnum.BOOK.getType())){
            list=shopDao.findShopBookByCategory(content);
        }

        //list=bookDao.findBookByCategory("体育");
        //
        return list;
    }
}
