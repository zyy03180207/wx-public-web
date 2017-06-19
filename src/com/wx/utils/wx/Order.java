package com.wx.utils.wx;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wx.utils.URLConnection;
import com.wx.xml.XMLCombination;

import freemarker.template.SimpleDate;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月29日 上午12:04:28 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Order {

	public static String getOrderId(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String orderId = format.format(new Date());
		orderId += new Date().getTime();
		return orderId;
	}
	
	
	public static String ctH5Pay(String money,String openId){
		String total = new BigDecimal(money).setScale(2).multiply(new BigDecimal("100")).toString();
		String spbill_create_ip = "127.0.0.1";
		String body = "微信H5收款支付";
		String trade_type = "JSAPI";	//
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
		buffer.append("<total_fee>"+total+"</total_fee>");
		buffer.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
		buffer.append("<notify_url>"+Configure.getAsyncUrl()+"</notify_url>");
		buffer.append("<trade_type>"+trade_type+"</trade_type>");
		buffer.append("<openid>"+openId+"</openid>");
		map.put("appid", Configure.getAppid());
		map.put("mch_id", Configure.getMchid());
		map.put("nonce_str", nonce_str);
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("total_fee", money);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", Configure.getAsyncUrl());
		map.put("trade_type", trade_type);
		map.put("openid", openId);
		String signature = Signature.getSign(map);
		buffer.append("<sign>"+signature+"</sign>");
		buffer.append("</xml>");
		String request = URLConnection.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", buffer.toString());
		System.out.println(request);
		Map<String, Object> maps = XMLCombination.pares(request);
		if(maps==null){
			return "";
		}else{
			if(maps.get("return_code").toString().equals("SUCCESS")){
				if(maps.get("result_code").toString().equals("SUCCESS")){
					return maps.get("prepay_id").toString();
				}else{
					return "";
				}
			}else{
				return "";
			}
		}
	}
	
}
