var sys = sys || {};


sys.showUserDataFun = function(id) {
	//alert('showUserDataFun ' + id)
	var dialog = parent.sy.modalDialog({
		title : '查看用户信息',
		url : sy.contextPath + '/securityJsp/base/SyuserForm.jsp?id=' + id
	});
};

/* 加载用户数据  */
sys.loadRoleUsersFun = function(id) {
	//alert('loadRoleUsersFun    ');
	var userGrid = $('#userGrid').datagrid({
		title : '',
		url : sy.contextPath + '/base/syuser!grid.sy?id='+id,
		striped : false,
		rownumbers : true,
		pagination : true,
		singleSelect : false,
		idField : 'id',
		sortName : 'createdatetime',
		sortOrder : 'desc',
		pageSize : 50,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		frozenColumns : [ [ 
		                     {
				     checkbox:true,
					width : '100',
					title : '选择',
					field : 'ID',
					sortable : true,
					formatter : function(value, row, index) {
						 
						//alert(value);
					}
				} 
			,{
			width : '100',
			title : '登录名',
			field : 'loginname',
			sortable : true
		}, {
			width : '80',
			title : '姓名',
			field : 'name',
			sortable : true
		} ] ],
		columns : [ [ {
			width : '150',
			title : '创建时间',
			field : 'createdatetime',
			sortable : true
		}, {
			width : '150',
			title : '修改时间',
			field : 'updatedatetime',
			sortable : true
		}, {
			width : '50',
			title : '性别',
			field : 'sex',
			sortable : true,
			formatter : function(value, row, index) {
				switch (value) {
				case '0':
					return '女';
				case '1':
					return '男';
				}
			}
		}, {
			width : '50',
			title : '年龄',
			field : 'age',
			hidden : true
		}, {
			width : '250',
			title : '照片',
			field : 'photo',
			formatter : function(value, row) {
				if(value){
					return sy.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			title : '操作',
			field : 'action',
			width : '80',
			formatter : function(value, row) {
				var str = '';
				if(checkRoleUsersHasPriv()){
					var bt = systool.createFuncButton('ext-icon-note','查看','查看用户资料','sys.showUserDataFun(\'{0}\');');
					str +=  sy.formatString(bt,row.id);
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
			
			//alert(userGrid);alert($('#userGrid'));alert(this);alert(data.total);alert(data.rows[0].id);
			 
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
	//alert('loadUserData  start   ');
	if (row) {
//		$.messager.alert('Info', row.name + ":" + row.id + ":"
//				+ row.attr1);
		//alert('loadUserData  start   ');
		sys.loadUserData(row.id);
	}
}

sys.getSelectedId = function(component) {
	var row = $(component).datagrid('getSelected');
	if (row) {
//		$.messager.alert('Info', row.name + ":" + row.id + ":"
//				+ row.attr1);
		//alert('loadUserData  start   ');
		return row.id;
	}
	return -1;
}

sys.getSelections = function(component) {
	var ss = [];
	var rows = $(component).datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		var row = rows[i];
		ss.push(row.id );
	}
	$.messager.alert('Info', ss.join(','));
	
	return ss;
}

sys.searchbox = function() {
	$('#user_searchBox').searchbox('setValue','');
	$('#userGrid').datagrid('load',{});
}
