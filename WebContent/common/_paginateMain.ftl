<#macro paginateMain currentPage totalPage actionUrl urlParas="">
<#local startPage = 1>
			<#if (currentPage == 1)> <#local lastPage = 1> <#else> <#local lastPage = currentPage-1> </#if>
			<#if (currentPage == totalPage)> <#local nextPage = currentPage> <#else> <#local nextPage = currentPage+1> </#if>
			
<!--分页-->
	<#if (totalPage <= 0) || (currentPage > totalPage)><#return></#if>
                    <ul class="zcMain_x clearfix">
                        <li class="zcMain_x_li1">共<span> #{totalPage} </span>条</li>
                        <li class="zcMain_x_li2">
                            <span class="zc_x_text1 zcSame0">当前</span>
                            <div class="zcMain_x_overflow">
                                <ul class="zcMain_x_number" id="zc_number">
                                    
                                	<#list startPage..totalPage as x>
										<#if currentPage == x>
											<li onclick="javascript:goWhere(#{x});" class="zc_x_li add_class" >#{x}</li>
										<#else>
											<li onclick="javascript:goWhere(#{x});" class="zc_x_li">#{x}</li>
										</#if>
									</#list>
                                </ul>
                            </div>
                        </li>
                        <li class="zcMain_x_li3">
                            <input type="button" onClick="javascript:goWhere('1');"  value="首页" class="zcSame1 S_btn1" id="J_x_btn1">
                            <input type="button" onClick="javascript:goWhere(#{lastPage});" value="上一页" class="zcSame1" id="J_x_btn2">
                            <input type="button" onClick="javascript:goWhere(#{nextPage});" value="下一页" class="zcSame1" id="J_x_btn3">
                            <input type="button" onClick="javascript:goWhere(#{totalPage});"  value="末页" class="zcSame1" id="J_x_btn4">
                        </li>
                        <li class="zcMain_x_li4">
                            <span class="zc_x_text2 zcSame0">跳到 :</span>
                            <div class="zc_x_btn5">
                                <input type="text" onKeyDown="return enterSubmit(#{totalPage},event);" value="${currentPage}" class="zc_x_btn55" id="J_text" autocomplete="off">
                            </div>
                            <span class="zc-text3 zcSame0"> 页</span>
                        </li>
                        <li class="zcMain_x_li5">
                            <input type="button" value="GO" class="zc_x_btn6" id="J_x_btn6" onClick="javascript:goWhere('-1');" >
                        </li>
                    </ul>
<!--分页end-->
</#macro>