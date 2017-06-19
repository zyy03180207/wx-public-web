package com.wx.parse.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.model.wx.Function;
import com.wx.utils.URLConnection;
import com.wx.utils.wxpay.TwoCodePay;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年12月1日 下午12:06:48 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class PayCode extends BaseFunction{

	public PayCode(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}

	public String ctPay(String fromUserName){
		initFun("PAYCODE");
		return XMLCombination.text(fromUserName, "请输入金额以分为单位，如：（10元输入1000）");
	}
	
	public String ctPayOrder(String fromUserName){
		try{
		String msgType = (String) maps.get("MsgType");
		if("text".equals(msgType)){
			String money = (String) maps.get("Content");
			int total = Integer.valueOf(money);
			TwoCodePay pay = new TwoCodePay();
			String url_code = pay.ctOrder(total);
			return XMLCombination.text(fromUserName, url_code);
		}else{
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"您只能输入数字哦！");
		}
		}catch(Exception e){
			return "";
		}
	}
	
}
