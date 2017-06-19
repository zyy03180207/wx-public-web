package com.wx.process;

import com.alibaba.fastjson.JSONObject;

public class HttpJson implements Handle {
	
	private Handle task;

	public HttpJson(Handle task) {
		this.task = task;
	}
	
	@Override
	public String execute(String text, String url) {
		// TODO Auto-generated method stub
		
		return task.execute(url,text);
	}

}
