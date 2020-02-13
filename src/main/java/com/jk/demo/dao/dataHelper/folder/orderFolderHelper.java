package com.jk.demo.dao.dataHelper.folder;

import java.io.File;

public class orderFolderHelper {

	/**
	 * 用于新建订单文件夹文件夹
	 * 
	 * @param orderID
	 */
	public void mkdirs(String orderID) {
		String path = "src/main/resources/order/";
		path = path + orderID;
		File A = new File(path);
		if (A.exists() == false) {
			A.mkdirs();
		}
	}
}
