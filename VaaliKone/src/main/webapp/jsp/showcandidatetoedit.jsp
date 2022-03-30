<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vaalikone</title>
	</head>
	<body>
		<h2>Edit Candidate</h2>
		<form action='update' method='post'>
		Candidate id: <input type='text' name='id' value='${requestScope.candidate.id}' readonly><br> 
		Candidate age: <input type='text' name='ika' value='${requestScope.candidate.age}'><br>
		Candidate surname: <input type='text' name='sukunimi' value='${requestScope.candidate.sur}'><br>
		Candidate firstname: <input type='text' name='etunimi' value='${requestScope.candidate.first}'><br>
		Candidate party: <input type='text' name='puolue' value='${requestScope.candidate.party}' ><br>
		Candidate domicile: <input type='text' name='kotipaikkakunta' value='${requestScope.candidate.home}'><br>
		Candidate why: <input type='text' name='miksi' value='${requestScope.candidate.why}'><br>
		Candidate what: <input type='text' name='mita' value='${requestScope.candidate.what}'><br>
		Candidate profession: <input type='text' name='ammatti' value='${requestScope.candidate.prof}'><br>
		<input type='submit' name='ok' value='Send'> 
		</form>
	</body>
</html>