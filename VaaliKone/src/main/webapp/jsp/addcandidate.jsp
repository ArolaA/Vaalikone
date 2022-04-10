<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaan lisäys vaalikoneeseen</title>

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
				<h2>ehdokkaan lisäys</h2>
			</div>
		</div>		
		<form action='/AddCandidate' id="lisayslomake" method="POST">
				<label for="id">Ehdokkaan numero:</label><br>
				<input type="number" id="id" name="id" min="1" required><br>
				<label for="sukunimi">Sukunimi:</label><br>
				<input type="text" id="sukunimi" name="sukunimi" required><br>
	  			<label for="etunimi">Etunimi:</label><br>
	  			<input type="text" id="etunimi" name="etunimi" required><br>
	  			<label for="puolue">Puolue:</label><br>			
	  			<input type="text" id="puolue" name="puolue" required><br>
	  			<label for="ika">Ikä</label><br>			
	  			<input type="number" id="ika" name="ika" min="1" required><br>
	  			<label for="kotipaikkakunta">Kotipaikkakunta:</label><br>			
	  			<input type="text" id="kotipaikkakunta" name="kotipaikkakunta" required><br>
	  			<label for="ammatti">Ammatti:</label><br>			
	  			<input type="text" id="ammatti" name="ammatti" required><br>
	  			<label for="miksi">Miksi haet eduskuntaan?</label><br>
	  			<textarea class="addcandidatetext" name="miksi" form="lisayslomake"></textarea> <br>			
	  			<label for="mita">Mitä asioita haluaisit edistää?</label><br>			
	  			<textarea class="addcandidatetext" name="mita" form="lisayslomake"></textarea><br>	
				<input type="submit" class="button"  value="Tallenna">
		</form><br>
		
		<div class="addcandidate_navigation">
			<a href='/ShowCandidates' class="btn-grad" style="margin-left: 0px;">Takaisin</a>	
		</div>
		
	</div>
</div>

			

</body>
</html>