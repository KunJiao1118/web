package com.jk.demo.dao.dataHelper.folder;

import java.io.File;

public class hotelFolderHelper {

	/**
	 * 用于新建酒店文件夹
	 * 
	 * @param hotelname
	 */
	public void mkdirs(String hotelname) {
		String path = "src/main/resources/hotel/";
		path = path + hotelname;
		File A = new File(path);
		if (A.exists() == false) {
			A.mkdirs();
		}
	}


	
		
}
