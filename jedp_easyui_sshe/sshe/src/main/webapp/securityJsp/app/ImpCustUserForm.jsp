<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	
%>
<% 
    /**
                导入员工信息  
    */
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">	
	var uploader;//上传对象
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url = sy.contextPath + '/app/cust-user!importUserData.sy';
		console.log("----------tt----------");
		console.log(sy.serializeObject($('form')))
		  $.post(url, sy.serializeObject($('form')), function(result) {
			  console.log("----------f----------");
			sy.progressBar('close');//关闭上传进度条
			console.log('result' +  result);
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				//$grid.datagrid('load');
				//$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json'); 
	};
	
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			//console.log(uploader.files);
			if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					console.log("--------------------");
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						console.log("----------d----------");
						submitNow($dialog, $grid, $pjq);
						console.log("----------e----------");
					}
				});
			} else {
				submitNow($dialog, $grid, $pjq);
			}
		}
	};
	
	$(function() {
		$('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'customerId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'customerId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'customerName',
				title : '客户',
				width : 100,
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'updateTime',
				title : '修改时间',
				width : 150,
				sortable : true
			} ] ]
		});
		
		uploader = new plupload.Uploader({//上传插件定义
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : sy.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			url : sy.contextPath + '/plupload?fileFolder=/custUserExcel',//上传文件路径
			max_file_size : '5mb',//100b, 10kb, 10mb, 1gb
			chunk_size : '10mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			/*resize : {
				width : 320,
				height : 240,
				quality : 90
			},*/
			// 指定要浏览的文件类型
			filters : [ {
				title : 'Excel文件',
				extensions : 'xlsx,png'
			} ]
		});
		uploader.bind('Init', function(up, params) {//初始化时
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			if (uploader.files.length > 1) {
				$.messager.alert('提示', '只允许选择一个数据文件！', 'error');
				uploader.stop();
				return;
			}
			$('.ext-icon-cross').hide();
		});
		uploader.bind('FilesAdded', function(up, files) {//选择文件后
			$.each(files, function(i, file) {
				$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
			});
			up.refresh();
		});
		uploader.bind('UploadProgress', function(up, file) {//上传进度改变
			var msg;
			if (file.percent == 100) {
				msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制员工看到99%，等后台合并完成...
			} else {
				msg = file.percent;
			}
			$('#' + file.id + '>strong').html(msg + '%');
			
			sy.progressBar({//显示文件上传滚动条
				title : '文件上传中...',
				value : msg
			});
		});
		uploader.bind('Error', function(up, err) {//出现错误
			$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
			up.refresh();
		});
		uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
			var response = $.parseJSON(info.response);
			if (response.status) {
				$('#' + file.id + '>strong').html("100%");
				//console.info(response.fileUrl);
				console.info(file.name);
				//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
				//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
				$(':input[name="data.icon"]').val(response.fileUrl);
				$(':input[name="data.ext1"]').val(file.name);
			}
		});
		uploader.init();
	});
	
	var importFun = function() {
		submitForm(null, null, parent.$);
	};
	
	var listMyRecord_CC = function() {
		var url = sy.contextPath + '/securityJsp/app/ImpCustUserList.jsp';
		window.location.href=url;
	};
	
	var showExcelFormat = function() {
		var url = sy.contextPath + '/securityJsp/app/ShowImpCustUser.jsp';
		window.location.href=url;
	};
</script>
</head>
<body style="height: 300px">
	<form method="post" class="form">
		<div class="easyui-tabs" style="width: 600px; height: 400px">
			<div title="基本信息" style="padding: 10px">
				<fieldset>
					<table class="table" style="width: 100%;">
						<tr>
							<th>客户</th>
							<td><input id="customerIdCombogrid"
								name="data.customerInfo.customerId" type="text" value=""
								style="width: 214px;"></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>导入的员工数据表格</th>
							<td colspan="3"><div id="container">
									<a id="pickfiles" href="javascript:void(0);"
										class="easyui-linkbutton"
										data-options="iconCls:'ext-icon-zoom'">选择文件</a>
									<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
								</div></td>
						</tr>
						<tr>
							<th>源文件</th>
							<td colspan="3"><input name="data.ext1" readonly="readonly" /> 
								<img id="icon" src="" style="width: 200px; height: 200px;">
							</td>
						</tr>
						<tr>
							<th>服务器文件</th>
							<td colspan="3"><input name="data.icon" readonly="readonly"
								style="display: none;" /> <img id="icon" src=""
								style="width: 200px; height: 200px;"></td>
						</tr>
					</table>
					
					<div style="text-align: center; padding: 5px">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							data-options="iconCls:'ext-icon-note_add',plain:true"
							onclick="listMyRecord_CC();">查询历史导入</a>
							
							<a href="javascript:void(0);" class="easyui-linkbutton"
							data-options="iconCls:'ext-icon-note_add',plain:true"
							onclick="showExcelFormat();">查看导入模板格式</a>
							
						<a href="javascript:void(0)" class="easyui-linkbutton"
						   onclick="importFun()">数据导入</a>
					</div>
			</div>
			
		</div>
	</form>
</body>
</html>