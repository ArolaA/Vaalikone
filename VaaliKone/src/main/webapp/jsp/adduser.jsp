<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="data.Users" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rekisteröi uusi käyttäjä</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">

</head>
<body>
<div class="background">
	<div class="frame" style="width: 25%;">
		<div class="addcandidate">	
			<div class="addcandidate_heading" style="width:60%">
				<h2>käyttäjän rekisteröinti</h2>
			</div>
		</div>		
		<div class="loginform">
			<form action="/registeruser" method="post">				        		        			  
			        <table>			        	
			            <tr>
			             	<td class="loginlogo"><img src="/img/user_logo.png" alt="username logo">	                
			                	<input type="text" name="user" required placeholder="käyttäjänimi" value="${requestScope.user}"></td>
			            </tr>			            	                     
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password" minlength="5" required placeholder="salasana (min 5 merkkiä)" ></td>
			            </tr>
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password2" required placeholder="salasana uudestaan"></td>
			            </tr>			  
			        </table>
			        <p style="color:red; font-size: 12px; font-weight: bold; text-transform: uppercase;">${requestScope.addfailed} </p>
			                 
			        <input class="button2" type="submit" value="Tallenna">  
			</form><br>
			<a href='../jsp/admin.jsp' class="btn-grad" style="width: 85px;">Takaisin</a>
		</div>
	</div>
</div>
</body>
</html>