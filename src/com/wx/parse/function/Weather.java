package com.wx.parse.function;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.utils.URLConnection;
import com.wx.xml.XMLCombination;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月11日 下午5:54:17 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class Weather extends BaseFunction {

	public Weather(String xml) {
		super(xml);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建天气查询
	 * @param fromUserName
	 * @return
	 */
	public String ctWeather(String fromUserName){
		initFun("WEATHER");
		return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"请输入城市名称！");
	}

	/**
	 * 查询城市天气
	 * @param fromUserName
	 * @return
	 */
	public String find(String fromUserName){
		try {
			String cityname = URLDecoder.decode((String) maps.get("Content"), "utf-8");
			System.out.println("cityname:"+cityname);
			String url = Common.WEATHERURL+"?cityname="+cityname+"&key="+Common.WeatherAppKey;
			String response = URLConnection.sendGet(url, "");
			System.out.println("response:"+response);
			JSONObject resJson = JSON.parseObject(response);
			String reason = resJson.getString("error_code");
			StringBuffer buffer = new StringBuffer();
			if(reason.equals("0")){
				JSONObject resultJson = resJson.getJSONObject("result");
				JSONObject dataJson = resultJson.getJSONObject("data");
				JSONObject realtimeJson = dataJson.getJSONObject("realtime");
				JSONObject weatherJson = realtimeJson.getJSONObject("weather");
				JSONObject windJson = realtimeJson.getJSONObject("wind");
				JSONObject lifeJson = dataJson.getJSONObject("life");
				JSONObject infoJson = lifeJson.getJSONObject("info");
				JSONArray chuanyiArray = infoJson.getJSONArray("chuanyi");
				JSONArray ganmaoArray = infoJson.getJSONArray("ganmao");
				JSONArray kongtiaoArray = infoJson.getJSONArray("kongtiao");
				JSONArray xicheArray = infoJson.getJSONArray("xiche");
				JSONArray yundongArray = infoJson.getJSONArray("yundong");
				JSONArray ziwaixianArray = infoJson.getJSONArray("ziwaixian");
				buffer.append("城市："+realtimeJson.getString("city_name")+"\n");
				buffer.append("日期："+realtimeJson.getString("date")+"\n");
				buffer.append("更新时间："+realtimeJson.getString("time")+"\n");
				buffer.append("温度："+weatherJson.getString("temperature")+"℃"+"\n");
				buffer.append("湿度："+weatherJson.getString("humidity")+"%rh"+"\n");
				buffer.append("信息："+weatherJson.getString("info")+"\n");
				buffer.append("风况："+windJson.getString("direct")+" "+windJson.getString("power")+"\n");
				buffer.append("穿衣：");
				for(int i=0;i<chuanyiArray.size();i++){
					buffer.append(chuanyiArray.getString(i)+"\n");
				}
				buffer.append("感冒：");
				for(int i=0;i<ganmaoArray.size();i++){
					buffer.append(ganmaoArray.getString(i)+"\n");
				}
				buffer.append("空调：");
				for(int i=0;i<kongtiaoArray.size();i++){
					buffer.append(kongtiaoArray.getString(i)+"\n");
				}
				buffer.append("洗车：");
				for(int i=0;i<xicheArray.size();i++){
					buffer.append(xicheArray.getString(i)+"\n");
				}
				buffer.append("运动：");
				for(int i=0;i<yundongArray.size();i++){
					buffer.append(yundongArray.getString(i)+"\n");
				}
				buffer.append("紫外线：");
				for(int i=0;i<ziwaixianArray.size();i++){
					buffer.append(ziwaixianArray.getString(i)+"\n");
				}
				JSONArray weather = dataJson.getJSONArray("weather");
				buffer.append("天气状况：{\n");
				for(int i=0;i<weather.size();i++){
					JSONObject otherJson = weather.getJSONObject(i);
					JSONObject info = otherJson.getJSONObject("info");
					JSONArray dayArray = info.getJSONArray("day");
					JSONArray nightArray = info.getJSONArray("night");
					buffer.append("日期："+otherJson.getString("date")+"\n");
					buffer.append("白天【");
					buffer.append("天气："+dayArray.getString(1)+"\n");
					buffer.append("温度："+dayArray.getString(2)+"℃\n");
					buffer.append("风向："+dayArray.getString(3)+"\n");
					buffer.append("风力："+dayArray.getString(4)+"\n");
					buffer.append("时间："+dayArray.getString(5)+"】\n");
					buffer.append("晚上【");
					buffer.append("天气："+nightArray.getString(1)+"\n");
					buffer.append("温度："+nightArray.getString(2)+"℃\n");
					buffer.append("风向："+nightArray.getString(3)+"\n");
					buffer.append("风力："+nightArray.getString(4)+"\n");
					buffer.append("时间："+nightArray.getString(5)+"】\n");
				}
				buffer.append("}");
				return XMLCombination.text(fromUserName, buffer.toString());
			}else{
				return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"获取天气失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return XMLCombination.text(fromUserName, Common.SYSTEMNOTIFICATION+"查询出现错误！");
		}
	}
}
