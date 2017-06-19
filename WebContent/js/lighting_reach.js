/*
created 2016-05-17
王志成
e-mail:hhx_wzc@163.com
*/


//头部下拉框
$(function(){
	var $Lr_hover=$(".lr_hover .divHover");
	var $Lr_show=$(".lr_hide");

    $Lr_hover.hover(

    	function(){
	    	$Lr_show.stop().slideDown();
		},

		function(){
			$Lr_show.stop().slideUp();
		}
	);
});


//左边导航系列
$(function() {

	var $Menu=$(".lr_menu>a");  //
	var $Color=$(".lr_s_menu li a");  //
	var $Lr_current_bg=$(".lr_current");  //获取默认状态的第一个导航
	var $Lr_current_icon=$(".lr_current .icon") ; //获取默认状态的的class为icon的元素
	var $lr_zc_current=$(".lr_zc_current");

	$Lr_current_bg.addClass("current");   //默认导航背景颜色
	$Lr_current_icon.addClass("icon-caret-left");  //默认添加icon图标
	$lr_zc_current.addClass("sec_current");


	//控制左侧一级导航

	$Menu.click(function(){

		$(this).addClass("current")   //当前一级背景颜色
		.children(".icon").addClass("icon-caret-left")  //三角图标出现
		.parent().next().slideDown()           //二级导航出现
		.parent().siblings().children("a").removeClass("current")  //恢复其他一级导航背景颜色
		.children(".icon").removeClass("icon-caret-left")  //其他同辈元素的三角图标消失
		.parent().next().slideUp();            //二级导航隐藏

	});

	//控制左侧二级导航

	$Color.click(function(){
		$(this).addClass("sec_current")   //当前二级背景颜色
		.parent().siblings().children("a").removeClass("sec_current")  //恢复当前元素同辈元素的背景颜色
		.parent().parent().parent().siblings().children("ul").children("li").children("a").removeClass("sec_current");  //恢复当前元素父元素同辈元素的子元素的子元素a的背景颜色
	})
    
});


//商户管理>商户审核查询 select 选择框

$(function(){

	var $select1=$("#ADTstate");
    var $Span1=$("#main_Form span strong");
    var $Span2=$("#main_Form span");

	$select1.change(function(){
		$Span1.text(  $('#ADTstate  option:selected').text());
        $Span2.css({"border-color":"#36c6d3"});

	})
});


//交易查询>下游对账导出 select 选择框

$(function(){

    var $select1=$("#lr_state3 select");
    var $Span1=$("#lr_state3 span strong");
    var $Span2=$("#lr_state3 span");

    $select1.change(function(){

        $Span1.text($(this).val());
        $Span2.css({"border-color":"#36c6d3"});

    })
})


//交易查询>微信对账查询 多选框

$(function(){

    var $lr_checked=$("#lr_checked");
    var $lr_all=$("#lr_all input:not(#lr_checked)");

    $lr_checked.click(function(){
            if(this.checked){
                $lr_all.prop("checked", true);
            }else{
                $lr_all.prop("checked", false);
            }
        });

})

//商户管理>商户审核查询———分页

$(function(){
    var i=0;
    var J_btn2=$("#J_x_btn2");          // 上一页
    var J_btn3=$("#J_x_btn3");          // 下一页
    var J_number=$("#zc_number");       // 样式框
    var J_li=$(".zc_x_li");             // 当前索引
    var J_btn1=$("#J_x_btn1");           // 首页
    var J_btn4=$("#J_x_btn4");           // 末页
    var J_btn6=$("#J_x_btn6");           // 跳转
    var indexG=0;
    var k=0;
    var h=3;
    var t=0;

    

});

//交易查询>分润明细查询查询———分页

$(function(){
    var i=0;
    var J_btn2=$("#J_t_btn2");          // 上一页
    var J_btn3=$("#J_t_btn3");          // 下一页
    var J_number=$("#zc_number1");       // 样式框
    var J_li=$(".zc_t_li");             // 当前索引
    var J_btn1=$("#J_t_btn1");           // 首页
    var J_btn4=$("#J_t_btn4");           // 末页
    var J_btn6=$("#J_t_btn6");           // 跳转
    var indexG=0;
    var k=0;
    var h=3;
    var t=0;

    //默认选中样式
    J_li.eq(0).addClass("add_class");
    J_btn1.addClass("add_class");

});

//交易查询>微信交易流水———分页

$(function(){
    var i=0;
    var J_btn2=$("#J_p_btn2");          // 上一页
    var J_btn3=$("#J_p_btn3");          // 下一页
    var J_number=$("#zc_number3");       // 样式框
    var J_li=$(".zc_p_li");             // 当前索引
    var J_btn1=$("#J_p_btn1");           // 首页
    var J_btn4=$("#J_p_btn4");           // 末页
    var J_btn6=$("#J_p_btn6");           // 跳转
    var indexG=0;
    var k=0;
    var h=3;
    var t=0;

    //默认选中样式
    J_li.eq(0).addClass("add_class");
    J_btn1.addClass("add_class");

});
//交易查询>微信对账查询———分页

$(function(){
    var i=0;
    var J_btn2=$("#J_g_btn2");          // 上一页
    var J_btn3=$("#J_g_btn3");          // 下一页
    var J_number=$("#zc_number2");       // 样式框
    var J_li=$(".zc_g_li");             // 当前索引
    var J_btn1=$("#J_g_btn1");           // 首页
    var J_btn4=$("#J_g_btn4");           // 末页
    var J_btn6=$("#J_g_btn6");           // 跳转
    var indexG=0;
    var k=0;
    var h=3;
    var t=0;

    //默认选中样式
    J_li.eq(0).addClass("add_class");
    J_btn1.addClass("add_class");

});


//生产二维码

$(function(){

    $("#pr_code").click(function(){

        $('#qrcode').qrcode("http://blog.wpjam.com");
         $('#qrcode img').css("display","none");
        //$('#qrcode').qrcode({width: 256,height: 256,text: "http://blog.wpjam.com"});//自定义大小
    })

})











