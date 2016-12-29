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
		
		  if($("#email").val() == "") {
			   alert("이메일을 입력하십시오.");
			   $("#email").focus();
			   return false;
			} 
		  else {
				var emailExp = /([\w-\.]+)@([a-z\d\.-]+)\.([a-z\.]{2,6})$/;
				if(!emailExp.test($('#email').val())){
					alert("이메일주소가 유효하지 않습니다.");
				    $('#email').focus();
				    return false;
				}
		  }
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
	<title>방명록</title>
</head>

<body>

	<jsp:include page="Header.jsp"/>

	<h1>방명록</h1>
	
	<c:choose>
		<c:when test="${param.orderCond != null}">
		<form action="list.do?orderCond=${param.orderCond}" method="post" id="insertform">
    </c:when>
    <c:otherwise>
		<form action="list.do" method="post" id="insertform">
    </c:otherwise>
	</c:choose>
		이메일 : <input type="text" name="email" id="email" value="" size="20"><br>
		비밀번호 : <input type="password" name="pwd" id="pwd" value="" size="20"><br>
		내용 : <input type="text" name="article" id="article" value="" size="100"><br>
		<input type="button" value="등록" onclick="javascript:checkInput();">
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
	<form action="update" method="post">
		<input type="hidden" name="gno" value="${article.gno}">
		<input type="hidden" name="email" value="${article.email}">
		<input type="hidden" name="article" value="${article.article}">
		<input type="submit" value="수정">
	</form>
	<br><br>
	</c:forEach>
	
	<jsp:include page="Tail.jsp"/>
	
</body>

</html>