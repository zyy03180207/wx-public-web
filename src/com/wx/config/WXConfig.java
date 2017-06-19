package com.wx.config;

import java.util.Timer;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.wx.controller.BackstageController;
import com.wx.controller.CardController;
import com.wx.controller.IndexController;
import com.wx.controller.JcuserController;
import com.wx.controller.LoginController;
import com.wx.controller.MatterController;
import com.wx.controller.MenuController;
import com.wx.controller.UserController;
import com.wx.controller.WXPayController;
import com.wx.model.Jcusr;
import com.wx.model.SecqurityGroup;
import com.wx.model.wx.AboutPeople;
import com.wx.model.wx.CollectionPeople;
import com.wx.model.wx.FunSubway;
import com.wx.model.wx.Function;
import com.wx.model.wx.Jcuser;
import com.wx.model.wx.Message;
import com.wx.model.wx.FunOpinion;
import com.wx.model.wx.Template;
import com.wx.timeTask.AccessTask;

public class WXConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		PropKit.use("little_config.txt");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/", IndexController.class, "/");
		me.add("/card", CardController.class, "/pages/wx");
		me.add("/login",LoginController.class, "/pages");
		me.add("/sec",JcuserController.class,"/pages");
		me.add("/menu", MenuController.class, "pages/menu");
		me.add("/user", UserController.class, "pages/user");
		me.add("/bask", BackstageController.class, "/pages");
		me.add("/pay", WXPayController.class, "/pages/wxpay");
		me.add("/matter", MatterController.class, "/pages/matter");
	}

	/**
	 * 配置数据源1
	 * @return
	 * 
	 */
	public static C3p0Plugin  createDruidPlugin() {
		String jdbcUrl = new String(PropKit.get("jdbcUrl"));
		String driver = PropKit.get("driverClass");
		String username = new String(PropKit.get("username"));
		String password = new String(PropKit.get("password"));
		return new C3p0Plugin (jdbcUrl, username, password, driver);
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		// 配置数据库连接池插件
		C3p0Plugin plugin = createDruidPlugin();
		me.add(plugin);
		ActiveRecordPlugin arp=new ActiveRecordPlugin(plugin);
		arp.setShowSql(true);
		arp.setDialect(new MysqlDialect());
		//show sql
//		String isShowSql = PropKit.get("showsql");
		arp.setShowSql(true);
		// 所有配置在 MappingKit 中搞定
		arp.addMapping("single_chat", "cid", AboutPeople.class);
		arp.addMapping("collection_people", "cid", CollectionPeople.class);
		arp.addMapping("jcuser", "cid", Jcuser.class);
		arp.addMapping("message", "cid", Message.class);
		arp.addMapping("template", "cid", Template.class);
		arp.addMapping("now_function", "cid", Function.class);
		arp.addMapping("fun_subway", "cid", FunSubway.class);
		arp.addMapping("opinion", "cid", FunOpinion.class);
		
		arp.addMapping("wx_secqurity_group", "gid", SecqurityGroup.class);
		arp.addMapping("wx_user", "uid", Jcusr.class);
		me.add(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new GlobalActionInter());
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		me.add(new ContextPathHandler());
	}

	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		super.afterJFinalStart();
//		Timer timer = new Timer();
//		//每间隔一小时执行一次更新
//		timer.schedule(new AccessTask(), 1000, Common.PERIOD);
	}

	public static void main(String[] args) {
		//		JFinal.start("WebRoot", 80, "/", 5);
	}
}
