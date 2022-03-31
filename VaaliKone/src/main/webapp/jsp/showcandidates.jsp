<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikone kaikki ehdokkaat</title>

<link rel="stylesheet" type="text/css" href="mycss.css">
<script src="myscriptfile.js"></script>

</head>
<body>
<div class="background">
	<div class="frame">
		<div class="heading">
			<div class="column">
			</div>
			<div class="column" id="heading">
				<h1>EHDOKASLISTA</h1>
			</div>			
			<div class="column">
			</div>
		</div>
		<table>		
			<tr class ="list_headers">
			    <th class="id_header">Numero</th>
			    <th class="name_header">Sukunimi</th>
			    <th class="name_header">Etunimi</th>
			    <th>Puolue</th>
		  	</tr>
			<c:forEach var="candidate" items="${requestScope.candidatelist}">
				<tr>
					<td class="id_column">${candidate.id} </td>
					<td>${candidate.surname} </td>
					<td>${candidate.firstname} </td>
					<td>${candidate.party} </td> 
					<td><a class='button'  href='/delete?id=${candidate.id}' onclick="return confirm('Ehdokas nro ${candidate.id} poistetaan. Oletko varma?')">poista</a></td>
					<td><a class='button2' href='/readtoupdate?id=${candidate.id}'>päivitä</a></td>				
				</tr>
			</c:forEach>
			<tr>
					<td><a href='../index.html' class="btn-grad">Takaisin</a></td>								
				</tr>		
		</table>
		
	</div>
</div>

</body>
</html>