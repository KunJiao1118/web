package com.jk.demo.service;

import com.jk.demo.dao.Dao_entities.BookCategory;
import com.jk.demo.dao.Dao_entities.Comment;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.sto.CommentSTO;

import java.util.List;

public interface ShopService {
    Shop findShopInfo(String shopId);
    List<BookCategory> findShopBookCategory(String sid);
    List<CommentSTO> findShopComment(String sid);
}
