package com.wx.utils;

import java.util.Date;

public class TimeUtil {

	public static String getTimeOut(long outTime){
		Date dt= new Date();
		Long time= dt.getTime();//这就是距离1970年1月1日0点0分0秒的毫秒数
		return String.valueOf(time - outTime);
	}

}
