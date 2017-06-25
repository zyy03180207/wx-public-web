<%@page import="com.wx.utils.wx.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wx.config.*"%>
<%@ page import="com.wx.utils.wxpay.Signature"%>
<%@ page import="com.wx.utils.wx.*" %>
<%
	String openId = request.getAttribute("openid").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title></title>
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<link href="<%=request.getContextPath()%>/css/mui.min.css"
	rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

.hd {
	height: 100vh;
	background: #6641E2;
}

.money {
	width: 100%;
	height: 40vh;
	background: #2D2E30;
}

.tl {
	width: 100%;
	height: 50vh;
	background: #FFFFFF;
}

td {
	text-align: center;
	font-size: 40px;
	color: #fff;
}

.btqr {
	width: 100%;
	height: 10vh;
	background: #6ECC00;
	font-size: 4vh;
	border: 0px;
	border-radius: 0px;
	color: #FFF;
}

.lb {
	width: 100%;
	color: #FFF;
	height: 8vh;
	text-align: center;
	line-height: 8vh;
	font-size: 4vh;
	overflow: hidden;
}

.bt {
	margin: 0px;
	width: 90%;
	height: 90%;
	font-size: 4.5vh;
	border: 1px #CCCCCC solid;
}

.line {
	width: 100%;
	height: 10vh;
	position: relative;
}

.bottom_line {
	width: 60vw;
	height: 0.8vh;
	bottom: 0px;
	left: 50%;
	margin-left: -30vw;
	position: absolute;
	background: #6ECC00;
}

.money_et {
	width: 100vw;
	height: 10vh;
	border: 0px;
	color: #FFFFFF;
	font-size: 6vh;
	text-align: center;
	line-height: 10vh;
	background: #2D2E30;
}
</style>
</head>
<body onload="config();">
	<div class="hd">
		<div class="money">
			<div class="lb">收款</div>
			<div class="lb">收款金额(元)</div>
			<div class="line">
				<input class="money_et" value="0.00" readonly="readonly" />
				<div class="bottom_line"></div>
			</div>
		</div>
		<button id="btqr" class="btqr" value="qr">确认支付</button>
		<table class="tl">
			<tbody>
				<tr>
					<td><button id="bt1" class="bt" value="1">1</button></td>
					<td><button id="bt2" class="bt" value="2">2</button></td>
					<td><button id="bt3" class="bt" value="3">3</button></td>
				</tr>
				<tr>
					<td><button id="bt4" class="bt" value="4">4</button></td>
					<td><button id="bt5" class="bt" value="5">5</button></td>
					<td><button id="bt6" class="bt" value="6">6</button></td>
				</tr>
				<tr>
					<td><button id="bt7" class="bt" value="7">7</button></td>
					<td><button id="bt8" class="bt" value="8">8</button></td>
					<td><button id="bt9" class="bt" value="9">9</button></td>
				</tr>
				<tr>
					<td><button id="btd" class="bt" value=".">.</button></td>
					<td><button id="bt0" class="bt" value="0">0</button></td>
					<td><button id="btx" class="bt" value="x">x</button></td>
				</tr>
			</tbody>
		</table>

	</div>
</body>
<script src="<%=request.getContextPath()%>/js/wxpay.js"></script>
<script src="<%=request.getContextPath()%>/js/sha1.js"></script>
<script type="text/javascript">
	function config() {
		var sign = <%=new Signature()%>;
		var appip = '<%=Signature.AppId%>';
		var timestamp = '<%=Signature.ctTimestamp()%>';
		var nonceStr = '<%=Signature.ctNonceStr(32)%>';
		var jsApiList = ['chooseWXPay'];
		wx.config({
			debug : false,
			appId : '<%=Signature.AppId%>',
			timestamp : '<%=Signature.timestamp%>',
			nonceStr : '<%=Signature.nonceStr%>',
			signature : '<%=Signature.sign("http://m.zyywxgz.com/WX/pay/webpay")%>',
			jsApiList: ['chooseWXPay']
		});
	} 
	
	function paying() {
		var money = moneys;
		var appIp = '<%=Signature.AppId%>';
		var openId = '<%=openId%>';
		var prepay_id = '';
		var timestamp = '<%=Signature.timestamp%>';
		var nonceStr = '<%=Signature.nonceStr%>';
		var packag = 'prepay_id=wx20161202112258ad7168cdf70423983971';
		var signType = 'MD5';
		var dic = [appIp,timestamp,nonceStr,packag,signType];
		var paySign = signaturemd5(dic);
		wx.chooseWXPay({
		    timestamp: '<%=Signature.timestamp%>',  
		    nonceStr: '<%=Signature.nonceStr%>', 
		    package : 'prepay_id=wx20161202112258ad7168cdf70423983971', //统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
		    signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		    paySign: '<%=Signature.signPay("wx20161202112258ad7168cdf70423983971");%>', // 支付签名
		    success: function (res) {
		        // 支付成功后的回调函数
		        alert("支付成功");
		    }
		});
	}

		$("button[id^='bt']").click(function(){
			switch($(this).val()){
				case "qr":
					$.ajax({
						
					});
				break;
				case ".":
				
				if($(".money_et").val().indexOf(".",0)==-1){
					if($(".money_et").val()==""){
						$(".money_et").val($(".money_et").val()+"0.");
					}else{
						$(".money_et").val($(".money_et").val()+".");
					}
				}
				break;
				case "x":
					$(".money_et").val($(".money_et").val().substring(0,$(".money_et").val().length-1));
				break;
				case "0":
					if($(".money_et").val()==""){
						$(".money_et").val($(".money_et").val()+"");
					}else{
						$(".money_et").val($(".money_et").val()+"0");
					}
				break;
				default:
					$(".money_et").val($(".money_et").val()+$(this).val());
				break;
			}
		});
</script>
</html>