package com.wx.model.wx;

import com.jfinal.plugin.activerecord.Model;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月9日 下午8:01:17 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class FunSubway extends Model<FunSubway> {

	public static final FunSubway dao = new FunSubway();
	
	public FunSubway findByOpenId(String openId){
		return dao.findFirst("SELECT * FROM fun_subway WHERE OPENID = ?", openId);
	}
}
