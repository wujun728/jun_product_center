<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/plug/ueditor142/third-party/codemirror/codemirror.css" />
<script type="text/javascript"
	src="${ctx}/plug/ueditor142/third-party/codemirror/codemirror.js"></script>
<style type="text/css">
.checkbox {
	vertical-align: middle;
}
.left {
	width: 120px;
	float: left;
}

.left table {
	background: #E0ECFF;
}

.left td {
	background: #eee;
}

.right {
	float: left;
	width: 600px;
}

.right table {
	background: #E0ECFF;
	width: 100%;
}

.right td {
	background: #fafafa;
	text-align: center;
	padding: 2px;
}

.right td {
	background: #E0ECFF;
}

.right td.drop {
	background: #fafafa;
	width: 100px;
}

.right td.over {
	background: #FBEC88;
}

.item {
	text-align: center;
	border: 1px solid #499B33;
	background: #fafafa;
	width: 100px;
}

.API {
	text-align: left;
	border: 1px solid #000;
	font-weight:bold;
	background: #fafafa;
	color:gray;
	width: 200px;
}
.assigned {
	border: 1px solid #BC2A4D;
}
.tstyle{
border:1px solid #888;
border-collapse:collapse;
border-spacing:2px;
border-color:gray
}
</style>

<script type="text/javascript">
	var sy_design_tabs;
	var sy_db_tree, sy_table_tree;
	var tablewebiframe="";
	var g_modal="";
	var g_url="";
	var jsedit=null;
	var jsname='';
	function setCodeUrl(d) {
		$.parser.parse($('#codeurl').val(d.url));
		sy_table_tree.tree({
			data : d.treedata
		});
		g_modal=d.modal;
		g_url=d.url;
		initFormDesign();
	};
	
	function jsControl(path){
		if(!path) { log('该表无法控制js！'); return;}
		var paths=path.split('/');
		jsname=paths[paths.length-1];
		$("#js").load(path,function(response,status,xhr){
			 if (status=="success")
		      {
				jsedit.setValue(response); 
				$('#jshelpmsg').panel({
					content:'您可尽情发挥!'
				});
		      }else{
		    	jsedit.setValue('');
		    	$('#jshelpmsg').panel({
					content:jsname+'不存在，无法控制js！'
				});
		      }
			});
	}
	
	function initFormDesign(){
		if(g_modal==''||g_url=='')return;
		delete tablewebiframe;
		tablewebiframe=$.window({  
        	url:'${ctx}'+g_url,
          	isIframe:true,
    		width:0,
    		height:0,
            winId:g_url, 
            target:'self',
            closed:true,
            modal: false,
        onComplete:function(){
        	var obj =tablewebiframe.find('iframe')[0].contentWindow;
        	jsControl($('#pjs',obj.document).attr('src'));
       		try{
       			if(obj[g_modal+"Id"]){
		        	formDtId(obj[g_modal+"Id"]);
		        	if(obj['columns']==undefined){
		        		formData(obj[g_modal+'Columns']);
		        	}else{
		        		formData(obj['columns']);//兼容老的命名
		        	}
       			}else{
       				syFormDesignClear();
       				$('#formhelpmsg').panel({
    					content:'页面不存在!您将无法进行表单设计。'
    				});
       			}
       		}catch(e){
       			syFormDesignClear();
       		}
          }
    	}); 
	}

	$(function() {
		jsedit=CodeMirror.fromTextArea($('#js')[0], {
		    mode: 'javascript',
		    indentWithTabs: true,
		    smartIndent: true,
		    lineWrapping:true,
		    lineNumbers: true,
		    matchBrackets : true,
		    autofocus: true
		  });
		
		sy_design_tabs = $("#sy_design_tabs");

		//加载树
		sy_db_tree = $("#sy_db_tree").tree(
				{
					animate : true,
					url : '${ctx}' + actionUrl('/sys/', 'syDb', 'Get_Tree'),
					onClick : function(n) {
						var sy_db_currnode = sy_db_tree.tree('getSelected');
						var id = sy_db_currnode.text;
						data('${ctx}'+ actionUrl('/sys/', 'syDb', 'Get_TableUrl')
								+ '?tableId=' + id, null, 'json', setCodeUrl);
						var tabs = sy_design_tabs.tabs("tabs");
						var l = tabs.length;
						for ( var i = l - 1; i >= 0; i--) {
							sy_design_tabs.tabs("close", tabs[i]
									.panel('options').title);
						}
					},//onclick
					onLoadSuccess : function() {
						$(this).tree('expandAll');
					}
				});

		sy_table_tree = $("#sy_table_tree").tree({
							animate : true,
							onClick : function(n) {
								advConstruction(null);//清空预览
								var sy_db_currnode = sy_db_tree
										.tree('getSelected');
								var tablename = sy_db_currnode.text;
								var sy_table_currnode = sy_table_tree
										.tree('getSelected');
								var subtitle = sy_table_currnode.text;
								var sy_design_tabs_url = "${ctx}/sys/SyDesign.jsp?fieldname="
										+ subtitle + "&tablename=" + tablename;
								//tabs
								if (!sy_design_tabs.tabs('exists', subtitle)) {
									sy_design_tabs.tabs(
													'addIframeTab',
													{tab : {
															title : subtitle,
															closable : true,
															tools : [ {
																iconCls : 'icon-mini-refresh',
																handler : function() {
																	sy_design_tabs
																			.tabs(
																					'updateIframeTab',
																					{
																						'which' : subtitle
																					});
																	sy_design_tabs
																			.tabs(
																					'select',
																					subtitle);
																}
															} ]
														},
														iframe : {
															src : sy_design_tabs_url
														}
													});
									sy_design_tabs.tabs('addEventParam');
									return false;
								} else {
									sy_design_tabs.tabs('updateIframeTab', {
										'which' : subtitle
									});
									sy_design_tabs.tabs('select', subtitle);
									sy_design_tabs.tabs('addEventParam');
									return false;
								}
								//tabs
							},//onclick
							onLoadSuccess : function() {
								$(this).tree('expandAll');
							}
						});
	});

	function cutString() {
		g_data = d.columnAlias;
		var result = g_data.split('^');
		for ( var i = 0; i < result.length; i++) {
			var name = result[i].split(':')[0];
			var val = result[i].split(':')[1];
			if (name == "addform" || name == "editform") {
				var vArray = val.substring(0, val.length - 1).split(',');
				for ( var j = 0; j < vArray.length; j++) {
					if (vArray[j].split(':')[0] == "type") {
						$('#mm').combobox('setValue', vArray[j].split(':')[1]);
					}
				}
			}
		}
	}

	function arrayToData(a) {
		var d = [];
		for ( var i in a) {
			var o = {
				"id" : 'eType.' + i,
				"text" : i
			};
			d.push(o);
		}
		return d;
	}

	function code() {
		var sy_db_currnode = sy_db_tree.tree('getSelected');
		var tablename = sy_db_currnode.text;
		var url = '${ctx}' + actionUrl('/sys/', 'syDb', 'Get_TableCode')
				+ '?tableId=' + tablename;
		if ($('#model').prop('checked')) {
			url += '!1,';
		} else {
			url += '!0,';
		}

		if ($('#service').prop('checked')) {
			url += '1,';
		} else {
			url += '0,';
		}

		if ($('#action').prop('checked')) {
			url += '1,';
		} else {
			url += '0,';
		}
		var web='0,';
		if ($('#web').prop('checked')) {
			web= '1,';
		} else {
			web= '0,';
		}

		if ($('#webHasFile').prop('checked')) {
			web= '2,';
		} 
		url += web;
		
		if ($('#parameter').prop('checked')) {
			url += '1';
		} else {
			url += '0';
		}
		var setData={'excelName':$('#excelName').val(),'codeModalName':$('#codeModalName').text()};
		data(url, setData, 'json', l);

	}
	
	function delCode(){
		var sy_db_currnode = sy_db_tree.tree('getSelected');
		var tablename = sy_db_currnode.text;
		var url = '${ctx}' + actionUrl('/sys/', 'syDb', 'Del_TableCode')
				+ '?tableId=' + tablename;
		data(url, null, 'json', l);
	}
	
	function l(d) {
		log(d.info);
	}
	
	/*表单设计*/
		var uedit='', script = '';
	var rowCount=0,cellCount=0;
	var g_formData="";
	var g_formDtId="";
	function formData(data){
		if(!data){
			syFormDesignClear();
			return;
		}
		g_formData=data;
		script = '';
		var cu = $('#i_form_type').combobox('getValue');
		cu == '0' ? (cu = true) : (cu = false);
		getData(cu);
	}
	
	function formDtId(data){
		if(!data){
			syFormDesignClear();
			return;
		}
		g_formDtId=data;
	}
	
	$(function() {
		$('#i_form_rowcount,#i_form_cellcount').change(function() {
			script = '';
			createTable();
		});
		$('#i_form_type').combobox({
			onChange : function(newValue, oldValue) {
				script = '';
				syFormDesignClear();
				var cu=newValue;
				cu== '0' ? ( cu= true) : (cu = false);
				getData(cu);
			}
		});
		$('#i_form_html_final').click(function() {
			$(this).text(UE.utils.html(uedit.getContent())+script.replaceAll("'", "\\'").replaceAll("\\\\/", "/"));
		});
		$('#iframehtml').tabs({
			fit:true,
			onSelect:function(title,index){
				if(title=='设计表格'){
					gHtml();
					uedit.setContent($('#i_form_html').val());
				}
			}
		});
		uedit = new UE.ui.Editor(({'allowDivTransToP':false}));
		uedit.render("dghtml");
	});
	var d = '';
	//获取数据
	function getData(eora) {
		if (g_formData=='') {
			log('页面还没有生成!');
			return;
		}
		d = inintDtColunms(g_formData);
		var h = '';
		var allField = '';
		for ( var i = 0; i < d[0].length; i++) {
			var colobj = d[0][i];
			if (eora) {//新增模式
				colobj.form=colobj.addform;
			} else {//编辑模式
				colobj.form=colobj.editform;
			}
			if (colobj.form.hidden != undefined &&colobj.form.hidden!=false) {
				h += '<tr><td><div class="item" style="color:red" ref="'+i+'" >'
						+ colobj.title + '</div></tr>';
			} else {
				h += '<tr><td><div class="item" ref="'+i+'" >' + colobj.title
						+ '</div></tr>';
			}
			allField += '[' + colobj.title + ']';
		}
		$('#allfield').html(allField);
		$('#item').html(h);
		$('#js_fields').html(h);
		$('#item .item').draggable({
			revert : true,
			proxy : 'clone'
		});
		$('#formhelpmsg').panel({
			content:'提示：共有' + d[0].length + '个字段，红色为隐藏字段，设计时应放在后面,并不能和显示字段放在一行!'
		});
	}
	function createTable() {
		$(".right").html('');
		rowCount = $('#i_form_rowcount').val();
		cellCount = $('#i_form_cellcount').val();
		if (!rowCount) {
			log('请填写表格行数据');
			return;
		}
		if (!cellCount) {
			log('请填写表格列数据');
			return;
		}
		gTable(rowCount, cellCount).appendTo($(".right"));
		$('.right td').addClass('drop');
		$('.right td.drop').droppable(
				{
					onDragEnter : function() {
						$(this).addClass('over');
					},
					onDragLeave : function() {
						$(this).removeClass('over');
					},
					onDrop : function(e, source) {
						$(this).removeClass('over');
						if ($(source).hasClass('assigned')) {
							$(this).append(source);
						} else {
							var c = $(source).clone().addClass('assigned');
							$(this).empty().append(c);
							c.draggable({
								revert : true
							});
							var h = $('#allfield').html();
							$('#allfield')
									.html(
											h.replace('[' + $(source).text()
													+ ']', ''));
						}
					}
				});
	}
	//动态创建表格
	function gTable(rowCount, cellCount) {
		var table = $('<table width="95%">');
		for ( var i = 0; i < rowCount; i++) {
			var tr = $("<tr></tr>");
			tr.appendTo(table);
			for ( var j = 0; j < cellCount; j++) {
				var td = $('<td>' + i + '*' + j + '</td>');
				td.appendTo(tr);
			}
		}
		table.append("</table>");
		return table;
	}

	//生成html
	function gHtml() {
		script='';
		var h = $('#allfield').html();
		if (h != '') {
			log('还有一下字段需要填入：' + h);
			return;
		}
		var dtid = g_formDtId;
		var cu = $('#i_form_type').combobox('getValue');
		if (dtid=="") {
			log('页面还没有生成!');
			return;
		}
		if (!cu) {
			log('请选择新建/编辑模式');
			return;
		}
		cu == '0' ? (cu = true) : (cu = false);
		var cobj = $('.right table').clone();
		$('td', cobj).each(function() {
			var gobj = $(this);
			var tr=gobj.parent();
			var de = $("div[class='item assigned']", gobj);
			if (de.length == 1) {
				var h=gFieldhtml(dtid, de.attr('ref'), cu);
				if(h.indexOf('display:none;')!=-1){tr.attr('style','display:none;')}
				gobj.replaceWith(h);
			} else {
				gobj.replaceWith('<td></td><td></td>');
			}
		});
		if (cu) {
			$('#i_form_html').val(
					'<form id="'+dtid+'_add_form"><table width="99%" border="1" class="formtable" >'
							+ cobj.html() + '</table></form>');
		} else {
			$('#i_form_html').val(
					'<form id="'+dtid+'_edit_form"><table width="99%" border="1" class="formtable" >'
							+ cobj.html()+ '</table></form>');
		}
		log('恭喜，生成成功!见下方');
	}
	
	function tdTypeStyle(type){
		switch (type) {
			case eType.Slider:
				return "height:80px;";
			default:
				return "";
		}
	}
	
	function objtoStr(obj){
		var str='';
		for (var i in obj){
			str=i+":'"+obj[i]+"', ";
		}
		str = str.substring(0,str.length-2);
		return str;
	}
	
	//生成字段html
	var fieldscript;
	function gFieldhtml(id, di, eora) {
		var html = "";
		fieldscript="";
		var colobj = d[0][di];
		if (eora) {//新增模式
			id += '_add_form_';
			colobj.form=colobj.addform;
		} else {//编辑模式
			id += '_edit_form_';
			colobj.form=colobj.editform;
		}
		var eId = id + colobj.field;
		colobj.form.type=eval(colobj.form.type);
		colobj.form.classname=getEtypeClass(colobj.form.type);
		html += '<td  class="label"  align="right" style="width:15%;'+ ((!colobj.form.hidden)? '': 'display:none;')+ '" >'
				+ colobj.title
				+ '</td><td align="left" style="width:'+(100-15*cellCount)/cellCount+'%;'+tdTypeStyle(colobj.form.type)+ ((!colobj.form.hidden)? '': 'display:none;') +'" >';
		if(typeof(colobj.form.dataoptions)=='object'){
			colobj.form.dataoptions=objtoStr(colobj.form.dataoptions);
		}
		switch (colobj.form.type) {
		case eType.HtmlEdit:
			html += '<div id="'
					+ eId
					+ '" '
					+ (colobj.form.classname == undefined ? '' : 'class="'
							+ colobj.form.classname + '"')
					+ ' '
					+ (colobj.form.style?  'style="'
						+ colobj.form.style + '"':'')
					+ '  '
					+ (colobj.form.readonly == true ? ' readonly="readonly"': '')
					+ ' '
					+ (colobj.form.attribute == undefined ? ''
							: '"' + colobj.form.attribute + '"')
					+ ' '
					+ (colobj.form.type == undefined ? 'reftype="'
							+ eType.Input + '"' : 'reftype="'
							+ colobj.form.type + '"') + ' ></div>';
			fieldscript+= '<script>function '+eId+'uei(){var str="";var style=$("#'+eId+'").attr("style");if(style){var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");str=(obj.height?("initialFrameHeight:"+obj.height+","):"")+(obj.width?("initialFrameWidth:"+obj.width):"");if(str){str="({"+str+"})";}}return eval(str);} var '+
			eId+'uedit = new UE.ui.Editor('+eId+'uei()); '+eId+'uedit.render("'+ eId + '");top.formfieldmap.put("' + eId+ '",'+eId+'uedit);<\\/script>';
			script+=fieldscript;
			break;
		case eType.Chart:
			var url=colobj.form.dataoptions.split("'")[1];
			html += '<div id="'
					+ eId
					+ '" '
					+ (colobj.form.classname == undefined ? '' : 'class="'
							+ colobj.form.classname + '"')
					+ ' '
					+ (colobj.form.style?  'style="'
						+ colobj.form.style + '"':'')
					+ '  '
					+ (colobj.form.attribute == undefined ? ''
							: '"' + colobj.form.attribute + '"')
					+ ' '
					+ (colobj.form.type == undefined ? 'reftype="'
							+ eType.Input + '"' : 'reftype="'
							+ colobj.form.type + '"') + ' ></div>';
			fieldscript+= '<script>function '+eId+'Chart(){var style=$("#'+eId+'").attr("style");var obj=eval("({"+style.replace(/;/g,",").replace(/px/g,"")+"})");';
			fieldscript+='var chart={};chart.title="'+colobj.title+'";chart.id="'+eId+'";chart.width=obj.width;';
			fieldscript+='chart.height=obj.height;chart.url="'+url+'";';
			fieldscript+= 'gChart(chart);}'+eId+'Chart();<\\/script>';
			script+=fieldscript;
			break;
		case eType.Calendar:
			html += '<div  id="'+ eId+'" '
						+ (colobj.form.classname == undefined ? '' : 'class="'
								+ colobj.form.classname + '"')
						+ '  '
						+ (colobj.form.style?'style="'
							+ colobj.form.style + '"':'')
						+ '  '
						+ (colobj.form.dataoptions == undefined ? ''
								: 'data-options="' + colobj.form.dataoptions + '"')
						+ ' '
						+ (colobj.form.attribute == undefined ? ''
								: '"' + colobj.form.attribute + '"')
						+ ' '
						+ (colobj.form.readonly == undefined ? ''
								: ' readonly:"' + colobj.form.readonly + '"')
						+ ' '
						+ (colobj.form.type == undefined ? 'reftype="'
								+ eType.Input + '"' : 'reftype="'
								+ colobj.form.type + '"') + ' ></div>';
				break;
		case eType.TextArea:
			html += '<textarea  id="'
					+ eId
					+ '" '
					+ (colobj.form.classname == undefined ? '' : 'class="'
							+ colobj.form.classname + '"')
					+ ' '
					+ (colobj.form.style?'style="'
						+ colobj.form.style + '"':'')
					+ '  '
					+ (colobj.form.attribute == undefined ? ''
							: '"' + colobj.form.attribute + '"')
					+ ' '
					+ (colobj.form.readonly == true ? ' readonly="readonly"': '')
					+ ' '
					+ (colobj.form.type == undefined ? 'reftype="'
							+ eType.Input + '"' : 'reftype="'
							+ colobj.form.type + '"') + ' ></textarea>';
			break;
		case eType.SelUsersInput:
			html += '<span  id="'+ eId+'" '
			+ (colobj.form.classname == undefined ? '' : 'class="'
					+ colobj.form.classname + '"')
			+ '  '
			+ (colobj.form.style?'style="'
				+ colobj.form.style + '"':'')
			+ '  '
			+ (colobj.form.dataoptions == undefined ? ''
					: 'data-options="' + colobj.form.dataoptions + '"')
			+ ' '
			+ (colobj.form.attribute == undefined ? ''
					: '"' + colobj.form.attribute + '"')
			+ ' '
			+ (colobj.form.readonly == undefined ? ''
					: ' readonly:"' + colobj.form.readonly + '"')
			+ ' '
			+ (colobj.form.type == undefined ? 'reftype="'
					+ eType.Input + '"' : 'reftype="'
					+ colobj.form.type + '"') + ' ></span>';
			html+='<a id="'+ eId+'_selusers" href="javascript:void(0);"  class="easyui-linkbutton" onclick="selUserDlg(\''+colobj.title+'\',\''+ eId+'\');" data-options="iconCls:\'icon-script_go\',plain:true">选人</a>';
			break;
		case eType.MultipleDateBox:
			html += '<span  id="'+ eId+'" '
			+ (colobj.form.classname == undefined ? '' : 'class="'
					+ colobj.form.classname + '"')
			+ '  '
			+ (colobj.form.style?'style="'
				+ colobj.form.style + '"':'')
			+ '  '
			+ (colobj.form.dataoptions == undefined ? ''
					: 'data-options="' + colobj.form.dataoptions + '"')
			+ ' '
			+ (colobj.form.attribute == undefined ? ''
					: '"' + colobj.form.attribute + '"')
			+ ' '
			+ (colobj.form.readonly == undefined ? ''
					: ' readonly:"' + colobj.form.readonly + '"')
			+ ' '
			+ (colobj.form.type == undefined ? 'reftype="'
					+ eType.Input + '"' : 'reftype="'
					+ colobj.form.type + '"') + ' ></span>';
			html+='<input id="'+eId+'_md" class="easyui-datebox" type="text"></input>'; 
			html+='<a id="'+eId+'_clean" href="javascript:void(0);"  class="easyui-linkbutton" onclick="cleanSpan(\''+ eId+'\');" data-options="iconCls:\'icon-script_go\',plain:true">清空</a><input id ="muti" type="hidden"></input>';
			fieldscript+='<script>$(function(){$("#'+eId+'_md").datebox({current:new Date(),onChange:function(n,o){$("#muti").val($("#muti").val()+";"+n);eSet("#'+eId+'",$("#muti").val());}});';
			fieldscript+='';
			fieldscript+='});<\\/script>';
			script+=fieldscript; 
			break;
		case eType.FlexPaper:
			html += '<span  id="'+ eId+'" '
			+ (colobj.form.classname == undefined ? '' : 'class="'
					+ colobj.form.classname + '"')
			+ '  '
			+ (colobj.form.style?'style="'
				+ colobj.form.style + '"':'')
			+ '  '
			+ (colobj.form.dataoptions == undefined ? ''
					: 'data-options="' + colobj.form.dataoptions + '"')
			+ ' '
			+ (colobj.form.attribute == undefined ? ''
					: '"' + colobj.form.attribute + '"')
			+ ' '
			+ (colobj.form.readonly == undefined ? ''
					: ' readonly:"' + colobj.form.readonly + '"')
			+ ' '
			+ (colobj.form.type == undefined ? 'reftype="'
					+ eType.Input + '"' : 'reftype="'
					+ colobj.form.type + '"') + ' ></span>';
			html+='<a id="'+ eId+'_pdfv" href="javascript:void(0);"  class="easyui-linkbutton" onclick="pdfViews(\''+colobj.title+'\',\''+ colobj.addform.addr+'\');" data-options="iconCls:\'icon-script_go\',plain:true">'+colobj.title+'显示</a>';
			break;
		case eType.Checkbox:
			html += '<input  id="'
					+ eId
					+ '"  type="checkbox" '
					+ (colobj.form.classname == undefined ? '' : 'class="'
							+ colobj.form.classname + '"')
					+ '  '
					+ (colobj.form.style?'style="'
						+ colobj.form.style + '"':'')
					+ '  '
					+ (colobj.form.dataoptions == undefined ? ''
							: 'data-options="' + colobj.form.dataoptions + '"')
					+ ' '
					+ (colobj.form.readonly == true ? ' readonly="readonly"': '')
					+ ' '
					+ (colobj.form.attribute == undefined ? ''
							: '"' + colobj.form.attribute + '"')
					+ ' '
					+ (colobj.form.type == undefined ? 'reftype="'
							+ eType.Input + '"' : 'reftype="'
							+ colobj.form.type + '"') + ' ></input>';
			break;
//html new
		default:
			html += '<input  id="'
					+ eId
					+ '"  type="text" '
					+ (colobj.form.classname == undefined ? '' : 'class="'
							+ colobj.form.classname + '"')
					+ '  '
					+ (colobj.form.style?'style="'
						+ colobj.form.style + '"':'')
					+ '  '
					+ (colobj.form.dataoptions == undefined ? ''
							: 'data-options="' + colobj.form.dataoptions + '"')
					+ ' '
					+ (colobj.form.readonly == true ? ' readonly="readonly"': '')
					+ ' '
					+ (colobj.form.attribute == undefined ? ''
							: '"' + colobj.form.attribute + '"')
					+ ' '
					+ (colobj.form.type == undefined ? 'reftype="'
							+ eType.Input + '"' : 'reftype="'
							+ colobj.form.type + '"') + ' ></input>';
			break;
		}
		if (colobj.form.setvalue != undefined) {
			fieldscript += '<script>$(function(){eSet("#' + eId + '",'
					+ colobj.form.setvalue + ');});<\\/script>';
			script+=fieldscript;
		}
		if(colobj.tooltip!=undefined){
			html += '<a href="#" title="'+colobj.tooltip.replaceAll('"', "'")+'" class="easyui-tooltip">填写说明</a>';
		}
		html += '</td>';
		return html;
	}
	
	function syFormDesignClear(){
		$("#js_fields").html('');
		$("#item").html('');
		$(".right").html('');
		$('#i_form_rowcount').val('');
		$('#i_form_cellcount').val('');
		$('#allfield').html('');
	}
	
function codeformhtml(){
	var html=(uedit=='')?'':UE.utils.html(uedit.getContent());
	if(html){
		html=html+script.replaceAll("'", "\\'").replaceAll("\\\\/", "/");
	}else{	
		gHtml();
		html=$('#i_form_html').val()+script.replaceAll("'", "\\'").replaceAll("\\\\/", "/");
	}
	var ero=$('#i_form_type').combobox('getValue');
	var setdata={'html':html,'ero':ero,'modal':g_modal};
	var url = '${ctx}' + actionUrl('/sys/', 'syDb', 'Set_TableFormHtml');
	data(url, setdata, 'json', l);
	}
	
	//预览
	function advConstruction(codeStr){
		if(codeStr==null){
			return $('#advc').html('');
		}
		var codeStrs=codeStr.split('^');
		var str="[[{field:'test',";
		for(var i=0;i<codeStrs.length;i++){
			str+=codeStrs[i]+',';
		}
		str=str.substring(0,str.length-1)+"}]]";
		console.warn(str);
		d=inintDtColunms(eval(str));
		cellCount=1;
		var strAddHtml='<tr><td>新建预览:</td><td></td></tr><tr>'+gFieldhtml('smartetest',0,true)+fieldscript.replaceAll("'", "\\'").replaceAll("\\\\/", "/")+'</tr>';//新建
		var strEditHtml='<tr><td>编辑预览:</td><td></td></tr><tr>'+gFieldhtml('smartetest',0,false)+fieldscript.replaceAll("'", "\\'").replaceAll("\\\\/", "/")+'</tr>';//编辑
		$.parser.parse($('#advc').html(strAddHtml+strEditHtml));
	}
	
	function webmodal(){
		var web = $
		.window({
			title : "页面模版",
			url : '${ctx}/sys/SyWebmodal.jsp?topthemeName='+top.themeName,
			isIframe : true,
			width : $(top).width(),
			height : $(top).height() - 60,
			left : 0,
			top : 60,
			winId : 'webdig',
			target : 'top',
			maximizable : true,
			buttons : [ {
				text : '确定',
				handler : function() {
					var dobj = web.find('iframe')[0].contentWindow.syWebmodalDt;
					var sel = dobj.datagrid('getChecked');
					if (sel.length!=1) {
						log('请勾选至多一个模版！');
					} else {
						$('#codeModalName').text(sel[0].codeName);
						web.window('close');
					}
				}
			} ],
			onComplete : function() {
				var obj = web.find('iframe')[0].contentWindow;
				obj.beSelModel();
			}
		});
	}
</script>
</head>
<body class="easyui-layout">
	<!-- 树 -->
	<div data-options="region:'west',split:true" title="数据库表列表"
		style="width: 180px;">
		<ul class="easyui-tree" id="sy_db_tree" style="padding: 10px 5px;">
		</ul>
	</div>
	<!-- tabs -->
	<div data-options="region:'center',fit:true" title="表设计"
		style="overflow: hidden;">
		<div class="easyui-tabs" data-options="fit:true" id="tabmain">
			<!-- 表设计 start -->
			<div title="代码生成" >
				<div class="easyui-layout" data-options="fit:true"
					id="sy_table_layout">
					<div data-options="region:'north'" style="height: 35px;"
						class="datagrid-toolbar">
						<table border="1" class="tstyle">
							<tr>
								<td style="color:red">数据模型:<input type="checkbox" class="checkbox" id="model" /></td>
									<td><span id="codeModalName" style="background-color: #EFF7FF"></span><a href="#" class="easyui-linkbutton" style="color:red"
									data-options="iconCls:'icon-layout_header',plain:true"
									onclick="javascript:webmodal()">页面模版</a></td>
								<td style="color:red">服务接口:<input type="checkbox" class="checkbox"
									id="service" /></td>
								<td style="color:red">
									控制器:<input type="checkbox" class="checkbox" id="action" />导出excel名:<input type="text"  id="excelName" />
								</td>
								<td style="color:red">系统路由:<input type="checkbox" class="checkbox"
									id="parameter" /></td>
								<td>表单(不带附件):<input type="checkbox" class="checkbox" id="web" /></td><td>表单(带附件):<input type="checkbox" class="checkbox" id="webHasFile" /></td>
								<td><a href="#" class="easyui-linkbutton"
									data-options="iconCls:'icon-save',plain:true"
									onclick="javascript:code()">生成代码</a></td>
									<td><a href="#" class="easyui-linkbutton"
									data-options="iconCls:'icon-cancel',plain:true"
									onclick="javascript:delCode()">抹去代码</a></td>
							</tr>
						</table>
					</div>
					<div data-options="region:'west',split:true" title="字段列表"
						style="width: 180px;">
						<ul class="easyui-tree" id="sy_table_tree"
							style="padding: 10px 5px;">
						</ul>
					</div>
					<div data-options="region:'center'" title="字段设计"
						style="overflow: hidden;">
						<div class="easyui-layout" data-options="fit:true">
							<div data-options="region:'center'" style="overflow: hidden;">
								<div class="easyui-tabs" fit="true" border="false"
									id="sy_design_tabs"></div>
							</div>
						</div>

					</div>
					<div data-options="region:'east',split:true" title="设计预览"
						style="width: 500px;padding:5px 185px 5px 5px;">
							<div class="easyui-panel"  
		       data-options="closable:false,   
		               collapsible:false,minimizable:false,maximizable:false,fit:true"> 
							<table width="99%" border="1" class="formtable" ><tbody id="advc">
							</tbody>
							</table>
							</div>
						</div>
						<div data-options="region:'south'" style="height: 35px;"
						class="datagrid-toolbar">
						<table border="1" class="tstyle">
							<tr>
							<td>链接:<input id="codeurl"  type="text" style="color: red;width:400px" readonly></input></td>
							</tr>
							</table>
							</div>
				</div>
			</div>
			<!-- 表设计 end -->
			<!-- 表单设计 start -->
			<div title="表单设计" >
			<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',title:'提示',split:true" style="height:80px;">
			<div id="formhelpmsg" class="easyui-panel"     
        style="background:#fafafa;"  
        data-options="closable:false,   
                collapsible:false,minimizable:false,maximizable:false,fit:true"> 
 			</div>
			</div>  
	<div data-options="region:'west',split:true,title:'数据菜单'"
		style="width: 150px;">
		<div class="left">
			<table id="item">
			</table>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="iframehtml">
			<div title="数据表格">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north'" style="height: 50px;"
						title="还有以下字段需要填入!">
						<div id="allfield"></div>
					</div>
					<div data-options="region:'center'" title="设计区域">
						<div class="right"></div>
					</div>
				</div>
			</div>
			<div title="设计表格">
				<script id="dghtml" type="text/plain" ></script>
			</div>
		</div>
	</div>
	<div data-options="region:'north'" style="height: 40px;"
						class="datagrid-toolbar">
	<table border="1" class="formtable">
							<tr>
								<td><strong>表格参数</strong> </td><td>行：</td><td><input id="i_form_rowcount" type="text"></input>
			</td><td>
		列：</td><td><input id="i_form_cellcount" type="text"></input></td><td> <strong>业务参数</strong></td><td>
		新建/编辑:</td><td><input
			id="i_form_type" class="easyui-combobox" value="0"
			data-options="
		valueField: 'value',
		textField: 'label',
		data: [{
			label: '新建',
			value: '0'
		},{
			label: '编辑',
			value: '1'
		}]" />
		</td>
		<td>
		<a href="#" class="easyui-linkbutton"
									data-options="iconCls:'icon-save',plain:true"
									onclick="javascript:codeformhtml()">插入页面</a>
	<textarea id="i_form_html_final" rows="3" style="width: 100%;display:none"></textarea>
				<textarea id="i_form_html" rows="3" style="width: 100%;display:none" ></textarea>
	
	</td>
	</tr>
	</table>
	</div>
</div>
			</div>
			<!-- 表单设计 end -->
			<!-- JS控制 start -->
			<div title="JS控制">
			<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',title:'提示',split:true" style="height:80px;">
			<div id="jshelpmsg" class="easyui-panel"     
        style="background:#fafafa;"  
        data-options="closable:false,   
                collapsible:false,minimizable:false,maximizable:false,fit:true"> 
 			</div>
 			</div>
 			<div data-options="region:'center',split:true">
 			<div class="easyui-layout" data-options="fit:true">
 			
 			<div data-options="region:'west',title:'字段',split:true" style="width: 150px;">
 			<table id="js_fields"></table>
 			</div>  
 			
 			<script type="text/javascript">
 			$(function(){
 				$.get(top.sysPath+"/js/core.js", function(data){
 					var h='';
 					var ar=data.match(/function\s+([^\w\{\s]+)/g).join(",").replace(/function/g, "").split("),");
 					for(var i=0;i<ar.length;i++){
 						if(i==ar.length-1){
 							h+='<tr><td><div class="API" >' +ar[i]+'</div></tr>';
 						}else{
 							h+='<tr><td><div class="API" >' +ar[i]+')</div></tr>';
 						}
 					}
 					$('#API').html(h);
 				});
 			});
 			</script>
 			<div data-options="region:'east',iconCls:'icon-eye',title:'API',split:true" style="width:500px;">
 			<table id="API">
 			</table>
 			</div> 
 			
 			<div id="jscodebtn">
<a id="btn" href="#" class="icon-standard-page-attach" onclick="
			var data_ = {
					jsName:jsname,
					jsValue: jsedit.getValue()
				};
				data('${ctx}' + actionUrl('/sys/', 'syDb', 'Set_Js'), data_, 'json', function(){if(jsname!=''||jsname!=undefined){
				$('#jshelpmsg').panel({
					content:'请等待3秒，在左侧数据库列表中重新选择该表！'
				});
				}});"></a> 
</div>
 			
 			<div data-options="region:'center',split:true,title:'代码',tools:'#jscodebtn'">
			<textarea id="js" style="margin:10; padding:5;"></textarea>
			</div>
			
			</div>
			</div>
			</div>
			</div>
			<!-- JS控制 end -->
		</div>
	</div>
</body>
</html>