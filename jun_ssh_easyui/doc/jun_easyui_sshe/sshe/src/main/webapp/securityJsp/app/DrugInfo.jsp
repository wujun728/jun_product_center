<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<%-- 引入javascript--%>
<script src="<%=contextPath%>/jslib/datagrid-detailview.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var addSpecFun = function(drugCode,drugName) {
		var dialog = parent.sy.modalDialog({
			title : '添加药品规格信息',
			url : sy.contextPath + '/securityJsp/app/DrugSpecInfoForm.jsp?drugCode='+drugCode+"&drugName="+drugName,
			buttons : [ {
				text : '添加规格',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp?id=' + id
		});
	};
	
	var showSpecFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看药品规格信息',
			url : sy.contextPath + '/securityJsp/app/DrugSpecInfoForm.jsp?id=' + id
		});
	};
	/* var drugInFun = function(id){
		var url = sy.contextPath + '/securityJsp/app/DrugRecordForm.jsp?id='+id;
		window.location.href=url;
	}; */
	var syncShortName = function(){
		//var  drugName = $(':input[name="QUERY_t#drugName_S_LK"]').val();
		//$(':input[name="QUERY_t#shortName_S_LK"]').val(drugName);
	};
	
	var drugSpecInFun = function(id,drugCode,specification,unit){
		var url = sy.contextPath + '/securityJsp/app/DrugRecordForm.jsp?id='+id+'&drugCode='+drugCode+'&specification='+specification+'&unit='+unit;
		window.location.href=url;
	};
	
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugInfoForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var editSpecFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑药品信息',
			url : sy.contextPath + '/securityJsp/app/DrugSpecInfoForm.jsp?id=' + id,
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
				$.post(sy.contextPath + '/app/drug-info!delete.sy', {
					drugInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	  
	var removeSpecFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/drug-spec-info!delete.sy', {
					DrugSpecInfoId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/drug-info!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'drugCode',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '药品编号',
				field : 'drugCode',
				sortable : true
			}, {
				width : '100',
				title : '药品名称',
				field : 'drugName',
				sortable : true
			}  ] ],
			columns : [ [  {
				width : '100',
				title : '客户',
				field : 'customerInfo',	
				sortable : true,
				formatter : function(value, row, index) {
					if(value != undefined)
					 return value.customerName ;
					return '';
				}
			},{
				width : '100',
				title : '简称',
				field : 'shortName',
				sortable : true
			}  ,{
				width : '150',
				title : '药品描述',
				field : 'drugDesc',
				sortable : true
			},   {
				title : '操作',
				field : 'action',
				width : '250',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/drug-info!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看药品资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugCode);
					<%}%>
					<%if (securityUtil.havePermission("/app/drug-info!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑药品资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugCode);
					<%}%>
					<%-- <%if (securityUtil.havePermission("/app/drug-record!save")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','入库','药品入库','drugInFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugCode);
					<%}%>	 --%>	
					<%if (securityUtil.havePermission("/app/drug-info!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除药品','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.drugCode);
					<%}%>
					
					<%-- <%if (securityUtil.havePermission("/app/drug-spec-info!save")) {%> --%>
					var bt = systool.createFuncButton('ext-icon-note_delete','添加规格','添加规格','addSpecFun(\'{0}\',\'{1}\');');
					str +=  sy.formatString(bt,row.drugCode,row.drugName);
					<%-- <%}%> --%>
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
			view : detailview,
			detailFormatter : function(index, row) {
				return '<div style="padding:1px"><table class="ddv"></table></div>';
			},
			onExpandRow : function(index,rowParent){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
				console.log(rowParent)
				ddv.datagrid({
							url : sy.contextPath + '/app/drug-spec-info!grid.sy?drugCode='+rowParent.drugCode,
							fitColumns : true,
							singleSelect : true,
							rownumbers : true,
							loadMsg : '加载规格...',
							height : 'auto',
							columns : [ [ {
								field : 'specId',
								title : '规格编号',
								width : 50
								}, {
									field : 'specification',
									title : '规格',
									width : 100,
									align : 'right'
								}, {
									field : 'unit',
									title : '单位',
									width : 70,
									align : 'right'
								},  {
									field : 'createTime',
									title : '创建时间',
									width : 100,
									align : 'right',
									hidden: true
								} ,{
									field : 'ext1',
									title : '备注',
									width : 100,
									align : 'right'
								} , {
									title : '操作',
									field : 'action',
									width : '250',
									formatter : function(value, row) {
										var str = '';
										<%if (securityUtil.havePermission("/app/drug-spec-info!getById")) {%>
											var bt = systool.createFuncButton('ext-icon-note','查看','查看药品规格','showSpecFun(\'{0}\');');
											str +=  sy.formatString(bt,row.specId);
										<%}%>
										<%if (securityUtil.havePermission("/app/drug-spec-info!update")) {%>
											var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑药品规格','editSpecFun(\'{0}\');');
											str +=  sy.formatString(bt,row.specId);
										<%}%>
										<%if (securityUtil.havePermission("/app/drug-in-record!save")) {%>
											var bt = systool.createFuncButton('ext-icon-note_edit','入库','药品入库','drugSpecInFun(\'{0}\',\'{1}\',\'{2}\',\'{3}\');');
											str +=  sy.formatString(bt,row.specId,rowParent.drugCode,row.specification,row.unit);
										<%}%>
										<%if (securityUtil.havePermission("/app/drug-spec-info!delete")) {%>
											var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除药品规格','removeSpecFun(\'{0}\');');
											str +=  sy.formatString(bt,row.specId);
										<%}%>
										return str;
									}
								}] ],
								onResize : function() {
									$('#grid').datagrid('fixDetailRowHeight',index);
								},
								onLoadSuccess : function() {
									setTimeout(
										function() {
											$('#grid').datagrid('fixDetailRowHeight',index);
										}, 0);
								}
							});
						$('#grid').datagrid('fixDetailRowHeight', index);
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
								<td>药品名称</td>
								<td>
									<input name="durgName" style="width: 180px;" />
								</td>
								<td>简称</td>
								<td>
									<input name="shortName" style="width: 180px;" />
								</td>
								<!-- <td>生产厂商</td>
								<td><input name="QUERY_t#produceComp_S_LK" style="width: 180px;" /></td> -->
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="syncShortName();grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%if (securityUtil.havePermission("/app/drug-info!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加药品</a></td>
							<%}%>
							<%-- <%if (securityUtil.havePermission("/app/drug-info!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addSpecFun();">添加药品规格</a></td>
							<%}%> --%>
							
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="grid.datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
						</tr>
						 
			
					</table>
				</td>
			</tr>
		</table>
	</div>
	 
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>