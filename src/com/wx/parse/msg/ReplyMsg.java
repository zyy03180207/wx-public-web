package com.wx.parse.msg;

import com.alibaba.fastjson.JSONObject;
import com.wx.config.Common;
import com.wx.utils.URLConnection;

/** * @author  作者 E-mail: * 
@date 创建时间：2016年11月10日 下午8:02:47 * 
@version 1.0 * 
@parameter  * 
@since  * 
@return  */
public class ReplyMsg {
	
	public static String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+Common.ACCESS_TOKEN;
	/**
	 * 回复文本消息
	 * @param openId
	 * @param content
	 * @return
	 */
	public static String text(String openId,String content){
		JSONObject json = new JSONObject();
		JSONObject text = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "text");
		text.put("content", content);
		json.put("text", text);
		System.out.println("json："+json.toJSONString());
		return URLConnection.sendPost(url, json.toJSONString());
	}
	/**
	 * 回复图片消息
	 * @param openId
	 * @param media_id
	 * @return
	 */
	public static String image(String openId,String media_id){
		JSONObject json = new JSONObject();
		JSONObject image = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "image");
		image.put("media_id", media_id);
		json.put("image", image);
		System.out.println("json："+json.toJSONString());
		return URLConnection.sendPost(url, json.toJSONString());
	}
	/**
	 * 回复语音消息
	 * @param openId
	 * @param media_id
	 * @return
	 */
	public static String voice(String openId,String media_id){
		JSONObject json = new JSONObject();
		JSONObject voice = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "voice");
		voice.put("media_id", media_id);
		json.put("voice", voice);
		System.out.println("json："+json.toJSONString());
		return URLConnection.sendPost(url, json.toJSONString());
	}
	/**
	 * 回复视频消息
	 * @param openId
	 * @param media_id
	 * @param thumb_media_id
	 * @param title
	 * @param description
	 * @return
	 */
	public static String video(String openId,String media_id,String thumb_media_id,String title,String description){
		JSONObject json = new JSONObject();
		JSONObject video = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "video");
		video.put("media_id", media_id);
		video.put("thumb_media_id", thumb_media_id);
		video.put("title", title);
		video.put("description", description);
		json.put("video", video);
		System.out.println("json："+json.toJSONString());
		return URLConnection.sendPost(url, json.toJSONString());
	}
	/**
	 * 回复音乐消息
	 * @param openId
	 * @param title
	 * @param description
	 * @param musicurl
	 * @param hqmusicurl
	 * @param thumb_media_id
	 * @return
	 */
	public static String music(String openId,String title,String description,String musicurl,String hqmusicurl,String thumb_media_id){
		JSONObject json = new JSONObject();
		JSONObject music = new JSONObject();
		json.put("touser", openId);
		json.put("msgtype", "music");
		music.put("title", title);
		music.put("description", description);
		music.put("musicurl", musicurl);
		music.put("hqmusicurl", hqmusicurl);
		music.put("thumb_media_id", thumb_media_id);
		json.put("music", music);
		System.out.println("json："+json.toJSONString());
		return URLConnection.sendPost(url, json.toJSONString());
	}
	
//	public static String news(String openId,String title,String description,String musicurl,String hqmusicurl,String thumb_media_id){
//		JSONObject json = new JSONObject();
//		JSONObject news = new JSONObject();
//		json.put("touser", openId);
//		json.put("msgtype", "news");
//		news.put("title", title);
//		news.put("description", description);
//		news.put("musicurl", musicurl);
//		news.put("hqmusicurl", hqmusicurl);
//		news.put("thumb_media_id", thumb_media_id);
//		json.put("news", json.toJSONString());
//		return URLConnection.sendPost(url, json.toJSONString());
//	}
	
}
