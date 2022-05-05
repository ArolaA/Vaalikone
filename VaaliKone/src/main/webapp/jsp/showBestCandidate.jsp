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
<title>Sopivimmat Ehdokkaat</title>
</head>

<body>
<h1></h1>

<div class="background">	  	  	
  	<div class="frame" style="padding-bottom: 20px; width:40%;">
  			
  		<div class="confirmdeletewindow">
  			<div class="confirmdeletemessage">
				<p>Vastauksiesi pohjalta sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${cand1.id}"/><br>
				<c:out value="Nimi: ${cand1.surname}"/> 
				<c:out value=" ${cand1.firstname}"/><br>
				<c:out value="Puolue: ${cand1.party}"/><br><br> 
			</div>		
			</div>	
			<div class="navigation">
			<div class="confirmdeletemessage">
				<p>Vastauksiesi pohjalta toiseksi sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${cand2.id}"/><br>
				<c:out value="Nimi: ${cand2.surname}"/> 
				<c:out value=" ${cand2.firstname}"/><br>
				<c:out value="Puolue: ${cand2.party}"/><br><br> 
			</div>		
			</div>	
			<div class="navigation">
			<div class="confirmdeletemessage">
				<p>Vastauksiesi pohjalta kolmanneksi sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${cand3.id}"/><br>
				<c:out value="Nimi: ${cand3.surname}"/> 
				<c:out value=" ${cand3.firstname}"/><br>
				<c:out value="Puolue: ${cand3.party}"/><br><br> 
			</div>		
			</div>	
			<div class="navigation">
			<div class="confirmdeletemessage">
				<p>Vastauksiesi pohjalta neljänneksi sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${cand4.id}"/><br>
				<c:out value="Nimi: ${cand4.surname}"/> 
				<c:out value=" ${cand4.firstname}"/><br>
				<c:out value="Puolue: ${cand4.party}"/><br><br> 
			</div>		
			</div>	
			<div class="navigation">
			<div class="confirmdeletemessage">
				<p>Vastauksiesi pohjalta viidenneksi sopivin ehdokas:</p>
				<c:out value="Ehdokas numero: ${cand5.id}"/><br>
				<c:out value="Nimi: ${cand5.surname}"/> 
				<c:out value=" ${cand5.firstname}"/><br>
				<c:out value="Puolue: ${cand5.party}"/><br><br> 
			</div>		
			</div>	
			<div class="navigation">
			<ul>
				<li><a href='/ShowCandidates' class="btn-grad" style="width:125px;">Vastaa uudestaan</a></li>
				<li><a href='/ShowCandidateDetails' class="btn-grad" style="width:125px;">Tarkastele ehdokkaita</a></li>
				<li><a href='index.html' class="btn-grad" style="width:125px;">Palaa etusivulle</a></li>
			</ul>
			</div>
	</div>
</div>
</body>
</html>

			