<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pagina registrazione</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>cre un nuovo accout</h2>
<c:if test="${ not empty error}">
	<p style="color: red;">${error}</p>
</c:if>
<form action="registrazioneServlet" method="post">
	<p>
		<label for="nome">nome</label>
		<input type="text" id="nome" name="nome" required>
	</p>
	<p>
		<label for="cognome">cognome</label>
		<input type="text" id="cognome" name="cognome" required>
	</p>
	<p>
		<label for="email">email</label>
		<input type="email" id="email" name="email" required>
	</p>
	<p>
		<label for="password">password</label>
		<input type="password" id="password" name="password" required>
	</p>
	<input type="submit" value="registrati">
</form>
<p> hai già un accuount? <a href="login.jpg"> accedi da qui</a></p>
</body>
</html>