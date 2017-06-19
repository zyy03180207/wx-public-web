package com.wx.parse.function;

import java.util.Date;

import com.sun.org.apache.xpath.internal.functions.FuncPosition;
import com.wx.config.Common;
import com.wx.model.wx.FunOpinion;
import com.wx.model.wx.Function;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月9日 下午9:42:40 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Opinion extends BaseFunction {

	public Opinion(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建一个意见反馈功能
	 * @param fromUserName
	 * @return
	 */
	public String ctOpinion(String fromUserName){
		initFun("OPINION");
		FunOpinion funOpinion = FunOpinion.dao.findByOpenId(fromUserName);
		if(funOpinion == null){
			funOpinion = new FunOpinion();
			funOpinion.set("openid", fromUserName).set("state", 0).set("createdate", new Date().getTime()).save();
		}
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION + "请输入反馈的内容（只支持文字，100字以内哦）");
	}
	/**
	 * 提交意见反馈
	 * @param fromUserName
	 * @return
	 */
	public String submit(String fromUserName){
		FunOpinion funOpinion = FunOpinion.dao.findByOpenId(fromUserName);
		if(funOpinion == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"当前使用功能出现故障，请使用下方菜单重新使用或者通过反馈将问题告诉我们，我们会及时向您回馈。");
		}else{
			if(maps.get("MsgType").equals("text")){
				funOpinion.set("content", maps.get("Content")).set("state", 1).update();
				initFun("NONE");
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"谢谢你的反馈，我们会及时处理的哦！");
			}else{
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"意见反馈只能输入文字");
			}
		}
	}
}
