package com.wx.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
/**
 *json工具类
 * **/
public class JsonUtil {
	private static Gson gjson=new GsonBuilder() .disableHtmlEscaping().create();
	//将json字符串转换成实体类
	public static <T> Object JsonDeserialization(String input,Class<T> massge){
		Object jobject=null;
		 try {
			jobject=gjson.fromJson(input, massge);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return jobject;
	}
	
	//将实体类转换成json字符串
	public static String JsonSerializer(Object obj){
		String json="";
		
		try {
			json=gjson.toJson(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	 public static <T> String BEANLIST_TO_JSON(List<T> list)
	    {
	        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	        JsonArray array = new JsonArray();
	        array = (JsonArray) gson.toJsonTree(list);
	        return array.toString();
	    }
	 
	@SuppressWarnings("unchecked")
    public static <T> List<T> JSON_TO_BEANLIST(String jsonString, Class<T> clazz)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        jsonString = jsonString.substring(jsonString.indexOf("[") + 1, jsonString.lastIndexOf("]"));
        String[] ss = jsonString.split("},");
        List list = new ArrayList();
        for (int i = 0; i < ss.length; i++)
        {
            String s = ss[i];
            if (i != ss.length - 1)
            {
                s += "}";
            }
           Object o = gson.fromJson(s, clazz);
            list.add(o);
        }
        return list;
    }
	
	public static String ListMapToJson(List<Map> list){
		JSONArray _json=JSONArray.fromObject(list);
		return _json.toString();
	}
	//map转json
	 public static String  MAP_TO_JSON(Map map){
		 JSONArray _json=JSONArray.fromObject(map);
			return _json.toString();
	    	
	    }
	
	public static void main(String[] args){
		//转化json
		List<Map<String,Object>> _list=new ArrayList<Map<String,Object>>();
		Map<String,Object> _map=new HashMap<String,Object>();
		_map.put("test", "test");
		_map.put("haha", "haha");
		_list.add(_map);

		JSONArray _json=JSONArray.fromObject(_list);

		//解json
		//List test=(List) JSONArray.toCollection(_json);
		//for(int len=0;len<test.size();len++){
		System.out.println(_json.toString());
		
		}

}
