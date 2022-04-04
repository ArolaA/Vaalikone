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
	  	<div class="frame adminview" style="padding-bottom: 60px;">
	  	<div class="header">
		  	<div class="column adminlogo">	 
		  		<img src="/img/admin.png" alt="admin logo">
		  		<p>admin mode</p>						
		  	</div> 
		  	<div class="logo column">	 		
		  		<img src="/img/vaalikone_logo.png" alt="vaalikone logo">
		  	</div>
		  	<div class="column logout">		  			  		
		  		<a class="loginbutton" href='/logout'>logout<img src="/img/logout_logo.png" alt="logout logo"></a>
		  		<!-- <img src="/img/logout_logo.png" alt="logout logo"> -->
		  	</div> 			
	  	</div> 			
	  		<div class="navigation">			    
			    <ul>
				  <li><a class="btn-grad" href='/ShowCandidates'>Näytä ehdokkaat</a></li>
				  <li><a class="btn-grad" href='/jsp/addcandidate.jsp'>Lisää ehdokas</a></li>
				  <li><a class="btn-grad" href="#">toiminto3</a></li>
				  <li><a class="btn-grad" href="#">toiminto4</a></li>
				</ul>			    
		    </div>
		 </div>
	   </div>	   	   	   		
  </body>
</html>