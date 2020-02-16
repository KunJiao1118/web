package com.jk.demo.service;

import com.jk.demo.dao.Dao_entities.BookCategory;
import com.jk.demo.dao.Dao_entities.Comment;
import com.jk.demo.dao.Dao_entities.Shop;

import java.util.List;

public interface ShopService {
    Shop findShopInfo(String shopId);
    List<BookCategory> findShopBookCategory(String sid);
    List<Comment> findShopComment(String sid);
}
