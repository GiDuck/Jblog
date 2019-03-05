<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<jsp:include page="../common/blog-header.jsp"/>
<body>
	<div class="center-content">
	<jsp:include page="../common/header.jsp"/>
		<form id="login-form" class="login-form" onsubmit = "return false;">
      		<label>아이디</label> <input type="text" name="id">
      		<label>패스워드</label> <input type="password" name="password">
      		<input type="submit" value="로그인">
		</form>
	</div>
</body>

<script>


$(document).ready(function(){
	
	$("#login-form").on("submit", function(e){
		
		e.preventDefault();
	
		let id = $("input[name='id']").val();
		let password = $("input[name='password']").val();
		
		if(!id || checkStrIsBlank(id)){
			swal("로그인 실패", "id가 공백입니다.", "error");
			return;
		}
		else if(!password || checkStrIsBlank(password)){
			swal("로그인 실패", "비밀번호가 공백입니다.", "error");
			return;
		}
		
		let formData = new FormData();
		formData.append("id", id);
		formData.append("password", password);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/member/loginAction",
			type : "post",
			data : formData,
			processData : false,
			contentType : false,
			success : function(response){
				if(memberStatusCode.AUTH_SUCCESS == response){
				
					window.location.replace("${pageContext.request.contextPath}/");
					
				}else if (memberStatusCode.ID_NOT_VALID == response){
					swal("로그인 실패", "id가 존재하지 않습니다.", "error");
				}else if (memberStatusCode.PWD_NOT_VALID == response){
					swal("로그인 실패", "pw가 정확하지 않습니다.", "error");
				}else{
					swal("로그인 실패", "로그인에 실패하였습니다.", "error");
				}
				
			},
			error : function(xhs){
				
				swal("로그인 실패", "로그인 중 오류가 발생하였습니다.", "error");
				console.log(xhs.status);
				
			}
			
			
		});
		
	});
	
	
});


</script>
</html>
