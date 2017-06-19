package com.wx.parse.function;

import java.util.Date;

import com.jfinal.kit.StrKit;
import com.wx.config.Common;
import com.wx.model.wx.FunSubway;
import com.wx.model.wx.Function;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月9日 下午7:53:20 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Subway extends BaseFunction {

	public Subway(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建一个查询事件
	 * @return
	 */
	public String create(String fromUserName){
		initFun("SUBWAY");
		FunSubway funSubway = FunSubway.dao.findByOpenId(fromUserName);
		if(funSubway == null){
			funSubway = new FunSubway();
			funSubway.set("openid", fromUserName).set("createdate", new Date().getTime()).save();
		}else{
			funSubway.set("start_station", "").set("end_station", "").set("createdate", new Date().getTime()).update();
		}
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"请输入始发站！");
	} 
	/**
	 * 搜索查询线路
	 * @param fromUserName
	 * @return
	 */
	public String search(String fromUserName){
		FunSubway funSubway = FunSubway.dao.findByOpenId(fromUserName);
		if(funSubway == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"当前使用功能出现故障，请使用下方菜单重新使用或者通过反馈将问题告诉我们，我们会及时向您回馈。");
		}
		if(StrKit.isBlank(funSubway.getStr("start_station"))){
			if(maps.get("MsgType").equals("text")){
				funSubway.set("start_station", maps.get("Content")).update();
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"请输入终点站");
			}else{
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"站点只能是文字！");
			}
		}else{
			if(maps.get("MsgType").equals("text")){
				//进行路线计算，并返回计算结果
				funSubway.set("start_station", maps.get("Content")).update();
                initFun("NONE");
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"计算结果");
			}else{
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"站点只能是文字！");
			}
			//进行路线计算
		}
	}
}
