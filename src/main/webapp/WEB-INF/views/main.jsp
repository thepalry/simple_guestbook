<%@ page import = "vo.GuestbookArticle" %>
<%@ page import = "java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	String email = "";
	if(request.getParameter("email") != null)
	{
		email = request.getParameter("email");
	}

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방명록</title>
</head>

<body>
	<h1>방명록</h1>
	
	<form action="list" method="post">
		이메일 : <input type="text" name="email" size="20" value="<%=email%>"><br>
		비밀번호 : <input type="password" name="pwd" size="20"><br>
		내용 : <input type="text" name="article" size="100"><br>
		<input type="submit" value="등록">
	</form>
	
	<h2>목록</h2>
	
	<%
	ArrayList<GuestbookArticle> articles = (ArrayList<GuestbookArticle>)request.getAttribute("articles");
	for(GuestbookArticle article : articles) {
	%>
	등록번호 : <%=article.getGno()%>
	이메일 : <%=article.getEmail()%>
	등록 시간 : <%=article.getCreatedTime()%>
	수정 시간 : <%=article.getModifiedTime()%>
	<p>내용 : </p><%=article.getArticle()%>
	<br><br>
	<% } %>
	
</body>

</html>