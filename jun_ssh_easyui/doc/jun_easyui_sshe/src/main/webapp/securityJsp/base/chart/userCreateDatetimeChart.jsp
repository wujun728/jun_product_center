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
				filename : '用户注册时间分布'
			},
			chart : {
				type : 'column',
				margin : [ 50, 50, 100, 80 ]
			},
			title : {
				text : '用户注册时间(总用户数：0)'
			},
			xAxis : {
				categories : [ '00-02', '02-04', '04-06', '06-08', '08-10', '10-12', '12-14', '14-16', '16-18', '18-20', '20-22', '22-24' ],
				labels : {
					rotation : -45,
					align : 'right',
					style : {
						fontSize : '13px',
						fontFamily : 'Verdana, sans-serif'
					}
				}
			},
			yAxis : {
				min : 0,
				title : {
					text : '时间段用户注册数'
				}
			},
			legend : {
				enabled : false
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '点</b><br/>' + '此时间段用户注册数量为: ' + this.y + '个用户';
				}
			},
			series : [ {
				data : [],
				dataLabels : {
					enabled : true,
					rotation : -90,
					color : '#FFFFFF',
					align : 'right',
					x : 4,
					y : 10,
					style : {
						fontSize : '13px',
						fontFamily : 'Verdana, sans-serif'
					}
				}
			} ]
		});

		$.post(sy.contextPath + '/base/syuser!doNotNeedSecurity_userCreateDatetimeChart.sy', function(result) {
			var chart = $('#container').highcharts();
			chart.series[0].setData(result);
			var countUser = 0;
			for ( var i = 0; i < result.length; i++) {
				countUser += result[i];
			}
			chart.setTitle({
				text : '用户注册时间(总用户数：' + countUser + ')'
			});
			parent.$.messager.progress('close');
		}, 'json');

	});
</script>
</head>
<body>
	<div id="container"></div>
</body>
</html>