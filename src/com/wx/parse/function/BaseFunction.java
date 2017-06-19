package com.wx.parse.function;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;

import com.wx.model.wx.Function;
import com.wx.xml.XMLParse;

public class BaseFunction {

	public Map<String, Object> maps = new HashMap<String, Object>();
	
	public BaseFunction(String xml) {
		try{
			InputStream in = new ByteArrayInputStream(xml.getBytes("UTF-8"));  
			XMLConfiguration configuration = new XMLConfiguration();
			configuration.load(in);
			Node node = configuration.getRoot();
			List children = node.getChildren();
			for(int i=0;i<children.size();i++){
				Node node2 = (Node) children.get(i);
				String name = node2.getName();
				String values = (String) node2.getValue();
				maps.put(name, values);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 修改function功能选项
	 */
	public void initFun(String fun){
		String fromUserName = (String) maps.get("FromUserName");
		Function function = Function.dao.findByOpenId(fromUserName);
		if(function == null){
			function = new Function();
			function.set("openid", fromUserName).set("function", fun).set("createdate", new Date().getTime()).save();
		}else{
			function.set("function", fun).set("createdate", new Date().getTime()).update();
		}
	}
	
}
