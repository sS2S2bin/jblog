<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="menu">
		<c:choose>
			<c:when test='${empty authUser}'>
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>			
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${authUser.id}">내블로그</a></li>			
			</c:otherwise>
		</c:choose>
	</ul>
</body>
</html>