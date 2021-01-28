<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${root}/static/css/index.css"/>
<link rel="stylesheet" type="text/css" href="${root}/static/easyui/easyui-extend/IconsExtension/icon_fm.css">   
	<c:import url="/admin/pages/common/headsource.jsp"/> 
		
<script type="text/javascript" src="${root}/static/plugin/webuploader-0.1.5/webuploader.min.js"></script>
<title>模板管理 </title>
</head>
<body class="easyui-layout" data-options="fit:true">
<c:import url="/admin/pages/common/loading.jsp"/>

	<table id="mtf-table" class="easyui-datagrid" title="模板管理"
		data-options="
			url				: adminActionPath+'/templatefile/findpage',
			rownumbers		: true,
			singleSelect	: true,
			fitColumns		: true, 
			toolbar			: '#mtf-toolbar',
			fit				: true,
			pagination		: true,
			pageSize		: 15,
			pageList        : [10,15,20,25,30],
			showFooter		: true,
			idField			: 'id',
			onDblClickRow   : function(){mtfdataTable.edit();}">

		<thead>
			<tr>
				<th data-options="field:'name',width:100,align:'left',formatter:complexCol">文件名</th>
				<th data-options="field:'dir',width:100,align:'left',formatter:complexCol">模板目录</th>
				<th data-options="field:'genFiledirPattern',width:200,align:'left',formatter:complexCol">生成目录规则</th>
				<th data-options="field:'genFilekeyPattern',width:100,align:'left',formatter:complexCol">文件名规则</th>
				<th data-options="field:'canMerge',width:50,align:'left',formatter:codeCol,codeClass:'yes_or_no'">合并</th>
				<th data-options="field:'orderNum',width:60,formatter:complexCol">排序</th>
				<th data-options="field:'tpfOp',width:60,align:'left',formatter:tpfOp">操作</th>
			</tr>
		</thead>

	</table>
	
	<div id="mtf-toolbar">

        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="mtfdataTable.add()">添加</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="mtfdataTable.edit()">修改</a>  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="mtfdataTable.remove()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-download" plain="true" onclick="downloadAll()">下载全部</a>
        
	</div>
	
	<div id="mtf-data-form-dlg" class="easyui-dialog" style="width: 600px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#mtf-dlg-buttons" modal="true">
		<form id="mtf-data-form" class="data-form" method="post">
			<input name="id" style="display: none" />					
			<table style="margin-left:-20px;">	
				<tr class="tr_padding">
					<td><label>上传文件</label></td>
					<td	colspan="3">
						<input class="easyui-textbox easyui-validatebox" id="name" name="name" style="width: 100%"
						data-options="required:true,buttonText:'选择文件',prompt:'文件名',onClickButton:uploadFile" >
						<input id="uuidName" name="uuidName" style="display: none" />						
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>模板目录</label></td>
					<td	colspan="3">
						<input name="dir" placeholder='以"/"开头，以"/"结束' class="easyui-validatebox" style="width: 375px" data-options="required:true,validType:'maxLength[255]'">
					</td>
				</tr>
				<tr class="tr_padding">
					<td><label>生成目录规则</label></td>
					<td	colspan="3">
						<input name="genFiledirPattern" placeholder='以"/"开头，以"/"结束' class="easyui-validatebox" style="width: 375px" data-options="required:true,validType:'maxLength[255]'">
					</td>
				</tr>
				
				<tr class="tr_padding">
					<td><label>生成文件名</label></td>
					<td	colspan="3">
						<input name="genFilekeyPattern" placeholder='[ENP]或者[LENP]占位' class="easyui-validatebox" style="width: 375px" data-options="required:true,validType:'maxLength[255]'">
					</td>
				</tr>	
				<tr>
					<td><label>合<span class="letter-space-2"></span>并</label></td>
					<td>
						<input name="canMerge" class="easyui-combobox"
						data-options="valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							codeClass:'yes_or_no'">
					</td>
					<td><label>排<span class="letter-space-2"></span>序</label></td>
					<td>
						<input name="orderNum" class="easyui-validatebox" data-options="validType:'positiveNumber'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="mtf-dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="mtfdataTable.save()">保存</a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#mtf-data-form-dlg').dialog('close')">取消</a>
	</div>

<button id="picker" style="display:hone">picker</button>
	<script type="text/javascript">	
		
		var mtfdataTable = new DataTable({
			$datagrid_table :$("#mtf-table"),
			$data_form_dialog : $("#mtf-data-form-dlg"),
			$data_form : $("#mtf-data-form"),
			data_form_name : "模板",
			
			addOpt : {
				url : adminActionPath+"/templatefile/add"
			},
			editOpt : {
				url : adminActionPath+"/templatefile/edit"
			},
			removeOpt : {
				url : adminActionPath+"/templatefile/delete"
			},
			saveOpt : {},
			searchOpt : {
				$searchForm : $("#search-form"),
			}
		});	
		
		
$(function(){
    tpfUploader = WebUploader.create( {
        auto : true,
        // 不压缩image
        resize : false,
        // swf文件路径
        swf : "${root}" + '/static/js/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server : adminActionPath+"/templatefile/upload",
        fileVal : "file", //指明参数名称，后台也用这个参数接收文件 
		pick: '#picker'
      });
    
    tpfUploader.on( 'fileQueued', function( file ) {
		$("#name").textbox("setValue",file.name);
    });
    tpfUploader.on( 'uploadSuccess', function( file,json ) {
	    $( '#uuidName' ).val(json.data);	
        if(json.type == "success"){
        	$.messager.show({
        		title : "提示",
        		msg : "上传成功！"
        	});
        }else{
        	$.messager.show({
        		title : "提示",
        		msg : "上传失败！"
        	});
        }
    });
});
	    
		function uploadFile(){
		    $('#picker').find('input').click();
		}
		
		function loadMtpfs(index, row){
			var url = adminActionPath + '/templatefile/findpage';
			$("#mtf-table").datagrid({
				url		 	: url,
				queryParams	:{templateId : row.id}	
			})
		}
		
		function tpfOp(value, row, index){
			return '<a href="javascript:void(0);" onclick="tpfDownload('+row.id+')">下载</a>';
		}
		
		function tpfDownload(tpfId){
			var url = adminActionPath+"/templatefile/tpfDownload";
			postParams(url,{tpfId:tpfId});
		}
		
		function downloadAll(){
			postParams(adminActionPath+"/templatefile/downloadAllByZip",{});
		}
		
	</script>

</body>
</html>