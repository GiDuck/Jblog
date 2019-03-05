<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="common.jsp" />
<ul class="admin-menu">

	<li id="li-basic"><a
		href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
	<li id="li-category"><a
		href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
	<li id="li-write"><a
		href="${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/write">글작성</a></li>

</ul>

<script>
	$(document).ready(function() {

		try {
			let $hidden = $("input[name='pageType']");
			let type = $hidden.val();
			let id = "#li-" + type;
			$(id).empty();
			$(id).html($hidden.attr("ref"));
			$(id).addClass("selected");
		} catch (e) {
			console.log(e);
		}

	});
</script>