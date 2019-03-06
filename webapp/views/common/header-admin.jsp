<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<jsp:include page="common.jsp"/>
	
	
<div id="header">

	<h1>${blog.title}</h1>
	<ul>
		<c:choose>
			<c:when test="${sessionScope.authUser eq null}">
				<li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
				
				<c:if test="${sessionScope.authUser.id eq userId}">
				<li><a
					href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">블로그
						관리</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>