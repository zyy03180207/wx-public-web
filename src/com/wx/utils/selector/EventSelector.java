package com.wx.utils.selector;

import com.wx.config.Common;
import com.wx.parse.function.AI;
import com.wx.parse.function.CollectionUser;
import com.wx.parse.function.Opinion;
import com.wx.parse.function.PayCode;
import com.wx.parse.function.SingleChat;
import com.wx.parse.function.Subscribe;
import com.wx.parse.function.Subway;
import com.wx.parse.function.Weather;
import com.wx.parse.msg.ReplyMsg;
import com.wx.utils.StringUtil;
import com.wx.xml.XMLCombination;

/**
 * 事件选择器,当点击菜单式触发该类事件
 * @author 张阳阳
 * @date 2016年10月19日12:27:08
 * @功能  对上传的事件进行选择处理，事件是通过上传的KEY值来确定的
 */
public class EventSelector extends BaseSelector{

	public EventSelector(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String exect() {
		// TODO Auto-generated method stub
		String result = "";
		//事件类型
		String event = (String) maps.get("Event");
		String fromUserName = (String) maps.get("FromUserName");
		switch(event){
		//菜单点击推送
		case "CLICK":
			switch ((String)maps.get("EventKey")) {
			//单聊创建事件
			case "KEY_SINGLECHAT":
				System.out.println("事件：单聊");
				SingleChat chat = new SingleChat(xml);
				String openId = chat.ctSingleChat(fromUserName);
				if(StringUtil.strisNotNull(openId)){
					result = XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"已为你随机匹配到一位用户，请耐心等待对方同意！");
					//回复对方
					ReplyMsg.text(openId, Common.SYSTEMNOTIFICATION+"有用户在随机约人中约到您，点击查看<a href='http://www.baidu.com'>对方资料</a>，您可以通过点击『同意』和『拒绝』菜单进行操作！");
				}else{
					result = XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"很遗憾本次未匹配到用户，请稍后再试！");
				}
				break;
				//同意聊天请求
			case "KEY_PASS":
				System.out.println("事件：同意聊天");
				SingleChat passChat = new SingleChat(xml);
				result = passChat.pass(fromUserName);
				break;
				//拒绝聊天请求
			case "KEY_REFUSE":
				System.out.println("事件：拒绝聊天");
				SingleChat refuseChat = new SingleChat(xml);
				result = refuseChat.refuse(fromUserName);
				break;
				//查询线路
			case "KEY_SUBWAY":
				System.out.println("事件：查询线路");
				Subway subway = new Subway(xml);
				result = subway.create(fromUserName);
				break;
				//意见反馈创建
			case "KEY_OPINION":
				System.out.println("事件：意见反馈创建");
				Opinion opinion = new Opinion(xml);
				result = opinion.ctOpinion(fromUserName);
				break;
				//收藏用户
			case "KEY_COLLECTION_USER":
				System.out.println("事件：收藏用户");
				CollectionUser user = new CollectionUser(xml);
				result = user.collect(fromUserName);
				break;
				//拉黑用户
			case "KEY_PULL_BLACK_USER":
				System.out.println("事件：拉黑用户");
				CollectionUser user2 = new CollectionUser(xml);
				result = user2.pullBlack(fromUserName);
				break;
				//举报用户
			case "KEY_REPORT":
				System.out.println("事件：举报用户");
				CollectionUser user3 = new CollectionUser(xml);
				result = user3.report(fromUserName);
				break;
				//天气预报
			case "KEY_WEATHER":
				System.out.println("事件：天气预报");
				Weather weather = new Weather(xml);
				result = weather.ctWeather(fromUserName);
				break;
				//问答机器人
			case "KEY_AI":
				AI ai = new AI(xml);
				result = ai.ctAI(fromUserName);
				break;
				//支付功能
			case "KEY_PAY":
				PayCode payCode = new PayCode(xml);
				result = payCode.ctPay(fromUserName);
				break;
			default:
				break;
			}
			break;
			//订阅事件
		case "subscribe":
			//获取用户详细信息
			result = Subscribe.sub(fromUserName, true);
			break;
			//取消订阅
		case "unsubscribe":
			result = Subscribe.unSub(fromUserName);
			break;
			//上送地理位置
		case "LOCATION":

			break;
		}
		return result;
	}

}
