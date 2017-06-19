package com.wx.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;

import com.wx.config.Common;
import com.wx.entity.News;

import oracle.net.aso.b;

/**
 * XML文件组合
 * @author 张阳阳
 * @date 2016年10月19日19:03:41
 * @功能 组合返回xml
 */
public class XMLCombination {

	public static Map<String, Object> pares(String xml){
		Map<String, Object> maps = new HashMap<>();
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
			return maps;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String exect(Map<String, Object> map,String openId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>\n");
		for(String key : map.keySet()){
			if(key.equals("ToUserName")){
				buffer.append("<"+key+"><![CDATA["+openId+"]]></"+key+">\n");
			}else if(key.equals("FromUserName")){
				buffer.append("<"+key+"><![CDATA["+map.get("ToUserName")+"]]></"+key+">\n");
			}else if(key.equals("CreateTime")||key.equals("MsgId")){
				buffer.append("<"+key+">"+map.get(key)+"</"+key+">\n");
			}else{
				buffer.append("<"+key+"><![CDATA["+map.get(key)+"]]></"+key+">");
			}
		}
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 组合统一无法找到功能
	 * @return
	 */
	public static String fail(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+openid+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"text"+"]]></MsgType>");
		buffer.append("<Content><![CDATA["+Common.CONTENT+"]]></Content>");
		buffer.append("<MsgId>"+"1234567890123456"+"</MsgId>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复文本消息
	 * @param toUserName
	 * @param content	文本内容
	 * @return
	 */
	public static String text(String toUserName,String content){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"text"+"]]></MsgType>");
		buffer.append("<Content><![CDATA["+content+"]]></Content>");
		buffer.append("<MsgId>"+"1234567890123456"+"</MsgId>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复图片消息
	 * @param toUserName
	 * @param MediaId	通过素材管理中的接口上传多媒体文件，得到的id。
	 * @return
	 */
	public static String image(String toUserName,String MediaId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"image"+"]]></MsgType>");
		buffer.append("<Image>");
		buffer.append("<MediaId><![CDATA["+MediaId+"]]></MediaId>");
		buffer.append("</Image>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复语音消息
	 * @param toUserName
	 * @param MediaId 通过素材管理中的接口上传多媒体文件，得到的id
	 * @return
	 */
	public static String voice(String toUserName,String MediaId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"image"+"]]></MsgType>");
		buffer.append("<Voice>");
		buffer.append("<MediaId><![CDATA["+MediaId+"]]></MediaId>");
		buffer.append("</Voice>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复视频消息
	 * @param toUserName
	 * @param MediaId	通过素材管理中的接口上传多媒体文件，得到的id
	 * @param title	视频消息的标题
	 * @param description 视频消息的描述
	 * @return
	 */
	public static String video(String toUserName,String MediaId,String title,String description){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"video"+"]]></MsgType>");
		buffer.append("<Video>");
		buffer.append("<MediaId><![CDATA["+MediaId+"]]></MediaId>");
		buffer.append("<Title><![CDATA["+title+"]]></Title>");
		buffer.append("<Description><![CDATA["+description+"]]></Description>");
		buffer.append("</Video>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复音乐消息
	 * @param toUserName
	 * @param MediaId	缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 * @param title		标题
	 * @param description	描述
	 * @param MusicUrl	音乐链接
	 * @param HQMusicUrl	高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @return
	 */
	public static String music(String toUserName,String MediaId,String title,String description,String MusicUrl,String HQMusicUrl){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"music"+"]]></MsgType>");
		buffer.append("<Music>");
		buffer.append("<Title><![CDATA["+title+"]]></Title>");
		buffer.append("<Description><![CDATA["+description+"]]></Description>");
		buffer.append("<MusicUrl><![CDATA["+MusicUrl+"]]></MusicUrl>");
		buffer.append("<HQMusicUrl><![CDATA["+MusicUrl+"]]></HQMusicUrl>");
		buffer.append("<ThumbMediaId><![CDATA["+MediaId+"]]></ThumbMediaId>");
		buffer.append("</Music>");
		buffer.append("</xml>");
		return buffer.toString();
	}
	/**
	 * 回复图文消息
	 * @param toUserName
	 * @param ArticleCount	
	 * @param news
	 * @return
	 */
	public static String news(String toUserName,int ArticleCount,List<News> news){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<xml>");
		buffer.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
		buffer.append("<FromUserName><![CDATA["+Common.OPENSID+"]]></FromUserName>");
		buffer.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
		buffer.append("<MsgType><![CDATA["+"news"+"]]></MsgType>");
		buffer.append("<ArticleCount>"+news.size()+"</ArticleCount>");
		buffer.append("<Articles>");
		for(int i=0;i<news.size();i++){
			News news2 = news.get(i);
			buffer.append("<item>");
			buffer.append("<Title><![CDATA["+news2.getTitle()+"]]></Title>");
			buffer.append("<Description><![CDATA["+news2.getDescription()+"]]></Description>");
			buffer.append("<PicUrl><![CDATA["+news2.getPicUrl()+"]]></PicUrl>");
			buffer.append("<Url><![CDATA["+news2.getUrl()+"]]></Url>");
			buffer.append("</item>");
		}
		buffer.append("</Articles>");
		buffer.append("</xml>");
		return buffer.toString();
	}
}
