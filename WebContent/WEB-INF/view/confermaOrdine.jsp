<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ordine confermato</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div style="text-align: center; margin-top: 50px;">
	<h1 style="color: green;"> ordine competato con sucesso</h1>
	<p>grazie per aver acquistato</p>
	<div style="border: 1px solid #ccc; display: inline-block; padding: 20px; border-radius: 8px;">
		<h3>Riepilogo Ordine:</h3>
		<p> indirizzo di spedizione: ${indirizzo}</p>
		<p> totale pagato: ${totalepagato}</p>
	</div>
</div>
<p><a href="catalogoServlet">Torna al catalogo</a></p>
</body>
</html>