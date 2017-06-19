package com.wx.model.wx;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class Function extends Model<Function> {
	public static Function dao = new Function();
	
	public Function findByOpenId(String openId){
		return dao.findFirst("SELECT * FROM now_function f WHERE f.openid = ?", openId);
	}
	
	public int updateFunction(String function,String openid){
		return Db.update("update now_function set `function` = ? where openid = ?",function, openid);
	}
}
