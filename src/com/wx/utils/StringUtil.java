package com.wx.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtil {

	public static boolean strisNotNull(String value) {
		// 判断返回字符串的结果
		boolean result = false;
		if (null != value && !"".equals(value)) {
			result = true;
		}
		return result;
	}

	
	public static String transMoney(long money){
		DecimalFormat df = new DecimalFormat("0.00");
		String m;
		if(money < 0){
			m = "0.00";
		}else{
			m = df.format(money/100.00f);
			
		}
		return m;
		
	}
	
	public static long transMoney(String money){
		long m;
		try{
			m = Long.parseLong(money)*100;
		}catch(Exception e){
			m = 0;
		}
		
		
		return m;
	}
	
	/** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static String getDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        Date date = null;  
        try {  
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c  
                .getTime());  
        return dayBefore;  
    }  
}
