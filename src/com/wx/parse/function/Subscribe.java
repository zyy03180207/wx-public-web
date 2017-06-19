package com.wx.parse.function;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.model.wx.Function;
import com.wx.model.wx.Jcuser;
import com.wx.utils.URLConnection;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月9日 下午6:09:01 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Subscribe extends BaseFunction {

	public Subscribe(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 关注公众号推送
	 * @param fromUserName
	 * @return
	 */
	public static String sub(String fromUserName,boolean isPush){
		String url = Common.WXURL+"cgi-bin/user/info?access_token="+Common.ACCESS_TOKEN+"&openid="+fromUserName+"&lang=zh_CN";
		String jsonStr = URLConnection.sendGet(url, "");
		JSONObject object = JSON.parseObject(jsonStr);
		Jcuser jcuser = Jcuser.dao.findByOpenId(fromUserName);
		if(jcuser==null){
			jcuser= new Jcuser();
			jcuser.set("subscribe", object.get("subscribe"))
			.set("openid", object.get("openid"))
			.set("nickname", object.get("nickname"))
			.set("sex", object.get("sex"))
			.set("language", object.get("language"))
			.set("city", object.get("city"))
			.set("province", object.get("province"))
			.set("country", object.get("country"))
			.set("headimgurl", object.get("headimgurl"))
			.set("subscribe_time", object.get("subscribe_time"))
			.set("unionid", object.get("unionid"))
			.set("remark", object.get("remark"))
			.set("groupid", object.get("groupid")).save();
		}else{
			jcuser.set("subscribe", object.get("subscribe"))
			.set("openid", object.get("openid"))
			.set("nickname", object.get("nickname"))
			.set("sex", object.get("sex"))
			.set("language", object.get("language"))
			.set("city", object.get("city"))
			.set("province", object.get("province"))
			.set("country", object.get("country"))
			.set("headimgurl", object.get("headimgurl"))
			.set("subscribe_time", object.get("subscribe_time"))
			.set("unionid", object.get("unionid"))
			.set("remark", object.get("remark"))
			.set("groupid", object.get("groupid")).update();
		}
		Function function = Function.dao.findByOpenId(fromUserName);
		if(function == null){
			function = new Function();
			function.set("openid", fromUserName).set("function", "NONE").set("createdate", new Date().getTime()).save();
		}else{
			function.set("openid", fromUserName).set("function", "NONE").set("createdate", new Date().getTime()).update();
		}
		if(isPush)
			return XMLCombination.text(fromUserName, Common.SUBSCRIBE);
		return "success";
	}
	/**
	 * 取消关注
	 * @param fromUserName
	 * @return
	 */
	public static String unSub(String fromUserName){
		Jcuser jcuser = Jcuser.dao.findByOpenId(fromUserName);
		if(jcuser != null){
			jcuser.set("subscribe", 1).update();
		}
		return "success";
	}
}
