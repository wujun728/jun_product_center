<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/7/16
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../shared/base-layout.jsp"%>
<div id="_wrapper">
    <base-layout>
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>用户管理</h5>
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
                        <th>Id</th>
                        <th>Name</th>
                        <th>Portrait</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ul}" var="item" varStatus="vs">
                        <tr>
                            <td><a href="/admin/user/delete?id=${item.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.portrait}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="add.jsp"%>
    </base-layout>
</div>

<script type="text/javascript">
    new Vue({
        el:"#_wrapper",
    })
</script>