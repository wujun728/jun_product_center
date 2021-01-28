var sy = sy || {};

/**
 * 更改easyui加载panel时的提示文字
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.panel.defaults, {
	loadingMessage : '加载中....'
});

/**
 * 更改easyui加载grid时的提示文字
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.datagrid.defaults, {
	loadMsg : '数据加载中....'
});

/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
sy.onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, sy.onMove);
$.extend($.fn.window.defaults, sy.onMove);
$.extend($.fn.panel.defaults, sy.onMove);

/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
sy.onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, sy.onLoadError);
$.extend($.fn.treegrid.defaults, sy.onLoadError);
$.extend($.fn.tree.defaults, sy.onLoadError);
$.extend($.fn.combogrid.defaults, sy.onLoadError);
$.extend($.fn.combobox.defaults, sy.onLoadError);
$.extend($.fn.form.defaults, sy.onLoadError);

/**
 * 扩展combobox在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combobox.defaults, {
	onShowPanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combobox('textbox').val();
			var _combobox = $(this);
			if (_value.length > 0) {
				$.post(_options.url, {
					q : _value
				}, function(result) {
					if (result && result.length > 0) {
						_combobox.combobox('loadData', result);
					}
				}, 'json');
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combobox('getData');/* 下拉框所有选项 */
			var _value = $(this).combobox('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.valueField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combobox('setValue', '');
			}
		}
	}
});

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
	onShowPanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _value = $(this).combogrid('textbox').val();
			if (_value.length > 0) {
				$(this).combogrid('grid').datagrid("load", {
					q : _value
				});
			}
		}
	},
	onHidePanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
			var _value = $(this).combogrid('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.idField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combogrid('setValue', '');
			}
		}
	}
});

/**
 * 扩展validatebox，添加新的验证功能
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {/* 验证两次密码是否一致功能 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
sy.loadFilter = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, sy.loadFilter);
$.extend($.fn.tree.defaults, sy.loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});

/**
 * 创建一个模式化的dialog
 * @requires jQuery,EasyUI
 * 
 */
sy.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};

sy.modalListFormDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 1000,
		height : 600,
		modal : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};

/**
 * 等同于原form的load方法，但是这个方法支持{data:{name:''}}形式的对象赋值
 */
$.extend($.fn.form.methods, {
	loadData : function(jq, data) {
		return jq.each(function() {
			load(this, data);
		});

		function load(target, data) {
			if (!$.data(target, 'form')) {
				$.data(target, 'form', {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var opts = $.data(target, 'form').options;

			if (typeof data == 'string') {
				var param = {};
				if (opts.onBeforeLoad.call(target, param) == false)
					return;

				$.ajax({
					url : data,
					data : param,
					dataType : 'json',
					success : function(data) {
						_load(data);
					},
					error : function() {
						opts.onLoadError.apply(target, arguments);
					}
				});
			} else {
				_load(data);
			}
			function _load(data) {
				var form = $(target);
				var formFields = form.find("input[name],select[name],textarea[name]");
				formFields.each(function() {
					var name = this.name;
					var value = jQuery.proxy(function() {
						try {
							return eval('this.' + name);
						} catch (e) {
							return "";
						}
					}, data)();
					var rr = _checkField(name, value);
					if (!rr.length) {
						var f = form.find("input[numberboxName=\"" + name + "\"]");
						if (f.length) {
							f.numberbox("setValue", value);
						} else {
							$("input[name=\"" + name + "\"]", form).val(value);
							$("textarea[name=\"" + name + "\"]", form).val(value);
							$("select[name=\"" + name + "\"]", form).val(value);
						}
					}
					_loadCombo(name, value);
				});
				opts.onLoadSuccess.call(target, data);
				$(target).form("validate");
			}

			function _checkField(name, val) {
				var rr = $(target).find('input[name="' + name + '"][type=radio], input[name="' + name + '"][type=checkbox]');
				rr._propAttr('checked', false);
				rr.each(function() {
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), val) >= 0) {
						f._propAttr('checked', true);
					}
				});
				return rr;
			}

			function _loadCombo(name, val) {
				var form = $(target);
				var cc = [ 'combobox', 'combotree', 'combogrid', 'datetimebox', 'datebox', 'combo' ];
				var c = form.find('[comboName="' + name + '"]');
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var type = cc[i];
						if (c.hasClass(type + '-f')) {
							if (c[type]('options').multiple) {
								c[type]('setValues', val);
							} else {
								c[type]('setValue', val);
							}
							return;
						}
					}
				}
			}
		}
	}
});

/**
 * 更换主题
 * 
 * @author 孙宇
 * @requires jQuery,EasyUI
 * @param themeName
 */
sy.changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	 
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);
	console.log(href)
	//自定义样式
	var $myeasyuiTheme = $('#myeasyuiTheme');
	var mycssurl = $myeasyuiTheme.attr('href');
	//console.log(mycssurl)
	 
	var href2 = mycssurl.substring(0, mycssurl.indexOf('style')) + 'style/' + themeName + '/syExtCss.css';
	$myeasyuiTheme.attr('href', href2);
	console.log(href)
	
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			try {
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
				$(ifr).contents().find('#$myeasyuiTheme').attr('href', href2);
			} catch (e) {
				try {
					ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
					ifr.contentWindow.document.getElementById('$myeasyuiTheme').href = href2;
				} catch (e) {
				}
			}
		}
	}

	sy.cookie('easyuiTheme', themeName, {
		expires : 7
	});
	sy.cookie('myeasyuiTheme', themeName, {
		expires : 7
	});
};



/**
 * 更换语言
 */
sy.changeLang = function(themeName) {
	
	
};


/**
 * 滚动条
 * 
 * @author 孙宇
 * @requires jQuery,EasyUI
 */
sy.progressBar = function(options) {
	if (typeof options == 'string') {
		if (options == 'close') {
			$('#syProgressBarDiv').dialog('destroy');
		}
	} else {
		if ($('#syProgressBarDiv').length < 1) {
			var opts = $.extend({
				title : '&nbsp;',
				closable : false,
				width : 300,
				height : 60,
				modal : true,
				content : '<div id="syProgressBar" class="easyui-progressbar" data-options="value:0"></div>'
			}, options);
			$('<div id="syProgressBarDiv"/>').dialog(opts);
			$.parser.parse('#syProgressBarDiv');
		} else {
			$('#syProgressBarDiv').dialog('open');
		}
		if (options.value) {
			$('#syProgressBar').progressbar('setValue', options.value);
		}
	}
};


/*1、级联勾选：不包括未加载的子节点
2、深度级联勾选：包括未加载的子节点
两种思路：
1、扩展个新方法cascadeCheck，当需要进行级联勾选时，调用该方法进行级联勾选或不勾选。
2、扩展onLoadSuccess方法，添加一个自定义属性：cascadeCheck（级联）或deepCascadeCheck（深度级联），通过监听checkbox的click事件判断是否级联或深度级联来进行级联勾选或不勾选。
*/
/** 
 * 扩展树表格级联勾选方法： 
 * @param {Object} container 
 * @param {Object} options 
 * @return {TypeName}  
 */  
$.extend($.fn.treegrid.methods,{  
    /** 
     * 级联选择 
     * @param {Object} target 
     * @param {Object} param  
     *      param包括两个参数: 
     *          id:勾选的节点ID 
     *          deepCascade:是否深度级联 
     * @return {TypeName}  
     */  
    cascadeCheck : function(target,param){  
        var opts = $.data(target[0], "treegrid").options;  
        if(opts.singleSelect)  
            return;  
        var idField = opts.idField;//这里的idField其实就是API里方法的id参数  
        var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选  
        var selectNodes = $(target).treegrid('getSelections');//获取当前选中项  
        for(var i=0;i<selectNodes.length;i++){  
            if(selectNodes[i][idField]==param.id)  
                status = true;  
        }  
        //级联选择父节点  
        selectParent(target[0],param.id,idField,status);  
        selectChildren(target[0],param.id,idField,param.deepCascade,status);  
        /** 
         * 级联选择父节点 
         * @param {Object} target 
         * @param {Object} id 节点ID 
         * @param {Object} status 节点状态，true:勾选，false:未勾选 
         * @return {TypeName}  
         */  
        function selectParent(target,id,idField,status){  
            var parent = $(target).treegrid('getParent',id);  
            if(parent){  
                var parentId = parent[idField];  
                if(status)  
                    $(target).treegrid('select',parentId);  
                else  
                    $(target).treegrid('unselect',parentId);  
                selectParent(target,parentId,idField,status);  
            }  
        }  
        /** 
         * 级联选择子节点 
         * @param {Object} target 
         * @param {Object} id 节点ID 
         * @param {Object} deepCascade 是否深度级联 
         * @param {Object} status 节点状态，true:勾选，false:未勾选 
         * @return {TypeName}  
         */  
        function selectChildren(target,id,idField,deepCascade,status){  
            //深度级联时先展开节点  
            if(!status&&deepCascade)  
                $(target).treegrid('expand',id);  
            //根据ID获取下层孩子节点  
            var children = $(target).treegrid('getChildren',id);  
            for(var i=0;i<children.length;i++){  
                var childId = children[i][idField];  
                if(status)  
                    $(target).treegrid('select',childId);  
                else  
                    $(target).treegrid('unselect',childId);  
                selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点  
            }  
        }  
    }  
});  


/** 
 * 扩展树表格级联选择（点击checkbox才生效）： 
 *      自定义两个属性： 
 *      cascadeCheck ：普通级联（不包括未加载的子节点） 
 *      deepCascadeCheck ：深度级联（包括未加载的子节点） 
 */  
$.extend($.fn.treegrid.defaults,{  
    onLoadSuccess : function() {  
        var target = $(this);  
        var opts = $.data(this, "treegrid").options;  
        var panel = $(this).datagrid("getPanel");  
        var gridBody = panel.find("div.datagrid-body");  
        var idField = opts.idField;//这里的idField其实就是API里方法的id参数  
        gridBody.find("div.datagrid-cell-check input[type=checkbox]").unbind(".treegrid").click(function(e){  
            if(opts.singleSelect) return;//单选不管  
            if(opts.cascadeCheck||opts.deepCascadeCheck){  
                var id=$(this).parent().parent().parent().attr("node-id");  
                var status = false;  
                if($(this).attr("checked")) status = true;  
                //级联选择父节点  
                selectParent(target,id,idField,status);  
                selectChildren(target,id,idField,opts.deepCascadeCheck,status);  
                /** 
                 * 级联选择父节点 
                 * @param {Object} target 
                 * @param {Object} id 节点ID 
                 * @param {Object} status 节点状态，true:勾选，false:未勾选 
                 * @return {TypeName}  
                 */  
                function selectParent(target,id,idField,status){  
                    var parent = target.treegrid('getParent',id);  
                    if(parent){  
                        var parentId = parent[idField];  
                        if(status)  
                            target.treegrid('select',parentId);  
                        else  
                            target.treegrid('unselect',parentId);  
                        selectParent(target,parentId,idField,status);  
                    }  
                }  
                /** 
                 * 级联选择子节点 
                 * @param {Object} target 
                 * @param {Object} id 节点ID 
                 * @param {Object} deepCascade 是否深度级联 
                 * @param {Object} status 节点状态，true:勾选，false:未勾选 
                 * @return {TypeName}  
                 */  
                function selectChildren(target,id,idField,deepCascade,status){  
                    //深度级联时先展开节点  
                    if(status&&deepCascade)  
                        target.treegrid('expand',id);  
                    //根据ID获取下层孩子节点  
                    var children = target.treegrid('getChildren',id);  
                    for(var i=0;i<children.length;i++){  
                        var childId = children[i][idField];  
                        if(status)  
                            target.treegrid('select',childId);  
                        else  
                            target.treegrid('unselect',childId);  
                        selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点  
                    }  
                }  
            }  
            e.stopPropagation();//停止事件传播  
        });  
    }  
});  

$.extend($.fn.datagrid.defaults.editors, {
	    combogrid: {
	        init: function(container, options){
	            var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
	            input.combogrid(options);
	            return input;
	        },
	        destroy: function(target){
	            $(target).combogrid('destroy');
	        },
	        getValue: function(target){
	        	//console.log("getValue:" + target);
	            return $(target).combogrid('getValue');
	        },
	        setValue: function(target, value){
	        	//console.log("setValue:" + target + " value:" + value);
	        	//console.log(target)
	        	//console.log(value)
	            $(target).combogrid('setValue', value);
	        },
	        resize: function(target, width){
	            $(target).combogrid('resize',width);
	        }
	    }
	});

 