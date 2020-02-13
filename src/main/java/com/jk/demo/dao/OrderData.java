package com.jk.demo.dao;

import com.jk.demo.dao.Dao_entities.Order;
import com.jk.demo.dao.Dao_entities.Userorder;
import com.jk.demo.dao.dataHelper.jdbc.Builder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderData {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static OrderData orderData = null;
    private Builder builder = new Builder();


    public static OrderData getInstance() {
        if (orderData == null) {
            orderData = new OrderData();
            return orderData;
        }
        return orderData;
    }

    /**
     * 增加一个订单
     * @param order
     * @return
     */
    public boolean addUserOrder(Userorder order) {
        try {
            String insert = "insert into `UserOrder` (oid,username,ordertime,state) values(?,?,?,?);";
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
     * 返回符合订单状态状态的订单
     * @param username
     * @param state
     * @return
     */
    public ArrayList<Userorder> FindUserordersByState(String username,String state){
        try {
            String select = "select * from `UserOrder` where `username`=? AND `state`=?;";
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
     * @param state
     * @return
     */
    public ArrayList<Userorder> FindAllsUserorders(String username,String state){
        try {
            String select = "select * from `UserOrder` where `username`=? ;";
            ArrayList<Userorder> resultlist=new ArrayList<Userorder>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, username);
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
     * 改变一个订单的状态
     * @param oid
     * @param state
     * @return
     */
    public boolean ChangeOrderState(String oid,String state){
        try {
            String select = "select * from `UserOrder` where `id`=?;";
            String update = "update person set `state`=? where oid=?;";
            conn = builder.BuildConnection();
            ps.setString(1,oid);
            ps = conn.prepareStatement(select);
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
     * 返回oid所包含的具体订单
     * @param oid
     * @return
     */
    public ArrayList<Order> FindDetailOrder(String oid){
        try {
            String select = "select * from `Order` where `oid`=?";
            ArrayList<Order> resultlist=new ArrayList<Order>();
            conn = builder.BuildConnection();
            ps = conn.prepareStatement(select);
            ps.setString(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
                Order order=new Order();
                order.setOpid(rs.getString(1));
                order.setOid(rs.getString(2));
                order.setPid(rs.getString(3));
                order.setNumber(rs.getInt(4));
                order.setPanme(rs.getString(5));
                order.setTotalnumber(rs.getInt(6));
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
