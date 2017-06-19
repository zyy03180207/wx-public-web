package com.wx.config;

import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.wx.controller.BaseController;
import com.wx.model.Jcusr;
import com.wx.model.SecqurityGroup;
import com.wx.model.VSecqurityGroup;
import com.wx.utils.Utility;

public class GlobalActionInter implements Interceptor{

	public void intercept(Invocation inv) {

		BaseController c = (BaseController)inv.getController();
		System.out.println("aaaaaaaaas--------------" + inv.getActionKey());
		//得到用户组所有权限
		Map m = Utility.getGroupSec(new SecqurityGroup(), "1");
		//得到所有权限
		VSecqurityGroup all = Utility.getGroupAll(new SecqurityGroup());
		c.getSession().setAttribute("sqll", all);

		if(c.getSession().getAttribute("user") == null){
			c.redirect("/");
			
		}else{
			Map userSecs = null;
			if(c.getSession().getAttribute("userSecs") != null){
				userSecs = (Map)c.getSession().getAttribute("userSecs");
			}
			Jcusr j = (Jcusr)c.getSession().getAttribute("user");
			System.out.println("cusrname:" + j.getStr("u_name"));
			if(userSecs == null){
				userSecs = SecqurityGroup.sg.getUserActions(j.getInt("uid") + "");
			}
			c.getSession().setAttribute("htmlMenu", Utility.getMenuHtml(m, inv.getActionKey(), userSecs));
			c.getSession().setAttribute("userSecs", userSecs);
			String action = inv.getActionKey();
			//拥有的权限可以执行
			if(userSecs.get(action) == null){
				//否则不能执行
				c.redirect("/");
				c.getSession().removeAttribute("user");
				c.getRequest().setAttribute("err", "sec");
			}else{
				inv.invoke();
			}
		}
		
		
		
		
	}

}
