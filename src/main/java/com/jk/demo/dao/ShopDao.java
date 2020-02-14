package com.jk.demo.dao;

import com.jk.demo.dao.Dao_entities.Comment;
import com.jk.demo.dao.Dao_entities.Shop;
import com.jk.demo.dao.Dao_entities.User;
import com.jk.demo.dao.dataHelper.jdbc.Builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Builder builder = new Builder();
    private ResultSet rs = null;
    static ShopDao shopdata = null;

    /**
     * 返回唯一的Userdata对象
     *
     * @return Userdata
     * @author xiamutian
     */
    public static ShopDao getInstance() {
        if (shopdata == null)
            shopdata = new ShopDao();
        return shopdata;
    }

    /**
     * 创建店铺
     * @param shop
     * @return
     */
    public boolean addShop(Shop shop) {
        try {
            String select = "select * from `shop`;";
            String insert = "insert into shop (sid,name,address) values(?,?,?);";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            rs = ps.executeQuery();
            String lastid="";
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                lastid=rs.getString(1);
                if (rs.getString(2).equals(shop.getName())) {
                    rs.close();
                    return false;
                }
            }
            int id=Integer.parseInt(lastid);
            id+=1;
            rs.close();
            ps = conn.prepareStatement(insert);
            ps.setString(1, String.valueOf(id));
            ps.setString(2, shop.getName());
            ps.setString(3, shop.getAddress());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 返回一个店铺所有的评价
     * @param sid
     * @return
     */
    public ArrayList<Comment> findCommentByShop(String sid){
        try {
            String select = "select * from `comment` where `sid`=?;";
            ArrayList<Comment> resultlist=new ArrayList<Comment>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Comment comment=new Comment();
                comment.setSid(rs.getString(1));
                comment.setOid(rs.getString(2));
                comment.setComment(rs.getString(3));
                resultlist.add(comment);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comment findCommentByOid(String oid){
        try {
            String select = "select * from `comment` where `oid`=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Comment comment=new Comment();
                comment.setSid(rs.getString(1));
                comment.setOid(rs.getString(2));
                comment.setComment(rs.getString(3));
                return  comment;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
