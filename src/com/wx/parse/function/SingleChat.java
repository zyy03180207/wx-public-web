package com.wx.parse.function;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;

import com.wx.config.Common;
import com.wx.model.wx.AboutPeople;
import com.wx.model.wx.Function;
import com.wx.model.wx.Jcuser;
import com.wx.parse.msg.ReplyMsg;
import com.wx.utils.StringUtil;
import com.wx.utils.TimeUtil;
import com.wx.xml.XMLCombination;

public class SingleChat extends BaseFunction{
	public SingleChat(String xml) {
		super(xml);
	}
	/**
	 * 返回发送消息
	 * @param myOpenId
	 * @return
	 */
	public String getSingleChat(String myOpenId){
		//返回信息
		String result = "success";
		String re = "";
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		System.out.println("time:"+time);
		//要接收消息的用户ID，通过数据库获得
		AboutPeople aboutPeople = AboutPeople.dao.findByMyId(myOpenId,time);
		//如果当前用户没有可发送消息的用户，或用户已过期
		if(aboutPeople == null){
			return XMLCombination.fail(myOpenId);
		}else if(aboutPeople.getInt("isno")==0){
			return XMLCombination.text(myOpenId, Common.SYSTEMNOTIFICATION+"对方还没有通过您的请求，请稍等或者点击『随机约人』获取新的朋友！");
		}else if(aboutPeople.getInt("isno")==2){
			return XMLCombination.text(myOpenId, Common.SYSTEMNOTIFICATION+"对方已经拒绝了你的请求，你可以点击『随机约人』获取新的朋友！");
		}
		String toUserName = aboutPeople.getStr("openid");
		System.out.println("touser："+toUserName);
		switch ((String)maps.get("MsgType")) {
		case "text":
			re = ReplyMsg.text(toUserName, (String) maps.get("Content"));
			break;

		case "image":
			re = ReplyMsg.image(toUserName, (String) maps.get("MediaId"));
			break;

		case "voice":
			re = ReplyMsg.voice(toUserName, (String) maps.get("MediaId"));
			break;

		default:
			result = XMLCombination.text(toUserName, Common.SYSTEMNOTIFICATION+"不支持该消息类型！");
			break;
		}
		System.out.println("result："+re);
		//组合xml
		return result;
	}
	/**
	 * 通过聊天请求
	 * @param fromUserName
	 * @return
	 */
	public String pass(String fromUserName){
		AboutPeople aboutPeople = AboutPeople.dao.findByOpenId(fromUserName);
		if(aboutPeople == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"还没有人约你或者对方已经约了别人！");
		}
		//判断是不是发起者
		if("B".equals(aboutPeople.getStr("islaunch"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你是发起方无法进行此操作，请等待有人约你！");
		}
		//判断是不是已处理
		if("B".equals(aboutPeople.getStr("ishandle"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你已处理此次操作！");
		}
		//获取用户当前使用的是不是单聊功能
		Function function = Function.dao.findByOpenId(aboutPeople.getStr("myopenid"));
		if(function == null||!"SINGLECHAT".equals(function.getStr("function"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"该用户已退出单聊功能，你无法进行同意操作！");
		}
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		//处理超时
		if(Long.valueOf(time) > Long.valueOf(aboutPeople.getStr("out_time"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"该操作已超过15分钟没操作！");
		}
		//更改聊天状态为1，可以进行聊天
		aboutPeople.set("out_time", new Date().getTime());
		aboutPeople.set("isno", 1).set("ishandle", "B").update();
		AboutPeople people = AboutPeople.dao.findByCid(fromUserName);
		if(people == null){
			people = new AboutPeople();
			people.set("myopenid", fromUserName);
			people.set("openid", aboutPeople.getStr("myopenid"));
			people.set("out_time", new Date().getTime());
			people.set("isno", 1).set("ishandle", "B").set("islaunch", "B").save();
		}else{
			people.set("openid", aboutPeople.getStr("myopenid"));
			people.set("out_time", new Date().getTime());
			people.set("isno", 1).set("ishandle", "B").set("islaunch", "B").update();
		}
		initFun("SINGLECHAT");
		ReplyMsg.text(aboutPeople.getStr("myopenid"), Common.SYSTEMNOTIFICATION+"对方已经同意你的聊天请求，你们有15分钟的聊天时间！");
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你们可以进行聊天了，你们有15分钟的聊天时间！");
	}
	/**
	 * 拒绝聊天请求
	 * @param fromUserName
	 * @return
	 */
	public String refuse(String fromUserName){
		AboutPeople aboutPeople = AboutPeople.dao.findByOpenId(fromUserName);
		if(aboutPeople == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"还没有人约你或者对方已经约了别人！");
		}
		//判断是不是发起者
		if("B".equals(aboutPeople.getStr("islaunch"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你是发起方无法进行此操作，请等待有人约你！");
		}
		//判断是不是已处理
		if("B".equals(aboutPeople.getStr("ishandle"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你已处理此次操作！");
		}
		//获取用户当前使用的是不是单聊功能
		Function function = Function.dao.findByOpenId(aboutPeople.getStr("myopenid"));
		if(function == null||!"SINGLECHAT".equals(function.getStr("function"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"该用户已退出单聊功能，你无法进行拒绝操作！");
		}
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		//处理超时
		if(Long.valueOf(time) > Long.valueOf(aboutPeople.getStr("out_time"))){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"该操作已超过15分钟没操作！");
		}
		//更改聊天状态为1，拒绝聊天状态
		aboutPeople.set("out_time", new Date().getTime());
		aboutPeople.set("isno", 2).update();
		ReplyMsg.text(aboutPeople.getStr("myopenid"), Common.SYSTEMNOTIFICATION+"对方拒绝了你的请求，你可以点击『随机约人』获取新的朋友！");
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你已拒绝对方的聊天请求");
	}

	/**
	 * 创建单聊
	 * @param myOpenId
	 * @return openId
	 */
	public String ctSingleChat(String myOpenId){
		AboutPeople aboutPeople = AboutPeople.dao.findByCid(myOpenId);
		//获取一个随机的用户,对方的微信ID
		String openId = randomOpenId(myOpenId);
		if(StringUtil.strisNotNull(openId)){
			if(aboutPeople == null){
				aboutPeople = new AboutPeople();
				aboutPeople.set("myopenid", myOpenId);
				aboutPeople.set("openid", openId);
				aboutPeople.set("out_time", new Date().getTime());
				aboutPeople.set("isno", 0);
				aboutPeople.set("ishandle", "A");	//A为未处理
				aboutPeople.set("islaunch", "A");	//A为发起方
				aboutPeople.save();
			}else{
				aboutPeople.set("openid", openId);
				aboutPeople.set("out_time", new Date().getTime());
				aboutPeople.set("isno", 0);
				aboutPeople.set("ishandle", "A");	//A为未处理
				aboutPeople.set("islaunch", "A");	//A为发起方
				aboutPeople.update();
			}
			//对方的对应表
			AboutPeople people = AboutPeople.dao.findByCid(openId);
			if(people == null){
				people = new AboutPeople();
				people.set("myopenid", openId);
				people.set("openid", myOpenId);
				people.set("out_time", new Date().getTime());
				people.set("isno", 0);
				people.set("ishandle", "A");	//A为未处理
				people.set("islaunch", "B");	//A为发起方
				people.save();
			}else{
				people.set("myopenid", openId);
				people.set("openid", myOpenId);
				people.set("out_time", new Date().getTime());
				people.set("isno", 0);
				people.set("ishandle", "A");	//A为未处理
				people.set("islaunch", "B");	//A为发起方
				people.update();
			}
			//更新当前功能
			initFun("SINGLECHAT");
			return openId;
		}else{
			//更新当前功能
			initFun("SINGLECHAT");
			return "";
		}
	}
	/**
	 * 随机生成一个openId
	 * @return
	 */
	public String randomOpenId(String myOpenId){
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		String openId = "";
		Jcuser jcuser = Jcuser.dao.findByRadmon(time,myOpenId);
		if(jcuser != null){
			openId = jcuser.getStr("openid");
		}else{
			openId = "";
		}
		return openId;
	}
}
