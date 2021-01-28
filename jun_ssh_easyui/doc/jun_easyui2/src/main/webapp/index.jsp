<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>系统正在启动……</title>
<style type="text/css">
.easyui-tree li a {
	font-size: 12px;
	color: #000;
	text-decoration: none;
}

.c_m_footer {
	background-color: #F1F1F1;
	border-top: 1px solid #D7D7D7;
	text-align: center;
}

a {
	color: Black;
	text-decoration: none;
	blr: expression(this.onFocus=this.blur ());
	outline: none
}

a:hover {
	color: Red;
}

.easyui-accordion ul {
	list-style-type: none;
	margin: 0px;
	padding: 10px;
}

.easyui-accordion ul li {
	padding: 0px;
}

.easyui-accordion ul li a {
	line-height: 24px;
}

.easyui-accordion ul li div {
	margin: 2px 0px;
	padding-left: 10px;
	padding-top: 2px;
}

.easyui-accordion ul li div:hover {
	background: #DCE0E1;
	cursor: pointer;
}

.easyui-accordion ul li div:hover a {
	color: #416AA3;
}

.easyui-accordion ul li div.selected {
	background: #9D9D9D;
	cursor: default;
}

.easyui-accordion ul li div.selected a {
	color: #fff;
	font-weight: bold;
}
</style>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/indexmeta.jsp"%>
<script type="text/javascript">
sysPath = '${ctx}';
themeName='default';
var currentTabTitle = '';
var login, main;
$(function() {
	tabCloseEven();
	tabClose();
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setErrorHandler(null);
	$('#chatclear').click(function(){
		$('#chartcontent').panel({
			content:''
		});
		$('.g_chart').remove();
	});
	$("#chatbut").click(function(){
		var msg=$("#chatmsg").val();
		msg="<div style='font-weight:700' class='g_chart'><span style='color:green'>"+getCurrentUserName()+'</span>:'+msg+"</div>";
		if(msg){SendMsg.sendMsg(msg);$("#chatmsg").val('')}
	});
	$('#sy_chart_dialog').dialog({onClose:function(){
		$('#syschatbtn').css("background-color",$('#i_m_header').css('background-color'));
	}});
	login = $('#login');
	main = $('#main');
	main.css("display", "none");
	login.css("display", "none");
	data('${ctx}' + actionUrl('/sys/', 'syUser', 'Get_IsLogin'), '', 'json', islogin);
	$('#loginform').keyup(function(event) {
		if (event.keyCode == 13) {
			loginFun();
		}
	});
	
});

function loginFun() {
	data_ = {
		userid: $('#username').val(),
		password: $('#password').val()
	};
	data('${ctx}' + actionUrl('/sys/', 'syUser', 'Login'), data_, 'json', islogin);
}

function islogin(d) {
	if (d.islogin==0) {
		$('#logininfo').html(d.info);
		data('${ctx}' + actionUrl('/sys/', 'syParameter', 'Get_LoginWebInfo'), '', 'json', loginweb);
	} else {
		setWeb();
		clientTime();
	}
}
function setWeb() {
	data('${ctx}' + actionUrl('/sys/', 'syParameter', 'Get_MainWebInfo'), '', 'json', web);
}


	
function web(d) {
	main.css("display", "block");
	$.each(d,
	function(k, v) {
		set(k, v);
	});
	main.layout('panel', 'center').panel({
		title: document.title
	});
	login.css("display", "none");
	//添加初始化方法
	data('${ctx}' + actionUrl('/sys/', 'syProject', 'List_All'), '', 'json', setSys);
	$('#i_u_tabs_index').tabs('update',{
		tab:$('#i_u_tabs_index').tabs('getTab','个人工作台'),
		options:{
			content:'<iframe id="grgzt" scrolling="no" frameborder="0"  src="/doroodo/portal/portal.jsp" style="width:100%;height:100%;" ></iframe>'
		}
	});
}

function setSys(d){
	var obj=$('#i_u_sys');
	var html='';
	var defproject_id=null;
	for(var i=0;i<d.length;i++){
		var project=d[i];
		if(project.projectUsed==1){
			defproject_id=project.projectName+'_sys';
		}
		html+='<div id="'+project.projectName+'_sys" onclick="changeSys(\''+project.id+'\');">'+project.projectName+'</div>';
	}
	obj.html(html);
	$('#i_u_sys_btn').menubutton({   
	    iconCls: 'icon-database_go',   
	    menu: '#i_u_sys'  
	});
	if(defproject_id){$('#'+defproject_id).click();}
}
//初始化左侧
var g_menuData = null;
var g_sysmenuData={};
var g_current_systemid='';
function changeSys(id){
	var obj={};
	obj['id']=id;
	g_current_systemid=id;
	data('${ctx}' + actionUrl('/sys/', 'syProject', 'Get_ById'), obj, 'json', function(d){
		var text=d.projectName;
		var modules=d.projectModule;
		main.layout('panel', 'west').panel({
			title: text
		});
		modules=','+modules+',';
		g_sysmenuData['menus']=new Array();
		g_sysmenuData['pid']=g_menuData.pid;
		$.each(g_menuData.menus,
				function(i, n) {
				if(modules.indexOf(','+n.menuname+',')!=-1){
					g_sysmenuData['menus'].push(n);
				}
		});
	if(!top.b_isleft) {topmenu(null);topmenu(g_sysmenuData);} return leftMenu(g_sysmenuData);
	});
}

function loginweb(d) {
	login.css("display", "block");
	$.each(d,
	function(k, v) {
		set(k, v);
	});
	$('#username').focus();
}

function leftMenu(data) {
	var pid = data.pid;
	var obj = $("#" + pid);
	var panels = obj.accordion('panels');
	var titles = new Array();
	for (var i = 0; i < panels.length; i++) {
		titles[i] = panels[i].panel('options').title;
	}
	for (var i = 0; i < titles.length; i++) {
		obj.accordion('remove', titles[i]);
	}
	if(data.menus){
	$.each(data.menus,
	function(i, n) {
		var menulist = '';
		menulist += '<ul>';
		var titles = ',';
		$.each(n.menus,
		function(j, o) {
			menulist += '<li><div><a ref="' + o.menuid + '" href="javascript:void(0);" type="nav_foot" cmshref="' + o.url + '" ><span class=' + o.icon + '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;<span class="nav">' + o.menuname + '</span></a></div></li> ';
			titles += o.menuname + ',';
		});
		menulist += '</ul>';
		obj.accordion('add', {
			title: n.menuname,
			content: menulist,
			titles: titles,
			iconCls: n.icon
		});
	});
	obj.accordion({
		animate: false
	});
	//选中第一个
	panels = obj.accordion('panels');
	initTab(pid, 'index');
	obj.accordion('select', panels[0].panel('options').title);
	}

}

//打开模块
function openModule(title){
	var leftmenu=$('#i_m_leftmenu');
	var panels=leftmenu.accordion('panels');
	for (var i = 0; i < panels.length; i++) {
		var spitf = panels[i].panel('options').titles.indexOf(',' + title + ',');
		if (spitf != -1) {
			var sel = (panels[i].panel('options').titles.substring(0, spitf).split(',').length - 1);
			leftmenu.accordion('select', panels[i].panel('options').title);
			return $('li a:eq(' + sel + ')', panels[i]).click();
		}
	}
}

//带条件的打开模块
var moduleSearch=new HashMap();
function openModuleBySearch(title,searchName,searchKey){
	moduleSearch.put('searchName',searchName);
	moduleSearch.put('searchKey',searchKey);
	openModule(title);
}

//获取左侧导航的图标
function getIcon(menuid) {
	return $("a[ref='" + menuid + "'] span:first-child").attr('class');
}

function initTab(mId, classId) {
	var panels = $('#' + mId).accordion('panels');
	$('#i_u_tabs_' + classId).tabs({
		onSelect: function(title, index) {
			currentTabTitle = title;
			$('.easyui-accordion li div').removeClass("selected");
			for (var i = 0; i < panels.length; i++) {
				var spitf = panels[i].panel('options').titles.indexOf(',' + title + ',');
				if (spitf != -1) {
					var sel = (panels[i].panel('options').titles.substring(0, spitf).split(',').length - 1);
					$('#' + mId).accordion('select', panels[i].panel('options').title);
					$('li a:eq(' + sel + ')', panels[i]).parent().attr("class", "selected");
					main.layout('panel', 'center').panel({
						title: document.title + '->' + panels[i].panel('options').title + '->' + title
					});
					return true;
				}
			}
		}
	});
	
	$('#' + mId + ' li a').die().live({
		click: function() {
			var subtitle = $('.nav', this).text();
			var url = $(this).attr('cmshref');
			if (url == (sysPath)) {
				url += "/error/404.jsp";
			}
			var menuid = $(this).attr("ref");
			var icon = getIcon(menuid);
			//更新内容到右侧的tabs内容区
			if (!$('#i_u_tabs_' + classId).tabs('exists', subtitle)) {
				$('#i_u_tabs_' + classId).tabs('addIframeTab', {
					tab: {
						title: subtitle,
						closable: true,
						icon: icon,
						tools: [{
							iconCls: 'icon-mini-refresh',
							handler: function() {
								updateTab(classId, subtitle);
							}
						}]
					},
					iframe: {
						src: url
					}
				});
				currentTabTitle = subtitle;
				$('#i_u_tabs_' + classId).tabs('addEventParam');
				tabClose() ;
				return false;
			} else {
				updateTab(classId, subtitle);
				return false;
			}
		}
	});
	
}
//更新tab功能 
function updateTab(classId, subtitle) {
	var obj = $('#i_u_tabs_' + classId);
	obj.tabs('updateIframeTab', {
		'which': subtitle
	});
	obj.tabs('select', subtitle);
	obj.tabs('addEventParam');
}
//退出
function logoutFun(path) {
	$.messager.confirm('注销', '确定要退出吗?',
	function(r) {
		if (r) {
			data(path + actionUrl('/sys/', 'syUser', 'LoginOut'), '', 'json', reloadFun);
		}
	});
}

function reloadFun() {
	window.location.reload();
}

function userEditFun(path) {
	$("input[name='syUser.userid']").val(getCurrentUserId());
	$("input[name='syUser.username']").val(getCurrentUserName());
	$("input[name='oldPassword']").val('');
	$("input[name='syUser.password']").val('');
	$('#sy_user_edit_dialog').dialog({
		left: $(window).width() - 400,
		top: 60
	});
	$('#sy_user_edit_dialog').dialog('open');
}

function sysInfo() {
	logt('系统版本:0.1 beta</br>发布时间:2014/5/23</br>使用建议：<strong style="color:green">建议使用IE8以上版本</strong>', 3);
}

function clientTime() {
	var timerSpan = $("#i_u_now"),
	interval = function() {
		timerSpan.text((new Date()).format("yyyy-MM-dd hh:mm:ss"));
	};
	interval();
	window.setInterval(interval, 1000);
}


function closeTab(title) {
	$('#i_u_tabs_index').tabs('close', title);
}


function topmenu(data) {
	if (data == null) {
		if (g_menuData != null) {
			$.each(g_menuData.menus,
			function(i, n) {
				var obj=$('#' + n.menuid);
				if(obj.length==1){obj.menubutton('destroy');}
			});
		}
	}else{
		var obj = $('#i_m_topmenu');
		if(!data.menus) return;
		$.each(data.menus,
		function(i, n) {
			var menulist = '';
			menulist += '<a id="' + n.menuid + '" ref="' + n.menuid + '" type="nav_foot"  href="javascript:void(0);" class="easyui-menubutton" data-options="menu:\'#' + n.menuid + '_reflink\',iconCls:\'' + n.icon + '\'">' + n.menuname + '</a>';
			menulist += '<div id="' + n.menuid + '_reflink" style="width: 120px; display: none;">';
			$.each(n.menus,
			function(j, o) {
				menulist += '<a onclick="topmenuclick(this)" name="' + o.menuname + '" ref="' + o.menuid + '" type="nav_foot" cmshref="' + o.url + '" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:\'' + o.icon + '\',plain:true">' + o.menuname + '</a>';
			});
			menulist += '</div>';
			obj.append(menulist);
		});
		$.parser.parse(obj);
	}
}

function topmenuclick(obj) {
	var classId = 'index';
	var subtitle = $(obj).attr('name');
	var url = $(obj).attr('cmshref');
	var icon = $(obj).linkbutton('options').iconCls;
	//更新内容到右侧的tabs内容区
	if (!$('#i_u_tabs_' + classId).tabs('exists', subtitle)) {
		$('#i_u_tabs_' + classId).tabs('addIframeTab', {
			tab: {
				title: subtitle,
				closable: true,
				icon: icon,
				tools: [{
					iconCls: 'icon-mini-refresh',
					handler: function() {
						updateTab(classId, subtitle);
					}
				}]
			},
			iframe: {
				src: url
			}
		});
		top.currentTabTitle = subtitle;
		$('#i_u_tabs_' + classId).tabs('addEventParam');
		return false;
	} else {
		updateTab(classId, subtitle);
		return false;
	}
}
var b_isleft = true;
function changeLayout() {
	if (b_isleft) {
		$('#main').layout('collapse', 'west');
		topmenu(g_sysmenuData);
		b_isleft = false;
	} else {
		$('#main').layout('expand', 'west');
		topmenu(null);
		b_isleft = true;
	}
	$('.tabs-selected').click();
}

//转换角色
function update(d) {
	if (d.info == '修改成功!') {
		setWeb();
	}
}

function cRole(obj) {
	var dataObj = {
		'rolename': $(obj).text()
	};
	data('${ctx}' + actionUrl('/sys/', 'syUser', 'E_SRole'), dataObj, 'json', update);
}

function roleHtml(v) {
	var html = '';
	var vs = v.split(',');
	for (var i = 0; i < vs.length; i++) {
		html += '<a style="font-weight:bold;color:#666666;text-decoration: underline;margin:0px;padding:0px;" href="javascript:void(0)" onclick="cRole(this)" >' + vs[i] + '</a>,';
	}
	return html;
}

function viewNotice(titleid, aobj, title, url, id, target) {
	var notice = $.window({
		title: title,
		url: sysPath + url,
		isIframe: true,
		width: $(top).width(),
		height: $(top).height() - 60,
		left: 0,
		top: 60,
		winId: id,
		target: target,
		maximizable: true,
		minimizable: true,
		buttons: [{
			text: '已阅',
			handler: function() {
				var dobj = notice.find('iframe')[0].contentWindow;
				var setData = {
					'id': titleid
				};
				data(getUrl('syNotice', 'Update_Readusernames'), setData, 'json', dobj.reshReadusernames);
				dobj.stamp();
			}
		},
		{
			text: '关闭',
			handler: function() {
				notice.window('close');
			}
		}],
		onComplete: function() {
			var obj = notice.find('iframe')[0].contentWindow;
			obj.setContent($('span', $(aobj)).html(), $(aobj).attr('fileid'), titleid);
		}
	});
}


function addChart(msg){
	var oldmsg=$('#chartcontent').panel('options').content||'';
	$('#chartcontent').panel({
		content:oldmsg+msg
	});
	$('#syschatbtn').css("background-color", "#BEBEBE");
}

function sysChart(){
	$('#syschatbtn').css("background-color",$('#i_m_header').css('background-color'));
	$('#sy_chart_dialog').dialog('open');
}

//绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#i_u_tabs_index_menu-tabupdate').click(function() {
		openModule($('#i_u_tabs_index_menu').data("currtab"));
	})
	// 关闭当前
	$('#i_u_tabs_index_menu-tabclose').click(function() {
		 if(!$('#i_u_tabs_index_menu').data("closeable"))return;
		var currtab_title = $('#i_u_tabs_index_menu').data("currtab");
		$('#i_u_tabs_index').tabs('close', currtab_title);
	})
	// 全部关闭
	$('#i_u_tabs_index_menu-tabcloseall').click(function() {
		$('.tabs-inner').each(function(i, n) {
			var t = $(n).children(".tabs-closable").text();
			if(t){//可以关闭
				$('#i_u_tabs_index').tabs('close', t);
			}
		});
	});
	// 关闭除当前之外的TAB
	$('#i_u_tabs_index_menu-tabcloseother').click(function() {
		$('#i_u_tabs_index_menu-tabcloseright').click();
		$('#i_u_tabs_index_menu-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#i_u_tabs_index_menu-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#i_u_tabs_index').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#i_u_tabs_index_menu-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			return false;
		}
		prevall.each(function(i, n) {
			var t = $(n).children(".tabs-inner").children(".tabs-closable").text();
			if(t){//可以关闭
				$('#i_u_tabs_index').tabs('close', t);
			}
		});
		return false;
	});

	// 退出
	$("#i_u_tabs_index_menu-exit").click(function() {
		$('#i_u_tabs_index_menu').menu('hide');
	});
}
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	})
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		var subtitle = $(this).text();
		var $tabmenu=$('#i_u_tabs_index_menu');
		if($(this).children(".tabs-closable").length){//可以关闭
			$tabmenu.menu('enableItem', $tabmenu.menu('findItem', '关闭').target);
			$tabmenu.data("closeable", true);
		}else{//不可以关闭
			$tabmenu.menu('disableItem', $tabmenu.menu('findItem', '关闭').target);
			$tabmenu.data("closeable", false);
		}
		$tabmenu.menu('show', {
			left : e.pageX,
			top : e.pageY
		});
		$tabmenu.data("currtab", subtitle);
		
		$('#i_u_tabs_index').tabs('select',subtitle);
		return false;
	});
}

</script>
</head>
<body class="easyui-layout" >
	<div class="easyui-layout" id="main" data-options="fit:true">
		<div data-options="region:'north',border:false"
			style="height: 60px; background: #fff; overflow: hidden; padding: 0px"
			id="i_m_header">
			<div class="c_m_navtitle"></div>
			<div style="position: absolute; right: 5px; top: 10px;">
			<a href="javascript:void(0);" id="i_u_sys_btn" style="display:display;">更换系统</a>
				欢迎您！用户名:[<strong id="i_u_username"></strong>]角色：[<strong
					id="i_u_userrole"></strong><strong style="display: display;"
					id="i_u_userid"></strong>] IP:[<strong id="i_u_userip"></strong>]
				现在:[<strong id="i_u_now"></strong>]
			</div>
			<div style="position: absolute; right: 8px; bottom: 0px;">
				<span id="i_m_topmenu"></span> <a href="javascript:void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-user_suit',plain:true"
					onclick="userEditFun('${ctx}');">个人信息</a> <a
					href="javascript:void(0);" class="easyui-menubutton"
					data-options="menu:'#layout_north_pfMenu',iconCls:'icon-wand'">界面风格</a>
				<a href="javascript:void(0);" class="easyui-linkbutton"
					onclick="changeLayout();"
					data-options="iconCls:'icon-layers',plain:true">更换布局</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					onclick="sysChart();" id="syschatbtn"
					data-options="iconCls:'icon-standard-comment',plain:true" style="display:display;">即时通讯</a><a
					href="javascript:void(0);" class="easyui-linkbutton"
					onclick="sysInfo();"
					data-options="iconCls:'icon-page_white_star',plain:true">关于</a> <a
					href="javascript:void(0);" class="easyui-linkbutton"
					onclick="logoutFun('${ctx}');"
					data-options="iconCls:'icon-exclamation',plain:true">注销</a>
			</div>
			<div id="layout_north_pfMenu" style="width: 120px; display: none;">
				<div onclick="changeTheme_('${ctx}','black');">黑色</div>
				<div onclick="changeTheme_('${ctx}','bootstrap');">银蓝</div>
				<div onclick="changeTheme_('${ctx}','default');">天空蓝</div>
				<div onclick="changeTheme_('${ctx}','gray');">银色</div>
				<div onclick="changeTheme_('${ctx}','sunny');">阳光</div>
				<div onclick="changeTheme_('${ctx}','cupertino');">清泉</div>
				<div onclick="changeTheme_('${ctx}','pepper-grinder');">古典</div>
				<div onclick="changeTheme_('${ctx}','metro');">美俏</div>
				<div onclick="changeTheme_('${ctx}','metro-blue');">美俏-蓝</div>
				<div onclick="changeTheme_('${ctx}','metro-gray');">美俏-灰</div>
				<div onclick="changeTheme_('${ctx}','metro-green');">美俏-绿</div>
				<div onclick="changeTheme_('${ctx}','metro-orange');">美俏-橙</div>
				<div onclick="changeTheme_('${ctx}','metro-red');">美俏-红</div>
			</div>
		</div>
		<div data-options="region:'west',split:true,title:'导航菜单'"
			style="width: 225px; overflow: hidden;">
			<div class="easyui-accordion" data-options="fit:true"  border="false"  id="i_m_leftmenu">
				<!--  模块导航内容 -->
			</div>
		</div>
			<div data-options="region:'south',border:false"
			style="height: 25px;  overflow: hidden;">
			<div class="c_m_footer"></div>
		</div>
		<!--//主体内容部分-->
		<div data-options="region:'center'" title="主页"
			style="overflow: hidden;">

			<div id="i_u_tabs_index" class="easyui-tabs" fit="true"
				border="false">
				<div title="个人工作台"  data-options="iconCls:'icon-house',tools:[{iconCls:'icon-mini-refresh',handler:function(){$('#grgzt')[0].contentWindow.reshIWork();}}]" >
					
				</div>
			</div>

		</div>
		<!--center-->
	</div>
	<!-- login start -->
	<div id="login"
		style="z-index:1;margin: 0; padding: 0; width: 100%; height: 100%; background: url(images/lg7.jpg) no-repeat;background-size: 100% 100%;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale', src='images/lg7.jpg')">
		<div id="i_u_login_header"
			style="background-color: #666666; color: #fff; height: 35px; font-size: 24px; text-align: center; width: 100%">
		</div>
		<!-- <h1 style="z-index:999;position: relative; left:100px;top: 300px;">欢迎使用DOROODO智能生成平台</h1> -->
		<h1 style="z-index:999;position: relative; left:200px;top: 300px;">建议使用微软IE8.0以上浏览器</h1>
		<div
			style="background-color: #FFF; margin-left: auto; margin-right: 150px; position: relative; top: 40px; width: 350px; height: 140px; font-size: 12px; font: bold;">
			<div
				style="position: absolute; top: 15px; left: 20px; height: 26px; width: 300px;">
				<form id="loginform">
					<table>
						<tr>
							<td>登录名:</td>
							<td><input type="text" id="username" style="width: 230px"></input>
							</td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="password" style="width: 230px"
								id="password"></input></td>
						</tr>
						<tr>
							<td></td>
							<td style="float: right"><a href="javascript:void(0)"
								class="easyui-linkbutton" icon="icon-bullet_go"
								style="background-color: #E0ECFF;" plain="true"
								onclick="loginFun();">登录</a></td>
						</tr>
						<tr>
						<td>系统提示:</td>
						<td><span id="logininfo" style="color:red;margin-right:5px;padding:2px;"></span></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="i_u_login_foot"
			style="position: absolute; bottom: 0px; background-color:#666666; height: 20px; color: #fff; text-align: right; width: 100%">
		</div>
	</div>
	<!-- login end -->
	<!-- useredit start -->
	<div id="sy_user_edit_dialog" class="easyui-dialog"
		data-options="closed:true,title:'个人信息',buttons:[{
			text : '提交',
			iconCls : 'icon-save',
			handler : function() {
				$('#sy_user_edit_form').form('submit', {
					url : '${ctx}'+actionUrl('/sys/','syUser','Update_'),
					success : function(r) {
					if(r=='') return;
						var obj = jQuery.parseJSON(r);
						setWeb();
						$('#sy_user_edit_dialog').dialog('close');
						log(obj.info);
					}
				});
			}
		}]"
		style="width: 400px; height: 210px;" align="center">
		<form id="sy_user_edit_form" method="post">
			<div style="padding: 10px 100px 10px 5px">
				<table width="100%">
					<tr>
						<td align="left" width="20%">登录名:</td>
						<td width="70%" style="text-align: left" align="left"><input
							name="syUser.userid" type="text" readonly="readonly"
							style="width: 70%"></input></td>
					</tr>
					<tr>
						<td align="left" width="20%">用户名：</td>
						<td width="70%" style="text-align: left" align="left"><input
							name="syUser.username" type="text" class="easyui-validatebox"
							data-options="required:true" style="width: 70%"></input></td>
					</tr>
					<tr>
						<td align="left" width="20%">旧密码:</td>
						<td width="70%" style="text-align: left" align="left"><input
							class="easyui-validatebox" type="password" name="oldPassword"
							data-options="required:true" style="width: 70%"></input></td>
					</tr>
					<tr>
						<td align="left" width="20%">新密码:</td>
						<td width="70%" style="text-align: left" align="left"><input
							type="password" name="syUser.password" style="width: 70%"></input>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<!-- useredit end -->
	<!-- chart start -->
	<div id="sy_chart_dialog" class="easyui-dialog"  
		data-options="closed:true,title:'即时通讯',iconCls:'icon-standard-comment'"  style="width: 400px; height: 400px;" align="center">
		<div class="easyui-layout" fit=true>
		<div data-options="region:'north',split:true" style="height:320px;">
		<div id="chartcontent" class="easyui-panel"     
        style="background:#fafafa;"  
        data-options="closable:false,   
                collapsible:false,minimizable:false,maximizable:false,fit:true"> 
 		</div>
		</div>
		<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<input type="text" id="chatmsg" style="width:60%" /><input type="button" value="发送" id="chatbut" /><input type="button" value="清空" id="chatclear" />
		</div>  
		</div>
		</div>
		<!-- chart end -->
		
	<!-- sys start -->
	<div id="i_u_sys" style="display: none;">
	</div>
	<!-- sys end -->
</body>
	<div id="i_u_tabs_index_menu" class="easyui-menu" style="width: 150px;">
			<div id="i_u_tabs_index_menu-tabupdate">刷新</div>
			<div id="i_u_tabs_index_menu-tabclose">关闭</div>
			<div id="i_u_tabs_index_menu-tabcloseall">全部关闭</div>
			<div id="i_u_tabs_index_menu-tabcloseother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="i_u_tabs_index_menu-tabcloseright">当前页右侧全部关闭</div>
			<div id="i_u_tabs_index_menu-tabcloseleft">当前页左侧全部关闭</div>
		</div>
</html>