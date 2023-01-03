<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- model.addAttribute("serverTime", formattedDate ) -->
<P>  The time on the server is ${serverTime}. </P>
<!-- model.addAttribute("name", "개똥이" ); -->
<p>${name}</p>
</body>
</html>
