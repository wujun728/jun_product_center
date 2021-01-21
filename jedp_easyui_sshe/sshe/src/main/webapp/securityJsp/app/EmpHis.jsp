<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
	String contextPath = request.getContextPath();
	SecurityUtil securityUtil = new SecurityUtil(session);
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var addFun = function(id,name) {
		console.log(sy.contextPath + '/securityJsp/app/EmpHisForm.jsp?id='+id+"&name="+name)
		var dialog = parent.sy.modalDialog({
			title : '添加员工职业史信息',
			url : sy.contextPath + '/securityJsp/app/EmpHisForm.jsp?id='+id+"&name="+name,
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
			title : '查看员工职业史信息',
			url : sy.contextPath + '/securityJsp/app/EmpHisForm.jsp?hisId=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑员工职业史信息',
			url : sy.contextPath + '/securityJsp/app/EmpHisForm.jsp?hisId=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？' +id, function(r) {
			if (r) {
				$.post(sy.contextPath + '/app/emp-his!delete.sy', {
					EmpHisId : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	 	
	   var getWorkTypeCombox = function(id) {
	    	 //alert(id)
	    	 var data = $("#workTypeCombox").combobox('getData');
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
	    };
	   
	$(function() {
		var	workTypeCombox = $('#workTypeCombox').combobox({
			url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=WORK_TYPE',
	        idField:'id',
	        valueField:'id',
	        textField:'text',
	        parentField:'pid'
        });
		
		
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/emp-his!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'hisId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '员工职业史编号',
				field : 'hisId',
				sortable : true
			}, {
				width : '100',
				title : '员工姓名',
				field : 'custUser',
				sortable : true,
				formatter : function(value, row, index) {
					return row.custUser.userName;
				}
			}  ] ],
			columns : [ [  {
				width : '50',
				title : '工作单位',
				field : 'workComp',
				sortable : true
			}, {
				width : '120',
				title : '开始时间',
				field : 'startTime',
				sortable : true
			},   {
				width : '120',
				title : '结束时间',
				field : 'endTime',
				sortable : true
			},{
				width : '150',
				title : '车间',
				field : 'workShop',
				sortable : true
			},  {
				width : '80',
				title : '工种',
				field : 'workType',
				sortable : true,
				formatter:function(row){
         		   if(row != undefined ){
             			return  getWorkTypeCombox(row);  
         		   }
         		   return '';
                }
			
			},  {
				width : '150',
				title : '有害因素',
				field : 'harmful',
				sortable : true
			},  {
				width : '150',
				title : '防护措施',
				field : 'protect',
				sortable : true
			},  {
				width : '150',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			},{
				title : '操作',
				field : 'action',
				width : '350',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/emp-his!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看员工职业史资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.hisId);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-his!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑员工职业史资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.hisId);
					<%}%>
					<%if (securityUtil.havePermission("/app/emp-his!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除员工职业史','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.hisId);
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
			}
		});
		
	
	});
	
	//员工职业史，后退返回到员工管理列表
	function returnUrl(){
		window.location.href=document.referrer;
	}
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
								<td>工作单位</td>
								<td><input name="QUERY_t#workComp_S_LK" style="width: 180px;" /></td>
								<td>工种</td>
								<td>
									<select id="workTypeCombox"    name="QUERY_t#workType_I_EQ" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#workTypeCombox').combotree('clear');" title="清空" />
								</td>
								<td>创建时间</td>
								<td><input name="QUERY_t#createTime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createTime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">过滤</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="returnUrl();">返回</a>
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
							<%if (securityUtil.havePermission("/base/syuser!save")) {%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun(<%=id%>,'<%=name%>');">添加</a></td>
							<%}%>
							<td><div class="datagrid-btn-separator"></div></td>
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