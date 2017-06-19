package com.wx.utils.selector;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration.Node;
import org.apache.commons.configuration.XMLConfiguration;

public abstract class BaseSelector {
	public Map<String, Object> maps = new HashMap<String, Object>();
	public String xml;
	public BaseSelector(String xml) {
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

	public abstract String exect();
}
