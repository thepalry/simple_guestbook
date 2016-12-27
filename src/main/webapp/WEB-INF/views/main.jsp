<%@ page import = "vo.GuestbookArticle" %>
<%@ page import = "java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>방명록</title>
</head>

<body>
	<h1>방명록</h1>
	<h2>목록</h2>
	
	<%
	ArrayList<GuestbookArticle> articles = (ArrayList<GuestbookArticle>)request.getAttribute("articles");
	for(GuestbookArticle article : articles) {
	%>
	<%=article.getGno()%>
	<%=article.getEmail()%>
	<%=article.getArticle()%>
	<%=article.getCreatedTime()%>
	<%=article.getModifiedTime()%>
	<% } %>
	
</body>

</html>