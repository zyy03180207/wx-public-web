<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wx.config.*" %>
<%@ page import="com.wx.utils.wxpay.*" %>
<%
	String openid = request.getAttribute("openid").toString();
	String prepay_id = request.getAttribute("prepay_id").toString();
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信H5支付测试</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mui.min.css">
<script src="<%=request.getContextPath()%>/js/mui.min.js"></script>
<script type="text/javascript" charset="utf-8">
			mui.init();
			
		</script>
<style>
.mui-content-padded {
	padding: 10px;
}

body, body .mui-content {
	background-color: #fff !important;
}

#total {
	-webkit-user-select: text;
	text-align: right;
	padding: 0 1em;
	border: 0px;
	border-bottom: 1px solid #007aff;
	border-radius: 0;
	font-size: 16px;
	width: 30%;
	outline: none;
}

textarea {
	margin-top: 10px;
}

.mui-btn-block {
	padding: 8px 5px;
}
</style>
</head>
<body onload="config();">
		<div id="dcontent" class="mui-content">
			<div class="mui-content-padded">
				<p style="text-indent: 22px;">
					
				</p>
				<div style="padding: 0 1em;text-align:center">
					捐赠金额：
					<input id="total" type="number" value="1.0" style="text-align:center" /> 元
				</div>
				<div class="mui-content-padded oauth-area">
					<!--探测本机软件，自动显示支付宝和微信支付方式-->
					<!--银联在线一直显示-->
					<form action="<%=request.getContextPath()%>/pay/pays?money=10&openid=<%=openid%>">
					<input id='UN_WEB' class="mui-btn mui-btn-red mui-btn-block pay" type="submit">
						</form>
				</div>
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						支付方式: <span id="channel">微信支付</span>
					</li>
					<li class="mui-table-view-cell">
						订单号: <span id="bill_no"></span>
					</li>
					<li class="mui-table-view-cell">
						支付金额: <span id="total_fee"></span>
					</li>
					<li class="mui-table-view-cell">
						支付状态:<span id="status"></span>
					</li>
					<textarea name="" id="status_msg" cols="20" rows="5"></textarea>
					<div id='WEB' class="mui-btn mui-btn-red mui-btn-block pay" onmousedown="paying('wx20161202112258ad7168cdf70423983971')">
						确认支付
					</div>
					<div id='EB' class="mui-btn mui-btn-red mui-btn-block pay" onmousedown="config()">
						确认支付
					</div>
				</ul>
			</div>
		</div>
</body>
<script src="<%=request.getContextPath()%>/js/beecloud.js">
	</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="<%=request.getContextPath()%>/js/wxpay.js"></script>
<script src="<%=request.getContextPath()%>/js/sha1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript">

function config() {
	var appip = '<%=Signature.AppId%>';
	var timestamp = '<%=Signature.ctTimestamp()%>';
	var nonceStr = '<%=Signature.ctNonceStr(32)%>';
	var jsApiList = ['chooseWXPay'];
	var array = [appip,'true',timestamp,nonceStr,jsApiList];
	var signatures = '<%=Signature.sign("http://localhost:8080/WX/pay/webpay")%>';
	wx.config({
		debug : false,
		appId : '<%=Signature.AppId%>',
		timestamp : '<%=Signature.timestamp%>',
		nonceStr : '<%=Signature.nonceStr%>',
		signature : '<%=Signature.sign("http://m.zyywxgz.com/WX/pay/webpay")%>',
		jsApiList: ['chooseWXPay']
	});
} 

function paying(prepay_id) {
	var appIp = '<%=Signature.AppId%>';
	var prepay_id = prepay_id;
	var timestamp = '<%=Signature.timestamp%>';
	var nonceStr = '<%=Signature.nonceStr%>';
	var packag = 'prepay_id='+prepay_id;
	var signType = 'MD5';
	var dic = [appIp,timestamp,nonceStr,packag,signType];
	var paySign = signaturemd5(dic);
	wx.chooseWXPay({
	    timestamp: '<%=Signature.timestamp%>',  
	    nonceStr: '<%=Signature.nonceStr%>', 
	    package : 'prepay_id=wx20161202112258ad7168cdf70423983971', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	    signType: 'MD5', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	    paySign: '<%=Signature.signPay(prepay_id)%>', // 支付签名
	    success: function (res) {
	        // 支付成功后的回调函数
	        alert("支付成功")
	    }
	});
}

function pay(openid) {
	
	$.ajax({
		type : "post",
		url : "<%=request.getContextPath()%>/pay/pays",
		data : {
			money : '10',
			openid : openid,
		},
		success : function(data) {
		},
		error : function() {
			alert('数据异常请联系管理员');
		}
	});
}

/**
 * 
 */
function randomStr(len) {
	len = len;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
	var maxPos = $chars.length;
	var pwd = '';
	for(var i=0;i<len;i++){
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}

function signatures(dic) {
	dic = dic;
	var sdic = dic.sort();
	var pwd = '';
	for(var i=0;i<sdic.length;i++){
		pwd += sdic[i];
	}
	return hex_sha1(pwd);
}


function signaturemd5(dic) {
	dic = dic;
	var sdic = dic.sort();
	var pwd = '';
	for(var i=0;i<sdic.length;i++){
		pwd += sdic[i];
	}
	return hex_md5(pwd);
}
/*
*
* Configurable variables.
*
*/
var hexcase = 0; /* hex output format. 0 - lowercase; 1 - uppercase */
var chrsz = 8; /* bits per input character. 8 - ASCII; 16 - Unicode */
/*
*
* The main function to calculate message digest
*
*/
function hex_sha1(s){
   return binb2hex(core_sha1(AlignSHA1(s)));
}
/*
*
* Perform a simple self-test to see if the VM is working
*
*/
function sha1_vm_test(){
   return hex_sha1("abc") == "a9993e364706816aba3e25717850c26c9cd0d89d";
}
/*
*
* Calculate the SHA-1 of an array of big-endian words, and a bit length
*
*/
function core_sha1(blockArray){
   var x = blockArray; // append padding
   var w = Array(80);
   var a = 1732584193;
   var b = -271733879;
   var c = -1732584194;
   var d = 271733878;
   var e = -1009589776;
   for (var i = 0; i < x.length; i += 16) // 每次处理512位 16*32
   {
       var olda = a;
       var oldb = b;
       var oldc = c;
       var oldd = d;
       var olde = e;
       for (var j = 0; j < 80; j++) // 对每个512位进行80步操作
       {
           if (j < 16) 
               w[j] = x[i + j];
           else
               w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
           var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)), safe_add(safe_add(e, w[j]), sha1_kt(j)));
           e = d;
           d = c;
           c = rol(b, 30);
           b = a;
           a = t;
       }
       a = safe_add(a, olda);
       b = safe_add(b, oldb);
       c = safe_add(c, oldc);
       d = safe_add(d, oldd);
       e = safe_add(e, olde);
   }
   return new Array(a, b, c, d, e);
}
/*
*
* Perform the appropriate triplet combination function for the current
* iteration
*
* 返回对应F函数的值
*
*/
function sha1_ft(t, b, c, d){
   if (t < 20) 
       return (b & c) | ((~ b) & d);
   if (t < 40) 
       return b ^ c ^ d;
   if (t < 60) 
       return (b & c) | (b & d) | (c & d);
   return b ^ c ^ d; // t<80
}
/*
*
* Determine the appropriate additive constant for the current iteration
*
* 返回对应的Kt值
*
*/
function sha1_kt(t){
   return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 : (t < 60) ? -1894007588 : -899497514;
}
/*
*
* Add integers, wrapping at 2^32. This uses 16-bit operations internally
*
* to work around bugs in some JS interpreters.
*
* 将32位数拆成高16位和低16位分别进行相加，从而实现 MOD 2^32 的加法
*
*/
function safe_add(x, y){
   var lsw = (x & 0xFFFF) + (y & 0xFFFF);
   var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
   return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0; /* hex output format. 0 - lowercase; 1 - uppercase  */
var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance */
var chrsz = 8; /* bits per input character. 8 - ASCII; 16 - Unicode  */
/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}
function b64_md5(s){ return binl2b64(core_md5(str2binl(s), s.length * chrsz));}
function str_md5(s){ return binl2str(core_md5(str2binl(s), s.length * chrsz));}
function hex_hmac_md5(key, data) { return binl2hex(core_hmac_md5(key, data)); }
function b64_hmac_md5(key, data) { return binl2b64(core_hmac_md5(key, data)); }
function str_hmac_md5(key, data) { return binl2str(core_hmac_md5(key, data)); }
/*
 * Perform a simple self-test to see if the VM is working
 */
function md5_vm_test()
{
 return hex_md5("abc") == "900150983cd24fb0d6963f7d28e17f72";
}
/*
 * Calculate the MD5 of an array of little-endian words, and a bit length
 */
function core_md5(x, len)
{
 /* append padding */
 x[len >> 5] |= 0x80 << ((len) % 32);
 x[(((len + 64) >>> 9) << 4) + 14] = len;
 var a = 1732584193;
 var b = -271733879;
 var c = -1732584194;
 var d = 271733878;
 for(var i = 0; i < x.length; i += 16)
 {
 var olda = a;
 var oldb = b;
 var oldc = c;
 var oldd = d;
 a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
 d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);
 c = md5_ff(c, d, a, b, x[i+ 2], 17, 606105819);
 b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
 a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
 d = md5_ff(d, a, b, c, x[i+ 5], 12, 1200080426);
 c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
 b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);
 a = md5_ff(a, b, c, d, x[i+ 8], 7 , 1770035416);
 d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
 c = md5_ff(c, d, a, b, x[i+10], 17, -42063);
 b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);
 a = md5_ff(a, b, c, d, x[i+12], 7 , 1804603682);
 d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);
 c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);
 b = md5_ff(b, c, d, a, x[i+15], 22, 1236535329);
 a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
 d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
 c = md5_gg(c, d, a, b, x[i+11], 14, 643717713);
 b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);
 a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
 d = md5_gg(d, a, b, c, x[i+10], 9 , 38016083);
 c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);
 b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);
 a = md5_gg(a, b, c, d, x[i+ 9], 5 , 568446438);
 d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);
 c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);
 b = md5_gg(b, c, d, a, x[i+ 8], 20, 1163531501);
 a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);
 d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
 c = md5_gg(c, d, a, b, x[i+ 7], 14, 1735328473);
 b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);
 a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);
 d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
 c = md5_hh(c, d, a, b, x[i+11], 16, 1839030562);
 b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);
 a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
 d = md5_hh(d, a, b, c, x[i+ 4], 11, 1272893353);
 c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);
 b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);
 a = md5_hh(a, b, c, d, x[i+13], 4 , 681279174);
 d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);
 c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);
 b = md5_hh(b, c, d, a, x[i+ 6], 23, 76029189);
 a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
 d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);
 c = md5_hh(c, d, a, b, x[i+15], 16, 530742520);
 b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);
 a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
 d = md5_ii(d, a, b, c, x[i+ 7], 10, 1126891415);
 c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);
 b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);
 a = md5_ii(a, b, c, d, x[i+12], 6 , 1700485571);
 d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
 c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);
 b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
 a = md5_ii(a, b, c, d, x[i+ 8], 6 , 1873313359);
 d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);
 c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
 b = md5_ii(b, c, d, a, x[i+13], 21, 1309151649);
 a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
 d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);
 c = md5_ii(c, d, a, b, x[i+ 2], 15, 718787259);
 b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);
 a = safe_add(a, olda);
 b = safe_add(b, oldb);
 c = safe_add(c, oldc);
 d = safe_add(d, oldd);
 }
 return Array(a, b, c, d);
}
/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t)
{
 return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function md5_ff(a, b, c, d, x, s, t)
{
 return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t)
{
 return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t)
{
 return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t)
{
 return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}
/*
 * Calculate the HMAC-MD5, of a key and some data
 */
function core_hmac_md5(key, data)
{
 var bkey = str2binl(key);
 if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);
 var ipad = Array(16), opad = Array(16);
 for(var i = 0; i < 16; i++)
 {
 ipad[i] = bkey[i] ^ 0x36363636;
 opad[i] = bkey[i] ^ 0x5C5C5C5C;
 }
 var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
 return core_md5(opad.concat(hash), 512 + 128);
}
/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
 var lsw = (x & 0xFFFF) + (y & 0xFFFF);
 var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
 return (msw << 16) | (lsw & 0xFFFF);
}
/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt)
{
 return (num << cnt) | (num >>> (32 - cnt));
}
/*
 * Convert a string to an array of little-endian words
 * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
 */
function str2binl(str)
{
 var bin = Array();
 var mask = (1 << chrsz) - 1;
 for(var i = 0; i < str.length * chrsz; i += chrsz)
 bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);
 return bin;
}
/*
 * Convert an array of little-endian words to a string
 */
function binl2str(bin)
{
 var str = "";
 var mask = (1 << chrsz) - 1;
 for(var i = 0; i < bin.length * 32; i += chrsz)
 str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);
 return str;
}
/*
 * Convert an array of little-endian words to a hex string.
 */
function binl2hex(binarray)
{
 var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
 var str = "";
 for(var i = 0; i < binarray.length * 4; i++)
 {
 str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF) +
   hex_tab.charAt((binarray[i>>2] >> ((i%4)*8 )) & 0xF);
 }
 return str;
}
/*
 * Convert an array of little-endian words to a base-64 string
 */
function binl2b64(binarray)
{
 var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
 var str = "";
 for(var i = 0; i < binarray.length * 4; i += 3)
 {
 var triplet = (((binarray[i >> 2] >> 8 * ( i %4)) & 0xFF) << 16)
    | (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )
    | ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);
 for(var j = 0; j < 4; j++)
 {
  if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
  else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
 }
 }
 return str;
}
/*
*
* Bitwise rotate a 32-bit number to the left.
*
* 32位二进制数循环左移
*
*/
function rol(num, cnt){
   return (num << cnt) | (num >>> (32 - cnt));
}
/*
*
* The standard SHA1 needs the input string to fit into a block
*
* This function align the input string to meet the requirement
*
*/
function AlignSHA1(str){
   var nblk = ((str.length + 8) >> 6) + 1, blks = new Array(nblk * 16);
   for (var i = 0; i < nblk * 16; i++) 
       blks[i] = 0;
   for (i = 0; i < str.length; i++) 
       blks[i >> 2] |= str.charCodeAt(i) << (24 - (i & 3) * 8);
   blks[i >> 2] |= 0x80 << (24 - (i & 3) * 8);
   blks[nblk * 16 - 1] = str.length * 8;
   return blks;
}
/*
*
* Convert an array of big-endian words to a hex string.
*
*/
function binb2hex(binarray){
   var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
   var str = "";
   for (var i = 0; i < binarray.length * 4; i++) {
       str += hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) +
       hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8)) & 0xF);
   }
   return str;
}
/*
*
* calculate MessageDigest accord to source message that inputted
*
*/
function calcDigest(){
   var digestM = hex_sha1(document.SHAForm.SourceMessage.value);
   document.SHAForm.MessageDigest.value = digestM;
}
</script>

</html>