package com.wx.model;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wx.utils.StringUtil;

/**
 * 后台管理用户
 * @author 张阳阳
 * @date 2016年10月28日
 * @time 下午6:15:58
 * @function
 */
public class Jcusr extends Model<Jcusr> {
public static final Jcusr ju = new Jcusr();
	
	public  Page<Record> getByPhone(int pageNumber, int pageSize, String phone){
		String sqlEx = " from wx_user a LEFT JOIN wx_usr_from_group b ON a.uid=b.uid "
				+ "LEFT JOIN wx_secqurity_group c ON b.gid=c.gid ";
		
		if (StringUtil.strisNotNull(phone)) {
			sqlEx +=  "where a.u_login = ? order by a.create_date desc";
			return Db.paginate(pageNumber, pageSize, "select a.*,c.`action`", sqlEx,phone);
		}
		sqlEx += "order by a.create_date desc";
		
		return Db.paginate(pageNumber, pageSize, "select a.*,c.`action`", sqlEx);
		
	}
	
	public Jcusr findbyLogin(String p){
		return ju.findFirst("select * from wx_user where u_login = ? and del_flag='0' order by uid desc", p);
	}

	
	public void upEnable(String cid, String cstate){
		
		Jcusr c = ju.findFirst("select * from wx_user where uid = ?", cid);
		c.set("u_state", cstate).update();
		
	}
	
	@Before(Tx.class)
	public void upUser(String gid, String[] sids){
		
		Db.update("delete from wx_usr_from_group where gid = ?", gid);
		if(sids != null){
			for(int i = 0 ; i < sids.length ; i ++){
				
				Db.update("insert into wx_usr_from_group values(?,?)", gid, sids[i]);
				
			}
		}
		
	}
	
	public Jcusr getUsrById(String id){
		return ju.findById(id);
	}
	
	public Jcusr findByMerId(Integer merid) {
		return ju.findFirst("select * from wx_user a where a.pater_id=? and a.pater_type='BUS_MER'",merid);
	}
}
