package com.jk.demo.dao;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.ShopBook;
import com.jk.demo.dao.Dao_entities.Userorder;
import com.jk.demo.dao.dataHelper.jdbc.Builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static BookDao bookDao = null;
    private Builder builder = new Builder();
    public static BookDao getInstance() {
        if (bookDao == null) {
            bookDao = new BookDao();
            return bookDao;
        }
        return bookDao;
    }

    /**
     * 返回推荐的所有书籍
     * (暂时先随便推荐十本书)
     */
    public ArrayList<Book> findBookByRecommend(){
        try {
            String select = "select * from book group by category limit 1,12 ;";
            ArrayList<Book> resultlist=new ArrayList<Book>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            //ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Book book=new Book();
                book.setPid(rs.getString(1));
                book.setName(rs.getString(2));
                book.setImage(rs.getString(3));
                book.setWriter(rs.getString(4));
                book.setPress(rs.getString(5));
                book.setTime(rs.getString(6));
                book.setISBN(rs.getString(7));
                book.setPage(rs.getString(8));
                book.setCategory(rs.getString(9));
                book.setIntro(rs.getString(10));
                resultlist.add(book);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回某一类别的所有书籍
     * @param category
     * @return
     */
    public ArrayList<Book> findBookByCategory(String category){
        try {
            String select = "select * from `book` where `category`=?;";
            ArrayList<Book> resultlist=new ArrayList<Book>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Book book=new Book();
                book.setPid(rs.getString(1));
                book.setName(rs.getString(2));
                book.setImage(rs.getString(3));
                book.setWriter(rs.getString(4));
                book.setPress(rs.getString(5));
                book.setTime(rs.getString(6));
                book.setISBN(rs.getString(7));
                book.setPage(rs.getString(8));
                book.setCategory(rs.getString(9));
                book.setIntro(rs.getString(10));
                resultlist.add(book);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 返回指定sid,pid的书籍信息
     * @param pid
     * @return
     */
    public Book findBookById(String pid) {
        try {
            String select = "select * from `book` as b where  b.pid=? ";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, pid);
            //ps.setString(2,sid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Book book = new Book();
                book.setPid(rs.getString(1));
                book.setName(rs.getString(2));
                book.setImage(rs.getString(3));
                book.setWriter(rs.getString(4));
                book.setPress(rs.getString(5));
                book.setTime(rs.getString(6));
                book.setISBN(rs.getString(7));
                book.setPage(rs.getString(8));
                book.setCategory(rs.getString(9));
                book.setIntro(rs.getString(10));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return  null;
    }

    /**
     * 返回指定name的书籍信息
     * @param name
     * @return
     */
    public Book findBookByName(String name) {
        try {
            String select = "select * from `book` where `name`=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Book book = new Book();
                book.setPid(rs.getString(1));
                book.setName(rs.getString(2));
                book.setImage(rs.getString(3));
                book.setWriter(rs.getString(4));
                book.setPress(rs.getString(5));
                book.setTime(rs.getString(6));
                book.setISBN(rs.getString(7));
                book.setPage(rs.getString(8));
                book.setCategory(rs.getString(9));
                book.setIntro(rs.getString(10));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return  null;
    }

    /**
     * 返回指定店铺的书籍信息
     * @param shopid
     * @return
     */
    public ArrayList<ShopBook> findBookByShopId(String shopid){
        try {
            String select = "select * from `shopbook` where `sid`=?;";
            ArrayList<ShopBook> resultlist=new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, shopid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                ShopBook book=new ShopBook();
                book.setPid(rs.getString(1));
                book.setSid(rs.getString(2));
                book.setPname(rs.getString(3));
                book.setImage(rs.getString(4));
                book.setCategory(rs.getString(5));
                book.setPrice(rs.getFloat(6));
                book.setExpress(rs.getFloat(7));
                book.setQuality(rs.getString(8));
                book.setRemain(rs.getInt(9));
                resultlist.add(book);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }



    /**
     * 把某本书籍的购买信息添加到店铺种
     * @param pid 书籍id
     * @param sid 店铺id
     * @param price 店铺设定的价格
     * @param express 运费
     * @param quality 品相
     * @param remain 剩余数量
     * @return
     */
    public boolean addBookToShop(String pid,String sid,Float price,Float express,String quality,int remain){
        Book book=this.findBookById(pid);
        ShopBook sbook=new ShopBook();
        sbook.setPid(book.getPid());
        sbook.setSid(sid);
        sbook.setPname(book.getName());
        sbook.setImage(book.getImage());
        sbook.setCategory(book.getCategory());
        sbook.setPrice(price);
        sbook.setExpress(express);
        sbook.setQuality(quality);
        sbook.setRemain(remain);
        try {
            String insert = "insert into `shopbook` (pid,sid,pname,image,category,price,express,quality,remain) values(?,?,?,?,?,?,?,?,?);";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(insert);
            ps.setString(1, sbook.getPid());
            ps.setString(2, sbook.getSid());
            ps.setString(3, sbook.getPname());
            ps.setString(4, sbook.getImage());
            ps.setString(5, sbook.getCategory());
            ps.setFloat(6, sbook.getPrice());
            ps.setFloat(7, sbook.getExpress());
            ps.setString(8, sbook.getQuality());
            ps.setInt(9, sbook.getRemain());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ShopBook findShopBookById(String sid,String pid) {
        try {
            String select = "select * from `shopbook` where `sid`=? and `pid`=?;";
            //ArrayList<ShopBook> resultlist=new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, sid);
            ps.setString(2,pid);
            rs = ps.executeQuery();
            rs.next();// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                ShopBook book=new ShopBook();
                book.setPid(rs.getString(1));
                book.setSid(rs.getString(2));
                book.setPname(rs.getString(3));
                book.setImage(rs.getString(4));
                book.setCategory(rs.getString(5));
                book.setPrice(rs.getFloat(6));
                book.setExpress(rs.getFloat(7));
                book.setQuality(rs.getString(8));
                book.setRemain(rs.getInt(9));
                return book;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //return null;

    }

    public ArrayList<ShopBook> findAllShopBooks(String bookName) {
        try {
            String select = "select * from `shopbook` where `pname`=? ;";
            ArrayList<ShopBook> resultlist=new ArrayList<ShopBook>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, bookName);
            //ps.setString(2,isbn);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                ShopBook book=new ShopBook();
                book.setPid(rs.getString(1));
                book.setSid(rs.getString(2));
                book.setPname(rs.getString(3));
                book.setImage(rs.getString(4));
                book.setCategory(rs.getString(5));
                book.setPrice(rs.getFloat(6));
                book.setExpress(rs.getFloat(7));
                book.setQuality(rs.getString(8));
                book.setRemain(rs.getInt(9));
                resultlist.add(book);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
