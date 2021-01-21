<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
 /**
    	学校信息管理
 */
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
		if ($(':input[name="data.customerId"]').val().length > 0) {
			url = sy.contextPath + '/app/cust-info!update.sy';
		} else {
			url = sy.contextPath + '/app/cust-info!save.sy';
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

		if ($(':input[name="data.customerId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/cust-info!getById.sy', {
				id : $(':input[name="data.customerId"]').val()
			}, function(result) {
				if (result.customerId != undefined) {
					$('form').form('load', {
						'data.customerId' : result.customerId,
						'data.customerName' : result.customerName,
						'data.customerEnName' : result.customerEnName,
						'data.certificateType' : result.certificateType,
						'data.isPerOrComp' : result.isPerOrComp,
						'data.customerLogo' : result.customerLogo,
						'data.customerAddr' : result.customerAddr,
						'data.customerContact' : result.customerContact,
						'data.postCode' : result.postCode,
						'data.enterpriseScale' : result.enterpriseScale,
						'data.customerWebsite' : result.customerWebsite,
						'data.legalPerson' : result.legalPerson,
						'data.customerStatus' : result.customerStatus,
						'data.customerType' : result.customerType,
						'data.ext1' : result.ext1
					});
					if (result.customerLogo) {
						$('#customerLogo').attr('src', sy.contextPath + result.customerLogo);
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
				msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制学校看到99%，等后台合并完成...
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

	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>学校基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>学校编号</th>
					<td><input name="data.customerId" value="<%=id%>"   readonly="readonly" /></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>学校名称</th>
					<td colspan="3"><input name="data.customerName" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>学校英文名称</th>
					<td colspan="3"><input name="data.customerEnName" class="easyui-validatebox"  maxlength="80"  data-options="required:false" /></td>
				</tr>
				<tr>
					<th>证件类型</th>
					<td><select class="easyui-combobox" name="data.certificateType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">营业执照</option>
							<option value="2">企业组织机构代码证</option>
					</select></td>
					<th>企业还是学校</th>
					<td><select class="easyui-combobox" name="data.isPerOrComp" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<!-- <option value="0">企业</option> -->
							<option value="1" selected="selected">学校</option>
					</select></td>
				</tr>
				<tr>
					<th>logo</th>
					<td colspan="3"><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'">选择文件</a>
							<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>预览图</th>
					<td colspan="3"><input name="data.customerLogo" readonly="readonly" style="display: none;" /> <img id="customerLogo" src="" style="width: 200px; height: 200px;"></td>
				</tr>
				<tr>
					<th>学校地址</th>
					<td><input name="data.customerAddr" class="easyui-validatebox" data-options="required:true" /></td>
					<th>学校联系方式</th>
					<td><input name="data.customerContact" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>邮编</th>
					<td><input name="data.postCode" class="easyui-validatebox" maxlength="6" data-options="required:true" /></td>
					<th>企业规模</th>
					<td>
					<select class="easyui-combobox" name="data.enterpriseScale" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=1>   10 人以下</option>
							<option value=2>   50 人以下</option>
							<option value=3>   100 以下</option>
							<option value=4>   500 人以下</option>
							<option value=5>   1000 人以下</option>
							<option value=6>   5000 人以下</option>
							<option value=7>   10000 人以下</option>
							<option value=8>   50000 人以下</option>
							<option value=9>   100000 人以下</option>
           					<option value=10>   更多 ... </option>
					</select>
					 </td>
				</tr>
				<tr>
					<th>企业官网</th>
					<td><input name="data.customerWebsite" class="easyui-validatebox" data-options="required:false" /></td>
					<th>企业法人</th>
					<td><input name="data.legalPerson" class="easyui-validatebox" data-options="required:false" /></td>
				</tr>
				<tr>
					<th>学校状态</th>
					<td><select class="easyui-combobox" name="data.customerStatus" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value=0>新建</option>
							<option value=1>合作有效</option>
							<option value=2>合作无效</option>
					</select></td>
					<th><!-- 学校类型 --></th>
					<td>
					<select class="customerType" hidden="true" name="data.customerType" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<!-- <option value="0">企业</option> -->
							<option value="1" selected="selected" >学校</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>