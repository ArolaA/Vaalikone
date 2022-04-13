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

<%  
if (session.getAttribute("user") != null) 
	{
	
	} 
else 
	{
	  response.sendRedirect("/jsp/login.jsp");            
	}
%>
    
<div class="background">
	<div class="frame">
		<div class="heading">			
			<div id="heading">
				<h2>ehdokaslista</h2>
			</div>			
		</div>
		<div class="candidatelist">
			<div class="showquestion_navigation">
				<a href='jsp/admin.jsp' class="btn-grad">Takaisin</a>
				<a href='jsp/addcandidate.jsp' class="btn-grad"><img src="/img/add_user_logo.png" alt="logout logo">Lisää ehdokas</a>	
			</div>
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
						<td><a class='button' href='/confirmDelete?id=${candidate.id}'>poista<img src="/img/delete_logo.png" alt="delete logo"></a></td>
						<td><a class='button2' href='/readtoupdate?id=${candidate.id}'>muokkaa<img src="/img/edit_logo.png" alt="edit logo"></a></td>
						<td><a class='button4' href='/showonecandidateanswers?id=${candidate.id}'>vastaukset<img src="/img/answer_logo.png" alt="edit logo"></a></td>				
					</tr>
				</c:forEach>	
			</table>
		</div>		
	</div>	
</div>
</body>
</html>