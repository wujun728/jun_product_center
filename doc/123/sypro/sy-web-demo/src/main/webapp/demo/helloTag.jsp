<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<title></title>
</head>
<body>

	<tags:hello user="孙宇" />

	<hr />
	<h1>JSP2.1新版的TagLib好像容易写了很多</h1>
	<div>只要写一些JSP片段之类的东西，不像以前写Java版的一个Taglib要学一天还不一定学会某些高深的用法。</div>
	<div>首先在WEB-INF/tags目录下放一个hello.tag文件，内容如下，先声明了编码，和传入的变量，剩下就是普通的JSP内容了</div>
	<br />
	<div>&lt;%@tag pageEncoding="UTF-8"%&gt;</div>
	<div>&lt;%@ attribute name="user" type="java.lang.String" required="true"%&gt;</div>
	<div>Hello, &lt;%==user%&gt;</div>
	<br />
	<div>然后在jsp中指向之前的目录，以tag文件名作为tag名称进行调用：</div>
	<br />
	<div>&lt;%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %&gt;</div>
	<div>&lt;tags:hello user="foo"/&gt;</div>
</body>
</html>