/**
 * 公共调用js
 */
$(function() {
	
	loading = function(){
		var d = dialog({
			width: 40,
			height: 40
		});

		d.showModal();
	};
	
	//select2 bootstrap样式
	$(".select2").select2({
		theme: "bootstrap",
		language: "zh-CN"
	});
	
	// 分页
	page = function(n, s) {
		$("#pageNo").val(n);
		$("#searchForm").submit();
	};

	// 全选/反选(如果有了iCheck该方法就会失效)
	checkAll = function(chkAll, name) {
		$("input[name='" + name + "']").prop("checked", chkAll.checked);

	};
	
	// 获取选中checkbox的值
	checkValue = function(name) {
		var str = "";
		$('input[type="checkbox"][name=' + name + ']').each(function() {
			if ($(this).prop('checked')) {
				str += $(this).val() + ",";
			}
		});
		if (str != "")
			str = str.substring(0, str.length - 1);
		return str;
	};

	// 编辑
	update = function(url) {
		var ids = checkValue("checkbox");
		if (ids == "") {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择要编辑的记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
		if (ids.split(",").length > 1) {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择一笔记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
//		window.location = url + "?id=" + ids;
		window.location = url + "/" + ids;
	};

	// 删除全部
	deleteAll = function(message, url) {
		var ids = checkValue("checkbox");
		if (ids == "") {
			var d = dialog({
				title : '提示',
				width : 200,
				content : '请选择要删除的记录',
				okValue : '确定',
				ok : function() {
					this.close();
					return false;
				}
			});
			d.showModal();
			return false;
		}
		confirmx(message, url + "/" + ids);
	};

	// 确认删除
	confirmx = function(message, url) {
		var d = dialog({
			title : '提示',
			width : 300,
			content : message,
			okValue : '确定',
			ok : function() {
				this.title('提交中…');
				window.location = url;
				return false;
			},
			cancelValue : '取消',
			cancel : function() {
			}
		});
		d.showModal();
		/*
		 * $.dialog.confirm(message, function(){
		 * $.dialog.tips(message,1,'loading.gif'); location = url; });
		 */
		return false;
	};

});
