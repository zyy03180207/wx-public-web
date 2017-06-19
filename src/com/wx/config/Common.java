package com.wx.config;

public class Common {
	//Http请求编码
	public static final String ENCODE = "UTF-8";
	//Http请求超时时间（秒）
	public static final int timeOutInSeconds = 5; 
	//微信请求URL
	public static final String WXURL = "https://api.weixin.qq.com/";
	//聚合数据天气接口
	public static final String WEATHERURL = "http://op.juhe.cn/onebox/weather/query";
	//聚合天气AppKey
	public static final String WeatherAppKey = "550276fba3d14768ae18b4e365937d55";
	//聚合图灵机器人AppKey
	public static final String AIAppKey = "3b9dfde62a434f7900e40a33ecc7e272";
	//微信APPID
	public static final String APPID = "wxabc2cdd631a15420";
	//微信SECRET
	public static final String SECRET = "bb9bc0ff9776220d29bb6a51ab87787f";
	//微信accesstoken
	public static String ACCESS_TOKEN = "这是一个accesstoken";
	//微信支付签名
	public static String TICKET = "";
	
	public static final int PERIOD = 3600*1000;
	//设置单聊超时时间
	public static final Long SINGLEOUT = 60*1000*15l;	//15分钟
	//统一回复消息
	public static final String CONTENT = Common.SYSTEMNOTIFICATION + "您还没有调用任何功能或者功能已超时，请从菜单中重新选择需要的功能，如有任何疑惑请联系客服。";
	//关注回复
	public static final String SUBSCRIBE = "欢迎关注我们的公众号";
	//系统提示
	public static final String SYSTEMNOTIFICATION = "【系统通知】";
	//开发者ID
	public static final String OPENSID = "gh_f1c96519fdc2";
}
