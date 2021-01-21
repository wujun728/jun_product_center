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
	var submitNow = function($dialog, $grid, $pjq) {
		 
		var url;
		if ($(':input[name="data.userId"]').val().length > 0) {
			url = sy.contextPath + '/app/cust-user!update.sy';
		} else {
			url = sy.contextPath + '/app/cust-user!save.sy';
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
	$(function() {
		
	 
		
	 
		$('#deptIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId=-1',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'deptId',
			textField : 'deptName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'deptId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'deptId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'deptName',
				title : '学校',
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
				title : '学校',
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
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				//alert(record);
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId);
		        $('#deptIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId='+r.customerId,
		            valueField:'deptId',  
		            textField:'deptName'  
		        }).combogrid('clear');
			}
		});
		if ($(':input[name="data.userId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			//alert('ddd  ' + $(':input[name="data.userId"]').val())
			$.post(sy.contextPath + '/app/cust-user!getById.sy', {
				id : $(':input[name="data.userId"]').val()
			}, function(result) {
				//alert(result.custDept.deptId)
				if (result.userId != undefined) {
					$('form').form('load', {
						'data.userId' : result.userId,
						'data.customerInfo.customerId':result.customerInfo.customerId,
						'data.custDept.deptId':result.custDept.deptId,
						'data.userCode' : result.userCode,
						'data.userName' : result.userName,
						'data.firstName' : result.firstName,
						'data.lastName' : result.lastName,
						'data.phone' :   result.phone,
						'data.sex' :     result.sex,
						'data.employeTime' : result.employeTime,
						'data.outdutyTime' : result.outdutyTime,
						'data.email' :       result.email,
						'data.birthdate' :   result.birthdate,
						'data.icon' :        result.icon,
						'data.onJob' :       result.onJob,
						'data.positionType' : result.positionType,
						'data.positionZh' : result.positionZh,
						'data.positionEn' : result.positionEn,
						'data.certificateType' : result.certificateType,
						'data.certificate' : result.certificate,
						'data.costCenter' : result.costCenter,
						'data.labor' : result.labor,
						'data.groupDate' : result.groupDate,
						'data.entryDate' : result.entryDate,
						'data.marriage' : result.marriage,
						'data.createTime' : result.createTime,
						'data.updateTime' : result.updateTime,
						'data.status' : result.status,
						'data.nationality' : result.nationality,
						'data.jrContact' : result.jrContact,
						'data.jhrContact' : result.jhrContact,
						'data.jzContact1' : result.jzContact1,
						'data.isEmpHurt' : result.isEmpHurt,
						'data.ext1' : result.ext1
					});
					if (result.icon) {
						$('#icon').attr('src', sy.contextPath + result.icon);
					}
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
				msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制学生看到99%，等后台合并完成...
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
				$(':input[name="data.icon"]').val(response.fileUrl);
			}
		});
		uploader.init();
	});
</script>
</head>
<body style="height: 300px">

	<form method="post" class="form" >
 <div class="easyui-tabs" style="width:600px;height:400px">
<div title="基本信息" style="padding:10px">
	 <fieldset>
			<table class="table" style="width: 100%;">
				<tr>
					<th>学生编号</th>
					<td><input name="data.userId" value="<%=id%>"   readonly="readonly" /></td>
					<th>学生编码</th>
					<td><input name="data.userCode"  class="easyui-validatebox" data-options="required:false" /></td>
				</tr>
				<tr>
					<th>学生姓名</th>
					<td><input name="data.userName"  class="easyui-validatebox" data-options="required:true" /></td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>学校</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					</td>
						<th></th>
					<td></td>
				</tr>				
				<tr>
					<th>年级班级</th>
					<td>
						<input id="deptIdCombogrid" name="data.custDept.deptId" type="text" value="" style="width: 214px;">
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>First Name</th>
					<td><input name="data.firstName" /></td>
					<th>Last Name</th>
					<td><input name="data.lastName" /></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td><input name="data.phone" /></td>
					<th>性别</th>
					<td><select class="easyui-combobox" name="data.sex" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">男</option>
										<option value="2">女</option>
					</select></td>
				</tr>
				<tr>
					<th>入职时间</th>
					<td>
						<input name="data.employeTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>毕业时间</th>
					<td>
						<input name="data.outdutyTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				
				<tr>
					<th>Email</th>
					<td><input name="data.email" /></td>
					<th>出生日期</th>
					<td>
					<input name="data.birthdate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
					<tr>
					<th>图像</th>
					<td colspan="3"><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>预览图</th>
					<td colspan="3"><input name="data.icon" readonly="readonly" style="display: none;" /> <img id="icon" src="" style="width: 200px; height: 200px;"></td>
				</tr>
				
				<%-- <tr>
					<th>在校情况</th>
					<td>
					<select class="easyui-combobox" name="data.onJob" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="0">在校</option>
								<option value="1">毕业</option>
						</select>
					</td>
					<th>岗位类别</th>
					<td>
						<select class="easyui-combobox" name="data.positionType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="1">正式工</option>
								<option value="2">临时工</option>
								<option value="3">自定义</option>
						</select>
					</td>
				</tr>
				 <tr>
					<th>岗位中文</th>
					<td><input name="data.positionZh" class="easyui-validatebox" data-options="required:true" /></td>
					<th>岗位英文</th>
					<td><input name="data.positionEn" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>证件类型</th>
					<td>
					<select id="data.certificateType" name="data.certificateType" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=USER_CERTIFICAT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" />
					</td>
					<th>证件号码</th>
					<td><input name="data.certificate" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				 <tr>
					<th>成本中心</th>
					<td><input name="data.costCenter" class="easyui-validatebox" data-options="required:true" /></td>
					<th>劳动关系</th>
					<td><input name="data.labor" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>入集团日期</th>
					<td>
					<input name="data.groupDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>入司日期</th>
					<td>
					<input name="data.entryDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				<tr>
					<th>婚姻状况</th>
					<td>
					<select class="easyui-combobox" name="data.marriage" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>未婚</option>
							<option value=2>已婚</option>
							<option value=3>离异</option>
					</select>
						</td>
					<th>创建时间</th>
					<td><input name="data.createTime" class="easyui-validatebox"    readonly="readonly"/></td>
				</tr>
				<tr>
					<th>修改时间</th>
					<td><input name="data.updateTime" class="easyui-validatebox"   readonly="readonly" /></td>
					<th>状态</th>
					<td>
					<select class="easyui-combobox" name="data.status" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>   在校</option>
							<option value=2>   毕业</option>
							<option value=3>   待业</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>国籍</th>
					<td><input name="data.nationality" class="easyui-validatebox" data-options="required:true" /></td>
					<th>家人联系方式</th>
					<td>
					<input name="data.jrContact" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>监护人联系方式</th>
					<td><input name="data.jhrContact" class="easyui-validatebox" data-options="required:true" /></td>
					<th>家长联系方式1</th>
					<td>
					<input name="data.jzContact1" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
			    <tr>
					<th>是否接触职业危害因素</th>
					<td>
					<select class="easyui-combobox" name="data.isEmpHurt" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=0>不接触</option>
							<option value=1>基础职业危害</option>
					</select>
					</td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr> --%>
			</table>
</div>
<div title="职业信息" style="padding:10px">
			<table class="table" style="width: 100%;">
				<%-- <tr>
					<th>学生编号</th>
					<td><input name="data.userId" value="<%=id%>"   readonly="readonly" /></td>
					<th>学生编码</th>
					<td><input name="data.userCode" /></td>
				</tr>
				<tr>
					<th>学生姓名</th>
					<td><input name="data.userName"   /></td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>年级班级</th>
					<td>
						<input id="deptIdCombogrid" name="data.custDept.deptId" type="text" value="" style="width: 214px;">
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
				
					<th>学校</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					</td>
						<th></th>
					<td></td>
				</tr>
				
				<tr>
					<th>First Name</th>
					<td><input name="data.firstName" /></td>
					<th>Last Name</th>
					<td><input name="data.lastName" /></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td><input name="data.phone" /></td>
					<th>性别</th>
					<td><select class="easyui-combobox" name="data.sex" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">男</option>
										<option value="0">女</option>
					</select></td>
				</tr>
				<tr>
					<th>入职时间</th>
					<td>
						<input name="data.employeTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>毕业时间</th>
					<td>
						<input name="data.outdutyTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				
				<tr>
					<th>Email</th>
					<td><input name="data.email" /></td>
					<th>出生日期</th>
					<td>
					<input name="data.birthdate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
					<tr>
					<th>图像</th>
					<td colspan="3"><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>预览图</th>
					<td colspan="3"><input name="data.icon" readonly="readonly" style="display: none;" /> <img id="icon" src="" style="width: 200px; height: 200px;"></td>
				</tr> --%>
					
				<tr>
					<th>在校情况</th>
					<td>
					<select class="easyui-combobox" name="data.onJob" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="0">在校</option>
								<option value="1">毕业</option>
						</select>
					</td>
					<th>岗位类别</th>
					<td>
						<select class="easyui-combobox" name="data.positionType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="1">正式工</option>
								<option value="2">临时工</option>
								<option value="3">自定义</option>
						</select>
					</td>
				</tr>
				 <tr>
					<th>岗位中文</th>
					<td><input name="data.positionZh" class="easyui-validatebox" data-options="required:false" /></td>
					<th>岗位英文</th>
					<td><input name="data.positionEn" class="easyui-validatebox" data-options="required:false" /></td>
				</tr>
				<tr>
					<th>证件类型</th>
					<td>
					<select id="data_certificateType" name="data.certificateType" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=USER_CERTIFICAT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data_certificateType').combotree('clear');" title="清空" />
					</td>
					<th>证件号码</th>
					<td><input name="data.certificate" class="easyui-validatebox" data-options="required:false" /></td>
				</tr>
				 <tr>
					<th>成本中心</th>
					<td><input name="data.costCenter" class="easyui-validatebox" data-options="required:false" /></td>
					<th>劳动关系</th>
					<td><input name="data.labor" class="easyui-validatebox" data-options="required:false" /></td>
				</tr>
				<tr>
					<th>入集团日期</th>
					<td>
					<input name="data.groupDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>入司日期</th>
					<td>
					<input name="data.entryDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				<tr>
					<th>婚姻状况</th>
					<td>
					<select class="easyui-combobox" name="data.marriage" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>未婚</option>
							<option value=2>已婚</option>
							<option value=3>离异</option>
					</select>
						</td>
					<th>创建时间</th>
					<td><input name="data.createTime" class="easyui-validatebox"    readonly="readonly"/></td>
				</tr>
				<!-- <tr>
					<th>修改时间</th>
					<td><input name="data.updateTime" class="easyui-validatebox"   readonly="readonly" /></td>
					<th>状态</th>
					<td>
					<select class="easyui-combobox" name="data.status" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>   在校</option>
							<option value=2>   毕业</option>
							<option value=3>   待业</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>国籍</th>
					<td><input name="data.nationality" class="easyui-validatebox" data-options="required:true" /></td>
					<th>家人联系方式</th>
					<td>
					<input name="data.jrContact" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>监护人联系方式</th>
					<td><input name="data.jhrContact" class="easyui-validatebox" data-options="required:true" /></td>
					<th>家长联系方式1</th>
					<td>
					<input name="data.jzContact1" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
			    <tr>
					<th>是否接触职业危害因素</th>
					<td>
					<select class="easyui-combobox" name="data.isEmpHurt" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=0>不接触</option>
							<option value=1>基础职业危害</option>
					</select>
					</td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr> -->
			</table>
		 
</div>
 <div title="联系信息" style="padding:10px">
 <table class="table" style="width: 100%;">
				<%-- <tr>
					<th>学生编号</th>
					<td><input name="data.userId" value="<%=id%>"   readonly="readonly" /></td>
					<th>学生编码</th>
					<td><input name="data.userCode" /></td>
				</tr>
				<tr>
					<th>学生姓名</th>
					<td><input name="data.userName"   /></td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>年级班级</th>
					<td>
						<input id="deptIdCombogrid" name="data.custDept.deptId" type="text" value="" style="width: 214px;">
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
				
					<th>学校</th>
					<td>
						<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 214px;">
					</td>
						<th></th>
					<td></td>
				</tr>
				
				<tr>
					<th>First Name</th>
					<td><input name="data.firstName" /></td>
					<th>Last Name</th>
					<td><input name="data.lastName" /></td>
				</tr>
				<tr>
					<th>手机号码</th>
					<td><input name="data.phone" /></td>
					<th>性别</th>
					<td><select class="easyui-combobox" name="data.sex" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">男</option>
										<option value="0">女</option>
					</select></td>
				</tr>
				<tr>
					<th>入职时间</th>
					<td>
						<input name="data.employeTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>毕业时间</th>
					<td>
						<input name="data.outdutyTime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				
				<tr>
					<th>Email</th>
					<td><input name="data.email" /></td>
					<th>出生日期</th>
					<td>
					<input name="data.birthdate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
					<tr>
					<th>图像</th>
					<td colspan="3"><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>预览图</th>
					<td colspan="3"><input name="data.icon" readonly="readonly" style="display: none;" /> <img id="icon" src="" style="width: 200px; height: 200px;"></td>
				</tr> --%>
				
				<%-- <tr>
					<th>在校情况</th>
					<td>
					<select class="easyui-combobox" name="data.onJob" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="0">在校</option>
								<option value="1">毕业</option>
						</select>
					</td>
					<th>岗位类别</th>
					<td>
						<select class="easyui-combobox" name="data.positionType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
								<option value="1">正式工</option>
								<option value="2">临时工</option>
								<option value="3">自定义</option>
						</select>
					</td>
				</tr>
				 <tr>
					<th>岗位中文</th>
					<td><input name="data.positionZh" class="easyui-validatebox" data-options="required:true" /></td>
					<th>岗位英文</th>
					<td><input name="data.positionEn" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>证件类型</th>
					<td>
					<select id="data.certificateType" name="data.certificateType" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=USER_CERTIFICAT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" />
					</td>
					<th>证件号码</th>
					<td><input name="data.certificate" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				 <tr>
					<th>成本中心</th>
					<td><input name="data.costCenter" class="easyui-validatebox" data-options="required:true" /></td>
					<th>劳动关系</th>
					<td><input name="data.labor" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>入集团日期</th>
					<td>
					<input name="data.groupDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
					<th>入司日期</th>
					<td>
					<input name="data.entryDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />
					</td>
				</tr>
				<tr>
					<th>婚姻状况</th>
					<td>
					<select class="easyui-combobox" name="data.marriage" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>未婚</option>
							<option value=2>已婚</option>
							<option value=3>离异</option>
					</select>
						</td>
					<th>创建时间</th>
					<td><input name="data.createTime" class="easyui-validatebox"    readonly="readonly"/></td>
				</tr> --%>
				<tr>
					<th>修改时间</th>
					<td><input name="data.updateTime" class="easyui-validatebox"   readonly="readonly" /></td>
					<th>状态</th>
					<td>
					<select class="easyui-combobox" name="data.status" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>在校</option>
							<option value=2>毕业</option>
							<option value=3>待业</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>国籍</th>
					<td><input name="data.nationality" class="easyui-validatebox" data-options="required:false" /></td>
					<th>家人联系方式</th>
					<td>
					<input name="data.jrContact" class="easyui-validatebox" data-options="required:false" />
					</td>
				</tr>
				<tr>
					<th>监护人联系方式</th>
					<td><input name="data.jhrContact" class="easyui-validatebox" data-options="required:false" /></td>
					<th>家人联系方式1</th>
					<td>
					<input name="data.jzContact1" class="easyui-validatebox" data-options="required:false" />
					</td>
				</tr>
			    <tr>
					<th>是否接触职业危害因素</th>
					<td>
					<select class="easyui-combobox" name="data.isEmpHurt" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=0>不接触</option>
							<option value=1>基础职业危害</option>
					</select>
					</td>
					<th></th>
					<td>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr>
			</table>
		 
 
 </div>
</div>




		
	</form>
</body>
</html>