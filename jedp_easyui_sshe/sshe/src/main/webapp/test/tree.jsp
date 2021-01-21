<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%String contextPath = request.getContextPath();%>
<jsp:include page="../inc.jsp"></jsp:include>
<%-- 引入jquery扩展 --%>
<script src="<%=contextPath%>/test/tree.js" type="text/javascript" charset="utf-8"></script>

</head>

<body>
	<ul id="tree">
	</ul>
	<script>
	/*   $(function() {
		$('#tree').tree({
			checkbox : false,
			url : 'http://localhost:8080/sshe/test/tree_data1.json',
			onClick : function(node) {
				$(this).tree('toggle', node.target);
				var ss = node.attributes.cas.toString();// p1
				alert('you click ' + ss);
			},
			onContextMenu : function(e, node) {
				e.preventDefault();
				$('#tree').tree('select', node.target);
			}
		});
	}); 
		  */
	</script>

</body>

</html>