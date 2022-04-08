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
				<a href='../index.html' class="btn-grad">Takaisin</a>
				<h2 class="heading">Kysymykset</h2>

				<form method="post" name="valinta">
					<c:forEach var="question" items="${requestScope.questionlist}">
	
						<div class="question">	
							<p class="questionheader">${question.id}. ${question.question}</p>	
							<div class="answerbuttons">
								<div class="answerbuttonheader">
									<p>1</p><p>2</p><p>3</p><p>4</p><p>5</p>
								</div>
								<div class="radiobuttons">
									<input type="hidden" id="q${question.id}id" name="q${question.id}id" value="${question.id}">
									<input type="radio" id="q${question.id}-1" name="q${question.id}answer" value="1">
									<input type="radio" id="q${question.id}-2" name="q${question.id}answer" value="2">
									<input type="radio" id="q${question.id}-3" name="q${question.id}answer" value="3">
									<input type="radio" id="q${question.id}-4" name="q${question.id}answer" value="4">
									<input type="radio" id="q${question.id}-5" name="q${question.id}answer" value="5">
								</div>
								<div class="scale">
									<p>1=täysin samaa mieltä,2=jokseenkin eri mieltä,3=ei samaa eikä eri mieltä,4=jokseenkin samaa mieltä,5=täysin eri mieltä</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</form>
			</div>
		</div>
	</body>
</html>