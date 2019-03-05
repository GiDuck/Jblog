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
		<form class="join-form" id="join-form" onsubmit="return false;">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>

<script>

$(document).ready(function(){
	
	let checkFlag = false;
	
	$("#btn-checkemail").on("click", function(){
		let checkEmail = $("#blog-id").val();
		if(!checkEmail || checkStrIsBlank(checkEmail)){
			swal("아이디 검증 실패", "공백은 입력이 불가능합니다.", "error");
			$("#blog-id").val("");
			return;
		}
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/member/checkId',
			type : 'get',
			data : {id : checkEmail},
			success : function(response){
				if(memberStatusCode.AUTH_FAIL == response){
					swal("아이디 검증 실패", "이미 같은 아이디가 존재합니다. 다시 입력 해 주세요.", "error");
				}else if(memberStatusCode.AUTH_SUCCESS == response){
					swal("아이디 검증 성공", "사용하실 수 있는 아이디 입니다.", "success");
					 $("#img-checkemail").css("display", "inline");
					 checkFlag = true;
				}
			},
			error : function(xhs){
				console.log(xhs.status);
			}
			
		});
		
		
	});
	
	
	$("#join-form").on("submit", function(e){
		e.preventDefault();
		let checked = $("#agree-prov").prop("checked");
		if(!checked){
			swal("가입 실패", "서비스 약관에 동의해 주십시오.", "error");
			return;
		}
		
		if(!checkFlag){
			swal("가입 실패", "아이디를 확인하여 주십시오.", "error");
			return;
		}
		
		let id = $("#blog-id").val();
		let password = $("#password").val();
		let name = $("#name").val();
		
		if(!id || checkStrIsBlank(id)){
			swal("가입 실패", "id가 공백입니다.", "error");
			return;
		}
		else if(!password || checkStrIsBlank(password)){
			swal("가입 실패", "비밀번호가 공백입니다.", "error");
			return;
		}
		else if(!name || checkStrIsBlank(name)){
			swal("가입 실패", "이름이 공백입니다.", "error");
			return;
		}
				
		let formData = new FormData();
		formData.append("id", id);
		formData.append("password", password);
		formData.append("name", name);
		formData.append("no", 0);

		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/member/joinAction',
			type : 'post',
			data : formData,
			processData : false,
			contentType: false,
			success : function(response){
				
				if(memberStatusCode.JOIN_SUCCESS == response){
					swal({
						title : "가입 성공",
						text : "가입이 성공적으로 끝났습니다.",
						type : "success",
						showConfirmButton : "false",
						timer : 2500
						
					}).then(function(){
						
						window.location.replace("${pageContext.request.contextPath}/member/joinSuccess");
						
					});
				}else{
					swal("가입 실패", "가입이 실패하였습니다.", "error");
				}
			},
			error : function(xhs){
				swal("가입 실패", "가입 중 오류가 발생하였습니다.", "error");

			}
			
		});
		
		
	});
	
	
	
});


</script>
</html>
