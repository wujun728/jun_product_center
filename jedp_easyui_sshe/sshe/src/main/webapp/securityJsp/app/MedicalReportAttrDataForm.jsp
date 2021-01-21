<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%
SecurityUtil securityUtil = new SecurityUtil(session);
	String contextPath = request.getContextPath();
%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
	//templateId='+templateId+"&templateName="+templateName+"&opOrder="+opOrder+"&custUserId="+custUserId)
	
	String templateId = request.getParameter("templateId");
	String templateName = request.getParameter("templateName");
	String opOrder = request.getParameter("opOrder");
	String custUserId = request.getParameter("custUserId");
	
	//templateId = "10";
	System.out.println("templateId "+templateId);
	System.out.println("templateName "+templateName);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<%-- 引入javascript--%>
<script src="<%=contextPath%>/jslib/module/app/tjReportAttrData.js" type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	var grid;
	 
	var phyKeshiCombox;
	var attrCheckCombox;
	var isIndicatorsCombox;
	var attrUnitCombox;
	var IsAbnormalCombox;
	
	var sy = sy || {};
	
	sy.checkGridValid = function($pjq){
		var t = $('#grid');
		var rowss = t.treegrid('getSelections');
		if(rowss.length <=0){
			$pjq.messager.alert('提示', '没有选择要提交的数据 !!', 'error');
			return false;
		}
		var returnValue = true;
		$.each(rowss, function(i,val){      
			var o = {};
			//alert(val.isIndicators)
		     if(val.isIndicators == '10105101'){
		    	 //数值属性
		    	 if(val.attrValue=='' ||  val.attrValue == undefined){
		    		 $pjq.messager.alert('提示', '数值不能为空 !!', 'error');
		    		 returnValue = false;
		    	 }
		     }
		     if(val.isIndicators == '10105102'){
		    	 //描述类型
		    	 if(val.attrDesc=='' ||  val.attrDesc == undefined){
		    		 $pjq.messager.alert('提示', '描述不能为空 !!', 'error');
		    		 returnValue = false;
		    		 return false;
		    	 }
		     }
		     //找每行的儿子
		     var childrows = t.treegrid('getChildren',val.attrId);
		     for(var i=0; i<childrows.length; i++){
		    	  //var r = childrows[i].attr("checked");
		    	 // console.log("checked:" + r);
		    	 t.treegrid('select',childrows[i].attrId);
		     }
		     //getParent
		     var parentNode = t.treegrid('getParent',val.attrId);
		     if(parentNode != null)
		     t.treegrid('select',parentNode.attrId);
		  });
		//alert('返回  returnValue ');
		return returnValue;
	}
	sy.serializeTreeGrid = function($row,form) {
		var data = {};
		
		var t = $('#grid');
		var rowss = t.treegrid('getSelections');
		//alert("$row.custUser.userId "+ $row.custUser.userId)
		//alert($row.medicalReportDef.templateId)
		data['total']=rowss.length;
		var rows = [];
		$.each(rowss, function(i,val){      
			var o = {};
		     // t.treegrid('endEdit', val.attrId);
		     //console.log(val.medicalReportAttr);
		      
		     //alert('val.attrValue' + val.attrValue )
			 o['attrId'] = val.attrId;
			 o['attrName'] = val.attrName;
			 o['attrDesc'] = val.attrDesc;
			 o['attrValue'] = val.attrValue;
			 o['custUser.userId'] = $row.custUser.userId;
			 o['opOrder'] = $row.opOrder;
			 o['medicalTime'] = $row.medicalTime;
			 o['medicalType'] = $row.medicalType;
			 o['templateId'] = $row.medicalReportDef.templateId;
			 o['attrKeshi'] = val.attrKeshi;
			 o['attrCankao'] =val.attrCankao;
			 o['attrCheck'] =val.attrCheck;
			 o['isIndicators'] =val.isIndicators;
			 o['attrUnit'] =val.attrUnit;
			 o['isAbnormal'] =val.isAbnormal;
			 o['medicalReportAttr.attrId'] = val.medicalReportAttr != undefined ? val.medicalReportAttr.attrId : '' ;
			 rows[i] = o;
		  });
		 data['rows']=rows;
		/*$.each(form.serializeArray(), function(index) {
			alert(this['name'] +'---' + this['value'])
			if (this['value'] != undefined && this['value'].length > 0) {// 如果表单项的值非空，才进行序列化操作
				if (o[this['name']]) {
					
					o[this['name']] = o[this['name']] + "," + this['value'];
					//alert(o[this['name']])
				} else {
					
					o[this['name']] = this['value'];
					//alert(o[this['name']])
				}
			}
		});*/
		//alert(JSON.stringify(data))
		return JSON.stringify(data);
		//return data;
	};

	
	// var uploader;//上传对象
	var submitNow = function($dialog,$row, $grid, $pjq) {
		var url;
		var rows = $('#grid').treegrid('getSelections');
		if (rows.length > 0) {
			url = sy.contextPath + '/app/medical-detail-data!update.sy';
		} else {
			url = sy.contextPath + '/app/medical-detail-data!save.sy';
		}
		url = sy.contextPath + '/app/medical-detail-data!saveList.sy';
		 var data = sy.serializeTreeGrid($row,$('#dataform'));
		var params = {'params':data};
	     if(!sy.checkGridValid($pjq)){
	    	// alert('数据校验出错 ！！');
	    	 return ;
	     }
	    // alert('准备提交数据！！');
	    // console.log(data)
	     $.post(url, params, function(result) {
			parent.sy.progressBar('close');//关闭上传进度条
			if (result.success) {
				$pjq.messager.alert('提示', result.msg, 'info');
				$grid.treegrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');  
	};
	var submitForm = function($dialog,$row, $grid, $pjq) {
		if ($('form').form('validate')) {
			/* if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						submitNow($dialog, $grid, $pjq);
					}
				});
			} else {
				submitNow($dialog, $grid, $pjq);
			}
			 */
			submitNow($dialog, $row,$grid, $pjq);
		}
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
	var getIsIndicatorsComboxData = function(){
		var data = $("#isIndicatorsCombox").combobox('getData');
	 		//alert(data)
		return data;
	} 
	
	var getAttrUnitComboxById = function(id) {
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
	
	var editingId;
	var editGrid = function () {
		if (editingId != undefined) {
			$('#grid').treegrid('select', editingId);
			return;
		}
		 
		/* var row = $('#grid').treegrid('getSelected');
		if (row) {
			editingId = row.attrId
			$('#grid').treegrid('beginEdit', editingId);
		} */
		
		var rows = $('#grid').treegrid('getSelections');
		$.each(rows, function(i,val){      
		      $('#grid').treegrid('beginEdit', val.attrId);
		  });
	}
	function saveGrid() {
		var t = $('#grid');
		var rows = t.treegrid('getSelections');
		$.each(rows, function(i,val){      
		      t.treegrid('endEdit', val.attrId);
		  });
		t.treegrid('selectAll');
		
		/* if (editingId != undefined) {
			var t = $('#grid');
			t.treegrid('endEdit', editingId);
			editingId = undefined;
			t.treegrid('unselectAll');
			
			  var persons = 0;
			var rows = t.treegrid('getChildren');
			for ( var i = 0; i < rows.length; i++) {
				var p = parseInt(rows[i].persons);
				if (!isNaN(p)) {
					persons += p;
				}
			}
			var frow = t.treegrid('getFooterRows')[0];
			frow.persons = persons;
			t.treegrid('reloadFooter'); 
			
		} */
	}
	 function removeGrid(){
		  
		 var rows = $('#grid').treegrid('getSelections');
			$.each(rows, function(i,val){      
			      $('#grid').treegrid('remove', val.attrId);
			 });
	}
	 
	 
	function cancelGrid() {
		 
		/* if (editingId != undefined) {
			$('#grid').treegrid('cancelEdit', editingId);
			editingId = undefined;
		} */
		
		$('#grid').treegrid('load',sy.serializeObject($('#searchForm')));
		
		$('#grid').treegrid('unselectAll');
	}

	$(function() {
		
		
		attrUnitCombox = $('#attrUnitCombox').combobox({
			url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT',
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
		 grid = $('#grid').treegrid({
				title : '',
				url : sy.contextPath + '/app/report-attr!treeGrid.sy?templateId='+<%=templateId%>,
				striped : false,
				treeField : 'attrName',
				parentField : 'pid',
				rownumbers : true,
				pagination : true,
				singleSelect : false,
				idField : 'attrId',
				sortName : 'createTime',
				sortOrder : 'desc',
				pageSize : 50,
				pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
				frozenColumns : [ [ 
				                     {
						     checkbox:true,
							width : '100',
							title : '选择',
							field : 'attrId',
							sortable : true 
						} 
					,{
					width : '100',
					title : '属性名称',
					field : 'attrName',
					sortable : true
				} ] ],
				columns : [ [{
					width : '80',
					title : '所属科室',
					field : 'attrKeshi',
					sortable : true,
					formatter : function(value, row, index) {
						return getPhyKeshiComboxById(value);
					}<%-- ,
					editor:{type:'combobox',
						    options:{
							valueField:'id',
							textField:'text',
							url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_VALUE_TYPE',
							required:true
							}
					} --%>
					
				},
				 {
					width : '80',
					title : '模板',
					field : 'medicalReportDef.templateId',
					hidden: true,
					sortable : true,
					formatter : function(value, row, index) {
						return row.medicalReportDef.templateId;
					}
				},  
				{
					width : '80',
					title : '上级属性',
					field : 'medicalReportAttr',
					sortable : true,
					formatter : function(value, row, index) {
						//alert(value)
						if (value != undefined) {
							return value.attrId;
						}else{
							return '';
						}
					}
				},  
				{
					width : '80',
					title : '模板',
					field : 'medicalReportDef.templateName',
					sortable : true,
					formatter : function(value, row, index) {
						//alert(row)
						//console.log(row)
						return row.medicalReportDef.templateName;
					}
				},
				{
					width : '80',
					title : '检查类型',
					field : 'attrCheck',
					sortable : true,
					formatter : function(value, row, index) {
						return getAttrCheckComboxById(value);
					}
				},   {
					width : '80',
					title : '数值类型',
					field : 'isIndicators',
					sortable : true,
					formatter : function(value, row, index) {
						return getIsIndicatorsComboxById(value);
					},
					editor: {
						
					}
				},{
					width : '150',
					title : '参考值',
					field : 'attrCankao',
					sortable : true
				}, {
					width : '80',
					title : '单位',
					field : 'attrUnit',
					sortable : true,
					formatter : function(value, row, index) {
						return getAttrUnitComboxById(value);
					}
				},  {
					width : '150',
					title : '数值',
					field : 'attrValue',
					sortable : true,
					editor: 'text'
				},  {
					width : '150',
					title : '是否异常',
					field : 'isAbnormal',
					sortable : true,
					formatter:function(row){
							//console.log('UUUU    ');
	            		    //console.log(row)
	            		   if(row != undefined ){
	            			   if(row.phyId != undefined){
	                			   return  getIsAbnormalComboxById(row.phyId);  
	                		   }else{
	                			   return  getIsAbnormalComboxById(row);
	                		   } 
	            		   }
	                   },
	                  editor: { type: 'combotree', options: {
	                	  	required: true , 
	                	  	editable:true,
	                	  	missingMessage: '请选择是否异常类型定义...',
	                	  	idField:'id',
	                	  	valueField:'id',
	                	  	textField:'text',
	                	  	parentField:'pid',
	                	  	url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_ISABNORMAL_TYPE',
	    				    panelHeight: 'auto',
	    				    formatter:function(row){
	    		        		   return    row.text;
	    		               }
	    				  }
	                  }
				}, {
					width : '150',
					title : '描述',
					field : 'attrDesc',
					sortable : true,
					editor: 'text'
				},  {
					width : '150',
					title : '创建时间',
					field : 'createTime',
					sortable : true,
					hidden:true
				}, {
					width : '150',
					title : '修改时间',
					field : 'updateTime',
					sortable : true,
					hidden:true
				} ,  {
					title : '操作',
					field : 'action',
					width : '180',
					hidden:true,
					formatter : function(value, row) {
						var str = '';
						if(checkRoleUsersHasPriv_getById()){
							var bt = systool.createFuncButton('ext-icon-note','查看','查看属性','sys.showUserDataFun(\'{0}\');');
							str +=  sy.formatString(bt,row.attrId);
						}
						if(checkRoleUsersHasPriv_update()){
							var bt = systool.createFuncButton('ext-icon-note','编辑','编辑属性','sys.editUserDataFun(\'{0}\');');
							str +=  sy.formatString(bt,row.attrId);
						}
						if(checkRoleUsersHasPriv_delete()){
							var bt = systool.createFuncButton('ext-icon-note','删除','删除属性','sys.removeUserDataFun(\'{0}\');');
							str +=  sy.formatString(bt,row.attrId);
						}
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
				onClickRow:function(row){  
                    //级联选择  
                    $(this).treegrid('cascadeCheck',{  
                        id:row.attrId, //节点ID  
                        deepCascade:true //深度级联  
                    });  
                }
			});
		
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:false,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								    
								<td>体检科室</td>
								<td>
									<select id="phyKeshiCombox" name="QUERY_t#attrKeshi_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#phyKeshiCombox').combotree('clear');" title="清空" />
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
								</td>
								</tr>
								<tr>
								<td>单位</td>
								<td>
									<select id="attrUnitCombox" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
								</td>
								<td>是否异常</td>
								<td>
									<select id=IsAbnormalCombox name="QUERY_t#isAbnormal_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#IsAbnormalCombox').combotree('clear');" title="清空" />
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
									onclick="grid.treegrid('load',sy.serializeObject($('#searchForm')));">过滤</a><a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-zoom_out',plain:true"
									onclick="$('#searchForm input').val('');grid.treegrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<%-- <%
								if (securityUtil.havePermission("/app/medical-detail!save")) {
							%>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="addFun();">添加</a></td>
							<%
								}
							%> --%>
							
							<td><div class="datagrid-btn-separator"></div></td>
							 <td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="editGrid();">编辑</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="saveGrid();">确定</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="removeGrid();">删除</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton"
								data-options="iconCls:'ext-icon-note_add',plain:true"
								onclick="cancelGrid();">取消</a></td>
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
	
	
 	<div data-options="region:'center',fit:true,border:false">
 		<form id="dataform" style="height:100%">
		<table id="grid" data-options="fit:true,border:false" ></table>
		</form>
	</div> 
 	
</body>
</html>