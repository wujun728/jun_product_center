<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title>资源下拉树</title>

<script type="text/javascript">
	$(function() {
		$('#resourceName').on('click', function() {
			$("#menuContent").css({
				left : $(this).offset().left + "px",
				top : $(this).offset().top + $(this).outerHeight() + "px"
			}).slideDown("fast");
		});

		$.fn.zTree.init($('#tree'), {
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				//beforeClick : beforeClick,
				onClick : function(event, treeId, treeNode) {
					$('#resourceName').val(treeNode.name);
					$('#resourceId').val(treeNode.id);
					$("#menuContent").fadeOut("fast");
				}
			}
		}, [ {
			"id" : "1",
			"pId" : null,
			"name" : "资1源",
			"open" : null
		}, {
			"id" : "2",
			"pId" : "1",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "3",
			"pId" : "1",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "4",
			"pId" : "1",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "5",
			"pId" : "1",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "6",
			"pId" : "1",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "7",
			"pId" : null,
			"name" : "资2源",
			"open" : null
		}, {
			"id" : "8",
			"pId" : "7",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "9",
			"pId" : "7",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "10",
			"pId" : "7",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "11",
			"pId" : "7",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "12",
			"pId" : "7",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "13",
			"pId" : null,
			"name" : "资3源",
			"open" : null
		}, {
			"id" : "14",
			"pId" : "13",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "15",
			"pId" : "13",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "16",
			"pId" : "13",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "17",
			"pId" : "13",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "18",
			"pId" : "13",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "19",
			"pId" : null,
			"name" : "资4源",
			"open" : null
		}, {
			"id" : "20",
			"pId" : "19",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "21",
			"pId" : "19",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "22",
			"pId" : "19",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "23",
			"pId" : "19",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "24",
			"pId" : "19",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "25",
			"pId" : null,
			"name" : "资5源",
			"open" : null
		}, {
			"id" : "26",
			"pId" : "25",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "27",
			"pId" : "25",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "28",
			"pId" : "25",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "29",
			"pId" : "25",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "30",
			"pId" : "25",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "31",
			"pId" : null,
			"name" : "资6源",
			"open" : null
		}, {
			"id" : "32",
			"pId" : "31",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "33",
			"pId" : "31",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "34",
			"pId" : "31",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "35",
			"pId" : "31",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "36",
			"pId" : "31",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "37",
			"pId" : null,
			"name" : "资7源",
			"open" : null
		}, {
			"id" : "38",
			"pId" : "37",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "39",
			"pId" : "37",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "40",
			"pId" : "37",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "41",
			"pId" : "37",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "42",
			"pId" : "37",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "43",
			"pId" : null,
			"name" : "资8源",
			"open" : null
		}, {
			"id" : "44",
			"pId" : "43",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "45",
			"pId" : "43",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "46",
			"pId" : "43",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "47",
			"pId" : "43",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "48",
			"pId" : "43",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "49",
			"pId" : null,
			"name" : "资9源",
			"open" : null
		}, {
			"id" : "50",
			"pId" : "49",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "51",
			"pId" : "49",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "52",
			"pId" : "49",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "53",
			"pId" : "49",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "54",
			"pId" : "49",
			"name" : "子资源5",
			"open" : null
		}, {
			"id" : "55",
			"pId" : null,
			"name" : "资10源",
			"open" : null
		}, {
			"id" : "56",
			"pId" : "55",
			"name" : "子资源1",
			"open" : null
		}, {
			"id" : "57",
			"pId" : "55",
			"name" : "子资源2",
			"open" : null
		}, {
			"id" : "58",
			"pId" : "55",
			"name" : "子资源3",
			"open" : null
		}, {
			"id" : "59",
			"pId" : "55",
			"name" : "子资源4",
			"open" : null
		}, {
			"id" : "60",
			"pId" : "55",
			"name" : "子资源5",
			"open" : null
		} ]);
	});
</script>

</head>
<body>

	资源名称
	<input id="resourceName" type="text" readonly value="" /> ID
	<input id="resourceId" type="text" readonly value="" />
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="tree" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>

</body>
</html>