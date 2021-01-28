<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/dbmap.js"></script>
<style type="text/css">
/* .var select,.var input{
height:20px;
width:150px;
} */
</style>
<script type="text/javascript">
	function addVar() {
		var idNum;
		var row1;
		row1 = $('#vars .var:last');//获得最后一行 -- 设置jquery对象(待克隆的div)
		//如果row1为空
		if (row1.length == 0) {
			idNum = 1;
			row1 = $("#var_" + (idNum - 1));
		} else {
			idNum = Number(row1.attr('id').split('_')[row1.attr('id')
					.split('_').length - 1]) + 1;
		}
		var newRow = row1.clone(true); //创建最后一行的一个副本
		// newRow.insertBefore(row1);	//在最后一行前插入
		newRow.insertAfter(row1); //在最后一行后插入	

		var nVarID = 'var_' + idNum;
		var nVarWheres = nVarID + '_Where';
		var nVarWhereBtn = nVarWheres + "Btn";
		var nVarWherePre = nVarID + '_Where';

		//var _prevVarWhere=row1.id+ '_Where';

		//var rndID = "varWhere" + idNum;
		var rndID = nVarWherePre;

		newRow.attr("id", nVarID); //设置行ID 每次都不一样 
		newRow.find('input[name="varWhereBtn"]').attr("id", nVarWhereBtn); //设置行ID 每次都不一样 
		newRow.find('div[name="varWheres"]').attr("id", nVarWheres); //设置行ID 每次都不一样 
		//给各个 select input 加上不同的id

		$.each(newRow.find("div[name='varWhere']"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_' + index);
		});

		/* $.each(newRow.find("select.tabNamesSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Tab_' + index);
		}); */

		$.each(newRow.find("select.colNamesSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Col_' + index);
		});

		$.each(newRow.find("select.relationsSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Op_' + index);
		});

		$.each(newRow.find("input.colLimit"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Val_' + index);
		});

		$.each(newRow.find("a[name='delWhere']"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Del_' + index);
		});
		//nVarID
		newRow.find("a[name='delVar']").attr("id", nVarID + '_Del');

		newRow.find("input[name='varDesc']").attr('id', 'varDesc' + idNum);
		newRow.find("input[name='varName']").attr('id', 'varName' + idNum);
		newRow.find("select.valFunsSelect").attr('id', 'varFun' + idNum);

		newRow.find("select[name='varTab']").attr('id', nVarID + '_Tab');
		newRow.find("select[name='varField']").attr('id', nVarID + '_Field');

		//拷贝设值
		$.each(newRow.find("select.tabNamesSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.tabNamesSelect")[index]).val());
		});

		$.each(newRow.find("select.colNamesSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.colNamesSelect")[index]).val());
		});

		$.each(newRow.find("select.relationsSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.relationsSelect")[index]).val());
		});

		//初始值为空

		//注册事件
		newRow.find("a[name='delVar']").click(function() {
			delRow(this);
		});
		// 新加行显示删除按键
		newRow.find("a[name='delVar']").html(
				" <span style='color: blue;'>删除</span>");
		//显示克隆出的新行数据
		newRow.show();
	}

	function delVar(me) {
		$("#" + me.id).remove();
	}

	//动态添加行
	function addRow(me) {
		var idNum;
		var row1;
		var pre = me.id.replace('Btn', '');
		row1 = $('#' + pre + ' div:last');//获得最后一行 -- 设置jquery对象(待克隆的div)
		//如果row1为空
		if (row1.length == 0) {
			idNum = 1;
			row1 = $('#' + pre + "_" + (idNum - 1));
		} else {
			idNum = Number(row1.attr('id').split('_')[row1.attr('id')
					.split('_').length - 1]) + 1;
		}
		var newRow = row1.clone(true); //创建最后一行的一个副本
		// newRow.insertBefore(row1);	//在最后一行前插入
		newRow.insertAfter(row1); //在最后一行后插入	  
		var rndID = pre;
		newRow.attr("id", pre + "_" + idNum); //设置行ID 每次都不一样 

		//给各个 select input 加上不同的id
		newRow.find("select.tabNamesSelect").attr('id', pre + '_Tab_' + idNum);
		newRow.find("select.colNamesSelect").attr('id', pre + '_Col_' + idNum);
		newRow.find("select.relationsSelect").attr('id', pre + '_Op_' + idNum);
		newRow.find("input.colLimit").attr('id', pre + '_Val_' + idNum);
		newRow.find("a").attr('id', pre + '_Del_' + idNum);

		//设置选中
		newRow.find("select.tabNamesSelect").val(
				row1.find("select.tabNamesSelect").val());
		newRow.find("select.colNamesSelect").val(
				row1.find("select.colNamesSelect").val());
		newRow.find("select.relationsSelect").val(
				row1.find("select.relationsSelect").val());
		//初始值为空

		//给新增的每一行内的删除加上删除事件
		newRow.find("a").click(function() {
			delRow(this);
		});
		// 新加行显示删除按键
		newRow.find("a").html(" <span style='color: blue;'>删除</span>");
		//显示克隆出的新行数据
		newRow.show();
	}

	//动态删除行
	function delRow(who) {
		$("#" + who.id.replace('_Del', '')).remove();
	}

	function initTab(who) {
		var index = 0;
		var _op = "";
		$.each(doroodo_db, function(tab, obj) {
			if (tab.indexOf('cx_sb') >= 0) {
				if (index == 0) {
					_op += '<option selected="selected" value="'+tab+'">' + tab
							+ '</option>';
				} else {
					_op += '<option value="'+tab+'">' + tab + '</option>';
				}
				index++;
			}
		});
		$(who).html(_op);
	}

	function initCol(tab, who) {
		data(
				'${ctx}/sys/syDb_Get_TableUrl?tableId=' + tab,
				null,
				'json',
				function(d) {
					var _op = "";
					if (d) {
						$
								.each(
										d["treedata"],
										function(index, obj) {
											if (index == 0) {
												_op += '<option selected="selected" value="'+obj["text"]+'">'
														+ obj["text"]
														+ '</option>';
											} else {
												_op += '<option value="'+obj["text"]+'">'
														+ obj["text"]
														+ '</option>';
											}
										});

					} else {
						log('加载失败');
					}
					$(who).html(_op);
				});

	}

	//取变量字符串
	function getVarStr($var) {
		//var idNum=$var.id.split('_')[1]
		var _str = "";
		var _varName = $($var).find("input[name='varName']").val();
		var _tab = $($var).find("select[name='varTab']").val();
		var _field = $($var).find("select[name='varField']").val();
		var _fun = $($var).find("select[name='valFun']").val();
		var _where = getWheresStr($var);
		_str += "var " + _varName + ";";
		_str += _varName + "=getVar(\"" + _tab + "\",\"" + _field + "\",\""
				+ _fun + "\",\"" + _where + "\");";

		return _str;
	}

	function getWheresStr($var) {
		var _wheres = "where 1=1 ";
		$.each($($var).find("div[name='varWhere']"), function(index, obj) {
			var _col = $(obj).find('select[name="whereCol"]').val();
			var _op = $(obj).find('select[name="whereOp"]').val();
			var _val = $(obj).find('input[name="whereVal"]').val();
			_wheres += " and " + getWhereStr(_col, _op, _val);
		});
		return _wheres;
	}

	function getWheresJson($var) {

		var _wheres = [];
		$.each($($var).find("div[name='varWhere']"), function(index, obj) {
			var _where = {};
			var _col = $(obj).find('select[name="whereCol"]').val();
			var _op = $(obj).find('select[name="whereOp"]').val();
			var _val = $(obj).find('input[name="whereVal"]').val();
			_where["col"] = _col;
			_where["op"] = _op;
			_where["val"] = _val;
			_wheres.push(_where);
		});
		return _wheres;
	}

	function getWhereStr(col, op, val) {
		var _where = "";
		var _isNumber = true;
		$.each(val, function(index, v) {
			if ('12345678990'.indexOf(v) < 0) {
				_isNumber = false;
			}
		})
		if (val == "") {
			_isNumber = false;
		}
		if (_isNumber) {
			if (op <= 5) {
				_where += col + queryOperateMaps[op]["op"] + val;
			} else if (op == 12) {
				//_where += col + queryOperateMaps[op]["op"].replace('XX', "'"+val+"'");
			} else {
				_where += col + queryOperateMaps[op]["op"].replace('XX', val);
			}
		} else {
			if (op <= 5) {
				_where += col + queryOperateMaps[op]["op"] + "'" + val + "'";
			} else if (op == 12) {
				//_where += col + queryOperateMaps[op]["op"].replace('XX', "'"+val+"'");
			} else {
				_where += col + queryOperateMaps[op]["op"].replace('XX', val);
			}
		}
		if (op == 12) {
			try {
				var val1 = val.split(',')[0];
				var val2 = val.split(',')[1];
				$.each(val1, function(index, v) {
					if ('12345678990'.indexOf(v) < 0) {
						val1 = "'" + val1 + "'";
					}
				})
				$.each(val2, function(index, v) {
					if ('12345678990'.indexOf(v) < 0) {
						val2 = "'" + val2 + "'";
					}
				})

				_where += col
						+ queryOperateMaps[op]["op"].replace('X1', val1)
								.replace('X2', val2);

			} catch (e) {
				var err = {};
				err["name"] = "Op error";
				err["message"] = "变量条件选择为区间时,赋值错误,要以英文逗号分隔.";
				//log('在。。之间,值的格式为[值1,值2],表示在值1和值2之间')
				throw err;

			}
		}
		return _where;

	}

	function initWhereOPs($o) {
		var _op = "";
		$.each(queryOperateMaps, function(index, op) {
			_op += '<option value="'+index+'">' + op.detail + '</option>';
		});
		$($o).html(_op);
	}

	//取变量值
	function getVar(tab, field, fun, wheres) {
		///sys/syPoint_Get_By_Tab_Where
		var arg = {};
		arg['tab'] = tab;
		arg['field'] = field;
		arg['fun'] = fun;
		arg['where'] = wheres;
		var _res = 10;
		/* data('${ctx}/sys/syPoint_Get_By_Tab_Where' ,arg,'json',function(d) {
			//setFunCall(d)
			_res=d;
			console.info(d);
		}); */
		return _res;
	}

	//取所有变量字符串
	function getVarsStr($vars) {
		var _varsStr = "";
		$.each($($vars).find('div.var'), function(index, $var) {
			_varsStr += getVarStr($var);
		})
		return _varsStr;
	}
	function getFormulaScript() {
		if ($('#formula_edit').val() == "") {
			var error = {};
			error["name"] = "FormulaNull";
			error["message"] = "公式为空";
			throw error;
		} else if ($('#formula_edit').val().trim().indexOf('val=') < 0) {
			var error = {};
			error["name"] = "FormulaNotFoundVal";
			error["message"] = "公式必须包含对val的赋值，如：val=a;";
			throw error;
		}
		$('#formula_view').val(
				getVarsStr($('#vars')) + 'var val="";'
						+ $('#formula_edit').val());
		return "function getPv(){" + getVarsStr($('#vars')) + 'var val="";'
				+ $('#formula_edit').val() + " return val;}getPv";
	}

	function checkFormulaScript() {
		try {
			var xx = eval(getFormulaScript())();
			log('校验成功:' + xx)
		} catch (e) {
			if (e.name == "SyntaxError") {
				log('校验失败,主要原因可能是：变量名称为空，或者公式编辑语法错误！')
			} else if (e.name == "ReferenceError") {
				log('校验失败,使用了未定义的变量：' + e.message)
			} else if (e.name == "FormulaNull") {
				log('校验失败,' + e.message)
			} else if (e.name == "FormulaNotFoundVal") {
				log('校验失败,' + e.message)
			} else {
				log('校验失败,' + e.message)
			}
		}
	}

	/* 	function getVarArg(varName,tab,field,where){
	
	 } */

	function getVarArg($var) {
		//var idNum=$var.id.split('_')[1]
		var _var = {};
		var _varName = $($var).find("input[name='varName']").val();
		var _tab = $($var).find("select[name='varTab']").val();
		var _field = $($var).find("select[name='varField']").val();
		var _fun = $($var).find("select[name='valFun']").val();
		var _where = getWheresStr($var);
		var _whereJson = getWheresJson($var);
		var _varDesc = $($var).find("input[name='varDesc']").val();
		_var["varName"] = _varName;
		_var["tab"] = _tab;
		_var["field"] = _field;
		_var["fun"] = _fun;
		_var["where"] = _where;
		_var["varDesc"] = _varDesc;
		_var["whereJson"] = _whereJson;
		return _var;
	}

	function getVarsArg($vars) {
		var _vars = [];
		$.each($($vars).find('div.var'), function(index, $var) {
			_vars.push(getVarArg($var));
		})
		return _vars;
	}

	function getVars(_vars, _formula, _callback) {
		//"function getPv(){"+getVarsStr($('#vars'))+'var val="";'+$('#formula_edit').val()+" return val;}getPv";
		//getVarsArg();
		var arg = {};

		arg['tab'] = "";
		arg['varName'] = "";
		arg['field'] = "";
		arg['fun'] = "";
		arg['where'] = "";
		$.each(_vars, function(index, _var) {
			arg['tab'] += _var['tab'] + '^';
			arg['varName'] += _var['varName'] + '^';
			arg['field'] += _var.field + '^';
			arg['fun'] += _var.fun + '^';
			arg['where'] += _var.where + '^';
		})
		arg['tab'] = arg['tab'].substring(0, arg['tab'].length - 1);
		arg['varName'] = arg['varName'].substring(0, arg['varName'].length - 1);
		arg['field'] = arg['field'].substring(0, arg['field'].length - 1);
		arg['fun'] = arg['fun'].substring(0, arg['fun'].length - 1);
		arg['where'] = arg['where'].substring(0, arg['where'].length - 1);
		data('${ctx}/sys/syPoint_GetJs_By_Vars_Arg', arg, 'json',
				function(d) {
					_callback(eval(
							"function getPv(){var val='';" + d.js + _formula
									+ " return val;}getPv")());
				});
	}

	function calFormula() {
		var _vars = getVarsArg($('#vars'));
		var _formula = $('#formula_edit').val();
		getVars(_vars, _formula, showVal);
	}

	function showVal(val) {
		log('当前计算结果:' + val);
	}

	function getScriptStr() {
		var _str = "";
		_str += " var _vars=" + json2str(getVarsArg($('#vars'))) + ";";
		_str += "var _formula=\"" + $('#formula_edit').val() + "\"";
		return _str;
	}

	function getDesignHtml() {
		return zipHtml($('#vars').html());
	}

	function changeLoadCol(me) {
		//var_1_Tab
		//var_1_Field
		//var_1_Where_Col_0
		//var_1_Where
		initCol($(me).val(), $("#" + me.id.replace('Tab', 'Field')));
		initCol($(me).val(), $("#" + me.id.replace('Tab', 'Where_Col_0')));
		$
				.each($("#" + me.id.replace('Tab', 'Where')
						+ " div[name='varWhere']"), function(index, obj) {
					if (index > 0) {
						$(obj).remove();
					}
				})
	}

	function loadFormula(e) {
		console.info('xxxxxxxxxxxxxxxxxxxxx')
		var _html = "";
		var _varsArg = [];
		var _script;
		if (e == 0) {
			_html = parent.$('#i_sy_syPoint_datagrid_add_form_note').val();
			_script = parent.$('#i_sy_syPoint_datagrid_add_form_codeCal')
					.val();
		} else {
			_html = parent.$('#i_sy_syPoint_datagrid_edit_form_note').val();
			_script = parent.$('#i_sy_syPoint_datagrid_edit_form_codeCal')
					.val();
		}
		try {
			if (_html != "" && _script != "") {
				eval(_script);
				_varsArg = _vars;
				$('#vars').tabs('close', '变量定义');
				var _varStr = "";
				for (var i = 0; i < $(_html).find('.var').length; i++) {
					var _title = $($(_html).find('ul.tabs li .tabs-title')[i])
							.html();
					var _obj = $($(_html).find('.var')[i]);
					$('#vars').tabs('add', {
						title : _title,
						content : _obj,
						closable : true,
						tools : [ {
							iconCls : 'icon-mini-add',
							handler : function() {
								addVarTab();
								//$('#vars').tabs('select', $('#vars').tabs("tabs").length-1);
							}
						} ]
					});
				}

				//赋值
				$.each($("#vars").find('div.var'), function(index, obj) {
					setVarArg(obj, _varsArg[index]);
				})
				//$("#vars").tabs();
				$('#formula_edit').val(_formula);
				//$('#formula_view').val(_script);
				syaxHighLight($('#formula_view'), _script);
			}
		} catch (ex) {
			console.info(ex)
		}
	}

	function setVarArg($var, _var) {

		$($var).find("input[name='varName']").val(_var["varName"]);
		$($var).find("select[name='varTab']").val(_var["tab"]);
		$($var).find("select[name='varField']").val(_var["field"]);
		$($var).find("select[name='valFun']").val(_var["fun"]);
		$($var).find("input[name='varDesc']").val(_var["varDesc"]);
		setVarWhere($var, _var["whereJson"]);

	}

	function setVarWhere($var, whereJson) {
		$.each($($var).find("div[name='varWhere']"), function(index, obj) {
			var _where = whereJson[index];
			$(obj).find('select[name="whereCol"]').val(_where["col"]);
			$(obj).find('select[name="whereOp"]').val(_where["op"]);
			$(obj).find('input[name="whereVal"]').val(_where["val"]);
		});
	}
	function addVarTab() {
		var idNum;
		var row1;
		row1 = $('#vars .var:last');//获得最后一行 -- 设置jquery对象(待克隆的div)
		//如果row1为空
		if (row1.length == 0) {
			idNum = 1;
			row1 = $("#var_" + (idNum - 1));
		} else {
			idNum = Number(row1.attr('id').split('_')[row1.attr('id')
					.split('_').length - 1]) + 1;
		}
		var newRow = row1.clone(true); //创建最后一行的一个副本
		// newRow.insertBefore(row1);	//在最后一行前插入
		//newRow.insertAfter(row1); //在最后一行后插入	

		$('#vars').tabs('add', {
			title : '变量定义',
			content : newRow,
			closable : true,
			tools : [ {
				iconCls : 'icon-mini-add',
				handler : function() {
					addVarTab();
					//$('#vars').tabs('select', $('#vars').tabs("tabs").length-1);
				}
			} ]
		});

		var nVarID = 'var_' + idNum;
		var nVarWheres = nVarID + '_Where';
		var nVarWhereBtn = nVarWheres + "Btn";
		var nVarWherePre = nVarID + '_Where';

		//var _prevVarWhere=row1.id+ '_Where';

		//var rndID = "varWhere" + idNum;
		var rndID = nVarWherePre;

		newRow.attr("id", nVarID); //设置行ID 每次都不一样 
		newRow.find('input[name="varWhereBtn"]').attr("id", nVarWhereBtn); //设置行ID 每次都不一样 
		newRow.find('div[name="varWheres"]').attr("id", nVarWheres); //设置行ID 每次都不一样 
		//给各个 select input 加上不同的id

		$.each(newRow.find("div[name='varWhere']"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_' + index);
		});

		/* $.each(newRow.find("select.tabNamesSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Tab_' + index);
		}); */

		$.each(newRow.find("select.colNamesSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Col_' + index);
		});

		$.each(newRow.find("select.relationsSelect"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Op_' + index);
		});

		$.each(newRow.find("input.colLimit"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Val_' + index);
		});

		$.each(newRow.find("a[name='delWhere']"), function(index, obj) {
			$(obj).attr('id', nVarWherePre + '_Del_' + index);
		});
		//nVarID
		newRow.find("a[name='delVar']").attr("id", nVarID + '_Del');

		newRow.find("input[name='varDesc']").attr('id', 'varDesc' + idNum);
		newRow.find("input[name='varName']").attr('id', 'varName' + idNum);
		newRow.find("select.valFunsSelect").attr('id', 'varFun' + idNum);

		newRow.find("select[name='varTab']").attr('id', nVarID + '_Tab');
		newRow.find("select[name='varField']").attr('id', nVarID + '_Field');

		//拷贝设值
		$.each(newRow.find("select.tabNamesSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.tabNamesSelect")[index]).val());
		});

		$.each(newRow.find("select.colNamesSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.colNamesSelect")[index]).val());
		});

		$.each(newRow.find("select.relationsSelect"), function(index, obj) {
			$(obj).val($(row1.find("select.relationsSelect")[index]).val());
		});

		//初始值为空

		//注册事件
		newRow.find("a[name='delVar']").click(function() {
			delRow(this);
		});
		// 新加行显示删除按键
		newRow.find("a[name='delVar']").html(
				" <span style='color: blue;'>删除</span>");
	}

	function changeVarName(obj) {
		var tab = $('#vars').tabs('getSelected'); // get selected panel
		$('#vars ul.tabs .tabs-selected .tabs-title').html(
				"变量：<font color='red'>" + $(obj).val() + "</font>");
	}

	$(function() {
		initTab($("#var_0_Tab"));
		initCol($("#var_0_Tab").val(), $("#var_0_Field"));
		initCol($("#var_0_Tab").val(), $("#var_0_Where_Col_0"));
		initWhereOPs($("#var_0_Where_Op_0"));
		//setTimeout(function(){loadFormula($.getUrlVar('type'));},900);
		loadFormula($.getUrlVar('type'));
		//initTab($("#TabNames"));
		//initCol($("#TabNames").val(), $("#ColNames"));
		//jsStyle();
	});

	function jsStyle() {
		jsedit = CodeMirror.fromTextArea($('#formula_edit')[0], {
			mode : 'javascript',
			indentWithTabs : true,
			smartIndent : true,
			lineWrapping : true,
			lineNumbers : true,
			matchBrackets : true,
			autofocus : true
		});
	}

	function syaxHighLight($outContainer, outString) {
		console.info(outString);
		var sample = outString.replace(/</g, '&lt;');
		var $iframe = $('<iframe class="test-wrap" src="about:blank" style="height:100%;width:100%"/>'), background = '#fff', themeName = 'Default';

		$($outContainer).append($iframe);

		$iframe.ready(function() {
					var doc = $iframe[0].contentDocument;
					$iframe.css('background', background);

					doc.write(''
									+ '<link type="text/css" rel="stylesheet" href="${ctx}/plug/syntaxhighlighter/styles/shCore' + themeName + '.css"/>'
									+ '<scr' + 'ipt type="text/javascript" src="${ctx}/plug/syntaxhighlighter/scripts/shCore.js"></scr' + 'ipt>'
									+ '<scr' + 'ipt type="text/javascript" src="${ctx}/plug/syntaxhighlighter/scripts/shBrushXml.js"></scr' + 'ipt>'
									+ '<scr' + 'ipt type="text/javascript" src="${ctx}/plug/syntaxhighlighter/scripts/shBrushJScript.js"></scr' + 'ipt>'

									+ '<pre type="syntaxhighlighter" class="brush: js; html-script: true; highlight: [5, 20]" '
									+ ' in action!">'
									+ sample
									+ '</pre>'
									+ '<scr' + 'ipt type="text/javascript">'
									+ 'SyntaxHighlighter.highlight();'
									+ '</scr' + 'ipt>');
					doc.close();
				});
	}
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'变量设计',border:false"
		style="height: 170px; padding: 0px; overflow-y: auto;">
		<div id="vars" class="easyui-tabs"
			data-options="tabPosition:'left',border:false" fit=true>
			<div title="变量定义" id="var_0" class='var'
				data-options="tools:'#p-tools'" style="padding: 5px;">

				<div>
					变量描述:<input type="text" name="varDesc"></input> 变量名称:<input
						type="text" name="varName" onblur="changeVarName(this)"></input>
				</div>
				<div>

					变量数表:<select id="var_0_Tab" name="varTab" class="tabNamesSelect"
						onchange="changeLoadCol(this)" style="width: 150px; height: 20px;"></select>
					取值字段:<select id="var_0_Field" name="varField"
						style="width: 150px; height: 20px;"></select> 取值方法:<select
						id="var_0_Fun" name="valFun" class="valFunsSelect"
						style="width: 150px; height: 20px;"margin: 0px 0px 0px 0px;">
						<option value="val">第一个元素值</option>
						<option value="max">最大值</option>
						<option value="min">最小值</option>
						<option value="avg">平均值</option>
						<option value="sum">和</option>
						<option value="count">统计数</option>
						<option value="last">最新数据</option>
					</select>
				</div>
				变量条件:<input type="button" value="添加条件"
					onclick="return addRow(this);" id="var_0_WhereBtn"
					name="varWhereBtn">
				<div class="conditionDiv" id="var_0_Where" name="varWheres"
					style="margin-left: 52px;">
					<div id="var_0_Where_0" name="varWhere">
						</select> <select id="var_0_Where_Col_0" name="whereCol"
							class="colNamesSelect" style="width: 100px;">
						</select> <select id="var_0_Where_Op_0" name="whereOp"
							class="relationsSelect"
							style="width: 80px; margin: 0px 0px 0px 10px;">
						</select> <input id="var_0_Where_Val_0" name="whereVal" class="colLimit"
							style="width: 100px; margin: 0px 0px 0px 20px;" /> <a href="#"
							id="var_0_Where_Del_0" name="delWhere" onclick="return false;"
							title="删除" class="delRow_Link"></a>
					</div>
				</div>
				<a href="#" id="var_0_Del" onclick="return false;"
					style="display: none;" name="delVar" title="删除" class="delRow_Link"></a>
			</div>
		</div>
		<div id="p-tools">
			<a href="#" class="icon-mini-add" onclick="addVarTab()"></a>
		</div>
	</div>
	<div data-options="region:'center',border:false"
		style="padding: 0px; overflow: hidden" class="easyui-layout">
		<div title="输入公式" data-options="region:'west',cache:false,"
			style="padding: 0px; width: 350px">
			<div class="easyui-layout" fit=true>
				<div data-options="region:'center',border:false"
					style="overflow: hidden">
					<textarea name="formula" id="formula_edit"
						placeholder="公式要求对val赋值,格式为：[val=a+b;],其中a、b分别是定义的变量名"
						style="height: 100%; width: 100%; overflow: hidden"></textarea>
				</div>
				<div data-options="region:'south'"
					style="height: 35px; overflow: hidden">
					<input style="height: 25px; margin: 5px;" type="button"
						value="校验公式" onclick="checkFormulaScript()"> <input
						style="height: 25px; margin: 5px;" type="button" value="预览公式脚本"
						onclick="getFormulaScript()"> <input
						style="height: 25px; margin: 5px;" type="button" value="计算并保存公式"
						onclick="calFormula()">
				</div>

			</div>
		</div>
		<div title="公式脚本" data-options="region:'center'"
			style="overflow: hidden">

			<div name="formula" id="formula_view"
				style="height: 100%; width: 100%; overflow: hidden"></div>
		</div>
	</div>
</body>
</html>