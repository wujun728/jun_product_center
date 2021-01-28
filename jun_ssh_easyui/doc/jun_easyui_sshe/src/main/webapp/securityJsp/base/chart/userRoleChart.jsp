<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	sy.util.base.SecurityUtil securityUtil = new sy.util.base.SecurityUtil(session);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {

		parent.$.messager.progress({
			text : '数据加载中....'
		});

		$('#container').highcharts({
			exporting : {
				filename : '用户角色分布'
			},
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			title : {
				text : '用户角色分布'
			},
			tooltip : {
				pointFormat : '{series.name}: <b>{point.y}</b>'
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						enabled : true,
						color : '#000000',
						connectorColor : '#000000',
						formatter : function() {
							return '<b>' + this.point.name + '</b>角色：' + this.y + ' 人';
						}
					}
				}
			},
			series : [ {
				type : 'pie',
				name : '用户数量：',
				data : []
			} ]
		});

		$.post(sy.contextPath + '/base/syrole!doNotNeedSecurity_userRoleChart.sy', function(result) {
			var chart = $('#container').highcharts();
			chart.series[0].setData(result);

			parent.$.messager.progress('close');
		}, 'json');

	});
</script>
</head>
<body>
	<div id="container"></div>
</body>
</html>