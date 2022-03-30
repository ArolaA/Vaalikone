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
<h2>Anna ehdokkaan tiedot</h2>
	
	
	<form action='/AddCandidate' id="lisayslomake" method="POST">
			<label for="id">Ehdokaan numero:</label><br>
			<input type="number" id="id" name="id" required><br><br>
			<label for="sukunimi">Sukunimi:</label><br>
			<input type="text" id="sukunimi" name="sukunimi" required><br><br>
  			<label for="etunimi">Etunimi:</label><br>
  			<input type="text" id="etunimi" name="etunimi" required><br><br>
  			<label for="puolue">Puolue:</label><br>			
  			<input type="text" id="puolue" name="puolue" required><br><br>
  			<label for="ika">Ikä</label><br>			
  			<input type="number" id="ika" name="ika" required><br><br>
  			<label for="kotipaikkakunta">Kotipaikkakunta:</label><br>			
  			<input type="text" id="kotipaikkakunta" name="kotipaikkakunta" required><br><br>
  			<label for="ammatti">Ammatti:</label><br>			
  			<input type="text" id="ammatti" name="ammatti" required><br><br>
  			<label for="miksi">Miksi haet eduskuntaan?</label><br>			
  			<input type="text" id="miksi" name="miksi"><br><br>
  			<label for="mita">Mitä asioita haluaisit edistää?</label><br>			
  			<input type="text" id="mita" name="mita"><br><br>		
			<input type="submit"  value="Tallenna">
		</form><br>
		<a href='../index.html' class="button3">Takaisin</a>
			

</body>
</html>