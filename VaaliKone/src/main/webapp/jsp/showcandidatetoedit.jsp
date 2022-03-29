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

		<input type='submit' name='ok' value='Send'> 
		</form>
	</body>
</html>