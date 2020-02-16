package com.jk.demo.service.impl;

import com.jk.demo.dao.Dao_entities.BookCategory;
import com.jk.demo.dao.Dao_entities.Comment;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.dao.ShopDao;
import com.jk.demo.service.ShopService;
import com.jk.demo.sto.CommentSTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Override
    public List<CommentSTO> findShopComment(String sid) {
        ArrayList<CommentSTO> commentByShop = shopDao.findCommentByShop(sid);

        return commentByShop;
    }

    ShopDao shopDao=ShopDao.getInstance();

    @Override
    public List<BookCategory> findShopBookCategory(String sid) {
        List<BookCategory> shopBookCategory = shopDao.findShopBookCategory(sid);
        return shopBookCategory;
    }

    @Override
    public Shop findShopInfo(String shopId) {
        Shop shopInfo = shopDao.findShopInfo(shopId);
        return shopInfo;
    }

}
