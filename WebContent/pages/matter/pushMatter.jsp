<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.jfinal.plugin.activerecord.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/_layoutMain.jsp"%>
<div class="Main">
	<div class="Main_bread">
		<span><a href="#">素材管理</a></span> > <i>推送素材管理 </i>
	</div>
	<div class="Change Change1 " id="detail_Change_Div"></div>
	<div class="Change " id="main_Change_Div">
		<form action="<%=request.getContextPath()%>/brand/queryBrand"
			method="post" class="Main_information1" id="main_Form">

			<div style="margin-top: 0px;">
				<label for="" >素材类型：</label>
				<select name="state" id="state"
					required="required" class="selectF">
					<option>图文素材</option>
					<option>图片素材</option>
					<option>语音素材</option>
					<option>视频素材</option>
				</select>
				<input style="margin-left: 50px" type="button" value="查询"
				onClick="" /> <input type="reset" value="重置" />
			</div>
			
		</form>

		<div class="lr_bg_white">
			<table summary="借款人信息" class="table1">
				<caption>查询结果</caption>
				<tbody>
					<tr>
						<td>图为消息标题</td>
						<td>封面图片素材id</td>
						<td>是否显示封面</td>
						<td>作者</td>
						<td>图文消息的摘要</td>
						<td>图文页的URL</td>
						<td>图文消息的原文地址</td>
						<td>最后更新时间</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
					<tr>
						<td>张阳阳</td>
						<td>18853211461</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>张阳阳</td>
						<td>删除</td>
					</tr>
				</tbody>
			</table>
			<%@include file="/common/_paginateMain.jsp"%>

		</div>
	</div>


</div>
<div class="lr_bottom">© 2016 十五度广告工作室 版权所有</div>