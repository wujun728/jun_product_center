<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script id="pjs" type="text/javascript"
	src="${ctx}/js/page/CxSbTerminal.js"></script>
<script>
	var cxSbTerminalId = "i_sy_cxSbTerminal_datagrid";
	var cxSbTerminalDt, cxSbTerminalUploadDg, cxSbTerminalUploadFm;
	var columns = [ [
			{
				field : 'id',
				addform : {
					type : 'eType.Input',
					hidden : true
				},
				editform : {
					type : 'eType.Input',
					hidden : true
				},
				checkbox : true,
				title : '编号',
				width : '150'
			},
			{
				field : 'terminalNumber',
				hidden : 获取链接参数('gk') != "1",
				addform : {
					type : 'eType.Input',
					dataoptions : "missingMessage:'不允许为空！', required:true",
					hidden : false
				},
				editform : {
					type : 'eType.Input',
					dataoptions : "required:true",
					hidden : false
				},
				checkbox : false,
				title : '终端地址',
				width : '150'
			},
			{
				field : 'position',
				addform : {
					type : 'eType.Input',
					hidden : false
				},
				editform : {
					type : 'eType.Input',
					hidden : false
				},
				checkbox : false,
				title : '安装位置',
				width : '150'
			},
			{
				field : 'circuit',
				title : '监测线路',
				addform : {
					type : 'eType.ComboBox',
					dataoptions : "valueField:'text', url:'/doroodo/sys/cxSbLine_ComboBox'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "valueField:'text', url:'/doroodo/sys/cxSbLine_ComboBox'"
				},
				width : '150'
			},
			{
				field : 'factory',
				title : '厂家',
				addform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				width : '150'
			},{
				field : 'simCard',
				addform : {
					type : 'eType.Input',
					hidden : false
				},
				editform : {
					type : 'eType.Input',
					hidden : false
				},
				checkbox : false,
				title : 'SIM卡号',
				width : '150'
			},
			{
				field : 'state',
				hidden : 获取链接参数('gk') == "1",
				addform : {
					type : 'eType.Input',
					hidden : true
				},
				editform : {
					type : 'eType.Input',
					hidden : true
				},
				title : '终端状态',
				formatter : function(value, row, index) {
					if (value == "分") {
						return "<div style='background:red;width:99%;height:99%;text-align: center;color:yellow'>分闸</div>";
					} else if(value == "合") {
						return "<div style='background:green;width:99%;height:99%;text-align: center;color:white'>合闸</div>";
					}else{
						return "<div style='background:gray;width:99%;height:99%;text-align: center;color:white'>未连接</div>";
					}

				},
				width : '150'
			},
			{
				field : 'factory',
				title : '厂家',
				addform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				width : '150'
			},
			
			{
				hidden : 获取链接参数('gk') != "1",
				field : 'cmdRate',
				title : '总召频率',
				addform : {
					type : 'eType.NumberBox',
					hidden : false
				},
				editform : {
					type : 'eType.NumberBox',
					dataoptions : "required:true"
				},
				width : '150'
			},
			{
				hidden : 获取链接参数('gk') != "1",
				field : 'protocol',
				title : '协议',

				addform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				editform : {
					type : 'eType.ComboBox',
					dataoptions : "url:'/doroodo/sys/cxSbLine_ComboBox', valueField:'text'"
				},
				width : '150'
			},
			{
				hidden : 获取链接参数('gk') != "1",
				field : 'type',
				checkbox : false,
				addform : {
					type : 'eType.Input',
					hidden : false
				},
				editform : {
					type : 'eType.Input',
					hidden : false,
					dataoptions : "required:false"
				},
				title : '型号',
				width : '150'
			},
			{
				field : 'dd',
				search : false,
				checkbox : false,
				addform : {
					type : 'eType.Input',
					hidden : false
				},
				editform : {
					type : 'eType.Input',
					hidden : false
				},
				title : '操作',
				hidden : 获取链接参数('gk') == "1"||获取链接参数('gk') == "2",
				width : '150',
				formatter : function(value, row, index) {
					if (row.state == "分") {
						return "<div style='width:99%;text-algin:center;'><input type='button' style='width:90%;cursor: pointer;' value='合' onclick='cmdControl("
								+ row['id']
								+ ",\""
								+ row.position
								+ "\""
								+ ",\"合\")'></input></div>";
					} else if (row.state == "合") {
						return "<div style='width:99%;text-algin:center;'><input type='button' style='width:90%;cursor: pointer;' value='分' onclick='cmdControl("
								+ row['id']
								+ ",\""
								+ row.position
								+ "\""
								+ ",\"分\")'></input></div>";
					}else{
						return "<div style='width:99%;text-algin:center;'><input type='button' style='width:90%' value='---' disabled='disabled'></input></div>";
					}

				}
			} ] ];
	
	function cmdControl(id,postion,handle){
		var _handle=handle;
		if(handle=="分"){
			handle="<span style='color:red'>分闸</span>"
		}else{
			handle="<span style='color:green'>合闸</span>"
		}
		$.messager.confirm('分合闸操作确认', '确认对['+postion+']进行'+handle+'操作！', function(r){
			if(!r)return;
			var cmd={};
			cmd["id"]=id;
			cmd["cmd"]=_handle;
			data(getUrl('cxSbTerminal', 'Control_ByCmd'), cmd, 'json', function(d){
				if(d=="0"){
					log('操作成功');
					cxSbTerminalDt.datagrid('reload');
				}else{
					log('操作失败');
				}
			});
          });
		}
	$(function() {
		$('#i_sy_cxSbTerminal_datagrid_add_dialog').dialog({
			onOpen : function() {
				cxSbTerminalAddOnOpen();
			}
		});

		$('#i_sy_cxSbTerminal_datagrid_edit_dialog').dialog({
			onOpen : function() {
				cxSbTerminalEditOnOpen();
			}
		});
		pageView(cxSbTerminalId, columns);
		cxSbTerminalonload();
	});

	function updateFun(d) {

	}

	//模式add
	function pageView_add() {
		$('#' + cxSbTerminalId + '_add_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + cxSbTerminalId + '_add_btn a').off().click(
				function() {
					if (!AddBtnClick())
						return;
					var setData = {};
					for (var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + cxSbTerminalId + '_add_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['cxSbTerminal' + "." + field] = eGet('#'
								+ cxSbTerminalId + '_add_form_' + field);
					}
					data(getUrl('cxSbTerminal', 'Add'), setData, 'json', null);
				});
		$('#' + cxSbTerminalId + '_add_dialog').dialog('open');
	}

	//模式edit
	function pageView_edit() {
		data_ = {
			id : $.getUrlParam('id')
		};
		$.ajax({
			url : getUrl('cxSbTerminal', 'Get_ById'),
			data : data_,
			dataType : 'json',
			type : "post",
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(r) {
				setForm(r, cxSbTerminalId);
				if (r) {
					if ('info' in r) {
						log(r.info);
					}
				}
				;
			}
		});
		$('#' + cxSbTerminalId + '_edit_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + cxSbTerminalId + '_edit_btn a').off().click(
				function() {
					if (!EditBtnClick())
						return;
					var setData = {};
					for (var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + cxSbTerminalId
								+ '_edit_form_' + field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['cxSbTerminal' + "." + field] = eGet('#'
								+ cxSbTerminalId + '_edit_form_' + field);
					}
					data(getUrl('cxSbTerminal', 'Update'), setData, 'json',
							null);
				});
		$('#' + cxSbTerminalId + '_edit_dialog').dialog('open');
	}

	//模式list
	function pageView_list() {
		var cxSbTerminalDataGrid = {
			id : cxSbTerminalId,
			url : '${ctx}' + actionUrl('/sys/', 'cxSbTerminal', 'List'),
			dId : 'id',
			columns : columns
		};

		cxSbTerminalUploadDg = $('#i_sy_cxSbTerminal_datagrid_upload_dialog');
		cxSbTerminalUploadFm = $('#i_sy_cxSbTerminal_datagrid_upload_dialog_form');
		cxSbTerminalUploadFm.attr('action', '${ctx}'
				+ actionUrl('/sys/', 'cxSbTerminal', 'Upload'));

		cxSbTerminalDt = gGrid2(cxSbTerminalDataGrid);
		if(获取链接参数('gk')=='1'){
		var straddfun = "dorow(cxSbTerminalId,'','${ctx}"
				+ actionUrl('/sys/', 'cxSbTerminal', 'Add')
				+ "',updateFun,'c')";
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-add', straddfun, "新增");
		var stredit = "dorow(cxSbTerminalId,'','${ctx}"
				+ actionUrl('/sys/', 'cxSbTerminal', 'Update')
				+ "',updateFun,'u')";
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(cxSbTerminalId,'您是否确定要删除选择的数据','${ctx}"
				+ actionUrl('/sys/', 'cxSbTerminal', 'Delete')
				+ "',updateFun,'d')";
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-remove', strdelfun, "删除");
		}
		var strexcelfun = "dorow(cxSbTerminalId,'您确定要导出数据','${ctx}"
				+ actionUrl('/sys/', 'cxSbTerminal', 'Excel')
				+ "',updateFun,'e')";
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-page_white_excel',
				strexcelfun, "导出");
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-page_white_excel',
				'upLoadFun()', "导入");
		gDataGridToolbarBtn(cxSbTerminalId, 'icon-page_find',
				'doroodo_search()', "复合查询");
	}

	function doroodo_search() {
		var searchObj = $
				.window({
					title : '查询构造器',
					url : top.sysPath + '/component/search.jsp?topthemeName='
							+ top.themeName,
					isIframe : true,
					height : 260,
					width : 800,
					winId : 'searchdig' + new Date().getTime(),
					target : 'self',
					maximizable : true,
					buttons : [
							{
								text : '查询',
								handler : function() {
									var obj = searchObj.find('iframe')[0].contentWindow;
									cxSbTerminalDt.datagrid('load', obj
											.getSearchs('cxSbTerminal'));
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
		cxSbTerminalUploadDg.dialog('open');
	}

	function submitUploadForm() {
		cxSbTerminalUploadFm.form('submit', {
			success : function(d) {
				cxSbTerminalDt.datagrid('reload');
				cxSbTerminalUploadDg.dialog('close');
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
		data(getUrl('cxSbTerminal', 'FormFile'), setData, 'json', function(d) {
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
<body class="easyui-layout">
	<div id="i_sy_cxSbTerminal_datagrid_toolbar" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_cxSbTerminal_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_cxSbTerminal_datagrid_searchbox"
					pdt="i_sy_cxSbTerminal_datagrid"></input>
					<div id="i_sy_cxSbTerminal_datagrid_dSComb" style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
	<table id="i_sy_cxSbTerminal_datagrid"></table>

	<div id="i_sy_cxSbTerminal_datagrid_add_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_cxSbTerminal_datagrid_add_btn'"
		align="right" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">新建</div>
			<!-- 请填入create表单html  start -->
			<form id="i_sy_cxSbTerminal_datagrid_add_form">
				<table width="99%" border="1" class="formtable">
					<tbody>
						<tr>
							<td class="label" align="right" style="width: 15%;">终端地址</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_terminalNumber"
								type="text" data-options="required:true" reftype="-1"></td>
							<td class="label" align="right" style="width: 15%;">监测线路</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_circuit" type="text"
								class="easyui-combobox"
								data-options="valueField:'id', url:'/doroodo/sys/cxSbLine_ComboBox'"
								reftype="2"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">安装位置</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_position" type="text"
								reftype="-1"></td>
							<td class="label" align="right" style="width: 15%;">厂家</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_factory" type="text"
								class="easyui-combobox"
								data-options="url:'/doroodo/sys/cxSbCompany_ComboBox', valueField:'id'"
								reftype="2"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">型号</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_type" type="text"
								data-options="required:false" reftype="-1"></td>
							<td class="label" align="right" style="width: 15%;">协议</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_protocol" type="text"
								class="easyui-combobox"
								data-options="url:'/doroodo/sys/cxSbSysdomian_ComboBox?field_name=protocol&table_name=cx_sb_terminal', valueField:'id'"
								reftype="2"></td>
						</tr>
						<tr>
							<td class="label" align="right" style="width: 15%;">SIM卡号</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_simCard" type="text"
								reftype="-1"></td>
							<td class="label" align="right" style="width: 15%;">总召频率</td>
							<td align="left" style="width: 35%;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_cmdRate" type="text"
								class="easyui-numberbox" data-options="required:true"
								reftype="5"></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">编号</td>
							<td align="left" style="width: 35%; display: none;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_id" type="text"
								reftype="-1"></td>
							<td></td>
							<td></td>
						</tr>
						<tr style="display: none;">
							<td class="label" align="right"
								style="width: 15%; display: none;">终端状态</td>
							<td align="left" style="width: 35%; display: none;"><input
								id="i_sy_cxSbTerminal_datagrid_add_form_state" type="text"
								reftype="-1"></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入create表单html  end -->
		</div>
	</div>
	<div id="i_sy_cxSbTerminal_datagrid_edit_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_cxSbTerminal_datagrid_edit_btn',toolbar:[{
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
				<form id="i_sy_cxSbTerminal_datagrid_edit_form">
					<table width="99%" border="1" class="formtable">
						<tbody>
							<tr>
								<td class="label" align="right" style="width: 15%;">终端地址</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_terminalNumber"
									type="text" data-options="required:true" reftype="-1"></td>
								<td class="label" align="right" style="width: 15%;">监测线路</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_circuit" type="text"
									class="easyui-combobox"
									data-options="valueField:'id', url:'/doroodo/sys/cxSbLine_ComboBox'"
									reftype="2"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">安装位置</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_position" type="text"
									reftype="-1"></td>
								<td class="label" align="right" style="width: 15%;">厂家</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_factory" type="text"
									class="easyui-combobox"
									data-options="url:'/doroodo/sys/cxSbCompany_ComboBox', valueField:'id'"
									reftype="2"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">型号</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_type" type="text"
									data-options="required:false" reftype="-1"></td>
								<td class="label" align="right" style="width: 15%;">协议</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_protocol" type="text"
									class="easyui-combobox"
									data-options="url:'/doroodo/sys/cxSbSysdomian_ComboBox?field_name=protocol&table_name=cx_sb_terminal', valueField:'id'"
									reftype="2"></td>
							</tr>
							<tr>
								<td class="label" align="right" style="width: 15%;">SIM卡号</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_simCard" type="text"
									reftype="-1"></td>
								<td class="label" align="right" style="width: 15%;">总召频率</td>
								<td align="left" style="width: 35%;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_cmdRate" type="text"
									class="easyui-numberbox" data-options="required:true"
									reftype="5"></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">编号</td>
								<td align="left" style="width: 35%; display: none;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_id" type="text"
									reftype="-1"></td>
								<td></td>
								<td></td>
							</tr>
							<tr style="display: none;">
								<td class="label" align="right"
									style="width: 15%; display: none;">终端状态</td>
								<td align="left" style="width: 35%; display: none;"><input
									id="i_sy_cxSbTerminal_datagrid_edit_form_state" type="text"
									reftype="-1"></td>
								<td></td>
								<td></td>
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
	<div id="i_sy_cxSbTerminal_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_cxSbTerminal_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
	<!-- 按钮 end -->

	<div id="i_sy_cxSbTerminal_datagrid_upload_dialog"
		class="easyui-dialog" title="上传" style="width: 400px; height: 100px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<form id="i_sy_cxSbTerminal_datagrid_upload_dialog_form" action=""
			enctype="multipart/form-data" method="post">
			<input type="text" name="fileid" style="display: none;" />上传文件：<input
				type="file" name="fileGroup"></br>
			<span style="color: red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br> <input
				type="button" value="上传" onClick="submitUploadForm();" />
		</form>
	</div>

</body>
</html>
