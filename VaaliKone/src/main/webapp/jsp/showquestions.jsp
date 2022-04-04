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
					<table>
						<tr>
						    <th class="id_column">ID</th>
						    <th class="question">Kysymys</th>
					  	</tr>
						<c:forEach var="question" items="${requestScope.questionlist}">
							<tr>
								<td class="id_column">${question.id} </td>
								<td>${question.question} </td>
								<%--TODO tähän radio button form joka määrittää käyttäjän vastauksen arvon--%>
							</tr>
						</c:forEach>
						<tr>
							<td><a href='../index.html' class="btn-grad">Takaisin</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>