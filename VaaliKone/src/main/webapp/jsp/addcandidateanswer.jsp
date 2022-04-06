<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.CandidateQuestion" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaan vastaukset</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">
<link rel="stylesheet" type="text/css" href="../questionform.css">

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
	<div class="frame" style="width:60%; padding:20px 40px 40px 40px;">		
		<h2 class="addcandidate_heading">Valitse mielestäsi sopivin vaihtoehto:</h2>		
			
		<form action='/AddCandidateAnswer' id="c_answerform" method="POST">		
			<c:forEach var="question" items="${requestScope.questionlist}">
				<div class="question">
						
						<p class="questionheader">${question.id}. ${question.question}</p>
										
						<div class="answerbuttons">		
							<div class="answerbuttonheader">			
								<p>1</p><p>2</p><p>3</p><p>4</p><p>5</p>
							</div>							
							<div class="radiobuttons">
								<input type="hidden" id="q${question.id}id" name="q${question.id}id" value="${question.id}">				
								<input type="radio" id="q${question.id}-1" name="q${question.id}answer" value="1">				
								<input type="radio" id="q${question.id}-2" name="q${question.id}answer" value="2">			
								<input type="radio" id="q${question.id}-3" name="q${question.id}answer" value="3" checked>
								<input type="radio" id="q${question.id}-4" name="q${question.id}answer" value="4">
								<input type="radio" id="q${question.id}-5" name="q${question.id}answer" value="5">
							</div>
							<div class="scale">			
								<p>1=täysin samaa mieltä,2=jokseenkin eri mieltä,3=ei samaa eikä eri mieltä,4=jokseenkin samaa mieltä,5=täysin eri mieltä</p>
							</div>
							<div class="commentarea">							
								<textarea class="comment" id="q${question.id}comment" name="q${question.id}comment" form="c_answerform" placeholder="kommmentti (vapaaehtoinen)"></textarea>			
							</div>
						</div>
				</div>					
			</c:forEach>
			<br><br>
			<p class="candidateidheading">Ehdokas</p>
			  <select id="candidateid" name="candidateid">
			  	<c:forEach var="candidate" items="${requestScope.candidatelist}">						
						<option value="${candidate.id}">${candidate.id}. ${candidate.surname} ${candidate.firstname}</option>						
				</c:forEach>			    			    
			  </select><br><br>					
			<input type="submit" class="button"  value="Tallenna vastauksesi">
		</form><br>	
		<a href='jsp/admin.jsp' class="btn-grad" style="width:85px;">Takaisin</a>			
	</div>
</div>

			

</body>
</html>