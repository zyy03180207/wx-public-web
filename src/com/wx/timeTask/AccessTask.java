package com.wx.timeTask;

import java.util.TimerTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.utils.URLConnection;

public class AccessTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String url=Common.WXURL+"cgi-bin/token?grant_type=client_credential&appid="+Common.APPID+"&secret="+Common.SECRET;
		String result = URLConnection.sendGet(url, "");
		JSONObject object = new JSONObject();
		object = JSON.parseObject(result);
		String accessToken = object.getString("access_token");
		System.out.println("access："+accessToken);
		Common.ACCESS_TOKEN = accessToken;
		String url1 = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		String reStr = URLConnection.sendGet(url1, "");
		JSONObject resJson = new JSONObject();
		resJson = JSON.parseObject(reStr);
		String ticket = resJson.getString("ticket");
		System.out.println("ticket："+ticket);
		Common.TICKET = ticket;
	}

}
