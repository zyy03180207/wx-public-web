package com.wx.model.wx;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
/**
 * 微信用户
 * @author 张阳阳
 * @date 2016年10月28日
 * @time 下午6:15:31
 * @function
 */
public class Jcuser extends Model<Jcuser> {
	public static Jcuser dao = new Jcuser();
	//随机获取一个用户(待修改)
	public Jcuser findByRadmon(String outTime,String openId){
		return dao.findFirst("SELECT * FROM jcuser WHERE openid <> ? AND openid NOT IN (SELECT openid FROM single_chat WHERE out_time > ?) AND openid NOT IN (SELECT myopenid FROM single_chat WHERE out_time > ?) AND sex <> (SELECT sex FROM jcuser WHERE openid = ?) AND cid >= ((SELECT MAX(cid) FROM jcuser) - (SELECT MIN(cid) FROM jcuser)) * RAND() + (SELECT MIN(cid) FROM jcuser) LIMIT 1", openId, outTime, outTime, openId);
	}
	
	public int delJcuser(String openId){
		return Db.update("DELETE FROM jcuser WHERE openid = ?", openId);
	}
	
	public Jcuser findByOpenId(String openId){
		return dao.findFirst("SELECT * FROM jcuser WHERE openid=?",openId);
	}
}
