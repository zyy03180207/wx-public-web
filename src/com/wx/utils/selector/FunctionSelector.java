package com.wx.utils.selector;

import java.util.Date;

import com.wx.model.wx.Function;
import com.wx.parse.function.AI;
import com.wx.parse.function.Opinion;
import com.wx.parse.function.PayCode;
import com.wx.parse.function.SingleChat;
import com.wx.parse.function.Subway;
import com.wx.parse.function.Weather;
import com.wx.xml.XMLCombination;

/**
 * 功能选择器，当在聊天框中输入文字信息时触发改事件
 * @author 张阳阳
 * @date 2016年10月19日13:18:12
 * @功能 选择微信公众号功能，通过function表来确定当前用户在使用的功能，
 * 如果function为NONE则提示用户还未使用任何功能，在功能是用完时要初始化function的值为NONE，
 * 不分功能不需要初始化
 */
public class FunctionSelector extends BaseSelector{

	public FunctionSelector(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String exect(){
		//发送消息的微信ID
		String openId = (String) maps.get("FromUserName");
		//获得当前拥有的功能
		Function function = Function.dao.findByOpenId(openId);
		//判断是否有功能选择
		if(function == null){
			function = new Function();
			function.set("openid", openId).set("function", "NONE")
			.set("createdate", new Date().getTime()).save();
			return XMLCombination.fail(openId);
		}
		//得到当前功能类型
		String type = function.getStr("function");
		String request = "";
		switch (type) {
		//单聊
		case "SINGLECHAT":
			//单聊处理
			SingleChat singleChat = new SingleChat(xml);
			//单聊返回
			request = singleChat.getSingleChat(openId);
			break;
			//查询路线
		case "SUBWAY":
			Subway subway = new Subway(xml);
			request = subway.search(openId);
			break;
			//意见反馈
		case "OPINION":
			Opinion opinion = new Opinion(xml);
			request = opinion.submit(openId);
			break;
			//天气预报
		case "WEATHER":
			Weather weather = new Weather(xml);
			request = weather.find(openId);
			break;
			//智能机器人
		case "AI":
			AI ai = new AI(xml);
			request = ai.answer(openId);
			break;
			//支付
		case "PAYCODE":
			PayCode code = new PayCode(xml);
			request = code.ctPayOrder(openId);
			break;
			//未调用任何功能
		case "NONE":
			request = XMLCombination.fail(openId);
			break;

		default:
			break;
		}
		//返回参数
		return request;

	}

}
