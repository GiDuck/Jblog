<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<div id="footer">
			<p>
				<strong>${blog.title}</strong> is powered by (c) <strong id="full-year"></strong>
			</p>
		</div>
		
		<script>
		
		window.onload = function(){
		let year = new Date().getFullYear();
		document.getElementById("full-year").innerHTML = year;
		}
		</script>