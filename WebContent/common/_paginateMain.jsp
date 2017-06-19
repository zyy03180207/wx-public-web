<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.jfinal.plugin.activerecord.*"%>
<%
	Page<Record> r = null;
	int totalPage = 1;
	int totalRom = 1;
	int currentPage = 1;
	int lastPage = 1;
	int nextPage = 1;
	int startPage =0;
	int endPage =0;
	if(request.getAttribute("record") != null){
		r = (Page)request.getAttribute("record");
		totalPage = r.getTotalPage();
		totalRom = r.getTotalRow();
		currentPage = r.getPageNumber();
		lastPage = 1;
		nextPage = 1;
	}
	
	if (currentPage == 1){
		lastPage = 1;
	}else{
		lastPage = currentPage - 1;
	}
	if (currentPage == totalPage){
		nextPage = currentPage;
	}else{
		nextPage = currentPage + 1;
	}
	
	/* if(currentPage > 4){
		startPage = currentPage - 4;
		endPage = currentPage + 4;
	}else{
		startPage=1;
	    endPage=8;	
	} */
	 if(totalPage<=9)
	{
		startPage=1;
		endPage=totalPage;
	}
	/* if(totalPage>9)
	{
		startPage=(currentPage-4)>1?(currentPage-4):1;
		endPage=(startPage+8)>totalPage?totalPage:(startPage+8);
	}  */
	
	 if(totalPage>9&&currentPage<=5)
	{
		startPage=1;
		endPage=9;
	}
	if(totalPage>9&&currentPage>=totalPage-4)
	{
		startPage=totalPage-8;
		endPage=totalPage;
	}
	if(totalPage>9&&currentPage>5&&currentPage<totalPage-4)
	{
		startPage=currentPage-4;
		endPage=currentPage+4;
	}  

	
	if ((totalPage <= 1) || (currentPage > totalPage)){
	}else{
%>
<!--分页-->

<ul class="zcMain_x clearfix">
	<li class="zcMain_x_li1">共<span><%=totalRom%></span>条
	</li>
	<li class="zcMain_x_li2"><span class="zc_x_text1 zcSame0">当前</span>
		<div class="zcMain_x_overflow">
			<ul class="zcMain_x_number" id="zc_number">
				<%
                for(int i = startPage; i <= endPage; i++){
                if(currentPage == i){%>
				<li onclick="javascript:goWhere(<%=i%>);" class="zc_x_li add_class"><%=i%></li>
				<%}else{%>
				<li onclick="javascript:goWhere(<%=i%>);" class="zc_x_li"><%=i%></li>
				<%}
                                    	}
                                    %>
			</ul>
		</div></li>
	<li class="zcMain_x_li3"><input type="button"
		onClick="javascript:goWhere('1');" value="首页" class="zcSame1 S_btn1"
		id="J_x_btn1"> <input type="button"
		onClick="javascript:goWhere(<%=lastPage%>);" value="上一页"
		class="zcSame1" id="J_x_btn2"> <input type="button"
		onClick="javascript:goWhere(<%=nextPage%>);" value="下一页"
		class="zcSame1" id="J_x_btn3"> <input type="button"
		onClick="javascript:goWhere(<%=totalPage%>);" value="末页"
		class="zcSame1" id="J_x_btn4"></li>
	<li class="zcMain_x_li4"><span class="zc_x_text2 zcSame0">跳到
			:</span>
		<div class="zc_x_btn5">
			<input type="text"
				onKeyDown="return enterSubmit(<%=totalPage%>,event);"
				value="<%=currentPage%>" class="zc_x_btn55" id="J_text"
				autocomplete="off">
		</div> <span class="zc-text3 zcSame0"> 页</span></li>
	<li class="zcMain_x_li5"><input type="button" value="GO"
		class="zc_x_btn6" id="J_x_btn6" onClick="javascript:goWhere('-1');">
	</li>
</ul>
<%}%>
<!--分页end-->