package com.wx.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VSecqurityGroup {
	
	private String sid;
	private String ppsid;
	private String menuName;
	private String psid;
	private String pname;
	private String des;
	private String action;
	private String seq;
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}
	private String gid;
	private String icon;
	private String ismenu;
	
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	private List<VSecqurityGroup> childs = new ArrayList();;

	public List<VSecqurityGroup> getChilds() {
		/*Collections.sort(childs, new Comparator<VSecqurityGroup>() {
            public int compare(VSecqurityGroup arg0, VSecqurityGroup arg1) {
            	
            	int i = 0;
            	int f = 0;
            	if(arg0 != null && arg0.getSeq() != null){
	            	try {
						i = Integer.valueOf(arg0.getSeq());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						i = 0;
					}
            	}
            	
            	if(arg1 != null && arg1.getSeq() != null){
	            	try {
						f = Integer.valueOf(arg1.getSeq());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						f = 0;
					}
            	}
            	if(i > f)
            		return 1;
            	else
            		return 0;
            }
        });*/
		return childs;
	}

	public void setChilds(VSecqurityGroup c) {
		childs.add(c);
	}

	public void o2m(Object[] o){
		if(o.length > 7){
			seq = o[9] + "";
			icon = (String)o[8];
			ismenu = (String)o[7];
		}
		sid = o[6] + "";
		ppsid = o[5] + "";
		menuName = (String)o[4];
		psid = o[3] + "";
		pname = (String)o[2];
		des = (String)o[1];
		action = (String)o[0];
	}
	
	public void o2mg(Object[] o){
		sid = o[5] + "";
		gid = o[4] + "";
		psid = o[3] + "";
		pname = (String)o[2];
		des = (String)o[1];
		action = (String)o[0];
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPpsid() {
		return ppsid;
	}
	public void setPpsid(String ppsid) {
		this.ppsid = ppsid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getPsid() {
		return psid;
	}
	public void setPsid(String psid) {
		this.psid = psid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
