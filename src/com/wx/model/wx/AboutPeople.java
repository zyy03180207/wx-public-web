package com.wx.model.wx;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class AboutPeople extends Model<AboutPeople> {
	
	public static AboutPeople dao = new AboutPeople();
	//获取在时效内的单聊用户
	public AboutPeople findByMyId(String myOpenId,String outTime){
		return dao.findFirst("SELECT * FROM single_chat p WHERE p.myopenid = ? and p.out_time > ?", myOpenId, outTime);
	}
	/**
	 * 等到我约到的人
	 * @param myOpenId
	 * @return
	 */
	public AboutPeople findByCid(String myOpenId){
		return dao.findFirst("SELECT * FROM single_chat p WHERE p.myopenid = ?", myOpenId);
	}
	/**
	 * 得到约到自己的人
	 * @param openId
	 * @return
	 */
	public AboutPeople findByOpenId(String openId){
		return dao.findFirst("SELECT * FROM single_chat p WHERE p.openid = ?", openId);
	}
}
