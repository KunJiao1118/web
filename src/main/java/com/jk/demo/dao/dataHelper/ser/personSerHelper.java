package com.jk.demo.dao.dataHelper.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import po.personPO.RecordPO;

public class personSerHelper {
	String path = "src/main/resources/person/";

	/**
	 * 写入record ser文件
	 * 
	 * @param personname
	 * @param object
	 * @throws IOException
	 */
	public boolean writeRecordSer(String personname, RecordPO object)
			throws IOException {
		System.out.println("used");
		String path = "src/main/resources/person/";
		path = path + personname + "/" + "record.txt";
		ArrayList<RecordPO> origin=new ArrayList<RecordPO>();
		File record = new File(path);
		boolean exists = record.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			record.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readRecordSer(personname);
				origin.add(object);
				FileOutputStream fos = new FileOutputStream(record);
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(origin);
			} else {
				FileOutputStream fos = new FileOutputStream(record);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				origin.add(object);
				oos.writeObject(origin);
				oos.flush();
				oos.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 读取record ser文件
	 * 
	 * @param personname
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<RecordPO> readRecordSer(String personname)
			throws IOException {
		ArrayList<RecordPO> result = new ArrayList<RecordPO>();
		String path = "src/main/resources/person/";
		path = path + personname + "/" + "record.txt";
		File a=new File(path);
		if(a.exists()==false){
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<RecordPO>) ois.readObject();
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
	 * 写入record ser文件
	 * 
	 * @param personname
	 * @param object
	 * @throws IOException
	 */
	public boolean writeOrderedHotelSer(String personname, String hotelname)
			throws IOException {
		String path = "src/main/resources/person/";
		path = path + personname + "/" + "orderedHotel.txt";
		ArrayList<String> origin = new ArrayList<String>();
		File orderedHotel = new File(path);
		boolean exists = orderedHotel.exists();
		if (exists == false) {
			orderedHotel.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readOrderedHotelSer(personname);
				if(origin.contains(hotelname)==false){
					origin.add(hotelname);}
				else{
					return true;
				}
				FileOutputStream fos = new FileOutputStream(orderedHotel);
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(origin);
			} else {
				FileOutputStream fos = new FileOutputStream(orderedHotel);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				origin.add(hotelname);
				oos.writeObject(origin);
				oos.flush();
				oos.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 读取record ser文件
	 * 
	 * @param personname
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<String> readOrderedHotelSer(String personname)
			throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		String path = "src/main/resources/person/";
		path = path + personname + "/" + "orderedHotel.txt";
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<String>) ois.readObject();
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
}
