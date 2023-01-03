<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>책 수정하기</title>
</head>
<body>
	<h1>책 수정</h1>
	<!-- 
		BookController에서 넘어온 데이터? mav.addObject("data",data);
	-->
	<form action="/update" method="post">
	<!-- 폼 데이터  -->
	<input type="text" name="bookId" value="${data.bookId}"/>
	<P>제목: <INPUT type="text" name="title" value="${data.title}" /></P>
	<P>카테고리: <INPUT type="text" name="category" value="${data.category}" /></P>
	<P>가격: <INPUT type="text" name="price" value="${data.price}" /></P>
	<p>
		<input type="submit" value="저장" />
		<input type="button" value="목록" />
	</p>
	</form>
</body>
</html>