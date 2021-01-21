<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	
	String templateId = request.getParameter("templateId");
	String templateName = request.getParameter("templateName");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	//alert("<%=templateId%>");
	var uploader;//上传对象
	var isIndicatorsCombox;
	
	var submitNow = function($dialog, $grid, $pjq) {
		var url;
		if ($(':input[name="data.attrId"]').val().length > 0) {
			url = sy.contextPath + '/app/report-attr!update.sy';
		} else {
			url = sy.contextPath + '/app/report-attr!save.sy';
		}
		$.post(url, sy.serializeObject($('form')), function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.treegrid('reload');
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
		isIndicatorsCombox = $('#isIndicatorsCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_VALUE_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		
		if ($(':input[name="data.attrId"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/report-attr!getById.sy', {
				id : $(':input[name="data.attrId"]').val()
			}, function(result) {
				if (result.attrId != undefined) {
					$('form').form('load', {
						'data.attrId' : result.attrId,
						'data.attrName' : result.attrName,
						'data.medicalReportDef.templateId' : result.medicalReportDef.templateId,
						'data.medicalReportDef.templateName' : result.medicalReportDef.templateName,
						'data.attrCankao' : result.attrCankao,
						'data.attrKeshi' : result.attrKeshi,
						'data.attrCheck' : result.attrCheck,
						'data.attrUnit' : result.attrUnit,
						'data.unitData' : result.unitData,
						'data.isIndicators' : result.isIndicators,
						'data.attrDesc' : result.attrDesc,
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

	});
</script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>属性数据</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>属性编号</th>
					<td><input name="data.attrId" value="<%=id%>"  readonly="readonly" /></td>
					<th>体检报告</th>
					<td><input name="data.medicalReportDef.templateId" value="<%=templateId%>"  style="width: 10px;display: none" readonly="readonly" />
					     <input name="data.medicalReportDef.templateName" value="<%=templateName%>"  readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>上级属性</th>
			 				<td><select id="data.medicalReportAttr.attrId" name="data.medicalReportAttr.attrId" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/app/report-attr!doNotNeedSecurity_getMainMenu.sy'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.medicalReportAttr.attrId').combotree('clear');" title="清空" /></td>
				  <tr>
					<th></th>
					<td></td>
				</tr>
				
					<th>属性名称</th>
					<td><input name="data.attrName" class="easyui-validatebox"  size="20"  maxlength="20" width="300px" data-options="required:true" /></td>
					<th>属性参考值</th>
					<td><input name="data.attrCankao" class="easyui-validatebox"  maxlength="80"  data-options="required:false" /></td>
				</tr>
				<tr>
					<th>所属科室</th>
					<td>
					<select id="data.attrKeshi" name="data.attrKeshi" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_KESHI'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrKeshi').combotree('clear');" title="清空" />
					</td>
					<th>属性检查类型</th>
					<td>
						<select id="data.attrCheck" name="data.attrCheck" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_CHECK'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrCheck').combotree('clear');" title="清空" />
					</td>
				</tr>
				<tr>
					<th>属性单位</th>
					<td>
						<select id="data.attrUnit" name="data.attrUnit" class="easyui-combotree" data-options="required:true,editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.attrUnit').combotree('clear');" title="清空" />
					</td>
					<th><!-- 属性数值 --></th>
					<td>
						<!-- <input name="data.unitData" class="easyui-validatebox"  maxlength="80"  data-options="required:false" /> -->
					</td>
				</tr>
				<tr>
					<th>数值类型</th>
					<td>
						<select id="isIndicatorsCombox" name="data.isIndicators" class="easyui-combotree"  data-options="required:true" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" />
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>属性描述</th>
					<td colspan="3"><textarea name="data.attrDesc" cols="100"  style="width: 300px"></textarea></td>
				</tr> 
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="data.ext1" cols="100" style="width: 300px"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>