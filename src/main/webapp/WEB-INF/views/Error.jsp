<!-- Error View -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>에러 발생</title>
</head>

<body>
	<h1 style="text-align:center">
		<c:choose>
			<c:when test="${error != null}">
			${error}							<!-- 에러 메시지가 있는 경우 에러 메시지 출력 -->
			</c:when>
			<c:otherwise>
			문제가 발생했습니다.						<!-- 에러 메시지가 없는 경우 출력 -->
			</c:otherwise>
		</c:choose>
		<a href="list.do">메인 화면으로</a>			<!-- 메인 화면(리스트 출력 화면)으로 이동 링크 -->
	</h1>
</body>

</html>