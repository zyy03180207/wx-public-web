<%@page import="oracle.net.aso.n"%>
<%@page import="javax.smartcardio.Card"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wx.entity.*"%>
<%
	List<CardNews> cardNews = (List<CardNews>) request.getAttribute("cards");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>搞笑文章</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/mui.min.css">
<style>
html, body {
	background-color: #efeff4;
}

.mui-bar ~.mui-content .mui-fullscreen {
	top: 44px;
	height: auto;
}

.mui-pull-top-tips {
	position: absolute;
	top: -20px;
	left: 50%;
	margin-left: -25px;
	width: 40px;
	height: 40px;
	border-radius: 100%;
	z-index: 1;
}

.mui-bar ~.mui-pull-top-tips {
	top: 24px;
}

.mui-pull-top-wrapper {
	width: 42px;
	height: 42px;
	display: block;
	text-align: center;
	background-color: #efeff4;
	border: 1px solid #ddd;
	border-radius: 25px;
	background-clip: padding-box;
	box-shadow: 0 4px 10px #bbb;
	overflow: hidden;
}

.mui-pull-top-tips.mui-transitioning {
	-webkit-transition-duration: 200ms;
	transition-duration: 200ms;
}

.mui-pull-top-tips .mui-pull-loading {
	/*-webkit-backface-visibility: hidden;
				-webkit-transition-duration: 400ms;
				transition-duration: 400ms;*/
	margin: 0;
}

.mui-pull-top-wrapper .mui-icon, .mui-pull-top-wrapper .mui-spinner {
	margin-top: 7px;
}

.mui-pull-top-wrapper .mui-icon.mui-reverse {
	/*-webkit-transform: rotate(180deg) translateZ(0);*/
	
}

.mui-pull-bottom-tips {
	text-align: center;
	background-color: #efeff4;
	font-size: 15px;
	line-height: 40px;
	color: #777;
}

.mui-pull-top-canvas {
	overflow: hidden;
	background-color: #fafafa;
	border-radius: 40px;
	box-shadow: 0 4px 10px #bbb;
	width: 40px;
	height: 40px;
	margin: 0 auto;
}

.mui-pull-top-canvas canvas {
	width: 40px;
}

.mui-slider-indicator.mui-segmented-control {
	background-color: #efeff4;
}
</style>
</head>

<body>
	<div class="mui-content">
		<div id="slider" class="mui-slider mui-fullscreen">
			<div id="sliderSegmentedControl"
				class="mui-scroll-wrapper mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<div class="mui-scroll">
					<a class="mui-control-item mui-active" href="#item1mobile"> 推荐
					</a> <a class="mui-control-item" href="#item2mobile"> 热点 </a>
				</div>
			</div>
			<div class="mui-slider-group">
				<div id="item1mobile"
					class="mui-slider-item mui-control-content mui-active">
					<div id="scroll1" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view">

								<li><br />
									<div class="mui-card">
										<div class="mui-card-content">
											<div class="mui-card-content-inner">
												<h3>Posted on January 18, 2016</h3>
												<p>作者：张阳阳</p>
												<p style="color: #333;">这里显示文章摘要，让读者对文章内容有个粗略的概念...</p>
											</div>
										</div>
										<div class="mui-card-footer">
											<a class="mui-card-link">查看全文</a>
										</div>
									</div></li>
								<li>
									<div class="mui-card">
										<div class="mui-card-content">
											<div class="mui-card-content-inner">
												<h3>Posted on January 18, 2016</h3>
												<p>作者：张阳阳</p>
												<p style="color: #333;">这里显示文章摘要，让读者对文章内容有个粗略的概念...</p>
											</div>
										</div>
										<div class="mui-card-footer">
											<a class="mui-card-link">查看全文</a>
										</div>
									</div>
								</li>
								<li>
									<div class="mui-card">
										<div class="mui-card-content">
											<div class="mui-card-content-inner">
												<h3>Posted on January 18, 2016</h3>
												<p>作者：张阳阳</p>
												<p style="color: #333;">这里显示文章摘要，让读者对文章内容有个粗略的概念...</p>
											</div>
										</div>
										<div class="mui-card-footer">
											<a class="mui-card-link">查看全文</a>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div id="item2mobile" class="mui-slider-item mui-control-content">
					<div class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view">
								<li class="mui-table-view-cell">第2个选项卡子项-1</li>
								<li class="mui-table-view-cell">第2个选项卡子项-2</li>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/mui.pullToRefresh.js"></script>
	<script src="<%=request.getContextPath()%>/js/mui.pullToRefresh.material.js"></script>
	<script>
		mui.init();
		(function($) {
			//阻尼系数
			var deceleration = mui.os.ios ? 0.003 : 0.0009;
			$('.mui-scroll-wrapper').scroll({
				bounce : false,
				indicators : true, //是否显示滚动条
				deceleration : deceleration
			});
			$
					.ready(function() {
						//循环初始化所有下拉刷新，上拉加载。
						$
								.each(
										document
												.querySelectorAll('.mui-slider-group .mui-scroll'),
										function(index, pullRefreshEl) {
											$(pullRefreshEl)
													.pullToRefresh(
															{
																down : {
																	callback : function() {
																		var self = this;
																		setTimeout(
																				function() {
																					var ul = self.element
																							.querySelector('.mui-table-view');
																					ul.innerHTML = '';
																					ul
																							.insertBefore(
																									createFragment(
																											ul,
																											index,
																											10,
																											true),
																									ul.firstChild);
																					self
																							.endPullDownToRefresh();
																				},
																				1000);
																	}
																},
																up : {
																	callback : function() {
																		var self = this;
																		setTimeout(
																				function() {
																					var ul = self.element
																							.querySelector('.mui-table-view');

																					ul
																							.appendChild(createFragment(
																									ul,
																									index,
																									5));
																					self
																							.endPullUpToRefresh();
																				},
																				1000);
																	}
																}
															});
										});
						var createFragment = function(ul, index, count, reverse) {
							var length = ul.querySelectorAll('li').length;
							var fragment = document.createDocumentFragment();
							var li;
							for (var i = 0; i < count; i++) {
								li = document.createElement('li');
								//							li.className = 'mui-table-view-cell';
								li.innerHTML = '<br/><div class="mui-card">'
										+ '<div class="mui-card-content">'
										+ '<div class="mui-card-content-inner">'
										+ '<h3>Posted on January 18, 2016</h3>'
										+ '<p>作者：张阳阳</p>'
										+ '<p style="color: #333;">这里显示文章摘要，让读者对文章内容有个粗略的概念...</p>'
										+ '</div>' + '</div>'
										+ '<div class="mui-card-footer">'
										+ '<a class="mui-card-link">查看全文</a>'
										+ '</div>' + '</div>';
								fragment.appendChild(li);
							}
							return fragment;
						};
					});
		})(mui);
	</script>
</body>

</html>