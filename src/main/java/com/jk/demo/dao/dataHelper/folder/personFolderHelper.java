package com.jk.demo.dao.dataHelper.folder;

import java.io.*;

public class personFolderHelper {

	/**
	 * 用于新建客户个人文件夹
	 * 
	 * @param personname
	 */
	public void mkdirs(String personname) {
		String path = "src/main/resources/person/";
		path=path+personname;
		File A = new File(path);
		if (A.exists() == false) {
			A.mkdirs();
		}
	}

}
