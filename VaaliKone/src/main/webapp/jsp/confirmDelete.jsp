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

<p>Lista pitäisi ALKAA</p><br>
<c:out value="${candidateinfo.id}"/>
<c:out value="${candidateinfo.surname}"/> 
<c:out value="${candidateinfo.firstname}"/><br> 
<c:out value="${candidateinfo.party}"/> 


<p>Lista pitäisi loppua</p><br>

<a class='button' href='/delete?id=${candidateinfo.id}'>Vahvista poistaminen</a>
</body>
</html>