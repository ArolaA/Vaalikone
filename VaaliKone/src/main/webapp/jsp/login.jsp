<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User login</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
<link rel="stylesheet" type="text/css" href="../mycss.css">

</head>
<body>
<div class="background">
	<div class="frame" style="width: 25%;">
		<div class="loginform">
			<form action="/checkloginrest" method="post">							  
			        <h3>Anna käyttäjänimi ja salasana</h3>			        			  
			        <table>			        	
			            <tr>
			             	<td class="loginlogo"><img src="/img/user_logo.png" alt="username logo">	                
			                	<input type="text" name="user" value="${requestScope.user}" required placeholder="käyttäjänimi"></td>
			            </tr>			            	                     
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password" id="password" required placeholder="salasana">
			                	<i class="bi bi-eye-slash" id="togglePassword"></i></td>
			                	
			            </tr>			  
			        </table>
			        <p style="color:red; font-size: 12px; font-weight: bold; text-transform: uppercase;">${requestScope.login} </p>
			                 
			        <input class="button2" type="submit" value="Kirjaudu">  
			</form><br>
			<a href='../index.html' class="btn-grad" style="width: 85px;">Takaisin</a>
		</div>
	</div>
</div>

<script>
	const togglePassword = document.querySelector("#togglePassword");
	const password = document.querySelector("#password");
	
	togglePassword.addEventListener("click", function () {
	    // toggle the type attribute
	    const type = password.getAttribute("type") === "password" ? "text" : "password";
	    password.setAttribute("type", type);
	    
	    // toggle the icon
	    this.classList.toggle("bi-eye");
	});   
</script>
</body>
</html>