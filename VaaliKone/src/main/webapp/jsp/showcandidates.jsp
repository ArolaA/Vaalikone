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

</head>
<body>
<div class="background">
	<div class="frame">
		<div class="heading">			
			<div id="heading">
				<h2>ehdokaslista</h2>
			</div>			
		</div>
		<div class="candidatelist">
			<table>			
				<tr>							
				    <th class="id_column">ID</th>
				    <th class="name_column">Sukunimi</th>
				    <th class="name_column">Etunimi</th>
				    <th class="name_column">Puolue</th>				    
			  	</tr>			  
				<c:forEach var="candidate" items="${requestScope.candidatelist}">
					<tr>
						<td class="id_column">${candidate.id} </td>
						<td>${candidate.surname} </td>
						<td>${candidate.firstname} </td>
						<td>${candidate.party} </td> 
						<td><a class='button' href='/confirmDelete?id=${candidate.id}'>poista</a></td>
						<td><a class='button2' href='/readtoupdate?id=${candidate.id}'>päivitä</a></td>				
					</tr>
				</c:forEach>
				<tr>
					<td><a href='../index.html' class="btn-grad">Takaisin</a></td>
				</tr>		
			</table>
		</div>		
	</div>	
</div>
</body>
</html>