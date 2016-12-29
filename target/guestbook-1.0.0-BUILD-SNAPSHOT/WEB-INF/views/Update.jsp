<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
	<script>
	
	function checkInput() {
		
		  if($("#pwd").val() == "") {
			   alert("비밀번호를 입력하십시오.");
			   $("#pwd").focus();
			   return false;
			}
		  if($("#article").val() == "") {
			   alert("내용을 입력하십시오.");
			   $("#article").focus();
			   return false;
			}

		  var form = $("#insertform");
		  form.submit();
	}
	
	</script>
	<title>방명록 수정</title>
</head>

<body>

	<jsp:include page="Header.jsp"/>

	<h1>방명록  수정</h1>

	<form action="update" method="post" id="insertform">
		등록번호 : ${param.gno}<input type="hidden" name="gno" id="gno" value="${param.gno}">
		이메일 : ${param.email}<input type="hidden" name="email" id="email" value="${param.email}">
		비밀번호 : <input type="password" name="pwd" size="20"id="pwd" value=""><br>
		내용 : <input type="text" name="article" size="100" id="article" value="${param.article}"><br>
		<input type="button" value="수정" onclick="javascript:checkInput();">
		<a href='/list.do'><button>취소</button></a>
	</form>
	
	<jsp:include page="Tail.jsp"/>

</body>

</html>