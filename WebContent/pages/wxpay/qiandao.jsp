<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>平安签到</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="<%=request.getContextPath()%>/css/mui.min.css" rel="stylesheet" />
	</head>
	<style type="text/css">
		body{
			background: #FFF;
			margin: 0;
			padding: 0;
		}
		.top-content{
			width: 100vw;
			height: 50vw;
			position: relative;
		}
		.div_people{
			width: 100vw;
			height: 30vw;
			background: #FFF;
			position: relative;
		}
		.lb_size{
			position: absolute;
			color: #000;
			font-size: 13px;
			font-family: "微软雅黑";
			margin-left: 5vw;
			top: 5vw;
		}
		.div_can{
			width: 90vw;
			height: 20vw;
			background: #FFF;
			position: absolute;
			bottom: 0;
			margin-left: 5vw;
			margin-right: 5vw;
		}
		.div_jianjie{
			width: 100vw;
			height: auto;
			padding: 2vw 5vw 5vw 5vw;
			font-family: "微软雅黑";
			font-size: 13px;
			background: #FFF;
			position: static;
		}
		.tb_people td{
			width: 7.5vw;
			height: 7.5vw;
			padding: 1vw;
		}
		.tb_people td img{
			width: 100%;
			height: 100%;
			border-radius: 3px;
		}
		.div_ri{
			width: 100vw;
			height: 10vw;
			line-height: 10vw;
			font-size: 2vh;
			overflow: hidden;
			position: relative;
		}
		.div_lie{
			width: 100vw;
			height: 15vw;
			font-family: "微软雅黑";
			font-size: 15px;
		}
		.li_lie{
			list-style-type: none;
		}
		.lb_day{
			margin-left: 5vw;
			line-height: 8vw;
		}
		.lb_date{
			margin-left: 5vw;
		}
	</style>
	<body>
		<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
		</script>
		<header class="top-content">
			<img src="<%=request.getContextPath()%>/img/top_img.png" style="width: 100%; height: 100%;" />
			<label style="position: absolute;color: #FFF; left: 5vw;bottom: 4vw;font-family:'微软雅黑'; font-size: 4.5vw;">平安签到</label>
		</header>
		<div class="div_people">
			<label class="lb_size">178565人已参加</label>
			<div class="div_can">
				<table class="tb_people">
					<tr>
						<td><img src="<%=request.getContextPath()%>/img/img1.png" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img10.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img11.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img12.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img13.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img14.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img15.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img16.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img17.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img18.png" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img19.png" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img2.jpeg" style="width: 100%;height: 100%;"/></td>
					</tr>
					<tr>
						<td><img src="<%=request.getContextPath()%>/img/img21.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img22.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img23.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img24.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img25.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img26.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img3.png" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img4.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img5.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img7.jpeg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img8.jpg" style="width: 100%;height: 100%;"/></td>
						<td><img src="<%=request.getContextPath()%>/img/img9.jpg" style="width: 100%;height: 100%;"/></td>
					</tr>
				</table>
			</div>
			<div style="background:#CCCCCC;width: 90vw;height: 1px;position: absolute;bottom: 0;margin-left: 5vw;margin-right: 5vw;"></div>
		</div>
		<div class="div_jianjie">
			<label>1、每天早上5：00~8：00打卡，全额积分；8：00~10：00打卡，积分减半；</label></br></br>
			<label>2、连续第N天打卡，当天奖励10xN积分。连续打卡超过30天，每天固定奖励150分。打卡中断后，再次打卡奖励积分重新从10分开始计算，总积分积累。</label></br></br>
			<label>3、参加活动即有机会赢Kindle、小米移动电源等好礼!</label></br></br>
			<a>点击查看详细活动介绍</a>
		</div>
		<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
		<div class="div_ri">
			<label style="font-family: '微软雅黑';position: absolute;margin-left: 5vw;">日程列表</label>
		</div>
		<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
		<div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			<div class="div_lie">
				<label class="lb_day">第一天</label></br>
				<label class="lb_date">2016-11-20</label>
			</div>
			<div style="background:#CCCCCC;width: 100vw;height: 1px;bottom: 0;"></div>
			
		</div>
	</body>
</html>