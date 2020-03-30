<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="hu">
<head>
	<title>Mosony Ákos beadandó</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">

</head>


<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.html"> <img src="img/logo.png" width="60" height="60" class="d-inline-block align-top" alt=""></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	<span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
	<ul class="navbar-nav">
	  <li class="nav-item ">
		<a class="nav-link" href="index.html">Főoldal <span class="sr-only">(current)</span></a>
	  </li>
	  <li class="nav-item active">
		<a class="nav-link" href="UgyfelekControllerServlet">Ügyfelek</a>
	  </li>
	  <li class="nav-item">
		<a class="nav-link" href="#">Járművek</a>
	  </li>
	  <li class="nav-item">
		<a class="nav-link disabled" href="#">Kölcsönzés</a>
	  </li>
	</ul>
  </div>
</nav>


<div class="jumbotron jumbotron-fluid bg-dark text-white">
	<div class="container-fluid wrapper">
		<h1 class="display-4">Ügyfelek listázása!</h1>		
	</div>
</div>


<table class="table table-dark">
 <thead>
	<tr>
		<th scope="col">Igazolvány szám</th>
		<th scope="col">Vezetéknév</th>
		<th scope="col">Keresztnév</th>
		<th scope="col">Telefonszám</th>
		<th scope="col">Email</th>
		<th scope="col">Műveletek</th>
	</tr>
 </thead>
	
	
	<c:forEach var="tempUgyfelek" items="${UGYFELEK_LIST}">
	
	<c:url var="templink" value="UgyfelekControllerServlet">
		<c:param name="command" value="LOAD" />
		<c:param name="ugyfelekId" value="${tempUgyfelek.id}" />
	</c:url>
	
	
	
	<c:url var="deletelink" value="UgyfelekControllerServlet">
		<c:param name="command" value="DELETE" />
		<c:param name="ugyfelekId" value="${tempUgyfelek.id}" />
	</c:url>
	
	
	<tr>
		<td> ${tempUgyfelek.igazolvanySzam} </td>
		<td> ${tempUgyfelek.vezetekNev} </td>
		<td> ${tempUgyfelek.keresztNev} </td>
		<td> ${tempUgyfelek.telefonSzam} </td>
		<td> ${tempUgyfelek.email} </td>
		<td> 
			<a href="${templink}">Módosítás</a>
			|
			<a href="${deletelink}"
			onclick="if (!(confirm('Biztosan törlöd?'))) return false">Törlés</a>	
		</td>
	</tr>
	</c:forEach>

</table>

<input type:"button" value="Ügyfél hozzáadása"
		onclick="window.location.href='ugyfel-hozzaadasa.jsp'; return false;"
		class="btn btn-warning"
/>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>