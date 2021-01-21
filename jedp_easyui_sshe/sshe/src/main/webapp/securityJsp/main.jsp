<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");
	String type = request.getParameter("type");
	
%>
<!DOCTYPE html>
<html>
<head>
<title>复医凯泽</title>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	var mainMenu;
	var mainMenu1;
	var mainTabs;
	
	var changeToKZ = function(nodeType) {
		console.log('changeToKZ :' + nodeType);
		var MENU = $('#mainMenu');
		if(nodeType == 0){
			MENU = $('#mainMenu');
		}else{
			MENU = $('#mainMenu1');
		}
		mainMenu = MENU.tree({
			url : sy.contextPath + '/base/syresource!doNotNeedSecurity_getMainMenu.sy?nodeType='+nodeType,
			parentField : 'pid',
			onClick : function(node) {
				if(node.attributes.isOpen=='1'){
					$.messager.alert('提示', '[' + node.text + ']被关闭！', 'warn');
					return;
				}
				
				if (node.attributes.url) {
					var src = sy.contextPath + node.attributes.url;
					if (!sy.startWith(node.attributes.url, '/')) {
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.attributes.isOpen=='1' ?  'ext-icon-exclamation': node.iconCls,
							content : sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});
	};
	
	var readVersionInfo = function() {
		var dialog = parent.sy.modalDialog({
			title : '版本信息',
			url : sy.contextPath + '/securityJsp/base/VersionInfo.jsp',
			buttons : [ {
				text : '关闭',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$);
				}
			} ]
		});
	};
	

	
	$(function() {
		 $('#tt').tabs({
		      border:true,
		      onSelect:function(title, index){   
		          if(index == '0' )
		          {
		        	  console.log(index + ' is selected'); 
		        	  changeToKZ(0);
		          }else{
		        	  console.log(index +  ' is selected'); 
		        	  changeToKZ(1);
		          }
		      }
		 });
		 
		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy', $('#loginDialog form').serialize(), function(result) {
					if (result.success) {
						$('#loginDialog').dialog('close');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginDialog form :input:eq(1)').focus();
						});
					}
					$('#loginBtn').linkbutton('enable');
				}, 'json');
			}
		};
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="data.pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');
		
		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'ext-icon-lock_edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post(sy.contextPath + '/base/syuser!doNotNeedSecurity_updateCurrentPwd.sy', {
							'data.pwd' : $('#pwd').val()
						}, function(result) {
							if (result.success) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');

		mainMenu = $('#mainMenu').tree({
			url : sy.contextPath + '/base/syresource!doNotNeedSecurity_getMainMenu.sy?nodeType=0',
			parentField : 'pid',
			nodeType : '0',
			onClick : function(node) {
				if(node.attributes.isOpen=='1'){
					$.messager.alert('提示', '[' + node.text + ']被关闭！', 'warn');
					return;
				}
				
				if (node.attributes.url) {
					var src = sy.contextPath + node.attributes.url;
					if (!sy.startWith(node.attributes.url, '/')) {
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.attributes.isOpen=='1' ?  'ext-icon-exclamation': node.iconCls,
							content : sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sy.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});
		
		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
			tools : [ {
				iconCls : 'ext-icon-arrow_up',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'top'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_left',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'left'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_down',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'bottom'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_right',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'right'
					});
				}
			}, {
				text : '刷新',
				iconCls : 'ext-icon-arrow_refresh',
				handler : function() {
					var panel = mainTabs.tabs('getSelected').panel('panel');
					var frame = panel.find('iframe');
					try {
						if (frame.length > 0) {
							for (var i = 0; i < frame.length; i++) {
								frame[i].contentWindow.document.write('');
								frame[i].contentWindow.close();
								frame[i].src = frame[i].src;
							}
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
			}, {
				text : '关闭',
				iconCls : 'ext-icon-cross',
				handler : function() {
					var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
					var tab = mainTabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						mainTabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});

	});
</script>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:'<%=contextPath%>/securityJsp/north.jsp'"
		style="height: 100px; width:180px; overflow: hidden;" class="logo">
		
	</div>
	
	<div data-options="region:'west',href:'',split:true,iconCls:'ext-icon-box'" title="导航菜单"
		style="width: 200px; padding: 0px;">
		<div id="tt" class="easyui-tabs" style="width: 175px;">
			<div title="客户" style="padding: 10px" data-options="iconCls:'ext-icon-heart'" onclick="changeToKZ(0)">
				<ul id="mainMenu"></ul>
			</div>
			<div title="凯泽" data-options="iconCls:'ext-icon-box'" onclick="changeToKZ(1)"
				style="padding: 10px"><ul id="mainMenu1"></ul></div>
		</div>
	</div>
	
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="首页" data-options="iconCls:'ext-icon-heart'">
				<iframe src="<%=contextPath%>/welcome.jsp" allowTransparency="true"
					style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<%-- <div data-options="region:'south',href:'<%=contextPath%>/securityJsp/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div>
 --%>
	<div
		data-options="region: 'south', title: '复医凯泽信息管理系统...1.0.7',  iconCls: 'ext-icon-color_wheel', collapsed: false, border: false,tools:'#tool' "
		style="height: 70px;">
		
		<div
			style="color: #4e5766; padding: 6px 0px 0px 0px; margin: 0px auto; text-align: center; font-size: 12px; font-family: 微软雅黑;">
			@2013 Copyright: Allianture John Chou.&nbsp;&nbsp;|&nbsp;&nbsp; 
			<a
				href="http://www.xxx.com" target="_blank"
				style="text-decoration: none;">关于本软件</a><br /> 建议使用&nbsp; <a
				href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home"
				target="_blank" style="text-decoration: none;">IE(Version
				9/10/11)</a>/ <a
				href="https://www.google.com/intl/zh-CN/chrome/browser/"
				target="_blank" style="text-decoration: none;">Chrome</a>/ <a
				href="http://firefox.com.cn/download/" target="_blank"
				style="text-decoration: none;">Firefox</a> &nbsp;系列浏览器。
			<script type="text/javascript">
                     
               </script>
		</div>
	</div>
	 <div id="tool" style="width:200px;">
	    <div class="datagrid-btn-separator"></div>  
	  <a href="javascript:readVersionInfo();" onclick="readVersionInfo()" title="显示版本信息"
	   class="easyui-menubutton" data-options="iconCls:'ext-icon-briefcase'"></a>
 		 	
	</div>
	<%if(type != null && type.equals("3")){ %>
	<div id="" data-options="region: 'east', title: '日历', iconCls: 'icon-standard-date', split: false, minWidth: 200, maxWidth: 500"
		style="width: 220px; padding: 1px; border-left-width: 0px;">
		<div id="eastLayout" class="easyui-layout" data-options="fit: true">
			<div data-options="region: 'north', split: false, border: false"
				style="height: 220px;">
				<div class="easyui-calendar" data-options="fit: true"></div>
			</div>
			<div
				data-options="region: 'center', title: '知识库', iconCls: 'icon-hamburg-link'">
				<ul id="linkList" class="portlet-list link-list"></ul>
			</div>
		</div>
	</div>
	<%} %>
	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td><%=sessionInfo.getUser().getLoginname()%><input
						name="data.loginname" readonly="readonly" type="hidden"
						value="<%=sessionInfo.getUser().getLoginname()%>" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="data.pwd" type="password"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox"
						data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>