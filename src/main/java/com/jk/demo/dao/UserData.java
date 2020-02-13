package com.jk.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jk.demo.dao.dataHelper.jdbc.Builder;
import com.jk.demo.dao.Dao_entities.User;

public class UserData  {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private Builder builder = new Builder();
	private ResultSet rs = null;
	static UserData userdata = null;

	/**
	 * 返回唯一的Userdata对象
	 *
	 * @return Userdata
	 * @author xiamutian
	 */
	public static UserData getInstance() {
		if (userdata == null)
			userdata = new UserData();
		return userdata;
	}

	public boolean addUser(User userInfo) {
		int lastID = 0;
		try {
			String name = userInfo.getUsername();
			String password = userInfo.getPassword();
			String email = userInfo.getEmail();
			String select = "select * from `Person`;";
			String insert = "insert into User (name,password,e-mail) values(?,MD5(?),?);";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(name)) {
					rs.close();
					return false;
				}
			}
			rs.close();
			ps = conn.prepareStatement(insert);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, email);
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
	 * 寻找一个客户
	 *
	 * @return PersonPO
	 * @author xiamutian
	 */
	public User findUser(String username) {
		User user = new User();
		try {
			String select = "select * from `person`;";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(username)) {
					user.setUsername(rs.getString(1));
					user.setPassword(rs.getString(2));
					user.setPassword(rs.getString(3));
					return user;
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}

	/**
	 * 改变一个客户信息
	 *
	 * @return boolean
	 * @author xiamutian
	 */
	public boolean changeEmail(User user) {
		try {
			String select = "select * from `person`;";
			String update = "update person set `e-mail`=? where name=?;";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(user.getUsername())) {
					ps = conn.prepareStatement(update);
					ps.setString(1, user.getEmail());
					ps.setString(1, user.getUsername());
					ps.execute();
					return true;
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePassword(User user) {
		try {
			String select = "select * from `person`;";
			String update = "update person set `password`=? where name=?;";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(user.getUsername())) {
					ps = conn.prepareStatement(update);
					ps.setString(1, user.getPassword());
					ps.setString(2, user.getUsername());
					ps.execute();
					return true;
				}
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 客户登陆
	 *
	 * @return boolean
	 * @author xiamutian
	 */
	public boolean personLogin(String personname, String password) {

		try {
			String select = "select * from `person` where `name`=? AND `password`=MD5(?);";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			ps.setString(1, personname);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				rs.close();
				ps.close();
				conn.close();
				return true;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

}