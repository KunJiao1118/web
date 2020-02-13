package com.jk.demo.dao.dataHelper.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import po.hotelPO.RoomPO;

public class orderSerHelper {
	String path = "src/main/resources/order/";

	/**
	 * 读取room ser文件
	 * 
	 * @param orderID
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<RoomPO> readRoomSer(String orderID)
			throws IOException {
		ArrayList<RoomPO> result = new ArrayList<RoomPO>();
		String path = "src/main/resources/order/";
		path = path + orderID + "/" + "room.txt";
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<RoomPO>) ois.readObject();
				ois.close();
				return result;
	
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
	
		}
	}

	/**
	 * 写入room ser文件
	 * 
	 * @param orderID
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public boolean writeRoomSer(String orderID, ArrayList<RoomPO> object)
			throws IOException {
		String path = "src/main/resources/order/";
		path = path + orderID + "/" + "room.txt";
		File comment = new File(path);
		boolean exists = comment.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			comment.createNewFile();
		}
		try {
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(object);
				oos.flush();
				oos.close();

	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
