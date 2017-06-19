package com.wx.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wx.utils.StringUtil;
import com.wx.utils.Utility;
/**
 * 权限管理
 * @author 张阳阳
 * @date 2016年10月10日15:30:39
 * 
 */
@SuppressWarnings("serial")
public class SecqurityGroup extends Model<SecqurityGroup> {
	
	public static final SecqurityGroup sg = new SecqurityGroup();
	
	public Page<SecqurityGroup> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from wx_secqurity_group jt");
	}

	
	public SecqurityGroup getSG(String gid){

		return sg.findById(gid);
	}
	
	public Page<Record> getPage(int currentIndex, int pageSize, String name) {
		Page<Record> xxPage;
		String sql = "select * ";
		String sqles = " from wx_secqurity_group where 1=1 ";
	
		String namestr = "%"+name+"%";
		if(StringUtil.strisNotNull(name)){
			sqles += " and name like ? ";
			xxPage = Db.paginate(currentIndex, pageSize, sql, sqles, namestr);
		}else{
			sqles += " ";
			xxPage = Db.paginate(currentIndex, pageSize, sql, sqles);
		}

		return xxPage;
	}
	
	@Before(Tx.class)
	public void upGroup(String gid, String[] sids){
		
		Db.update("delete from wx_sec_from_group where gid = ?", gid);
		if(sids != null){
			for(int i = 0 ; i < sids.length ; i ++){
				
				Db.update("insert into wx_sec_from_group values(?,?)", gid, sids[i]);
				
			}
		}
		
	}
	
	@Before(Tx.class)
	public String addGroup(String name, String action, String[] sids){
		
		String gid = "";
		if(!Utility.isNull(name) && !Utility.isNull(action)){
			SecqurityGroup _sg = new SecqurityGroup();
			_sg.set("name", name).set("action", action).save();
			gid = _sg.getInt("gid") + "";
			if(sids != null){
				for(int i = 0 ; i < sids.length ; i ++){
					
					Db.update("insert into wx_sec_from_group values(?,?)", gid, sids[i]);
					
				}
			}
		}
		
		return gid;
	}
	
	public String getUserActionFirst(String uid){
		Record r = Db.findFirst("select s.* from wx_sec_from_group g,wx_secqurity s,wx_secqurity_group sg,wx_usr_from_group u where g.sid = s.sid and sg.gid = u.gid and sg.gid = g.gid and u.uid = ? and s.ismenu = '1' order by s.seq desc", uid);
		String ac = "";
		if(r != null && r.getStr("action") != null){
			ac = r.getStr("action");
		}
		
		return ac;
	}
	
	public Map getUserActions(String uid){
		Map m = new HashMap();
		List<Object> l = Db.query("select s.* from wx_sec_from_group g,wx_secqurity s,wx_secqurity_group sg,wx_usr_from_group u where g.sid = s.sid and sg.gid = u.gid and sg.gid = g.gid and u.uid = ?", uid);
		if(l != null){
			for(int i = 0; i < l.size() ; i ++){
				Object[] o = (Object[])l.get(i);
				String action = o[1] + "";
				String sid = o[0] + "";
				
				m.put(action, sid);
			}
		}
		
		return m;
	}
	
	public List getGroupSec(String gid){
		
		List res = new ArrayList();
		Map mseq = new HashMap();
		Map allsec = new HashMap();
		
		List<Object> gls = Db.query("select sq1.action, sq1.des, sq1.menu_name pname, sq1.psid, sq2.menu_name,sq2.psid ppsid, sq2.sid  from wx_secqurity sq1, wx_secqurity sq2 where sq2.psid = sq1.sid");
		List<Object> ls = Db.query("select sq1.action, sq1.des, sq1.menu_name pname, sq1.psid, sq2.* from wx_secqurity sq1, wx_sec_from_group sq2 where sq2.sid = sq1.sid and sq2.gid = ?" ,gid);
	
		
		//sid action des menuname psid menuname ppsid
		
		if(gls != null){
			for(int i = 0; i < gls.size() ; i ++){
				Object[] o = (Object[])gls.get(i);
				VSecqurityGroup vsg = new VSecqurityGroup();
				vsg.o2m(o);
				allsec.put(vsg.getSid(), vsg);

			}
		}
		
		if(ls != null){
			
			for(int i = 0; i < ls.size() ; i ++){
				Object[] o = (Object[])ls.get(i);
				VSecqurityGroup vsg = new VSecqurityGroup();
				vsg.o2mg(o);
				
				mseq.put(vsg.getSid(), vsg);
			}
		}
		
		res.add(mseq);
		res.add(allsec);
		
		return res;
		
	}
	
	public Map getAllSecqurityAction(){
		Map mm = new HashMap();
		
		List<Object> ls = Db.query("select sq2.action, sq1.des, sq1.menu_name pname, sq1.psid, sq2.menu_name,sq2.psid ppsid, sq2.sid,sq2.ismenu,sq2.icon,sq2.seq from wx_secqurity sq1, wx_secqurity sq2 where sq2.psid = sq1.sid order by sq1.seq");
		if(ls != null){
			for(int i = 0; i < ls.size() ; i ++){
				Object[] o = (Object[])ls.get(i);
				VSecqurityGroup vsg = new VSecqurityGroup();
				vsg.o2m(o);
				mm.put(vsg.getSid(), vsg);

			}
		}
		return mm;
	}
	
	/**
	 * 得到用户组所有可执行的权限
	 * @param gid
	 * @return
	 */
	public Map getGroupSecqurityAction(String gid){
		
		Map mm = new HashMap();
		List<Object> ls = Db.query("select sq1.action, sq1.des, sq1.menu_name pname, sq1.psid, sq2.* from wx_secqurity sq1, wx_sec_from_group sq2 where sq2.sid = sq1.sid and sq2.gid = ?", gid);
		if(ls != null){
			for(int i = 0; i < ls.size() ; i ++){
				Object[] o = (Object[])ls.get(i);
				mm.put(o[1] + "", o[0] + "");
			}
		}
		
		return mm;
	}
}
