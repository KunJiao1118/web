package com.jk.demo.dao;

import com.jk.demo.bean.TypeStr;
import com.jk.demo.dao.Dao_entities.*;
import com.jk.demo.dao.dataHelper.jdbc.Builder;
import com.jk.demo.sto.CommentSTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Builder builder = new Builder();
    private ResultSet rs = null;
    static ShopDao shopdata = null;

    /**
     * 返回店铺的书籍分类信息
     */
    public List<BookCategory> findShopBookCategory(String sid){
        try {
            String select = "select category,count(*) from `shopbook` where `sid`=? group by category;";
            List<BookCategory> resultlist = new ArrayList<BookCategory>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            //ps.setString(1,"`"+type+"`");
            ps.setString(1,sid);

            rs = ps.executeQuery();
            while (rs.next()){
                BookCategory bookCategory=new BookCategory();
                bookCategory.setName(rs.getString(1));
                bookCategory.setNum(rs.getString(2));
                resultlist.add(bookCategory);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
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
     * 返回某个店铺的相关推荐
     * 返回店铺中品质为上品的十个种类各一本书
     */
    public List<ShopBook> findBookByRecommendInShop(String sid){
        try {
            String select = "select `shopbook`.*, writer from `shopbook`,`book`" +
                    "where `shopbook`.pid=`book`.pid and `sid`=? and `quality`='上品' group by category limit 10;";
            List<ShopBook> resultlist = new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            //ps.setString(1,"`"+type+"`");
            ps.setString(1,sid);

            rs = ps.executeQuery();
            while (rs.next()){
                ShopBook shopBook=new ShopBook();
                shopBook.setPid(rs.getString(1));
                shopBook.setSid(rs.getString(2));
                shopBook.setPname(rs.getString(3));
                shopBook.setImage(rs.getString(4));
                shopBook.setCategory(rs.getString(5));
                shopBook.setPrice(rs.getFloat(6));
                shopBook.setExpress(rs.getFloat(7));
                shopBook.setQuality(rs.getString(8));
                shopBook.setRemain(rs.getInt(9));
                Book book = new Book();
                book.setWriter(rs.getString(10));
                shopBook.setBookInfo(book);
                resultlist.add(shopBook);
            }
            //如果书籍不足10本，则以缺货图片展示。
            if(resultlist.size()<10){
                ShopBook sb=new ShopBook();
                sb.setSid(sid);
                sb.setImage("static/img/timg.jpg");
                while(resultlist.size()<10){
                    resultlist.add(sb);
                }
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 返回某个店铺内的书籍
     */
    public List<ShopBook> findShopBookInShop(String content,String sid){
        try {
            String select = "select * from `shopbook` where `sid`=? and (`pname` like ? or category like ?);";
            List<ShopBook> resultlist = new ArrayList<ShopBook>();
            content="%"+content+"%";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            //ps.setString(1,"`"+type+"`");
            ps.setString(1,sid);
            ps.setString(2,content);
            ps.setString(3,content);
            rs = ps.executeQuery();
            while (rs.next()){
                ShopBook shopBook=new ShopBook();
                shopBook.setPid(rs.getString(1));
                shopBook.setSid(rs.getString(2));
                shopBook.setPname(rs.getString(3));
                shopBook.setImage(rs.getString(4));
                shopBook.setCategory(rs.getString(5));
                shopBook.setPrice(rs.getFloat(6));
                shopBook.setExpress(rs.getFloat(7));
                shopBook.setQuality(rs.getString(8));
                shopBook.setRemain(rs.getInt(9));
                resultlist.add(shopBook);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 返回书籍与店铺的关联信息(by sid)
     */
    public List<ShopBook> findShopBookBySid(String sid) {
        try {
            String select = "select * from `shopbook` where `sid`=?;";
            List<ShopBook> resultlist = new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            //ps.setString(1,"`"+type+"`");
            ps.setString(1,sid);

            rs = ps.executeQuery();
            while (rs.next()){
                ShopBook shopBook=new ShopBook();
                shopBook.setPid(rs.getString(1));
                shopBook.setSid(rs.getString(2));
                shopBook.setPname(rs.getString(3));
                shopBook.setImage(rs.getString(4));
                shopBook.setCategory(rs.getString(5));
                shopBook.setPrice(rs.getFloat(6));
                shopBook.setExpress(rs.getFloat(7));
                shopBook.setQuality(rs.getString(8));
                shopBook.setRemain(rs.getInt(9));
                resultlist.add(shopBook);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回书籍与店铺的关联信息(by 书籍名字)
     */
    public List<ShopBook> findShopBookByType(String content, String Type){
        try {
            String select;
            switch (Type){
                case TypeStr.WRITER:
                    select = "select `shopbook`.*,`book`.*, `shop`.* from `shopbook`,`book`,`shop`" +
                            "where `shopbook`.pid = `book`.pid and `shopbook`.sid = `shop`.sid and `writer` like ?";
                    break;
                case TypeStr.ITEM:
                    select = "select `shopbook`.*,`book`.*, `shop`.* from `shopbook`,`book`,`shop`" +
                            "where `shopbook`.pid = `book`.pid and `shopbook`.sid = `shop`.sid and `pname` like ?";
                    break;
                case TypeStr.PRESS:
                    select = "select `shopbook`.*,`book`.*, `shop`.* from `shopbook`,`book`,`shop`" +
                            "where `shopbook`.pid = `book`.pid and `shopbook`.sid = `shop`.sid and `press` like ?";
                    break;
                default:
                    throw new IllegalArgumentException("未知搜索类型");
            }
            List<ShopBook> resultlist = new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1,"%"+content+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                ShopBook shopBook=new ShopBook();
                shopBook.setPid(rs.getString(1));
                shopBook.setSid(rs.getString(2));
                shopBook.setPname(rs.getString(3));
                shopBook.setImage(rs.getString(4));
                shopBook.setCategory(rs.getString(5));
                shopBook.setPrice(rs.getFloat(6));
                shopBook.setExpress(rs.getFloat(7));
                shopBook.setQuality(rs.getString(8));
                shopBook.setRemain(rs.getInt(9));
                Book book = new Book();
                book.setPid(rs.getString(10));
                book.setName(rs.getString(11));
                book.setImage(rs.getString(12));
                book.setWriter(rs.getString(13));
                book.setPress(rs.getString(14));
                book.setTime(rs.getString(15));
                book.setISBN(rs.getString(16));
                book.setPage(rs.getString(17));
                book.setCategory(rs.getString(18));
                book.setIntro(rs.getString(19));
                shopBook.setBookInfo(book);
                Shop shop = new Shop();
                shop.setSid(rs.getString(20));
                shop.setName(rs.getString(21));
                shop.setAddress(rs.getString(22));
                shopBook.setShopInfo(shop);
                resultlist.add(shopBook);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回书籍与店铺的关联信息(by 书籍类型)
     */
    public List<ShopBook> findShopBookByCategory(String content){
        try {
            String select = "select * from `shopbook` where `category`=?;";
            List<ShopBook> resultlist = new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1,"%"+content+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                ShopBook shopBook=new ShopBook();
                shopBook.setPid(rs.getString(1));
                shopBook.setSid(rs.getString(2));
                shopBook.setPname(rs.getString(3));
                shopBook.setImage(rs.getString(4));
                shopBook.setCategory(rs.getString(5));
                shopBook.setPrice(rs.getFloat(6));
                shopBook.setExpress(rs.getFloat(7));
                shopBook.setQuality(rs.getString(8));
                shopBook.setRemain(rs.getInt(9));
                resultlist.add(shopBook);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
     * 添加评论
     */
    public boolean addShopComment(Comment comment) {
        try {
            String select = "select * from `comment`;";
            String insert = "insert into comment (sid,oid,comment) values(?,?,?);";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            rs = ps.executeQuery();
            String lastid="";
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                lastid=rs.getString(2);
                if (rs.getString(1).equals(comment.getSid())&&
                        rs.getString(2).equals(comment.getOid())) {
                    rs.close();
                    break;
                }
            }
            int id=(lastid!="")?Integer.parseInt(lastid):1;
            id+=1;
            rs.close();
            ps = conn.prepareStatement(insert);
            ps.setString(1, comment.getSid());
            ps.setString(2, String.valueOf(id));
            ps.setString(3, comment.getComment());
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
    public ArrayList<CommentSTO> findCommentByShop(String sid){
        try {
            String select = "select comment.sid,comment.oid,comment.comment,order.pname,order.ordertime from `comment`,`order` where comment.sid=order.sid " +
                    "and comment.oid=order.oid and comment.sid=?;";
            ArrayList<CommentSTO> resultlist=new ArrayList<CommentSTO>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                CommentSTO comment=new CommentSTO();
                comment.setSid(rs.getString(1));
                comment.setOid(rs.getString(2));
                comment.setComment(rs.getString(3));
                comment.setPname(rs.getString(4));
                comment.setOrdertime(rs.getString(5));
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

    /**
     * 返回店铺信息
     */
    public Shop findShopInfo(String sid){
        try {
            String select = "select * from `shop` where `sid`=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, sid);
            rs = ps.executeQuery();
            while (rs.next()){

                    Shop shop = new Shop();
                    shop.setSid(sid);
                    shop.setName(rs.getString(2));
                    shop.setAddress(rs.getString(3));
                    return shop;


            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
