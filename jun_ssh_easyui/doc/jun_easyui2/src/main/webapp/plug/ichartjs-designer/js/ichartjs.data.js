/* ============================================================
 * ichartjs.data.js by taylor@ichartjs.com
 * http://www.ichartjs.com/
 * ============================================================
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * ============================================================ */
!function ($) {
$.DefaultOption = {
	width : 800,
	height : 400,	
	background_color : '#fefefe',
	gradient:false,
	color_factor:0.2,
	border:{
		color:'BCBCBC',
		width:1
	},
	title : {
		color : '#111111',
		fontsize : 20,
		textAlign:'center',
		font:'微软雅黑',
		height:30,
		offsetx:0,
		offsety:0
	},
	subtitle : {
		color : '#111111',
		fontsize : 16,
		textAlign:'center',
		font:'微软雅黑',
		height:20,
		offsetx:0,
		offsety:0
	},
	footnote : {
		color : '#111111',
		fontsize : 12,
		textAlign:'right',
		font:'微软雅黑',
		height:20,
		offsetx:0,
		offsety:0
	},
	align:'center',
	offsetx:0,
	offsety:0,
	shadow : true,
	shadow_blur : 1,
	shadow_color : '#666666',
	showpercent : false,
	column_width:'70%',
	bar_height:'70%',
	radius:'90%',
	sub_option : {
		label : {
			fontsize:11,
			fontweight:500,
			sign:'square',
			background_color:'#fefefe',
			sign_size:12,
			color : '#4572a7',
			border : {
				width : 1,
				color : '#BCBCBC'
			}
		},
		border : {
			width : 1,
			color : '#BCBCBC'
		}
	},
	legend:{
		enable:false,
		border : {
			width : 1,
			color : '#BCBCBC'
		},
		background_color : '#fefefe',
		color:'#333333',
		fontsize:12,
		column:1,
		align:'right',
		valign:'center',
		offsetx:0,
		offsety:0
	},
	label : {
		fontsize:11,
		fontweight:500,
		color : '#666666'
	},
	coordinate : {
		background_color : '#ffffff',
		grid_color : '#d9d9d9',
		label : {
			fontsize:11,
			fontweight:500,
			color : '#666666'
		},
		axis:{
			color:'#a5acb8',
			width:[1,0,1,0]
		},
		width : '80%',
		height:'84'
	}
};
$.Example = [
{
	title:'2D条形图示例',	
	data:{
		title : {
			text : '集团近8年游网游事业部业绩分析',
			color : '#c0c8e7',
			fontsize : 20,
			textAlign:'left',
			font:'微软雅黑',
			height:30,
			offsetx:30,
			offsety:0
		},
		subtitle : {
			text:'Showing AOA online,2012 Estimated,unit:Millions',
			color : '#c0c8e7',
			fontsize : 16,
			textAlign:'left',
			font:'微软雅黑',
			height:20,
			offsetx:30,
			offsety:0
		},
		footnote : {
			text:'Info Center',
			fontsize : 12,
			color : '#5d7f97',
			textAlign : 'right',
			font:'微软雅黑',
			height:20,
			offsetx:0,
			offsety:0
		},
		type:'bar2d',
		gradient:false,
		data:[
	        	{name : '2005',value : 17,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2006',value : 19,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2007',value : 26,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2008',value : 27,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2009',value : 38,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2010',value : 42,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2011',value : 38,color:iChart.toRgba('#ECAD55',0.9)},
	        	{name : '2012',value : 44,color:iChart.toRgba('#ECAD55',0.9)}
    	],
		width : 800,
		height : 400,
		legend:{
			enable:false,
			border : {
				width : 1,
				color : '#BCBCBC'
			},
			color:'#333333',
			fontsize:12,
			background_color : '#fefefe',
			column:1,
			align:'right',
			valign:'center',
			offsetx:0,
			offsety:0
		},
		label : {
			fontsize:11,
			fontweight:500,
			color : '#d3d4f0'
		},
		border:{
			color:'#bcbcbc',
			width:1
		},
		background_color : '#353757',
		shadow : false,
		shadow_blur : 5,
		shadow_color : '#666666',
		showpercent : false,
		bar_height:'70%',
		offsetx : 0,
		offsety : 0,
		sub_option : {
			label : {
				fontsize:11,
				fontweight:500,
				color : '#4572a7'
			},
			border : {
				width : 2,
				color : '#6d869f'
			}
		},
		coordinate : {
			background_color : iChart.toRgba('#353757',0),
			grid_color : '#c0c0c0',
			label : {
				fontsize:11,
				fontweight:500,
				color : '#d3d4f0'
			},
			axis:{
				color : '#666791',
				width : [0, 0, 0, 0]
			},
			width:'82%',
			height:'80%'
		}
	}
},
{
	title:'2D饼图示例',	
	data:{
		title : {
			text : '攻城师需要掌握的核心技能',
			color : '#6d869f',
			fontsize : 20,
			textAlign:'center',
			font:'微软雅黑',
			height:30,
			offsetx:0,
			offsety:0
		},
		subtitle : {
			color : '#111111',
			fontsize : 16,
			textAlign:'center',
			font:'微软雅黑',
			height:20,
			offsetx:0,
			offsety:0
		},
		footnote : {
			color : '#111111',
			fontsize : 12,
			textAlign:'right',
			font:'微软雅黑',
			height:20,
			offsetx:0,
			offsety:0
		},
		type:'pie2d',
		gradient:false,
		data:[
	        	{name : 'HTML5&CSS3',value : 30,color:iChart.toRgba('#fedd74',0.9)},
	        	{name : 'JavaScript',value : 25,color:iChart.toRgba('#82d8ef',0.9)},
	        	{name : 'Java',value : 15,color:iChart.toRgba('#f76864',0.9)},
	        	{name : 'XML',value : 20,color:iChart.toRgba('#80bd91',0.9)},
	        	{name : 'PhotoShop',value : 10,color:iChart.toRgba('#fd9fc1',0.9)}
        	],
		width : 800,
		height : 400,
		label : {
			fontsize:11,
			fontweight:500,
			color : '#666666'
		},
		border:{
			color:'#bcbcbc',
			width:1
		},
		legend:{
			enable:false,
			border : {
				width : 1,
				color : '#BCBCBC'
			},
			color:'#333333',
			fontsize:12,
			background_color : '#fefefe',
			column:1,
			align:'right',
			valign:'center',
			offsetx:0,
			offsety:0
		},
		background_color : '#f4f4f4',
		shadow : true,
		shadow_blur : 5,
		shadow_color : '#666666',
		showpercent : true,
		radius:'90%',
		offsetx : 0,
		offsety : 0,
		sub_option : {
			label : {
				fontsize:18,
				fontweight:500,
				sign:'square',
				background_color:iChart.toRgba('#f4f4f4',0),
				sign_size:20,
				color : '#4572a7',
				border : {
					width : 0,
					color : '#BCBCBC'
				}
			},
			border : {
				width : 2,
				color : '#6d869f'
			}
		}
	}
},{
	title:'2D柱形图示例',	
	data:{
		title : {
			text : '2012年安卓手机游戏数据报告',
			color : '#3e576f',
			fontsize : 26,
			font:'微软雅黑',
			textAlign:'center',
			height:30,
			offsetx:0,
			offsety:0
		},
		subtitle : {
			text : '游戏市场规模达10亿元,单位:百万',
			color : '#3e576f',
			fontsize : 16,
			font:'微软雅黑',
			textAlign:'center',
			height:24,
			offsetx:0,
			offsety:0
		},
		footnote : {
			text : '模拟数据',
			color : '#c0c0c0',
			fontsize : 12,
			font:'微软雅黑',
			textAlign:'right',
			height:20,
			offsetx:0,
			offsety:0
		},
		type:'column2d',
		gradient:false,
		data:[
				{name : 'Jan-Mar',value : 20,color:iChart.toRgba('#827fbf',0.8)},
	        	{name : 'Apr-Jun',value : 24,color:iChart.toRgba('#e4be4d',0.8)},
	        	{name : 'Jul-Sep',value : 30,color:iChart.toRgba('#91aa51',0.8)},
	        	{name : 'Oct-Dec',value : 26,color:iChart.toRgba('#a14545',0.8)}
		],
		width : 800,
		height : 400,
		label : {
			fontsize:11,
			color : '#666666'
		},
		border:{
			color:'#3e1c3e',
			width:1
		},
		legend:{
			enable:false,
			border : {
				width : 1,
				color : '#BCBCBC'
			},
			color:'#333333',
			fontsize:12,
			column:1,
			background_color : '#fefefe',
			align:'right',
			valign:'center',
			offsetx:0,
			offsety:0
		},
		background_color : '#fdfdfd',
		shadow : true,
		shadow_blur : 5,
		shadow_color : '#666666',
		showpercent : false,
		offsetx : 0,
		offsety : -10,
		column_width:'70%',
		sub_option : {
			label : {
				fontsize:11,
				fontweight:600,
				color : '#4572a7'
			},
			border : {
				width : 1,
				color : '#666791'
			}
		},
		coordinate : {
			background_color : iChart.toRgba('#f6f6f6',0.1),
			grid_color : '#c0c0c0',
			label : {
				fontsize:11,
				fontweight:500,
				color : '#666666'
			},
			axis:{
				color : '#666791',
				width : [0, 0, 2, 0]
			},
			width:'88%',
			height:'80%'
		}
	}
},{
	title:'模范示例',	
	data:{
		title : {
			text : 'DOROODO图表组件',
			color : '#f5f5f5',
			fontsize : 24,
			font:'Verdana',
			textAlign:'left',
			height:30,
			offsetx:36,
			offsety:0
		},
		subtitle : {
			text : '一周工作心情指数(百分制)',
			color : '#8d9db5',
			fontsize : 24,
			font:'微软雅黑',
			textAlign:'left',
			height:50,
			offsetx:36,
			offsety:6
		},
		footnote : {
			text : 'DOROODO研发团队',
			color : '#8d9db5',
			fontsize : 14,
			font:'微软雅黑',
			textAlign:'right',
			height:30,
			offsetx:-32,
			offsety:0
		},
		type:'column2d',
		gradient:false,
		data:[
				{name : 'Mon.',value : 50,color:iChart.toRgba('#83a6d5',0.9)},
	        	{name : 'Tues.',value : 60,color:iChart.toRgba('#f37db2',0.9)},
	        	{name : 'Wed.',value : 70,color:iChart.toRgba('#edecee',0.9)},
	        	{name : 'Thur.',value : 80,color:iChart.toRgba('#8fc640',0.9)},
	        	{name : 'Fri.',value : 90,color:iChart.toRgba('#648bbf',0.9)}
		],
		width : 800,
		height : 400,
		label : {
			fontsize:18,
			fontweight:600,
			color : '#f5f5f5'
		},
		border:{
			color:'#404c5d',
			width : 1
		},
		legend:{
			enable:true,
			color:'#c1cdde',
			fontsize:13,
			border : {
				width : 0,
				color : '#85898f'
			},
			column:5,
			background_color : iChart.toRgba('#fefefe',0.2),
			align:'right',
			valign:'top',
			offsetx:-32,
			offsety:-40
		},
		background_color : '#2e3b4e',
		shadow : true,
		shadow_blur : 10,
		shadow_color : '#fafafa',
		showpercent : false,
		offsetx : 0,
		offsety : -20,
		column_width:'70%',
		sub_option : {
			label : {
				fontsize:20,
				fontweight:600,
				color : '#f5f5f5'
			},
			border : {
				width : 1,
				color : '#fefefe'
			}
		},
		coordinate : {
			background_color : iChart.toRgba('#f6f6f6',0.05),
			grid_color : '#c0c0c0',
			label : {
				fontsize:0,
				fontweight:500,
				color : '#f5f5f5'
			},
			axis:{
				color : '#bfbfc3',
				width : [0, 0, 6, 0]
			},
			width:'92%',
			height:'80%'
		}
	}
}];
}(window.BootStrap);
$(function(){
	var $e = $("#ide-example");
	for(var i=0;i<BootStrap.Example.length;i++){
		$e.prepend("<li><a href='javascript:BootStrap.load(BootStrap.Example["+i+"].data)'>"+BootStrap.Example[i].title+"</a></li>");
	}
//	setTimeout(function(){
//		BootStrap.load(BootStrap.Example[3].data)
//	},500);
//	setChartData(BootStrap.Example[2].data);
});

function so(obj){
	var info="";
	if(obj){
		for (var i in obj)
			info += "属性:"+i+" 值:"+obj[i]+"</br>";
	}else{
		info="对象为空";
	}
	alert(info);
}

function setChartData(d){
	BootStrap.load(d)
}

function getCode(){
	$('#ide-sys-code').click();
	return $('#code').html();
}