package com.jk.demo.dao.dataHelper.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Builder {
	/**
	 * 返回一个建立好的mysql连接
	 * 
	 * @author xiamutian
	 * @return Connection
	 */
	public Connection BuildConnection() {
		Connection conn = null;
		String database = "jdbc:mysql://172.19.240.141:3306/project?serverTimezone=UTC&characterEncoding=utf8&useSSL=true";
		String username = "newuser";
		String password = "123456";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 引入驱动类
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 建立连接对象
			conn = DriverManager.getConnection(database, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
