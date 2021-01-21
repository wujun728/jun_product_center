<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	//// 体检
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<%-- 引入javascript--%>
<script src="<%=contextPath%>/jslib/module/app/tjReportData.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var grid;
	var userGrid;
	var medicalTypeCombox;
	var medicalBaseTypeCombox;
	var phyKeshiCombox;
	var attrCheckCombox;
	var isIndicatorsCombox;
	var attrUnitCombox;
	var IsAbnormalCombox;
	
	var addAttrFun = function() {
		
		var templateId =sys.getSelectedId( $('#grid')) ;
		var templateName = sys.getSelectedName( $('#grid')) ;
		var row = $('#grid').datagrid('getSelected');
		if (row) {
			var opOrder  =   row.opOrder;
			var custUserId = row.custUser.userId;
		    //alert(sy.contextPath + '/securityJsp/app/MedicalReportAttrDataForm.jsp?templateId='+templateId+"&templateName="+templateName+"&opOrder="+opOrder+"&custUserId="+custUserId)
			var dialog = parent.sy.modalListFormDialog({
				title : '添加体检模板属性',
				url : sy.contextPath + '/securityJsp/app/MedicalReportAttrDataForm.jsp?templateId='+templateId+"&templateName="+templateName+"&opOrder="+opOrder+"&custUserId="+custUserId,
				buttons : [{
					text : '提交属性数据',
					handler : function() {
						dialog.find('iframe').get(0).contentWindow.submitForm(dialog,row, $('#userGrid'), parent.$);
					}
				}]
			}); 
		}
	};
	var refreshAttr = function() {
		$('#userGrid').treegrid('load',sy.serializeObject($('#searchForm2')));
	}
	
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加体检信息',
			url : sy.contextPath + '/securityJsp/app/MedicalDetailForm.jsp?oType=TJ_REPORT_TYPE_INDUTY',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var addFunRe = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '添加体检信息',
			url : sy.contextPath + '/securityJsp/app/MedicalDetailForm.jsp?oType=TJ_REPORT_TYPE_INDUTY&data_empMedicalDetail_id=' + id ,
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看体检信息',
			url : sy.contextPath + '/securityJsp/app/MedicalDetailForm.jsp?oType=TJ_REPORT_TYPE_INDUTY&id=' + id
		});
	};
	
	var editFun = function(id) {
		var dialog = parent.sy
				.modalDialog({
					title : '编辑体检信息',
					url : sy.contextPath
							+ '/securityJsp/app/MedicalDetailForm.jsp?oType=TJ_REPORT_TYPE_INDUTY&id=' + id,
					buttons : [ {
						text : '编辑',
						handler : function() {
							dialog.find('iframe').get(0).contentWindow
									.submitForm(dialog, grid, parent.$);
						}
					} ]
				});
	};
	
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/medical-detail!delete.sy', {
					EmpMedicalDetailId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
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
	var getIsIndicatorsComboxById = function(id) {
		
	    var data = $("#isIndicatorsCombox").combobox('getData');
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

	var getPhyKeshiComboxById = function(id) {
		var data = $("#phyKeshiCombox").combobox('getData');
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

	var getAttrCheckComboxById = function(id) {
		var data = $("#attrCheckCombox").combobox('getData');
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
	var getIsAbnormalComboxById = function(id) {
		var data = $("#IsAbnormalCombox").combobox('getData');
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
	/* var grantRoleFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改角色',
			url : sy.contextPath + '/securityJsp/app/SyuserRoleGrant.jsp?id='
					+ id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	};
	var grantOrganizationFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改机构',
			url : sy.contextPath
					+ '/securityJsp/app/SyuserOrganizationGrant.jsp?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(
							dialog, grid, parent.$);
				}
			} ]
		});
	}; */
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
	var getMedicalBaseTypeOptionById = function(id) {
		var data = $("#medicalBaseTypeCombox").combobox('getData');
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
	var getAttrUnitComboxById = function(id) {
		var data = $("#attrUnitCombox").combobox('getData');
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
	var reloadMedicalTypeConbox = function() {
		var data = $('#medicalTypeCombox').combobox('getData');
        if (data.length > 0) {
       	 $('#medicalTypeCombox').combobox('select',data[0].id);
        }
	}
	$(function() {
		attrUnitCombox = $('#attrUnitCombox').combobox(
						{
							url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT',
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
		IsAbnormalCombox =$('#IsAbnormalCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_ISABNORMAL_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		medicalTypeCombox= $('#medicalTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getSubListByType.sy?type=TJ_REPORT_TYPE_INDUTY',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid',
	        hidden :true,
	        onLoadSuccess : function(){
	        	 var data = $('#medicalTypeCombox').combobox('getData');
                 if (data.length > 0) {
                	 $('#medicalTypeCombox').combobox('select',data[0].id);
                 }
	        }
		});
		
		medicalBaseTypeCombox= $('#medicalBaseTypeCombox').combobox({
	        url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE_BASE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid',
	        onLoadSuccess : function(){
	        	 var data = $('#medicalTypeCombox').combobox('getData');
                 if (data.length > 0) {
                	 $('#medicalTypeCombox').combobox('select',data[0].id);
                 }
	        }
		});
		isIndicatorsCombox = $('#isIndicatorsCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_VALUE_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
	        });
		grid = $('#grid').treegrid(
				{
					title : '',
					url : sy.contextPath + '/app/medical-detail!grid.sy?data.medicalType=10104102',
					striped : true,
					rownumbers : true,
					pagination : true,
					singleSelect : true,
					treeField : 'opOrder',
					parentField : 'pid',
					idField : 'id',
					sortName : 'createTime',
					sortOrder : 'desc',
					pageSize : 50,
					pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
					frozenColumns : [ [ {
						width : '50',
						title : '体检编号',
						field : 'id',
						sortable : true
					}, {
						width : '130',
						title : '体检流水',
						field : 'opOrder',
						sortable : true
					},  {
						width : '100',
						title : '体检人',
						field : 'custUser',
						sortable : true,
						formatter : function(value, row, index) {
							// alert(value.deptName + '  ' + row +'  ' + index)
							 return value.userName ;
						}
					} ] ],
					columns : [ [
							{
								width : '70',
								title : '体检日期',
								field : 'medicalTime',
								sortable : true 
							},
							{
								width : '80',
								title : '体检性质',
								field : 'medicalType',
								hidden : false,
								formatter : function(value, row, index) {
									return getMedicalTypeOptionById(value);
								}
							},
							{
								width : '80',
								title : '基本类型',
								field : 'medicalBaseType',
								hidden : false,
								formatter : function(value, row, index) {
									return getMedicalBaseTypeOptionById(value);
								}
							},
							{
								width : '80',
								title : '体检模板',
								field : 'medicalReportDef',
								formatter : function(value, row, index) {
									return value.templateName;
								}
							},
							{
								width : '100',
								title : '体检项目',
								field : 'medicalProject'
							},
							{
								width : '100',
								title : '体检结果',
								field : 'medicalResult'
							},
							{
								width : '100',
								title : '检查结论',
								field : 'medocaConclusion'
							},
							{
								width : '100',
								title : '创建时间',
								field : 'createTime',
								hidden: true,
								sortable : true
							},
							{
								width : '100',
								title : '修改时间',
								field : 'updateTime',
								sortable : true,
								hidden: true,
							},
							{
								title : '操作',
								field : 'action',
								width : '350',
								formatter : function(value, row) {
									var str = '';
							<%if (securityUtil.havePermission("/app/medical-detail!getById")) {%>
								var bt = systool.createFuncButton('ext-icon-note', '查看', '查看体检资料',
																		'showFun(\'{0}\');');
																str += sy.formatString(bt, row.id);
							<%}%>
							<%if (securityUtil.havePermission("/app/medical-detail!update")) {%>
								var bt = systool.createFuncButton('ext-icon-note_edit', '编辑',
																		'编辑体检资料', 'editFun(\'{0}\');');
																str += sy.formatString(bt, row.id);
							<%}%>
							<%-- <%if (securityUtil.havePermission("/app/medical-detail!grantRole")) {%>
								var bt = systool.createFuncButton('ext-icon-user', '体检角色', '给体检授予角色',
																		'grantRoleFun(\'{0}\');');
																str += sy.formatString(bt, row.id);
							<%}%>
							<%if (securityUtil.havePermission("/app/medical-detail!grantOrganization")) {%>
								var bt = systool.createFuncButton('ext-icon-group', '体检机构',
																		'给体检授予体检机构',
																		'grantOrganizationFun(\'{0}\');');
																str += sy.formatString(bt, row.id);
							<%}%> --%>
							<%if (securityUtil.havePermission("/app/medical-detail!delete")) {%>
								var bt = systool.createFuncButton('ext-icon-note_delete', '删除',
													'删除体检', 'removeFun(\'{0}\');');
											str += sy.formatString(bt, row.id);
							<%}%>
							<%if (securityUtil.havePermission("/app/medical-detail!save")) {%>
							var bt = systool.createFuncButton('ext-icon-note_edit', '添加复查体检',
																'添加复查体检', 'addFunRe(\'{0}\');');
														str += sy.formatString(bt, row.id);
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
				
		       
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								    
								<td>体检人</td>
								<td><input name="QUERY_t#custUser#userName_S_LK"
									style="width: 180px;" /></td>
								<td>体检性质</td>
								<td>
									<%-- <select id="medicalTypeCombox" name="QUERY_t#medicalType_B_EQ" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" /> --%>
									<select id="medicalTypeCombox" hidden="true" name="QUERY_t#medicalType_I_EQ" class="easyui-combotree"  style="width: 155px;display: none"></select>
									<!-- <img class="iconImg ext-icon-cross" onclick="$('#medicalTypeCombox').combotree('clear');" title="清空" /> -->
								</td>
								<td>体检类型</td>
								<td>
									<select id="medicalBaseTypeCombox" name="QUERY_t#medicalBaseType_I_EQ" class="easyui-combotree"  style="width: 155px;" ></select><img class="iconImg ext-icon-cross" onclick="$('#medicalBaseTypeCombox').combotree('clear');" title="清空" />
								</td>
								<td>体检时间</td>
								<td><input name="QUERY_t#medicalTime_D_GE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" />-<input
									name="QUERY_t#medicalTime_D_LE" class="Wdate"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom',plain:true"
									onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');reloadMedicalTypeConbox();grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
						 	
							<td><div class="datagrid-btn-separator"></div></td>
							<%
								if (securityUtil.havePermission("/app/medical-detail!save")) {
							%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="addFun();">添加体检信息</a></td>
							<%
								}
							%>
							
							<!-- <td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_add',plain:true"
								onclick="">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-table_go',plain:true" onclick="">导出</a></td>
								 -->
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
			style="height: 595px; margin-top: 1px">
			<div
				data-options="region:'north',split:true ,fit:false,iconCls:'icon-ok'"
				title="体检员工" style="width: 300px;height: 300px">
				<table id="grid" data-options="fit:true,border:false" ></table>
			</div>
			<div data-options="region:'center',title:'体检数据',split:true ,fit:false,iconCls:'icon-ok' "
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
											 	<td>检查类型</td>
								<td>
									<%-- <select id="medicalTypeCombox" name="QUERY_t#medicalType_B_EQ" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" /> --%>
									<select id="attrCheckCombox" name="QUERY_t#attrCheck_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrCheckCombox').combotree('clear');" title="清空" />
								</td>
											 
											<td>数值类型</td>
								<td>
									<%-- <select id="medicalTypeCombox" name="QUERY_t#medicalType_B_EQ" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=TJ_REPORT_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.sysDef.sysCode').combotree('clear');" title="清空" /> --%>
									<select id="isIndicatorsCombox" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#isIndicatorsCombox').combotree('clear');" title="清空" />
									<select id="attrUnitCombox" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
								</td>
											<th>体检科室</th>
											<td>
											<select id="phyKeshiCombox" name="QUERY_t#attrKeshi_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#phyKeshiCombox').combotree('clear');" title="清空" />
											</td>
											<td>是否异常</td>
											<td>
												<select id=IsAbnormalCombox name="QUERY_t#isAbnormal_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#IsAbnormalCombox').combotree('clear');" title="清空" />
											</td>											
											<td colspan="2"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="refreshAttr();">过滤</a>
												<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm2 input').val('');$('#userGrid').treegrid('load',{});">重置过滤</a>
											</td>
											</tr>
										</table>
									</form>
								</td>
							</tr>
							<tr>
								<td>
									<table>
										<tr>
											<%if (securityUtil.havePermission("/app/medical-detail!save")) {%>
											<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addAttrFun();">添加属性数据</a></td>
											<%}%>
											<td><div class="datagrid-btn-separator"></div></td>
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