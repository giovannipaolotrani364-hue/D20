<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>il tuo carello</title>
</head>
<body>
<h1> Carello dei giochi</h1>
<c:choose>
	<c:when test:"${empty sessionScope.carello || empty sessionScope.carello.items}">
	<p>il tuo carello e attualemte vuoto </p>
	<p> <a href="catalogoServlet"> torna al catalogo servlet</a></p>
	</c:when>
	
	<c:otherwise>
		<table border="1" cellpaddding="8" cellspacing="0">
			<thead>
				<tr>
					<th>Gioco</th>
					<th>Prezzo singolo</th>
					<th>Quantità</th>
					<th>Subtotale</th>
					<th>Azione</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var:"item" items="${sessionScope.carello.itemas}">
						<tr>
							<td>${item.prodotto.titolo}</td>
							<td>${item.prodotto.prezzo}</td>
							<td>${item.quantita}</td>
							<td>${item.getTotale}</td>
							<td> 
							<form action="carelloServelt" methob="post">
								<input type="hidden" name="idProdotto" value="${item.prodotto.idPrdotto}">
								<input type="hidden" name="azione" value="delete">
								<input type="submit" value="rimuovi">
							</form>
							</td>
							</tr>
							</c:forEach>
				</tbody>
		</table>
		<h3> Totoale complesssivo: ${sessioneScope.carello.getTotale}</h3>
		
		<h2> Completa l'ordine</h2>
		<form action="checkoutServlet" method="post">
			<p> 
				<label for="indirizzo"> indirizzo di spedizione:</label>
				<input type="text" id="indirizzo" name="indirizzo" required style="width: 350px;" placeholder="via, civico, citta , cap">
				</p>
				<input type="submit" value="conferma acquisto ${sessionScope.carello.getTotale}">
		</form>
		<br><p><a href="catalogoServlet"> continua ad acquistare</a></p>
		</c:otherwise>
		</c:choose>
</body>
</html>
