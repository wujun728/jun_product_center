/* ============================================================
 * chart.painter v1.0 by taylor@ichartjs.com
 * http://www.ichartjs.com/
 * ============================================================
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * ============================================================ */
;$(function(){
	/**
     * =================菜单效果===================begin=
     */
	var H = $(window).height(), 
		$accordion = $("#accordion2"), 
		$sidebar = $(".sidebar-nav"), 
		original_top = 40, 
		$last = $("#last-accordion-group");
	
    /**
     * bind mousewheel
     */
	$sidebar.mousewheel(function(event, delta, deltaX, deltaY) {
		var top = parseInt($(this).css("top"))+(deltaY*120),
			h=$last.height(),
			sidebar_height = $accordion.height();;
		if(top>original_top){
			top = original_top;
		}else if(top<=-(sidebar_height-original_top-h)){
			top = -(sidebar_height-original_top-h);
		}
		$(this).css("top",top);
	    return false;
	});
	
	
    
    $accordion.on('shown', function (e) {
    	var top = parseInt($sidebar.css("top"));
    	if(top<original_top&&($(e.target).parent().position().top-44)<Math.abs(top)){
    		$sidebar.css("top",-($(e.target).parent().position().top-44))
    	}
     });
    /**
     * =================菜单效果===================end=
     */
    /**
	 * ==================绑定按钮事件===============begin
	 */
	var edit_mode = true;
	$(document).keydown(function(e){
		if(e.keyCode==27&&BootStrap.editable()){
			if(!edit_mode){
				$("#ide-back-edit").click();
			}else{
				$("#ide-preview").click();
			}
			return false;
		}
	});
	$("#ide-preview").click(function(){
		if(!$(this).hasClass('active')){
			edit_mode = false;
			$("body").removeClass("edit").addClass("preview"); 
		}
	});
	$("#ide-back-edit").on("click", function(){
		edit_mode = true;
		$("#ide-edit").addClass('active');
		$("#ide-preview").removeClass('active');
		$("body").removeClass("preview").addClass("edit"); 
		$(this).blur();
	});
	
	/**
	 * ==================绑定按钮事件===============end
	 */
	
	
    /**
     * =================组装配置项===================begin=
     */
    //主配置项-begin
    BootStrap.box = $("#collapseMain .accordion-inner");
    
    new BootStrap.Label("尺寸");
	new BootStrap.Input("width",{text:["宽度","px"],min:100,max:2000,step:10,value:800});
	new BootStrap.Input("height",{text:["高度","px"],min:50,max:2000,step:10,value:400});
	
	new BootStrap.Divider();
	new BootStrap.Label("背景");
	new BootStrap.Colors("background_color",{value:'#fefefe'});
	new BootStrap.Label("渐变背景");
	new BootStrap.Switch("gradient").
		bind(new BootStrap.Input("color_factor",{type:"range",text:[],zoom:10,min:0,max:5,value:2,step:1,span:'span4'}));
	
	new BootStrap.Divider();
	new BootStrap.Label("边框");
	new BootStrap.Colors("border.color",{value:'#BCBCBC'});
	new BootStrap.Input("border.width",{text:["","px"],min:0,max:10,value:1,span:'span6'});
	
	new BootStrap.Divider();
	new BootStrap.Label("对齐方式");
	new BootStrap.Alignment("align",{active:'center'});
	
	new BootStrap.Divider();
	new BootStrap.Label("偏移");
	new BootStrap.Input("offsetx",{text:["X轴","px"],min:-600,max:600,step:5,value:0});
	new BootStrap.Input("offsety",{text:["Y轴","px"],min:-600,max:600,step:5,value:0});
	//主配置项-end
	
	 //子配置项-begin
    BootStrap.box = $("#collapseSub .accordion-inner");
    
	new BootStrap.Label("边框");
	new BootStrap.Colors("sub_option.border.color",{value:'#BCBCBC'});
	new BootStrap.Input("sub_option.border.width",{text:["","px"],min:0,max:10,value:1,span:'span6'});
	
	new BootStrap.Divider();
	new BootStrap.Label("阴影");
	new BootStrap.Switch("shadow").
		bind(new BootStrap.Colors("shadow_color",{value:'#666666',position:"top"})).
		bind(new BootStrap.Input("shadow_blur",{type:"range",text:[],text:[],min:2,max:10,value:1,span:'span6'}));
	
	new BootStrap.Divider();
	new BootStrap.Label("文字");
	new BootStrap.Button("sub_option.label.fontweight",{text:"B",value:[500,600]});
	new BootStrap.Input("sub_option.label.fontsize",{text:["","px"],min:10,max:40,value:12,span:'span6'})
	new BootStrap.Colors("sub_option.label.color",{value:'#333333',position:"top"});
	
	new BootStrap.Divider();
	new BootStrap.Label("显示百分比");
	new BootStrap.Switch("showpercent");
	
	new BootStrap.Divider();
	new BootStrap.Label("单柱宽度",{owner:'column2d'});
	new BootStrap.Input("column_width",{text:["","%"],owner:'column2d',unit:'%',min:10,max:100,step:5,value:70,span:'span7'});
	
	new BootStrap.Label("单柱高度",{owner:'bar2d'});
	new BootStrap.Input("bar_height",{text:["","%"],owner:'bar2d',unit:'%',min:10,max:100,step:5,value:70,span:'span7'});
	
	new BootStrap.Label("半径",{owner:'pie2d'});
	new BootStrap.Input("radius",{text:["","%"],owner:'pie2d',unit:'%',min:20,max:100,step:5,value:90,span:'span7'});
	
	new BootStrap.Divider('',{owner:'pie2d'});
	new BootStrap.Label("颜色块",{owner:'pie2d'});
	new BootStrap.Button("sub_option.label.sign",{text:"隐藏",owner:'pie2d',value:['square','']});
	new BootStrap.Input("sub_option.label.sign_size",{text:["","px"],owner:'pie2d',min:10,max:40,value:12,span:'span6'})
	
	new BootStrap.Label("标签边框",{owner:'pie2d'});
	new BootStrap.Colors("sub_option.label.border.color",{value:'#BCBCBC',owner:'pie2d'});
	new BootStrap.Input("sub_option.label.border.width",{text:["","px"],owner:'pie2d',min:0,max:10,value:1,span:'span6'});
	
	new BootStrap.Divider('',{owner:'pie2d'});
	new BootStrap.Label("标签背景",{owner:'pie2d'});
	new BootStrap.Colors("sub_option.label.background_color",{value:'#fefefe',owner:'pie2d',position:"top",opacity:true});
	
	
	//子配置项-end
	
	//标题配置项-begin
	BootStrap.box = $("#collapseTitle .accordion-inner");
	new BootStrap.Label("尺寸");
	new BootStrap.Input("title.height",{text:["高度","px"],min:20,step:2,max:200,value:30});
	
	new BootStrap.Divider();
	new BootStrap.Label("文字");
	new BootStrap.Colors("title.color",{value:'#111111'});
	new BootStrap.Input("title.fontsize",{text:["","px"],min:10,max:60,step:2,value:20,span:'span6'})
	new BootStrap.Select("title.font",{type:"text",value:'微软雅黑'});
	
	
	new BootStrap.Divider();
	new BootStrap.Label("对齐方式");
	new BootStrap.Alignment("title.textAlign",{active:'center'});
	
	new BootStrap.Divider();
	new BootStrap.Label("偏移");
	new BootStrap.Input("title.offsetx",{text:["X轴","px"],min:-600,step:5,max:600,value:0});
	new BootStrap.Input("title.offsety",{text:["Y轴","px"],min:-600,step:5,max:600,value:0});
	//标题配置项-end
	
	//副标题配置项-begin
	BootStrap.box = $("#collapseSubTitle .accordion-inner");
	new BootStrap.Label("尺寸");
	new BootStrap.Input("subtitle.height",{text:["高度","px"],min:20,step:2,max:100,value:20});
	
	new BootStrap.Divider();
	new BootStrap.Label("文字");
	new BootStrap.Colors("subtitle.color",{value:'#111111'});
	new BootStrap.Input("subtitle.fontsize",{text:["","px"],min:10,max:60,step:2,value:16,span:'span6'})
	new BootStrap.Select("subtitle.font",{type:"text",value:'微软雅黑'});
	
	new BootStrap.Divider();
	new BootStrap.Label("对齐方式");
	new BootStrap.Alignment("subtitle.textAlign",{active:'center'});
	
	new BootStrap.Divider();
	new BootStrap.Label("偏移");
	new BootStrap.Input("subtitle.offsetx",{text:["X轴","px"],min:-600,step:5,max:600,value:0});
	new BootStrap.Input("subtitle.offsety",{text:["Y轴","px"],min:-600,step:5,max:600,value:0});
	//副标题配置项-end
	
	//脚注配置项-begin
	BootStrap.box = $("#collapseFoot .accordion-inner");
	new BootStrap.Label("尺寸");
	new BootStrap.Input("footnote.height",{text:["高度","px"],min:20,step:2,max:100,value:20});
	
	new BootStrap.Divider();
	new BootStrap.Label("文字");
	new BootStrap.Colors("footnote.color",{value:'#111111'});
	new BootStrap.Input("footnote.fontsize",{text:["","px"],min:10,max:40,value:12,span:'span6'})
	new BootStrap.Select("footnote.font",{type:"text",value:'微软雅黑'});
	
	new BootStrap.Divider();
	new BootStrap.Label("对齐方式");
	new BootStrap.Alignment("footnote.textAlign",{active:'right'});
	
	new BootStrap.Divider();
	new BootStrap.Label("偏移");
	new BootStrap.Input("footnote.offsetx",{text:["X轴","px"],min:-600,max:600,step:5,value:0});
	new BootStrap.Input("footnote.offsety",{text:["Y轴","px"],min:-600,max:600,step:5,value:0});
	//脚注配置项-end
	
	//图例配置项-begin
	BootStrap.box = $("#collapseLegend .accordion-inner");
	var Ls= new BootStrap.Switch("legend.enable");
	new BootStrap.Label("背景");
	Ls.bind(new BootStrap.Colors("legend.background_color",{value:'#fefefe',opacity:true}));
	
	new BootStrap.Divider();
	new BootStrap.Label("文字");
	new BootStrap.Colors("legend.color",{value:'#333333'});
	new BootStrap.Input("legend.fontsize",{text:["","px"],min:10,max:40,value:12,span:'span6'});
	
	new BootStrap.Divider();
	new BootStrap.Label("边框");
	Ls.bind(new BootStrap.Colors("legend.border.color",{value:'#BCBCBC'}));
	Ls.bind(new BootStrap.Input("legend.border.width",{text:["","px"],min:0,max:10,value:1,span:'span6'}));
	new BootStrap.Label("列数");
	Ls.bind(new BootStrap.Input("legend.column",{text:["","列"],min:1,max:50,value:1,span:'span6'}));
	
	new BootStrap.Divider();
	new BootStrap.Label("水平对齐");
	Ls.bind(new BootStrap.Alignment("legend.align",{active:'right'}));
	new BootStrap.Label("垂直对齐");
	Ls.bind(new BootStrap.VAlignment("legend.valign"));
	
	new BootStrap.Divider();
	new BootStrap.Label("偏移");
	Ls.bind(new BootStrap.Input("legend.offsetx",{text:["X轴","px"],min:-600,max:600,step:5,value:0}));
	Ls.bind(new BootStrap.Input("legend.offsety",{text:["Y轴","px"],min:-600,max:600,step:5,value:0}));
	//图例配置项-end

	//坐标系-begin
    BootStrap.box = $("#collapseCoo .accordion-inner");
    
    new BootStrap.Label("尺寸");
	new BootStrap.Input("coordinate.width",{text:["宽度","%"],unit:'%',min:40,max:100,step:2,value:80,span:'span6'});
	new BootStrap.Input("coordinate.height",{text:["高度","%"],unit:'%',min:40,max:100,step:2,value:84,span:'span6'});
	
	new BootStrap.Divider();
	new BootStrap.Label("背景");
	new BootStrap.Colors("coordinate.background_color",{value:'#ffffff',opacity:true});
	
	new BootStrap.Divider();
	new BootStrap.Label("边框");
	new BootStrap.Colors("coordinate.axis.color",{value:'#a5acb8',position:"top"});
	new BootStrap.Input("coordinate.axis.width",{type:'text',text:["","px"],value:'1 0 1 0',span:'span6',formatter:function(v,f){
		if(f){
			return v.join(" ");
		}else{
			v = v.split(" ");
			for(var i=0;i<4;i++){
				v[i] = parseFloat(v[i])||0;
			}
		}
		return v;
	}});
	new BootStrap.Divider();
	new BootStrap.Label("网格颜色");
	new BootStrap.Colors("coordinate.grid_color",{value:'#d9d9d9',position:"top"});
	
	new BootStrap.Divider();
	new BootStrap.Label("标签文字");
	new BootStrap.Button("label.fontweight",{text:"B",value:[500,600]})
	new BootStrap.Colors("label.color",{value:'#333333',position:"top"});
	new BootStrap.Input("label.fontsize",{text:["","px"],min:10,max:40,value:12,span:'span6'})
	
	new BootStrap.Divider();
	new BootStrap.Label("坐标值轴文本");
	new BootStrap.Button("coordinate.label.fontweight",{text:"B",value:[500,600]})
	new BootStrap.Colors("coordinate.label.color",{value:'#333333',position:"top"});
	new BootStrap.Input("coordinate.label.fontsize",{text:["","px"],min:10,max:40,value:12,span:'span6'})
	
	//坐标系-end
	
});