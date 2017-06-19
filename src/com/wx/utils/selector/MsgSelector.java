package com.wx.utils.selector;

import com.wx.parse.msg.BaseMsg;
import com.wx.parse.msg.TextMsg;
import com.wx.utils.emnu.FunctionType;

/**
 * 消息推送选择
 * @author 张阳阳
 * @date 2016年10月19日13:33:37
 * @功能 选择消息类型
 */
public class MsgSelector extends BaseSelector{

	public MsgSelector(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String exect(){
			//获取消息类型
			String MsgType = (String) maps.get("MsgType");
			BaseMsg msg = null;
			switch (MsgType) {
			//文本消息
			case "text":
				msg = new TextMsg(xml);
				break;
				//图片消息
			case "image":
				
				break;
				//语音消息
			case "voice":
				
				break;
				//视频消息
			case "video":
				
				break;
				//小视频
			case "shortvideo":
				
				break;
				//地理位置
			case "location":
				
				break;
				//链接消息
			case "link":
				
				break;
				//事件消息
			case "event":
				
				break;
			default:
				break;
			}
			return "";
	}
}
