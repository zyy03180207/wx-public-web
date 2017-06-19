package com.wx.controller;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

/** * @author  作者 E-mail: * 
@date 创建时间：2017年2月15日 上午11:25:41 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class MatterController extends BaseController {
	
	public void index() {
		
	}
	/**
	 * 推送素材
	 */
	public void push() {
		renderJsp("pushMatter.jsp");
	}
	
	public void seach() {
		renderJsp("allMatter.jsp");
	}
	
}
