<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<c:import url="/admin/pages/common/headsource.jsp"/>


<style type="text/css">
.login-copyright {
	padding-top: 10px;
	text-align: center;
}

	.title-box{
		margin:auto;
		height:70px;
		font-family:"Microsoft YaHei";
		vertical-align:middle;
		display:table-cell;
	}
	.title-box b{
		padding-left:20px;
		font-weight:700;
		font-size:30px;	
		display: inline-block;
	}
	.title-box small {
		font-size: 16px;
		margin-left: 10px;
		display: inline-block;
	}

</style>	
<title>fieldmeta:${templateName}</title>
</head>
<body class="easyui-layout" data-options="fit:true">
<c:import url="/admin/pages/common/loading.jsp"/>
		<div class="title-box" data-options="region:'north'" style="height: 70px"> 
			<b>fieldmeta - 字段元数据  : 模板${templateName}</b> 
			<small>[${templateDescription}]</small>
		</div>

		<div data-options="region:'west',split:true" title="菜单" style="width:220px;">
			<ul class="easyui-tree" data-options="url:'tree_menu.json',method:'get',animate:true,onClick:treeClick,onSelect:treeSelect"></ul>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div class="easyui-tabs" id="tt" data-options="fit:true,border:false,plain:true,enableConextMenu:true,contextMenu:tab_menu">

			</div>
		</div>
		<div data-options="region:'south',split:true" style="height:50px;">
			<div class="login-copyright">
					© 2018 coderfun-boot - Powered By <a href="https://gitee.com/klguang" target="_blank">klguang</a>.
			</div>		
		</div>
</body>

<script src="http://www.easyui-extlib.com/Scripts/jquery-extensions/jquery.jdirk.js"></script>
<script src="http://www.easyui-extlib.com/Scripts/jquery-easyui-extensions/panel/jeasyui.extensions.panel.iframe.js"></script>
<script src="http://www.easyui-extlib.com/Scripts/jquery-easyui-extensions/menu/jeasyui.extensions.menu.js"></script>
<script src="http://www.easyui-extlib.com/Scripts/jquery-easyui-extensions/tabs/jeasyui.extensions.tabs.getTabs.js"></script>
<script src="http://www.easyui-extlib.com/Scripts/jquery-easyui-extensions/tabs/jeasyui.extensions.tabs.closeTabs.js"></script>
<script src="http://www.easyui-extlib.com/Scripts/jquery-easyui-extensions/tabs/jeasyui.extensions.tabs.contextMenu.js"></script>
<script>
		//tab右键刷新菜单
		var tab_menu=[{id:'refresh',text:'刷新',hideOnClick:true,handler:function(e, menuItem, menu, target, title, index){
			 var tab = $(target).tabs('getSelected');  // 获取选择的面板
			 tab.panel('refresh');
		}}]

		// 打开一个tab iframe
		function addTab(option){
			if ($('#tt').tabs('exists', option.title)){
				$('#tt').tabs('select', option.title);
			} else {
				$('#tt').tabs('add',option);
			}
		}
		//tree 的点击事件，新打开一个tab iframe
		function treeClick(node)
		{
			var title = node.text;	
			if(node.url){
				var url = node.url;
				var icon= node.iconCls;

				if(url.charAt(0) == '/')
					url = "${pageContext.request.contextPath}" + url;
				else
					url =  url;	
				addTab({
					title:title,
					closable:true,
					href:url,
					iniframe:true
				});		
			}
		}
		//tree的 select事件，打开或关闭节点的触发器，target参数是一个节点DOM对象。
		function treeSelect(node){
			$(this).tree('toggle',node.target);   
		}

</script>
</html>