<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function() {
	$("#btn-check").click(function() {
		var id = $("#blog-id").val();
		if(id == '') {
			alert('블로그 아이디는 비어있을 수 없습니다.');
			return;
		}
		
		$.ajax({
			url: "/jblog3/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, err){
				console.error(err);			
			},
			success: function(response) {
				if(response.result == "fail") {
					alert(response.message);
					$("#blog-id").val("");
					$("#blog-id").focus();
					return;
				}else{
					// 사용할 수 있는 이메일
					$("#btn-check").hide();
					$("#img-check").show();
				}
				
				if(response.data) {
					//alert("존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
					return
				}
				
				
			}
		});
	})
});
</script>

</head>
<body>
	<div class="center-content">
		<h1 class="logo"></h1> 
		<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" src="${pageContext.request.contextPath}/assets/images/check.png" style="vertical-align:bottom; width:24px; display: none">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/greencheck.png">

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
</html>
