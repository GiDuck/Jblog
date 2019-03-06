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
		<input type = "hidden" name="pageType" value="basic" ref="기본설정"/>
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<jsp:include page="../common/admin-detail.jsp"/>

				<form id="blog-form" onsubmit="return false;">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blog.title}"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img name="logo-img" src='${pageContext.request.contextPath}${blog.logo}' onerror="this.src='${pageContext.request.contextPath}/assets/images/spring-logo.jpg'"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
			      	
			      	
				</form>
			</div>
		</div>
		<jsp:include page="../common/blog-footer.jsp"/>

	</div>
</body>

<script>

$(document).ready(function(){
	
	$("input[name='logo-file']").change(function(e){
		
		let file = $(this).prop("files");
		let reader = new FileReader();
		reader.onload = function(e){
			$("img[name='logo-img']").attr('src', e.target.result);
		}
		reader.readAsDataURL(file[0]);
		
	});
	
	
	$("#blog-form").on("submit", function(e){
		
	
		e.preventDefault();
		let files = $("input[name='logo-file']").prop("files");
		let formData = new FormData();
		formData.append("title", $("input[name='title']").val());
		formData.append("user_no", "${blog.user_no}");
		formData.append("logo", "${blog.logo}");
		formData.append("logo-file", files[0]);

		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/updateInfo",
			type : "post",
			data : formData,
			processData : false,
			contentType : false,
			success : function(response){
				
				if(blogStatusCode.BLOG_UPDATE_SUCCESS == response){
					swal("수정 성공", "블로그 수정 성공하였습니다.", "success");
					return;
				}
				
				swal("수정 실패", "블로그 수정 실패하였습니다.", "error");

			},
			error : function(xhs){
				
				swal("수정 실패", "블로그 수정 중 오류가 발생하였습니다..", "error");


			}
			
			
		});
		
	});
	
	
});

</script>

</html>