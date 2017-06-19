package com.wx.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Clear;
import com.jfinal.render.CaptchaRender;
import com.wx.model.Jcusr;
import com.wx.model.SecqurityGroup;
import com.wx.utils.Utility;
@Clear
public class LoginController extends BaseController {
	
	public void index(){
		getSession().removeAttribute("user");
		this.getSession().removeAttribute("userSecs");
		renderJsp("login.jsp");
	}
	
	public void toLogin() {
		System.out.println("Blog Controller add method");
		String username = getPara("loginname");
		String userRsaPwd = getPara("loginpwd");
//		RSAPrivateKey privateKey=getSessionAttr("privateKey");
		try {
//			String userPwd=RSAUtils.decryptByPrivateKey(userRsaPwd,privateKey);
			String yanzhengma = getPara("yzm"); 
			boolean loginSuccess = CaptchaRender.validate(this, yanzhengma);
			if(loginSuccess){//验证码校验
				Jcusr user = Jcusr.ju.findbyLogin(username);
				String mdpassword = Utility.encodeMD5Hex(userRsaPwd);
				if(user==null){
					setAttr("ok", "6");//找不到用户
				}else if(user.getStr("u_pwd").equalsIgnoreCase(mdpassword)) {//密码校验
					int uid=user.getInt("uid");
					Object sessionMap=getSession().getServletContext().getAttribute("sessionMap");
					
					boolean loginFlag=false;
					if(sessionMap!=null){
						HashMap<String,HttpSession> map=(HashMap<String,HttpSession>)sessionMap;
						if(map.size()!=0){							
							Iterator<Entry<String,HttpSession>> it=map.entrySet().iterator();
							while(it.hasNext()){
								Entry e=it.next();
								HttpSession sess=(HttpSession) e.getValue();
								Jcusr j=(Jcusr)sess.getAttribute("user");
								if(j!=null&&uid==j.getInt("uid")){
									loginFlag=true;
									
									map.remove(e.getKey());
									map.put(sess.getId(), sess);
									sess.invalidate();
									getSession().getServletContext().setAttribute("sessionMap",map);
								}
							}
						}
					}
//					if(loginFlag){
//						setAttr("ok","7");
//					}else{
						System.out.println(user.getStr("cloginpwd")+"<>"+userRsaPwd+"<>"+mdpassword);
						setSessionAttr("user", user);
						String action = SecqurityGroup.sg.getUserActionFirst(user.getInt("uid") + "");
						if(!Utility.isNull(action)){
							setAttr("action", this.getRequest().getContextPath() + action);
							setAttr("ok", "1");
							this.getSession().removeAttribute("privateKey");//登录成功，删除RSA私钥
						}else{
							setAttr("ok", "2");
						}
//					}
				}else{
					setAttr("ok", "0");
				}
				
			}else{
				setAttr("ok", "5");
			}
		} catch (Exception e) {
			setAttr("ok", "5");
		}
		
		renderJson();
	}

	public void toLoginOut() {
		System.out.println("toLoginOut");
		this.getSession().removeAttribute("userSecs");
		index();
	}
	@Clear
	public void imgRe() {  
        CaptchaRender img = new CaptchaRender();  
        render(img);  
    } 
}
