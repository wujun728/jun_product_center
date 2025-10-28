<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title>用户管理</title>

<script type="text/javascript">
	$(function() {

		$('#grid').jqGrid({
			url : '${pageContext.request.contextPath}/controllerLogController/find.do',
			datatype : 'json',
			mtype : 'POST',
			colNames : [ '主键', '类名', '方法名', '方法全名', '中文方法名', '参数内容', '返回值', 'IP地址', '操作用户', '操作时间' ],
			colModel : [ {
				name : 'id',
				width : 50
			}, {
				name : 'className',
				width : 250
			}, {
				name : 'methodName',
				width : 60
			}, {
				name : 'methodFullName',
				width : 700
			}, {
				name : 'methodCnName',
				width : 80
			}, {
				name : 'argsContent',
				width : 800
			}, {
				name : 'returnValue',
				width : 800
			}, {
				name : 'ip',
				width : 100
			}, {
				name : 'userName',
				width : 100
			}, {
				name : 'created',
				width : 150
			} ],
			pager : '#pager',
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			sortname : 'id',
			sortorder : 'desc',
			viewrecords : true,
			gridview : true,
			autoencode : true,
			caption : '系统操作日志',
			prmNames : {
				page : 'page', // 表示请求页码的参数名称  
				rows : 'pageSize', // 表示请求行数的参数名称  
				sort : 'sort', // 表示用于排序的列名的参数名称  
				order : 'order', // 表示采用的排序方式的参数名称  
				search : 'search', // 表示是否是搜索请求的参数名称  
				nd : 'nd', // 表示已经发送请求的次数的参数名称  
				id : 'id', // 表示当在编辑数据模块中发送数据时，使用的id的名称  
				oper : 'oper', // operation参数名称（我暂时还没用到）  
				editoper : 'update', // 当在edit模式中提交数据时，操作的名称  
				addoper : 'save', // 当在add模式中提交数据时，操作的名称  
				deloper : 'delete', // 当在delete模式中提交数据时，操作的名称  
				subgridid : 'id', // 当点击以载入数据到子表时，传递的数据名称  
				npage : null,
				totalrows : 'totalRows' // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal  
			},
			jsonReader : {
				repeatitems : false,// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
				root : 'rows', // json中代表实际模型数据的入口  
				page : 'page', // json中代表当前页码的数据  
				total : 'totalPage', // json中代表页码总数的数据  
				records : 'totalRows' // json中代表数据行总数的数据
			},
			deepempty : true,
			autowidth : true,
			shrinkToFit : false,
			autoScroll : false,
			height : '100%',
			emptyrecords : '无数据',
			rownumbers : true,
			sortable : true,
			multiselect : true,
			/* scroll : 10,//实现一个页面分页 */
			onSelectRow : function(id) {
				console.info(id);
				console.info($(this).jqGrid('getRowData', id));
			},
			loadComplete : function(result) {
				if (result && !result.success) {
					if (result.msg) {
						alert(result.msg);
					}
				}
			}
		});

	});

	function _search() {
		$("#grid").jqGrid('setGridParam', {
			postData : null
		});
		$("#grid").jqGrid('setGridParam', {
			postData : sy.serializeObject($('#searchForm'))
		});
		$("#grid").trigger("reloadGrid");
	}
</script>

</head>
<body>

	<form id="searchForm">
		<table>
			<tr>
				<td>类名</td>
				<td><input name="Q_t.className_like" /></td>
			</tr>
			<tr>
				<td>方法名</td>
				<td><input name="Q_t.methodName_like" /></td>
			</tr>
			<tr>
				<td>方法全名</td>
				<td><input name="Q_t.methodFullName_like" /></td>
			</tr>
			<tr>
				<td>中文方法名</td>
				<td><input name="Q_t.methodCnName_like" /></td>
			</tr>
			<tr>
				<td>参数内容</td>
				<td><input name="Q_t.argsContent_like" /></td>
			</tr>
			<tr>
				<td>返回值</td>
				<td><input name="Q_t.returnValue_like" /></td>
			</tr>
			<tr>
				<td>IP地址</td>
				<td><input name="Q_t.ip_like" /></td>
			</tr>
			<tr>
				<td>操作用户</td>
				<td><input name="Q_t.userName_like" /></td>
			</tr>
			<tr>
				<td>操作时间</td>
				<td><input name="Q_t.created_>=_dateTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />至<input name="Q_t.created_<=_dateTime" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
			</tr>
		</table>
		<button type="button" onclick="_search();">查询</button>
	</form>

	<table id="grid"></table>
	<div id="pager"></div>

</body>
</html>