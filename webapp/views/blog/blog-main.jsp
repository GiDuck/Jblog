<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<jsp:include page="../common/blog-header.jsp"/>

<body>
	<div id="container">
		<jsp:include page="../common/header-admin.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="blog-content" class="blog-content">
					<h4>${singlePost.title}</h4>
					<p>${singlePost.content}<p>
				</div>
				<ul id="blog-list" class="blog-list"></ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src='${pageContext.request.contextPath}${blog.logo}' onerror="this.src='${pageContext.request.contextPath}/assets/images/spring-logo.jpg'">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul id="blog-category"></ul>
		</div>
		
		<jsp:include page="../common/blog-footer.jsp"/>

	</div>
</body>

<script>

	$(document).ready(function(){
		let posts = ${posts};
		let categories = ${categories};
		let $blogList = $("#blog-list");
		let $categoryList = $("#blog-category");	
		
		
		
		$.each(posts, function(index, item){
			
			let url = "${pageContext.request.contextPath}/${userId}/" + item.c_no + "/" + item.no;
			let date = new Date(parseInt(item.reg_Date));
		
			let $li = $("<li>")
			.append($("<a>").attr("href", url).html(item.title))
			.append($("<span>").html(date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate()));
			
			$blogList.append($li);
			
		});
		
		
		$.each(categories, function(index, item){
			
			let url = "${pageContext.request.contextPath}/${userId}/" + item.no;
			
			let $li = $("<li>")
			.append($("<a>").attr("href", url).html(item.name));
			
			$categoryList.append($li);
			
			
		});
		
		
		
		
	});


</script>



</html>