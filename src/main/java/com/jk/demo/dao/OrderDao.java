package com.jk.demo.dao;

import com.jk.demo.dao.Dao_entities.Book;
import com.jk.demo.dao.Dao_entities.Order;
import com.jk.demo.dao.Dao_entities.Userorder;
import com.jk.demo.dao.dataHelper.jdbc.Builder;
import com.jk.demo.sto.OrderSTO;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static OrderDao orderDao = null;
    private Builder builder = new Builder();


    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
            return orderDao;
        }
        return orderDao;
    }

    /**
     * 增加一个订单
     * @param order
     * @return
     */
    public boolean addUserOrder(Userorder order) {
        try {
            String insert = "insert into `userorder` (oid,username,ordertime,state) values(?,?,?,?);";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(insert);
            ps.setString(1, order.getOid());
            ps.setString(2, order.getUsername());
            ps.setString(3, order.getOrdertime());
            ps.setString(4, order.getState());
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
     * 删除一个订单
     * @param oid
     * @return
     */
    public boolean delUserOrder(String oid) {
        try {
            String delete = "delete from `userorder` where `oid`=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(delete);
            ps.setString(1, oid);
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
     * 返回符合订单状态状态的订单
     * @param username
     * @param state
     * @return
     */
    public ArrayList<Userorder> findUserordersByState(String username,String state){
        try {
            String select = "select * from `userorder` where `username`=? AND `state`=?;";
            ArrayList<Userorder> resultlist=new ArrayList<Userorder>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, username);
            ps.setString(2, state);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Userorder userorder=new Userorder();
                userorder.setOid(rs.getString(1));
                userorder.setUsername(rs.getString(2));
                userorder.setOrdertime(rs.getString(3));
                userorder.setState(rs.getString(4));
                resultlist.add(userorder);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回用户的所有订单
     * @param username
     * @return
     */
    public ArrayList<OrderSTO> findAllsUserorders(String username){
        try {
            String select = "select * from `userorder`,`order`,`book` where `username`=? " +
                    "and userorder.oid=order.oid and order.pid=book.pid;";
            ArrayList<OrderSTO> resultlist=new ArrayList<OrderSTO>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                OrderSTO orderSTO=new OrderSTO();
                Book book=new Book();
                orderSTO.setOid(rs.getString(1));
                orderSTO.setUsername(rs.getString(2));
                orderSTO.setOrdertime(rs.getString(3));
                orderSTO.setState(rs.getString(4));
                orderSTO.setNumber(rs.getString(9));
                orderSTO.setPrice(rs.getString(10));
                orderSTO.setSid(rs.getString(12));
                orderSTO.setSname(rs.getString(13));
                book.setPid(rs.getString(14));
                book.setName(rs.getString(15));
                book.setImage(rs.getString(16));
                book.setWriter(rs.getString(17));
                book.setPress(rs.getString(18));
                book.setTime(rs.getString(19));
                book.setISBN(rs.getString(20));
                book.setPage(rs.getString(21));
                book.setCategory(rs.getString(22));
                book.setIntro(rs.getString(23));
                orderSTO.setBook(book);
                resultlist.add(orderSTO);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回符合要求的用户的所有订单
     * @param username
     * @return
     */
    public ArrayList<OrderSTO> findUserOrdersByState(String username,String state){
        try {
            String select = "select * from `userorder`,`order`,`book` where `username`=? and userorder.state=? " +
                    "and userorder.oid=order.oid and order.pid=book.pid ;";
            ArrayList<OrderSTO> resultlist=new ArrayList<OrderSTO>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, username);
            ps.setString(2, state);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                OrderSTO orderSTO=new OrderSTO();
                Book book=new Book();
                orderSTO.setOid(rs.getString(1));
                orderSTO.setUsername(rs.getString(2));
                orderSTO.setOrdertime(rs.getString(3));
                orderSTO.setState(rs.getString(4));
                orderSTO.setNumber(rs.getString(9));
                orderSTO.setPrice(rs.getString(10));
                orderSTO.setSid(rs.getString(12));
                orderSTO.setSname(rs.getString(13));
                book.setPid(rs.getString(14));
                book.setName(rs.getString(15));
                book.setImage(rs.getString(16));
                book.setWriter(rs.getString(17));
                book.setPress(rs.getString(18));
                book.setTime(rs.getString(19));
                book.setISBN(rs.getString(20));
                book.setPage(rs.getString(21));
                book.setCategory(rs.getString(22));
                book.setIntro(rs.getString(23));
                orderSTO.setBook(book);
                resultlist.add(orderSTO);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回用户的所有订单
     * @param username
     * @return
     */
    public ArrayList<OrderSTO> findAllsUserorders(String username,String start,String count){
        try {
            String select = "select * from `userorder`,`order`,`book` where `username`=? " +
                    "and userorder.oid=order.oid and order.pid=book.pid limit "+start+","+count+";";
            ArrayList<OrderSTO> resultlist=new ArrayList<OrderSTO>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                OrderSTO orderSTO=new OrderSTO();
                Book book=new Book();
                orderSTO.setOid(rs.getString(1));
                orderSTO.setUsername(rs.getString(2));
                orderSTO.setOrdertime(rs.getString(3));
                orderSTO.setState(rs.getString(4));
                orderSTO.setNumber(rs.getString(9));
                orderSTO.setPrice(rs.getString(10));
                orderSTO.setSid(rs.getString(12));
                orderSTO.setSname(rs.getString(13));
                book.setPid(rs.getString(14));
                book.setName(rs.getString(15));
                book.setImage(rs.getString(16));
                book.setWriter(rs.getString(17));
                book.setPress(rs.getString(18));
                book.setTime(rs.getString(19));
                book.setISBN(rs.getString(20));
                book.setPage(rs.getString(21));
                book.setCategory(rs.getString(22));
                book.setIntro(rs.getString(23));
                orderSTO.setBook(book);
                resultlist.add(orderSTO);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 改变一个订单的状态(改变userorder 表)
     * @param oid
     * @param state
     * @return
     */
    public boolean changeOrderState(String oid,String state){
        try {
            String select = "select * from `order` where `oid`=?;";
            String update = "update `order` set `state`=? where oid=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1,oid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                ps = conn.prepareStatement(update);
                ps.setString(1, state);
                ps.setString(2, oid);
                ps.execute();
            }
            rs.close();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 改变一个订单的状态(改变userorder 表)
     * @param oid
     * @param state
     * @return
     */
    public boolean changeUserOrderState(String oid,String state){
        try {
            String select = "select * from `userorder` where `oid`=?;";
            String update = "update userorder set `state`=? where oid=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1,oid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                ps = conn.prepareStatement(update);
                ps.setString(1, state);
                ps.setString(2, oid);
                ps.execute();
            }
            rs.close();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 增加一条订单
     * @param order
     * @return
     */
    public boolean addDetailOrder(Order order) {
        try {
            String insert = "insert into `order` (oid,ordertime,pid,pname,number,price,state) values(?,?,?,?,?,?,?);";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(insert);
            ps.setString(1, order.getOid());
            ps.setString(2, order.getOrdertime());
            ps.setString(3, order.getPid());
            ps.setString(4, order.getPanme());
            ps.setInt(5, order.getNumber());
            ps.setFloat(6, order.getPrice());
            ps.setString(7, order.getState());
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
     * 删除一个订单
     * @param oid
     * @return
     */
    public boolean delDetailOrder(String oid) {
        try {
            String delete = "delete from `order` where `oid`=?;";
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(delete);
            ps.setString(1, oid);
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
     * 返回oid所包含的具体订单
     * @param oid
     * @return
     */
    public ArrayList<Order> findDetailOrder(String oid){
        try {
            String select = "select * from `order` where `oid`=?";
            ArrayList<Order> resultlist=new ArrayList<Order>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Order order=new Order();
                order.setOid(rs.getString(1));
                order.setOrdertime(rs.getString(2));
                order.setPid(rs.getString(3));
                order.setPanme(rs.getString(4));
                order.setNumber(rs.getInt(5));
                order.setPrice(rs.getFloat(6));
                order.setState(rs.getString(7));
                resultlist.add(order);
            }
            return resultlist;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    }


