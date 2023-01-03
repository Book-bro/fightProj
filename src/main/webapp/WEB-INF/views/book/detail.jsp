<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 
BookController로 부터 넘어오는 정보 2가지
model : data 데이터를 jsp로 넘겨줌
mav.addObject("data",data); => bookVO 데이터
mav.addObject("bookId",bookVO.getBookId()); => 기본키 데이터(int형)

 -->
<html>
<head>
<title>detail(책 상세)</title>
</head>
<body>
<!-- 
JSTL(JSP Starndard Tag Library) : 개발자가 자주 사용하는 패턴을 모아 놓은 집합
=> BookController에서 보내준 데이터를 뷰에 표현하도록 도와줌
JSTL은 maven에서 설정되어있음 => pom.xml(스프링계의 배철수) =>jstl

 -->
   <h1>책 상세</h1>
   <p> 제목 : ${data.title }</p>
   <p> 카테고리 :${data.category } </p>
   <p> 가격 : ${data.price }</p>
   <p> 입력일 :${data.insertDate } </p>
   <p> <a href="/update?bookId=${bookId}">수정폼</a></p>
   
</body>
</html>