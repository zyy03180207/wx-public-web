package com.wx.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.xml.sax.SAXException;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.tencent.common.XMLParser;
import com.wx.utils.selector.EventSelector;
import com.wx.utils.selector.FunctionSelector;
import com.wx.utils.selector.MsgSelector;
import com.wx.xml.XMLParse;
@Clear
public class IndexController extends BaseController {
	
	public void index(){

		render("index.html");
	}


	public void msg(){
		try {
			String echostr = this.getPara("echostr");
			System.out.println("参数："+echostr);
			if(!StrKit.isBlank(echostr)){
				this.renderText(echostr);
			}else{
				String xml = HttpKit.readData(this.getRequest());
				System.out.println("请求参数："+xml);
				XMLParse parse = new XMLParse();
				Map<String, Object> map = parse.parse(xml);
				String xmls = "";
				switch ((String)map.get("MsgType")) {
				//事件消息
				case "event":
					EventSelector eventSelector = new EventSelector(xml);
					xmls = eventSelector.exect();
					break;

				default:
					FunctionSelector functionSelector = new FunctionSelector(xml);
					xmls = functionSelector.exect();
					break;
				}
				System.out.println("返回参数："+xmls);
				//返回消息给微信
				this.renderText(xmls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.render("success");
		}
	}

}
