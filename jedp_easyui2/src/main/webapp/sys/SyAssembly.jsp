<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/plug/ueditor135/third-party/codemirror/codemirror.css" />
<script type="text/javascript"
	src="${ctx}/plug/ueditor135/third-party/codemirror/codemirror.js"></script>
<script id="pjs" type="text/javascript" src="${ctx}/js/page/SyAssembly.js"></script>
<script>
	var syAssemblyId = "i_sy_syAssembly_datagrid";
	var syAssemblyDt, syAssemblyUploadDg, syAssemblyUploadFm;
	var columns = [ [
			{
				field : 'id',
				checkbox : true,
				addform : {
					type : 'eType.Input',
					hidden : true
				},
				editform : {
					type : 'eType.Input',
					hidden : true
				},
				title : '编号',
				hidden : false,
				width : '150'
			},
			{
				field : 'name',
				title : '组件名称',
				addform : {
					type : 'eType.ValidateBox',
					dataoptions : "required:true"
				},
				editform : {
					type : 'eType.ValidateBox',
					dataoptions : "required:true"
				},
				width : '150'
			},
			{
				field : 'sysname',
				title : '系统名称（英文）',
				addform : {
					type : 'eType.ValidateBox',
					dataoptions : "required:true"
				},
				editform : {
					type : 'eType.ValidateBox',
					dataoptions : "required:true"
				},
				width : '150'
			},
			{
				field : 'getscript',
				title : '获取值脚本',
				addform : {
					type : 'eType.TextArea'
				},
				editform : {
					type : 'eType.TextArea'
				},
				hidden : true,
				tooltip : '如：obj.combobox("getValue");',
				width : '150'
			},
			{
				field : 'setscript',
				title : '设置值脚本',
				addform : {
					type : 'eType.TextArea'
				},
				editform : {
					type : 'eType.TextArea'
				},
				hidden : true,
				tooltip : '如：obj.datebox("setValue", v);',
				width : '150'
			},
			{
				field : 'classname',
				hidden : true,
				addform : {
					type : 'eType.TextArea'
				},
				editform : {
					type : 'eType.TextArea'
				},
				title : '设置CSS类名脚本',
				tooltip : '如："easyui-combobox";',
				width : '150'
			},
			{
				field : 'html',
				title : 'html构建脚本',
				addform : {
					type : 'eType.TextArea'
				},
				editform : {
					type : 'eType.TextArea'
				},
				hidden : true,
				tooltip : 'eId,colobj.form.classname,colobj.form.style,colobj.form.readonly,colobj.form.attribute,colobj.form.type',
				width : '150'
			}, {
				field : 'propety',
				title : '组件属性',
				addform : {
					type : 'eType.TextArea'
				},
				editform : {
					type : 'eType.TextArea'
				},
				hidden : true,
				tooltip : '',
				width : '150'
			}, {
				field : 'createuserid',
				title : '创建人ID',
				addform : {
					type : 'eType.Input'
				},
				editform : {
					type : 'eType.Input'
				},
				width : '150'
			}, {
				field : 'filename',
				title : '使用说明',
				addform : {
					type : 'eType.ValidateBox'
				},
				editform : {
					type : 'eType.ValidateBox'
				},
				hidden : true,
				width : '150'
			}, {
				field : 'createtime',
				title : '创建时间',
				addform : {
					type : 'eType.DateTimeBox'
				},
				editform : {
					type : 'eType.DateTimeBox'
				},
				width : '150'
			} ] ];
	$(function() {
		$('#i_sy_syAssembly_datagrid_add_dialog').dialog({
			onOpen : function() {
				syAssemblyAddOnOpen();
			}
		});

		$('#i_sy_syAssembly_datagrid_edit_dialog').dialog({
			onOpen : function() {
				syAssemblyEditOnOpen();
			}
		});
		pageView(syAssemblyId, columns);
		syAssemblyonload();
	});

	function updateFun(d) {

	}

	//模式add
	function pageView_add() {
		$('#' + syAssemblyId + '_add_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syAssemblyId + '_add_btn a').off()
				.click(
						function() {
							if (!AddBtnClick())
								return;
							var setData = {};
							for ( var i = 0; i < columns[0].length; i++) {
								var field = columns[0][i].field;
								var title = columns[0][i].title;
								if (!checkFormField('#' + syAssemblyId
										+ '_add_form_' + field)) {
									log('[' + title + ']不能为空，请填写!');
									return;
								}
								setData['syAssembly' + "." + field] = eGet('#'
										+ syAssemblyId + '_add_form_' + field);
							}
							data(getUrl('syAssembly', 'Add'), setData, 'json',
									null);
						});
		$('#' + syAssemblyId + '_add_dialog').dialog('open');
	}

	//模式edit
	function pageView_edit() {
		data_ = {
			id : $.getUrlParam('id')
		};
		$.ajax({
			url : getUrl('syAssembly', 'Get_ById'),
			data : data_,
			dataType : 'json',
			type : "post",
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(r) {
				setForm(r, syAssemblyId);
				if (r) {
					if ('info' in r) {
						log(r.info);
					}
				}
				;
			}
		});
		$('#' + syAssemblyId + '_edit_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syAssemblyId + '_edit_btn a').off().click(
				function() {
					if (!EditBtnClick())
						return;
					var setData = {};
					for ( var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syAssemblyId + '_edit_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syAssembly' + "." + field] = eGet('#'
								+ syAssemblyId + '_edit_form_' + field);
					}
					data(getUrl('syAssembly', 'Update'), setData, 'json',
							null);
				});
		$('#' + syAssemblyId + '_edit_dialog').dialog('open');
	}

	//模式list
	function pageView_list() {
		var syAssemblyDataGrid = {
			id : syAssemblyId,
			url : '${ctx}' + actionUrl('/sys/', 'syAssembly', 'List'),
			dId : 'id',
			columns : columns
		};

		syAssemblyUploadDg = $('#i_sy_syAssembly_datagrid_upload_dialog');
		syAssemblyUploadFm = $('#i_sy_syAssembly_datagrid_upload_dialog_form');
		syAssemblyUploadFm.attr('action', '${ctx}'
				+ actionUrl('/sys/', 'syAssembly', 'Upload'));

		syAssemblyDt = gGrid2(syAssemblyDataGrid);
		var straddfun = "dorow(syAssemblyId,'','${ctx}"
				+ actionUrl('/sys/', 'syAssembly', 'Add') + "',updateFun,'c')";
		gDataGridToolbarBtn(syAssemblyId, 'icon-add', straddfun, "新增");
		var stredit = "dorow(syAssemblyId,'','${ctx}"
				+ actionUrl('/sys/', 'syAssembly', 'Update')
				+ "',updateFun,'u')";
		gDataGridToolbarBtn(syAssemblyId, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(syAssemblyId,'您是否确定要删除选择的数据','${ctx}"
				+ actionUrl('/sys/', 'syAssembly', 'Delete')
				+ "',updateFun,'d')";
		gDataGridToolbarBtn(syAssemblyId, 'icon-remove', strdelfun, "删除");
		var strexcelfun = "dorow(syAssemblyId,'您确定要导出数据','${ctx}"
				+ actionUrl('/sys/', 'syAssembly', 'Excel')
				+ "',updateFun,'e')";
		gDataGridToolbarBtn(syAssemblyId, 'icon-page_white_excel', strexcelfun,
				"导出");
		gDataGridToolbarBtn(syAssemblyId, 'icon-page_white_excel',
				'upLoadFun()', "导入");
	}

	function upLoadFun() {
		syAssemblyUploadDg.dialog('open');
	}

	function submitUploadForm() {
		syAssemblyUploadFm.form('submit', {
			success : function(d) {
				syAssemblyDt.datagrid('reload');
				syAssemblyUploadDg.dialog('close');
				d = $.parseJSON(d);
				log(d.info);
			}
		});
	}

	function getEditFormHtml(title, type) {
		var form = $('#report').clone();
		var word = $('table', form);
		title = title + "详细资料";
		$('td', word).each(function() {
			var gobj = $(this);
			gobj.children().each(function(i, n) {
				var obj = $(n)
				var id = obj.attr('id');
				if (id) {
					gobj.html(eGet('#' + id));
				}
			});
		});
		form.children().each(function(i, n) {
			$('tr', $(n)).each(function(ii, nn) {
				if ($(nn).attr('style') == 'display:none;') {
					$(nn).remove();
				}
			});
		});
		var setData = {
			'tableHtml' : '<div class="titlep">' + title + '</div>'
					+ form.html(),
			'tableTitle' : title
		};
		data(getUrl('syAssembly', 'FormFile'), setData, 'json', function(d) {
			if (type == 'word') {
				window.open(top.sysPath + '/report/word.jsp', new Date()
						.getTime());
			} else if (type == 'excel') {
				window.open(top.sysPath + '/report/excel.jsp', new Date()
						.getTime());
			}
		});

	}
</script>
</head>
<body class="easyui-layout" >
	<div id="i_sy_syAssembly_datagrid_toolbar" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px" id="i_sy_syAssembly_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syAssembly_datagrid_searchbox"
					pdt="i_sy_syAssembly_datagrid"></input>
					<div id="i_sy_syAssembly_datagrid_dSComb" style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
	<table id="i_sy_syAssembly_datagrid"></table>

	<div id="i_sy_syAssembly_datagrid_add_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syAssembly_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">新建</div>
			<!-- 请填入新建表单html  start -->
			<form id="i_sy_syAssembly_datagrid_add_form">
				<table width="99%" border="1" class="formtable">
					<tbody>
						<tr class="firstRow">
							<td class="label" align="right" style="width: 15%;">组件名称</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_syAssembly_datagrid_add_form_name" type="text"
								class="easyui-validatebox" data-options="required:true"
								reftype="0" /></td>
							<td class="label" align="right" style="width: 15%;">系统名称（英文）</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_syAssembly_datagrid_add_form_sysname" type="text"
								class="easyui-validatebox" data-options="required:true"
								reftype="0" /></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">获取值脚本</td>
							<td align="left" style="width: 35%;"><textarea
									id="i_sy_syAssembly_datagrid_add_form_getscript"
									class="easyui-validatebox" reftype="16"></textarea><a href="#"
								title="如：obj.combobox('getValue');" class="easyui-tooltip">填写说明</a></td>
							<td class="label" align="right" style="width: 15%;">设置值脚本</td>
							<td align="left" style="width: 35%;"><textarea
									id="i_sy_syAssembly_datagrid_add_form_setscript"
									class="easyui-validatebox" reftype="16"></textarea><a href="#"
								title="如：obj.datebox('setValue', v);" class="easyui-tooltip">填写说明</a></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">设置CSS类名脚本</td>
							<td align="left" style="width: 35%;"><textarea
									id="i_sy_syAssembly_datagrid_add_form_classname"
									class="easyui-validatebox" reftype="16"></textarea><a href="#"
								title="如：'easyui-combobox';" class="easyui-tooltip">填写说明</a></td>
							<td class="label" align="right" style="width: 15%;">html构建脚本</td>
							<td align="left" style="width: 35%;"><textarea
									id="i_sy_syAssembly_datagrid_add_form_html"
									class="easyui-validatebox" reftype="16"></textarea><a href="#" id="i_sy_syAssembly_datagrid_add_form_html_tooltip">填写说明</a></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">组件属性</td>
							<td align="left" style="width: 35%;"><textarea
									id="i_sy_syAssembly_datagrid_add_form_propety"
									class="easyui-validatebox" reftype="16"></textarea><a href="#"
								id="i_sy_syAssembly_datagrid_add_form_propety_tooltip">填写说明</a></td>
							<td class="label" align="right" style="width: 15%;">创建人ID</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_syAssembly_datagrid_add_form_createuserid" type="text"
								reftype="-1" /></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">使用说明</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_syAssembly_datagrid_add_form_filename" type="text"
								class="easyui-validatebox" reftype="0" /></td>
							<td class="label" align="right" style="width: 15%;">创建时间</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_syAssembly_datagrid_add_form_createtime" type="text"
								class="easyui-datetimebox" reftype="7" /></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">编号</td>
							<td align="left" style="width: 35%; display: none;"><input
								id="i_sy_syAssembly_datagrid_add_form_id" type="text"
								reftype="-1" /></td>
							<td><br /></td>
							<td><br /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入新建表单html  end -->
		</div>
	</div>
	<div id="i_sy_syAssembly_datagrid_edit_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syAssembly_datagrid_edit_btn',toolbar:[{
				text:'导出',
				iconCls:'icon-page_white_excel',
				handler:function(){
					getEditFormHtml('编辑','excel');//请修改
				}
			},
			{
				text:'导出',
				iconCls:'icon-page_white_word',
				handler:function(){
					getEditFormHtml('编辑','word');//请修改
				}
			}]"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">编辑</div>
			<div id="report">
				<!-- 请填入编辑表单html  start -->
				<form id="i_sy_syAssembly_datagrid_edit_form">
					<table width="99%" border="1" class="formtable">
						<tbody>
							<tr class="firstRow">
								<td class="label" align="right" style="width: 15%;">组件名称</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_syAssembly_datagrid_edit_form_name" type="text"
									class="easyui-validatebox" data-options="required:true"
									reftype="0" /></td>
								<td class="label" align="right" style="width: 15%;">系统名称（英文）</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_syAssembly_datagrid_edit_form_sysname" type="text"
									class="easyui-validatebox" data-options="required:true"
									reftype="0" /></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">获取值脚本</td>
								<td align="left" style="width: 35%;"><textarea
										id="i_sy_syAssembly_datagrid_edit_form_getscript"
										class="easyui-validatebox" reftype="16"></textarea><a href="#"
									title="如：obj.combobox('getValue');" class="easyui-tooltip">填写说明</a></td>
								<td class="label" align="right" style="width: 15%;">设置值脚本</td>
								<td align="left" style="width: 35%;"><textarea
										id="i_sy_syAssembly_datagrid_edit_form_setscript"
										class="easyui-validatebox" reftype="16"></textarea><a href="#"
									title="如：obj.datebox('setValue', v);" class="easyui-tooltip">填写说明</a></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">设置CSS类名脚本</td>
								<td align="left" style="width: 35%;"><textarea
										id="i_sy_syAssembly_datagrid_edit_form_classname"
										class="easyui-validatebox" reftype="16"></textarea><a href="#"
									title="如：'easyui-combobox';" class="easyui-tooltip">填写说明</a></td>
								<td class="label" align="right" style="width: 15%;">html构建脚本</td>
								<td align="left" style="width: 35%;"><textarea
										id="i_sy_syAssembly_datagrid_edit_form_html"
										class="easyui-validatebox" reftype="16"></textarea><a href="#" id="i_sy_syAssembly_datagrid_edit_form_html_tooltip">填写说明</a></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">组件属性</td>
								<td align="left" style="width: 35%;"><textarea
										id="i_sy_syAssembly_datagrid_edit_form_propety"
										class="easyui-validatebox" reftype="16"></textarea><a href="#" id="i_sy_syAssembly_datagrid_edit_form_propety_tooltip">填写说明</a></td>
								<td class="label" align="right" style="width: 15%;">创建人ID</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_syAssembly_datagrid_edit_form_createuserid"
									type="text" reftype="-1" /></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">使用说明</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_syAssembly_datagrid_edit_form_filename" type="text"
									class="easyui-validatebox" reftype="0" /></td>
								<td class="label" align="right" style="width: 15%;">创建时间</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_syAssembly_datagrid_edit_form_createtime" type="text"
									class="easyui-datetimebox" reftype="7" /></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">编号</td>
								<td align="left" style="width: 35%; display: none;"><input
									id="i_sy_syAssembly_datagrid_edit_form_id" type="text"
									reftype="-1" /></td>
								<td><br /></td>
								<td><br /></td>
							</tr>
						</tbody>
					</table>
				</form>
				<!-- 请填入编辑表单html  end -->
				<div id="report_ps"></div>
			</div>
		</div>
	</div>

	<!-- 按钮 start -->
	<div id="i_sy_syAssembly_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syAssembly_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
	<!-- 按钮 end -->

	<div id="i_sy_syAssembly_datagrid_upload_dialog" class="easyui-dialog"
		title="上传" style="width: 400px; height: 100px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<form id="i_sy_syAssembly_datagrid_upload_dialog_form" action=""
			enctype="multipart/form-data" method="post">
			<input type="text" name="fileid" style="display: none;" />上传文件：<input
				type="file" name="fileGroup"></br>
			<span style="color: red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br> <input
				type="button" value="上传" onClick="submitUploadForm();" />
		</form>
	</div>

</body>
</html>
