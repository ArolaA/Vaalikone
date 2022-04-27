<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikone kaikki kysymykset</title>

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
	<div class="frame" style="width: 65%;">
		<div class="heading">			
			<div id="heading">
				<h2>kysymyslista</h2>
			</div>			
		</div>
		<div class="candidatelist">
			<div class="addquestion_navigation">
				<a href='jsp/admin.jsp' class="btn-grad">Takaisin</a>
				<a href='jsp/addquestion.jsp' class="btn-grad"><img src="/img/add_question_logo.png" alt="logout logo">Lisää kysymys</a>	
			</div>
			<table>								
				<tr>							
				    <th class="id_column" style="width: 50px;">ID</th>
				    <th class="name_column" style="text-align: center;">Kysymys</th>				    				    
			  	</tr>			  
				<c:forEach var="question" items="${requestScope.questionlist}">
					<tr>
						<td class="id_column">${question.id} </td>
						<td>
							<div class="questiontext">
								${question.question}
							</div>
						</td>						
						<td><a class='button' onclick="return confirm('Kysymys ${question.id} poistetaan. Oletko varma?')" href='/DeleteQuestionRest?id=${question.id}'>poista<img src="/img/delete_logo.png" alt="delete logo"></a></td>
						<td><a class='button2' href='/ReadQuestionToUpdate?id=${question.id}'>muokkaa<img src="/img/edit_logo.png" alt="edit logo"></a></td>				
					</tr>
				</c:forEach>					
			</table>
		</div>		
	</div>	
</div>
</body>
</html>