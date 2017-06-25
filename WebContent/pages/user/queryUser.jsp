<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.jfinal.plugin.activerecord.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/_layoutMain.jsp"%>
<%
	Page<Record> rr = null;
	List<Record> l = null;
	int pageIndex = 1;
	String phone ="";
	String braname ="";
	String state ="";
	if(request.getAttribute("phone") != null)
		phone = (String)request.getAttribute("phone");
	if(request.getAttribute("braname") != null)
		braname = (String)request.getAttribute("braname");
	if(request.getAttribute("state") != null)
		state = (String)request.getAttribute("state");
	if (request.getAttribute("record") != null) {
		rr = (Page) request.getAttribute("record");
		l = rr.getList();
		pageIndex = rr.getPageNumber();
	}

%>
<div class="Main">
	<div class="Main_bread">
		<span><a href="#">用户管理</a></span> > <i>获取用户列表 </i>
	</div>
	<div class="Change Change1 " id="detail_Change_Div"></div>
	<div class="Change " id="main_Change_Div">
		<form action="<%=request.getContextPath()%>/brand/queryBrand"
			method="post" class="Main_information1" id="main_Form">

			<label for="">品牌商全称：</label> <input id="braname" name="braname"
				type="text" value="djsakld" autocomplete="off" /> <label for="">手机号：</label>
			<input id="phone" name="phone" type="text" value="123456"
				autocomplete="off" /> <input type="hidden" id="action"
				name="action" value="serch" /> <input type="hidden" id="pageIndex"
				name="pageIndex" value="dff" /> <input type="button" value="查询"
				onClick="" /> <input type="reset" value="重置" />
			<div style="margin-top: 20px;">
				<label for="">审核状态：</label> <select name="state" id="state"
					required="required" class="selectF">
					<option>审核成功</option>
					<option>审核中</option>
				</select>
			</div>
		</form>

		<div class="lr_bg_white">
			<table summary="借款人信息" class="table1">
				<caption>查询结果</caption>
				<tbody>
					<tr>
						<td>品牌商全称</td>
						<td>品牌商简称</td>
						<td>法人</td>
						<td>身份证</td>
						<td>电话</td>
						<td>审核状态</td>
						<td>创建日期</td>
						<td>操作</td>
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