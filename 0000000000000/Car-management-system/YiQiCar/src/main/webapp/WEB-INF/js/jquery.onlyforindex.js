/*!
 * 使用须知：仅供INDEX页面使用
 * 必要条件：先载入jQuery1.4+
 * 
 * Copyright 2010-4, Hugo Zhang
 */
$(document).ready(function() {
						   
	//页面框架自适应浏览器可视面积
	confSize();
	
	//左侧导航栏隐藏、显示控制条悬停样式
	$(".toggle").hover(
		function(){$(this).addClass("toggle-hover")}, 
		function(){$(this).removeClass("toggle-hover")} 
	);
	
	//左侧导航栏隐藏、显示控制
	$(".toggle").toggle(
		function(){
			$("#main-nav").animate( { width:'hide',opacity:'hide' }, 500);
			$("#main-content").animate( { marginLeft:'0' }, 500);
			$(this).addClass("toggle-open");
			},
		function(){
			$("#main-nav").animate( { width:'show',opacity:'show' }, 500);
			$("#main-content").animate( { marginLeft:'200' }, 500);
			$(this).removeClass("toggle-open");
			}
	);

});

//浏览器窗口大小改变时
$(window).resize(function(){
	//页面框架自适应浏览器可视面积
	//alert('resize');
	confSize();
});

//设定浏览器最小宽度和高度值
var minW = 1004;
var minH = 500;

function setMinSize(w, h){
	minW = w;
	minH = h;
}

//重置页面框架元素宽度与高度
function confSize(){
	var w = $(window).width(); //取得浏览器窗口宽度
	var h = $(window).height(); //取得浏览器窗口高度
	if (w < minW) //当前浏览器宽度小于最小宽度时
		w = minW; //设定内容宽度等于最小宽度
		//html横向滚动条属性为auto，需针对IE6单独刷新页面内容宽度，此处暂不做处理
		//$("html").css("overflowX","auto");
	if (h < minH) //当前浏览器窗口高度小于最小高度时
		h = minH; //设定内容高度等于最小高度
		$("html").css("overflowY","auto"); //html纵向滚动条属性为auto
	$("#header-wrap,#main-wrap").width(w); //统一页面框架宽度
	$("#siderbar-box,#main-nav,#main-content,#main-content-box,#content-box").height(h - 82); //统一页面框架高度
	//单独设定侧栏导航#nav-box高度，让导航菜单滚动条与浏览器高度契合，但页面内容高度小于设定最小高度时会导致左右滚动条高度不一致。
	$("#nav-box",document.frames("siderbar-box").document).height($(window).height() - 92);
}