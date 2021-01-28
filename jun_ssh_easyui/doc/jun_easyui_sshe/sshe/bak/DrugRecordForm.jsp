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
    
	var listMyRecord_CC = function() {
		listType();
		return ;
	};
		
	var submitForm_BASE = function($op) {
		if ($('form').form('validate')) 
		{
			var url;
			console.log($(':input[name="data.drugInfo.drugCode"]').val()) 
			if ($(':input[name="data.drugInfo.drugCode"]').val() > 0) {
				if($op == 'rk'){
					url = sy.contextPath + '/app/drug-record!save.sy?opMod=IN';
				}else if($op == 'pd'){
					url = sy.contextPath + '/app/drug-record!save.sy?opMod=PANDIAN';
				}
				
			} else {
				parent.$.messager.alert('提示', '请选择入库药品..如果没有找到你要添加的药品，请去药品管理界面中增加!!', 'info');
				return false;
			}
			console.log(sy.serializeObject($('form')))
			//alert(sy.serializeObject($('form')))
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						console.log(result.obj)
						if (result.obj.id != undefined) {
									$('form').form('load',{
														'data.id' : result.obj.id,
														'data.drugInfo.drugCode' : result.obj.drugInfo.drugCode,
														'data.num' : result.obj.num,
														'data.price' : result.obj.price,
														'data.drugInfo.specification' : result.obj.drugInfo.specification
														
									});
									
								}
						listType();
					}
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
					//$('#workTime').html('');
					//$('#afterWorkTime').html('');
					//$('#isLeave').html('');
				}
			}, 'json');
		} 
	};
	 
	 /* var getAttrUnitComboxById = function(id) {
    	 //alert(id)
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
    }; */
    
    var listType = function(){
    	//$(':input[name="data.id"]').val(1)
    	console.log($(':input[name="data.drugInfo.drugCode"]').val());
    	var medicalId = $(':input[name="data.drugInfo.drugCode"]').val();
        if ( medicalId > 0) 
        {
    	 
        datagrid = $("#datagrid").datagrid({
        	url : sy.contextPath + '/app/drug-record!grid.sy?opMod=SELF',
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
           	 
             { field: 'drugInfo', title: '药品信息', width: 100,
            	   formatter:function(row){
            		    return row.drugName;
                   }
              },
              {field: 'drugTimes', title: '批次', width: 60, sortable: true},
              {field: 'num', title: '数量', width: 60, sortable: true},
              {field: 'drugInfo', title: '规格', width: 100, sortable: true,formatter:function(row){return row.specification;}},
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
              {field: 'createTime', title: '入库时间', width: 120, sortable: true }
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
		var drugCodeCombogrid = $('#drugCodeCombogrid').combogrid({
			url : sy.contextPath + '/app/drug-info!doNotNeedSecurity_getMainMenu.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'drugCode',
			textField : 'drugName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'drugCode',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'drugCode',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'drugName',
				title : '药品名称',
				width : 100,
				sortable : true
			}, {
				field : 'drugDesc',
				title : '药品描述',
				width : 150,
				sortable : true
			}, {
				field : 'expireTime',
				title : '过期时间',
				width : 150,
				sortable : true
			}, {
				field : 'specification',
				title : '规格',
				width : 150,
				sortable : true
			} ] ],
			onSelect:function(record){
				//console.log(record);
				var g = $('#drugCodeCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId )
				if(r.drugCode != null){
					$(':input[name="data.drugInfo.specification"]').val(r.specification);
				}
			}
			
		});
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    	
<%--     var attrUnitCombox = $('#attrUnitCombox').combobox({
		url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=TEST',
        idField:'id',
        valueField:'id',
        textField:'text',
        parentField:'pid'
      });
	   --%>
    //~~~~~~~~~~~~~~~~~~~~~~~~~
	});
</script>

 

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	<div class="easyui-panel" title="药品入库"  style="height: 526px" data-options="fit:true,border:false" >
		<div style="padding: 10px 0 10px 10px;"   >
			<table>
			     <tr>
					<td>
						<div class="easyui-panel" title="入库操作" style="height: 140px;width: 700px">
							<div style="padding: 10px 0 10px 10px">
								<form id="form" method="post">
									<table>
										<tr>
											<td>编号:</td>
											<td><input class="easyui-validatebox" type="text" 
												name="data.id" readonly="readonly"></input></td>
											<th>药品选择</th>
											<td><input id="drugCodeCombogrid" value="<%=id%>"
												name="data.drugInfo.drugCode" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;"></td>
											</td>
										</tr>
										<tr>
											<th>数量</th>
											<td><input name="data.num" class="easyui-numberbox"
												max="99999.99" type="text" style="width: 130px;"
												data-options="required:true"> 最大 99999.99</td>
											</td>
											<th>规格</th>
											<td><input name="data.drugInfo.specification"
												class="easyui-validatebox" type="text" style="width: 130px;"
												readonly="readonly"></td>
											</td>
											<th>单价</th>
											<td><input name="data.price" class="easyui-numberbox"
												type="text" precision="2" max="99999.99" size="8"
												maxlength="8" style="text-align: right;"
												style="width: 130px;" data-options="required:true">元
											</td>
											<th>批次</th>
											<td><input name="data.drugTimes" class="easyui-validatebox"
												type="text"  max="99999" size="10"
												maxlength="10" style="text-align: right;"
												style="width: 130px;" data-options="required:true">
											</td>
										</tr>
									</table>
								</form>
								 
							</div>
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-note_add',plain:true"
									onclick="listMyRecord_CC();">查询历史入库</a> 
									<a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_BASE('rk')">入库</a>   
								 <a href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_BASE('pd')">盘点</a>   
							</div>
						</div>
					</td>
				</tr>  
				
				<tr>
					<td>
						<div class="easyui-panel" title="历史入库信息"   >
							<!-- <div hidden="true">
								<select id="attrUnitCombox"  hidden="true" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
							</div> -->
							<div data-options="region:'center',fit:true,border:false" style="height: 330px">
								  <form id="dataform" style="height:100%">
									<table id="datagrid" data-options="fit:true,border:false"></table>
								</form>  
							</div>
							
							<!-- <div style="text-align: center; padding: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm_TG()">保存</a>
							</div> -->
						</div>
					</td>
				</tr>
				
				 
				
			</table>

		</div>
	</div>


	 
	 
</body>
</html>