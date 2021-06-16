<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>
<style type="text/css">
#_table th, td {
	text-align: left;
}
</style>
</head>
<body>

	<h1>整个项目，你只需要编写model，其余的类，都可以由这个代码生成工具生成。生成后，就拥有了model的增删改查、动态查询、级联查询、分页、排序、统计等基本功能</h1>
	<h1>你也可以直接执行sy.util.base包里面的GeneratorUtil类的main方法</h1>
	<h1>如果你要生成某个model对应的dao、service、controller，请直接运行sy.util.base包里面的GeneratorUtil类的main方法吧，这里就不演示了。</h1>
	<h2>generatorUtil.generator(new String[] { "DemoA" });工具可以调用这个方法，生成某个，或某几个model对应的文件</h2>

	<form action="${pageContext.request.contextPath}/generatorController/generator.do" target="_blank" method="post">
		<table style="width: 100%" border="1" id="_table">
			<tr>
				<th>将生成的文件写入哪个目录</th>
				<td><input name="generatorPath" value="D:/generator" style="width: 450px;" /></td>
				<th>最终会将dao、daoImpl、service、serviceImpl、controller生成在这个包的子包下面</th>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th>模型文件路径</th>
				<td><input name="modelPath" value="C:/Users/SunYu/git/sypro/sy-web-demo/src/main/java/sy/model/demo" style="width: 450px;" /></td>
				<th>就是你放model文件的绝对路径</th>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th>要生成dao接口的包名</th>
				<td><input name="daoPackageName" value="sy.dao.demo" style="width: 450px;" /></td>
				<th>如果dao的包不在sy.dao的子包下面，还要去修改spring.xml的component-scan扫描部分，将你自己的包配置到里面</th>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th>要生成service的包名</th>
				<td><input name="servicePackageName" value="sy.service.demo" style="width: 450px;" /></td>
				<th>如果service的包不在sy.service的子包下面，还要去修改spring.xml的component-scan扫描部分，将你自己的包配置到里面</th>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<th>要生成controller的包名</th>
				<td><input name="controllerPackageName" value="sy.controller.demo" style="width: 450px;" /></td>
				<th>如果controller的包不在sy.controller的子包下面，还要去修改spring-mvc.xml的component-scan扫描部分，将你自己的包配置到里面</th>
			</tr>
		</table>
		<button type="submit">生成代码，请看后台日志</button>
	</form>


</body>
</html>