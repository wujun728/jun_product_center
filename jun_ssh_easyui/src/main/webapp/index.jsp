<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="layout/script.jsp"></jsp:include>
	<!-- 加载 Mock -->
	<script src="http://mockjs.com/dist/mock.js"></script>
	<script type="text/javascript">
		$(function(){
	        Mock.mock('systemAction!findAllFunctionList.action',
	        		[{"child":[{"child":[],"iconCls":"icon-pro","name":"菜单管理1111","url":"jsp/function/functionMain.jsp"},{"child":[],"iconCls":"icon-db","name":"数据库管理","url":"druid/index.html"},{"child":[],"iconCls":"icon-config","name":"权限分派","url":"jsp/permission/permissionAssignmentMain.jsp"},{"child":[],"iconCls":"icon-adds","name":"用户管理","url":"jsp/user/userMain.jsp"},{"child":[],"iconCls":"icon-role","name":"用户角色分派","url":"jsp/roleConfig/roleConfigMain.jsp"},{"child":[],"iconCls":"icon-tip","name":"组织管理","url":"jsp/organization/organizationMain.jsp"},{"child":[],"iconCls":"icon-pro","name":"日志管理","url":"jsp/logs/logsMain.jsp"},{"child":[],"iconCls":"icon-undo","name":"数据字典","url":"jsp/systemCode/systemCodeMain.jsp"},{"child":[],"iconCls":"icon-remove","name":"参数设置","url":"jsp/sysParameter/sysParameterMain.jsp"},{"child":[],"iconCls":"icon-bug","name":"Bug管理","url":"jsp/bugManager/bugMain.jsp"}],"iconCls":"icon-sys","name":"系统管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-edit","name":"公司档案录入","url":"jsp/company/companyMain.jsp"}],"iconCls":"icon-comp","name":"公司管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-item","name":"物料档案录入","url":"jsp/itemManager/itemMain.jsp"},{"child":[],"iconCls":"icon-excel","name":"物料档案导入","url":"jsp/docView/view.jsp"}],"iconCls":"icon-item","name":"物料管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-edit","name":"客户档案录入","url":"jsp/cstManager/cstMain.jsp"},{"child":[],"iconCls":"icon-excel","name":"客户档案导入","url":"sdf"},{"child":[],"iconCls":"icon-end","name":"客户订单录入","url":"jsp/orderSale/orderSaleMain.jsp"}],"iconCls":"icon-role","name":"客户管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-tip","name":"供应商档案录入","url":"jsp/supManager/supMain.jsp"},{"child":[],"iconCls":"icon-excel","name":"供应商档案导入","url":"sdf"},{"child":[],"iconCls":"icon-adds","name":"采购单录入","url":"jsp/orderPurchase/orderPurchaseMain.jsp"}],"iconCls":"icon-role","name":"供应商管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-adds","name":"项目档案录入","url":"jsp/project/projectMain.jsp"}],"iconCls":"icon-sys","name":"项目管理","url":"javascript:void(0);"},{"child":[{"child":[],"iconCls":"icon-end","name":"数据备份","url":"jsp/dbBackUp/dbBackUpMain.jsp"}],"iconCls":"icon-db","name":"数据备份","url":"javascript:void(0);"}]
	        );
	        Mock.mock('functionAction!findAllFunctionList.action',
	        		[{"created":"2013-05-23 00:00:00","creater":1,"description":"系统管理","iconCls":"icon-sys","isused":"Y","lastmod":"2013-06-18 00:00:00","modifyer":1,"myid":"sysMgr","name":"系统管理","permissionId":1,"pname":"","sort":0,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-05-23 00:00:00","creater":1,"description":"公司管理","iconCls":"icon-comp","isused":"Y","lastmod":"2013-06-20 15:33:13","modifyer":1,"myid":"compMgr","name":"公司管理","permissionId":3,"pname":"","sort":0,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-05-23 00:00:00","creater":1,"description":"","iconCls":"icon-item","isused":"Y","lastmod":"2013-08-05 10:09:30","modifyer":1,"myid":"itemMgr","name":"物料管理","permissionId":6,"pname":"","sort":1,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-06-24 09:45:45","creater":1,"description":"","iconCls":"icon-role","isused":"Y","lastmod":"2013-06-24 09:45:45","modifyer":1,"myid":"cstMgr","name":"客户管理","permissionId":68,"pname":"","sort":3,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-06-26 12:27:50","creater":1,"description":"","iconCls":"icon-role","isused":"Y","lastmod":"2013-06-26 12:29:22","modifyer":1,"myid":"supMgr","name":"供应商管理","permissionId":74,"pname":"","sort":5,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-06-27 12:02:43","creater":1,"description":"","iconCls":"icon-sys","isused":"Y","lastmod":"2013-06-27 12:02:43","modifyer":1,"myid":"projectMgr","name":"项目管理","permissionId":87,"pname":"","sort":5,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"},{"created":"2013-06-28 13:32:09","creater":1,"description":"","iconCls":"icon-db","isused":"Y","lastmod":"2013-06-28 13:32:22","modifyer":1,"myid":"dbBackUp","name":"数据备份","permissionId":92,"pname":"","sort":7,"state":"closed","status":"A","type":"F","url":"javascript:void(0);"}]
    		);
	        /* var newsList = $('#news_list');
	        $.ajax({
	            url: "http://g.cn",
	            dataType: "json"
	        }).done(function(data, status, xhr){
	            // console.log(JSON.stringify(data, null, 4));
	            for(let i = 0; i < data.news.length; i++){
	                var news = data.news[i];
	                newsList.append(`
	                <li class='list-group-item'> <a href=''>
	                    <h4>  ${news.ctitle} </h4>
	                    </a>
	                </li>`);
	            }
	        }); */
	    });
	</script>
	<script type="text/javascript">
		$(function(){
			initMenu();
			if (jqueryUtil.isLessThanIe8()) {
				$.messager.show({
					title : '警告',
					msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
					timeout : 1000 * 30
				});
			}
		});
		function initMenu(){
			var $ma=$("#menuAccordion");
			$ma.accordion({animate:true,fit:true,border:false});
			$.post("systemAction!findAllFunctionList.action", {userName:"1"}, function(rsp) {
				$.each(rsp,function(i,e){
					var menulist ="<div class=\"well well-small\">";
					if(e.child && e.child.length>0){
						$.each(e.child,function(ci,ce){
							var effort=ce.name+"||"+ce.iconCls+"||"+ce.url;
							menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
						});
					}
					menulist+="</div>";
					$ma.accordion('add', {
			            title: e.name,
			            content: menulist,
						border:false,
			            iconCls: e.iconCls,
			            selected: false
			        });
				});
			}, "JSON").error(function() {
				$.messager.alert("提示", "获取菜单出错,请重新登陆!");
			});
		}
	</script>
	<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-body {
		padding:5px;
	}
	#menuAccordion span:focus{
		outline: none;
	}
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:40px;background:#EEE;padding:10px;overflow: hidden;"  href="layout/north.jsp"></div>
	<div data-options="region:'west',split:true,title:'主要菜单'" style="width:200px;">
			<div id="menuAccordion"></div>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="layout/south.jsp"></div>
	<div data-options="region:'center',plain:true,title:'欢迎使用ERP'" style="overflow: hidden;"  href="layout/center.jsp"></div>
<%--	<jsp:include page="user/loginAndReg.jsp"></jsp:include>--%>
</body>
</html>
