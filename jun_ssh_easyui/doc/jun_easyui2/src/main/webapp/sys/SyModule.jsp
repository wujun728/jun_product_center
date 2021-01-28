<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
	var syModuleId = "i_sy_syModule_datagrid";
	var syModuleDt;
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
				field : 'menuname',
				title : '模块名称',
				width : 150
			},
			{
				field : 'menuid',
				title : '模块ID',
				width : 150,
				hidden : true,
				form : {
					edit : 'readonly="true"'
				}
			},
			{
				field : 'url',
				title : '链接',
				width : 150
			},
			{
				field : 'icon',
				title : '图标',
				width : 150,
				formatter : function(value, row, index) {
					if (value != undefined)
						return '<span class="'+value+'">&nbsp;&nbsp;&nbsp;&nbsp;</span>';
				}
			}, {
				field : 'pid',
				title : '从属模块ID',
				width : 150,
				hidden : true
			}, {
				field : 'sort',
				title : '排序',
				width : 150,
				formatter : function(value, row, index) {
					if(row.pid=='0'){
						return "<div style='font-weight:700;color:yellow;background-color:green;margin:0px;padding:0px;'>一级排序:" + value + "</div>";
					}else{
						return "<div style='font-weight:700;color:green;background-color:yellow;margin:0px;padding:0px;'>二级排序:" + value + "</div>";
					}
				}
			} ] ];
	$(function() {
		$('#'+syModuleId+'_add_dialog').dialog({
			onOpen:function(){
				reshOrganData('add');
			}
		});
		
		$('#'+syModuleId+'_edit_dialog').dialog({
			onOpen:function(){
				reshOrganData('edit');
			}
		});
		pageView(syModuleId, columns);
		intiIcons();
	});

	function updateFun(d) {

	}
	

	function initIconsWin(id){
		$('#winIcons').attr('tag',id);
		$('#winIcons').window('open');  
	}
	function intiIcons(){
			$.get(top.sysPath + "/plug/easyui-icons/icon-all.css", function(data) {
				var jsonObj = new Array();
				var ar = data.match(/([\w-]+)\s?(?={)/g);
				var html="";
				for ( var i = 0; i < ar.length; i++) {
					var obj = {};
					obj.id = obj.text = ar[i];
					html+=' <a href="javascript:void(0)" class="easyui-linkbutton" onclick="selectItem(\''+obj.text+'\')" data-options="'+"plain:true,iconCls:'"+obj.text+"',size:'large'"+'"> </a>';
				}
				$('#winIcons').html(html);
				$.parser.parse('#winIcons');  
				
			});
		}
		
	function selectItem(text){
		$('#'+$('#winIcons').attr('tag')).val(text);
		$('#winIcons').window('close');  
	}
		

	//模式add （通过链接pageView=add访问）
	function pageView_add() {
		$('#' + syModuleId + '_add_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syModuleId + '_add_btn a').off().click(
				function() {
					var setData = {};
					for ( var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syModuleId + '_add_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData[syModule + "." + field] = eGet('#' + syModuleId
								+ '_add_form_' + field);
					}
					data(getUrl('syModule', 'Add'), setData, 'json', null);
				});
		$('#' + syModuleId + '_add_dialog').dialog('open');
	}

	//模式edit （通过链接pageView=edit访问）
	function pageView_edit() {
		data_ = {
			id : $.getUrlParam('id')
		};
		$.ajax({
			url : getUrl('syModule', 'Get_ById'),
			data : data_,
			dataType : 'json',
			type : "post",
			async : true,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(r) {
				setForm(r, syModuleId);
				if (r) {
					if ('info' in r) {
						log(r.info);
					}
				}
				;
			}
		});
		$('#' + syModuleId + '_edit_dialog').dialog({
			noheader : true,
			fit : true,
			border : false,
			title : null,
			modal : true
		});
		$('#' + syModuleId + '_edit_btn a').off().click(
				function() {
					var setData = {};
					for ( var i = 0; i < columns[0].length; i++) {
						var field = columns[0][i].field;
						var title = columns[0][i].title;
						if (!checkFormField('#' + syModuleId + '_edit_form_'
								+ field)) {
							log('[' + title + ']不能为空，请填写!');
							return;
						}
						setData['syModule' + "." + field] = eGet('#'
								+ syModuleId + '_edit_form_' + field);
					}
					data(getUrl('syModule', 'Update'), setData, 'json', null);
				});
		$('#' + syModuleId + '_edit_dialog').dialog('open');
	}

	//模式list （通过链接pageView=list访问）
	function pageView_list() {
		var syModuleDataGrid = {
			id : syModuleId,
			url : "${ctx}" + actionUrl('/sys/', 'syModule', 'List'),
			dId : 'id',
			columns : columns,
			dTreeId : 'menuname',
			etype : eType.TreeGrid
		};

		syModuleDt = gGrid2(syModuleDataGrid);
		var straddfun = "dorow(syModuleId,'','${ctx}"
				+ actionUrl('/sys/', 'syModule', 'Add') + "',updateFun,'c')";
		gDataGridToolbarBtn(syModuleId, 'icon-add', straddfun, "新增");
		var stredit = "dorow(syModuleId,'','${ctx}"
				+ actionUrl('/sys/', 'syModule', 'Update') + "',updateFun,'u')";
		gDataGridToolbarBtn(syModuleId, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(syModuleId,'您是否确定要删除选择的数据','${ctx}"
				+ actionUrl('/sys/', 'syModule', 'Delete') + "',updateFun,'d')";
		gDataGridToolbarBtn(syModuleId, 'icon-remove', strdelfun, "删除");
		var strexcelfun = "dorow(syModuleId,'您确定要导出数据','${ctx}"
				+ actionUrl('/sys/', 'syModule', 'Excel') + "',updateFun,'e')";
		gDataGridToolbarBtn(syModuleId, 'icon-page_white_excel', strexcelfun,
				"导出");
	}

	function updateFun(d) {
		top.setWeb();
	}
		
	function reshOrganData(eora){
		$('#i_sy_syModule_datagrid_'+eora+'_form_pid').combotree('reload','/doroodo/sys/syModule_Get_ComboBox');
	}
</script>
</head>
<body class="easyui-layout" >
	<div id="i_sy_syModule_datagrid_toolbar" style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px" id="i_sy_syModule_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syModule_datagrid_searchbox" pdt="i_sy_syModule_datagrid"></input>
					<div id="i_sy_syModule_datagrid_dSComb" style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
	<table id="i_sy_syModule_datagrid"></table>

	<div id="i_sy_syModule_datagrid_add_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syModule_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">新建</div>
			<!-- 请填入新建表单html  start -->
			<form id="i_sy_syModule_datagrid_add_form">
				<table class="formtable" border="1" width="99%">
					<tbody>
						<tr class="firstRow">
							<td class="label" align="center">模块名称</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_add_form_menuname" reftype="-1"
								type="text" /></td>
								<td class="label" align="center">排序</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_add_form_sort" type="text"
								class="easyui-numberbox" reftype="5" /><a href="#" title="排序从0开始"
								class="easyui-tooltip">说明</a></td>
						</tr>
						<tr>
							<td class="label" align="center">链接</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_add_form_url" reftype="-1"
								type="text" /></td>
							<td class="label" align="center">图标</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_add_form_icon" reftype="-1"
								type="text" />
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  
        onclick="initIconsWin('i_sy_syModule_datagrid_add_form_icon');"></a>  
								
								</td>
						</tr>
						<tr>
							<td class="label" align="center">从属模块</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_add_form_pid" type="text" class="easyui-combobox" data-options="url:'/doroodo/sys/syModule_Get_ComboBox', valueField:'text'" reftype="2"/></td>
							<td class="label" style="display: none;" align="center">编号</td>
							<td style="display: none;" align="center"><input
								id="i_sy_syModule_datagrid_add_form_id" reftype="-1" type="text" /></td>
						</tr>
						<tr>
							<td class="label" align="center" style="display: none;" >模块ID</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syModule_datagrid_add_form_menuid" reftype="-1"
								type="text" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入新建表单html  end -->
		</div>
	</div>
	<div id="i_sy_syModule_datagrid_edit_dialog" class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syModule_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
			<div class="titlep">编辑</div>
			<!-- 请填入编辑表单html  start -->
			<form id="i_sy_syModule_datagrid_edit_form">
				<table class="formtable" border="1" width="99%">
					<tbody>
						<tr class="firstRow">
							<td class="label" align="center">模块名称</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_edit_form_menuname" reftype="-1"
								type="text" /></td>
							<td class="label" align="center">排序</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_edit_form_sort" type="text"
								class="easyui-numberbox" reftype="5" /><a href="#" title="排序从0开始"
								class="easyui-tooltip">说明</a></td>
						</tr>
						<tr>
							<td class="label" align="center">链接</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_edit_form_url" reftype="-1"
								type="text" /></td>
							<td class="label" align="center">图标</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_edit_form_icon" reftype="-1"
								type="text" />
								<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  
        onclick="initIconsWin('i_sy_syModule_datagrid_edit_form_icon');"></a>  
								</td>
						</tr>
						<tr>
							<td class="label" align="center">从属模块</td>
							<td align="center"><input
								id="i_sy_syModule_datagrid_edit_form_pid" type="text" class="easyui-combobox" data-options="url:'/doroodo/sys/syModule_Get_ComboBox', valueField:'text'" reftype="2"/></td>
							<td class="label" style="display: none;" align="center">编号</td>
							<td style="display: none;" align="center"><input
								id="i_sy_syModule_datagrid_edit_form_id" reftype="-1"
								type="text" /></td>
						</tr>
						<tr>
						<td class="label" align="center" style="display: none;">模块ID</td>
							<td align="center" style="display: none;"><input
								id="i_sy_syModule_datagrid_edit_form_menuid" reftype="-1"
								readonly="true" type="text" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			<!-- 请填入编辑表单html  end -->
		</div>
	</div>

	<!-- 按钮 start -->
	<div id="i_sy_syModule_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syModule_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
	<div id="i_flowtoobar"></div>
	<!-- 按钮 end -->
	
	<div id="winIcons" class="easyui-window" title="图标选择框" style="width:600px;height:400px"  
        data-options="iconCls:'icon-save',modal:true,closed:true">  
</div>  
	

</body>
</html>
