<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="data.Users" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rekisteröi uusi käyttäjä</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
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
			<form class="registeruser" id="registeruser" action="/registeruser" method="post">				        		        			  
			        <table>			        	
			            <tr>
			             	<td class="loginlogo"><img src="/img/user_logo.png" alt="username logo">	                
			                	<input type="text" name="user" required placeholder="käyttäjänimi" value="${requestScope.user}"></td>
			            </tr>			            	                     
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password" id="password" minlength="5" value="${requestScope.pwd}" required placeholder="salasana (min 5 merkkiä)" >
			                	<i class="bi bi-eye-slash" id="togglePassword"></i></td>
			            </tr>
			            <tr>
			            	<td class="passwordlogo"><img src="/img/password_logo.png" alt="password logo">
			                	<input type="password" name="password2" id="password2" value="${requestScope.pwd}" required placeholder="salasana uudestaan">
			                	<i class="bi bi-eye-slash" id="togglePassword2"></i></td>
			            </tr>			  
			        </table>
			        <p style="color:red; font-size: 12px; font-weight: bold; text-transform: uppercase;">${requestScope.addfailed} </p>
			                 
			        <input class="button2" type="submit" value="Tallenna">  
			</form><br>
			<a href='../jsp/admin.jsp' class="btn-grad" style="width: 85px;">Takaisin</a>
		</div>
	</div>
</div>

<script>
	const togglePassword = document.querySelector("#togglePassword");
	const password = document.querySelector("#password");
	
	const togglePassword2 = document.querySelector("#togglePassword2");
	const password2 = document.querySelector("#password2");
	
	togglePassword.addEventListener("click", function () {
	    // toggle the type attribute
	    const type = password.getAttribute("type") === "password" ? "text" : "password";
	    password.setAttribute("type", type);
	    
	    // toggle the icon
	    this.classList.toggle("bi-eye");
	}); 
	
	togglePassword2.addEventListener("click", function () {
	    // toggle the type attribute
	    const type2 = password2.getAttribute("type") === "password" ? "text" : "password";
	    password2.setAttribute("type", type2);
	    
	    // toggle the icon
	    this.classList.toggle("bi-eye");
	}); 
</script>

</body>
</html>