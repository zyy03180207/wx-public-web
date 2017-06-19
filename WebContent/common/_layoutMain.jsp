<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="UTF-8" xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="bookmark" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="icon" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/img/favicon.ico"
	mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
<title>公众号后台管理平台</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/lighting_reach.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Font-Awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bigAutoComplete.css" />
<link
	href="<%=request.getContextPath()%>/My97DatePicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">

<!--[if IE 7]>
	<link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css">
	<![endif]-->
<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-bigAutocomplete.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/laydate/laydate.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/lighting_reach.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/js/html5shiv.js"
	type="text/javascript"></script>
<script type="text/javascript">

/*提交表单*/
function goWhere(page){
	showLoading();
	var pageText = page != '-1'?page:$("#J_text").val();
	$("#pageIndex").val(pageText);
	$("#main_Form").submit();
}
function go(){

	$("#main_Form").submit();
}
function goto(path){

	window.location.href = path;
}
//判断是否是金额
function isMoney(money){
	return /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/.test(money);
}
//分页输入框回车事件
function enterSubmit(total,e)
{
     if(window.event)
         keyPressed = window.event.keyCode; // IE
     else
        keyPressed = e.which; // Firefox
        
     if(keyPressed==13)
    { 
    	var page = $("#J_text").val();
    	if(page<=total || page>=1 ){goWhere(page); return true;}
    }
}

function isConfirmGoto(title, url){
	 if(confirm(title))
	 {
	 	window.location.href = url;
	 }
}
function showLoading(){
	var webH = $(document).height(); // 获取网页内容高度
	var webW = $(document).width(); // 获取网页内容高度
	$("#loading").height(webH);
	$("#loading").width(webW);
	$("#loading").show();
}
function hideLoading(){
	$("#loading").hide();
	
}
	
window.onload=function(){
	var err='<%=request.getAttribute("err") == null ? "":request.getAttribute("err")%>';
	var suc='<%=request.getAttribute("suc") == null ? "":request.getAttribute("suc") %>';
	if(err != 'null' && err != ''){
		alert(err);
	}
	if(suc != 'null' && suc != ''){
		alert(suc);
	}
}	

</script>

</head>
<body>

	<section id="loading" class="loading"> <section class="mask"></section>
	<section id="loadingCon" class="conmask"> <img
		src="<%=request.getContextPath()%>/img/hook-spinner.gif"
		id="loadingImg">
	<P class="waiting">加载中</P>
	</section> </section>
	<!-- 查看更多  图片 -->
	<section id="img" class="loading"> <section class="mask"
		id="mask"></section> <section id="imgContent" class="imgmask">
	<img src="" id="imgBig">
	<p class="" title="旋转图片">
		<span class="rotateIcon" id="rotate" onclick="rotateI();"><i
			class="icon-repeat"></i></span> <span class="rotateIcon" id="closeTk"><i
			class="icon-remove"></i></span>
	</p>
	</section> </section>
	<!--头部-->
	<header>
	<div class="center">
		<h1>公众号后台管理系统</h1>
		<ul class="nav">
			<!--后台获取调用span里的内容，预留调用id-->
			<li class="lr_hover">
				<div class="divHover" href="#" target="_blank">
					<i class="icon-user"></i><i class="icon-angle-down"></i>
					<div class="lr_hide">
						<a href="<%=request.getContextPath()%>/resetPwd">修改密码</a> <a
							href="<%=request.getContextPath()%>/login/toLoginOut" class="quit">退出登录</a>
					</div>
				</div>
			</li>
		</ul>
	</div>
	</header>
	<!--正文-->
	<section> <%=request.getSession().getAttribute("htmlMenu")%>

	<!--右侧主体-->
	<div class="Right_Main">