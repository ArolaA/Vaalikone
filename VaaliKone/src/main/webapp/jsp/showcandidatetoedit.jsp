<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../mycss.css">
<meta charset="UTF-8">
<title>Vaalikone</title>
</head>
<body>
	<div class="background">
		<div class="frame" style="width: 40%; padding: 20px 40px 40px 40px;">
			<div class="addcandidate">
				<div class="addcandidate_heading">
					<h2>ehdokkaan muokkaus</h2>
				</div>
			</div>
			<form id="lisayslomake" action='update' method='post'>
				<label for="id">Ehdokkaan numero</label><br>
				<input type='number' name='id' value='${requestScope.candidate.id}' readonly><br>
				<label for="ika">ikä</label><br>
				<input type='number' name='ika' value='${requestScope.candidate.age}' required min="1"><br> 
				<label for="sukunimi">sukunimi</label><br>
				<input type='text' name='sukunimi' value='${requestScope.candidate.surname}' required><br>
				<label for="etunimi">etunimi</label><br>
				<input type='text' name='etunimi' value='${requestScope.candidate.firstname}' required><br>
				<label for="puolue">puolue</label><br>
				<input type='text' name='puolue' value='${requestScope.candidate.party}' required><br>
				<label for="kotiopaikkakunta">kotipaikkakunta</label><br> 
				<input type='text' name='kotipaikkakunta' value='${requestScope.candidate.hometown}' required><br> 
				<label for="miksi">Miksi haet eduskuntaan?</label><br>
				<textarea class="editcandidatetext" name="miksi" form="lisayslomake">${requestScope.candidate.why}</textarea> <br>
				<label for="mita">Mitä asioita haluaisit edistää?</label><br> 
				<textarea class="editcandidatetext" name="mita" form="lisayslomake">${requestScope.candidate.what}</textarea> <br>
				<label for="ammatti">ammatti:</label><br> 
				<input type='text' name='ammatti' value='${requestScope.candidate.profession}' required><br>
				<input type='submit' name='ok' value='Tallenna'>
			</form>
			<div class="addcandidate_navigation">
				<a href='/ShowCandidates' class="btn-grad" style="margin-left: 0px;">Takaisin</a>
			</div>
		</div>
	</div>
</body>
</html>