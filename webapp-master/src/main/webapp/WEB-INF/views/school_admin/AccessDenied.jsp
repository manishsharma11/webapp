<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Access Denied</title>
</head>
<body>
		<h2 align="center">
			Access denied. &nbsp;&nbsp;&nbsp;&nbsp;
			<br><br>
			<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>	
		</h2>
		
</body>
</html>