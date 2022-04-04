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
		<h2 class="addcandidate_heading">Anna ehdokkaan tiedot</h2>		
		<form action='/AddCandidate' id="lisayslomake" method="POST">
				<label for="id">Ehdokkaan numero:</label><br>
				<input type="number" id="id" name="id" required><br>
				<label for="sukunimi">Sukunimi:</label><br>
				<input type="text" id="sukunimi" name="sukunimi" required><br>
	  			<label for="etunimi">Etunimi:</label><br>
	  			<input type="text" id="etunimi" name="etunimi" required><br>
	  			<label for="puolue">Puolue:</label><br>			
	  			<input type="text" id="puolue" name="puolue" required><br>
	  			<label for="ika">Ikä</label><br>			
	  			<input type="number" id="ika" name="ika" required><br>
	  			<label for="kotipaikkakunta">Kotipaikkakunta:</label><br>			
	  			<input type="text" id="kotipaikkakunta" name="kotipaikkakunta" required><br>
	  			<label for="ammatti">Ammatti:</label><br>			
	  			<input type="text" id="ammatti" name="ammatti" required><br>
	  			<label for="miksi">Miksi haet eduskuntaan?</label><br>			
	  			<input type="text" id="miksi" name="miksi"><br>
	  			<label for="mita">Mitä asioita haluaisit edistää?</label><br>			
	  			<input type="text" id="mita" name="mita"><br><br>		
				<input type="submit" class="button"  value="Tallenna">
		</form><br>
		
		<table style="justify-content:left;">
			<tr>
				<td><a href='admin.jsp' class="btn-grad">Takaisin</a></td>								
			</tr>		
		</table>
		
	</div>
</div>

			

</body>
</html>