<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript"
	src="${ctx}/js/page/SyPoint.js"></script>
<script>
	var syPointId = "i_sy_syPoint_datagrid";
	var syPointDt, syPointUploadDg, syPointUploadFm;
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
				title : '序号',
				width : '150'
			},
			{
				field : 'code',
				checkbox : false,
				addform : {
					type : 'eType.Input'
				},
				editform : {
					type : 'eType.Input'
				},
				title : '点号',
				width : '150'
			},
			{
				field : 'codeVal',
				checkbox : false,
				addform : {
					type : 'eType.Input',
					hidden : true
				},
				editform : {
					type : 'eType.Input',
					hidden : true
				},
				title : '点号值',
				width : '150'
			},
			{
				field : 'codeDesc',
				checkbox : false,
				addform : {
					type : 'eType.Input'
				},
				editform : {
					type : 'eType.Input'
				},
				title : '点描述',
				width : '150'
			},
			{
				field : 'codeCal',
				title : '公式',
				search : false,
 				hidden : true,
				addform : {
					type : 'eType.Input'
				},
				editform : {
					type : 'eType.Input'
				},
				width : '150'
			},
			{
				field : 'ctype',
				title : '类型',
				addform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/sySysdomain_ComboBox?field_name=ctype&table_name=sy_point', valueField:'id'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "valueField:'id', url:'/doroodo/sys/sySysdomain_ComboBox?field_name=ctype&table_name=sy_point'"
				},
				map : 'cx_sb_sysdomian|sy_point|ctype',
				width : '150'
			},
			{
				field : 'unit',
				title : '单位',
				addform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/sySysdomain_ComboBox?field_name=unit&table_name=sy_point', valueField:'id'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "valueField:'id', url:'/doroodo/sys/sySysdomain_ComboBox?field_name=unit&table_name=sy_point'"
				},
				map : 'cx_sb_sysdomian|sy_point|unit',
				width : '150'
			}, {
				field : 'note',
				checkbox : false,
				addform : {
					type : 'eType.Input',
					hidden : true
				},
				editform : {
					type : 'eType.Input',
					hidden : true
				},
				title : '备注Html',
				search : false,
 				hidden : true,
				width : '150'
			} ] ];
	$(function() {
		$('#i_sy_syPoint_datagrid_add_dialog').dialog({
			onOpen : function() {
				syPointAddOnOpen();
			}
		});

		$('#i_sy_syPoint_datagrid_edit_dialog').dialog({
			onOpen : function() {
				syPointEditOnOpen();
			}
		});
		pageView(syPointId, columns);
		syPointonload();
	});

	function updateFun(d) {

	}

	//模式add
	function pageView_add() {
		$('#' + syPointId + '_add_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syPointId + '_add_btn a').off().click(
				function() {
					if (!AddBtnClick())
						return;
					var setData = {};
					for (var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syPointId + '_add_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syPoint' + "." + field] = eGet('#'
								+ syPointId + '_add_form_' + field);
					}
					data(getUrl('syPoint', 'Add'), setData, 'json', null);
				});
		$('#' + syPointId + '_add_dialog').dialog('open');
	}

	//模式edit
	function pageView_edit() {
		data_ = {
			id : $.getUrlParam('id')
		};
		$.ajax({
			url : getUrl('syPoint', 'Get_ById'),
			data : data_,
			dataType : 'json',
			type : "post",
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(r) {
				setForm(r, syPointId);
				if (r) {
					if ('info' in r) {
						log(r.info);
					}
				}
				;
			}
		});
		$('#' + syPointId + '_edit_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syPointId + '_edit_btn a').off().click(
				function() {
					if (!EditBtnClick())
						return;
					var setData = {};
					for (var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syPointId + '_edit_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syPoint' + "." + field] = eGet('#'
								+ syPointId + '_edit_form_' + field);
					}
					data(getUrl('syPoint', 'Update'), setData, 'json', null);
				});
		$('#' + syPointId + '_edit_dialog').dialog('open');
	}

	//模式list
	function pageView_list() {
		var syPointDataGrid = {
			id : syPointId,
			url : '${ctx}' + actionUrl('/sys/', 'syPoint', 'List'),
			dId : 'id',
			columns : columns
		};

		syPointUploadDg = $('#i_sy_syPoint_datagrid_upload_dialog');
		syPointUploadFm = $('#i_sy_syPoint_datagrid_upload_dialog_form');
		syPointUploadFm.attr('action', '${ctx}'
				+ actionUrl('/sys/', 'syPoint', 'Upload'));

		syPointDt = gGrid2(syPointDataGrid);
		var straddfun = "dorow(syPointId,'','${ctx}"
				+ actionUrl('/sys/', 'syPoint', 'Add') + "',updateFun,'c')";
		gDataGridToolbarBtn(syPointId, 'icon-add', straddfun, "新增");
		var stredit = "dorow(syPointId,'','${ctx}"
				+ actionUrl('/sys/', 'syPoint', 'Update')
				+ "',updateFun,'u')";
		gDataGridToolbarBtn(syPointId, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(syPointId,'您是否确定要删除选择的数据','${ctx}"
				+ actionUrl('/sys/', 'syPoint', 'Delete')
				+ "',updateFun,'d')";
		gDataGridToolbarBtn(syPointId, 'icon-remove', strdelfun, "删除");
		var strexcelfun = "dorow(syPointId,'您确定要导出数据','${ctx}"
				+ actionUrl('/sys/', 'syPoint', 'Excel') + "',updateFun,'e')";
		gDataGridToolbarBtn(syPointId, 'icon-page_white_excel', strexcelfun,
				"导出");
		gDataGridToolbarBtn(syPointId, 'icon-page_white_excel',
				'upLoadFun()', "导入");
		gDataGridToolbarBtn(syPointId, 'icon-page_find', 'doroodo_search()',
				"复合查询");
	}

	function doroodo_search() {
		var searchObj = $.window({
			title : '查询构造器',
			url : top.sysPath + '/component/search.jsp?topthemeName='
					+ top.themeName,
			isIframe : true,
			height : 260,
			width : 800,
			winId : 'searchdig' + new Date().getTime(),
			target : 'self',
			maximizable : true,
			buttons : [ {
				text : '查询',
				handler : function() {
					console.info('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx')
					var obj = searchObj.find('iframe')[0].contentWindow;
					syPointDt.datagrid('load', obj.getSearchs('syPoint'));
					searchObj.window('destroy');
				}
			}, {
				text : '取消',
				handler : function() {
					searchObj.window('destroy');
				}
			} ],
			onComplete : function() {
				var obj = searchObj.find('iframe')[0].contentWindow;
				obj.setSearchColumns(columns);
			}
		});
	}
	function upLoadFun() {
		syPointUploadDg.dialog('open');
	}

	function submitUploadForm() {
		syPointUploadFm.form('submit', {
			success : function(d) {
				syPointDt.datagrid('reload');
				syPointUploadDg.dialog('close');
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
				var obj = $(n);
				if (!obj.is('a')) {
					var id = obj.attr('id');
					if (id) {
						gobj.html(eGet('#' + id));
					}
				}
			});
		});
		form.children().each(function(i, n) {
			$('*', $(n)).each(function(ii, nn) {
				if ($(nn).css("display") == 'none') {
					$(nn).remove();
				}
			});
		});
		$('script', form).remove();
		var setData = {
			'tableHtml' : '<div class="titlep">' + title + '</div>'
					+ form.html(),
			'tableTitle' : title
		};
		data(getUrl('syPoint', 'FormFile'), setData, 'json', function(d) {
			if (type == 'word') {
				window.open(top.sysPath + '/report/word.jsp', new Date()
						.getTime());
			} else if (type == 'excel') {
				window.open(top.sysPath + '/report/excel.jsp', new Date()
						.getTime());
			}
		});

	}

	function doroodo_formula(obj) {
		var type = 0;
		var _codeCtl="#i_sy_syPoint_datagrid_add_form_codeCal";
		var _htmlCtl="#i_sy_syPoint_datagrid_add_form_note";
		if (obj.id.indexOf('edit') >= 0) {
			type = 1;
			_codeCtl="#i_sy_syPoint_datagrid_edit_form_codeCal";
			_htmlCtl="#i_sy_syPoint_datagrid_edit_form_note";
		}
		var searchObj = $
				.window({
					title : '公式构造器',
					url : top.sysPath
							+ '/component/PointFormula.jsp?topthemeName='
							+ top.themeName + '&type=' + type,
					isIframe : true,
					height : 400,
					width : 800,
					winId : 'formula' + new Date().getTime(),
					target : 'self',
					maximizable : true,
					buttons : [
							{
								text : '确定',
								handler : function() {
									var obj = searchObj.find('iframe')[0].contentWindow;
									$(_codeCtl).val(obj.getScriptStr());
									$(_htmlCtl).val(obj.getDesignHtml());
									searchObj.window('destroy');
								}
							}, {
								text : '取消',
								handler : function() {
									searchObj.window('destroy');
								}
							} ],
					onComplete : function() {
						//var obj = searchObj.find('iframe')[0].contentWindow;
						//obj.setSearchColumns(columns);
					}
				});
	}
</script>
</head>
<body class="easyui-layout">
	<div id="i_sy_syPoint_datagrid_toolbar" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px" id="i_sy_syPoint_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syPoint_datagrid_searchbox"
					pdt="i_sy_syPoint_datagrid"></input>
					<div id="i_sy_syPoint_datagrid_dSComb" style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
	<table id="i_sy_syPoint_datagrid"></table>

	<div id="i_sy_syPoint_datagrid_add_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syPoint_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">新建</div>
			<!-- 请填入新建表单html  start -->
			<form id="i_sy_syPoint_datagrid_add_form">
				<table width="99%" border="1" class="formtable">
					<tbody>
						<tr>
							<td class="label" align="right" style="width: 15%;">点号</td>
							<td align="left" style="width: 85%;"><input
								id="i_sy_syPoint_datagrid_add_form_code" type="text"
								reftype="-1"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">点描述</td>
							<td align="left" style="width: 85%;"><input
								id="i_sy_syPoint_datagrid_add_form_codeDesc" type="text"
								reftype="-1"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">公式</td>
							<td align="left" style="width: 85%;"><input
								id="i_sy_syPoint_datagrid_add_form_codeCal" type="text"
								reftype="-1"><input type="button" id="btn_add_codeCal"
								onclick="doroodo_formula(this)" value="设计公式"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">类型</td>
							<td align="left" style="width: 85%;"><input
								id="i_sy_syPoint_datagrid_add_form_ctype" type="text"
								class="easyui-combobox"
								data-options="url:'/doroodo/sys/sySysdomain_ComboBox?field_name=ctype&amp;table_name=sy_point', valueField:'id'"
								reftype="2"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">单位</td>
							<td align="left" style="width: 85%;"><input
								id="i_sy_syPoint_datagrid_add_form_unit" type="text"
								class="easyui-combobox"
								data-options="url:'/doroodo/sys/sySysdomain_ComboBox?field_name=unit&amp;table_name=sy_point', valueField:'id'"
								reftype="2"></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">点号值</td>
							<td align="left" style="width: 85%; display: none;"><input
								id="i_sy_syPoint_datagrid_add_form_codeVal" type="text"
								reftype="-1"></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">备注Html</td>
							<td align="left" style="width: 85%; display: none;"><input
								id="i_sy_syPoint_datagrid_add_form_note" type="text"
								reftype="-1"></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">序号</td>
							<td align="left" style="width: 85%; display: none;"><input
								id="i_sy_syPoint_datagrid_add_form_id" type="text"
								reftype="-1"></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入新建表单html  end -->
		</div>
	</div>
	<div id="i_sy_syPoint_datagrid_edit_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syPoint_datagrid_edit_btn',toolbar:[{
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
				<form id="i_sy_syPoint_datagrid_edit_form">
					<table width="99%" border="1" class="formtable">
						<tbody>
							<tr>
								<td class="label" align="right" style="width: 15%;">点号</td>
								<td align="left" style="width: 85%;"><input
									id="i_sy_syPoint_datagrid_edit_form_code" type="text"
									reftype="-1"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">点描述</td>
								<td align="left" style="width: 85%;"><input
									id="i_sy_syPoint_datagrid_edit_form_codeDesc" type="text"
									reftype="-1"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">公式</td>
								<td align="left" style="width: 85%;"><input
									id="i_sy_syPoint_datagrid_edit_form_codeCal" type="text"
									reftype="-1"><input type="button" id="btn_edit_codeCal"
									onclick="doroodo_formula(this)" value="编辑公式"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">类型</td>
								<td align="left" style="width: 85%;"><input
									id="i_sy_syPoint_datagrid_edit_form_ctype" type="text"
									class="easyui-combobox"
									data-options="valueField:'id', url:'/doroodo/sys/sySysdomain_ComboBox?field_name=ctype&amp;table_name=sy_point'"
									reftype="2"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">单位</td>
								<td align="left" style="width: 85%;"><input
									id="i_sy_syPoint_datagrid_edit_form_unit" type="text"
									class="easyui-combobox"
									data-options="valueField:'id', url:'/doroodo/sys/sySysdomain_ComboBox?field_name=unit&amp;table_name=sy_point'"
									reftype="2"></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">点号值</td>
								<td align="left" style="width: 85%; display: none;"><input
									id="i_sy_syPoint_datagrid_edit_form_codeVal" type="text"
									reftype="-1"></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">备注Html</td>
								<td align="left" style="width: 85%; display: none;"><input
									id="i_sy_syPoint_datagrid_edit_form_note" type="text"
									reftype="-1"></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">序号</td>
								<td align="left" style="width: 85%; display: none;"><input
									id="i_sy_syPoint_datagrid_edit_form_id" type="text"
									reftype="-1"></td>
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
	<div id="i_sy_syPoint_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syPoint_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
	<!-- 按钮 end -->

	<div id="i_sy_syPoint_datagrid_upload_dialog" class="easyui-dialog"
		title="上传" style="width: 400px; height: 100px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<form id="i_sy_syPoint_datagrid_upload_dialog_form" action=""
			enctype="multipart/form-data" method="post">
			<input type="text" name="fileid" style="display: none;" />上传文件：<input
				type="file" name="fileGroup"></br> <span style="color: red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
			<input type="button" value="上传" onClick="submitUploadForm();" />
		</form>
	</div>

</body>
</html>
