<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kysymyksen muokkaus</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">

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
	<div class="frame" style="width:30%; padding:20px 40px 40px 40px;">
		<div class="addcandidate">	
			<div class="addcandidate_heading">
				<h2>kysymyksen muokkaus</h2>
			</div>
		</div>		
		<form action='/UpdateQuestionRest' id="lisayslomake" method="POST">
				<label for="id">Kysymyksen numero:</label><br>
				<input class="editquestionid" type="number" id="id" name="id" readonly value="${requestScope.question.id}"><br>
				<label for="question">Kysymys:</label><br>
				<textarea class="editquestion" name="question" form="lisayslomake">${requestScope.question.question}</textarea>
				<br>						
				<input type="submit" class="button"  onclick="return confirm('Kysymys ${requestScope.question.id} päivitetään. Ovatko tiedot oikein?')" value="Tallenna">
		</form><br>
		
		<div class="addcandidate_navigation">
			<a href='/ListAllQuestions' class="btn-grad" style="margin-left: 0px;">Takaisin</a>	
		</div>
		
	</div>
</div>

			

</body>
</html>