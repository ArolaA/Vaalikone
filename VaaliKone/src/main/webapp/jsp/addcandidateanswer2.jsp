<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.CandidateQuestion" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaan vastaukset</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">
</head>
<body onload="setValues()">


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
	<div class="frame" style="width:70%; padding:20px 40px 40px 40px;">	
		<div class="addcandidate">	
			<div class="addcandidate_heading" style="width: 30%;">
				<h2>kysymykset</h2>
				
			</div>
		</div>		
		<a href='/jsp/admin.jsp' class="btn-grad" onclick="clearLocalStorage()" style="width:85px; margin-bottom: 20px;">Takaisin</a>
		
		<c:set var="questions" value="${applicationScope.questionlist}"/>									
		<c:set var="max" value="${fn:length(questions)}"/>		
		
    	<c:set var="i" value="${param.i}"/>
    	
    	<c:if test="${empty i or i<0}">
    		<c:set var="i" value="0"/>
		</c:if>	
		<c:if test="${i > (max-1)}">			
    		<c:set var="i" value="${max-1}"/>
		</c:if>					
			<div class="question">								
					<p class="questionheader">${questions[i].id}. ${questions[i].question}</p>
					<form action='/AddCandidateAnswer' id="c_answerform" method="POST">												
						<div class="answerbuttons">		
							<div class="answerbuttonheader">			
								<p>1</p><p>2</p><p>3</p><p>4</p><p>5</p>
							</div>							
							<div class="radiobuttons">
								<input type="hidden" id ="q${questions[i].id}id" name="q${questions[i].id}id" value="${questions[i].id}">				
								<input type="radio" id="q${questions[i].id}answer" name="q${questions[i].id}answer" value="1">				
								<input type="radio" id="q${questions[i].id}answer" name="q${questions[i].id}answer" value="2">			
								<input type="radio" id="q${questions[i].id}answer" name="q${questions[i].id}answer" value="3" checked>
								<input type="radio" id="q${questions[i].id}answer" name="q${questions[i].id}answer" value="4">
								<input type="radio" id="q${questions[i].id}answer" name="q${questions[i].id}answer" value="5">
							</div>
							<div class="scale">			
								<p>1=täysin samaa mieltä,2=jokseenkin samaa mieltä,3=ei samaa eikä eri mieltä,4=jokseenkin eri mieltä,5=täysin eri mieltä</p>
							</div>
							<div class="commentarea">							
								<textarea class="comment" id="q${questions[i].id}comment" name="q${questions[i].id}comment" form="c_answerform" placeholder="kommentti (vapaaehtoinen)"></textarea>			
							</div>							
							<div class="nextbuttons">								
								<c:if test="${i > 0}">			
		    						<a class="nextbutton" href="/jsp/addcandidateanswer2.jsp?i=${i-1 }" onclick="setToLocalStorage()">&laquo;</a>
								</c:if>
								<c:if test="${i < (max-1)}">
									<a class="nextbutton" href="/jsp/addcandidateanswer2.jsp?i=${i+1}" onclick="setToLocalStorage()">&raquo;</a>
								</c:if>								
							</div>
							<div class="clearbutton">
								<a class="button" href="#" onclick="confirmClearLocalStorage()" style="width: 100px;">Tyhjennä lomake</a>
							</div>	
						</div>					
						
						<div class="showsend">
						<c:if test="${i == (max-1)}">
							<p class="candidateidheading">ehdokas:</p>
							  <select id="candidateid" name="candidateid">
							  	<c:forEach var="candidate" items="${applicationScope.candidatelist}">						
										<option value="${candidate.id}">${candidate.id}. ${candidate.surname} ${candidate.firstname}</option>						
								</c:forEach>			    			    
							  </select>	
							<a class="button2" href="#" onclick="setToLocalStorage();sendAnswers()" style="width: 130px;">tallenna vastauksesi</a>
						</c:if>
						</div>										
					</form><br>
			</div>						
	</div>
</div>	
	
<script>				
	
	function setToLocalStorage(){
		var answer = document.querySelector( 'input[name="q${questions[i].id}answer"]:checked').value;
		var comment = document.getElementById("q${questions[i].id}comment").value	
		var question = document.getElementById("q${questions[i].id}id").value
		localStorage.setItem("qid"+question,answer);
		localStorage.setItem("com"+question,comment);
	}
	
	function printLocalStorage(){
		if (!localStorage){
			return;
		}						
		document.getElementById("answerlist").innerHTML = "";
		document.getElementById("answerlist").innerHTML = "<ol>";
		for (var indeksi=0;indeksi < localStorage.length;indeksi++){
			var avain=localStorage.key(indeksi);				
			document.getElementById("answerlist").innerHTML += "<li>"+avain+" = "+localStorage[avain]+"</li>";
		}
		document.getElementById("answerlist").innerHTML += "</ol>";
	}
	
	function confirmClearLocalStorage(){		
		var proceed = confirm("Tyhjennetäänkö lomake?");
		if (proceed) {
			clearLocalStorage();
			window.location.href = '/jsp/addcandidateanswer2.jsp?i=0'; 
		} else {
			
		}
		
	}	
	
	function clearLocalStorage(){
		localStorage.clear();
	}
	
	function setValues()
	{
		var avain= "qid" +document.getElementById("q${questions[i].id}id").value;
		var avain2= "com" +document.getElementById("q${questions[i].id}id").value;
		var rad = document.querySelectorAll('input[type=radio]');
		var value = localStorage[avain];
		var value2 = localStorage[avain2];			
		
		if (value == null)
			{
			 return;
			}			
		
		for (i = 0; i < rad.length; i++)
		{				
			if (rad[i].value == value)
			{
				rad[i].checked = true;
			}
		}
		if(value2 == null)
		{
			return;
		}
		else{
			document.getElementById("q${questions[i].id}comment").value = value2;
		}			
	}
	
	
	function sendAnswers(){
		
		var proceed = confirm("Lähetetäänkö tiedot?");
		if (proceed) {
		  	var candidate = document.getElementById("candidateid").value
			localStorage.setItem("candidateid",candidate);			
			var jsonAnswers=JSON.stringify(localStorage);			
			var xmlhttp=new XMLHttpRequest();			
			xmlhttp.open("POST", "/AddCandidateAnswer2", true);
			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.send(jsonAnswers);
			
			var proceed2 = confirm("Vastauksesi on lisätty tietokantaan. Palataanko ylläpito-sivulle?")
			if (proceed2) {
				clearLocalStorage();
				window.location.href = '/jsp/admin.jsp';
			} else{
				
			}
		} else {
		  //don't proceed
		}
		
		
	}

</script>		

</body>
</html>