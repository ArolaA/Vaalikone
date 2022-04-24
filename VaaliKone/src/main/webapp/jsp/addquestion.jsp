<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Question" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kysymyksen lisäys vaalikoneeseen</title>

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
				<h2>UUSI KYSYMYS</h2>	
			</div>
		</div>					
		<form action='/AddQuestionRest' id="lisayslomake" method="POST">			
			<label for="question">Kysymys:</label><br>
			<textarea class="addquestiontext" id="question" name="question" form="lisayslomake" required></textarea><br>	  				
			<input type="submit" class="button"  value="Tallenna">
		</form><br>		
		<div class="addquestion_navigation">
			<a href='/ListAllQuestions' class="btn-grad" style="margin-left: 0px;">Takaisin</a>	
		</div>
	</div>
</div>
</body>
</html>