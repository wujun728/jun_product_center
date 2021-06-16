<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/3/12
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <ul class="pagination">
        <li <c:if test="${page.hasPreviouse}">class="disabled" </c:if> >
            <a href="?statt=0" aria-label="Previouse">
                <span><<</span>
            </a>
        </li>
        <li>
            <a href="?start=${page.start-page.count}" aria-label="Previous">
                <span aria-hidden="true"><</span>
            </a>
        </li>
        <c:forEach begin="0" end="${page.totalPage -1}" varStatus="status">
            <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                <a href="?start=${status.index*page.count}" <c:if test="${status.index*page.count==page.start}">class="current"</c:if>>
                        ${status.count}
                </a>
            </li>
        </c:forEach>
        <li>
            <a href="?start=${page.start + page.count}" aria-label="Next">
                <span aria-hidden="true">></span>
            </a>
        </li>
        <li>
            <a href="?start=${page.last}" aria-label="Next">
                <span aria-hidden="true">>></span>
            </a>
        </li>
    </ul>
</nav>