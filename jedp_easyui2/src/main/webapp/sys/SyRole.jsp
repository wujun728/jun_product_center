<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/include/taglibs.jsp"%>
		<%@ include file="/include/meta.jsp"%>
		<script>
	var dtid = "i_sy_syRole_datagrid";
	var dt;
	var mdmap = new HashMap();//不允许id一样
	$(function() {
		var columns = [ [ {
			field : 'id',
			title : '编号',
			width : 150,
			checkbox : true,
			form : {
				hidden : true
			}
		}, {
			field : 'rolename',
			title : '角色名称',
			width : 150
		}, {
			field : 'roleid',
			title : '角色ID',
			width : 150,
			hidden : true,
			form : {
				hidden : true
			}
		}, {
			field : 'operatemapname',
			title : '操作权限',
			width : 150,
			hidden : true,
			form : {
				hidden : true
			}
		}, {
			field : 'operatemap',
			title : '操作权限id',
			width : 150,
			hidden : true,
			form : {
				hidden : true
			}
		}, {
			field : 'roleinfo',
			title : '角色描述',
			width : 150
		} ] ];
		var dataGrid = {
				id:dtid,
				url:"${ctx}"+actionUrl('/sys/','syRole','List'),
				dId:'id',
				columns:columns
				};
		dt = gGrid2(dataGrid);
		var straddfun = "dorow(dtid,'','${ctx}"+actionUrl('/sys/','syRole','Add')+"',updateFun,'c')";
		gDataGridToolbarBtn(dtid, 'icon-add', straddfun, "新增");
		var stredit = "dorow(dtid,'','${ctx}"+actionUrl('/sys/','syRole','Update')+"',updateFun,'u')";
		gDataGridToolbarBtn(dtid, 'icon-edit', stredit, "修改");
		var strdelfun = "dorow(dtid,'您是否确定要删除选择的数据','${ctx}"+actionUrl('/sys/','syRole','Delete')+"',updateFun,'d')";
		gDataGridToolbarBtn(dtid, 'icon-remove', strdelfun, "删除");
		var strexcelfun="dorow(dtid,'您确定要导出数据','${ctx}'+actionUrl('/sys/','syRole','Excel'),'','e')";
		gDataGridToolbarBtn(dtid,'icon-page_white_excel',strexcelfun,"导出");
		dt.datagrid({
			onClickRow : function(rowIndex, rowData) {
				delete mdmap;
				mdmap = new HashMap();
				initCheckTree(rowData.operatemap);
			},
			onCheck:function(rowIndex,rowData){
				$(this).datagrid('selectRow',rowIndex);
			}
		});
		$('#module_options').tree({
			url:'${ctx}'+actionUrl('/sys/','syParameter','Get_WebLeftMenuInfo')+'?systemId='+top.g_current_systemid,
			onBeforeSelect:function(node){
				var oldnode = $('#module_options').tree('getSelected');
				if(oldnode){
					if(oldnode.id==node.id) return;
				}
				saveLast_module_data_options();
			},onSelect:function(node){
				mSel();
			}
		});
	});
	
	function uncheck(id){
		var checkeds=$('#'+id).tree('getChecked');
		$.each(checkeds,function(k,v){
			$('#'+id).tree('uncheck',v.target);
		});
	}
	
	function mSel(){
		var node = $('#module_options').tree('getSelected');
		if(!node) return;
		$('#module_options').tree('check',node.target);
		uncheck('data_options');
		var dsel=mdmap.get(node.id).split('_');
		var do_roots =$('#data_options').tree('getRoots');
		for(var i=0;i<dsel.length;i++){
			if(dsel[i]==2){
				$('#data_options').tree('check',do_roots[0].target);
			}else if(dsel[i]==3){
				$('#data_options').tree('check',do_roots[1].target);
			}else if(dsel[i]==4){
				$('#data_options').tree('check',do_roots[2].target);
			}else if(dsel[i]==5){
				$('#data_options').tree('check',do_roots[3].target);
			}
		}
	}
	
	function initCheckTree(data){
		uncheck('data_options');
		uncheck('module_options');
		if(!data) return;
		var mdmap_ = new HashMap();
		var datas=data.split(',');
		for(var i=0;i<datas.length;i++){
			var ds = datas[i].split(':');
			mdmap_.put(ds[0],ds[1]);
		}
		var roots = $('#module_options').tree('getRoots');
		$.each(roots,function(k,v){
			var children =$('#module_options').tree('getChildren',v.target);
			var opts = mdmap_.get(v.id);
			if(opts){
				var md_opts=opts.split('-');
				if(md_opts[0]==1){$('#module_options').tree('check',v.target);}
				if(md_opts.length>1){mdmap.put(v.id,md_opts[1]);}
			}
			$.each(children,function(k,v){
				var opts = mdmap_.get(v.id);
				if(opts){
					var md_opts=opts.split('-');
					if(md_opts[0]==1){$('#module_options').tree('check',v.target);}
					if(md_opts.length>1){mdmap.put(v.id,md_opts[1]);}
				}
			});
		});
		mSel();
	}
	
	function updateFun(d) {
	}
	
	function updateDt(){
		dt.datagrid('load');
	}
	
	function saveLast_module_data_options(){
		var oldnode = $('#module_options').tree('getSelected');
		if(oldnode){
			var data_checkeds=$('#data_options').tree('getChecked');
			var data_checkeds_ids=[];
			$.each(data_checkeds,function(k,v){
				if(v.text=="增"){
					data_checkeds_ids.push(2);
				}else if(v.text=="删"){
					data_checkeds_ids.push(3);
				}else if(v.text=="改"){
					data_checkeds_ids.push(4);
				}else if(v.text=="查"){
					data_checkeds_ids.push(5);
				}
			});
			mdmap.put(oldnode.id,data_checkeds_ids.join("_"));
		}
	}
	
	function save_module_options(){
		var row = dt.datagrid('getSelected');
		if(!row) {log("请选择一行!");return;}
		saveLast_module_data_options();
		var module_checkeds=$('#module_options').tree('getChecked');
		var module_checkeds_ids=[];
		$.each(module_checkeds,function(k,v){
			module_checkeds_ids.push(v.id);
		});
		module_checkeds_ids = ','+module_checkeds_ids.join(',')+',';
		var roots = $('#module_options').tree('getRoots');
		var mol =[];
		$.each(roots,function(k,v){
			var children =$('#module_options').tree('getChildren',v.target);
			if(module_checkeds_ids.indexOf(trim(v.id))!=-1){
				mol.push(v.id+":1"+"-"+mdmap.get(v.id));
			}else{
				mol.push(v.id+":0");
			}
			$.each(children,function(k,v){
				if(module_checkeds_ids.indexOf(trim(v.id))!=-1){
					mol.push(v.id+":1"+"-"+mdmap.get(v.id));
				}else{
					mol.push(v.id+":0");
				}
			});
		});
		var roleid_=row.roleid;
		var id=row.id;
		var dataobj={roleid:roleid_,moduleids:mol.join(','),id:id};
		data("${ctx}"+actionUrl('/sys/','syRole','Update_Module'),dataobj,'json',updateDt);
	}
	
	function beSelModel(){
		$(dt.datagrid('options').toolbar).html('');//隐藏工具栏
		dt.datagrid('resize', { width:function(){return document.body.clientWidth;}, height:400});//重置大小
		$(document.body).layout('remove','east');  
	}
</script>
	</head>
	<body class="easyui-layout">
	<div id="module_toolbar">
	  <a href="#" class="icon-save" onclick="javascript:save_module_options()"></a>  
	</div>
		<div data-options="region:'center',split:true">
			<div id="i_sy_syRole_datagrid_toolbar"
		style="padding: 2px 0">
		<table cellpadding="0" cellspacing="0" style="width: 100%">
			<tr>
				<td style="padding-left: 2px"
					id="i_sy_syRole_datagrid_toolbtn"></td>
				<td style="text-align: right; padding-right: 2px"><input
					class="easyui-searchbox" data-options="prompt:'请输入搜索关键词'"
					style="width: 200px" searcher="dSearch"
					id="i_sy_syRole_datagrid_searchbox"
					pdt="i_sy_syRole_datagrid"></input>
					<div id="i_sy_syRole_datagrid_dSComb"
						style="width: 120px"></div></td>
			</tr>
		</table>
	</div>
 <table  id="i_sy_syRole_datagrid"></table> 
 
 <div id="i_sy_syRole_datagrid_add_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'新建',buttons:'#i_sy_syRole_datagrid_add_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">新建</div>
<!-- 请填入新建表单html  start -->
<form id="i_sy_syRole_datagrid_add_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">角色名称</td><td align="left" style="width:35%;"><input id="i_sy_syRole_datagrid_add_form_rolename" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">角色描述</td><td align="left" style="width:35%;"><input id="i_sy_syRole_datagrid_add_form_roleinfo" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_add_form_id" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">角色ID</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_add_form_roleid" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">角色map</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_add_form_operatemap" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">角色mapname</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_add_form_operatemapname" type="text" reftype="-1"/></td></tr></tbody></table></form>
<!-- 请填入新建表单html  end -->
</div>
</div>
<div id="i_sy_syRole_datagrid_edit_dialog"
		class="easyui-dialog"
		data-options="closed:true,modal:true,maximizable:true,resizable:true,title:'修改',buttons:'#i_sy_syRole_datagrid_edit_btn'"
		align="center" style="width: 1000px; height: 1000px;">
		<div style="padding: 10px 0 10px 10px">
		<div class="titlep">编辑</div>
<!-- 请填入编辑表单html  start -->
<form id="i_sy_syRole_datagrid_edit_form"><table width="99%" border="1" class="formtable"><tbody><tr class="firstRow"><td class="label" align="right" style="width:15%;">角色名称</td><td align="left" style="width:35%;"><input id="i_sy_syRole_datagrid_edit_form_rolename" type="text" class="easyui-validatebox" data-options="required:true" reftype="0"/></td><td class="label" align="right" style="width:15%;">角色描述</td><td align="left" style="width:35%;"><input id="i_sy_syRole_datagrid_edit_form_roleinfo" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">编号</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_edit_form_id" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">角色ID</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_edit_form_roleid" type="text" reftype="-1"/></td></tr><tr style="display:none;"><td class="label" align="right" style="width:15%;display:none;">角色map</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_edit_form_operatemap" type="text" reftype="-1"/></td><td class="label" align="right" style="width:15%;display:none;">角色mapname</td><td align="left" style="width:35%;display:none;"><input id="i_sy_syRole_datagrid_edit_form_operatemapname" type="text" reftype="-1"/></td></tr></tbody></table></form>
<!-- 请填入编辑表单html  end -->
</div>
</div>

<!-- 按钮 start -->
<div id="i_sy_syRole_datagrid_edit_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a>
	</div>
	<div id="i_sy_syRole_datagrid_add_btn">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">确定</a>
	</div>
<!-- 按钮 end -->

		</div>
		<div data-options="region:'east',split:true" style="width: 400px;">
			<div class="easyui-layout" fit="true">
				<div data-options="region:'center',split:true,title:'模块权限分配',tools:'#module_toolbar'"">
					<ul class="easyui-tree" id="module_options"
						data-options="checkbox:true,lines:true"></ul>
				</div>
				<div region="east" style="width: 150px;">
					<div class="easyui-panel"
						data-options="split:true,title:'数据权限分配',fit:true">
						<ul class="easyui-tree" id="data_options" data-options="checkbox:true,lines:true">
							<li>
								<span>增</span>
							</li>
							<li>
								<span>删</span>
							</li>
							<li>
								<span>改</span>
							</li>
							<li>
								<span>查</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
