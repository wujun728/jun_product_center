<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/5
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/vendor/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
<template id="base-layout">
    <div id="template-wrapper">
        <%@include file="_navigation.jsp"%>
        <div id="page-wrapper" class="gray-bg">
            <%@include file="_topNavBar.jsp"%>
            <%-- page-header solt --%>
            <header><slot name="page-header"></slot></header>
            <main id="content">
                <!--default solt-->
                <slot></slot>
            </main>
            <%@include file="_footer.jsp"%>
        </div>
    </div>
</template>
<%--add ibox components--%>
<%@include file="page-ibok.jsp"%>
<!-- Mainly scripts -->
<script src="${pageContext.request.contextPath}/vendor/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath}/vendor/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/js/plugins/pace/pace.min.js"></script>
<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath}/vendor/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/vendor/js/base-config.js"></script>

<script>
    Vue.component("base-layout", {
        template: "#base-layout",
        components: {
        },
        props: {
            layoutData: {
                type: Object,
                default:function () {
                    return {iboxData:null}
                }
            }
        }
    });
</script>
</body>
</html>


