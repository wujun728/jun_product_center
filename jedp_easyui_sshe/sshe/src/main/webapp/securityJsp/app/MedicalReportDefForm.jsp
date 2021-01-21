<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
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
	var medicalTypeCombox;
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.templateId"]').val().length > 0) {
			url = sy.contextPath + '/app/report-def!update.sy';
		} else {
			url = sy.contextPath + '/app/report-def!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	};
	var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						submitNow($dialog, $grid, $pjq);
					}
				});
			} else {
				submitNow($dialog, $grid, $pjq);
			}
		}
	};
	
	var changeStatus = function(){
		//$("#data.status").prop('disabled', 'disabled');
		//$("#data.status").attr('disabled', false);
		//$('#status').attr('disabled', 'disabled');
		$('#data.status').combobox("disabled");
	}
	
	var getMedicalTypeOptionById = function(id) {
		var data = $("#medicalTypeCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	
	$(function() {
		medicalTypeCombox= $('#medicalTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		
		if ($(':input[name="data.templateId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/report-def!getById.sy', {
				id : $(':input[name="data.templateId"]').val()
			}, function(result) {
				if (result.templateId != undefined) {
					$('form').form('load', {
						'data.templateId' : result.templateId,
						'data.customerInfo.customerId' : result.customerInfo ? result.customerInfo.customerId : '',
						'data.templateName' : result.templateName,
						'data.templateContent' : result.templateContent,
						'data.reportType' : result.reportType,
						'data.medicalReportType' : result.medicalReportType,
						'data.belongToHispital' : result.belongToHispital,
						'data.status' : result.status,
						'data.ext1' : result.ext1
					});
					 
				}
				parent.$.messager.progress('close');
			}, 'json');
		}

		uploader = new plupload.Uploader({//上传插件定义
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : sy.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			url : sy.contextPath + '/plupload?fileFolder=/userPhoto',//上传文件路径
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
				title : '图片文件',
				extensions : 'jpg,gif,png'
			} ]
		});
		uploader.bind('Init', function(up, params) {//初始化时
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			if (uploader.files.length > 1) {
				parent.$.messager.alert('提示', '只允许选择一张照片！', 'error');
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
				msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
			} else {
				msg = file.percent;
			}
			$('#' + file.id + '>strong').html(msg + '%');

			parent.sy.progressBar({//显示文件上传滚动条
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
				//console.info(file.name);
				//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
				//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
				$(':input[name="data.customerLogo"]').val(response.fileUrl);
			}
		});
		uploader.init();

		
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
		 
		
	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>体检模板信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>模板编号</th>
					<td><input name="data.templateId" value="<%=id%>"   readonly="readonly" /></td>
					<th>客户编号</th>
					<td><input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;"></td>
				</tr>
				<tr>
					<th>模板名称</th>
					<td><input name="data.templateName" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
					<th>模板描述</th>
					<td><input name="data.templateContent" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>报告类型</th>
					<td><select class="easyui-combobox" name="data.reportType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">体检报告</option>
							<option value="2">报告结果</option>
						</select>
					</td>
					<th>体检报告分类</th>
					<td>
						<select id="medicalTypeCombox" name="data.medicalReportType" class="easyui-combotree"  style="width: 155px;"></select>
					</td>
				</tr>
				<tr>
					<th>所属医院</th>
					<td><input name="data.belongToHispital" class="easyui-validatebox" data-options="required:true" /></td>
					<th>状态</th>
					<td><select id="data.status" class="easyui-combobox"   name="data.status" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="0" selected="selected">正常状态</option>
						    
					</select></td>
				</tr>
				
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
	
	<script type="text/javascript">
	
	$(function() {
		changeStatus();
	});
	</script>
</body>
</html>