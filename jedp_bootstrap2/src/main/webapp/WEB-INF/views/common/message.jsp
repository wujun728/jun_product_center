<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 提示消息 -->
<c:if test="${not empty message}">
	<script type="text/javascript">
		/* $.dialog.tips("${message}",1,"success.gif"); */
		var d = dialog({
			width:300,
			content:'<div class="success-message"><i class="fa fa-exclamation-triangle"></i> ${message}</div>'
			});
		d.show();
		setTimeout(function () {
		    d.close().remove();
		}, 2000);
	</script>
</c:if>
<!-- 错误信息 -->
<c:if test="${not empty error}">
	<script type="text/javascript">
		/* $.dialog.alert("${error}",1,"error.gif"); */
		var d = dialog({
			width:300,
			content:'<div class="error-message"><i class="fa fa-exclamation-triangle"></i> ${error}</div>'
			});
		d.show();
		setTimeout(function () {
		    d.close().remove();
		}, 2000);
	</script>
</c:if>