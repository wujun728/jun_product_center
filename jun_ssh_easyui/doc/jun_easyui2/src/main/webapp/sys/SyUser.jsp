<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
	var syUserId = "i_sy_syUser_datagrid";
	var syUserDt, UploadDg, UploadFm;
	var linkp = '';
	var organid = '';
	var routeid='';
	var columns = [ [
			{
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true,
				form : {
					hidden : true
				}
			},
			{
				field : 'username',
				title : '用户名',
				width : 150,
				form : {
					classname : 'easyui-validatebox',
					dataoptions : "required:true",
					type : eType.ValidateBox
				}
			},
			{
				field : 'userid',
				title : '登录名',
				width : 150
			},
			{
				field : 'routename',
				title : '从属组织',
				width : 150,
				form : {
					classname : 'easyui-combotree',
					dataoptions : "url:'" + '${ctx}'
							+ actionUrl('/sys/', 'syOrgan', 'Get_Tree') + "'",
					type : eType.ComboTree
				}
			}, {
				field : 'routeid',
				title : '从属组织',
				width : 150,
				form : {
					hidden : true
				}
			}, {
				field : 'rolename',
				title : '用户角色',
				width : 150,
				form : {
					hidden : true
				}
			} ] ];
	$(function() {
		$('#'+syUserId+'_add_dialog').dialog({
			onOpen:function(){
				eSet('#i_sy_syUser_datagrid_add_form_routeid',routeid);
			}
		});
		organid = $.getUrlParam('id');
		routeid= $.getUrlParam('routeid');
		pageView(syUserId, columns);
	});

	//模式add （通过链接pageView=add访问）
	function pageView_add() {
		$('#' + syUserId + '_add_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syUserId + '_add_btn a').off().click(
				function() {
					var setData = {};
					for ( var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syUserId + '_add_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syUser' + "." + field] = eGet('#' + syUserId
								+ '_add_form_' + field);
					}
					data(getUrl('syUser', 'Add'), setData, 'json', null);
				});
		$('#' + syUserId + '_add_dialog').dialog('open');
	}

	//模式edit （通过链接pageView=edit访问）
	function pageView_edit() {
		data_ = {
			id : $.getUrlParam('id')
		};
		$.ajax({
			url : getUrl('syUser', 'GetById'),
			data : data_,
			dataType : 'json',
			type : "post",
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(r) {
				setForm(r, syUserId);
				if (r) {
					if ('info' in r) {
						log(r.info);
					}
				}
				;
			}
		});
		$('#' + syUserId + '_edit_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syUserId + '_edit_btn a').off().click(
				function() {
					var setData = {};
					for ( var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syUserId + '_edit_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syUser' + "." + field] = eGet('#' + syUserId
								+ '_edit_form_' + field);
					}
					data(getUrl('syUser', 'Update'), setData, 'json', null);
				});
		$('#' + syUserId + '_edit_dialog').dialog('open');
	}

	//模式list （通过链接pageView=list访问）
	function pageView_list() {
		var syUserDataGrid = {
			id : syUserId,
			url : "${ctx}" + actionUrl('/sys/', 'syUser', 'List')
					+ "?syOrgan.organid=" + organid,
			dId : 'id',
			columns : columns
		};

		UploadDg = $('#i_sy_syUser_datagrid_upload_dialog');
		UploadFm = $('#i_sy_syUser_datagrid_upload_dialog_form');
		UploadFm.attr('action', "${ctx}"
				+ actionUrl('/sys/', 'syUser', 'Upload'));

		syUserDt = gGrid2(syUserDataGrid);
		var straddfun = "dorow(syUserId,'','${ctx}"
				+ actionUrl('/sys/', 'syUser', 'Add') + "',updateFun,'c')";
		gDataGridToolbarBtn(syUserId, 'icon-add', straddfun, "新增");
		var stredit = "dorow(syUserId,'','${ctx}"
				+ actionUrl('/sys/', 'syUser', 'Update') + "',updateFun,'u')";
		gDataGridToolbarBtn(syUserId, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(syUserId,'您是否确定要删除选择的数据','${ctx}"
				+ actionUrl('/sys/', 'syUser', 'Delete') + "',updateFun,'d')";
		gDataGridToolbarBtn(syUserId, 'icon-remove', strdelfun, "删除");
		gDataGridToolbarBtn(syUserId, 'icon-edit', 'updateRole();', "分配角色");
		var strexcelfun = "dorow(syUserId,'您确定要导出数据','${ctx}"
				+ actionUrl('/sys/', 'syUser', 'Excel') + "?syOrgan.organid="
				+ organid + "',updateFun,'e')";
		gDataGridToolbarBtn(syUserId, 'icon-page_white_excel', strexcelfun,
				"导出");
		gDataGridToolbarBtn(syUserId, 'icon-page_white_excel', 'upLoadFun()',
				"导入");
	}

	function updateFun(d) {

	}

	function updateDt() {
		syUserDt.datagrid('load');
	}

	function updateRole() {
		var rows = syUserDt.datagrid('getChecked');
		if (rows.length > 0) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			var roled = $
					.window({
						title : "角色分配",
						url : '${ctx}/sys/SyRole.jsp',
						isIframe : true,
						height : 320,
						width : 500,
						winId : 'roledig',
						target : 'self',
						maximizable : true,
						buttons : [ {
							text : '确定',
							handler : function() {
								var dobj = roled.find('iframe')[0].contentWindow.dt;
								var sel = dobj.datagrid('getChecked');
								if (sel.length < 1) {
									$.messager.show({
										title : '提示',
										msg : '请勾选至少一个角色！'
									});
								} else {
									var rolenames = '';
									for ( var i = 0; i < sel.length; i++) {
										rolenames += sel[i].rolename + ',';
									}
									$.ajax({
												url : '${ctx}'
														+ actionUrl('/sys/',
																'syUser',
																'Update_Role'),
												data : {
													ids : ids.join(','),
													rolename : rolenames
												},
												dataType : 'text',
												type : "post",
												contentType : "application/x-www-form-urlencoded; charset=utf-8",
												success : function(r) {
													if (r == '')
														return;
													var obj = jQuery
															.parseJSON(r);
													dt.datagrid('uncheckAll');
													roled.dialog('close');
													updateDt();
													log(obj.info);
												}
											});
								}
							}
						} ],
						onComplete : function() {
							var obj = roled.find('iframe')[0].contentWindow;
							obj.beSelModel();
						}
					});

		} else {
			log("请选择要操作的数据!");
		}
	}

	function upLoadFun() {
		UploadDg.dialog('open');
	}

	function submitUploadForm() {
		UploadFm.form('submit', {
			success : function(d) {
				syUserDt.datagrid('reload');
				UploadDg.dialog('close');
				d = $.parseJSON(d);
				log(d.info);
			}
		});
	}
</script>
</head>
<body class="easyui-layout" >
	<div id="i_sy_syUser_datagrid_toolbar" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px" id="i_sy_syUser_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syUser_datagrid_searchbox" pdt="i_sy_syUser_datagrid"></input>
					<div id="i_sy_syUser_datagrid_dSComb" style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
	<table id="i_sy_syUser_datagrid"></table>

	<div id="i_sy_syUser_datagrid_add_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syUser_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">新建</div>
			<!-- 请填入新建表单html  start -->
			<form id="i_sy_syUser_datagrid_add_form">
				<table width="99%" border="1" class="formtable">
					<tbody>
						<tr>
							<td class="label" align="right" valign="top">用户名</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_add_form_username" type="text"
								class="easyui-validatebox" data-options="required:true"
								reftype="0" /></td>
							<td class="label" align="right" valign="top">登录名</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_add_form_userid" type="text"
								reftype="-1" /></td>
						</tr>
						<tr>
							<td class="label" align="right" valign="top">从属组织</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_add_form_routeid" type="text"
								class="easyui-combotree"
								data-options="url:'${ctx}/sys/syOrgan_Get_Tree'" reftype="3" /></td>
							<td class="label" align="center" style="display: none;">编号</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_add_form_id" type="text" reftype="-1" /></td>
						</tr>
						<tr>
							<td class="label" align="center" style="display: none;">从属组织</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_add_form_routename" type="text"
								reftype="-1" /></td>
							<td class="label" align="center" style="display: none;">用户角色</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_add_form_rolename" type="text"
								reftype="-1" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入新建表单html  end -->
		</div>
	</div>
	<div id="i_sy_syUser_datagrid_edit_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syUser_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">编辑</div>
			<!-- 请填入编辑表单html  start -->
			<form id="i_sy_syUser_datagrid_edit_form">
				<table width="99%" border="1" class="formtable">
					<tbody>
						<tr>
							<td class="label" align="right" valign="top">用户名</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_edit_form_username" type="text"
								class="easyui-validatebox" data-options="required:true"
								reftype="0" /></td>
							<td class="label" align="right" valign="top">登录名</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_edit_form_userid" type="text"
								reftype="-1" /></td>
						</tr>
						<tr>
							<td class="label" align="right" valign="top">从属组织</td>
							<td align="left" valign="top"><input
								id="i_sy_syUser_datagrid_edit_form_routeid" type="text"
								class="easyui-combotree"
								data-options="url:'${ctx}/sys/syOrgan_Get_Tree'" reftype="3" /></td>
							<td class="label" align="center" style="display: none;">编号</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_edit_form_id" type="text" reftype="-1" /></td>
						</tr>
						<tr>
							<td class="label" align="center" style="display: none;">从属组织</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_edit_form_routename" type="text"
								reftype="-1" /></td>
							<td class="label" align="center" style="display: none;">用户角色</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syUser_datagrid_edit_form_rolename" type="text"
								reftype="-1" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入编辑表单html  end -->
		</div>
	</div>

	<!-- 按钮 start -->
	<div id="i_sy_syUser_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syUser_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<!-- 按钮 end -->

	<div id="i_sy_syUser_datagrid_upload_dialog" class="easyui-dialog"
		title="上传" style="width: 400px; height: 100px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<form id="i_sy_syUser_datagrid_upload_dialog_form" action=""
			enctype="multipart/form-data" method="post">
			<input type="text" name="fileid" style="display: none;" />上传文件：<input
				type="file" name="fileGroup"><a
				href="${ctx}/excelmodel/用户表.xls" target="_blank">导入模版下载</a></br>
			<span style="color: red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br> <input
				type="button" value="上传" onClick="submitUploadForm();" />
		</form>
	</div>
</body>
</html>
