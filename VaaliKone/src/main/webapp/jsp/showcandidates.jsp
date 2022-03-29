<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fish application</title>

<link rel="stylesheet" type="text/css" href="mycss.css">
<script src="myscriptfile.js"></script>

</head>
<body>
<h2>Ehdokkaat</h2>
	
	
	<table>
		<tr>
		    <th>ID</th>
		    <th>Sukunimi</th>
		    <th>Etunimi</th>
		    <th>Puolue</th>
	  	</tr>
		<c:forEach var="candidate" items="${requestScope.candidatelist}">
			<tr>
				<td>${candidate.id} </td>
				<td>${candidate.surname} </td>
				<td>${candidate.firstname} </td>
				<td>${candidate.party} </td> 
				<td><a class='button' href='/delete?id=${candidate.id}'>poista</a></td>
				<td><a class='button2' href='/readtoupdate?id=${candidate.id}'>päivitä</a></td>				
			</tr>
		</c:forEach>
		
	</table>


</body>
</html>