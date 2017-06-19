package com.wx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.smartcardio.Card;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.wx.config.Common;
import com.wx.entity.CardNews;
import com.wx.process.HttpJson;
import com.wx.process.HttpRequest;
import com.wx.utils.URLConnection;
@Clear
public class CardController extends BaseController {

	public void index(){
		try{
			String url = Common.WXURL+"cgi-bin/material/batchget_material?access_token="+Common.ACCESS_TOKEN;
			JSONObject json = new JSONObject();
			json.put("type", "news");
			json.put("offset", 0);
			json.put("count", 30);
			String jsonStr = json.toJSONString();
			String result = URLConnection.sendPost(url, jsonStr);
			System.out.println("编码前"+result);
			result = new String(result.getBytes(),"UTF-8");
			System.out.println("编码后"+result);
			List<CardNews> cards = new ArrayList<>();
			JSONObject object = JSON.parseObject(result);
			JSONArray array = object.getJSONArray("item");
			for(int i=0;i<array.size();i++){
				JSONObject object2 = array.getJSONObject(i);
				JSONObject content = object2.getJSONObject("content");
				JSONArray news_item = content.getJSONArray("news_item");
				for(int j=0;j<news_item.size();j++){
					CardNews card = (CardNews) JSON.parseObject(news_item.getJSONObject(j).toJSONString(),CardNews.class);
					cards.add(card);
				}
			}
			this.setAttr("cards", cards);
			this.setAttr("ss", "sssss");
			renderJsp("card.jsp");
		}catch(Exception e){
			
		}
	}
	
	public void card(){
		
	}

}
