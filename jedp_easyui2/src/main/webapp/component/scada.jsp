<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jtopo/topotools/css/menu.css"/>
<script src="${ctx}/plug/jtopo/topotools/require/jtopo-0.4.8-min.js"></script>
<script src="${ctx}/plug/jtopo/topotools/require/Map.js"></script>
<script src="${ctx}/plug/jtopo/topotools/require/Math.uuid.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/view-tools-core.js"></script>
<script src="${ctx}/plug/jtopo/topotools/js/view-tools-core3.js"></script>

<link rel="Stylesheet" type="text/css"
	href="${ctx}/plug/jpicker-1.1.6/css/jPicker-1.1.6.css" />
<link rel="Stylesheet" type="text/css"
	href="${ctx}/plug/jpicker-1.1.6/jPicker.css" />
<script src="${ctx}/plug/jpicker-1.1.6/jpicker.js"
	type="text/javascript"></script>
<script>
	var flag = 0;
	var pageJson = null;
	var realData = {};
	$(document)
			.ready(
					function() {
						$.fn.jPicker.defaults.images.clientPath = '${ctx}/plug/jpicker-1.1.6/images/';
						$("#colorMain").jPicker({
							window : {
								title : '颜色选择器'
							}
						});
						var canvas = document.getElementById('canvas__');
						stage = new JTopo.Stage(canvas);
						scene = new JTopo.Scene();
						stage.add(scene);
						initRightButton();
						scene.mode = 'select';
						scene.areaSelect = false;
						renderPage(renderToPo);
						//menuJs("javascriptMain");
						scene.cpage=$("#a-return-page").attr("pid");
						if($("#a-return-page").attr("rid")!=""){
							$("#a-return-page").show();
						}else{
							$("#a-return-page").hide();
						}
						scene.rpage=$("#a-return-page").attr("rid")+"-"+$("#a-return-page").attr("pid");
						
					});

	function renderPage(_callback) {
		var pgId = $.getUrlVar("id");
		var arg = {};
		arg["id"] = pgId;
		data('${ctx}/sys/sySbProjectPage_Get_ById', arg, 'json', function(d) {
			try {
				scene.alpha=1;
				scene.backgroundColor=d.backgroundColor;
				pageJson = eval(d.pageContent);
				_callback(pageJson);
				console.info('xxxxxxxxx')
				//LoadData(pageJson);
				LoadDataSence(scene)
			} catch (ex) {
				log(ex)
			}
		});
	}

	function LoadData(nodeJson) {
		try {
			for (var i = 0; i < nodeJson.length; i++) {
				if (nodeJson[i]["fields"] == "undefined") {
					continue;
				} else {
					getPointByObj(nodeJson[i], setValByNode);
				}
			}
		} catch (ex) {
			console.info(ex)
		}
	}

	function LoadDataSence(sence) {
		for (var i = 0; i < scene.childs.length; i++) {
			var _node = scene.childs[i];
			if (_node.fields) {
				var _fields = _node.fields;
				for (var j = 0; j < _fields.length; j++) {
					if (_fields[j].name && _fields[j].point) {
						_fields[j]["val"] = "";
						_node["point_id"] = _fields[j].point;
						var point={};
						point["id"]=_fields[j].point;
						point["index"]=j;
						getPointByObj(point, setValByNode,_node);
					}
				}
			}
		}
	}

	function getPointByObj(point,_callback,obj) {
		var arg = {};
		arg["id"] = point.id;
		data('/doroodo/sys/syPoint_Get_By_Code', arg, 'json', function(d) {
			try {
				if (d.codeCal) {
					eval(d.codeCal);
					point["vars"] = _vars;
					point["formula"] = _formula;
					getVarsByObj(point, _callback,obj);
				}
			} catch (ex) {
				log("err:" + ex.message)
			}
		});
	}

	function getVarsByObj(point, _callback, obj) {
		var arg = {};
		_vars = point["vars"];
		_formula = point["formula"];
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
		data('/doroodo/sys/syPoint_GetJs_By_Vars_Arg', arg, 'json',
				function(d) {
					point['value'] = eval(
							"function getPv(){var val='';" + d.js + _formula
									+ " return val;}getPv")();
					_callback(point,obj);
				});
	}

	function setValByNode(point,node) {
		var _field=node[point.index];
		_field['val']=point.value;
		showMsgBar(node.cNodeId, node.fields);
	}

	function setValByNodeId(nodeId, value) {
		for (var i = 0; i < scene.childs.length; i++) {
			if (scene.childs[i].nodeId == nodeId) {
				node = scene.childs[i];
				node.text = value;
				node.alarmColor = '0,255,0';
			}
		}
	};
	function enlarge() {
		stage.zoomOut();
	}
	function narrow() {
		stage.zoomIn();
	}

	function getRightMenu(nodeId) {
		var setData = {};
		setData["sySbRightEventInfo.nodeId"] = nodeId;
		var contextMenu = "",jsStr = "";

		dataNoWait('/doroodo/sys/sySbRightEventInfo_Get_ByObj', setData, 'json',
				function(d) {
					for (var i = 0; i < d.length; i++) {
						contextMenu += '<li onclick="R' + d[i].nodeId + d[i].id
								+ '()"><a>' + d[i].menuName + '</a></li>';
						var tx = d[i].nodeId + d[i].id;
						var jscontent = d[i].eventContent;
						jsStr += 'function R' + tx + '(){' + jscontent
								+ '$("#contextmenu").hide();}';
					}
					$("#contextmenu").html(contextMenu);
					$("#javascriptMain").html("<script>" + jsStr + "<\/script>");
				});
	}
	//分合控制
	function cmdControl(cnode) {
		//getState(node)
		arg = {};
		arg['id'] = cnode.dId;
		data(
				'/doroodo/sys/cxSbTerminal_Get_ById',
				arg,
				'json',
				function(d) {
					if (d.state && d.state == "分") {
						$.messager
								.confirm(
										'分合闸操作确认',
										d.position + '当前闸位状态是：分，确认对['
												+ d.position + ']进行合闸操作！',
										function(r) {
											if (r) {
												var cmd = {};
												cmd["id"] = cnode.dId;
												cmd["cmd"] = "合";
												data(
														'/doroodo/sys/cxSbTerminal_Control_ByCmd',
														cmd,
														'json',
														function(res) {
															if (res == "0") {
																log('操作成功');
																cnode.state = "合";
																cnode
																		.setImage(
																				"${ctx}/images/close.jpg",
																				false);
															} else {
																log('操作失败');
																cnode.state = d.state;
																cnode
																		.setImage(
																				"${ctx}/images/open.jpg",
																				false);
															}
														});
											} else {
												//检查页面的闸位状态是否与实际一致
												cnode.state = d.state;
												cnode
														.setImage(
																"${ctx}/images/open.jpg",
																false);
											}
										});
					} else {
						$.messager
								.confirm(
										'分合闸操作确认',
										d.position + '当前闸位状态是：合，确认对['
												+ d.position + ']进行分闸操作！',
										function(r) {
											if (r) {

												var cmd = {};
												cmd["id"] = cnode.dId;
												cmd["cmd"] = "分";
												data(
														'/doroodo/sys/cxSbTerminal_Control_ByCmd',
														cmd,
														'json',
														function(res) {
															if (res == "0") {
																log('操作成功');
																cnode.state = "分";
																cnode
																		.setImage(
																				"${ctx}/images/open.jpg",
																				false);
															} else {
																log('操作失败');
																cnode.state = d.state;
																cnode
																		.setImage(
																				"${ctx}/images/close.jpg",
																				false);
															}
														});
											} else {
												//检查页面的闸位状态是否与实际一致
												cnode.state = d.state;
												cnode
														.setImage(
																"${ctx}/images/close.jpg",
																false);

											}
										});
					}
				});
	}
	function returnPage(obj){
		//history.go(-1);
		console.info('return......')
		var _cpage=$(obj).attr('pid');
		var _rpage=$(obj).attr('rid');
		var cpage=_rpage.split('-')[_rpage.split('-').length-1];
		var rpage=_rpage.substring(0,_rpage.indexOf("-"+cpage));
		if(rpage==""){
			$(obj).hide();
		}
		location.href='/doroodo/component/scada.jsp?id='+cpage+'&rid='+rpage;
	}
	
	function loadStatus(node){
		if(!node.dataId||node.dataId=="undefined"){
			return;
		}
		if(!node.stausImages||node.stausImages=="undefined"){
			return;
		}
		var point={};
		point["id"]=node.dataId;
		point["index"]=0;		
		getPointByObj(point,changeStatusImg,node);
		
	}
	
	function changeStatusImg(point,node){
		var img="";
		for(var i=0;i<node.stausImages.length;i++){
			if(node.stausImages[i].status==point.value){
				img=node.stausImages[i].images;
				break;
			}
		}
		if(img=="")return;
		node.state = d.state;
		node.setImage(img,false);
	}
	var b_istop = true;
	function changeLayout() {
    	if (b_istop) {
    		parent.$('#main').layout('collapse', 'north');
    		b_istop = false;
    	} else {
    		parent.$('#main').layout('expand', 'north');
    		b_istop = true;
    	}
    }
</script>
</head>
<body>

	<div id="cc" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'组态工程浏览'"
			style="padding: 5px; background: #eee;">
			<table style="width:99%"><tbody><tr><td align="left">
			<div id="toolbar" >
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="enlarge()">放大</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="narrow()">缩小</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-wand" plain="true"
					onclick="javascript:$('#colorcaiter').dialog('open');">设置背景</a>
					<a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-layers" plain="true"
					onclick="changeLayout()">布局</a>
			</div>
			</td><td align="right"><div><a id="a-return-page" href="javascript:void(0);" pid="${param.id}" rid="${param.rid}" onclick="returnPage(this)" style="display:none;">返回</a></div></td></tr></tbody></table>

			<canvas width='1205' height='696' style="border: 1px solid #808080;" id='canvas__'></canvas>
			<ul id="contextmenu" style="display: none; font-size: 12px">
				
			</ul>
		</div>
	</div>
	<div id="colorcaiter" class="easyui-dialog" title="选择颜色"
		style="width: 600px; height: 360px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<div id="colorMain" style="width: 100%; height: 100%;"></div>
	</div>
	<div id="javascriptMain" style="display: none;"></div>
</body>
</html>