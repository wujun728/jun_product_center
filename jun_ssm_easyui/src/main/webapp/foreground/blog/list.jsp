<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<div class="left-box">
                     <p class="left-box-title shadow"><i class="fa fa-cog fa-spin fa-fw" aria-hidden="true"></i>全部文章
                     </p>
                      <c:forEach var="blog" items="${blogList}">
				  <div class="article shadow clearfix">
                           <div class="article-left">
					  		<img src="res/images/cover/201708252044567037.jpg"
                                 		alt="${blog.title }" layer-index="0">
                           </div>
                           <div class="article-right">
                               <div class="article-title">
                                   <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title }</a>
                               </div>
                               <div class="article-abstract">
                                  		 摘要: ${blog.summary }...
                                  		 <%-- <span class="img">
								  		<c:forEach var="image" items="${blog.imagesList }">
									  		<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${image }</a>
									  		&nbsp;&nbsp;
								  		</c:forEach>
								  	</span> --%>
                               </div>
                               <div class="article-footer">
                                   <span class="layui-hide-xs"><i class="fa fa-tag" aria-hidden="true"></i>&nbsp;<a
                                           style="color:#009688" href="#">${blog.blogType.typeName} </a></span>
                                   <span><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;
                                   	<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy年MM月dd日"/></a>
                                   </span>
                                   <span class="article-viewinfo">${blog.clickHit}阅</span>
                                   <span class="article-viewinfo">${blog.replyHit}评</span>
                                   <span class="article-viewinfo">${blog.replyHit}赞</span>
                                   <a class="read layui-btn layui-btn-xs layui-btn-normal layui-hide-xs"
                                       href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html" title="阅读全文：${blog.title }">阅读全文</a>
                               </div>
                           </div>
                       </div>
			  </c:forEach>
          </div>
          <div class="blog-pagebox">
              <div class="laypage-main">
                  <!-- <span class="laypage-curr">1</span>
                  <a href="#">2 </a>  
                   <a href="#">3 </a> 
                  <a href="#" class="laypage-next">下一页</a>  -->
                   ${pageCode }
              </div>
          </div>