<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title>资源管理</title>

<script type="text/javascript">
	$(function() {
		jQuery('#tree').jqGrid({
			url : '${pageContext.request.contextPath}/demoResourceController/findTreeGrid.do',
			mtype : 'POST',
			colNames : [ '主键', '名称' ],
			colModel : [ {
				name : 'id',
				hidden : true
			}, {
				name : 'name'
			} ],
			width : '780',
			gridview : true,
			height : 'auto',
			scrollrows : true,
			treeGrid : true,
			treeGridModel : 'adjacency',
			ExpandColumn : 'name',
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
			treeReader : {
				level_field : 'level',// number 表示此数据在哪一级
				parent_id_field : 'parent', // mixed 表示父节点是哪个，如果当前节点为父节点，则值为null，不是父节点则为其父节点ID
				leaf_field : 'isLeaf',// boolean 表示此数据是否为叶子节点
				expanded_field : 'expanded',// boolean 表示此节点是否展开
				loaded : 'loaded'// boolean 表示是否加载完成，设置为True表示加载完成，不需要在加载
			},
			sortname : 'id',
			sortorder : 'asc',
			datatype : 'json'
		});
	});
</script>

</head>
<body>

	<table id="tree"></table>

</body>
</html>