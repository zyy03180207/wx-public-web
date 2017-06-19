package com.wx.utils.wxpay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wx.config.Common;
import com.wx.utils.wx.RandomStringGenerator;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年12月1日 下午8:24:13 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Signature {

	public String AppId = "wxabc2cdd631a15420";
	public String timestamp = "";
	public String nonceStr = "";
	public String[] jsApiList = {"chooseWXPay"};
	
	
	public Signature() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * wx.config签名
	 * @param url
	 * @return
	 */
	public String sign(String url){
		Map<String, Object> map = new HashMap<>();
		map.put("noncestr", nonceStr);
		map.put("jsapi_ticket", Common.TICKET);
		map.put("timestamp", timestamp);
		map.put("url", url);
		return com.wx.utils.wx.Signature.getSigns(map);
	}
	
	public String signPay(String prepay_id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", AppId);
		map.put("timeStamp", timestamp);
		map.put("nonceStr", nonceStr);
		map.put("package", "prepay_id=wx20161202112258ad7168cdf70423983971");
		map.put("signType", "MD5");
		return com.wx.utils.wx.Signature.getSign(map);
	}
	
	/**
	 * 生成随机32位字符串
	 * @param len
	 * @return
	 */
	public String  ctNonceStr(int len){
		nonceStr = RandomStringGenerator.getRandomStringByLength(len);
		return nonceStr;
	}
	
	public String ctTimestamp(){
		timestamp = String.valueOf(new Date().getTime()/1000);
		return timestamp;
	}
}
