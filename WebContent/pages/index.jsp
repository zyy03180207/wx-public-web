<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.jfinal.plugin.activerecord.*"%>
<%@ page import="java.util.*"%>
<%@ include file="/common/_layoutMain.jsp"%>
<%
String result = null;
if(request.getAttribute("add_result")!=null)
	result = (String)request.getAttribute("add_result");
%>
<script type="text/javascript">
var maxsize = 300*1024;  
var errMsg = "上传文件不能超过300K！！！";  
var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过300K，建议使用Chrome浏览器。";  
var  browserCfg = {};  
var ua = window.navigator.userAgent;  
if (ua.indexOf("MSIE")>=1){  
    browserCfg.ie = true;  
}else if(ua.indexOf("Firefox")>=1){  
    browserCfg.firefox = true;  
}else if(ua.indexOf("Chrome")>=1){  
    browserCfg.chrome = true;  
}  
function checkfile(id){  
    try{  
        var obj_file = document.getElementById(id);  
        if(obj_file.value==""){  
            alert("请先选择上传文件");  
            return false;  
        }  
        var filesize = 0;  
        if(browserCfg.firefox || browserCfg.chrome ){  
            filesize = obj_file.files[0].size;  
        }else{  
            alert(tipMsg);
            obj_file.outerHTML=obj_file.outerHTML;
       		return false;  
        }  
        if(filesize==-1){  
            alert(tipMsg);  
            obj_file.outerHTML=obj_file.outerHTML; 
            return false;  
        }else if(filesize>maxsize){  
            alert(errMsg);
            obj_file.outerHTML=obj_file.outerHTML;  
            return false;  
        }else{   
            return true;  
        }  
    }catch(e){  
        alert(e);  
    }  
}   

function upload(id){
	 var file1 = document.getElementById(id); // 获取文件对象
	 var fileObj = file1.files[0];
	 var AllImgExt=".jpg|.jpeg|"//全部图片格式类型 
	 var FileExt=fileObj.name.substr(fileObj.name.lastIndexOf(".")).toLowerCase(); 
	 if(AllImgExt.indexOf(FileExt+"|")==-1)//如果图片文件，则进行图片信息处理 
	 {
			alert("格式错误");
			file1.outerHTML=file1.outerHTML;
	 		return;
	 }
	if(!checkfile(id)) return;
     var FileController = "<%=request.getContextPath()%>/agent/uploadAgImg";              // 接收上传文件的后台地址 
     // FormData 对象
     var form = new FormData();
     form.append("name", id);                        // 可以增加表单数据
     form.append("file", fileObj);                           // 文件对象
     // XMLHttpRequest 对象
     xmlHttp=null;
     if (window.XMLHttpRequest)
       {// code for IE7, Firefox, Opera, etc.
       xmlHttp=new XMLHttpRequest();
       }
     else if (window.ActiveXObject)
       {// code for IE6, IE5
       xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
       }
    xmlHttp.open("POST", FileController, true);
	//xmlHttp.setRequestHeader("Content-Type","multipart/form-data;charset=UTF-8");
     xmlHttp.onload = function () {
        var text = xmlHttp.responseText;
        console.log(text);
        if(text<0){
			file1.outerHTML=file1.outerHTML;
        }else
        $("#"+id+"_f").val(text);
        hideLoading();
     };
     showLoading();
     xmlHttp.send(form);
}

$(function(){
	var result = '<%=result%>';
	if(result!=null && result!='' && result!='null'){
		if(result=='ok'){
			alert("添加成功");
		}else{
			alert("添加失败");
		}
	}
	
	$("#province").change(function(){
		  $.ajax({
				type : "POST",
				url :"<%=request.getContextPath()%>/merchant/getCity",
				data : "pro="+$("#province").val(),
				dataType : "json",
				success : function(msg) {	
					var value = msg.city;
					var area = msg.area;
					console.log(msg);
					 $("#addressCity").empty();
					 $("#addressTown").empty();
					 $.each(value, function(i,item){
			                $("#addressCity").append("<option value='"+item.city_no+"'>"+item.city_name+"</option>");  
			            }); 
					 $.each(area, function(i,item){
			                $("#addressTown").append("<option value='"+item.area_no+"'>"+item.area_name+"</option>");  
			            });  
				 },
				error:function(msg){
					alert("服务器错误");
				}
			  });
	  })
	$("#addressCity").change(function(){
		  $.ajax({
				type : "POST",
				url :"<%=request.getContextPath()%>/merchant/getCity",
				data : "city="+$("#addressCity").val(),
				dataType : "json",
				success : function(msg) {	
					var value = msg.area;
					console.log(msg);
					 $("#addressTown").empty();
					 $.each(value, function(i,item){
			                $("#addressTown").append("<option value='"+item.area_no+"'>"+item.area_name+"</option>");  
			            });  
				 },
				error:function(msg){
					alert("服务器错误");
				}
			  });
	  })
	$("#prov_bank").change(function(){
		  $.ajax({
				type : "POST",
				url :"<%=request.getContextPath()%>/merchant/getBankCity",
				data : "prov="+$("#prov_bank").val(),
				dataType : "json",
				success : function(msg) {	
					var value = msg.city;
					console.log(msg);
					 $("#card_bank_address").empty();
					 $.each(value, function(i,item){
			                $("#card_bank_address").append("<option value='"+item.city+"'>"+item.city+"</option>");  
			            });  
				 }
			  });
	  })
	$("#card_bank").change(function(){
		 changeBankBranch();
	  })
		
})

function changeBankBranch(){
	var city = $("#card_bank_address").val();
	var bank = $("#card_bank").val();
	$.ajax({
		type : "POST",
		url : "<%=request.getContextPath()%>/merchant/getBankBranch",
		data : "bank="+bank+"&city="+city,
		dataType : "json",
		success : function(msg) {
			$("#card_branch").bigAutocomplete({
				width:435,
				data:msg,
				callback:function(data){
					
				}
			});
		}
	});
}

function checkMessage(){
	if(!confirm("确认信息输入完整，并提交"))
		return false;
///////////////////////////////////////////////
	if($("#comShortName").val()==''){
		alert("企业简称不可为空");
		return false;
	}

	if($("#comFullName").val()==''){
		alert("企业全称不可为空");
		return false;
	}
	if($("#addressTown").val()==''){
		alert("请选择所属地区");
		return false;
	}
	if($("#addressDetail").val()==''){
		alert("企业地址不可为空");
		return false;
	}
	 if($("#setupDate").val()==""){
		 alert("成立日期不可为空");
		 $("#setupDate").focus();
		 return false;
	 }
	 if($("#businessScope").val()==""){
		 alert("营业范围不可为空");
		 $("#businessScope").focus();
		 return false;
	 }	
	 if($("#license_id").val()==""){
		alert("营业执照编号不可为空");
		$("#license_id").focus();
		return false;
	 }
	 if($("#legal_person").val()==""){
		alert("法人姓名不可为空");
		$("#legal_person").focus();
		return false;
	 }
	 var v = $("#id_card").val();
	 var a = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/;
	 if (!v.match(a)) {
		alert("结算卡身份证错误");
		$("#id_card").focus();
		return false;
	}
	if($("#loginAccount").val()==''){
		alert("登陆账号不可为空");
		return false;		
	}
	var pwd = $("#loginPassword").val();
	var repwd = $("#confirmPassword").val();
	if(pwd==''||repwd==''){
		alert("登陆密码或确认密码为空");
		return false;
	}else if(pwd!=repwd){
		alert("两次输入密码不一致");
		return false;
	}
	 if($("#openName").val()==""){
		 alert("开户名不可为空");
		 return false;
	 } 
	 var openAccount = $("#openAccount").val();
	 if(openAccount==""){
		 alert("开户帐号不可为空");
		 return false;
	 }
	 if(openAccount.indexOf(" ") >=0){
		 alert("开户帐号格式不正确");
		 return false;
	 }
	 if(openAccount.replace(/\s+/g,"").length<9||openAccount.replace(/\s+/g,"").length>23){
	 	alert("开户帐号长度不正确");
		 return false;
	 }
	 if($("#openAccount").val() != $("#confirmAccount").val()){
		 alert("开户帐号和确认开户帐号不一致");
		 return false;
	 }
	 if($("#card_bank_address").val()==""){
		 alert("开户地不可为空");
		 return false;
	 }

	 if($("#card_bank").val()==""){
		 alert("开户行不可为空");
		 return false;
	 }
	 if($("#card_branch").val()==""){
		 alert("开户分行不可为空");
		 $("#card_branch").focus();
		 return false;
	 }

	 var rateM = /^\d+(\.\d+)?$/;
	 var rate1 =$("#rate1").val();
	 if(!rate1.match(rateM)){
			alert("费率输入错误");
			$("#rate1").focus();
			return false;
	}
	 if(!$("#rate2").val().match(rateM)){
			alert("费率输入错误");
			$("#rate2").focus();
			return false;
	}
	 if(!$("#rate3").val().match(rateM)){
			alert("费率输入错误");
			$("#rate3").focus();
			return false;
	}
	
	 if(!$("#cr_ratef").val().match(rateM)){
			alert("分润比例输入错误");
			$("#cr_ratef").focus();
			return false;
	}
////////////////////////////////////////////
	if($("#busLicenseImg_f").val()==null || $("#busLicenseImg_f").val()==''){
		alert("营业执照照片没有提交");
		return false;
	}
	if($("#frontIDImg_f").val()==null || $("#frontIDImg_f").val()==''){
		alert("法人身份证（正面）照片没有提交");
		return false;
	}
	if($("#backIDImg_f").val()==null || $("#backIDImg_f").val()==''){
		alert("法人身份证（反面）照片没有提交");
		return false;
	}
	if($("#handIDImg_f").val()==null || $("#handIDImg_f").val()==''){
		alert("法人手持身份证照片没有提交");
		return false;
	}
	if($("#casBankCardImg_f").val()==null || $("#casBankCardImg_f").val()==''){
		alert("结算卡照片没有提交");
		return false;
	}
	if($("#accPermitImg_f").val()==null || $("#accPermitImg_f").val()==''){
		alert("开户许可证照片没有提交");
		return false;
	}
	showLoading();
	return true;
}
function checkLoginAcc(){
	//登陆帐号
	  var  re = /^1[3|4|5|8][0-9]\d{4,8}$/;
	  var cloginacc = $("#loginAccount").val();
	if(cloginacc==''||!re.test(cloginacc)){
		alert("请检查登陆账号");
		return false;
	}else{
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/agent/add",
			data : "action=check&phone=" + cloginacc,
			success : function(msg) {
				if (msg.isok == "no") {

				} else {
					alert("手机号已存在");
					$("#loginAccount").val("");
					$("#loginAccount").focus();
				}
			}
		});
	}
	
}

function addASubmit(){
	$("#change_form").submit();
}

</script>
<div class="Main">
	<div class="Main_bread">
		<span><a href="#">机构管理</a></span> > <i>新增机构 </i>
	</div>
	<div class="Change Change1 " id="detail_Change_Div">
		<!--新增机构 信息填写-->
		<form class="Change Change2 " id="change_form"
			action="<%=request.getContextPath()%>/agent/add" method="post"
			onsubmit="return checkMessage();">
			<input type="hidden" id="action" name="action" value="add" /> <input
				type="hidden" id="phone_flag" value="no" /> <input type="hidden"
				name="agent.state" value="00" /> <input type="hidden"
				id="busLicenseImg_f" name="photos" value=""> <input
				type="hidden" id="frontIDImg_f" name="photos" value=""> <input
				type="hidden" id="backIDImg_f" name="photos" value=""> <input
				type="hidden" id="handIDImg_f" name="photos" value=""> <input
				type="hidden" id="casBankCardImg_f" name="photos" value="">
			<input type="hidden" id="accPermitImg_f" name="photos" value="">

			<!--新增机构 基本信息-->
			<div class="content BoxShadow mt40">
				<h4 class="titlehost">
					<i class="iconline"></i>基本信息
				</h4>
				<ul class="search label120 mt40">
					<li><label for=""><i>*</i>企业简称：</label> <input type="text"
						class="input wd185" name="agent.short_name" id="comShortName"
						value=""> <label for="" class="ml50"><i>*</i>企业类型：</label>
						<select class="selectS" style="width: 180px" name="agent.com_type"
						id="companyType">
							<option value="有限责任公司">有限责任公司</option>
							<option value="个体户">个体户</option>
							<option value="企业非法人">企业非法人</option>
					</select></li>
					<li><label for=""><i>*</i>企业全称：</label> <input type="text"
						class="input wdlonginput" name="agent.full_name" id="comFullName"
						value=""></li>
					<li><label for=""><i>*</i>所属地区：</label> <select
						class="selectS" name="agent.province" id="province">
							<option value="">-省市-</option>
							<%
								if (request.getAttribute("prov") != null) {
									List<Record> l = (List<Record>) request.getAttribute("prov");
									for (int i = 0; i < l.size(); i++) {
										Record item = l.get(i);
							%>
							<option value="<%=item.getStr("province_no")%>"><%=item.getStr("province_name")%></option>
							<%
								}
								}
							%>
					</select> - <select class="selectS" name="" id="addressCity">
							<option>选择市区</option>
					</select> - <select class="selectS" name="agent.city" id="addressTown">
							<option>选择县镇</option>
					</select></li>
					<li><label for=""><i>*</i>企业地址：</label> <input type="text"
						class="input wdlonginput" name="agent.reg_address"
						id="addressDetail" value=""></li>
					<li><label for=""><i>*</i>成立日期：</label> <input type="month"
						class="input wd80" name="agent.reg_date" id="setupDate" value=""
						readonly="readonly" onclick="WdatePicker({el:'setupDate'})">
					</li>
					<li><label for=""><i>*</i>经营范围：</label> <input type="text"
						class="input wd185" name="agent.business_scope" id="businessScope"
						value=""> <span class="tips">请与营业执照信息一致</span></li>
					<li><label for=""><i>*</i>营业执照号：</label> <input type="text"
						class="input wd185" name="agent.license_id" id="license_id"
						value=""></li>
					<li><label for=""><i>*</i>法人姓名：</label> <input type="text"
						class="input wd185" name="agent.legal_person" id="legal_person"
						value=""></li>
					<li><label for=""><i>*</i>身份证号：</label> <input type="text"
						maxlength="18" class="input wd185" name="agent.id_card"
						id="id_card" value=""></li>
					<li><label for=""><i>*</i>登录帐号：</label> <input type="text"
						maxlength="11" class="input wd185" onblur="checkLoginAcc(this);"
						name="user.u_login" id="loginAccount" value=""> <span
						class="tips">帐号必须是手机号码</span></li>
					<li><label for=""><i>*</i>登录密码：</label> <input type="password"
						class="input wd185" name="user.u_pwd" placeholder="输入密码"
						id="loginPassword" value=""> <label for="" class="ml50"><i>*</i>确认密码：</label>
						<input type="password" class="input wd185" placeholder="确认密码"
						id="confirmPassword" value=""></li>
				</ul>
			</div>
			<!--新增机构 结算信息-->
			<div class="content BoxShadow">
				<h4 class="titlehost">
					<i class="iconline"></i>结算信息
				</h4>
				<ul class="search label120 mt40">
					<li><label for=""><i>*</i>账户类型：</label> <select
						class="selectS" style="width: 200px;" name="card.card_type"
						id="card_type">
							<option value="2">对公</option>
							<option value="1">法人</option>
					</select></li>
					<li><label for=""><i>*</i>开户名：</label> <input type="text"
						class="input wd185" name="card.card_name" id="openName" value="">
					</li>
					<li><label for=""><i>*</i>开户帐号：</label> <input type="text"
						class="input wd185" name="card.card_no" id="openAccount" value="">
					</li>
					<li><label for=""><i>*</i>确认帐号：</label> <input type="text"
						class="input wd185" id="confirmAccount" value=""></li>
					<li><label for=""><i>*</i>开户地：</label> <select class="selectS"
						name="bankprovince" id="prov_bank">
							<option value="">-选择省份-</option>
							<%
								if (request.getAttribute("prov_bank") != null) {
									List<Record> l = (List<Record>) request.getAttribute("prov_bank");
									for (int i = 0; i < l.size(); i++) {
										Record item = l.get(i);
							%>
							<option value="<%=item.getStr("province")%>"><%=item.getStr("province")%></option>
							<%
								}
								}
							%>
					</select> - <select class="selectS" name="card.card_bank_address"
						id="card_bank_address">
							<option value="">选择市区</option>
					</select></li>
					<li><label for=""><i>*</i>开户行：</label> <select class="selectS"
						name="card.card_bank" id="card_bank">
							<option value="">-开户行-</option>
							<%
								if (request.getAttribute("card_bank") != null) {
									List<Record> l = (List<Record>) request.getAttribute("card_bank");
									for (int i = 0; i < l.size(); i++) {
										Record item = l.get(i);
							%>
							<option value="<%=item.getStr("bank_head")%>"><%=item.getStr("bank_head")%></option>
							<%
								}
								}
							%>
					</select>- <input type="text" class="input" style="width: 430px;"
						id="card_branch" name="card.card_branch" value=""
						autocomplete="off" /></li>

					<li class="mt40"><label for="">成本费率：</label> <span
						class="tips">说明： 如1%请填写0.01</span> <br> <label for=""></label>
						<input type="text" class="input wd80" style="widows: 130px;"
						value="" name="crate.cr_ratea" id="rate1"> <span
						class="tips">标准费率0.0038</span> <br> <label for=""></label> <input
						type="text" class="input wd80" style="widows: 130px;" value=""
						name="crate.cr_rateb" id="rate2"> <span class="tips">标准费率0.0078</span>
						<br> <label for=""></label> <input type="text"
						class="input wd80" style="widows: 130px;" value=""
						name="crate.cr_ratec" id="rate3"> <span class="tips">标准费率0.0125</span>
					</li>
					<li><label for=""><i>*</i>分润比例：</label> <input type="text"
						class="input wd80 mlr5" value="" name="crate.cr_ratef"
						id="cr_ratef"> <span class="tips">说明： 如1%请填写0.01</span></li>
				</ul>
			</div>
			<!--新增机构 资质上传-->
			<div class="content BoxShadow mt40">
				<h4 class="titlehost">
					<i class="iconline"></i>资质上传
				</h4>
				<ul class="uploadTips">
					<li>资质提交说明：</li>
					<li>1. 请使用jpeg或jpg格式文件上传，单个上传文件不能大于300K</li>
					<li>2. 特殊资质要求：</br> (1)体现甲乙双方名称和地址信息；</br> (2)体现有效期信息；</br> (3)落款签字、日期和盖章信息
					</li>
				</ul>
				<ul class="search fl labelThird wdpercent40 mt40">
					<li><label for=""><i>*</i>营业执照：</label> <input type="file"
						class="file" onchange="upload('busLicenseImg');"
						id="busLicenseImg"></li>
					<li><label for=""><i>*</i>法人身份证（正面）：</label> <input
						type="file" class="file" onchange="upload('frontIDImg');"
						id="frontIDImg"></li>
					<li><label for=""><i>*</i>法人身份证（反面）：</label> <input
						type="file" class="file" onchange="upload('backIDImg');"
						id="backIDImg"></li>
				</ul>
				<ul class="search fl label180 wdpercent60 mt40">
					<li><label for=""><i>*</i>法人手持身份证照片：</label> <input
						type="file" class="file" onchange="upload('handIDImg');"
						id="handIDImg"></li>
					<li><label for=""><i>*</i>结算卡照片：</label> <input type="file"
						class="file" onchange="upload('casBankCardImg');"
						id="casBankCardImg"></li>
					<li><label for=""><i>*</i>开户许可证：</label> <input type="file"
						class="file" onchange="upload('accPermitImg');" id="accPermitImg"><br>
						<label for=""></label></li>
				</ul>
			</div>
		</form>
	</div>
	<!--新增机构 信息填写  结束-->
	<div class="textC mt50 mb30">
		<input type="button" onclick="addASubmit();"
			class="normalBtn searchbtn" value="提交审核" />
	</div>
</div>

<div class="lr_bottom">© 2016 十五度工作室 版权所有</div>