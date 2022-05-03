<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikone admin mode</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">

</head>
<body>

<%  
if (session.getAttribute("user") != null) 
	{
		String user = session.getAttribute("user").toString();
	} 
else 
	{
	  response.sendRedirect("/jsp/login.jsp");            
	}
%>

<div class="background">	  	  	
	  	<div class="frame adminview" style="padding-bottom: 5px;">
	  	<div class="header">
		  	<div class="column">		  			  							
		  	</div> 
		  	<div class="logo column">	 		
		  		<img id="adminlogo" src="/img/vaalikone_logo_admin.jpg" alt="vaalikone logo admin mode">
		  	</div>
		  	<div class="column logout">			  			  			  		
		  		<a class="loginbutton" onclick="return confirm('Käyttäjä ${user} kirjataan ulos?')" href='/logout'><img src="/img/logout_logo.png" alt="logout logo"><c:out value = "${user}"/></a>		  		
		  	</div> 			
	  	</div> 			
	  		<div class="navigation">			    
			    <ul>
				  <li><a class="btn-grad" href='/ShowCandidates'>Ehdokkaat</a></li>
				  <li><a class="btn-grad" href='/ShowCandidateQuestions'>Ehdokas vastaa</a></li>
				  <li><a class="btn-grad" href='/ListAllQuestionsRest'>Kysymykset</a></li>
				  <li><a class="btn-grad" href='adduser.jsp'>Luo uusi käyttäjä</a></li>				  
				</ul>			    
		    </div>
		    
		    <p class="copyright">&copy; 2022 Ari-Jussi Ahonen, Arsi Arola & Oskari Ahoniemi. All rights reserved.</p>
		 </div>		 
	   </div>	   	     	   	   		
  </body>
</html>