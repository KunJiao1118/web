package com.jk.demo.dao.dataHelper.jdbc;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 改变calendar为timestamp
 * @author xiamutian
 *
 */
public class ChangerHelper {
	public  static  Timestamp changeToTimestamp(Calendar cal){
		if(cal!=null){
			java.util.Date date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			Timestamp result = Timestamp.valueOf(time);
			return result;}
		else{
			return null;
		}
	}
	public static Calendar changeToCalendar(Timestamp time){
		if(time!=null){
			Calendar result = Calendar.getInstance();
			result.setTime(time);
			return result;}
		else{
		return null;
		}
	}
	
	
	
}
