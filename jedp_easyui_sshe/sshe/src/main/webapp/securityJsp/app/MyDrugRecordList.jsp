<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%
	//药品入库
	String contextPath = request.getContextPath();
	String id = request.getParameter("id");
	SecurityUtil securityUtil = new SecurityUtil(session);
	System.out.println(">>>>>>" + id);
	id = ( id == null ) ? "" : id; 
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
	var grid;
	var userIdCombogrid;
	var datagrid; //定义全局变量datagrid
    var editRow = undefined; //定义全局变量：当前编辑的行
    
 
    
    var listType = function(){
    	//$(':input[name="data.id"]').val(1)
    	//console.log($(':input[name="data.drugInfo.drugCode"]').val());
    	//var medicalId = $(':input[name="data.drugInfo.drugCode"]').val();
       // if ( medicalId > 0) 
        {
    	 
        datagrid = $("#datagrid").datagrid({
        	url : sy.contextPath + '/app/drug-in-record!grid.sy?opMod=SELF',
            iconCls: 'icon-save', //图标
            pagination: true, //显示分页
            pageSize: 15, //页大小	
            pageList: [15, 30, 45, 60], //页大小下拉选项此项各value是pageSize的倍数
            fit: true, //datagrid自适应宽度
            fitColumn: false, //列自适应宽度
            striped: true, //行背景交换
            nowap: true, //列内容多时自动折至第二行
            border: false,
            idField: 'id', //主键
            columns: [[//显示的列
           	 {field: 'id', title: '编号', width: 50, sortable: true },
           	 
            { field: 'drugSpecInfo', title: '药品信息', width: 100,
            	   formatter:function(row){
            		   //console.log(row)
            		    return row.drugInfo.drugName;
                   }
              },
              {field: 'drugLotNo', title: '批次', width: 60, sortable: true},
              {field: 'num', title: '数量', width: 60, sortable: true},
              {field: 'drugSpecInfo.specification', title: '规格', width: 100, sortable: true,formatter:function(value, row, index){
            	     //console.log(row)
            	  	return row.drugSpecInfo.specification;
            	  }},
              {field: 'drugSpecInfo.unit', title: '单位', width: 100, sortable: true,formatter:function(value, row, index){
             	  	return row.drugSpecInfo.unit;
             	}},
              {field: 'price', title: '价格', width: 80, sortable: true },
              {field: 'opType', title: '操作', width: 100, sortable: true,
            	  formatter : function(value, row, index) {
  					switch (value) {
  					case 10:
  						return '入库';
  					case 20:
  						return '消耗';
  					case 30:
  						return '盘点';
  					case 40:
  						return '出库';
  					}
  					return '';
            	  }}, 
              {field: 'createTime', title: '入库时间', width: 120, sortable: true },
              {field: 'syuser', title: '操作人', width: 100, sortable: true,formatter:function(value, row, index){
         	  	return value.name;
         	  }}
               ]],
            onBeforeLoad:function(param){
            	 editRow = undefined;
             },
            onAfterEdit: function (rowIndex, rowData, changes) {
                //endEdit该方法触发此事件
                console.info(rowData);
                editRow = undefined;
            },
            onDblClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
                if (editRow != undefined) {
                    datagrid.datagrid("endEdit", editRow);
                }
                if (editRow == undefined) {
                    datagrid.datagrid("beginEdit", rowIndex);
                    editRow = rowIndex;
                }
            },
            onLoadSuccess:function(data){
            	console.log("clearSelections");
            	datagrid.datagrid("clearSelections");
            	datagrid.datagrid("unselectAll");
            }
        });
        
       }
    };
  	
	$(function() {
		listType();
		////-----------------------------------------------------
		 
		});
</script>



</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	<div class="easyui-panel" title="我的历史入库信息"
		style="height: 440px; width: 800px" data-options="region:'center',fit:true,border:false">
		<div data-options="region:'center',fit:true,border:false"
			style="height: 95%; width: 100%">
			<table id="datagrid" data-options="fit:true,border:false"></table>
		</div>

	</div>
</body>
</html>