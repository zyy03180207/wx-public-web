package com.wx.entity;
/**
 * 图文消息回复
 * @author 张阳阳
 * @date 2016年10月24日21:42:22
 */
public class News {
	/**图文消息标题*/
	public String Title;
	/**图文消息描述*/
	public String Description;
	/**图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200*/
	public String PicUrl;
	/**点击图文消息跳转链接*/
	public String Url;
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
