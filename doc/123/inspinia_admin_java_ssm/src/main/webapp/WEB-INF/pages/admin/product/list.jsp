<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/6/14
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--master page--%>
<%@include file="../shared/base-layout.jsp"%>
<div id="_wrapper">
    <base-layout>
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>产品管理</h5>
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
                        <th>Name</th>
                        <th>Title</th>
                        <th>originalPrice</th>
                        <th>promotePrice</th>
                        <th>stock</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ps}" var="item" varStatus="vs">
                        <tr>
                            <td><a href="/admin/product/delete?id=${item.id}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                            <td>/</td>
                            <td>${item.name}</td>
                            <td>${item.subTitle}</td>
                            <td>${item.originalPrice}</td>
                            <td>${item.promotePrice}</td>
                            <td>${item.stock}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="add.jsp"%>
    </base-layout>
</div>
<script>
    var app = new Vue({
        el:"#_wrapper",
        data: {
            addCategorySelected: ${c.id},
            addCategoryOptions: []
        },
        methods: {
            getAllCategory:function (event) {
                $.get("/admin/category/ajaxList",function(data,status,xhr) {
                    if(status == "success" && data.code == 100){
                        app.addCategoryOptions = data.data;
                    }
                },"json");
            }
        }
    })
</script>



