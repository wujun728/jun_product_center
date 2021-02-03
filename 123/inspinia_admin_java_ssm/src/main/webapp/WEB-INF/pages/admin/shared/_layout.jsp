<%--到百度首页
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/7
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>INSPINIA | @ViewBag.Title</title>

    <link href="${pageContext.request.contextPath}/vendor/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/css/style.css" rel="stylesheet">
    <%--vue js--%>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

</head>
<body>
    <div id="wrapper">
        <%@include file="_navigation.jsp"%>
        <div id="page-wrapper" class="gray-bg">
            <%@include file="_topNavBar.jsp"%>
            <render-body></render-body>
            <%@include file="_footer.jsp"%>
        </div>
    </div>
    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/vendor/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath}/vendor/js/inspinia.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/pace/pace.min.js"></script>

    <render-script>
    </render-script>

    <script>
        new Vue({
            el:"#wrapper",
        })
    </script>
</body>
</html>
