<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mycss.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<h1></h1>

<div class="background">	  	  	
  	<div class="frame" style="padding-bottom: 20px; width:40%;">
  		<div class="bestcandidatewindow">
  			<div class="bestcandidatemessage">
				<p>Vastauksiesi pohjalta sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${candidate.id}"/><br>
				<c:out value="Nimi: ${candidate.surname}"/> 
				<c:out value=" ${candidate.firstname}"/><br>
				<c:out value="Puolue: ${candidate.party}"/><br><br> 
			</div>		
		</div>	
	
		<a href='/ShowCandidates' class="btn-grad" style="width:125px;">Vastaa uudestaan</a>
		<a href='index.html' class="btn-grad" style="width:125px;">Palaa etusivulle</a>
	</div>
</div>
</body>
</html>