/*
created 2016-06-23
chen jin liang
 */

var maxsize = 300 * 1024;
var errMsg = "上传文件不能超过300K！！！";
var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过300K，建议使用Chrome浏览器。";
var browserCfg = {};
var ua = window.navigator.userAgent;
if (ua.indexOf("MSIE") >= 1) {
	browserCfg.ie = true;
} else if (ua.indexOf("Firefox") >= 1) {
	browserCfg.firefox = true;
} else if (ua.indexOf("Chrome") >= 1) {
	browserCfg.chrome = true;
}
function checkfile(id) {
	try {
		var obj_file = document.getElementById(id);
		if (obj_file.value == "") {
			alert("请先选择上传文件");
			return false;
		}
		var filesize = 0;
		if (browserCfg.firefox || browserCfg.chrome) {
			filesize = obj_file.files[0].size;
		} else {
			alert(tipMsg);
			return false;
		}
		if (filesize == -1) {
			alert(tipMsg);
			return false;
		} else if (filesize > maxsize) {
			alert(errMsg);
			return false;
		} else {
			return true;
		}
	} catch (e) {
		alert(e);
	}
}

function upload(id) {
	var file1 = document.getElementById(id); // 获取文件对象
	var fileObj = file1.files[0];
	var AllImgExt = ".jpg|.jpeg|"// 全部图片格式类型
	var FileExt = fileObj.name.substr(fileObj.name.lastIndexOf("."))
			.toLowerCase();
	if (AllImgExt.indexOf(FileExt + "|") == -1)// 如果图片文件，则进行图片信息处理
	{
		alert("格式错误");
		file1.outerHTML=file1.outerHTML;
		return;
	}
	if (!checkfile(id))
		return;
	var FileController = "uploadImg"; // 接收上传文件的后台地址
	// FormData 对象
	var form = new FormData();
	form.append("name", id); // 可以增加表单数据
	form.append("file", fileObj); // 文件对象
	// XMLHttpRequest 对象
	xmlHttp = null;
	if (window.XMLHttpRequest) {// code for IE7, Firefox, Opera, etc.
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// code for IE6, IE5
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("post", FileController, true);
	xmlHttp.onload = function() {
		var text = xmlHttp.responseText;
		//console.log(text);
		$("#" + id + "_f").val(text);
	};

	xmlHttp.send(form);
}

function Details(merid) {
	showLoading();
	$.ajax({
		type : "POST",
		url : "update",
		data : "action=findById&merid=" + merid,
		dataType : "json",
		success : function(msg) {
			showDetail(msg);
			goToDetail('show');
			hideLoading();
		},
		error : function(msg) {
			alert("服务器错误");

			hideLoading();
		}
	});
}

function showDetail(msg) {
console.log(msg);
	var citys = msg.citys;
	var towns = msg.towns;
	var bcitys = msg.bcitys;
	var sign_rate = msg.sign_rate;
	var mer = msg.mer;
	var card = msg.card;
	var area = msg.area;
	var mcc = msg.mcc;
	filePhotos = msg.photos;
	$("#mer_id").val(mer.mer_id); 
	$("#pater_id").val(mer.pater_id); 
	$("#pater_type").val(mer.pater_type); 
	$("#bc_id").val(card.bc_id); 
	$("#uid").val(msg.user.uid); 
	$("#mShortName").text(mer.short_name);
	$("#mFullName").text(mer.full_name);
	
	$("#mer_address").text(area.province_no+"-"+area.city_no+"-"+area.area_no);
	$("#addressDetail").text(mer.mer_address);
	
	$("#mLinkman").text(mer.mer_person);
	$("#mLinkmanMobile").text(mer.lp_tel);
	$("#mEmail").text(msg.user.u_email);
	$("#rateInput").val(mer.sign_rate);
	$("#costRate").text("标准费率："+mer.cost_rate);
	$("#cost_rate").val(mer.cost_rate);
	//t0 t1
	var clear_type = mer.clear_type;
	if(clear_type=='0'){
		clear_type = 'T0';
	}else if(clear_type='1'){
		clear_type = 'T1';
	}else{
		clear_type = 'T0和T1';
	}
	
	$("#paymentMeans").text(clear_type);
	
	var type = mer.bus_type;
	var tem1 = /^1\d{3}$/.test(type);
	var tem2 = /^\d1\d{2}$/.test(type);
	var tem3 = /^\d{2}1\d$/.test(type);
	var tem4 = /^\d{3}1$/.test(type);
	$("#bus_type0").attr('checked', tem1);
	$("#bus_type1").attr('checked', tem2);
	$("#bus_type2").attr('checked', tem3);
	$("#bus_type3").attr('checked', tem4);

	$("#firmName").text(mer.com_name);
	$("#com_type").text(mer.com_type);
	$("#registerAddress").text(mer.reg_address);
	$("#setupDate").text(mer.reg_date.substring(0, 10));
	$("#businessScope").text(mer.business_scope);
	$("#license_no").text(mer.license_no);
	$("#businessCorporate").text(mer.legal_person);
	$("#idNo").text(mer.id_card);
	$("#idAdress").text(mer.id_address);
	$("#accountName").val(card.card_name);
	$("#personid").val(card.card_person_id);
	$("#cardphone").val(card.card_phone);
	$("#accountNo").val(card.card_no);
	$("#accountNoConfirm").val(card.card_no);
	$("#card_type").val(card.card_type);
	$("#accountAddressProvince").val(card.card_bank_address.split("-")[0]);
	$("#accountAddressCity").empty();
	$.each(bcitys,function(i, item) {
				$("#accountAddressCity").append("<option value='" + item.city + "'>" + item.city + "</option>");
			});
	$("#accountAddressCity").val(card.card_bank_address.split("-")[1]);

	$("#card_bank").val(card.card_bank);
	$("#card_branch").val(card.card_branch);
	
	$("#t0_money").val(mer.t0_money);

}

function goToDetail(type) {
	if (type == 'show') {
		$("#main_Change_Div").slideUp(); // 隐藏
		$("#detail_Change_Div").slideDown(); // 显示
		$(".Main_bread").append("<i id=\"second\">查看详情</i> ");
	} else {
		$("#detail_Change_Div").slideUp();
		$("#main_Change_Div").slideDown();
		$("#second").remove();
		$("#mer_id").val('');
		$("#bc_id").val('');
		$("#uid").val('');
		$("input[name='photos']").val('');
		$('#lr_state1')[0].reset();
	}

}

//页面切换
function selectTag(showContent,selfObj){
	// 标签
	var tag = document.getElementById("Tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
	tag[i].className = "";
	}
	selfObj.parentNode.className = "ontitle";
	// 标签内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
	j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
	
}