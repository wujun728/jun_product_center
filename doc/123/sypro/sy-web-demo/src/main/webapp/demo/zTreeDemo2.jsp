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
			$('#menuContent').css({
				left : $(this).offset().left + 'px',
				top : $(this).offset().top + $(this).outerHeight() + 'px'
			}).toggle();
		});

		$.fn.zTree.init($('#tree'), {
			view : {
				dblClickExpand : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'pid'
				}
			},
			async : {
				enable : true,
				url : '${pageContext.request.contextPath}/demoResourceController/findTree.do'
			},
			callback : {
				//beforeClick : beforeClick,
				onClick : function(event, treeId, treeNode) {
					$('#resourceName').val(treeNode.name);
					$('#resourceId').val(treeNode.id);
					$('#menuContent').toggle();
				}
			}
		});
	});
</script>

</head>
<body>

	资源名称
	<input id="resourceName" type="text" readonly value="" /> ID
	<input id="resourceId" type="text" readonly value="" />
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="tree" class="ztree" style="margin-top: 0;"></ul>
	</div>

</body>
</html>