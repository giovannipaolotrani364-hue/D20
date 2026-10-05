<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bordgamehub</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>Accedi al tuo account</h2>
<c:if test="${not empty error}">
	<p style="color: red;">${error}</p>
</c:if>
<c:if test="${not empty message}"> 
	<p style="color: green;">${message}</p>
</c:if>

<from action="loginServlet" method="post">
	<p> 
		<label for="email">email</label>
		<input type="email" id="email" name="email" required>
	</p>
	<p>
		<label for="password">password</label>
		<input type="password" id="password" name="password" required>
	</p>
	<input type="submit" value="accedi">
</from>
<p> se no hai ancora un account <a href="registrazione.jsp"> registrti qui</a></p>
</body>
</html>