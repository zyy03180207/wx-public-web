<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	String err = "";
	if (request.getAttribute("err") != null) {

		err = request.getAttribute("err") + "";
		if (err.equals("sec")) {
%>alert('您没有执行此操作的权限！');<%
	} else {
%>alert(<%=err%>);<%
	}
	}
	String action = "";
	if (request.getAttribute("action") != null) {
		action = request.getAttribute("action") + "";
	}
%>
<html>
<head>
<meta charset="utf-8">
<meta name="keywords" content="公众号后台管理系统" />
<title>公众号后台管理平台</title>
<link href="<%=request.getContextPath()%>/css/login.css" type="text/css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="icon" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/RSA.js"></script>
<style type="text/css">
#logo {
	color: #FFFFFF;
	font-size: xx-large;
	font-family: 仿宋
}

#ll {
	color: #FFFFFF;
	font-size: larger;
	font-family: 仿宋
}

#lll {
	color: #FFFFFF;
	font-size: large;
	font-family: 仿宋
}
</style>
<script type="text/javascript">
 function getUrlStr(method){
	 var location = (window.location+'').split('/'); 
		var url = '/' + location[3] + '/'+method;
		return url;
 }

function refresh() {
	 var suiji = Math.random();
		var url = getUrlStr('login/imgRe?'+suiji);
	  $("#mathnum").attr("src",url);
	
 }

      function submiting() {
    	  
  	 	var loginpwd = $("#inputPassword").val();
  	 	var loginname = $("#inputName").val();
  	 	var yzm = $("#yzm").val();
  	 	
        $.ajax({
				type : "POST",
				url : "<%=request.getContextPath()%>/login/toLogin",
			data : "loginname=" + loginname + "&loginpwd=" + loginpwd + "&yzm="
					+ yzm,
			dataType : "json",
			success : function(msg) {
				refresh();
				if (msg.ok == "5") {
					alert("验证码输入不正确");
					return;
				} else if (msg.ok == "1") {
					top.window.location.href = msg.action;
				} else if (msg.ok == "2") {
					alert("您没有操作的权限！");
				} else if (msg.ok == "6") {
					alert("账号错误！");
				} else if (msg.ok == "7") {
					alert("此账号已登录");
				} else {
					alert("账号或密码错误");
					return;
				}
			},
			error : function(msg) {
				alert(msg);
			}
		});
	}

	function keyLogin() {
		if (event.keyCode == 13) {
			submiting();
		}
	}
</script>
</head>
<body onkeydown="keyLogin();">
	<form method="post" name="shoudanyun" action="">

		<section class="login">
			<b id="logo">十五度工作室</b></br>
			</br> <b id="ll">SHIWUDU STUDIO</b></br>
			</br>
			</br> <b id="lll">公众号后台管理系统</b></br>
			</br>
			<div class="loginCon">
				<input id="inputName" name="loginname" class="input-xlarge mt30"
					type="text" placeholder="用户名"> <input id="inputPassword"
					name="loginpwd" class="input-xlarge" type="password"
					placeholder="密码">
				<div class="yzmCon">
					<input name="loginyzm" type="text" id="yzm" class="yzmWith"
						placeholder="验证码">
					<div class="floatF">
						<img class="yzm" id="mathnum"
							src="<%=request.getContextPath()%>/login/imgRe"> <span
							id="mathnum1" onclick="refresh();" class="changeYzm">换一张</span>
					</div>

				</div>
				<button onclick="submiting();" type="button"
					class="btn btn-primary mb30">登录</button>
			</div>
		</section>
	</form>
</body>
</html>
