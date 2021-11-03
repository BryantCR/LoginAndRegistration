<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome</title>
	</head>
	<body>
		<h1>Welcome, <c:out value="${user.email}" /></h1>
		<a href="/logout">Logout</a>
	</body>
</html>
