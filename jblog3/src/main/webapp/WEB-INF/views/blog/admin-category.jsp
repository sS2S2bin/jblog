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
	$("#submit-cateogry").click(function() {
		var name = $("#name").val();
		var description = $("#description").val();
		if(name == '' || description=='') {
			alert('카테고리 제목과 설명을 채워주세요.');
			event.preventDefault(); // 폼 제출 방지
		}
	})
});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1> 
			<c:import url="/WEB-INF/views/includes/menu.jsp"/>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
						<c:set var="count" value="${fn:length(categoryList) }" />
						<c:forEach items="${categoryList}" var="categoryVo" varStatus="status">
					<tr>
						<td>${count-status.index }</td>
						<td>${categoryVo.name }</td>
						<td>${categoryVo.postCount }</td>
						<td>${categoryVo.description }</td>
						<td>
						<c:if test="${categoryVo.postCount>0 }">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						</c:if>
						</td>
					</tr>  
						</c:forEach>
				</table>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<form action="${pageContext.request.contextPath }/${id }/admin/category" method="post">
		      	<input type="hidden" name="blogId" value="${id }">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="description" type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="submit-cateogry" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
	      		</form> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>