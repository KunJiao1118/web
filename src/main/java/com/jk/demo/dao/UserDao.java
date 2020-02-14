package com.jk.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jk.demo.dao.dataHelper.jdbc.Builder;
import com.jk.demo.dao.Dao_entities.User;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private Builder builder = new Builder();
	private ResultSet rs = null;
	static UserDao userdata = null;

	/**
	 * 返回唯一的Userdata对象
	 *
	 * @return Userdata
	 * @author xiamutian
	 */
	public static UserDao getInstance() {
		if (userdata == null)
			userdata = new UserDao();
		return userdata;
	}

	public boolean addUser(User userInfo) {
		int lastID = 0;
		try {
			String name = userInfo.getUsername();
			String password = userInfo.getPassword();
			String email = userInfo.getEmail();
			String select = "select * from `user`;";
			String insert = "insert into user (name,password,email) values(?,MD5(?),?);";
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
			String select = "select * from `user`;";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(username)) {
					System.out.print("find it");
					user.setUsername(rs.getString(1));
					user.setPassword(rs.getString(2));
					user.setEmail(rs.getString(3));
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
	 * 改变一个用户邮箱
	 *
	 * @return boolean
	 * @author xiamutian
	 */
	public boolean changeEmail(User user) {
		try {
			String select = "select * from `user`;";
			String update = "update user set `email`=? where name=?;";
			conn = builder.BuildConnection();
			ps = conn.prepareStatement(select);
			rs = ps.executeQuery();
			while (rs.next()) {// next函数 第一次调用先指向第一条，返回bool提示是否有下一条
				if (rs.getString(1).equals(user.getUsername())) {
					ps = conn.prepareStatement(update);
					ps.setString(1, user.getEmail());
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
	 * 更改用户密码
	 * @param user
	 * @return
	 */
	public boolean changePassword(User user) {
		try {
			String select = "select * from `user`;";
			String update = "update user set `password`=? where name=?;";
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
	public boolean login(String personname, String password) {

		try {
			String select = "select * from `user` where `name`=? AND `password`=MD5(?);";
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