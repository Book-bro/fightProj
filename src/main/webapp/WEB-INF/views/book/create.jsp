<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>책 등록하기</title>
</head>
<body>
<h1>책 등록</h1>
<!-- 폼 태그. post 방식으로 /create URL을 톰켓서버에 요청 
URL : http://localhost/create?title=개똥이모험&category=소설&price=10000
-->
<form action="/create" method="post">
<!-- 폼 데이터 -->
	<p>제목 : <input type="text" name="title" /></p>
	<p>카테고리 : <input type="text" name="category" /></p>
	<p>가격 : <input type="text" name="price" /></p>
	<p>
		<input type="submit" value="저장" />
		<input type="button" value="목록" />
	</p>
</form>
</body>
</html>