var sys = sys || {};

sys.showUserDataFun = function(id,name) {
	alert('showUserDataFun ' + id)
	var dialog = parent.sy.modalDialog({
		title : '查看属性信息',
		url : sy.contextPath + '/securityJsp/app/MedicalDetailDataForm.jsp?id=' + id + "&name=" + name
	});
};

sys.editUserDataFun = function(id,name) {
	var dialog = parent.sy.modalDialog({
		title : '编辑属性信息',
		url : sy.contextPath + '/securityJsp/app/MedicalDetailDataForm.jsp?id=' + id + "&name=" + name,
		buttons : [ {
			text : '编辑',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, $('#userGrid'), parent.$);
			}
		} ]
	});
};
sys.removeUserDataFun  = function(id) {
	 
	parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
		if (r) {
			$.post(sy.contextPath + '/app/medical-detail-data!delete.sy', {
				MedicalReportAttrId : id
			}, function() {
				$('#userGrid').datagrid('reload');
			}, 'json');
		}
	});
};

/* 加载用户数据  */
sys.loadRoleUsersFun = function(id) {
	//alert(id)
	var userGrid = $('#userGrid').datagrid({
		title : '',
		url : sy.contextPath + '/app/medical-detail-data!grid.sy?opOrder='+id,
		striped : false,
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
			width : '150',
			title : '所属科室',
			field : 'attrKeshi',
			sortable : true
		}, {
			width : '150',
			title : '属性参考值',
			field : 'attrCankao',
			sortable : true
		},  {
			width : '150',
			title : '属性检查类型',
			field : 'attrCheck',
			sortable : true
		},  {
			width : '150',
			title : '属性单位',
			field : 'attrUnit',
			sortable : true
		},  {
			width : '150',
			title : '属性数值',
			field : 'unitData',
			sortable : true
		},  {
			width : '150',
			title : '是否指标',
			field : 'isIndicators',
			sortable : true
		}, {
			width : '150',
			title : '属性描述',
			field : 'attrDesc',
			sortable : true
		},  {
			width : '150',
			title : '创建时间',
			field : 'createTime',
			sortable : true
		}, {
			width : '150',
			title : '修改时间',
			field : 'updateTime',
			sortable : true
		} ,  {
			title : '操作',
			field : 'action',
			width : '180',
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
		toolbar : '#usertoolbar',
		onBeforeLoad : function(param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
		onLoadSuccess : function(data) {
			$('.iconImg').attr('src', sy.pixel_0);
			parent.$.messager.progress('close');
				
			$.each(data.rows, function(index) {
				var row = data.rows[index];
					//alert(row.id + row.name);
				if(index >= 0 ){
					sys.checkRow(index);
				}
			});
		}
	});
};

sys.checkRow = function(index){
	$('#userGrid').datagrid('checkRow', index);
}
sys.loadUserData = function(roleid){
	//alert('loadUserData    ');
	sys.loadRoleUsersFun(roleid);
}

/**
 */
sys.getSelected = function(component) {
	var row = $(component).datagrid('getSelected');
	 
	if (row) {
		//$.messager.alert('Info', row.id + ":" + row.opOrder) ;
	     
		sys.loadUserData(row.opOrder);
		
		return row.opOrder;
	}
	
	return 0;
}

sys.getSelectedId = function(component) {
	var row = $(component).datagrid('getSelected');
	if (row) {
		//$.messager.alert('Info', row.medicalReportDef.templateName + ":" + row.medicalReportDef.templateId + ":" + row.attr1);
		//alert('loadUserData  start   ');
		return row.medicalReportDef.templateId;
	}
	return -1;
}

sys.getSelectedName = function(component) {
	var row = $(component).datagrid('getSelected');
	if (row) {
//		$.messager.alert('Info', row.name + ":" + row.id + ":"
//				+ row.attr1);
		//alert('loadUserData  start   ');
		return row.medicalReportDef.templateName;
	}
	return -1;
}


sys.getSelections = function(component) {
	var ss = [];
	var rows = $(component).datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ss.push(row.opOrder );
	}
	//$.messager.alert('Info', ss.join(','));
	
	return ss;
}

sys.searchbox = function() {
	$('#user_searchBox').searchbox('setValue','');
	$('#userGrid').datagrid('load',{});
}
