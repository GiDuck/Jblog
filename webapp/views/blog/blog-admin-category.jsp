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
		<input type = "hidden" name="pageType" value="category" ref="카테고리"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
			<jsp:include page="../common/admin-detail.jsp"/>

		      	<table id="ctg_tbl" class="admin-cat">
		      		<tr id="tbl_header">
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>			  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="add-category-btn" type="button" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<jsp:include page="../common/blog-footer.jsp"/>

	</div>
</body>

<script>

function Table($table){
	
	this.$table = $table,
	this.add = function(valueObj){
		
		let $newLine = $("<tr>").attr("no", valueObj.num)
		.append($("<td>").html(valueObj.num))
		.append($("<td>").html(valueObj.name))
		.append($("<td>").html(valueObj.postCount))
		.append($("<td>").html(valueObj.descritpion))
		.append($("<td>").append($("<img>")
				.attr("src", "${pageContext.request.contextPath}/assets/images/delete.jpg")
				.css("cursor", "pointer").on("click", function(e){
			let $parentTd = $(e.target).closest("tr");
			let rowNo = $parentTd.attr("no");
			removeRow(rowNo);
			//$parentTd.remove();
		})));
		
		$table.append($newLine);
	},
	this.empty = function(){
		
		$table.not("#tbl_header").empty();
	}

}

function removeRow(no){
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/removeCategory",
		data :  {categoryNo : no},
		type : "get",
		success : function(response){
			
			if(response == blogStatusCode.CATEGORY_DEL_SUCCESS){
				
				swal("삭제 성공", "카테고리 삭제에 성공하였습니다." , "success");
				getCategoryList(userNo);
				return;
				
			}else if(response == blogStatusCode.CATEGORY_HAS_POSTING){
				swal("삭제 실패", "카테고리는 관련 포스트가 없을 때만 가능합니다. 모든 관련 포스트를 삭제하시고, 다시 시도해 주십시오." , "error");
				return;
			}
				
			swal("삭제 실패", "카테고리 삭제에 실패하였습니다." , "error");
			
		},
		error : function(xhs){
			swal("삭제 실패", "카테고리 삭제 중 오류가 발생하였습니다." , "error");
			console.log(xhs.status);
		}
		
	})
	
}

function appendRows(list){
	
	tableObj.empty();
	for(let i=0; i<list.length; ++i){
		
		let nowElement = list[i];
		let valueObj = new Object();
		valueObj.num = nowElement.no;
		valueObj.name = nowElement.name;
		valueObj.descritpion = nowElement.description;
		valueObj.postCount = nowElement.postCount;
		tableObj.add(valueObj);
		
	}

	
	
}

function getCategoryList(userNo){
	
	if(!userNo) return;
	
	$.ajax({
		
		url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/getCategoryList",
		data : {userNo : userNo},
		type : "get",
		success : function(response){
			
			appendRows(response);
			
		},
		error : function(xhs){
			console.log(xhs.status);
		}
		
		
	})
	
	
}


let tableObj = new Table($("#ctg_tbl"));
let $nameField = $("input[name='name']");
let $descField = $("input[name='desc']");
let userNo = "${sessionScope.authUser.no}";
$(document).ready(function(){


	getCategoryList(userNo);
	
	$("#add-category-btn").on("click", function(e){
		
		let name = $nameField.val();
		let desc = $descField.val();
		
		if(!checkStrIsBlank(name) && !checkStrIsBlank(desc) && userNo){
			
			let formData = new FormData();
			formData.append("name", name);
			formData.append("description", desc);
			formData.append("user_no", userNo);
			
			$.ajax({
		
				url : "${pageContext.request.contextPath}/${sessionScope.authUser.id}/admin/regCategory",
				type : "post",
				data : formData,
				processData : false,
				contentType : false,
				success : function(response){
					
					if(response == blogStatusCode.CATEGORY_ADD_SUCCESS){
						swal("카테고리 등록 성공", "새로운 카테고리가 생성되었습니다.", "success");
						getCategoryList(userNo);
						$nameField.val("");
						$descField.val("");
						return;
					}
					
					swal("카테고리 등록 실패", "카테고리 삭제에 실패하였습니다.", "error");
					
					
				},
				error : function(xhs){
					console.log(xhs.status);
					swal("카테고리 등록 실패", "카테고리 삭제 중 오류가 발생하였습니다.", "error");

				}
				
				
				
			});
			
		}
		
	});
	
	
});


</script>


</html>