<%@page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script src="${ctx}/plug/jtopo/topotools/require/jtopo-0.4.8-min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jtopo/topotools/css/index.css"/>
<script src="${ctx}/plug/jtopo/topotools/require/Map.js"></script>
<script src="${ctx}/plug/jtopo/topotools/require/Math.uuid.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/tools-core2.js"></script>

<link rel="Stylesheet" type="text/css" href="${ctx}/plug/jpicker-1.1.6/css/jPicker-1.1.6.css" />
<link rel="Stylesheet" type="text/css" href="${ctx}/plug/jpicker-1.1.6/jPicker.css" />
<script src="${ctx}/plug/jpicker-1.1.6/jpicker-1.1.6.js" type="text/javascript"></script>
<script>
	var menuJson=[];
	var g_Ids,sys_name,g_Ids1,sys_name1;
    var RENDER_MAP = new Map_();
    var leftX = 47;
    $(document).ready(function () {
    	//getImgList();
        $.fn.jPicker.defaults.images.clientPath='${ctx}/plug/jpicker-1.1.6/images/';
    	$("#bcolorMain").jPicker({window:{title:'颜色选择器'}});
        getProjectList();
        httpPrimitiveClass();
    });
    
    function prosubmit(){
    	var projectName=$.trim($('input[name="projectName"]').val());
    	var projectDes=$.trim($('#projectDes').val());
    	//var indexPage=$.trim($('input[name="indexPage"]').val());
    	var setData={};
    	setData['sySbProject.projectName']=projectName;
    	setData['sySbProject.projectDescription']=projectDes;
    	//setData['sySbProject.indexPage']=indexPage;
    	data(getUrl('sySbProject','Add'),setData,'json',function(d){
    		if(d.info=="新增成功!"){
    			$('#addProjectDlg').dialog('close');
    			location.reload();
    		}
    	});
    }
    
    function updatePro(){
    	var id=$.trim($('input[name="uid"]').val());
    	var projectName=$.trim($('input[name="uprojectName"]').val());
    	var projectDes=$.trim($('#uprojectDes').val());
    	var indexPage=$.trim($('input[name="uindexPage"]').val());
    	var pageNode=$("#indexPageOPtion").val();
    	var setData={};
    	setData['sySbProject.id']=id;
    	setData['sySbProject.projectName']=projectName;
    	setData['sySbProject.projectDescription']=projectDes;
    	setData['sySbProject.indexPage']=indexPage;
    	setData['sySbProject.pageNode']=pageNode;
    	data(getUrl('sySbProject','Update'),setData,'json',function(d){
    		if(d.info=="修改成功!"){
    			$('#updateProjectDlg').dialog('close');
    		}
    	});
    }
    
    function Imgsubmit(){
    	var primitiveName=$.trim($('input[name="primitiveName"]').val());
    	var myDate = new Date();	
    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
    	var primitiveNodeType=$('select[name="primitiveNodeType"]').val();
    	var imgWidth=$.trim($('input[name="imgWidth"]').val());
    	var imgHeight=$.trim($('input[name="imgHeight"]').val());
    	var classId=$("#primitiveClass").val();
    	var setData={};
    	setData['sySbPrimitive.primitiveName']=primitiveName;
    	setData['sySbPrimitive.classId']=classId;
    	setData['sySbPrimitive.updateTime']=dateStr;
    	setData['sySbPrimitive.fileName']=sys_name;
    	setData['sySbPrimitive.primitiveNodeType']=primitiveNodeType;
    	setData['sySbPrimitive.imgWidth']=imgWidth;
    	setData['sySbPrimitive.imgHeight']=imgHeight;
    	data(getUrl('sySbPrimitive','Add_HasFiles'),setData,'json',updateFun);
    }
    
    function getPrimitiveClass(){
    	data('${ctx}'+actionUrl('/sys/', 'sySbPrimitiveClass','ComboBox'),null,'json',function(d){
    		option="";
    		for(var i=0;i<d.length;i++){
    			var idStr=d[i].id;
    			var classStr=d[i].text;
    			option+="<option value=\""+idStr+"\">"+classStr+"</option>";
    		}
    		$("#primitiveClass").html(option);
    	});
    }
    
    function updateFun(d){
    	if(d.info=='新增成功!'){
    		var setData={'ids':g_Ids,'fileid':d.fileid};
    		$('#addImgListDlg').dialog('close');
    		data(getUrl('syFile','Update'),setData,'json',null);
    	}else if(d.info=='删除成功!'){
    		var setData={'fileids':d.fileids};
    		data(getUrl('syFile','Delete_ByFileIds'),setData,'json',null);
    	}
    }
    function submitUploadForm(){
    	$("#i_sy_tsbPrimitive_datagrid_upload_dialog_form").form('submit',{
    		success:function(d){
    			d=$.parseJSON(d);
    			g_Ids=d.syFiles[0].id;
    			sys_name=d.syFiles[0].sysname;
    			log(d.info);
    		}
    	});
    }
    function getProjectList(){
    	data('${ctx}'+actionUrl('/sys/', 'sySbProject','List_All'),null,'json',getlist);
    }
    function getlist(d){
    	 
    	var html="",option="";
    	var i;
    	for(i=0;i<d.length;i++){
    		var id=d[i].id;
    		var text=d[i].projectName;
    		if(i==0){
				option+='<option value="0">无</option>';
			}
    		option+="<option value=\""+id+"\">"+text+"</option>";
    		html="<div id=\"p"+id+"\"></div>";
    		$("#prodiv").append(html);
    		$('#p'+id).panel({
        	    title:text,
                collapsible:true,
                collapsed:true,
                fit:true,
                tools: [{   
                    iconCls:'icon-edit',
                    handler:function(){
                    	var str=$(this).parent().parent().siblings().attr("id");
                    	str=str.substring(1);
                    	getProjectJson(str);
                    	$('#updateProjectDlg').dialog('open');
                    }   
                }]
        	});
    		var setData={};
    		setData["sySbProjectPage.parentId"]=id;
    		data('${ctx}'+actionUrl('/sys/', 'sySbProjectPage','Get_ByObj'),setData,'json',function(d){
    			htmlStr="";
    			htmlStr+="<ul>";
    			var parentId;
    			for(var a=0;a<d.length;a++){
    				var idS=d[a].id;
    				parentId=d[a].parentId;
    				htmlStr+="<li><div><a type='nav_foot' id='"+idS+"' onclick='clickProItem("+idS+")'"
	    			+" href='javascript:void(0);'><span class='icon-standard-report-key'>"
	    			+"&nbsp;&nbsp;&nbsp;&nbsp;</span><span class='nav'>"+d[a].pageName+"</span><span class='delete-ico' onclick='deleteProPage("+idS+")'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></div></li>";
    			}
    			htmlStr+="</ul>";
    			//alert(parentId);
    			
    			$("#p"+parentId).html(htmlStr);
    		});
    		
    	}
    	$("#parentNode").html(option);
    }
    function getProjectJson(id){
    	var setData={};
    	setData["id"]=id;
	    data('${ctx}'+actionUrl('/sys/', 'sySbProject','Get_ById'),setData,'json',function(d){
	    	//alert(JSON.stringify(d));
	    	$('input[name="uid"]').val(d.id);
	    	readOneProject(d.id,d.pageNode);
	    	$('input[name="uprojectName"]').val(d.projectName);
	    	$('#uprojectDes').val(d.projectDescription);
	    	$('input[name="uindexPage"]').val(d.indexPage);
	    });
    }
    
    function readOneProject(id,pageid){
    	var setData={};
    	setData["sySbProjectPage.parentId"]=id;
    	data('${ctx}'+actionUrl('/sys/', 'sySbProjectPage','Get_ByObj'),setData,'json',function(d){
    		var option="";
    		for(var i=0;i<d.length;i++){
    			if(i==0){
    				option+='<option value="0">无</option>';
    			}
    			option+="<option value=\""+d[i].id+"\">"+d[i].pageName+"</option>";
    		}
    		$("#indexPageOPtion").html(option);
    		$("#indexPageOPtion").val(pageid);
    	});
    	//$("#indexPageOPtion").text(pageName);
    	 
    }
    function getImgList(classId){
    	//var idata=[];
    	var setData={},a=0;
    	setData["sySbPrimitive.classId"]=classId;
    	//alert(classId);
    	data('${ctx}'+actionUrl('/sys/', 'sySbPrimitive','Get_ByObj'),setData,'json',function(d){
    		for(var i=0;i<d.length;i++){
    			var obj={};
    			var listStr={};
    			obj["fileid"]="sySbPrimitive-"+d[i].id;
	    		listStr["c_Id"]=d[i].id;
	    		listStr["dId"]=d[i].dId;
	    		listStr["nodeId"]=d[i].nodeId;
	    		listStr["nodeElementType"]=d[i].primitiveNodeType;
	    		listStr["c_name"]=d[i].primitiveName;
	    		if(d[i].primitiveNodeType=="textNode"){
	    			listStr["c_imagePath"]="";
	    		}else{
	    			listStr["c_imagePath"]=top.sysPath+"/fileupload/"+d[i].fileName;
	    		}
	    		listStr["nodeField"]=d[i].nodeField;
	    		listStr["c_sizeW"]=parseInt(d[i].imgWidth);
	    		listStr["c_sizeH"]=parseInt(d[i].imgHeight);
	    		listStr["d_name"]=d[i].primitiveName;
	    		listStr["d_sizeW"]=parseInt(d[i].imgWidth);
	    		listStr["d_sizeH"]=parseInt(d[i].imgHeight);
	    		listStr["elementEvent"]=d[i].elementEvent;
	    		listStr["nodeField"]=d[i].nodeField;
	    		listStr["linkPage"]=d[i].linkPage;
	    		listStr["scaleX"]=d[i].scaleX;
	    		listStr["scaleY"]=d[i].scaleY;
	    		listStr["dataElementEvent"]=d[i].dataElementEvent;
	    		listStr["lineType"]=d[i].lineType;
	    		listStr["bundlingNode"]=d[i].bundlingNode;
	    		listStr["cNodeId"]=d[i].cNodeId;
	    		listStr["fields"]=d[i].fields;
	    		listStr["fontColor"]=d[i].fontColor;
	    		listStr["dataId"]=d[i].dataId;
	    		menuJson.push(listStr);
	    		a++;
    		}
    		$("#leftCanvas__"+classId).attr("height",a*70);
    		a=0;
    		loadCanvas(classId);
    		//alert(JSON.stringify(menuJson));
    	});
    }
    function loadCanvas(classId){
    	var canvas = document.getElementById('leftCanvas__'+classId);
        var stage = new JTopo.Stage(canvas);
        var scene = new JTopo.Scene();
        scene.mode = 'select';
        scene.areaSelect = false;
        stage.add(scene);
        for (var i = 0; i < menuJson.length; i++) {
            var jsonNode = menuJson[i];
            var node = createModel(jsonNode);
            RENDER_MAP.put(jsonNode.c_Id, jsonNode);
            var s = 10 + i * 65;
            if (s == 10) {
                s = 18;
            }
            node.setLocation(leftX, s);
            scene.add(node);
        }
        menuJson=[];
    }
    function submitdata(){
    	var setData={};
    	var pageName=$("input[name='pageName']").val();
    	var dataJson=document.getElementById('theJson').value;
    	var myDate = new Date();	
    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
    	var parentId=$("#parentNode").val();
    	var pId=$("#pId").val();
    	var nodeId=$("#LinkNodeId").val();
    	var bcolor=$("#Binded").val();
    	setData["sySbProjectPage.parentId"]=parentId;
    	setData["sySbProjectPage.pageName"]=pageName;
    	setData["sySbProjectPage.pageContent"]=dataJson;
    	setData["sySbProjectPage.updateTime"]=dateStr;
    	setData["sySbProjectPage.backgroundColor"]=bcolor;
    	setData["sySbProjectPage.pid"]=pId;
    	setData["sySbProjectPage.nodeId"]=nodeId;
    	if(canvasFram.window.flag==0){
    		data(getUrl('sySbProjectPage','Add'),setData,'json',function(d){
        		if(d.info=="新增成功!"){
        			$('#getjson').dialog('close');
        			location.reload();
        		}
        	});
    	}else{
    		setData["sySbProjectPage.id"]=$("input[name='id']").val();
    		data(getUrl('sySbProjectPage','Update'),setData,'json',function(d){
        		if(d.info=="修改成功!"){
        			$('#getjson').dialog('close');
        		}
        	});
    	}
    }
    
    function clickProItem(id){
    	$("#"+id).parent().parent().css("background","#9d9d9d");
    	$("#"+id).parent().parent().siblings().css("background","#fff");
    	$("#canvasFram").attr("src","${ctx}/plug/jtopo/canvasFram.jsp?proid="+id);
    }
    
    function selectChange(json){
    	var pId=$("#parentNode").val();
    	var setData={};
    	setData["sySbProjectPage.parentId"]=pId;
    	data(getUrl('sySbProjectPage','Get_ByObj'),setData,'json',function(d){
    		var option="";
    		for(var i=0;i<d.length;i++){
    			if(i==0){
    				option+='<option value="0">无</option>';
    			}
    			option+="<option value=\""+d[i].id+"\">"+d[i].pageName+"</option>"
    		}
    		$("#pId").html(option);
    		$("#LinkNodeId").html(option);
    		if(json!=null){
	    	 	$("#pId").val(json.pid);
	    	    $("#LinkNodeId").val(json.nodeId);
    		}
    	});
    	return true;
    }
    function addParentPage(json){
    	if(json!=null){
	    	$("input[name='id']").val(json.id);
	    	$("input[name='pageName']").val(json.pageName);
	    	$("#parentNode").val(json.parentId);
	    	$("#Binded").val(json.backgroundColor);
	    	selectChange(json);
    	}else{
    		$("input[name='id']").val("");
	    	$("input[name='pageName']").val("");
	    	$("#parentNode").val("0");
	    	$("#LinkNodeId").val("0");
	    	$("#Binded").val("255,255,255");
    	}
    }
    function selectchNodeChange(){
    	var str=$("#indexPageOPtion").val();
    	$("input[name='uindexPage']").val("/component/scada.jsp?id="+str);
    }
    
    function prisubmit(){
    	var priName=$.trim($('input[name="priName"]').val());
    	var myDate = new Date();
    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
    	var setData={};
    	setData['sySbPrimitiveClass.className']=priName;
    	setData['sySbPrimitiveClass.updateTime']=dateStr;
    	data(getUrl('sySbPrimitiveClass','Add'),setData,'json',function(d){
    		if(d.info=="新增成功!"){
    			$('#addPriModeDlg').dialog('close');
    		}
    	});
    }
    
    function httpPrimitiveClass(){
    	data('${ctx}'+actionUrl('/sys/', 'sySbPrimitiveClass','ComboBox'),null,'json',function(d){
    		option="";
    		for(var i=0;i<d.length;i++){
    			var className=d[i].text;
    			var html='<div id="p'+className+'"><p>panel content.</p><p>panel content.</p></div>';
    			$("#primitiveMain").append(html);
    			$('#p'+className).panel({
            	    title:className,
                    collapsible:true,
                    collapsed:true,
                    fit:true
            	});
    			var hcontent='<canvas width="125" height="0" id="leftCanvas__'+d[i].id+'"></canvas>';
    			$("#p"+className).html(hcontent);
    			getImgList(d[i].id);
    		}
    	});
    }
    var b_isleft = true;
    function changeLayout() {
    	if (b_isleft) {
    		$('body').layout('collapse', 'west');
    		b_isleft = false;
    	} else {
    		$('body').layout('expand', 'west');
    		b_isleft = true;
    	}
    	$('.tabs-selected').click();
    }
    function deleteProPage(id){
    	var mes=confirm("您确定要删除吗？");
    	if(mes==true){
    		var setData={};
	    	setData["ids"]=id;
	    	data(getUrl('sySbProjectPage','Delete'),setData,'json',function(d){
	    		if(d.info=="删除成功!"){
	    			location.reload();
	    		}
	    	});
    	}
    }
    function getPrimitiveCom(){
    	data(getUrl('sySbPrimitive','ComboBox'),null,'json',function(d){
    		var option="";
    		if(d.length>0){
	    		for(var i=0;i<d.length;i++){
	    			if(i==0){
	    				option+='<option value="0">无</option>';
	    			}
	    			option+='<option value="'+d[i].id+'">'+d[i].text+'</option>';
	    		}
    		}else{
    			option+='<option value="0">无</option>';
    		}
    		$("#uimgClass").html(option);
    	});
    }
    function submitUploadImgForm(){
    	$("#i_sy_uploadImg_datagrid_upload_dialog_form").form('submit',{
    		success:function(d){
    			d=$.parseJSON(d);
    			g_Ids1=d.syFiles[0].id;
    			sys_name1=d.syFiles[0].sysname;
    			log(d.info);
    		}
    	});
    }
    function changeClassSelect(){
    	var str=$("#uimgClass").val();
    	$("#fileClass").val(str);
    }
    
    function submitProImg(){
    	var myDate = new Date();	
    	var dateStr=myDate.getFullYear()+"-"+myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes()+":"+myDate.getSeconds();
    	var primitiveId=$('#uimgClass').val();
    	var imageUname=$.trim($("input[name='uimageName']").val());
    	alert(imageUname);
    	var setData={};
    	setData['sySbProjectImagesInfos.ptimitiveId']=primitiveId;
    	setData['sySbProjectImagesInfos.imageUname']=imageUname;
    	setData['sySbProjectImagesInfos.updateTime']=dateStr;
    	setData['sySbProjectImagesInfos.imgName']=sys_name1;
    	data(getUrl('sySbProjectImagesInfos','Add'),setData,'json',updateFun1);
    }
    function updateFun1(d){
    	if(d.info=='新增成功!'){
    		var setData={'ids':g_Ids1,'fileid':d.fileid};
    		$('#uploadImgDlg').dialog('close');
    		data(getUrl('syFile','Update'),setData,'json',null);
    	}else if(d.info=='删除成功!'){
    		var setData={'fileids':d.fileids};
    		data(getUrl('syFile','Delete_ByFileIds'),setData,'json',null);
    	}
    }
</script>
<style type="text/css">
.easyui-accordion ul li a {
	line-height: 24px;
}
a {
	color: Black;
	text-decoration: none;
	blr: expression(this.onFocus=this.blur ());
	outline: none
}
.panel ul {
	margin: 0px;
	padding: 10px;
}

.panel ul li,.easyui-accordion ul li {
	padding: 0px;
	list-style-type: none;
}
.panel-body ul li{height:30px;line-height:30px;}
.panel-body ul li a{display:block;width:100%;height:100%;}
.delete-ico{float:right;background:url("/doroodo/plug/jquery-easyui-1.3.3/themes/icons/cancel.png") no-repeat;}

.jPicker .Button .Ok{display:none;}
.jPicker .Button .Cancel{display:none;}
</style>
</head>
<body class="easyui-layout">  
    <div data-options="region:'east',iconCls:'icon-reload',title:'图元列表',split:true,tools:[{
    	iconCls:'icon-add',
    	handler:function(){
    		getPrimitiveClass();
    		$('#addImgListDlg').dialog('open');
    	}
    },{
    	iconCls:'icon-database_link',
    	handler:function(){
    		$('#addPriModeDlg').dialog('open');
    	}
    }]" style="width:160px;" id="primitiveMain">
    	<!-- <canvas width='125' height='785' id='leftCanvas__'></canvas> -->
    </div>  
    <div data-options="region:'west',title:'组态工程',split:true,tools: [{   
        iconCls:'icon-add',   
        handler:function(){$('#addProjectDlg').dialog('open'); }   
    }]" style="width:200px;" id="prodiv">
    </div>  
    <div data-options="region:'center',title:'页面',tools: [{
    iconCls:'icon-images',
    handler:function(){
    	getPrimitiveCom();
    	$('#uploadImgDlg').dialog('open');
    	}
    },{
    iconCls:'icon-add',
    handler:function(){
    	$('.panel-body').find('ul li').css('background','#fff');
    	$('#canvasFram').attr('src','${ctx}/plug/jtopo/canvasFram.jsp');
    	}
    },{
    iconCls:'icon-layers',
    handler:function(){
    	changeLayout();
    	}
    },{   
        iconCls:'icon-save',   
        handler:function(){document.getElementById('canvasFram').contentWindow.saveJson();$('#getjson').dialog('open'); }   
    }]" >
    	<iframe src="${ctx}/plug/jtopo/canvasFram.jsp" style="width:99%;height:95%;" name="canvasFram" id="canvasFram"></iframe>
    </div>  
    <div id="getjson" class="easyui-dialog" title="页面数据" style="width:400px;height:300px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
				 	//var jsons=eval(document.getElementById('theJson').value);
					//renderToPo(jsons); 
					submitdata();
				}
			}]">
		<div style="width:90%;height:90%;margin:0px auto;">
	    	<textarea id="theJson" style="width:90%;height:30%;display:none;"></textarea>
	    	<div style="margin:10px 0px;">
		    	<label>页面名称:</label>
		    	<input type="hidden" name="id" value="0"/>
		    	<input type="text" name="pageName" style="width:140px;"/>
	    	</div>
	    	<div style="margin:10px 0px;">
		    	<label>隶&nbsp;&nbsp;属 &nbsp;于:</label>
		    	<select id="parentNode" onchange="selectChange(null)">
		    		
		    	</select>
		    	<select id="pId">
		    		<option value="0">无</option>
		    	</select>
	    	</div>
	    	<div style="margin:10px 0px;">
		    	<label>链接节点:</label>
		    	<select id="LinkNodeId">
		    		<option value="0">无</option>
		    	</select>
	    	</div>
	    	<div style="margin:10px 0px;">
		    	<label>设置背景色:</label>
		    	<input id="Binded" type="text" readonly="readonly" value="" /><input type="button" onclick="javascript:$('#bcolorcaiter').dialog('open');" value="设置颜色">
	    	</div>
    	</div>
    </div>
    <div id="bcolorcaiter" class="easyui-dialog" title="选择颜色" style="width:600px;height:360px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'设置',
				handler:function(){
					var rg=$.jPicker.List[0].color.active.val('all');
					$('#Binded').val( rg.r+','+rg.g+','+rg.b);
					$('#bcolorcaiter').dialog('close');
				}
			}]">
		<div id="bcolorMain" style="width:100%;height:100%;"></div>
    </div>
    <div id="addProjectDlg" class="easyui-dialog" title="添加工程" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
     text:'保存',
     handler:function(){
     	//$('#projectForm').form('submit');
     	prosubmit();
     }
     }]">
     	<div style="padding:10px 60px 20px 60px">
        <form id="projectForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>工程名称:</td>
                    <td><input class="easyui-validatebox" type="text" name="projectName" data-options="required:true" style="width:160px;"></input></td>
                </tr>
                <tr>
                    <td>工程描述:</td>
                    <td>
                    	<textarea id="projectDes" style="width:160px;height:100px;resize:none;"></textarea>
                    </td>
                </tr>
                <!-- <tr>
                    <td>首页地址:</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" name="indexPage"  style="width:160px;"></input>
                    </td>
                </tr> -->
            </table>
        </form>
        </div>
     </div>
     <div id="addImgListDlg" class="easyui-dialog" title="添加图元列表" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
     text:'保存',
     handler:function(){
     	//alert('保存');
     	Imgsubmit();
     	//$('#ImgListForm').form('submit');
     }
     }]">
		  <div style="padding:10px 60px 20px 60px">
		    <form id="ImgListForm">
		       <div>
			      <label>图元名称:</label>
	              <input class="easyui-validatebox" name="primitiveName"/>
               </div>
               <div>
			      <label>图元分类:</label>
	              <select id="primitiveClass">
	              	
	              </select>
               </div>
               <div>
			      <label>图片宽度:</label>
	              <input class="easyui-validatebox" name="imgWidth" value="25"/>
               </div>
               <div>
			      <label>图片高度:</label>
	              <input class="easyui-validatebox" name="imgHeight" value="25"/>
               </div>
               <div>
			      <label>图元类型:</label>
	              <select name="primitiveNodeType">
	              	<option value="node">node</option>
	              	<option value="textNode">textNode</option>
	              </select>
               </div>
		    </form>
		    <hr style="width:100%;height:1px;"/>
		    <form id="i_sy_tsbPrimitive_datagrid_upload_dialog_form" action='/doroodo/sys/syFile_Upload?fileid=' enctype="multipart/form-data" method="post" >
    			<input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        		<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
		  </div>
    </div>
    <div id="updateProjectDlg" class="easyui-dialog" title="修改工程" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
     text:'保存',
     handler:function(){
     	updatePro();
     }
     }]">
     	<div style="padding:10px 60px 20px 60px">
        <form id="uprojectForm" method="post">
        <input  type="hidden" name="uid"></input>
            <table cellpadding="5">
                <tr>
                    <td>工程名称:</td>
                    <td><input class="easyui-validatebox" type="text" name="uprojectName" data-options="required:true" style="width:160px;"></input></td>
                </tr>
                <tr>
                    <td>工程描述:</td>
                    <td>
                    	<textarea id="uprojectDes" style="width:160px;height:100px;resize:none;"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>首页节点:</td>
                    <td>
                    	<select id="indexPageOPtion" onchange="selectchNodeChange()">
                    	
                    	</select>
                    </td>
                </tr>
                <tr>
                    <td>首页地址:</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" name="uindexPage" readonly="readonly" style="width:160px;"></input>
                    </td>
                </tr>
            </table>
        </form>
        </div>
     </div>
     <div id="addPriModeDlg" class="easyui-dialog" title="添加图元分类" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
     text:'保存',
     handler:function(){
     	prisubmit();
     }
     }]">
     	<div style="padding:10px 60px 20px 60px">
        <form id="addPriModeDlgForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>分类名称:</td>
                    <td><input class="easyui-validatebox" type="text" name="priName" data-options="required:true" style="width:160px;"></input></td>
                </tr>
            </table>
        </form>
        </div>
     </div>
     
     <div id="uploadImgDlg" class="easyui-dialog" title="上传图片" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
     text:'保存',
     handler:function(){
     	submitProImg();
     }
     }]">
		  <div style="padding:10px 60px 20px 60px">
		    <form id="ImgForm">
		    <div>
			      <label>图片名称:</label>
	              <input id="uimageName" name="uimageName" type="text" class="easyui-validatebox" data-options="required:true"/>
               </div>
		       <div>
			      <label>图片分类:</label>
	              <select id="uimgClass" onchange="changeClassSelect();"></select>
               </div>
		    </form>
		    <hr style="width:100%;height:1px;"/>
		    <form id="i_sy_uploadImg_datagrid_upload_dialog_form" action='/doroodo/sys/sySbProjectImagesInfos_upload_file?fileid=' enctype="multipart/form-data" method="post" >
    			<input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"><input type="hidden" id="fileClass" name="fileClass"/></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        		<input type="button" value="上传" onClick="submitUploadImgForm();" />
        	</form>
		  </div>
    </div>
</body>  
</html>
