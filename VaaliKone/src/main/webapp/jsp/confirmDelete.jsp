<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POISTETTAVA EHDOKAS</title>
<link rel="stylesheet" type="text/css" href="mycss.css">
</head>
<body>

<div class="background">	  	  	
  	<div class="frame" style="padding-bottom: 20px; width:40%;">
  		<div class="confirmdeletewindow">
  			<div class="confirmdeletemessage">
				<p>Seuraava ehdokas poistetaan:</p>
				<c:out value="Ehdokas numero: ${candidateinfo.id}"/><br>
				<c:out value="${candidateinfo.surname}"/> 
				<c:out value="${candidateinfo.firstname}"/><br>
				<c:out value="${candidateinfo.party}"/><br><br> 
			</div>		
		</div>	
		<div class="confirmdeletebutton">
				<a class='button' style="margin-bottom: 30px;" href='/delete?id=${candidateinfo.id}'>Vahvista poistaminen</a>
		</div>		
		<a href='/ShowCandidates' class="btn-grad" style="width:125px;">Takaisin ehdokas- listaukseen</a>
	</div>
</div>

</body>
</html>