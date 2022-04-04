<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User login</title>

<link rel="stylesheet" type="text/css" href="../mycss.css">

</head>
<body>
<div class="background">
	<div class="frame" style="width: 25%;">
		<div class="loginform">
			<form action="/checklogin" method="post">							  
			        <h3>Anna käyttäjänimi ja salasana</h3>			        			  
			        <table>			        	
			            <tr>
			            	<td class="loginlogo"><img src="/img/user_logo.png" alt="username logo">	                
			                	<input type="text" name="user" required placeholder="käyttäjänimi"></td>
			            </tr>			            	                     
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password" required placeholder="salasana"></td>
			            </tr>			  
			        </table>
			        <p style="color:red; font-size: 12px; font-weight: bold; text-transform: uppercase;">${requestScope.login} </p>
			                 
			        <input class="loginbutton" type="submit" value="Kirjaudu">  
			</form>
		</div>
	</div>
</div>
</body>
</html>