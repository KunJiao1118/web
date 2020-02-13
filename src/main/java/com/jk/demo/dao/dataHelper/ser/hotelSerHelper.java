package com.jk.demo.dao.dataHelper.ser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


import po.hotelPO.CommentPO;
import po.hotelPO.RoomPO;
import po.promotionpo.hotelpromotionPO.BirthdayHotelproPO;
import po.promotionpo.hotelpromotionPO.EnterpriseHotelproPO;
import po.promotionpo.hotelpromotionPO.LargeAmountHotelproPO;
import po.promotionpo.hotelpromotionPO.PeriodHotelproPO;

public class hotelSerHelper {
	String path = "src/main/resources/order/";

	/**
	 * 写入commentpo ser文件
	 * 
	 * @param hotelname
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public boolean writeCommentSer(String hotelname, CommentPO object)
			throws IOException {
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "comment.txt";
		ArrayList<CommentPO> origin = new ArrayList<CommentPO>();
		File comment = new File(path);
		boolean exists = comment.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			comment.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readCommentSer(hotelname);
				origin.add(object);
				FileOutputStream fos = new FileOutputStream(comment);
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(origin);
			} else {
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
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

	public boolean writePeriodHotelSer(String hotelname, PeriodHotelproPO object)
			throws IOException {
		int lastID=0;
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "PeriodHotelPromotion.txt";
		ArrayList<PeriodHotelproPO> origin = new ArrayList<PeriodHotelproPO>();
		File comment = new File(path);
		boolean exists = comment.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			comment.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readPeriodPromotionSer(hotelname);
				for(int i=0;i<origin.size();i++){
					if(origin.get(i).getPromotionID()>lastID){
						lastID=origin.get(i).getPromotionID();
					}
				}
				object.setPromotionID(lastID+1);
				origin.add(object);
				FileOutputStream fos = new FileOutputStream(comment);
				@SuppressWarnings("resource")
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(origin);
			} else {
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				object.setPromotionID(1);
				origin.add(object);
				System.out.println("now ID has "+origin.get(0).getPromotionID());
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

	public boolean modifyPeriodHotelSer(String hotelname,
			PeriodHotelproPO object) throws IOException {
		System.out.println("This promotionID is  "+object.getPromotionID());

		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "PeriodHotelPromotion.txt";
		ArrayList<PeriodHotelproPO> origin = new ArrayList<PeriodHotelproPO>();
		File comment = new File(path);
		boolean exists = comment.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			comment.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readPeriodPromotionSer(hotelname);
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				for (int i = 0; i < origin.size(); i++) {
					if (origin.get(i).getPromotionID() == object
							.getPromotionID()) {
						System.out.println("find");
						origin.remove(i);
						origin.add(object);
					}
				}
				oos.writeObject(origin);
				oos.flush();
				oos.close();

			} else {
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				origin.add(object);
				oos.writeObject(origin);
				oos.flush();
				oos.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean deletePeriodHotelSer(String hotelname,PeriodHotelproPO object) throws IOException {
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "PeriodHotelPromotion.txt";
		ArrayList<PeriodHotelproPO> origin = new ArrayList<PeriodHotelproPO>();
		File comment = new File(path);
		boolean exists = comment.exists();
		if (exists == false) {
			comment.createNewFile();
		}
		try {
			if (exists) {
				origin = this.readPeriodPromotionSer(hotelname);
				FileOutputStream fos = new FileOutputStream(comment);// out和in不能同时被实例化
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				for (int i = 0; i < origin.size(); i++) {
					if (origin.get(i).getPromotionID() == object
							.getPromotionID()) {
						origin.remove(i);
					}
				}
				System.out.println("Now size is +++"+origin.size());
				if(origin.size()!=0){
					oos.writeObject(origin);
					oos.flush();
					oos.close();
					return true;
				}
				else{
					oos.close();
					return comment.delete();
				}
			} 
			else{
				return true;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * 读取comment ser文件
	 * 
	 * @param hotelname
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<CommentPO> readCommentSer(String hotelname)
			throws IOException {
		ArrayList<CommentPO> result = new ArrayList<CommentPO>();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "comment.txt";
		File comment = new File(path);
		if (comment.exists() == false) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<CommentPO>) ois.readObject();
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
	 * 读取room ser文件
	 * 
	 * @param hotelname
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<RoomPO> readRoomSer(String hotelname) throws IOException {
		ArrayList<RoomPO> result = new ArrayList<RoomPO>();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "room.txt";
		File comment = new File(path);
//		if (comment.exists() == false) {
//			return null;
//		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<RoomPO>) ois.readObject();
				ois.close();
				return result;

			} catch (ClassNotFoundException e) {
				System.out.println("no");
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			System.out.println("no");
			e.printStackTrace();
			return null;

		}
	}

	@SuppressWarnings("resource")
	public EnterpriseHotelproPO readEnterprisePromotionSer(String hotelname)
			throws IOException {
		EnterpriseHotelproPO result = new EnterpriseHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "EnterpriseHotelPromotion.txt";
		File comment = new File(path);
		if (comment.exists() == false) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (EnterpriseHotelproPO) ois.readObject();
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
	public boolean deleteEnterprisePromotionSer(String hotelname)
			throws IOException {
		@SuppressWarnings("unused")
		EnterpriseHotelproPO result = new EnterpriseHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "EnterpriseHotelPromotion.txt";
		File comment = new File(path);
		if (comment.exists() == false) {
			return true;
		}
		return comment.delete();
	}

	@SuppressWarnings("resource")
	public BirthdayHotelproPO readBirthdayPromotionSer(String hotelname)
			throws IOException {
		BirthdayHotelproPO result = new BirthdayHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" +"BirthdayHotelPromotion.txt";
		File comment=new File(path);
		if(comment.exists()==false){
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
					result =(BirthdayHotelproPO)ois.readObject();
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
	public boolean deleteBirthdayPromotionSer(String hotelname)
			throws IOException {
		@SuppressWarnings("unused")
		BirthdayHotelproPO result = new BirthdayHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "BirthdayHotelPromotion.txt";
		File comment = new File(path);
		if (comment.exists() == false) {
			return true;
		}
		return comment.delete();
	}

	@SuppressWarnings("resource")
	public LargeAmountHotelproPO readLargeAmountPromotionSer(String hotelname)
			throws IOException {
		LargeAmountHotelproPO result = new LargeAmountHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "LargeAmountHotelPromotion.txt";
		File promotion = new File(path);
		if (promotion.exists() == false) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (LargeAmountHotelproPO) ois.readObject();
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

	@SuppressWarnings({ "unchecked", "resource" })
	public ArrayList<PeriodHotelproPO> readPeriodPromotionSer(String hotelname)
			throws IOException {
		ArrayList<PeriodHotelproPO> result = new ArrayList<PeriodHotelproPO>();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "PeriodHotelPromotion.txt";
		File comment = new File(path);
		if (comment.exists() == false) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				result = (ArrayList<PeriodHotelproPO>) ois.readObject();
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
	
	public boolean deleteLargeAmountPromotionSer(String hotelname)
			throws IOException {
		@SuppressWarnings("unused")
		LargeAmountHotelproPO result = new LargeAmountHotelproPO();
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "LargeAmountHotelPromotion.txt";
		File promotion = new File(path);
		if (promotion.exists() == false) {
			return true;
		}
		return promotion.delete();
	}
	/**
	 * 写入room ser文件
	 * 
	 * @param hotelname
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public boolean writeRoomSer(String hotelname, ArrayList<RoomPO> object)
			throws IOException {
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "room.txt";
		File room = new File(path);
		boolean exists = room.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			room.createNewFile();
		}
		try {
			FileOutputStream fos = new FileOutputStream(room);// out和in不能同时被实例化
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

	public boolean writeBirthdayPromotionSer(String hotelname,
			BirthdayHotelproPO object) throws IOException {
		System.out.println("promiton used!!!!");
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "BirthdayHotelPromotion.txt";
		File room = new File(path);
		boolean exists = room.exists();
		if(object==null){
			System.out.println("promiton nullllll!!!!");
			return false;
		}
		if (exists == false) {
			room.createNewFile();
		}
		try {
			FileOutputStream fos = new FileOutputStream(room);// out和in不能同时被实例化
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

	public boolean writeEnterprisePromotionSer(String hotelname,
			EnterpriseHotelproPO object) throws IOException {
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "EnterpriseHotelPromotion.txt";
		File room = new File(path);
		boolean exists = room.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			room.createNewFile();
		}
		try {
			FileOutputStream fos = new FileOutputStream(room);// out和in不能同时被实例化
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

	public boolean writeLargeAmountPromotionSer(String hotelname,
			LargeAmountHotelproPO object) throws IOException {
		String path = "src/main/resources/hotel/";
		path = path + hotelname + "/" + "LargeAmountHotelPromotion.txt";
		File room = new File(path);
		boolean exists = room.exists();
		if(object==null){
			return false;
		}
		if (exists == false) {
			room.createNewFile();
		}
		try {
			FileOutputStream fos = new FileOutputStream(room);// out和in不能同时被实例化
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
