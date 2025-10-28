$.dictManager={
		getCodeItemName : function(cls, code, callback) {
			if(!dictJson[cls])
				return code;
			var items=dictJson[cls];
			for(var i=0;i<items.length;i++){
				if(items[i].code==code){
					return items[i].name;					
				}
			}
			return code;
		},
		getCodeItems:function(cls){
			return dictJson[cls]; 
		}
};


//-------------  easyui col format ------------------------

function complexCol(value, row, index) {
	if (this.field.indexOf('.') == -1)
		return value;
	var fields = this.field.split('.');
	var currentRef = row;
	for (var i = 0; i < fields.length; i++) {
		currentRef = currentRef[fields[i]];
		if (currentRef == null)
			break;
	}

	// value = eval("row['"+field.replace(/\./g,"']['")+"']");
	// alert(tmp);
	return currentRef;
}


function EasyUiDateTime(value, row, index){
	if (this.field.indexOf('.') == -1)
		return calUtil.formatDateTime(value);
	var fields = this.field.split('.');
	var currentRef = row;
	for (var i = 0; i < fields.length; i++) {
		currentRef = currentRef[fields[i]];
		if (currentRef == null)
			break;
	}
	return calUtil.formatDateTime(currentRef);
}

function EasyUiDate(value, row, index){
	if (this.field.indexOf('.') == -1)
		return calUtil.formatDate(value);
	var fields = this.field.split('.');
	var currentRef = row;
	for (var i = 0; i < fields.length; i++) {
		currentRef = currentRef[fields[i]];
		if (currentRef == null)
			break;
	}
	return calUtil.formatDate(currentRef);
}
//highlightCode 可选；highlightStyle可选，默认红色
//<th data-options="field:'depotid',width:140,align:'left',formatter:codeCol,codeClass:'57',highlightCode:'2'">仓库</th>
function codeCol(value, row, index){
	//获取配置在field options中codeClass，this是字段field options
	var codeClass=this.codeClass;
	var highlightCode=this.highlightCode;
	var highlightStyle=this.highlightStyle;
	
	var codeName = null;
	
	if (this.field.indexOf('.') == -1){
		codeName = $.dictManager.getCodeItemName(codeClass,value);		
	}else{
		var fields = this.field.split('.');
		var currentRef = row;
		for (var i = 0; i < fields.length; i++) {
			currentRef = currentRef[fields[i]];
			if (currentRef == null)
				break;
		}
		codeName = $.dictManager.getCodeItemName(codeClass,currentRef);		
	}
	if(highlightCode && value==highlightCode){
		if(!highlightStyle){
			highlightStyle="color:red"
		}
		codeName = "<div style='"+highlightStyle+"'>" + codeName + "</div>";
	}
	return codeName;
}



function resetForm(form_id,callback){
	$("#"+form_id).form("reset");
	if(callback)
		callback();
}

//在导出excel时有用到
function postParams(URL, PARAMS) {      
    var temp = document.createElement("form");      
    temp.action = URL;      
    temp.method = "post";      
    temp.style.display = "none";      
    for (var x in PARAMS) {      
        var opt = document.createElement("textarea");
        opt.name = x;      
        opt.value = PARAMS[x];      
        // alert(opt.name)      
        temp.appendChild(opt);      
    }      
    document.body.appendChild(temp);      
    temp.submit();      
    return temp;      
}

//----------------checkbox_editor--------------------------

var ag_checkbox_editor={  
        type:'checkboxCell',  
        options:{  
            on: "yes",  
            off: "no"  
        }  
    }; 

function checkboxCol(value, row, index){
	if(value == "yes"){
		
		return '<input onclick="javascript:return false;" checked=true readonly=true style="width:15px;height:18px" type="checkbox">';
	}else{
		return '<input onclick="javascript:return false;" readonly=true style="width:15px;height:18px" type="checkbox">';
	}
}

$.extend($.fn.datagrid.defaults.editors, {
	checkboxCell: {
		init: function(container, options){
			var input = $('<input style="width:15px;height:18px" type="checkbox">').appendTo(container);
			input.val(options.on);
			input.attr('offval', options.off);
			return input;
		},
		getValue: function(target){
			if ($(target).is(':checked')){
				return $(target).val();
			} else {
				return $(target).attr('offval');
			}
		},
		setValue: function(target, value){
			var checked = false;
			if ($(target).val() == value){
				checked = true;
			}
			$(target)._propAttr('checked', checked);
		}
	}
});	

/**
 *  springmvc requestBody
 * @param opt
 */

function ajaxRequestBody(opt){
	$.ajax({
		url			: opt.url,
		/**必须是POST方法**/
		type		: 'post',
		/**必须制定请求的类型**/
		contentType	: 'application/json;charset=utf-8',
		data		: JSON.stringify(opt.data),
		dataType	: 'json',

		success		: function(json){
			if(json.type == "success"){
				$.messager.show({
					title : '提示',
					msg : "保存成功！"
				});
			}
		}
	});
}

//easy ui 解析完成调用

$.parser.addParseComplete=function(func){
	var old=$.parser.onComplete;
	if(!old)
		$.parser.onComplete=func;
	else{
		$.parser.onComplete=function(){
			old();
			func();
		}
	}
}

function ajaxResulthandler(json , successCall){
	if(json.type == "success"){
		successCall(json);
	}else{
        $.messager.show({
        	title : "操作失败",
        	msg : json.message
        })
	}
}
