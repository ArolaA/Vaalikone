<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.CandidateQuestion" %> 
 <%@ page import="data.CandidateAnswer" %>  
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaan vastaukset</title>

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
		<c:set var="count" value="0" scope="page" ></c:set>
		<c:set var="questions" value="${requestScope.questionlist}" scope="page" ></c:set>
		<c:set var="answers" value="${requestScope.answerlist}" scope="page" ></c:set>
		<c:set var="candidate" value="${requestScope.candidate}" scope="page" ></c:set>
		
					
		<div class="frame" style="width:70%; padding:20px 40px 40px 40px;">
		<h1 class="candidateIdheading">Ehdokas ${candidate.id}. ${candidate.surname} ${candidate.firstname} </h1>
		
			<form action='/AddCandidateAnswer' id="c_answerform" method="POST">		
				
				<c:if test="${fn:length(answerlist) < fn:length(questionlist)}">
					<h3 class="noanswers">Et ole vielä vastannut kaikkiin vaalikoneen kysymyksiin.</h3><br>
					<a href='/ShowCandidateQuestions' class="btn-grad" style="width:125px;">Vastaa kysymyksiin</a>
					
				</c:if>
							
				<c:if test="${!empty requestScope.answerlist}">
				
					<a href='/ShowCandidates' class="btn-grad" style="width:85px;">Takaisin</a>
				
					<h2 class="addcandidate_heading">antamasi vastaukset</h2>				
					
					<c:forEach items="${requestScope.answerlist}" var="answer">						
						 
							<div class="question">
									
									<p class="questionheader">${answer.questionId}. ${questions[count].question} </p>
													
									<div class="answerbuttons">		
										<div class="answerbuttonheader">			
											<p>1</p><p>2</p><p>3</p><p>4</p><p>5</p>
										</div>							
										<div class="radiobuttons">
											<input type="hidden" id="q${answer.questionId}id" name="q${answer.questionId}id" value="${answer.questionId}">	
											<input type="hidden" id="candidateid" name="candidateid" value="${candidate.id}">			
											<input type="radio" id="q${answer.questionId}-1" name="q${answer.questionId}answer" value="1" <c:if test="${answer.answer == '1'}">CHECKED</c:if>>				
											<input type="radio" id="q${answer.questionId}-2" name="q${answer.questionId}answer" value="2" <c:if test="${answer.answer == '2'}">CHECKED</c:if>>			
											<input type="radio" id="q${answer.questionId}-3" name="q${answer.questionId}answer" value="3" <c:if test="${answer.answer == '3'}">CHECKED</c:if>>
											<input type="radio" id="q${answer.questionId}-4" name="q${answer.questionId}answer" value="4" <c:if test="${answer.answer == '4'}">CHECKED</c:if>>
											<input type="radio" id="q${answer.questionId}-5" name="q${answer.questionId}answer" value="5" <c:if test="${answer.answer == '5'}">CHECKED</c:if>>
										</div>
										<div class="scale">			
											<p>1=täysin samaa mieltä,2=jokseenkin samaa mieltä,3=ei samaa eikä eri mieltä,4=jokseenkin eri mieltä,5=täysin eri mieltä</p>
										</div>
										<div class="commentarea">							
											<textarea class="comment" id="q${answer.questionId}comment" name="q${answer.questionId}comment" form="c_answerform" placeholder="kommmentti (vapaaehtoinen)">${answer.comment}</textarea>			
										</div>
									</div>
							</div>
																	
						<c:set var="count" value="${count + 1}" scope="page"></c:set>					
					</c:forEach>				
					<br><br>							
					<input type="submit" class="button"  value="Tallenna vastauksesi">
				</c:if>	
			</form><br>
			
			<a href='/ShowCandidates' class="btn-grad" style="width:125px;">Takaisin</a>
							
		</div>
	</div>
	
		<button onclick="topFunction()" id="scrollBtn" title="Go to top"><b>&#11165;</b></button>
		<script>
			// javascript for scroll to top button
			mybutton = document.getElementById("scrollBtn");	
			
			window.onscroll = function() {
				scrollFunction()
			};
	
			function scrollFunction() {
				if (document.body.scrollTop > 50
						|| document.documentElement.scrollTop > 50) {
					mybutton.style.display = "block";
				} else {
					mybutton.style.display = "none";
				}
			}
	
			// When the user clicks on the button, scroll to the top of the document
			function topFunction() {
				document.body.scrollTop = 0; // For Safari
				document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
			}
		</script>	

			

</body>
</html>