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
	<div class="frame" style="width: 30%;">
		<div class="loginform">
			<form action="/checklogin" method="post">
					<p style="color:red; font-size: 12px; font-weight: bold;">${requestScope.login} </p>		  
			        <h3>Anna käyttäjänimi ja salasana</h3>			        			  
			        <table>
			        	<tr>
			        		<td>Käyttäjänimi:</td>
			        	</tr>
			            <tr>	                
			                <td><input type="text" name="user" required /></td>
			            </tr>
			            <tr>	         
			                <td>Salasana:</td>
			            </tr>	                     
			            <tr>
			                <td><input type="password" name="password" required/></td>
			            </tr>			  
			        </table>
			        <br>	          
			        <input type="submit" value="Kirjaudu" />  
			</form>
		</div>
	</div>
</div>
</body>
</html>