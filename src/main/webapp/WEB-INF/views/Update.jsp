<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방명록 수정</title>
</head>

<body>

	<jsp:include page="Header.jsp"/>

	<h1>방명록  수정</h1>
	
	<form action="update" method="post">
		등록번호 : ${article.gno}
		이메일 : ${article.email}
		비밀번호 : <input type="text" name="pwd" size="20"><br>
		내용 : <input type="text" name="article" size="100"><br>
		<input type="submit" value="수정">
		<a href='list'><button>취소</button></a>
	</form>
	
	<jsp:include page="Tail.jsp"/>

</body>

</html>