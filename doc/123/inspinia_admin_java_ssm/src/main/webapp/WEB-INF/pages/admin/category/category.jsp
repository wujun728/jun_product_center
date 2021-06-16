<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/8
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>分类管理页</title>
    <link href="${pageContext.request.contextPath}/vendor/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/vendor/css/plugins/dataTables/datatables.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/vendor/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/vendor/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/vendor/js/jquery-3.1.1.min.js"></script>
</head>
<body>
    <div id="wrapper">
        <%@include file="../shared/_navigation.jsp"%>
        <div id="page-wrapper" class="gray-bg">
            <%@include file="../shared/_topNavBar.jsp"%>
            <renderBody>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Hover Table  </h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="#">Config option 1</a>
                                </li>
                                <li><a href="#">Config option 2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Opration</th>
                                <th>Image</th>
                                <th>CategoryID</th>
                                <th>CategoryName</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cs}" var="item" varStatus="vs">
                                    <tr>
                                        <td>
                                            <a href="javascript:;" onclick="del('${item.id}')"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                            <a href="javascript:;" onclick="edit('${item.id}')"><i class="fa fa-pencil-square-o"></i></a>
                                        </td>
                                        <td><span class="pie"><img src="/admin/_upload/img/category/${item.id}.jpg" class="img-rounded img-md"></span></td>
                                        <td>${item.id}</td>
                                        <td class="text-navy"><a href="/admin/product/list?cid=${item.id}"><i class="fa fa-level-up"></i> ${item.name}</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%@include file="../shared/_pageBar.jsp"%>
                <div id="content_Add">
                    <%@include file="add.jsp"%>
                </div>
                <div id="content_Edit">
                    <%@include file="edit.jsp"%>
                </div>
            </renderBody>
            <%@include file="../shared/_footer.jsp"%>
        </div>
    </div>


    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath}/vendor/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${pageContext.request.contextPath}/vendor/js/inspinia.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/js/plugins/pace/pace.min.js"></script>

    <!-- Page-Level Scripts -->
    <renderScript>
        <script src="${pageContext.request.contextPath}/src/category.js"></script>
    </renderScript>
</body>
</html>
