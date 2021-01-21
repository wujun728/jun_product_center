<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.smartport{
height:200px; padding: 5px;
}
</style>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jquery-easyui-portal/portal.css" />
<script type="text/javascript" src="${ctx}/plug/jquery-easyui-portal/jquery.portal.js"></script>
<script type="text/javascript" src="${ctx}/js/portal.js"></script>


<script type="text/javascript">

sysPath = '${ctx}';

var portalData={
		panels:[{
		    id: 'p0',
		    title: '断路器分合信息',
		    collapsible: false,
		    closable:false,
		    type:portalType.datagrid,
		    dataurl: sysPath + actionUrl('/sys/', 'cxSbTerminal', 'List'),
		    uiurl:'/sys/syParameter_Go_CxSbTerminal?gk=2',
		    hiddencols:'simCard,terminalNumber,id',
		    obj:null
			},{
			    id: 'p1',
			    title: '通知公告',
			    collapsible: false,
			    closable:false,
			    type:portalType.datagrid,
			    dataurl: sysPath + actionUrl('/sys/', 'syNotice', 'List'),
			    uiurl:'/sys/syParameter_Go_SyNotice',
			    hiddencols:'id',
			    obj:null
				}
			/*
			,{
				    id: 'p3',
				    title:'通知公告3',
				    type:portalType.url,
				    collapsible: false,
				    closable:false,
				    uiurl:'http://www.baidu.com'
				}
			*/
			,{
				    id: 'p4',
				    title:'故障统计',
				    type:portalType.url,
				    collapsible: false,
				    closable:false,
				    uiurl:'/chart/fault.html?id=1&width=0&height=200'
				},{
				    id: 'p5',
				    title:'实时故障信息',
				    type:portalType.datagrid,
				    collapsible: false,
				    closable:false,
				    dataurl: sysPath + actionUrl('/sys/', 'vcxSbTakeStateMonitor', 'List'),
				    uiurl:'/sys/syParameter_Go_VcxSbTakeStateMonitor'
				}],
				sort:'p0,p4:p1,p5',//在一起的用，分割，不在一起的用：分割
				width:'50:50'
	}
	
	$(function(){
		portalData.panels[2].uiurl='/chart/fault.html?id=1&width=0&height='+(($('#portal').height()-70)/2-30);
		initPortal(portalData);
	});
</script>
</head>
<body class="easyui-layout" fit="true" id="workspace">
								<div data-options="region: 'center'" fit="true" id="portal" style="position:relative">
								</div>
								<!-- portal html start -->
							    <!-- portal html end -->
	<!-- toolbar start-->
		<div id="g_toobar"></div>
		<!-- toolbar end-->
</body>
</html>