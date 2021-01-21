<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>plupload示例</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-2.0.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/i18n/zh-CN.js"></script>
<script type="text/javascript">
	var uploader;
	$(function() {
		uploader = new plupload.Uploader({//配置参数
			browse_button : 'pickfiles',//选择文件的按钮
			container : 'container',//文件上传容器
			runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
			flash_swf_url : '${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
			silverlight_xap_url : '${pageContext.request.contextPath}/jslib/plupload_1_5_7/plupload/js/plupload.silverlight.xap',//silverlight环境路径设置
			url : '${pageContext.request.contextPath}/plupload',//上传文件路径
			max_file_size : '3gb',//100b, 10kb, 10mb, 1gb
			chunk_size : '1mb',//分块大小，小于这个大小的不分块
			unique_names : true,//生成唯一文件名
			// 如果可能的话，压缩图片大小
			// resize : { width : 320, height : 240, quality : 90 },
			// 指定要浏览的文件类型
			filters : [ {
				title : '图片',
				extensions : 'jpg,gif,png'
			}, {
				title : '文件',
				extensions : 'zip,7z'
			} ]
		});
		uploader.bind('Init', function(up, params) {//初始化的时候
			//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
			$('#filelist').html("");
		});
		uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
			$('.plupload_delete').hide();
		});
		uploader.bind('FilesAdded', function(up, files) {//选择文件后
			$.each(files, function(i, file) {
				$('#filelist').append('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b>' + '&nbsp;<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor: pointer;" class="plupload_delete">删除</span></div>');
			});
			up.refresh();
		});
		uploader.bind('UploadProgress', function(up, file) {//上传进度
			$('#' + file.id + " b").html(file.percent + "%");
		});
		uploader.bind('Error', function(up, err) {//出现错误
			$('#filelist').append("<div>Error: " + err.code + ", Message: " + err.message + (err.file ? ", File: " + err.file.name : "") + "</div>");
			up.refresh();
		});
		uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
			$('#' + file.id + " b").html("100%(后台正在合并文件，请稍后....)");

			var response = $.parseJSON(info.response);
			if (response.status) {
				$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
				$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
			}
		});
		uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
			if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
				//$('#f1')[0].submit();
			}
		});
		uploader.init();//初始化uploader
		$('#uploadfiles').click(function(e) {
			uploader.start();
			e.preventDefault();
		});

		// 客户端表单验证
		$('#f1').submit(function(e) {
			if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
				uploader.start();
			} else {
				alert('请选择至少一个文件进行上传！');
			}
			return false;
		});
	});
</script>

</head>
<body>
	<form id="f1" action="${pageContext.request.contextPath}/DemoAction" method="post">
		<table border="1">
			<tr>
				<td colspan="2">
					<div id="container">
						<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
						<button id="pickfiles">选择文件</button>
						<button id="uploadfiles">上传文件</button>
					</div>
				</td>
			</tr>
			<tr>
				<th>姓名</th>
				<td><input name="name" value="孙宇" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" value="123456" /></td>
			</tr>
		</table>
		<button type="submit">提交表单</button>
	</form>
</body>
</html>