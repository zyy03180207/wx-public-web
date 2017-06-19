package com.wx.parse.msg;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.XMLConfiguration;

import com.wx.utils.emnu.FunctionType;

import org.apache.commons.configuration.HierarchicalConfiguration.Node;

public abstract class BaseMsg {

	public Map<String, Object> maps = new HashMap<String, Object>();
	public String xml;
	public BaseMsg(String xml) {
		// TODO Auto-generated constructor stub
		this.xml = xml;
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
	 * 转发回复消息组包，用户发送的内容传给对应用户
	 * @param maps	消息内容
	 * @param openid 接收消息的用户id
	 * @return
	 */
	public String replyMsg(List<Map<String, Object>> maps,String openid){
		
		return "";
	}
	
	public abstract String repMsg(FunctionType type);
	
}
