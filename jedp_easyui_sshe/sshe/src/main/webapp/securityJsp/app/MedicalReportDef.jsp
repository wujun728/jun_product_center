<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	//体检模板管理
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<%-- 引入javascript--%>
<script src="<%=contextPath%>/jslib/module/app/reportDef.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var grid;
	var userGrid;
	var medicalTypeCombox;
	
	var phyKeshiCombox;
	var attrCheckCombox;
	var isIndicatorsCombox;
	var attrUnitCombox;
	
	var addAttrFun = function() {
		var templateId =sys.getSelectedId( $('#grid')) ;
		var templateName = sys.getSelectedName( $('#grid')) ;
		
		if(templateId == null || templateId <0 ){
			parent.$.messager.alert('提示', '请先选择一个体检模板 ... \n操作提示： 鼠标选择左边体检模板中的对应模板，只能选择单行数据,然后再增加属性 ', 'info');
			return ;
		}
		var dialog = parent.sy.modalDialog({
			title : '添加体检模板属性',
			url : sy.contextPath + '/securityJsp/app/MedicalReportAttrDefForm.jsp?templateId='+templateId+"&templateName="+templateName,
			buttons : [ {
				text : '添加属性',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, $('#userGrid'), parent.$);
				}
			} ]
		});
	};
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加体检模板信息',
			url : sy.contextPath + '/securityJsp/app/MedicalReportDefForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看客户信息',
			url : sy.contextPath + '/securityJsp/app/MedicalReportDefForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑客户信息',
			url : sy.contextPath + '/securityJsp/app/MedicalReportDefForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/report-def!delete.sy', {
					MedicalReportDefId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	/* var grantRoleFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改角色',
			url : sy.contextPath + '/securityJsp/app/SyuserRoleGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	}; */
/* 	var grantOrganizationFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改机构',
			url : sy.contextPath + '/securityJsp/app/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	}; */
	
	var checkRoleUsersHasPriv_getById = function() {
		<%if (securityUtil.havePermission("/app/report-attr!getById")) {%>
			return true;
		<%}%>
		return false;
	};
	var checkRoleUsersHasPriv_update = function() {
		<%if (securityUtil.havePermission("/app/report-attr!update")) {%>
			return true;
		<%}%>
		return false;
	};
	var checkRoleUsersHasPriv_delete = function() {
		<%if (securityUtil.havePermission("/app/report-attr!delete")) {%>
			return true;
		<%}%>
		return false;
	};
	var refreshDef = function(){
		grid.datagrid('load',sy.serializeObject($('#searchForm')));
	}
	var refreshAttr = function(){
		$('#userGrid').treegrid('load',sy.serializeObject($('#searchForm2')));
	}
	
	var getMedicalTypeOptionById = function(id) {
		var data = $("#medicalTypeCombox").combobox('getData');
		var name = '';
		var count = data.length;
		//alert('count:'+ count)
		for ( var i = 0; i < count; i++) {
			//alert(data[i].text)
			if (data[i].id == id) {
				name = data[i].text;
				break;
			}
		}
		return name;
	}
	var getIsIndicatorsComboxById = function(id) {
			var data = $("#isIndicatorsCombox").combobox('getData');
			var name ='';
			var count = data.length;
			//alert('count:'+ count)
			for(var i =0; i < count; i++){
				//alert(data[i].text)
				 if(data[i].id == id)  
			        {  
			            name = data[i].text;
			            break;  
			        }
			}
			return name;
		}
		var getAttrCheckComboxById = function(id) {
			var data = $("#attrCheckCombox").combobox('getData');
			var name ='';
			var count = data.length;
			//alert('count:'+ count)
			for(var i =0; i < count; i++){
				//alert(data[i].text)
				 if(data[i].id == id)  
			        {  
			            name = data[i].text;
			            break;  
			        }
			}
			return name;
		}
		var getPhyKeshiComboxById = function(id) {
			//console.log('getAttrUnitComboxById' + id);
			var data = $("#phyKeshiCombox").combobox('getData');
			var name ='';
			var count = data.length;
			//alert('count:'+ count)
			for(var i =0; i < count; i++){
				//alert(data[i].text)
				 if(data[i].id == id)  
			        {  
			            name = data[i].text;
			            break;  
			        }
			}
			return name;
		}
		var getAttrUnitComboxById = function(id) {
			//console.log('getAttrUnitComboxById' + id);
			var data = $("#attrUnitCombox").combobox('getData');
			var name ='';
			var count = data.length;
			//alert('count:'+ count)
			for(var i =0; i < count; i++){
				//alert(data[i].text)
				 if(data[i].id == id)  
			        {  
			            name = data[i].text;
			            break;  
			        }
			}
			return name;
		}
		
		
	$(function() {
		medicalTypeCombox= $('#medicalTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		attrUnitCombox = $('#attrUnitCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/report-def!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'templateId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '编号',
				field : 'templateId',
				sortable : true,
				hidden : true
			}, {
				width : '100',
				title : '模板名称',
				field : 'templateName',
				sortable : true
			}  ] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				hidden : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updateTime',
				sortable : true,
				hidden : true
			}, {
				width : '50',
				title : '体检模板类别',
				field : 'medicalReportType',
				sortable : true,
				formatter : function(value, row, index) {
					return getMedicalTypeOptionById(value);
				}
			}, {
				width : '50',
				title : '状态',
				field : 'status',
				hidden : true,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '正常';
					case 1:
						return '删除';
					case 2:
						return '合作无效';
					}
				}
			}, {
				title : '操作',
				field : 'action',
				width : '350',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/report-def!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看客户资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.templateId);
					<%}%>
					<%if (securityUtil.havePermission("/app/report-def!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑客户资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.templateId);
					<%}%>
					<%if (securityUtil.havePermission("/app/report-def!grantRole")) {%>
						var bt = systool.createFuncButton('ext-icon-user','客户角色','给客户授予角色','grantRoleFun(\'{0}\');');
						str +=  sy.formatString(bt,row.templateId);
					<%}%>
					<%if (securityUtil.havePermission("/app/report-def!grantOrganization")) {%>
						var bt = systool.createFuncButton('ext-icon-group','客户机构','给客户授予客户机构','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.templateId);
					<%}%>						
					<%if (securityUtil.havePermission("/app/report-def!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除客户','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.templateId);
					<%}%>
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			},
			onClickRow: function(rowIndex, rowData) {
				// alert('rowIndex, rowData' + rowIndex + "   "+ rowData);
				 sys.getSelected($(this));
			}
		});
		
 	

		$('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'customerId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'customerId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'customerName',
				title : '客户',
				width : 100,
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'updateTime',
				title : '修改时间',
				width : 150,
				sortable : true
			} ] ]
		});
		;
		

		isIndicatorsCombox = $('#isIndicatorsCombox').combobox({
					url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_VALUE_TYPE',
			        idField:'id',
			        valueField:'id',
			        textField:'text',
			        parentField:'pid'
			        });
				
				phyKeshiCombox = $('#phyKeshiCombox').combobox({
			        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_KESHI',
			        idField:'id',
			        valueField:'id',
			        textField:'text',
			        parentField:'pid'
			        });
				attrCheckCombox= $('#attrCheckCombox').combobox({
			        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_CHECK',
			        idField:'id',
			        valueField:'id',
			        textField:'text',
			        parentField:'pid'
			        });
				
				
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<select id="isIndicatorsCombox" name="QUERY_t#isIndicators_B_EQ" class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#isIndicatorsCombox').combotree('clear');" title="清空" />
	<select id="attrCheckCombox" name="QUERY_t#attrCheck_B_EQ" class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrCheckCombox').combotree('clear');" title="清空" />
	<select id="phyKeshiCombox" name="QUERY_t#attrKeshi_B_EQ"  class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#phyKeshiCombox').combotree('clear');" title="清空" />
	<select id="attrUnitCombox" name="QUERY_t#attrUnit_I_EQ"   class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>客户名称</td>
								<td>
									<input id="customerIdCombogrid" name="QUERY_t#customerInfo#customerId_I_EQ" type="text" value="" style="width: 214px;">
								</td>
							</tr>
							<tr>
							<th>体检报告分类</th>
							<td>
							<!-- <select class="easyui-combobox" name="QUERY_t#medicalReportType_B_EQ" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1">入职体检</option>
							<option value="3">定期年度体检</option>
							<option value="11">入职体检(职业病)</option>
							<option value="22">离职体检(职业病)</option>
							<option value="33">定期年度体检(职业病)</option>
					</select> -->
					<select id="medicalTypeCombox" name="QUERY_t#medicalReportType_I_EQ" class="easyui-combotree"  style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#medicalTypeCombox').combotree('clear');" title="清空" />
					</td>
							</tr>
							<tr>	
								<td colspan="2"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="refreshDef()">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
							<!-- <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td> -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	 
<!-- 	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div> -->
	
	<div data-options="region:'center',fit:true,border:false">
		<div id="main-layout" class="easyui-layout"
			style="height: 445px; margin-top: 1px">
			<div
				data-options="region:'west',split:true ,fit:false,iconCls:'icon-ok'"
				title="体检模板" style="width: 300px;height: 300px">
				<table id="grid" data-options="fit:true,border:false" ></table>
			</div>
			<div data-options="region:'center',title:'模板属性',split:true ,fit:false,iconCls:'icon-ok' "
			 id="_content_00" style="width: 300px;height: 300px">
			 
			 <div id="usertoolbar" style="display: block;">
		<table>
			<tr>
				<td>
					<form id="searchForm2">
						<table>
							<tr>
								<td>属性名称</td>
								<td>
								<input id="data.attrName" name="QUERY_t#attrName_S_LK" type="text" value="" style="width: 214px;">
								</td>
							 	
							<th>属性检查类型</th>
							<td>
								<select id="QUERY_t_attrCheck_I_EQ" name="QUERY_t#attrCheck_I_EQ" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_CHECK'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#QUERY_t_attrCheck_I_EQ').combotree('clear');" title="清空" />
								
							</td>
							
								<td colspan="2"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="refreshAttr();">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm2 input').val('');refreshAttr();">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/app/report-attr!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addAttrFun();">添加属性</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="refreshAttr();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
							<!-- <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td> -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
	
					<table id="userGrid" data-options="fit:true,border:false" ></table>
			</div>
		</div>
	</div>
	
</body>
</html>