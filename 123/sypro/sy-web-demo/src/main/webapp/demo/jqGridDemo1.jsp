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
			url : '${pageContext.request.contextPath}/demoUserController/find.do',
			datatype : 'json',
			mtype : 'POST',
			colNames : [ '主键', '姓名', '年龄', '生日' ],
			colModel : [ {
				name : 'id'
			}, {
				name : 'name'
			}, {
				name : 'age'
			}, {
				name : 'birthday',
				formatter : function(value) {
					if (value) {
						return value.substr(0, 10);
					}
					return value;
				}
			} ],
			pager : '#pager',
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			sortname : 'id',
			sortorder : 'desc',
			viewrecords : true,
			gridview : true,
			autoencode : true,
			caption : '用户管理',
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
			autowidth : true,
			emptyrecords : '无数据',
			rownumbers : true,
			sortable : true,
			multiselect : true,
			/*scroll : 10,//实现一个页面分页*/
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

	function del() {
		$("#grid").jqGrid('delRowData', 100);
	}
	function delRow() {
		var gr = $("#grid").jqGrid('getGridParam', 'selrow');
		if (gr != null) {
			$("#grid").jqGrid('delGridRow', gr, {
				reloadAfterSubmit : false
			});
		} else {
			alert('请选中要删除的行！');
		}
	}
	function changePostData() {
		$("#grid").jqGrid('setGridParam', {
			postData : {
				q : 1,
				param1 : "p1"
			}
		});
		$("#grid").trigger("reloadGrid");
	}
	function delPostData() {
		delete $("#grid").jqGrid('getGridParam', 'postData')['param1'];
	}
	function getPostData() {
		console.info($("#grid").jqGrid('getGridParam', 'postData'));
	}
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
		用户名(模糊)：<input name="Q_t.name_like" value="孙" /> 年龄在：<input name="Q_t.age_>=_short" value="0" /> - <input name="Q_t.age_<=_short" value="100" />之间
		<button type="button" onclick="_search();">查询</button>
	</form>

	<table id="grid"></table>
	<div id="pager"></div>

	<button onclick="$('#grid').trigger('reloadGrid');">重新加载数据</button>
	<button onclick="del();">删除</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','url'));">获得url</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','sortname'));">获得Sort Name</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','sortorder'));">获得Sort Order</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','selrow'));">获得最后选中的行的id</button>
	<button onclick="alert($('#grid').jqGrid('getGridParam','selarrrow'));">获得选中的ids</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','page'));">获得当前页码</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','rowNum'));">获得每页显示记录数</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','datatype'));">获得数据格式</button>
	<button onclick="alert(jQuery('#grid').jqGrid('getGridParam','records'));">获得总记录数</button>

	<button onclick="$('#grid').jqGrid('setGridParam',{url:'${pageContext.request.contextPath}/demoUserController/find.do'}).trigger('reloadGrid');">重新设置url并加载数据</button>
	<button onclick="$('#grid').jqGrid('setGridParam',{sortname:'name',sortorder:'asc'}).trigger('reloadGrid');">动态设置字段排序</button>
	<button onclick="$('#grid').jqGrid('setGridParam',{page:2}).trigger('reloadGrid');">动态翻到第2页</button>
	<button onclick="$('#grid').jqGrid('setGridParam',{rowNum:15}).trigger('reloadGrid');">动态设置每页显示记录数</button>
	<button onclick="$('#grid').jqGrid('setCaption','新标题');">更改表格标题</button>
	<button onclick="$('#grid').jqGrid('sortGrid','name',false);">排序</button>

	<button onclick="$('#grid').jqGrid('setSelection','100');">选中id是100的行</button>

	<button onclick="$('#grid').jqGrid('hideCol','name');">隐藏name列</button>
	<button onclick="$('#grid').jqGrid('showCol','name');">显示name列</button>

	<button onclick="delRow();">删除选中行</button>

	<button onclick="$('#grid').jqGrid('resetSelection');">取消选中</button>

	<button onclick="changePostData();">更改postData</button>
	<button onclick="delPostData();">删除postData某个属性</button>
	<button onclick="getPostData();">获得postData</button>

</body>
</html>