package com.wx.parse.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.utils.URLConnection;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月11日 下午7:30:19 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class AI extends BaseFunction {

	public AI(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建机器人问答
	 * @param fromUserName
	 * @return
	 */
	public String ctAI(String fromUserName){
		initFun("AI");
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"请输入要发送给机器人的内容，不要超过30个字符！");
	}

	public String answer(String fromUserName){
		String msgType = (String) maps.get("MsgType");
		if("text".equals(msgType)){
			String url = "http://op.juhe.cn/robot/index?key="+Common.AIAppKey+"&info="+(String) maps.get("Content");
			String response = URLConnection.sendGet(url, "");
			System.out.println("response:"+response);
			JSONObject respJson = JSON.parseObject(response);
			String error_code = respJson.getString("error_code");
			if("0".equals(error_code)){
				JSONObject resultJson = respJson.getJSONObject("result");
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+resultJson.getString("text"));
			}else{
				return Common.SYSTEMNOTIFICATION+"我太笨了，不知道您说的什么！";
			}
		}else{
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"您只能输入文字哦！");
		}
	}
}
