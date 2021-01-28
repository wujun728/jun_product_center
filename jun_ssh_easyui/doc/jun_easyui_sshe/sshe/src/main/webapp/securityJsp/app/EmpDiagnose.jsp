<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.util.base.SecurityUtil"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%@ page import="sy.util.base.ConfigUtil"%>
<%@ page import="sy.util.base.StringUtil"%>
<%
	//员工就诊
	String contextPath = request.getContextPath();
	//SecurityUtil securityUtil = new SecurityUtil(session);
	String userId = request.getParameter("userId");
	String userName = StringUtil.changeCharset(request.getParameter("userName"),"UTF-8");
	String id = request.getParameter("id");
/* 	SessionInfo sessionInfo = (SessionInfo) request.getSession()
			.getAttribute(ConfigUtil.getSessionInfoName());
	String userId = sessionInfo.getUserId();
	String userName = sessionInfo.getUser().getName(); */
	
	SecurityUtil securityUtil = new SecurityUtil(session);
	
	System.out.println(">>>>>>" + id);
	//if(id == null) id = "";
	//if(userName == null) userName = "";
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
	var fzdatagrid;
	var drugdatagrid;
	var hisdatagrid;
	
    var editRow = undefined; //定义全局变量：当前编辑的行
    var fzeditRow = undefined; //定义全局变量：当前编辑的行
    var drugeditRow = undefined; //定义全局变量：当前编辑的行
    
		
    var inputBox = function(op,comp){
    	var dialog = parent.sy.modalDialog({
			title : '自定义',
			url : sy.contextPath + '/securityJsp/app/InputForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, comp, parent.$);
				}
			} ]
		});
    }
	var listMyRecord_CC = function() {
		//userId = 2;
		//var url = sy.contextPath + '/securityJsp/app/EmpDiagnoseList.jsp?userId='+userId;
		//window.location.href=url;
		
		var userId = 0 ;
			<%if(userId == null && id == null) {%>
			var userIdCombogrid = $('#userIdCombogrid').combogrid('grid');	// get datagrid object
			var userIdCombogridrow = userIdCombogrid.datagrid('getSelected');	// get the selected row
				userId =userIdCombogridrow.userId;
			<%} %>
			<%if( (userId != null&& !userId.isEmpty()) || id !=null) {%>
			//alert('dddddd');
				userId = 	$(':input[name="data.custUser.userId"]').val();
			<%} %>
			//alert('listMyRecord_CC' + userId)
			//userId = 2;
			//alert($(':input[name="data.custUser.userId"]').val())
			
		var url = sy.contextPath + '/securityJsp/app/EmpDiagnoseList.jsp?userId='+userId;
		window.location.href=url;
	};
	
	var sy = sy || {};
	
	sy.checkGridValid = function(t){
		//var t = $('#datagrid');
		var rowss = t.datagrid('getSelections');
		if(rowss.length <=0){
			parent.$.messager.alert('提示', '没有选择要提交的数据 !! 提示： 修改的数据先点击确定后，再选择数据行，然后保存', 'error');
			return false;
		}
		var returnValue = true;
		$.each(rowss, function(i,val){      
			var o = {};
			//alert(val.isIndicators)
			console.log(val)
		     if(val.description == ''){
		    	 //数值属性
		    	 if(val.attrValue=='' ||  val.attrValue == undefined){
		    		 parent.$.messager.alert('提示', '数值不能为空 !!', 'error');
		    		 returnValue = false;
		    	 }
		     }
			if(val.physicalTypeDef == '' ||  val.physicalTypeDef == undefined){
		    		 parent.$.messager.alert('提示', '没有选择要提交的数据 !! 提示： 修改的数据先点击确定后，再选择数据行，然后保存', 'error');
		    		 returnValue = false;
		     }
		     //找每行的儿子
		     /* var childrows = t.datagrid('getChildren',val.attrId);
		     for(var i=0; i<childrows.length; i++){
		    	  //var r = childrows[i].attr("checked");
		    	 // console.log("checked:" + r);
		    	 t.datagrid('select',childrows[i].attrId);
		     } */
		     //getParent
		     /* var parentNode = t.datagrid('getParent',val.attrId);
		     if(parentNode != null)
		     t.datagrid('select',parentNode.attrId); */
		  });
		return returnValue;
	}
	sy.serializeTreeGrid = function(form,t) {
		var data = {};
		
		//var t = $('#datagrid');
		var rowss = t.datagrid('getSelections');
		//alert("$row.custUser.userId "+ $row.custUser.userId)
		//alert($row.medicalReportDef.templateId)
		data['total']=rowss.length;
		//alert('rowss.length : ' + rowss.length)
		var rows = [];
		$.each(rowss, function(i,val){      
			var o = {};
		     // t.treegrid('endEdit', val.attrId);
		     console.log(val);
		      
		     //alert('val.attrValue' + val.attrValue )
		     o['id'] = val.id;
		     o['medicalId'] = $(':input[name="data.medicalId"]').val();
			 o['phyTypeList'] = val.physicalTypeDef;
			 o['description'] = val.description;
			 o['ss'] = val.ss;
			 o['stype'] = val.stype;
			 
			 rows[i] = o;
		  });
		 data['rows']=rows;
		 
		//alert(JSON.stringify(data))
		return JSON.stringify(data);
		//return data;
	};
	
	var submitForm_TG = function(){
		if ($('dataform').form('validate')) {
			var url;
			
			if ($(':input[name="data.medicalId"]').val() > 0) {
				url = sy.contextPath + '/app/emp-diagnose-record-detail!saveList.sy';
			} else {
				//url = sy.contextPath + '/app/emp-diagnose-record!save.sy';
				
				parent.$.messager.alert('提示', '没有就诊编号，请先填写主诉信息!!', 'info');
				return ;
			}
			url = sy.contextPath + '/app/emp-diagnose-record-detail!saveList.sy';
			//console.log(url)
			var data = sy.serializeTreeGrid($('#dataform'),$('#datagrid'));
			var params = {'params':data};
			
			//console.log(data)
			//alert('返回  data ' + data);
		     if(!sy.checkGridValid($('#datagrid'))){
		    	  //alert('数据校验出错 ！！');
		    	 return ;
		     }
		     //alert('开始提交 ' );
			$.post(url, params, function(result) {
				if (result.success) {
					 
					parent.$.messager.alert('提示', result.msg, 'info');
					console.log("clearSelections");
		            datagrid.datagrid("clearSelections");
		            datagrid.datagrid("unselectAll");
		            datagrid.datagrid('reload');
					 
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
				 	
				}
			}, 'json');
		}
	};
	
	
	var submitForm_FZ = function(){
		if ($('fzdataform').form('validate')) {
			var url;
			
			if ($(':input[name="data.medicalId"]').val() > 0) {
				url = sy.contextPath + '/app/emp-diagnose-record-detail!saveList.sy';
			} else {
				//url = sy.contextPath + '/app/emp-diagnose-record!save.sy';
				
				parent.$.messager.alert('提示', '没有就诊编号，请先填写主诉信息!!', 'info');
				return ;
			}
			url = sy.contextPath + '/app/emp-diagnose-record-detail!saveList.sy';
			//console.log(url)
			var data = sy.serializeTreeGrid($('#fzdataform'),$('#fzdatagrid'));
			var params = {'params':data};
			
			
			//alert('返回  data ' + data);
		     if(!sy.checkGridValid($('#fzdatagrid'))){
		    	  //alert('数据校验出错 ！！');
		    	 return ;
		     }
		     console.log(params)
			$.post(url, params, function(result) {
				if (result.success) {
					 
					parent.$.messager.alert('提示', result.msg, 'info');
					console.log("clearSelections");
		            datagrid.datagrid("clearSelections");
		            datagrid.datagrid("unselectAll");
		            datagrid.datagrid('reload');
					 
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
				 	
				}
			}, 'json');
		}
	}
	var gotoDiagnose = function(id) {
		var url = sy.contextPath + '/securityJsp/app/EmpDiagnose.jsp?id='+id;
		window.location.href=url;
	};
	var submitForm_BASE = function($op) {
		//console.log('dddd') 
	    
		
		if ($(':input[name="data.custUser.userId"]').val().length <= 0) {
			parent.$.messager.alert('提示', '请选择员工 !!', 'error');
			return;
		} 
		
		if ($(':input[name="data.diagTime"]').val().length <= 0) {
			parent.$.messager.alert('提示', '请选择就诊时间 !!', 'error');
			return;
		} 
		 
		//alert($('baseform').form('validate'))
		//return;
		if ($('baseform').form('validate')) {
			var url;
						
			if ($(':input[name="data.medicalId"]').val() > 0) {
				url = sy.contextPath + '/app/emp-diagnose-record!update.sy';
			} else {
				url = sy.contextPath + '/app/emp-diagnose-record!save.sy';
			}
			console.log(url)
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					//$grid.treegrid('reload');
					//$dialog.dialog('destroy');
					//console.log('success 上班时间: ');
					// console.log(result.obj.sbTime);
					//$(':input[name="data.id"]').val(result.obj.id);
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						//alert(result.obj.medicalId);
						if (result.obj.medicalId != undefined) {
									/* 
									此处不加载局部数据了， 新的就诊记录产生后，就初始化整个就诊页面。
									$('form').form('load',{
														'data.medicalId' : result.obj.medicalId,
														'data.diagTime' : result.obj.diagTime,
														'data.sgiNo' : result.obj.sgiNo,
														'data.mainInfo' : result.obj.mainInfo,
														'data.ext1' : result.ext1,
														'data.custUser.userId' : result.custUser.userId,
														'data.custUser.userName' : result.custUser.userName,
														'data.propose' : result.propose,
														'data.firstStep' : result.firstStep
														
									}); */
									gotoDiagnose(result.obj.medicalId);
								}
					}
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('fail');
					//$('#workTime').html('');
					//$('#afterWorkTime').html('');
					//$('#isLeave').html('');
				}
			}, 'json');
		}else{
			parent.$.messager.alert('提示', '表单异常!!', 'error');
		}
	};
	
	// -------------提交用药记录
	sy.checkGridValid_YAO = function(){
		console.log("checkGridValid_YAO.......")
		var t = $('#drugdatagrid');
		var rowss = t.datagrid('getSelections');
		if(rowss.length <=0){
			parent.$.messager.alert('提示', '没有选择要提交的数据 !! 提示： 修改的数据先点击确定后，再选择数据行，然后保存 !!', 'error');
			return false;
		}
		var returnValue = true;
		$.each(rowss, function(i,val){      
			var o = {};
			//alert(val.isIndicators)
			console.log(val)
		     if(val.drugTimes == '' || val.drugTimes == undefined  ){
		    	 //数值属性
		    	 if(val.drugTimes=='' ||  val.drugTimes == undefined){
		    		 parent.$.messager.alert('提示', '数值不能为空 !!', 'error');
		    		 returnValue = false;
		    	 }else if (val.drugTimes.id < 0 ){
		    		 parent.$.messager.alert('提示', '药品不能为空 !!', 'error');
		    		 returnValue = false;
		    	 }
		     }
		     
		     //找每行的儿子
		     /* var childrows = t.datagrid('getChildren',val.attrId);
		     for(var i=0; i<childrows.length; i++){
		    	  //var r = childrows[i].attr("checked");
		    	 // console.log("checked:" + r);
		    	 t.datagrid('select',childrows[i].attrId);
		     } */
		     //getParent
		     /* var parentNode = t.datagrid('getParent',val.attrId);
		     if(parentNode != null)
		     t.datagrid('select',parentNode.attrId); */
		  });
		console.log("checkGridValid_YAO.......end")
		return returnValue;
	}
	sy.serializeTreeGrid_YAO = function(form) {
		var data = {};
		
		var t = $('#drugdatagrid');
		var rowss = t.datagrid('getSelections');
		//alert("$row.custUser.userId "+ $row.custUser.userId)
		//alert($row.medicalReportDef.templateId)
		data['total']=rowss.length;
		//alert('rowss.length : ' + rowss.length)
		var rows = [];
		$.each(rowss, function(i,val){      
			var o = {};
		     // t.treegrid('endEdit', val.attrId);
		     console.log(val);
		      
		     //alert('val.attrValue' + val.attrValue )
		     o['id'] = val.id;
		     o['medicalId'] = $(':input[name="data.medicalId"]').val();
		     o['drugTimes'] = val.drugTimes;
		     o['drugName'] =  '';
		     o['price'] = val.price;
		     o['number'] = val.number;
		     o['medicalFee'] = val.medicalFee;
		     o['empTakeFee'] =val.empTakeFee;
		     o['insuranceFee'] =val.insuranceFee;
		     o['ebType'] =val.ebType;
		     o['ext1'] = val.ext1;
			 o['ss'] = val.ss;
			 rows[i] = o;
		  });
		 data['rows']=rows;
		 
		//alert(JSON.stringify(data))
		return JSON.stringify(data);
		//return data;
	};
	
	var submitForm_YAO = function(){
		if ($('drugdataform').form('validate')) {
			var url;
			
			if ($(':input[name="data.medicalId"]').val() > 0) {
				url = sy.contextPath + '/app/emp-drug-use-record!saveList.sy';
			} else {
				parent.$.messager.alert('提示', '没有就诊编号，请先填写主诉信息!!', 'info');
				return ;
			}
			url = sy.contextPath + '/app/emp-drug-use-record!saveList.sy';
			//console.log(url)
			var data = sy.serializeTreeGrid_YAO($('#drugdataform'));
			var params = {'params':data};
			
			console.log("提交的数据: " + data)
			//alert('返回  data ' + data);
		     if(!sy.checkGridValid_YAO()){
		    	  alert('数据校验出错 ！！');
		    	 return ;
		     }
		     
			$.post(url, params, function(result) {
				if (result.success) {
					console.log('drugdatagrid success !!');
					parent.$.messager.alert('提示', result.msg, 'info');
					console.log("clearSelections");
		            drugdatagrid.datagrid("clearSelections");
		            drugdatagrid.datagrid("unselectAll");
		            drugdatagrid.datagrid('reload');
					 
				} else {
					parent.$.messager.alert('提示', result.msg, 'error');
					console.log('drugdatagrid fail !!');
				 	
				}
			}, 'json');
		}
	}
	// ------------------- 用药记录 结束 ~~~~~~~~~~~~~~~~~~~~
	var checkIllType = function(){
		$('[name="data_suspectedCheckbox"]:checked').each(function(){
				 if ("yes" == $(this).attr("value")) {
    				// alert( $(this).attr('value') );
    				if($(':input[name="data_illType"]').attr('value') == undefined 
    						|| $(':input[name="data_illType"]').attr('value') == ''){
    					//parent.$.messager.alert('提示', "请选择疑似疾病的类型...", 'info');
    					return false;
    				}else{
    					 //suspected = true;
    					 //$(':input[name="data_illType"]').attr('value');
    				}
			}
		});
		
		return true;
	}
	var submitForm_CC = function($op) {
		if ($('form').form('validate')) {
			var url;
	 		
			console.log("checkbox:" + $('[name="data_suspectedCheckbox"]').attr('value'))
			console.log("checkbox:" + $(':input[name="data_illType"]').val())
			console.log("checkbox:" + $('#data_suspectedCheckbox').attr("checked"))
			
			if(!checkIllType()){
				
				return ;
			}
			
			
			if ($(':input[name="data.medicalId"]').val() > 0) {
				url = sy.contextPath + '/app/emp-diagnose-record!update.sy';
			} else {
				url = sy.contextPath + '/app/emp-diagnose-record!save.sy';
			}
			//console.log(url)
			$.post(url, sy.serializeObject($('form')), function(result) {
				if (result.success) {
					//$grid.treegrid('reload');
					//$dialog.dialog('destroy');
					//console.log('success 上班时间: ');
					// console.log(result.obj.sbTime);
					//$(':input[name="data.id"]').val(result.obj.id);
					
					parent.$.messager.alert('提示', result.msg, 'info');
					
					if (result.obj != null){
						alert(result.obj.medicalId);
						if (result.obj.medicalId != undefined) {
									$('form').form('load',{
														'data.medicalId' : result.obj.medicalId,
														'data.diagTime' : result.obj.diagTime,
														'data.sgiNo' : result.obj.sgiNo,
														'data.mainInfo' : result.obj.mainInfo,
														'data.ext1' : result.ext1,
														'data.custUser.userId' : result.custUser.userId,
														'data.custUser.userName' : result.custUser.userName,
														'data.propose' : result.propose,
														'data.firstStep' : result.firstStep
														
									});
									
								}
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

	 var getAttrUnitComboxById = function(id) {
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
    };
    
    var getAttrFZUnitComboxById = function(id) {
   	 //alert(id)
   	 var data = $("#attrFZUnitCombox").combobox('getData');
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
   
   var getAttrEBUnitComboxById = function(id) {
	   	 //alert(id)
	   	 var data = $("#attrEBUnitCombox").combobox('getData');
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
	   
    var listType = function(){
    	
    	//alert("listType")
    	var medicalId = $(':input[name="data.medicalId"]').val();
    	//alert(medicalId);
        if ( medicalId > 0) 
        {
    	  
        datagrid = $("#datagrid").datagrid({
        	url : sy.contextPath + '/app/emp-diagnose-record-detail!grid.sy?medicalId='+medicalId+'&opMod=TG',
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
           	 {field: 'id', title: '编号', width: 100, sortable: true },
           	 
             { field: 'physicalTypeDef', title: '体格类型名称', width: 100,
            	   formatter:function(row){
            		   //console.log(row)
            		  // alert("UUUU " + row)
            		   if(row != undefined ){
            			   if(row.phyId != undefined){
                			   return  getAttrUnitComboxById(row.phyId);  
                		   }else{
                			   return  getAttrUnitComboxById(row);
                		   } 
            		   }
            		   
                   },
                  editor: { type: 'combotree', options: {
                	  	required: true , 
                	  	editable:true,
                	  	missingMessage: '请选择体格类型定义...',
                	  	idField:'id',
                	  	valueField:'id',
                	  	textField:'text',
                	  	parentField:'pid',
                	  	url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=TG_TYPE',
    				    panelHeight: 'auto',
    				    formatter:function(row){
    		        		   return    row.text;
    		               }
    				  } 
                  }
              },
              {field: 'description', title: '结果', width: 200, sortable: true ,
            	  editor: {
                      type: 'validatebox',
                      options: {
                          required: true,
                          missingMessage: '请输入结果......'
                      }
                  }
              },
              {field: 'ss', title: 'ss', width: 200, sortable: true , hidden:true },
              {field: 'stype', title: 'stype', width: 200, sortable: true , hidden:true }
              
               ]],
             
            toolbar: [{ text: '添加', iconCls: 'icon-add', handler: function () {//添加列表的操作按钮添加，修改，删除等
                //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
                if (editRow != undefined) {
                    datagrid.datagrid("endEdit", editRow);
                }
                var CurIndex = datagrid.datagrid('getRows').length+1;  
            //alert(editRow);
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (editRow == undefined) {
                    datagrid.datagrid("appendRow", {
                        	id : CurIndex,
							ss : 'I',
							stype : 'TG'
                    });
                    var lastIndex = datagrid.datagrid('getRows').length - 1; 
                    //将新插入的那一行开户编辑状态
                    datagrid.datagrid('selectRow', lastIndex);  
                    datagrid.datagrid('beginEdit', lastIndex);  
                    //给当前编辑的行赋值
                    editRow = lastIndex;
                }

            }
            }, '-',
             { text: '删除', iconCls: 'icon-remove', handler: function () {
                 //删除时先获取选择行
                 var rows = datagrid.datagrid("getSelections");
                 //选择要删除的行
                 if (rows.length > 0) {
                     $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                         if (r) {
                             var ids = [];
                             for (var i = 0; i < rows.length; i++) {
                                 ids.push(rows[i].id);
                             }
                             //将选择到的行存入数组并用,分隔转换成字符串，
                             //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                             alert(ids.join(','));
                             var deleteIds = {'ids' : ids.join(',')};
                             var url = sy.contextPath + '/app/emp-diagnose-record-detail!deleteIds.sy';
                             $.post(url, deleteIds, function(result) {
                 				if (result.success) {
                 					parent.$.messager.alert('提示', result.msg, 'info');
                 					
                 					if (result.obj != null){
                 						//alert(result.obj.medicalId);
                 						
                 					}
                 				} else {
                 					parent.$.messager.alert('提示', result.msg, 'error');
                 					console.log('fail');
                 				}
                 			}, 'json');
                             
                         }
                     });
                 }
                 else {
                     $.messager.alert("提示", "请选择要删除的行", "error");
                 }
             }
             }, '-',
             { text: '修改', iconCls: 'icon-edit', handler: function () {
                 //修改时要获取选择到的行
                 var rows = datagrid.datagrid("getSelections");
                 //如果只选择了一行则可以进行修改，否则不操作
                 if (rows.length == 1) {
                     //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                     if (editRow != undefined) {
                         datagrid.datagrid("endEdit", editRow);
                     }
                     //当无编辑行时
                     if (editRow == undefined) {
                         //获取到当前选择行的下标
                         var index = datagrid.datagrid("getRowIndex", rows[0]);
                         //开启编辑
                         datagrid.datagrid("beginEdit", index);
                         //把当前开启编辑的行赋值给全局变量editRow
                         editRow = index;
                         //当开启了当前选择行的编辑状态之后，
                         //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                         datagrid.datagrid("unselectAll");
                     }
                 }
             }
             }, '-',
             { text: '确定', iconCls: 'icon-save', handler: function () {
                 //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                  datagrid.datagrid('acceptChanges');
                  console.info(editRow);
                  
                  datagrid.datagrid("endEdit", editRow);
             }
             }, '-',
             { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                 //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                 editRow = undefined;
                 datagrid.datagrid("rejectChanges");
                 //datagrid.datagrid("acceptChanges");
                 datagrid.datagrid("unselectAll");
             }
             }, '-'],
             onBeforeLoad:function(param){
            	 //alert();
            	 editRow = undefined;
            	 
            	 //datagrid.datagrid("clearSelections");
                 //datagrid.datagrid("rejectChanges");
                // datagrid.datagrid("unselectAll");
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
    
	var listFZType = function(){
    	
    	//alert("listType")
    	var medicalId = $(':input[name="data.medicalId"]').val();
    	//alert(medicalId);
        if ( medicalId > 0) 
        {
    	  
        	fzdatagrid = $("#fzdatagrid").datagrid({
        	url : sy.contextPath + '/app/emp-diagnose-record-detail!grid.sy?medicalId='+medicalId+'&opMod=FZ',
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
           	 {field: 'id', title: '编号', width: 100, sortable: true },
           	 
             { field: 'physicalTypeDef', title: '体格类型名称', width: 100,
            	   formatter:function(row){
            		   //console.log(row)
            		  // alert("UUUU " + row)
            		   if(row != undefined ){
            			   if(row.phyId != undefined){
                			   return  getAttrFZUnitComboxById(row.phyId);  
                		   }else{
                			   return  getAttrFZUnitComboxById(row);
                		   } 
            		   }
            		   
                   },
                  editor: { type: 'combotree', options: {
                	  	required: true , 
                	  	editable:true,
                	  	missingMessage: '请选择体格类型定义...',
                	  	idField:'id',
                	  	valueField:'id',
                	  	textField:'text',
                	  	parentField:'pid',
                	  	url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=FZ_TYPE',
    				    panelHeight: 'auto',
    				    formatter:function(row){
    		        		   return    row.text;
    		               }
    				  } 
                  }
              },
              {field: 'description', title: '结果', width: 200, sortable: true ,
            	  editor: {
                      type: 'validatebox',
                      options: {
                          required: true,
                          missingMessage: '请输入结果......'
                      }
                  }
              },
              {field: 'ss', title: 'ss', width: 200, sortable: true , hidden:true },
              {field: 'stype', title: 'stype', width: 200, sortable: true , hidden:true }
               ]],
             
            toolbar: [{ text: '添加', iconCls: 'icon-add', handler: function () {//添加列表的操作按钮添加，修改，删除等
                //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
                if (fzeditRow != undefined) {
                    fzdatagrid.datagrid("endEdit", fzeditRow);
                }
                var CurIndex = fzdatagrid.datagrid('getRows').length+1;  
            //alert(editRow);
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (fzeditRow == undefined) {
                    fzdatagrid.datagrid("appendRow", {
                        	id : CurIndex,
							ss : 'I',
							stype : 'FZ'
                    });
                    var lastIndex = fzdatagrid.datagrid('getRows').length - 1; 
                    //将新插入的那一行开户编辑状态
                    fzdatagrid.datagrid('selectRow', lastIndex);  
                    fzdatagrid.datagrid('beginEdit', lastIndex);  
                    //给当前编辑的行赋值
                    fzeditRow = lastIndex;
                }

            }
            }, '-',
             { text: '删除', iconCls: 'icon-remove', handler: function () {
                 //删除时先获取选择行
                 var rows = fzdatagrid.datagrid("getSelections");
                 //选择要删除的行
                 if (rows.length > 0) {
                     $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                         if (r) {
                             var ids = [];
                             for (var i = 0; i < rows.length; i++) {
                                 ids.push(rows[i].id);
                             }
                             //将选择到的行存入数组并用,分隔转换成字符串，
                             //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                             alert(ids.join(','));
                             var deleteIds = {'ids' : ids.join(',')};
                             var url = sy.contextPath + '/app/emp-diagnose-record-detail!deleteIds.sy';
                             $.post(url, deleteIds, function(result) {
                 				if (result.success) {
                 					parent.$.messager.alert('提示', result.msg, 'info');
                 					
                 					if (result.obj != null){
                 						//alert(result.obj.medicalId);
                 						
                 					}
                 				} else {
                 					parent.$.messager.alert('提示', result.msg, 'error');
                 					console.log('fail');
                 				}
                 			}, 'json');
                             
                         }
                     });
                 }
                 else {
                     $.messager.alert("提示", "请选择要删除的行", "error");
                 }
             }
             }, '-',
             { text: '修改', iconCls: 'icon-edit', handler: function () {
                 //修改时要获取选择到的行
                 var rows = fzdatagrid.datagrid("getSelections");
                 //如果只选择了一行则可以进行修改，否则不操作
                 if (rows.length == 1) {
                     //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                     if (fzeditRow != undefined) {
                         fzdatagrid.datagrid("endEdit", fzeditRow);
                     }
                     //当无编辑行时
                     if (fzeditRow == undefined) {
                         //获取到当前选择行的下标
                         var index = fzdatagrid.datagrid("getRowIndex", rows[0]);
                         //开启编辑
                         fzdatagrid.datagrid("beginEdit", index);
                         //把当前开启编辑的行赋值给全局变量editRow
                         fzeditRow = index;
                         //当开启了当前选择行的编辑状态之后，
                         //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                         fzdatagrid.datagrid("unselectAll");
                     }
                 }
             }
             }, '-',
             { text: '确定', iconCls: 'icon-save', handler: function () {
                 //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                  fzdatagrid.datagrid('acceptChanges');
                  console.info(fzeditRow);
                  
                  fzdatagrid.datagrid("endEdit", fzeditRow);
             }
             }, '-',
             { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                 //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                 fzeditRow = undefined;
                 fzdatagrid.datagrid("rejectChanges");
                 //datagrid.datagrid("acceptChanges");
                 fzdatagrid.datagrid("unselectAll");
             }
             }, '-'],
             onBeforeLoad:function(param){
            	 //alert();
            	 fzeditRow = undefined;
            	 
            	 //datagrid.datagrid("clearSelections");
                 //datagrid.datagrid("rejectChanges");
                // datagrid.datagrid("unselectAll");
             },
            onAfterEdit: function (rowIndex, rowData, changes) {
                //endEdit该方法触发此事件
                console.info(rowData);
                fzeditRow = undefined;
            },
            onDblClickRow: function (rowIndex, rowData) {
            //双击开启编辑行
                if (fzeditRow != undefined) {
                    fzdatagrid.datagrid("endEdit", fzeditRow);
                }
                if (fzeditRow == undefined) {
                    fzdatagrid.datagrid("beginEdit", rowIndex);
                    fzeditRow = rowIndex;
                }
            },
            onLoadSuccess:function(data){
            	console.log("clearSelections");
            	fzdatagrid.datagrid("clearSelections");
            	fzdatagrid.datagrid("unselectAll");
            }
        });
       }
    };
    
    var getDrugCodeCombogridById = function(id) {
    	console.log("getDrugCodeCombogridById" + id);
    	var g = $('#drugCodeCombogrid').combogrid('grid');	// get datagrid object
		var data = g.datagrid('getData');	// get the selected row
		    console.log(data)
	    	var name ='';
	    	var count = data.total;
	    	count = data.rows.length;
	    	//alert('count:'+ count)
	    	// console.log('count:'+ count)
	    	for(var i =0; i < count; i++){
	    		//alert(data[i].text)
	    		 //console.log(data.rows)
	    		 if(data.rows[i].id == id)  
	    	        { 
	    	            name = data.rows[i].drugName;
	    	            break;  
	    	        } 
	    	}
	      return name; 
   };
   
    
 	var listDrug = function(){
    	
    	var medicalId = $(':input[name="data.medicalId"]').val();
    	//alert(medicalId);
        if ( medicalId > 0) 
        {
    	    
        	drugdatagrid = $("#drugdatagrid").datagrid({
        	url : sy.contextPath + '/app/emp-drug-use-record!grid.sy?medicalId='+medicalId,
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
           	 
             { field: 'drugTimes', title: '药品名称', width: 100,
            	   formatter:function(value,rowData,rowIndex){
            		   //console.log("UUUUU   " + value + "    " + rowData.drugCode + "" + rowIndex)
            		   //alert("UUUU " + row.drugCode)
            		   //console.log("EEEEEE   " + rowData);
            		   
            		   var ed = $('#drugdatagrid').datagrid('getEditor', {index:rowIndex,field:'drugTimes'});
         			   if(ed != null){
         			   		$(ed.target).combogrid('setValue', rowData.id);
         			   }
         			   
            		   if(value != undefined ){
            			   //console.log("UUUUU  1 " + value + "    " + rowData + "" + rowIndex)
            			   if(value.id != undefined){
            				  // console.log("UUUUU  10 " + value + "    " + rowData + "" + rowIndex)
                			  return  getDrugCodeCombogridById(value.id);  
                		   }else{	
                			  // console.log("UUUUU  20 " + value + "    " + rowData + "" + rowIndex)
                			    return  getDrugCodeCombogridById(value);
                		   } 
            		   }
                   },  
                  editor: { type: 'combogrid', options: {
	                	  url : sy.contextPath + '/app/drug-times!doNotNeedSecurity_getMainMenu.sy',
	          			panelWidth : 500,
	          			panelHeight : 200,
	          			idField : 'id',
	          			textField : 'drugName',
	          			pagination : true,
	          			fitColumns : true,
	          			required : false,
	          			rownumbers : true,
	          			mode : 'local',
	          			delay : 500,
	          			sortName : 'id',
	          			sortOrder : 'asc',
	          			pageSize : 5,
	          			pageList : [ 5, 10 ],
	          			columns : [ [ {
	          				field : 'id',
	          				title : '编号',
	          				width : 50,
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
	          			},  {
	          				field : 'drugLotNo',
	          				title : '药品批号',
	          				width : 100,
	          				sortable : true
	          			}, {
	          				field : 'expireTime',
	          				title : '过期时间',
	          				width : 120,
	          				sortable : true
	          			}, {
	          				field : 'specification',
	          				title : '规格',
	          				width : 60,
	          				sortable : true
	          			},  {
	          				field : 'rest',
	          				title : '剩余量',
	          				width : 60,
	          				sortable : true
	          			}] ],
	          			onSelect:function(rowIndex,rowData){
	          				//console.log(this);
	          				 console.log($("#drugdatagrid").datagrid("getSelected"));
	          				 console.log("========= " + rowIndex + "     " + rowData + " drugeditRow=" + drugeditRow);
	          				 
	          				var ed = $('#drugdatagrid').datagrid('getEditor', {index:drugeditRow,field:'price'});
	          				if(ed != null){
	          					$(ed.target).numberbox('setValue', rowData.price);
	          				}
	          				var ed1 = $('#drugdatagrid').datagrid('getEditor', {index:drugeditRow,field:'medicalFee'});
	          				if(ed1 != null){
	          					$(ed1.target).numberbox('setValue', rowData.price);
	          				}
	          				 console.log("onSelect:function end ");
	          				  
	          			} // onselect  ~~~
    				  } 
                  }
              },
              {field: 'price', title: '单价', width: 60, sortable: true  , 
            	  editor: {
                      type: 'numberbox',
                      
                      options: {
                          required: true,
                          precision:2,
                          max:99999.99,
                          missingMessage: '请输入价格......'
                      }
                  }
              },
              {field: 'number', title: '数量', width: 60, sortable: true ,
            	  editor: {
                      type: 'numberbox',
                      options: {
                          required: true,
                          missingMessage: '请输入数量......' 
                      }
                  }
              },
              {field: 'medicalFee', title: '医疗费用', width: 60, sortable: true ,
            	  editor: {
                      type: 'numberbox',
                      options: {
                          required: true,
                          precision:2,
                          max:99999.99,
                          missingMessage: '请输入医疗费用......' 
                      }
                  }
              },
              {field: 'empTakeFee', title: '员工承担费用', width: 60, sortable: true ,
            	  hidden:true,
            	  editor: {
                      type: 'numberbox',
                      options: {
                          required: false,
                          precision:2,
                          max:99999.99,
                          missingMessage: '请输入员工承担费用......'
                      }
                  }
              },
              {field: 'insuranceFee', title: '保险公司承担费用', width: 100, sortable: true ,
            	  hidden:true,
            	  editor: {
                      type: 'numberbox',
                      options: {
                          required: false,
                          precision:2,
                          max:99999.99,
                          missingMessage: '请输入保险公司承担费用......'
                      }
                  }
              },
              {field: 'ebType', title: '医保类型', width: 100, sortable: true ,
            	  formatter:function(row){
           		   //console.log(row)
           		  // alert("UUUU " + row)
           		   if(row != undefined ){
           			   if(row.phyId != undefined){
               			   return  getAttrEBUnitComboxById(row.phyId);  
               		   }else{
               			   return  getAttrEBUnitComboxById(row);
               		   }
           		   }
                  },
                 editor: { type: 'combotree', options: {
	               	  	required: false , 
	               	  	editable:true,
	               	  	missingMessage: '请选择体格类型定义...',
	               	  	idField:'id',
	               	  	valueField:'id',
	               	  	textField:'text',
	               	  	parentField:'pid',
	               	  	url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=EB_TYPE',
	   				    panelHeight: 'auto',
	   				    formatter:function(row){
	   		        		   return    row.text;
	   		               }
	   				  }
                 }
              },
              {field: 'ext1', title: '备注', width: 100, sortable: true ,
            	  editor: {
                      type: 'validatebox',
                      options: {
                          required: false,
                          missingMessage: '请输入备注......'
                      }
                  }
              },
              {field: 'ss', title: 'ss', width: 200, sortable: true , hidden:true }
            ]],
            toolbar: [{ text: '添加', iconCls: 'icon-add', handler: function () {//添加列表的操作按钮添加，修改，删除等
                //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
                if (drugeditRow != undefined) {
                    drugdatagrid.datagrid("endEdit", drugeditRow);
                }
                var CurIndex = drugdatagrid.datagrid('getRows').length+1;  
            	//alert(editRow);
                //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
                if (drugeditRow == undefined) {
                	drugdatagrid.datagrid("appendRow", {
                        	id : CurIndex,
							ss : 'I'
                    });
                    var lastIndex = drugdatagrid.datagrid('getRows').length - 1; 
                    //将新插入的那一行开户编辑状态
                    drugdatagrid.datagrid('selectRow', lastIndex);  
                    drugdatagrid.datagrid('beginEdit', lastIndex);  
                    //给当前编辑的行赋值
                    drugeditRow = lastIndex;
                }
            }
            }, '-',
             { text: '删除', iconCls: 'icon-remove', handler: function () {
                 //删除时先获取选择行
                 var rows = drugdatagrid.datagrid("getSelections");
                 //选择要删除的行
                 if (rows.length > 0) {
                     $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                         if (r) {
                             var ids = [];
                             for (var i = 0; i < rows.length; i++) {
                                 ids.push(rows[i].id);
                             }
                             //将选择到的行存入数组并用,分隔转换成字符串，
                             //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                             //alert(ids.join(','));
                             var deleteIds = {'ids' : ids.join(',')};
                             var url = sy.contextPath + '/app/emp-drug-use-record!deleteIds.sy';
                             $.post(url, deleteIds, function(result) {
                 				if (result.success) {
                 					parent.$.messager.alert('提示', result.msg, 'info');
                 					
                 					if (result.obj != null){
                 						//alert(result.obj.medicalId);
                 					}
                 				} else {
                 					parent.$.messager.alert('提示', result.msg, 'error');
                 					console.log('fail');
                 				}
                 			}, 'json');
                             
                         }
                     });
                 }
                 else {
                     $.messager.alert("提示", "请选择要删除的行", "error");
                 }
             }
             }, '-',
             { text: '修改', iconCls: 'icon-edit', handler: function () {
                 //修改时要获取选择到的行
                 var rows = drugdatagrid.datagrid("getSelections");
                 //如果只选择了一行则可以进行修改，否则不操作
                 if (rows.length == 1) {
                     //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                     if (drugeditRow != undefined) {
                    	 drugdatagrid.datagrid("endEdit", editRow);
                     }
                     //当无编辑行时
                     if (drugeditRow == undefined) {
                         //获取到当前选择行的下标
                         var index = drugdatagrid.datagrid("getRowIndex", rows[0]);
                         //开启编辑
                         drugdatagrid.datagrid("beginEdit", index);
                         //把当前开启编辑的行赋值给全局变量editRow
                         drugeditRow = index;
                         //当开启了当前选择行的编辑状态之后，
                         //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                         drugdatagrid.datagrid("unselectAll");
                     }
                 }
             }
             }, '-',
             { text: '确定', iconCls: 'icon-save', handler: function () {
                 //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                  drugdatagrid.datagrid('acceptChanges');
                  console.info(editRow);
                  
                  drugdatagrid.datagrid("endEdit", drugeditRow);
             }
             }, '-',
             { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
                 //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                 drugeditRow = undefined;
                 drugdatagrid.datagrid("rejectChanges");
                 //datagrid.datagrid("acceptChanges");
                 drugdatagrid.datagrid("unselectAll");
             }
             }, '-'],
             onBeforeLoad:function(param){
            	 console.info("onBeforeLoad:function : " + param );
            	 drugeditRow = undefined;
            	 
            	 //datagrid.datagrid("clearSelections");
                 //datagrid.datagrid("rejectChanges");
                // datagrid.datagrid("unselectAll");
             },
             onBeforeEdit:function(rowIndex, rowData){
            	 console.info("onBeforeEdit:function : " + rowIndex );
            	 console.info("onBeforeEdit:function : " + rowData );
            	 drugeditRow = rowIndex;
             },
            onAfterEdit: function (rowIndex, rowData, changes) {
            	console.info("onAfterEdit:function : " +rowIndex );
                //endEdit该方法触发此事件
                console.info(rowData);
                //计算费用
                if (drugeditRow != undefined) {
                		  console.log('ddddddddddddddddddddddd');
                		  console.log(drugeditRow);
                		  var ed1 = $('#drugdatagrid').datagrid('getEditor', {index:drugeditRow,field:'medicalFee'});
                		  console.log(ed1);
	          				if(ed1 != null){
	          					$(ed1.target).numberbox('setValue', 111);
	          				}
	          				rowData.medicalFee = rowData.price * rowData.number+"";
	          				$('#drugdatagrid').datagrid('updateRow',{
	          	                index: rowIndex,
	          	                row: rowData
	          	            });
                    	 $('#drugdatagrid').datagrid("endEdit", drugeditRow);
                    	 //$('#drugdatagrid').datagrid('refreshRow', drugeditRow);  
                }
                drugeditRow = undefined;
            },
            onDblClickRow: function (rowIndex, rowData) {
            	console.info("onDblClickRow:function : " + rowIndex );
            //双击开启编辑行
                if (drugeditRow != undefined) {
                	drugdatagrid.datagrid("endEdit", drugeditRow);
                }
                if (drugeditRow == undefined) {
                	drugdatagrid.datagrid("beginEdit", rowIndex);
                    drugeditRow = rowIndex;
                }
            },
            onLoadSuccess:function(data){
            	console.log("clearSelections");
            	drugdatagrid.datagrid("clearSelections");
            	drugdatagrid.datagrid("unselectAll");
            }
        });
       }
    };
    
    
	$(function() {
		if ($(':input[name="data.medicalId"]').val().length > 0 && $(':input[name="data.medicalId"]').val() != 'null') {
			//alert('数据加载中....');
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(sy.contextPath + '/app/emp-diagnose-record!getById.sy', {
				EmpDiagnoseRecordId : $(':input[name="data.medicalId"]').val()
			}, function(result) {
				if (result != undefined && result.medicalId != undefined ) {
					//alert(result.custUser.userName)
					console.log('result.firstStep' +  result.firstStep);
					console.log('result.propose' +  result.propose);
					$('form').form('load', {
						'data.medicalId' : result.medicalId,
						'data.diagTime' : result.diagTime,
						'data.sgiNo' : result.sgiNo,
						'data.mainInfo' : result.mainInfo,
						'data.ext1' : result.ext1,
						'data.custUser.userId' : result.custUser.userId,
						'data.custUser.userName' : result.custUser.userName,
						'data.propose' : result.propose,
						'data.firstStep' : result.firstStep
					});
					listHisGrid();
					$('form-propose').form('load',{
						'data.propose' : result.propose
					});
					$('form-chubu').form('load',{
						'data.firstStep' : result.firstStep
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
			
			listType();
			listFZType();
			listDrug();
		}
		
		/* if ($(':input[name="data.syuser.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			
			$.post(sy.contextPath+ '/app/medical-attend!getCurrentUserInfo.sy',{
								queryType : 'me-today'
							},
							function(result) {
								//alert(result.iCONCLS)
								if (result.id != undefined) {
									$('form').form('load',{
														'data.id' : result.id,
														'data.syuser.id' : result.syuser ? result.syuser.id: '',
														'data.syuser.name' : result.syuser ? result.syuser.name: '',
														'data.yyyymmdd' : result.yyyymmdd,
														'data.ext1' : result.ext1
									});
									$('#workTime').html('' + result.workTime);
									console.log(result.afterWorkTime);
									$('#afterWorkTime').html('' + result.afterWorkTime);
									$('#isLeave').html(result.isLeave == 0 ? '没有请假': '今天请假了');
									$('#iCONCLS').attr('class', result.iCONCLS);//设置背景图标
								}
								parent.$.messager.progress('close');
							}, 'json');
		}; */
		
		
		<%-- // 体格检查表格
		grid = $('#grid').datagrid({
			title : '',
			url : sy.contextPath + '/app/cust-user!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'userId',
			sortName : 'createTime',
			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [{
				width : '50',
				title : '员工编号',
				field : 'userId',
				sortable : true
			}, {
				width : '100',
				title : '员工名称',
				field : 'userName',
				sortable : true
			}  ] ],
			columns : [ [ {
				width : '80',
				title : '创建时间',
				field : 'createTime',
				sortable : true
			}, {
				width : '80',
				title : '修改时间',
				field : 'updateTime',
				sortable : true
			} , {
				title : '操作',
				field : 'action',
				width : '350',
				formatter : function(value, row) {
					var str = '';
					<%if (securityUtil.havePermission("/app/cust-user!getById")) {%>
						var bt = systool.createFuncButton('ext-icon-note','查看','查看员工资料','showFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!update")) {%>
						var bt = systool.createFuncButton('ext-icon-note_edit','编辑','编辑员工资料','editFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!grantRole")) {%>
						var bt = systool.createFuncButton('ext-icon-user','员工角色','给员工授予角色','grantRoleFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>
					<%if (securityUtil.havePermission("/app/cust-user!grantOrganization")) {%>
						var bt = systool.createFuncButton('ext-icon-group','员工机构','给员工授予员工机构','grantOrganizationFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
					<%}%>						
					<%if (securityUtil.havePermission("/app/cust-user!delete")) {%>
						var bt = systool.createFuncButton('ext-icon-note_delete','删除','删除员工','removeFun(\'{0}\');');
						str +=  sy.formatString(bt,row.userId);
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
		}); --%>
		
		////-----------------------------------------------------
		var customerIdCombogrid = $('#customerIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-info!doNotNeedSessionAndSecurity_customerInfoIdComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'customerId',
			textField : 'customerName',
			pagination : true,
			fitColumns : true,
			required : false,
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
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				//alert(record);
				var g = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var r = g.datagrid('getSelected');	// get the selected row
				//alert(r.customerId )
				if(r.customerId != null){
					 $('#deptIdCombogrid').combogrid({
				            disabled:false,  
				            url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId='+r.customerId,
							panelWidth : 500,
							panelHeight : 200,
							idField : 'deptId',
							textField : 'deptName',
							pagination : true,
							fitColumns : true,
							required : false,
							rownumbers : true,
							mode : 'local',
							delay : 500,
							sortName : 'deptId',
							sortOrder : 'asc',
							pageSize : 5,
							pageList : [ 5, 10 ],
							columns : [ [ {
								field : 'deptId',
								title : '编号',
								width : 100,
								sortable : true
							}, {
								field : 'deptName',
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
							} ] ],
							onSelect:function(record){
								//console.log(record);
								//刷新数据，重新读取省份下的城市，并清空当前输入的值  
								var deptIdCombogrid = $('#deptIdCombogrid').combogrid('grid');	// get datagrid object
								var deptIdCombogridrow = deptIdCombogrid.datagrid('getSelected');	// get the selected row
								
								var customerIdCombogrid = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
								var customerIdCombogridrow = customerIdCombogrid.datagrid('getSelected');	// get the selected row
								
								if(customerIdCombogrid  != '' && deptIdCombogrid != ''){
						        $('#userIdCombogrid').combogrid({
						            disabled:false,  
						            url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogridById.sy?customerInfoId='+customerIdCombogridrow.customerId+'&deptId=' + deptIdCombogridrow.deptId,
						            panelWidth : 500,
									panelHeight : 200,
						            idField : 'userId',
									textField : 'userName',
									pagination : true,
									fitColumns : true,
									required : true,
									rownumbers : true,
									mode : 'local',
									delay : 500,
									sortName : 'userId',
									sortOrder : 'asc',
									pageSize : 5,
									pageList : [ 5, 10 ],
									columns : [ [ {
										field : 'userId',
										title : '编号',
										width : 100,
										sortable : true
									}, {
										field : 'userName',
										title : '用户',
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
						        }) ;
								}
							}
				        }) ;
				}
		       
			}
			
		});
		if(customerIdCombogrid.val() != null && customerIdCombogrid.val() != ''){
		//alert(customerIdCombogrid.val());
		var deptIdCombogrid = $('#deptIdCombogrid').combogrid({
			url : sy.contextPath + '/app/cust-dept!doNotNeedSessionAndSecurity_deptIdComboGrid.sy?customerId=' +customerIdCombogrid.val(),
			panelWidth : 500,
			panelHeight : 200,
			idField : 'deptId',
			textField : 'deptName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'deptId',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'deptId',
				title : '编号',
				width : 100,
				sortable : true
			}, {
				field : 'deptName',
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
			} ] ],
			onSelect:function(record){
				//console.log(record);
				//刷新数据，重新读取省份下的城市，并清空当前输入的值  
				var deptIdCombogrid = $('#deptIdCombogrid').combogrid('grid');	// get datagrid object
				var deptIdCombogridrow = deptIdCombogrid.datagrid('getSelected');	// get the selected row
				
				var customerIdCombogrid = $('#customerIdCombogrid').combogrid('grid');	// get datagrid object
				var customerIdCombogridrow = customerIdCombogrid.datagrid('getSelected');	// get the selected row
				
				if(customerIdCombogrid  != '' && deptIdCombogrid != ''){
		        $('#userIdCombogrid').combogrid({
		            disabled:false,  
		            url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogridById.sy?customerInfoId='+customerIdCombogridrow.customerId+'&deptId=' + deptIdCombogridrow.deptId,
		            valueField:'userId',  
		            textField:'userName'  
		        }).combogrid('clear');
				}
			}
		});
		};
		
		if(customerIdCombogrid.val() != '' && $('#deptIdCombogrid').val() != ''){
			userIdCombogrid = $('#userIdCombogrid').combogrid({
				url : sy.contextPath + '/app/cust-user!doNotNeedSessionAndSecurity_userIdCombogridById.sy?customerInfoId='+customerIdCombogrid.val()+'&deptId=' + $('#deptIdCombogrid').val(),
				panelWidth : 500,
				panelHeight : 200,
				idField : 'userId',
				textField : 'userName',
				pagination : true,
				fitColumns : true,
				required : true,
				rownumbers : true,
				mode : 'local',
				delay : 500,
				sortName : 'userId',
				sortOrder : 'asc',
				pageSize : 5,
				pageList : [ 5, 10 ],
				columns : [ [ {
					field : 'userId',
					title : '编号',
					width : 100,
					sortable : true
				}, {
					field : 'userName',
					title : '用户',
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
		};
		  
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		

    
    var attrUnitCombox = $('#attrUnitCombox').combobox({
		url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=TG_TYPE',
        idField:'id',
        valueField:'id',
        textField:'text',
        parentField:'pid'
        });
    
    
    var attrFZUnitCombox = $('#attrFZUnitCombox').combobox({
		url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=FZ_TYPE',
        idField:'id',
        valueField:'id',
        textField:'text',
        parentField:'pid'
        });
    var attrEBUnitCombox = $('#attrEBUnitCombox').combobox({
		url : '<%=contextPath%>/app/phy-def!doNotNeedSecurity_getAllListByType.sy?type=EB_TYPE',
        idField:'id',
        valueField:'id',
        textField:'text',
        parentField:'pid'
        });
    //~~~~~~~~~~~~~~~~~~~~~~~~~
    
    
    ////-----------------------------------------------------
		var drugCodeCombogrid = $('#drugCodeCombogrid').combogrid({
			url : sy.contextPath + '/app/drug-times!doNotNeedSecurity_getMainMenu.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'id',
			textField : 'drugName',
			pagination : true,
			fitColumns : true,
			required : false,
			rownumbers : true,
			mode : 'local',
			delay : 500,
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 100,
			pageList : [ 100, 200 ],
			columns : [ [ {
				field : 'id',
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
					//$(':input[name="data.drugInfo.specification"]').val(r.specification);
				}
			}
			
		});
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		var inputCombogrid = $('#inputCombogrid').combogrid({
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
					//$(':input[name="data.drugInfo.specification"]').val(r.specification);
				}
			}
			
		});
	});
</script>

<script type="text/javascript">
var attrCheckCombox;
var phyKeshiCombox;
var isIndicatorsCombox;
var IsAbnormalCombox;
var attrUnitCombox2;

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
};

var getAttrUnitCombox2ById = function(id) {
	var data = $("#attrUnitCombox2").combobox('getData');
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

var checkRoleUsersHasPriv_getById = function() {
	<%if (securityUtil.havePermission("/app/report-attr!getById")) {%>
		return true;
	<%}%>
	return false;
};
var showUserDataFun = function(id,name) {
	//alert('showUserDataFun ' + id)
	var dialog = parent.sy.modalDialog({
		title : '查看属性信息',
		url : sy.contextPath + '/securityJsp/app/MedicalDetailDataForm.jsp?id=' + id + "&name=" + name
	});
};

var listHisGrid = function(){
	var userId = 0 ;
	<%if(userId == null && id == null) {%>
	var userIdCombogrid = $('#userIdCombogrid').combogrid('grid');	// get datagrid object
	var userIdCombogridrow = userIdCombogrid.datagrid('getSelected');	// get the selected row
		userId =userIdCombogridrow.userId;
	 	
	<%} else%>
	<%if( (userId != null&& !userId.isEmpty()) || id !=null) {%>
		userId = 	$(':input[name="data.custUser.userId"]').val();
	<%} else {%>
		userId = <%=userId%>
	<% } %>
	if(userId == 'null'){
		return ;
	}
	 //只查询异常的数据
	 hisdatagrid  = $('#hisdatagrid').datagrid({
					title : '',
					url : sy.contextPath + '/app/medical-detail-data!treeGrid.sy?QUERY_t%23isAbnormal_I_EQ=10113102&QUERY_t%23custUser%23userId_I_EQ='+userId,
					striped : false,
					rownumbers : true,
					pagination : true,
					singleSelect : false,
					treeField : 'attrName',
					parentField : 'pid',
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
								sortable : true,
								hidden : true
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
						}
					}, {
						width : '80',
						title : '属性参考值',
						field : 'attrCankao',
						sortable : true
					},  {
						width : '80',
						title : '属性检查类型',
						field : 'attrCheck',
						sortable : true ,
						formatter : function(value, row, index) {
							return getAttrCheckComboxById(value);
						}
					},  {
						width : '80',
						title : '属性单位',
						field : 'attrUnit',
						sortable : true ,
						formatter : function(value, row, index) {
							return getAttrUnitCombox2ById(value);
						}
					},  {
						width : '100',
						title : '属性数值',
						field : 'attrValue',
						sortable : true
					},  {
						width : '80',
						title : '数值类型',
						field : 'isIndicators',
						sortable : true ,
						formatter : function(value, row, index) {
							return getIsIndicatorsComboxById(value);
						}
					},  {
						width : '80',
						title : '是否异常',
						field : 'isAbnormal',
						sortable : true,
						formatter:function(row){
			        		   if(row != undefined ){
			        			   if(row.phyId != undefined){
			            			   return  getIsAbnormalComboxById(row.phyId);  
			            		   }else{
			            			   return  getIsAbnormalComboxById(row);
			            		   } 
			        		   }
			               }
			              
					},{
						width : '100',
						title : '属性描述',
						field : 'attrDesc',
						sortable : true
					},  {
						width : '120',
						title : '创建时间',
						field : 'createTime',
						sortable : true
					}, {
						width : '120',
						title : '修改时间',
						field : 'updateTime',
						sortable : true
					} ,  {
						title : '操作',
						field : 'action',
						width : '80',
						formatter : function(value, row) {
							var str = '';
							if(checkRoleUsersHasPriv_getById()){
								var bt = systool.createFuncButton('ext-icon-note','查看','查看属性','showUserDataFun(\'{0}\');');
								str +=  sy.formatString(bt,row.dataId);
							}
							return str;
						}
					} ] ],
					toolbar : '#usertoolbar',
					onBeforeLoad : function(param) {
						parent.$.messager.progress({
							text : '数据加载中....'
						});
					},
					onLoadSuccess : function(data) {
						$('.iconImg').attr('src', sy.pixel_0);
						parent.$.messager.progress('close');
						if(data!=null){
							$.each(data.rows, function(index) {
								var row = data.rows[index];
									//alert(row.id + row.name);
								 
							});
						}
					}
				});
};


$(function () {
	
	attrUnitCombox2 = $('#attrUnitCombox2').combobox(
			{
							url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_UNIT',
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
	phyKeshiCombox = $('#phyKeshiCombox').combobox({
		    url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_KESHI',
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
	IsAbnormalCombox =$('#IsAbnormalCombox').combobox({
		url : '<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=ATTR_ISABNORMAL_TYPE',
	    idField:'id',
	    valueField:'id',
	    textField:'text',
	    parentField:'pid'
	    });
	
	
	
	
	var curr_time = new Date();
	   var strDate = curr_time.getFullYear()+"-";
	   strDate += curr_time.getMonth()+1+"-";
	   strDate += curr_time.getDate() ;
	  /*  strDate += curr_time.getHours()+":";
	   strDate += curr_time.getMinutes()+":";
	   strDate += curr_time.getSeconds(); */
	   $("#data_diagTime").datebox("setValue", strDate); 
	   
});
</script>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	<div class="easyui-panel" title="员工就诊"  style="height: 526px" >
		<div style="padding: 10px 0 10px 10px;"   >
			<table>
				<tr>
					<td>
						<div class="easyui-panel" title="主诉" style="height: 240px">
							<div style="padding: 10px 0 10px 10px">
								<form id="baseform" method="post">
	
									<table>
										<tr>
											<td></td>
										</tr>
										<tr>
											<td> 编号:</td>
											<td><input class="easyui-validatebox" type="text" value="<%=id==null ? "" : id%>"
												name="data.medicalId" data-options="required:false"
												readonly="readonly"></input></td>
										</tr>
										 <tr>
										 	<%if(userId == null && id == null) {%>
												    <th>客户</th>
													<td>
														<input id="customerIdCombogrid" name="data.customerInfo.customerId" type="text" value="" style="width: 130px;">
													</td>
													<th>部门</th>
													<td>
														<input id="deptIdCombogrid" name="data.custDept.deptId" type="text" value="" style="width: 130px;">
													</td>
													<th>就诊员工</th>
													<td> 
													     <input class="easyui-validatebox" id="userIdCombogrid" name="data.custUser.userId" data-options="required:true"  type="text" value="" style="width: 130px;">
													</td>
												<%} %>
												<%if( (userId != null&& !userId.isEmpty()) || id !=null) {%>
													<td>就诊员工</td>
													<td><input class="easyui-validatebox"  hidden="false" type="text"
													name="data.custUser.userId" value="<%=userId%>"
													data-options="required:false"></input> <input
													class="easyui-validatebox" type="text"
													name="data.custUser.userName" value="<%=userName%>"
													disabled="disabled" data-options="required:false"></input></td>
													<th></th><td  style="width: 130px;"></td>
											 		<th></th><td  style="width: 130px;"></td>
													<th></th><td  style="width: 130px;"></td>
												<%} %>
												
											</tr>
											<tr>
										 
										 	
											<!-- <td>就诊时间:</td>
											<td><input class="easyui-validatebox" type="text"
												name="data.yyyymmdd" readonly="readonly" data-options="required:false"></input></td>
												 -->
										 	<th>就诊年月</th>
											<td>
												<input  id="data_diagTime" name="data.diagTime" class="easyui-datebox" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"   data-options="required:true" style="width: 120px;" />
											</td>
										 	
											<td><!-- SGI编号: --></td>
											<td><input class="easyui-validatebox" type="text" maxlength="25" hidden="false"
												name="data.sgiNo"   data-options="required:false"></input></td>
										<tr >
											<td>主诉:</td>
											<td colspan="5"><textarea name="data.mainInfo" id="data_mainInfo" style="height: 40px;"></textarea>
											<a href="javascript:void(0);" class="easyui-linkbutton"
												data-options="iconCls:'ext-icon-note_add',plain:true"
												onclick="inputBox('cb',$('#data_mainInfo'));">快速录入</a>
											</td>
										</tr>
									</table>
								</form>
								 
							</div>
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-note_add',plain:true"
									onclick="listMyRecord_CC();">查询历史就诊</a> <a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_BASE('sb')">保存</a>   
								 
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="easyui-panel" title="既往病史" style="height: 300px">
							<div data-options="region:'center',fit:true,border:false" style="height: 230px">
								<div hidden="true">
								<table>
											<tr>
												 
											 	<td>检查类型</td>
								<td>
									<select id="attrCheckCombox" name="QUERY_t#attrCheck_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrCheckCombox').combotree('clear');" title="清空" />
								</td>
											 
											<td>数值类型</td>
								<td>
									<select id="isIndicatorsCombox" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#isIndicatorsCombox').combotree('clear');" title="清空" />
									<select id="attrUnitCombox2" name="QUERY_t#attrUnit_S_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
								</td>
											<th>体检科室</th>
											<td>
											<select id="phyKeshiCombox" name="QUERY_t#attrKeshi_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#phyKeshiCombox').combotree('clear');" title="清空" />
											</td>
											<td>是否异常</td>
											<td>
												<select id=IsAbnormalCombox name="QUERY_t#isAbnormal_I_EQ" class="easyui-combotree"  style="width: 105px;"></select><img class="iconImg ext-icon-cross" onclick="$('#IsAbnormalCombox').combotree('clear');" title="清空" />
											</td>
											</tr>
										</table>
										</div>
								<form id="hisdataform" style="height:100%">
									<table id="hisdatagrid" data-options="fit:true,border:false"></table>
								</form>
							</div>
							<!-- <div style="text-align: left; padding: 5px">
								<input id="iconCls" class="ext-icon-bell" readonly="readonly"
									style="padding-left: 18px; width: 13px; border: none" />盘点纠正：把库存的数量改成盘点的数量
								<br /> <input id="iconCls" class="ext-icon-bell"
									readonly="readonly"
									style="padding-left: 18px; width: 13px; border: none" />盘点纠正：盘点的情况下，只能输入盘点的数量
								<br /> <input id="iconCls" class="ext-icon-bell"
									readonly="readonly"
									style="padding-left: 18px; width: 13px; border: none" />盘点纠正：如果找不到对应的药品和规格的盘点数据，请检查原因，并且到药品入库模块中增加
								<br /> <input id="iconCls" class="ext-icon-bell"
									readonly="readonly"
									style="padding-left: 18px; width: 13px; border: none" />盘点纠正：为了保证盘点数据的输入准确性，当前版本只支持单个数据的盘点！！
							</div> -->
							
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="">保存</a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="easyui-panel" title="体格检查" style="height: 300px">
							<div hidden="true">
								<select id="attrUnitCombox"  hidden="true" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#attrUnitCombox').combotree('clear');" title="清空" />
							</div>
							<div data-options="region:'center',fit:true,border:false" style="height: 230px">
								<form id="dataform" style="height:100%">
									<table id="datagrid" data-options="fit:true,border:false"></table>
								</form>
							</div>
							
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm_TG()">保存</a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="easyui-panel" title="辅助检查" style="height: 300px">
							<div hidden="true">
								<select id="attrFZUnitCombox"  hidden="true" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#attrFZUnitCombox').combotree('clear');" title="清空" />
							</div>
							<div data-options="region:'center',fit:true,border:false" style="height: 230px">
								<form id="fzdataform" style="height:100%">
									<table id="fzdatagrid" data-options="fit:true,border:false"></table>
								</form>
							</div>
							
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm_FZ()">保存</a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="easyui-panel" title="初步判断" style="height: 180px">

							<div data-options="region:'center',fit:true,border:false"
								style="height: 55px">
								<form id="form-chubu" method="post">
									<table>
										<tr >
											<td>初步判断:</td>
											<td colspan="5"><textarea id="data_firstStep" name="data.firstStep" style="height: 55px; width: 500px"></textarea>
											 
								 			
											<a    href="javascript:void(0);" class="easyui-linkbutton"
													data-options="iconCls:'ext-icon-note_add',plain:true"
													onclick="inputBox('cb',$('#data_firstStep'));">快速录入</a>
											
											</td>
										</tr>
										<tr>
											<td><input id="data_suspectedCheckbox" type="checkbox" name="data_suspectedCheckbox"  value="yes" >疑似病例</td>
											<td>
												<select id="data_illType" name="data_illType" class="easyui-combotree" data-options="editable:false,idField:'id',textField:'text',parentField:'pid',url:'<%=contextPath%>/base/sys-def!doNotNeedSecurity_getAllListByType.sy?type=LIUXINGBING_TYPE'" style="width: 155px;"></select><img class="iconImg ext-icon-cross" onclick="$('#data.type').combotree('clear');" title="清空" />
											</td>
										</tr>
									</table>
								</form>
							</div>
							
							<div style="text-align: center; padding: 10px">
								 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm_CC('sb')">保存</a>
							</div>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<div class="easyui-panel" title="给药" style="height: 300px">
							<div hidden="true">
								<input id="drugCodeCombogrid"  
												name="data.drugTimes.id" class="easyui-validatebox"
												data-options="required:false" type="text"
												style="width: 130px;">
							</div>
							<div hidden="true">
								<select id="attrEBUnitCombox"  hidden="true" name="QUERY_t#isIndicators_I_EQ" class="easyui-combotree"  style="width: 105px; display: none"></select><img class="iconImg ext-icon-cross" onclick="$('#attrEBUnitCombox').combotree('clear');" title="清空" />
							</div>
							<div data-options="region:'center',fit:true,border:false" style="height: 230px">
								<form id="drugdataform" style="height:100%">
									<table id="drugdatagrid" data-options="fit:true,border:false"></table>
								</form>
							</div>
							
							<div style="text-align: center; padding: 5px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm_YAO()">保存</a>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="easyui-panel" title="建议" style="height: 200px">
							<div data-options="region:'center',fit:true,border:false"
								style="height: 55px">
								<form id="form-propose" method="post">
								 	<table>
										<tr >
											<td>建议:</td>
											<td colspan="5">
											<textarea id="data_propose" name="data.propose" style="height: 55px; width: 500px">
											</textarea> <a    href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'ext-icon-note_add',plain:true"
									onclick="inputBox('cb',$('#data_propose'));">快速录入</a>
											</td>
										</tr>
									</table>
								</form>
							</div>
							
							<div style="text-align: center; padding: 10px">
								<a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('sb')">保存</a>

							</div>
						</div>
					</td>
				</tr>
				<!-- <tr>
					<td>
						<div class="easyui-panel" title="操作区域"  >
							<div style="text-align: center; padding: 5px">
								<a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('sb')">保存</a>
								<a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('sb')">提交</a>
								<a
									href="javascript:void(0)" class="easyui-linkbutton"
									onclick="submitForm_CC('sb')">返回</a>
								
							</div>
						</div>
					</td>
				 	
				</tr> -->
				
			</table>

		</div>
	</div>


	 
	 
</body>
</html>