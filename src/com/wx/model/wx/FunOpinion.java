package com.wx.model.wx;

import com.jfinal.plugin.activerecord.Model;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月9日 下午9:45:57 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class FunOpinion extends Model<FunOpinion> {

	public static final FunOpinion dao = new FunOpinion();
	
	public FunOpinion findByOpenId(String openId){
		return dao.findFirst("SELECT * FROM opinion WHERE openid = ? AND state = '0'", openId);
	}
}
