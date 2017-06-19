package com.wx.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.wx.utils.URLConnection;
import com.wx.utils.wx.Configure;
import com.wx.utils.wx.Order;
import com.wx.utils.wx.RandomStringGenerator;
import com.wx.utils.wx.Signature;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月25日 下午6:17:39 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
@Clear
public class WXPayController extends Controller{

	public void index(){
		
	}
	
	public void webpay(){
		String openid = this.getPara("openid", "oEWYJwWOL7Kd3K0dLjxRE-dAJF5g");
		this.setAttr("openid", openid);
		this.setAttr("prepay_id", "");
		renderJsp("qiandao.jsp");
	}
	
	public void pays(){
		String money = this.getPara("money","1000");
		String openid = this.getPara("openid","oEWYJwWOL7Kd3K0dLjxRE-dAJF5g");
		String spbill_create_ip = "127.0.0.1";
//		String openid = "oEWYJwWOL7Kd3K0dLjxRE-dAJF5g";
		String body = "H5支付测试";
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
		buffer.append("<total_fee>"+money+"</total_fee>");
		buffer.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
		buffer.append("<notify_url>"+Configure.getAsyncUrl()+"</notify_url>");
		buffer.append("<trade_type>"+trade_type+"</trade_type>");
		buffer.append("<openid>"+openid+"</openid>");
		map.put("appid", Configure.getAppid());
		map.put("mch_id", Configure.getMchid());
		map.put("nonce_str", nonce_str);
		map.put("body", body);
		map.put("out_trade_no", out_trade_no);
		map.put("total_fee", money);
		map.put("spbill_create_ip", spbill_create_ip);
		map.put("notify_url", Configure.getAsyncUrl());
		map.put("trade_type", trade_type);
		map.put("openid", openid);
		String signature = Signature.getSign(map);
		buffer.append("<sign>"+signature+"</sign>");
		buffer.append("</xml>");
		String request = URLConnection.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", buffer.toString());
		System.out.println(request);
		Map<String, Object> maps = XMLCombination.pares(request);
		if(maps==null){
			this.setAttr("ok", 2);
		}else{
			if(maps.get("return_code").toString().equals("SUCCESS")){
				if(maps.get("result_code").toString().equals("SUCCESS")){
					this.setAttr("ok", 1);
					this.setAttr("prepay_id", maps.get("prepay_id").toString());
				}else{
					this.setAttr("ok", 2);
				}
			}else{
				this.setAttr("ok", 2);
			}
		}
		this.setAttr("openid", openid);
		this.render("/webpay.jsp");
	}
	/**
	 * 获取本机ip地址
	 * @return
	 */
	private String getIpAddr() {
	     String ipAddress = null;   
	     //ipAddress = this.getRequest().getRemoteAddr();   
	     ipAddress = this.getRequest().getHeader("x-forwarded-for");   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	      ipAddress = this.getRequest().getHeader("Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	         ipAddress = this.getRequest().getHeader("WL-Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	      ipAddress = this.getRequest().getRemoteAddr();   
	      if(ipAddress.equals("127.0.0.1")){   
	       //根据网卡取本机配置的IP   
	       InetAddress inet=null;   
	    try {   
	     inet = InetAddress.getLocalHost();   
	    } catch (UnknownHostException e) {   
	     e.printStackTrace();   
	    }   
	    ipAddress= inet.getHostAddress();   
	      }   
	            
	     }   
	  
	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
	         if(ipAddress.indexOf(",")>0){   
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
	         }   
	     }   
	     return ipAddress;    
	  }   
}
