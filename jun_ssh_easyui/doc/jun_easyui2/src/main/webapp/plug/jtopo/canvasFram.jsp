<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jtopo/topotools/css/menu.css"/>
<script src="${ctx}/plug/jtopo/topotools/require/jtopo-0.4.8-min.js"></script>
<script src="${ctx}/plug/jtopo/topotools/require/Map.js"></script>
<script src="${ctx}/plug/jtopo/topotools/require/Math.uuid.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/tools-core.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/tools-core3.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/tools-core2.js"></script>

<link rel="Stylesheet" type="text/css" href="${ctx}/plug/jpicker-1.1.6/css/jPicker-1.1.6.css" />
<link rel="Stylesheet" type="text/css" href="${ctx}/plug/jpicker-1.1.6/jPicker.css" />
<script src="${ctx}/plug/jpicker-1.1.6/jpicker-1.1.6.js" type="text/javascript"></script>
<style type="text/css">
.EventLeft{width:60px;height:100%;float:left;border:1px solid #d4d4d4;}
.EventRight{width:290px;height:100%;float:left;border:1px solid #d4d4d4;margin-left:1px;}
.eventlist{height:30px;line-height:30px;border-bottom:1px solid #d4d4d4;}
.EventLeft ul{margin:0px;padding:0px;}
.EventLeft ul li{height:20px;line-height:20px;list-style:none;text-align:center;cursor:pointer;*cursor:hand;}
.eventRItem{height:30px;line-height:30px;border-bottom:1px solid #d4d4d4;}

ul{margin:0px;padding:0px;}
ul.level2{width:92px;margin:-38px 0 0 93px;background:#eee;border:1px solid #aaa;display:none;}
ul.level2 li{list-style:none;margin:0px;padding:0px;}
ul.level0 > li.imyeah:hover > ul,ul.level0 > li.imyeah1:hover > ul{display:block;}

.jPicker .Button .Ok{display:none;}
.jPicker .Button .Cancel{display:none;}
</style>
<script>
	var flag=0;
    $(document).ready(function () {
    	$.fn.jPicker.defaults.images.clientPath='${ctx}/plug/jpicker-1.1.6/images/';
    	$("#colorMain").jPicker({window:{title:'颜色选择器'}});
    	$("#colorMain1").jPicker({window:{title:'颜色选择器'}});
    	$("#colorMain2").jPicker({window:{title:'颜色选择器'}});
        var canvas =document.getElementById('canvas__');
        stage = new JTopo.Stage(canvas);
        scene = new JTopo.Scene();
        scene.background = '${ctx}/plug/jtopo/topotools/images/bg21.jpg';
        stage.add(scene);
        scene.mode = 'select';
        scene.areaSelect = false;
        initDBClick();
        initRightButton();
        getjson();
        getFieldJson();
        getjseventCom();
        httpOPtions();
        httpStatusOPtions();
        $('#bundlingDatas').dialog({
            onClose:function(){
             	$("#opMain").html("");
            }
        });
        $('#bundNodeStatus').dialog({
            onClose:function(){
             	$("#opStatusMain").html("");
            }
        });
    });
    function getjson(){
    	var setData={};
    	if(getQueryString("proid")!=null){
    		flag=1;
    		setData["id"]=getQueryString("proid");
	    	data('${ctx}'+actionUrl('/sys/', 'sySbProjectPage','Get_ById'),setData,'json',function(d){
	    		//alert(JSON.stringify(d));
	    		parent.addParentPage(d);
	    		
	    		renderToPo(eval(d.pageContent));
	    	});
    	}else{
    		parent.addParentPage(null);
    		flag=0;
    	}
    }
    function getQueryString(name) {
    	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    	var r = window.location.search.substr(1).match(reg);
    	if (r != null) return unescape(r[2]); return null;
    } 
    function bundlingNodeFieldJson(){
    	var option="";
    	for(var i=0;i<scene.childs.length;i++){
  		   if(scene.childs[i].elementType=="node"){
  			   var nodeStr=scene.childs[i];
  			   if(nodeStr.cNodeId!="undefined"&&nodeStr.cNodeId!=""&&nodeStr.cNodeId!=undefined){
  				 option+='<option value="'+nodeStr.cNodeId+'">'+nodeStr.text+'</option>';
  			   }
  		   }
  	   }
    	$("#bundlingNodeField").html(option);
    }
    function getFieldJson(){
	    data('${ctx}'+actionUrl('/sys/', 'cxSbPoint','ComboBox'),null,'json',function(d){
	    	//alert(JSON.stringify(d));
	    	var option="";
	    	for(var i=0;i<d.length;i++){
	    		option+='<option value="'+d[i].id+'">'+d[i].text+'</option>';
	    	}
	    	$("#bundlingField").html(option);
	    });
    }
    
    function getjseventCom(){
	    data('${ctx}'+actionUrl('/sys/', 'sySbJsDictionaries','ComboBox'),null,'json',function(d){
	    	var option="",list="";
	    	for(var i=0;i<d.length;i++){
	    		option+='<option value="'+d[i].id+'">'+d[i].text+'</option>';
	    		list+='<li>'+d[i].text+'</li>';
	    	}
	    	$(".EventLeft ul").html(list);
	    	$("#eventType").html(option);
	    });
    }
    
    function selectTypeChange(){
    	$("#eventClass").val("customEvent");
    	$("#eventContent").val("");
    }
    
    function selectChange(){
    	var classStr=$("#eventClass").val();
    	if(classStr=="commonEvent"){
    		var setData={};
	    	setData["id"]=$("#eventType").val();
	    	data('${ctx}'+actionUrl('/sys/', 'sySbJsDictionaries','Get_ById'),setData,'json',function(d){
	 	    	$("#eventContent").val(d.jsEventContent);
	 	    });
    	}else{
    		$("#eventContent").val("");
    	}
    }
    
    function getpageCom(){
    	//var pId=${param.proid}+"";
    	var setData={};
    	//setData["sySbProjectPage.parentId"]=pId;
    	var option="";
    	data('${ctx}'+actionUrl('/sys/', 'sySbProjectPage','ComboBox'),setData,'json',function(d){
 	    	for(var i=0;i<d.length;i++){
 	    		option+='<option value="'+d[i].id+'">'+d[i].text+'</option>';
 	    	}
 	    	$("#sbundlingPage").html(option);
 	    	var linkPage=currentNode.linkPage;
        	$("#sbundlingPage").val(linkPage);
 	    });
    }
    
    function saDatas(){
    	var d=[];
    	$("#opMain").find(".opitems").each(function(i){
    		var a=$.trim($(this).find("input[type='text']").val());
    		var b=$(this).find("select.select1").val();
    		var e=$(this).find("select.select2").val();
    		var f=$(this).find("select.select3").val();
    		var c={};
    		c["name"]=a;
    		c["point"]=b;
    		c["visable"]=e;
    		c["toTexId"]=f;
    		d.push(c);
    	})
    	currentNode.fields=d;
    	$("#opMain").html("");
    	$('#bundlingDatas').dialog('close');
	}
    var appendIndex=1,value1="",value2="";
    function httpOPtions(){ 
    	data('${ctx}'+actionUrl('/sys/', 'cxSbPoint','ComboBox'),null,'json',function(d){
 	    	value1=d;
 	    });
    	
    }
    
    function addOPtions(){ 
    	var showStr='<label>指向文本:</label><select class="select3" style="width:50px;"></select>';
    	var rowStr='<div style="margin:10px 0px;" id="opItem'+appendIndex+'" class="opitems"><label>名称:</label><input type="text" style="width:30px;"/>'
    	+'<label>点:</label><select style="width:150px;" class="select1"></select>'
    	+'<label>是否可见:</label><select style="width:50px;" class="select2"><option value="0">否</option><option value="1">是</option></select>'
    	+showStr+'</div>';
    	$("#opMain").append(rowStr);
    	data('${ctx}'+actionUrl('/sys/', 'cxSbPoint','ComboBox'),null,'json',function(d){
 	    	//alert(JSON.stringify(d));
 	    	var option="",option1="";
 	    	for(var i=0;i<d.length;i++){
 	    		option+='<option value="'+d[i].id+'">'+d[i].text+'</option>';
 	    	}
 	    	for(var ii=0;ii<scene.childs.length;ii++){
 	    		if(scene.childs[ii].bundlingNode==currentNode.cNodeId){
 	    			option1+='<option value="'+scene.childs[ii].cNodeId+'">'+scene.childs[ii].text+'</option>'
 	    		}
 	    	}
 	    	//alert("opItem"+appendIndex);
 	    	$("#opItem"+appendIndex).find("select.select1").html(option);
 	    	$("#opItem"+appendIndex).find("select.select3").html(option1);
 	    	appendIndex++;
 	    });
    	
    }
    function deleteOPtions(){
    	if(appendIndex>1){
    		var str=appendIndex-1;
    		$("#opMain").find("#opItem"+str).remove();
    		appendIndex--;
    	}else{
    		alert("没有可以删除的行");
    	}
    }
    
    function setOPtionValue(){
    	var str=currentNode.fields;
    	if(str!=undefined){
	    	for(var i=0;i<str.length;i++){   		
	    		var name=str[i].name;
	    		var point=str[i].point;
	    		var visable=str[i].visable;
	    		var toTexId=str[i].toTexId;
	    		var showStr='<label>指向文本:</label><select class="select3" style="width:50px;"></select>';
	    		var rowStr='<div style="margin:10px 0px;" id="opItem'+appendIndex+'" class="opitems"><label>名称:</label><input type="text" style="width:30px;"/>'
	        	+'<label>点:</label><select style="width:150px;" class="select1"></select>'
	        	+'<label>是否可见:</label><select style="width:50px;" class="select2"><option value="0">否</option><option value="1">是</option></select>'
	        	+showStr+'</div>';
	        	$("#opMain").append(rowStr);
	    		var option="",option1="";
	    		for(var a=0;a<value1.length;a++){
	    			option+='<option value="'+value1[a].id+'">'+value1[a].text+'</option>';
	    		}
	    		for(var ii=0;ii<scene.childs.length;ii++){
	 	    		if(scene.childs[ii].bundlingNode==currentNode.cNodeId){
	 	    			option1+='<option value="'+scene.childs[ii].cNodeId+'">'+scene.childs[ii].text+'</option>'
	 	    		}
	 	    	}
	    		//alert("opItem"+appendIndex);
	 	    	$("#opItem"+appendIndex).find("select.select1").html(option);
	 	    	$("#opItem"+appendIndex).find("select.select3").html(option1);
	 	    	$("#opItem"+appendIndex).find("select.select1").val(point);
	 	    	$("#opItem"+appendIndex).find("select.select2").val(visable);
	 	    	$("#opItem"+appendIndex).find("select.select3").val(toTexId);
	 	    	$("#opItem"+appendIndex).find("input").val(name);
	 	    	appendIndex++;
	    		
	    	}
    	}
    	$('#bundlingDatas').dialog('open');
    }
    var s_appendIndex=1,svalue="";
    function addStatusOPtions(){ 
    	var rowStr='<div style="margin:10px 0px;" id="sopItem'+s_appendIndex+'" class="sopitems">'
    	+'<label>点表状态:</label><input type="text" style="width:100px;"/>'
    	+'<label>图片:</label><select style="width:150px;" class="sselect1"></select></div>';
    	$("#opStatusMain").append(rowStr);
    	var idStr=currentNode.c_Id;
    	var setData={};
    	setData["sySbProjectImagesInfos.PtimitiveId"]=idStr;
    	data('${ctx}'+actionUrl('/sys/', 'sySbProjectImagesInfos','List'),setData,'json',function(d){
 	    	//alert(JSON.stringify(d));
 	    	var option="",option1="";
 	    	for(var i=0;i<d.rows.length;i++){
 	    		
 	    		option+='<option value="'+d.rows[i].imgName+'">'+d.rows[i].imageUname+'</option>';
 	    	}
 	    	//alert("opItem"+appendIndex);
 	    	$("#sopItem"+s_appendIndex).find("select.sselect1").html(option);
 	    	s_appendIndex++;
 	    });
    	
    }
    function deleteStatusOPtions(){
    	if(s_appendIndex>1){
    		var str=s_appendIndex-1;
    		$("#opStatusMain").find("#sopItem"+str).remove();
    		s_appendIndex--;
    	}else{
    		alert("没有可以删除的行");
    	}
    }
    
    function ssaDatas(){
    	var d=[];
    	$("#opStatusMain").find(".sopitems").each(function(i){
    		var a=$.trim($(this).find("input[type='text']").val());
    		var b=$(this).find("select.sselect1").val();

    		var c={};
    		c["status"]=a;
    		c["images"]=b;
    		d.push(c);
    	})
    	currentNode.stausImages=d;
    	$("#opStatusMain").html("");
    	$('#bundNodeStatus').dialog('close');
	}
    
    function httpStatusOPtions(){	
    	data('${ctx}'+actionUrl('/sys/', 'sySbProjectImagesInfos','List_All'),null,'json',function(d){
 	    	svalue=d;
 	    });
    	
    }
    
    function ssetOPtionValue(ptimitiveId){
    	var str=currentNode.stausImages;
    	if(str!=undefined){
	    	for(var i=0;i<str.length;i++){   		
	    		var status=str[i].status;
	    		var images=str[i].images;
	    		var rowStr='<div style="margin:10px 0px;" id="sopItem'+s_appendIndex+'" class="sopitems">'
	        	+'<label>点表状态:</label><input type="text" style="width:100px;"/>'
	        	+'<label>图片:</label><select style="width:150px;" class="sselect1"></select></div>';
	        	$("#opStatusMain").append(rowStr);
	    		var option="",option1="";
	    		for(var a=0;a<svalue.length;a++){
	    			if(ptimitiveId=svalue[a].ptimitiveId){
	    				option+='<option value="'+svalue[a].imgName+'">'+svalue[a].imageUname+'</option>';
	    			}
	    		}
	    		
	    		//alert("opItem"+appendIndex);
	 	    	$("#sopItem"+s_appendIndex).find("select.sselect1").html(option);
	 	    	$("#sopItem"+s_appendIndex).find("select.sselect1").val(images);
	 	    	$("#sopItem"+s_appendIndex).find("input").val(status);
	 	    	s_appendIndex++;
	    		
	    	}
    	}
    	$("#bundNodeStatus").dialog('open');
    }
</script>
</head>
<body>
	<canvas width='1205' height='696' style="border:1px solid #808080" id='canvas__'></canvas>
		<div id="content"></div>
		<textarea id="jtopo_textfield" style="display:none;width: 60px;position: absolute;"
		          onkeydown="if(event.keyCode==13)this.blur();"></textarea>
		<ul id="contextmenu" style="display:none;font-size:12px" class="level0">
			
		    <li class="imyeah" style="width:92px;height:38px;">
			    <a>开始连线</a>
			    <ul class="level2">
			    	<li><a>直线连线</a></li>
			    	<li><a>折线连线</a></li>
			    	<li><a>二次折线连线</a></li>
			    	<li><a>曲线连线</a></li>
			    </ul>
		    </li>
		    <li class="imyeah1" style="width:92px;height:38px;">
		    	<a>事件绑定</a>
		    	<ul class="level2">
			    	<li><a>鼠标左键事件</a></li>
			    	<li><a>鼠标右键事件</a></li>
			    	<li><a>数据事件</a></li>
			    </ul>
		    </li>
		    <li><a>绑定跳转页面</a></li>
		    <li><a>绑定多字段</a></li>
		    <li><a>设置节点状态</a></li>
		    <!-- <li><a>绑定数据</a></li> -->
		    <li><a>设置数据编号</a></li>
		    <li><a>绑定设备编号</a></li>
		    <li><a>放大</a></li>
		    <li><a>缩小</a></li>
		    <!--<li><a>设置成告警</a></li>
		    <li><a>清除告警</a></li>
		    <li><a>顺时针旋转</a></li>
		    <li><a>逆时针旋转</a></li>
		     -->
		    <li><a>删除该节点</a></li>
		</ul>
		
		<ul id="contextmenu2" style="display:none;font-size:12px">
			<li><a>设置线条类型</a></li>
		    <li><a>设置颜色</a></li>
		    <li><a>设置线条宽度</a></li>
		    <li><a>删除连线</a></li>
		</ul>
		<ul id="contextmenu3" style="display:none;font-size:12px" class="level0">
			<li class="imyeah" style="width:92px;height:38px;">
			    <a>开始连线</a>
			    <ul class="level2">
			    	<li><a>直线连线</a></li>
			    	<li><a>折线连线</a></li>
			    	<li><a>二次折线连线</a></li>
			    	<li><a>曲线连线</a></li>
			    </ul>
		    </li>
		    <li><a>设置填充颜色</a></li>
		    <li><a>设置字体颜色</a></li>
		    <li><a>绑定节点</a></li>
		    <li><a>删除该节点</a></li>
		</ul>
		
		<div id="jsonContext" style="width:700px;height:700px;display:none;font-size:12px">
		
		</div>
		
		<div id="bundlingData" class="easyui-dialog" title="绑定数据" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.nodeField=document.getElementById('bundlingField').value;
					$('#bundlingData').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>绑定字段:</label>
		    	<select id="bundlingField">
		    		
		    	</select>
	    	</div>
    	</div>
    </div>
    
    <div id="bundlingDatas" class="easyui-dialog" title="绑定多字段" style="width:500px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					saDatas();
				}
			}],toolbar:[{
				iconCls:'icon-add',
		    	handler:function(){
		    		addOPtions();
		    	}
			},{
				iconCls:'icon-remove',
		    	handler:function(){
		    		deleteOPtions();
		    	}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;" id="opMain">
	    	
    	</div>
    </div>
    
    <div id="bundlingEvent" class="easyui-dialog" title="绑定node事件" style="width:400px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.elementEvent=document.getElementById('eventContent').value;
					$('#bundlingEvent').dialog('close');
				}
			}]">
		<div style="width:98%;height:98%;margin:1px auto;">
	    	<div class="EventLeft">
	    		<div class="eventlist">事件列表</div>
	    		<ul>
	    			<li>单击</li>
	    			<li>双击</li>
	    			<li>移入</li>
	    			<li>移出</li>
	    		</ul>
	    	</div>
	    	<div class="EventRight">
	    		<div class="eventRItem">
	    			<label>事件类型</label>
	    			<select id="eventType" onchange="selectTypeChange()">
	    				<option value="click">单击</option>
	    				<option value="dbclick">双击</option>
	    				<option value="mousemove">移入</option>
	    				<option value="mouseout">移出</option>
	    			</select>
	    		</div>
	    		<div class="eventRItem">
	    			<label>事件分类</label>
	    			<select id="eventClass" onchange="selectChange()">
	    				<option value="customEvent">自定义事件</option>
	    				<option value="commonEvent">常用事件</option>
	    			</select>
	    		</div>
	    		<div style="height:155px;">
	    			<textarea  id="eventContent" style="width:98%;height:99%;resize:none;"></textarea>
	    		</div>
	    	</div>
    	</div>
    </div>
    
    <div id="bundlingDataEvent" class="easyui-dialog" title="绑定数据事件" style="width:400px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.dataElementEvent=document.getElementById('dataEventContent').value;
					$('#bundlingDataEvent').dialog('close');
				}
			}]">
		<div style="width:98%;height:98%;margin:1px auto;">
			<div style="height:30px;line-height:30px;color:red;">注:请务删除cNodeId和datas参数</div>
	    	<textarea  id="dataEventContent" style="width:98%;height:80%;resize:none;"></textarea>
    	</div>
    </div>
    
    <div id="colorcaiter" class="easyui-dialog" title="选择颜色" style="width:600px;height:360px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					var rg=$.jPicker.List[0].color.active.val('all');
					currentNode.strokeColor = rg.r+','+rg.g+','+rg.b;
					$('#colorcaiter').dialog('close');
				}
			}]">
		<div id="colorMain" style="width:100%;height:100%;"></div>
    </div>
    <div id="lineMain" class="easyui-dialog" title="设置线条宽度" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.lineWidth=document.getElementById('wlineValue').value;
					$('#lineMain').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>宽度:</label>
		    	<select id="wlineValue">
		    		<option value="1">1</option>
		    		<option value="2">2</option>
		    		<option value="3">3</option>
		    		<option value="4">4</option>
		    		<option value="5">5</option>
		    		<option value="6">6</option>
		    	</select>
	    	</div>
    	</div>
    </div>
    
    <div id="lineClassMain" class="easyui-dialog" title="设置线条类型" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					
					var str=document.getElementById('lineClassValue').value;
					if(str=='link'){
						currentNode.dashedPattern=null;
					}else if(str=='arrow'){
						currentNode.dashedPattern=5;
					}
					$('#lineClassMain').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>线条类型:</label>
		    	<select id="lineClassValue">
		    		<option value="link">实线</option>
		    		<option value="arrow">虚线</option>
		    	</select>
	    	</div>
    	</div>
    </div>
    
    <div id="bundlingPage" class="easyui-dialog" title="绑定页面" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.linkPage=document.getElementById('sbundlingPage').value;
					$('#bundlingPage').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>绑定页面:</label>
		    	<select id="sbundlingPage">
		    		
		    	</select>
	    	</div>
    	</div>
    </div>
    
    <div id="bundlingNode" class="easyui-dialog" title="绑定节点" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.bundlingNode=document.getElementById('bundlingNodeField').value;
					$('#bundlingNode').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
			<div style="height:50px;color:red;">注:在进行节点绑定时，请先设置需要绑定节点id，再进行绑定</div>
	    	<div style="margin:10px 0px;">
		    	<label>绑定节点:</label>
		    	<select id="bundlingNodeField">
		    		
		    	</select>
	    	</div>
    	</div>
    </div>
    
    <div id="RbundlingEvent" class="easyui-dialog" title="绑定鼠标右键菜单" style="width:500px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'关闭',
				handler:function(){
					$('#RbundlingEvent').dialog('close');
				}
			}]">
		<table id="dg"></table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="javascript:$('#addR').dialog('open');">添加</a>
	</div>
	<script type="text/javascript">
	$(function(){  
        $('#dg').datagrid({
        	width:470,
        	height:220,
        	toolbar:'#tb',
        	fitColumns:true,
        	singleSelect:true,  
            columns:[[ 
              {field:'nodeId',title:'节点编号',width:100},  
              {field:'menuName',title:'菜单名称',width:100},  
              {field:'updateTime',title:'更新时间',width:100},  
              {field:'opt',title:'操作',width:100,align:'center',  
                formatter:function(value,rec,index){    
                    var e = '<a href="#" mce_href="#" onclick="edit(\''+ rec.id + '\')">编辑</a> ';  
                    var d = '<a href="#" mce_href="#" onclick="del(\''+ rec.id +'\',\''+index+'\')">删除</a> ';  
                    return e+d;  
                }  
              }  
            ]],
        });
	})
		function edit(id){
			//alert(id);
			$('#updateR').dialog('open');
			var setData={};
			setData["id"]=id;
			data('${ctx}'+actionUrl('/sys/', 'sySbRightEventInfo','Get_ById'),setData,'json',function(d){
				$('input[name=\'u_id\']').val(d.id);
				$('input[name=\'u_nodeId\']').val(d.nodeId);
				$('input[name=\'u_mune_name\']').val(d.menuName);
				$('#u_REventContent').val(d.eventContent);
			});
		}
		function del(id,index){
			var setData={};
			setData["ids"]=id;
			data('${ctx}'+actionUrl('/sys/', 'sySbRightEventInfo','Delete'),setData,'json',function(d){
				if(d.info=="删除成功!"){
					$('#dg').datagrid('deleteRow', index);
				}
			});
		}
		function appendRow(nodeId,menuName,dateStr){
			$('#dg').datagrid('appendRow',{nodeId:nodeId,menuName:menuName,updateTime:dateStr});
		}
		function saveMenus(){
			var nodeId="";
			var cNodeId=currentNode.cNodeId;
        	if(cNodeId!="undefined"&& cNodeId!=undefined&& cNodeId!=""){
        		nodeId=currentNode.cNodeId;
        	}else{
        		var str=currentNode._id;
        		var indexStr1=parseInt(str.lastIndexOf("-"))+1;
        		var NId=str.substring(indexStr1);
        		nodeId=NId;
        		
        	}
			var myDate = new Date();
	    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
	    	var menuName=$.trim($('input[name=\'mune_name\']').val());
			var content=$.trim($('#REventContent').val());
			var setData={};
			setData["sySbRightEventInfo.nodeId"]=nodeId;
			setData["sySbRightEventInfo.menuName"]=menuName;
			setData["sySbRightEventInfo.eventContent"]=content;
			setData["sySbRightEventInfo.updateTime"]=dateStr;
			data('${ctx}'+actionUrl('/sys/', 'sySbRightEventInfo','Add'),setData,'json',function(d){
	 	    	if(d.info=="新增成功!"){
	 	    		//appendRow(nodeId,menuName,dateStr);
	 				$('#addR').dialog('close');
	 				datagridLoad(nodeId);
	 	    	}
	 	    });
		}
		function updateMenus(){
			var id=$.trim($('input[name=\'u_id\']').val());
			var nodeId=$.trim($('input[name=\'u_nodeId\']').val());
			var myDate = new Date();
	    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
	    	var menuName=$.trim($('input[name=\'u_mune_name\']').val());
			var content=$.trim($('#u_REventContent').val());
			var setData={};
			setData["sySbRightEventInfo.id"]=id;
			setData["sySbRightEventInfo.nodeId"]=nodeId;
			setData["sySbRightEventInfo.menuName"]=menuName;
			setData["sySbRightEventInfo.eventContent"]=content;
			setData["sySbRightEventInfo.updateTime"]=dateStr;
			data('${ctx}'+actionUrl('/sys/', 'sySbRightEventInfo','Update'),setData,'json',function(d){
	 	    	if(d.info=="修改成功!"){
	 	    		datagridLoad(nodeId);
	 				$('#updateR').dialog('close');
	 	    	}
	 	    });
		}
		function datagridLoad(nodeId){
			var setData={},dataStr={};
			setData["sySbRightEventInfo.nodeId"]=nodeId;
			data('${ctx}'+actionUrl('/sys/', 'sySbRightEventInfo','Get_ByObj'),setData,'json',function(d){
				if(d.length>0){
					for(var i=0;i<d.length;i++){
						dataStr.rows=d;
					}
				}else{
					dataStr.rows=[];
				}
				$('#dg').datagrid('loadData',dataStr);
			});
		}
	</script>
		
		<div id="addR" class="easyui-dialog" title="新增菜单" style="width:400px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					saveMenus();
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>菜单名称:</label>
		    	<input type="text" name="mune_name"/>
	    	</div>
	    	<div style="width:98%;height:98%;margin:1px auto;">
				<div style="height:30px;line-height:30px;color:red;">注:只需输入执行方法内容体,结束时输入“;”号</div>
		    	<textarea  id="REventContent" style="width:98%;height:80%;resize:none;"></textarea>
    		</div>
    	</div>
    </div>
    <div id="updateR" class="easyui-dialog" title="修改菜单" style="width:400px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'修改',
				handler:function(){
					updateMenus();
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>菜单名称:</label>
		    	<input type="text" name="u_mune_name"/>
		    	<input type="hidden" name="u_id"/>
		    	<input type="hidden" name="u_nodeId"/>
	    	</div>
	    	<div style="width:98%;height:98%;margin:1px auto;">
				<div style="height:30px;line-height:30px;color:red;">注:只需输入执行方法内容体</div>
		    	<textarea  id="u_REventContent" style="width:98%;height:80%;resize:none;"></textarea>
    		</div>
    	</div>
    </div>
    </div>
    
    <div id="setDeviceDlg" class="easyui-dialog" title="设置设备编号" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.dId=document.getElementById('device_id').value;
					$('#setDeviceDlg').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>设备编号:</label>
		    	<input type="text" name="device_id" id="device_id"/>
	    	</div>
    	</div>
    </div>
    
    <div id="colorcaiter1" class="easyui-dialog" title="设置填充颜色" style="width:600px;height:360px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					var rg=$.jPicker.List[0].color.active.val('all');
					currentNode.alpha =1;
					currentNode.fillColor= rg.r+','+rg.g+','+rg.b;
					$('#colorcaiter1').dialog('close');
				}
			}]">
		<div id="colorMain1" style="width:100%;height:100%;"></div>
    </div>
    
    <div id="colorcaiter2" class="easyui-dialog" title="设置字体颜色" style="width:600px;height:360px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					var rg=$.jPicker.List[0].color.active.val('all');
					currentNode.fontColor= rg.r+','+rg.g+','+rg.b;
					$('#colorcaiter2').dialog('close');
				}
			}]">
		<div id="colorMain2" style="width:100%;height:100%;"></div>
    </div>
    
     <div id="setDataIdDlg" class="easyui-dialog" title="设置数据编号" style="width:200px;height:200px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					currentNode.dataId=document.getElementById('data_id').value;
					$('#setDataIdDlg').dialog('close');
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<div style="margin:10px 0px;">
		    	<label>数据编号:</label>
		    	<input type="text" name="data_id" id="data_id"/>
	    	</div>
    	</div>
    </div>
    
    <div id="bundNodeStatus" class="easyui-dialog" title="绑定节点状态" style="width:500px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					ssaDatas();
				}
			}],toolbar:[{
				iconCls:'icon-add',
		    	handler:function(){
		    		addStatusOPtions();
		    	}
			},{
				iconCls:'icon-remove',
		    	handler:function(){
		    		deleteStatusOPtions();
		    	}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;" id="opStatusMain">
	    	
    	</div>
    </div>
</body>
</html>