<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방명록</title>
</head>

<body>

	<jsp:include page="Header.jsp"/>

	<h1>방명록</h1>
	
	<form action="list" method="post">
		이메일 : <input type="text" name="email" size="20"><br>
		비밀번호 : <input type="password" name="pwd" size="20"><br>
		내용 : <input type="text" name="article" size="100"><br>
		<input type="submit" value="등록">
	</form>
	
	<h2>목록</h2>
	<a href="list.do?orderCond=CREATED_TIME">작성 시간 순서</a>
	<a href="list.do?orderCond=MODIFIED_TIME">수정 시간 순서</a>
	<br><br>
	<c:forEach var="article" items="${articles}">
	등록번호 : ${article.gno}
	이메일 : ${article.email}
	등록 시간 : ${article.createdTime}
	수정 시간 : ${article.modifiedTime}
	<p>내용 : </p>${article.article}
	<br><br>
	</c:forEach>
	
	<jsp:include page="Tail.jsp"/>
	
</body>

</html>