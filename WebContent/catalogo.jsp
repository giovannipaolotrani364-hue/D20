<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo Giochi da tavolo</title>
</head>
<body>
<div class="user-header" style="background-color: #f4f4f4; padding: 10px; margin-bottom: 20px; text-align: right;">
	<c:choose>
		<c:when test="${not empty sessionScope.utente}">
		<span> benvenuto, $sessionScope.utente.nome }</span>
		<a href="loginServlet?=logout">logout</a>
		</c:when>
	</c:choose>
	<c:otherwise>
		<a href="login.jsp"> accedi</a>
		<a href="registrazione.jsp"> registrati</a>
	</c:otherwise>
	<a href="carello.jsp"> carello (${not empty sessionScope.carello ? sessioneScope.carello.items.size() :0}</a>
</div>

<h1>Catalogo giochi da tavolo</h1>
	<div class="product-grid">
	<c:forEach var="prod" items="${prodotti}">
		<div class="product-card" style="border:1px solid #ccc; padding: 15px; margin: 10px; display:inLine-block; width: 250px">
		<img src="${prod.imagine}" alt="${prod.titlo}" style="max-width: 100%; height: auto;" onerror="this.src='images/nulla.png'" />
		
		<h2>${prod.titolo}</h2>
		<p>${prod.descrizione}</p>
		<p>Prezzo: ${prod.prezzo}</p>
		<p>Giocatori: ${prod.numeroGiocatoriMin}-${prod.numeroGiocatoriMax} 
		| Eta:${prod.etaMinima}+ | Durata: ${prod.durataMinuti} min</p>
		
		<form action="carelloServlet" method="post">
			<input type="hidden" name="idProdotto" value="${prod.idProdotto}">
			<input type="hidden" name="action" value="add">
			<input type="submit" name="aggiungi al carello">
		</form>
		</div>
		</c:forEach>
		</div>
</body>
</html>