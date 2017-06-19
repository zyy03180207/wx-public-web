package com.wx.parse.function;

import java.util.Date;

import com.wx.config.Common;
import com.wx.model.wx.AboutPeople;
import com.wx.model.wx.CollectionPeople;
import com.wx.utils.TimeUtil;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月11日 下午3:00:31 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class CollectionUser extends BaseFunction {

	public CollectionUser(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 收藏用户
	 * @param openId
	 * @return
	 */
	public String collect(String fromUserName){
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		AboutPeople people = AboutPeople.dao.findByMyId(fromUserName, time);
		if(people == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你还没有约到人！");
		}
		CollectionPeople collectionPeople = new CollectionPeople();
		collectionPeople.set("myopenid", fromUserName).set("openid", people.getStr("openid")).set("createdate", new Date().getTime()).set("isno", 1).save();
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"已为你收藏该用户，你可以在收藏列表里找到TA！");
	}
	/**
	 * 拉黑用户
	 * @param fromUserName
	 * @return
	 */
	public String pullBlack(String fromUserName){
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		AboutPeople people = AboutPeople.dao.findByMyId(fromUserName, time);
		if(people == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你还没有约到人！");
		}
		CollectionPeople collectionPeople = new CollectionPeople();
		collectionPeople.set("myopenid", fromUserName).set("openid", people.getStr("openid")).set("createdate", new Date().getTime()).set("isno", 0).save();
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"已为你拉黑该用户！");
	}
	/**
	 * 举报用户
	 * @param fromUserName
	 * @return
	 */
	public String report(String fromUserName){
		String time = TimeUtil.getTimeOut(Common.SINGLEOUT);
		AboutPeople people = AboutPeople.dao.findByMyId(fromUserName, time);
		if(people == null){
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你还没有约到人！");
		}
		CollectionPeople collectionPeople = new CollectionPeople();
		collectionPeople.set("myopenid", fromUserName).set("openid", people.getStr("openid")).set("createdate", new Date().getTime()).set("isno", 1).save();
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"你已举报该用户，我们会尽快！");
	}
}
