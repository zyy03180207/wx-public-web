package com.wx.utils.wxpay;

import java.util.HashMap;
import java.util.Map;

import com.wx.utils.URLConnection;
import com.wx.utils.wx.Configure;
import com.wx.utils.wx.Order;
import com.wx.utils.wx.RandomStringGenerator;
import com.wx.utils.wx.Signature;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年12月1日 下午12:00:33 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class TwoCodePay {

	public String ctOrder(int money){
		String spbill_create_ip = "127.0.0.1";
//		String openid = "oEWYJwWOL7Kd3K0dLjxRE-dAJF5g";
		String body = "H5支付测试";
		String trade_type = "NATIVE";	//
		Map<String, Object> map = new HashMap<>();
		StringBuffer buffer = new StringBuffer();
		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		String out_trade_no = Order.getOrderId();
		buffer.append("<xml>");
		buffer.append("<appid>"+Configure.getAppid()+"</appid>");
		buffer.append("<mch_id>"+Configure.getMchid()+"</mch_id>");
		buffer.append("<nonce_str>"+nonce_str+"</nonce_str>");
		buffer.append("<body>"+body+"</body>");
		buffer.append("<out_trade_no>"+out_trade_no+"</out_trade_no>");
		buffer.append("<total_fee>"+money+"</total_fee>");
		buffer.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
		buffer.append("<notify_url>"+Configure.getAsyncUrl()+"</notify_url>");
		buffer.append("<trade_type>"+trade_type+"</trade_type>");
		map.put("appid", Configure.getAppid());
		map.put("mch_id", Configure.getMchid());
		map.put("nonce_str", nonce_str);
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("total_fee", money);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", Configure.getAsyncUrl());
		map.put("trade_type", trade_type);
		String signature = Signature.getSign(map);
		buffer.append("<sign>"+signature+"</sign>");
		buffer.append("</xml>");
		String request = URLConnection.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", buffer.toString());
		System.out.println(request);
		Map<String, Object> maps = XMLCombination.pares(request);
		if(maps.get("return_code").equals("SUCCESS")){
			if(maps.get("result_code").equals("SUCCESS")){
				return maps.get("code_url").toString();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
}
