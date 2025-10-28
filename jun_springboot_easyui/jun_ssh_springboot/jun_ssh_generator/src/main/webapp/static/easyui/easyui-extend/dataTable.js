/**
 * easyui-表格封装，不依赖其他js库
 * @author klguang
 */

/**
 * 
 * 初始化：
  var dataTable = new DataTable({
	$datagrid_table : "",
	$data_form_dialog : "",
	$data_form : "",// 可不配置 
	data_form_name : "",

	addOpt : {
		url : ""
	},
	editOpt : {
		url : ""
	},
	removeOpt : {
		url : "",
	},
	saveOpt : {},
	searchOpt : {
		searchForm : "",
	}
})
 * 
 * 返回json数据必须用JsonDate对象
 * 
 * 注意remove是根据idField进行删除的。
 * 
 */
function DataTable(params) {
	if(!params)
		return this;
	// 构造器
	this.$datagrid_table = params.$datagrid_table;
	this.$data_form_dialog = params.$data_form_dialog;
	this.$data_form = !params.$data_form ? null : params.$data_form;
	this.data_form_name = params.data_form_name;

	this.addOpt = params.addOpt;
	this.editOpt = params.editOpt;
	this.saveOpt = params.saveOpt;
	this.removeOpt = params.removeOpt;
	this.searchOpt = params.searchOpt;
	//_saveFlag 1为正常，0为保存中
	this._saveFlag = 1;
	this._saveType = null;
	
	this.datagrid = function(options, param) {
		this.$datagrid_table.datagrid(options, param);
	};

	this.add = function(callback){add(this,callback);};
	this.edit = function(callback){edit(this,callback);};
	this.save = function(callback){save(this,callback);};
	this.remove = function(callback){remove(this,callback);};
	this.search = function(callback){search(this,callback);};
	//动态url
	this._getHandlerURL=function(){return getHandlerURL(this);};
	function getHandlerURL(target){
		if(target._saveType=="add"){
			return target.addOpt.url||target.addOpt.urlFn();
		}else if(target._saveType=="edit"){
			return target.editOpt.url||target.editOpt.urlFn();
		}
	}
	// ------------------------CRUD 函数---------------------------

	function add(target,callback) {
		target._saveType="add";
		var addOpt=target.addOpt;
		if (!addOpt || !(addOpt.url||addOpt.urlFn)) {
			$.messager.show({
				title : '非法操作',
				msg : "禁止添加！"
			});
			return;
		}
		if(addOpt.alertValidation && ! addOpt.alertValidation()){
			return;
		}
		
		if (addOpt.title)
			target.$data_form_dialog.dialog('open').dialog('setTitle', addOpt.title);
		else
			target.$data_form_dialog.dialog('open').dialog('setTitle', "增加-" + target.data_form_name);
		if (target.$data_form&&!addOpt.notReset) {
			target.$data_form.form('reset');
		}
		if (addOpt.afterOpenDlg){
			addOpt.afterOpenDlg(target.$data_form);			
		}
		
		if(callback)
			callback();
		target._saveFlag = 1;
	}

	function edit(target,callback) {
		target._saveType="edit";
		var editOpt=target.editOpt;
		if (!editOpt || !editOpt.url) {
			$.messager.show({
				title : '非法操作',
				msg : "禁止修改！"
			});
			return;
		}
		
		// row 是后台一个完整的实体的json
		var row = target.$datagrid_table.datagrid('getSelected');
		if (row) {
			if (editOpt.title)
				target.$data_form_dialog.dialog('open').dialog('setTitle', editOpt.title);
			else
				target.$data_form_dialog.dialog('open').dialog('setTitle', "修改-" + target.data_form_name);
			if (target.$data_form) {
				//重置
				target.$data_form.form('reset');				
				try{
					// 将json加载到form里
					target.$data_form.form("myLoad", row);	
				}catch(e){
					console.log(e.name + ": " + e.message);
				}
			}
			if (editOpt.loadHandler) {
				editOpt.loadHandler(row);
			}
			if(callback)
				callback(row);
			target._saveFlag = 1;
		}else{
			$.messager.alert("提示","请选择一行记录！");
		}
	}

	function save(target,callback) {
		var saveOpt=target.saveOpt;
		if (!target._saveFlag) {
			$.messager.show({
				title : '提示',
				msg : "请不要重复保存！"
			});
			return;
		}
		// 校验表单
		if (saveOpt.validation && !saveOpt.validation())
			return;
		// 默认表单校验
		if (target.$data_form&&!target.$data_form.form('validate'))
			return;
		
		// 防止重复提交
		target._saveFlag = 0;
		var params=saveOpt.paramsFn ? saveOpt.paramsFn(target.$data_form) : target.$data_form.serializeJson();
		var ajaxSetting={
				url : target._getHandlerURL(),
				// data : target.$data_form.serialize(),//jquery的serialize()序列号表单
				data : params,
				type : "POST",
				dataType : "json",
				success : function(result) {
					//处理自定义行为
					if (saveOpt.onSuccess)
						saveOpt.onSuccess(result);
					if(callback)
						callback(result);
					
					if (result.type == "success") {						
						if(!saveOpt.notReload){
							target.$datagrid_table.datagrid('reload',saveOpt.reloadParamsFn?saveOpt.reloadParamsFn():null); // reload
						}
					} else if (result.type == "paramsHasNull") {
						$.messager.show({
							title : '更新失败',
							msg : "必须字段不能为空！"
						});
					} else if (result.type == "repeatedlyAdd") {
						$.messager.show({
							title : 'Error',
							msg : "重复添加！"
						});
					} else {
						$.messager.show({
							title : '更新失败',
							msg : result.message
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.show({
						title : '更新失败',
						msg : "系统异常！"
					});
				},
				complete : function(XMLHttpRequest, textStatus) {
					if (!saveOpt.notClose){
						target.$data_form_dialog.dialog('close'); // close the dialog
					}
					target._saveFlag = 1;					
				}
			};
		//修改默认的ajax选项
		if(saveOpt.ajaxSetting)
			$.extend(ajaxSetting,saveOpt.ajaxSetting);
		$.ajax(ajaxSetting);
	}

	function remove(target,callback) {
		var removeOpt=target.removeOpt;
		if (!removeOpt || !removeOpt.url) {
			$.messager.show({
				title : '非法操作',
				msg : "禁止删除！"
			});
			return;
		}
		console.log("$datagrid_table"+target.$datagrid_table.selector);
		var row = target.$datagrid_table.datagrid('getSelected');

		if (!row) {
			$.messager.alert("提示", "请选择一条记录！");
			return;
		}
		// 校验表单
		if (removeOpt.validation && !removeOpt.validation(row))
			return;
		if (!removeOpt.idField)
			removeOpt.idField = target.$datagrid_table.datagrid("options").idField;
		var idParam = "{\"" + removeOpt.idField + "\":\"" + row[removeOpt.idField] + "\"}";				
		console.log(idParam);
		if (row) {
			$.messager.confirm('确认', '确定要删除这条信息吗?', function(r) {
				if (r) {
					$.post(removeOpt.url, JSON.parse(idParam), function(result) {
						//处理自定义行为
						if (removeOpt.onSuccess){
							removeOpt.onSuccess(result);
						}
						if(callback)
							callback(result);
						if (result.type == "success") {
							if(!removeOpt.notReload){
								target.$datagrid_table.datagrid('reload',removeOpt.reloadParamsFn?removeOpt.reloadParamsFn():null); // reload
							}
						} else {
							$.messager.show({ // show error message
								title : '删除失败',
								msg : result.message
							});
						}
					}, 'json');
				}
			});
		}
	}
	/**
	 * 查詢
	 */
	function search(target) {
		var searchOpt=target.searchOpt;
		if (!searchOpt ||!searchOpt.$searchForm)
			return;
		
		if (searchOpt.paramsFn)
			target.$datagrid_table.datagrid("load", searchOpt.paramsFn(searchOpt.$searchForm));
		else{
			target.$datagrid_table.datagrid("load", searchOpt.$searchForm.serializeJson());
		}
	}
}

// 默认去除表单元素未填写的字段，nullable=true保留
$.fn.serializeJson = function(nullable) {
	var serializeObj = {};
	var array = this.serializeArray();
	$(array).each(function() {
		if (!nullable&&this.value == "") // 去除表单元素未填写的字段
			return;
		if (serializeObj[this.name]) {
			if ($.isArray(serializeObj[this.name])) {
				serializeObj[this.name].push(this.value);
			} else {
				serializeObj[this.name] = [ serializeObj[this.name], this.value ];
			}
		} else {
			serializeObj[this.name] = this.value;
		}
	});
	return serializeObj;
};

function mergeFn(fn1,fn2){
	var target=null;
	if(fn1||fn2){
		target=function(params){
			if(fn1)
				fn1(params);
			if(fn2)
				fn2(params);
		};
	}
	return 	target;
}

// ----------------------------------BaseTable-------------------------
/**
 * 继承自DataTable
   var dataTable = new DefaultTable({
   	containerId:"project-crud",
	data_form_name : "",

	addOpt : {
		url : ""
	},
	editOpt : {
		url : ""
	},
	removeOpt : {
		url : "",
	},
	saveOpt : {},
	//不要配置searchForm
	searchOpt : {
		paramsFn:funciton(){}
	}
})
 */

function DefaultTable(option){
	if(!option.containerId)
		throw new Error("必须定义容器!");
	option.datagrid_table_id=option.containerId+"-datagrid-table";
	option.toolbar_id=option.containerId+"-toolbar";
	option.data_form_dialog_id=option.containerId+"-data-form-dlg";
	option.dlg_buttons_id=option.containerId+"-dlg-buttons";
	option.dlg_body_id=option.containerId+'-dlg-body';	
	
	option.$container=$("#"+option.containerId);	
	this.tableOpt=option.tableOpt;
	this.$container=option.$container;
	this.option=option;
	gen(this);

	option.$datagrid_table = $("#"+option.datagrid_table_id);
	option.$data_form_dialog = $("#"+option.data_form_dialog_id);
	option.$data_form = $("#"+option.dlg_body_id).find(".data-form");
	option.$search_form=$("#"+option.toolbar_id).find(".search-form");
	//后面优先级最高
	option.searchOpt=$.extend({},option.searchOpt,{$searchForm:option.$search_form});

	DataTable.call(this, option);
	
	this.tablePanel=function(method){tablePanel(this,method);};
	
	datagrid(this);
	bindEvent(this);
	function datagrid(target){
		var tableOpt_default={
				toolbar			: "#"+target.option.toolbar_id,
				onDblClickRow   : function(){
					target.edit();
				}
		};
		if(target.tableOpt)
			$.extend(tableOpt_default,target.tableOpt)
		if(!tableOpt_default.notAutoLoad)
			console.log(target.$datagrid_table.selector)
			$("#"+option.datagrid_table_id).datagrid(tableOpt_default).datagrid("resize");	
	}
	
	function bindEvent(target){
		var $toolbar=$("#"+target.option.toolbar_id);
		var $dlg_buttons=$("#"+target.option.dlg_buttons_id);
		$toolbar.find("a[iconCls='icon-search']")
			.on("click",function(){
				target.search();
			});
		$toolbar.find("a[iconCls='icon-add']")
			.on("click",function(){
				target.add();
			});
		$toolbar.find("a[iconCls='icon-edit']")
			.on("click",function(){
				target.edit();
			});
		$toolbar.find("a[iconCls='icon-remove']")
			.on("click",function(){
				target.remove();
			});
		
		$dlg_buttons.find("a[iconCls='icon-ok']")
			.on("click",function(){
				target.save();
			});		
	}
	
	function tablePanel(target,method){
		target.$container.find(".easyui-datagrid").datagrid('getPanel');
	}
	
	function gen(target){
		$.extend($.fn.datagrid.defaults,{
			fit				: true,
			pagination		: true,
			pageSize		: 15,
			pageList        : [10,15,20,25,30],
			showFooter		: true,
			idField			: 'id',
			rownumbers		: true,
			singleSelect	: true
		});
		var option=target.option;
		var $container=target.$container;
		var htmls='';
		
		htmls+=$container.find(".easyui-datagrid").attr("id",option.datagrid_table_id)[0].outerHTML;
		var $toolbar=$('<div id="'+option.toolbar_id+'"></div>');
		if($container.find(".search-form").length>0){
			$toolbar.append($container.find(".search-form").append('<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" >查询</a>'));
			
		}
		
		var $crud_toolbar=$("<p class='crud-toolbar' style='display:inline-block;padding-left:5px;margin:0'></p>");
		if(option.addOpt){
			$crud_toolbar.append('<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>');
		}
		if(option.editOpt){
			$crud_toolbar.append('<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>');
		}
		if(option.removeOpt){
			$crud_toolbar.append('<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>');
		}
		if($toolbar.find(".search-form").length>0)
			$toolbar.find(".search-form").append($crud_toolbar);
		else
			$toolbar.append($crud_toolbar);
		htmls+=$toolbar[0].outerHTML;
				
		if($container.find(".dlg-body").length>0){
			htmls+='<div id="'+option.data_form_dialog_id+'" class="easyui-dialog" style="width: 600px; height: 400px; padding: 10px 20px" closed="true" buttons="#'+option.dlg_buttons_id+'" maximizable="true">';
			htmls+=$container.find(".dlg-body").attr("id",option.dlg_body_id)[0].outerHTML;
			htmls+='</div>';
			
			htmls+='<div id="'+option.dlg_buttons_id+'">';
			htmls+='	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok">保存</a>';
			htmls+='	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$(\'#'+option.data_form_dialog_id+'\').dialog(\'close\')">取消</a>';
			htmls+='</div>';
		}
		$container.html(htmls);
		console.log($container[0]);
	}
}

DefaultTable.prototype=new DataTable();

