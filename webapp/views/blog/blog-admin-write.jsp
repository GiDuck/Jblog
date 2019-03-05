<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<jsp:include page="../common/blog-header.jsp"/>

<body>
		<input type = "hidden" name="pageType" value="write" ref="글쓰기"/>

	<div id="container">
				<jsp:include page="../common/header-admin.jsp"/>

		<div id="wrapper">
			<jsp:include page="../common/admin-detail.jsp"/>

				<form action="" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="category">
				      				<option>미분류</option>
				      				<option>자바</option>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<jsp:include page="../common/blog-footer.jsp"/>

	</div>
</body>
</html>