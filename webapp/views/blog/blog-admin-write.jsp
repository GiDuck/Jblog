<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<jsp:include page="../common/blog-header.jsp"/>

<body>
		<input type = "hidden" name="pageType" value="write" ref="글쓰기"/>
		<jsp:include page="../common/header-admin.jsp"/>
	<div id="container">

		<div id="wrapper">
			<jsp:include page="../common/admin-detail.jsp"/>

				<form id="writeForm" onsubmit="return false;">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" id="title">
				      			<select id ="categoryOps" name="category">
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea id="content"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input id="submitBtn" type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
	
		<jsp:include page="../common/blog-footer.jsp"/>
</body>

<script>

$(document).ready(function(){
	let categories = ${categories};
	let $category = $("#categoryOps");
	$.each(categories, function(index, item){
		let $ops = $("<option>").html(item.name).val(item.no);
		$category.append($ops);
	});
	
	
	$("#writeForm").on("submit", function(){
		
		let title = $("#title").val();
		let content = $("#content").val();
		let categoryNo = $("#categoryOps option:selected").val();

		
		if(checkStrIsBlank(title) || checkStrIsBlank(content)){
		
			swal("등록 실패", "제목 혹은 본문이 공백입니다.", "error");
			return;
		}
		
		
			$.ajax({
				
				url : "${pageContext.request.contextPath}/${userId}/admin/writeAction",
				type : "post",
				data : {
					title : title, 
					content : content,
					category_no : categoryNo
				},
				success : function(response){
					console.log(blogStatusCode);
					console.log(response);
					if(response == blogStatusCode.POST_SUCCESS){
						swal("등록 성공", "포스트 등록에 성공하였습니다.", "success");
						setTimeout(function(){
							
							window.location.replace("${pageContext.request.contextPath}/${userId}");
							
							
						}, 3000);
					}
				},
				error : function(xhs){
					
					
				}
				
			});
			
		
		
		
		
	});
	
	
	
	
	
	
	
	
});


</script>
</html>