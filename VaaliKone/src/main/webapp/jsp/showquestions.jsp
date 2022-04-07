<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="data.Question" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="../mycss.css">
		<title>Kysymykset</title>
	</head>
	<body>
		<div class="background">
			<div class="frame">
				<div class="heading">
					<div id="heading">
						<h2>Kysymykset</h2>
					</div>
				</div>
				<div class="questionlist">
					<form method="post" name="valinta">
						<table>
							<tr>
							    <th class="id_column">ID</th>
							    <th class="question_column">Kysymys</th>
						  	</tr>
							<c:forEach var="question" items="${requestScope.questionlist}">
								<tr>
									<td class="id_column">${question.id} </td>
									<td>${question.question} </td>
								</tr>
								
									<td><input type="radio" name="valinta" value="1">1 samaa mieltä</td>
									<td><input type="radio" name="valinta" value="2">2 suht samaa mieltä</td>
									<td><input type="radio" name="valinta" value="3">3</td>
									<td><input type="radio" name="valinta" value="4">4</td>
									<td><input type="radio" name="valinta" value="5">5</td>
								
							</c:forEach>
							<tr>
								<td><a href='../index.html' class="btn-grad">Takaisin</a></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>