<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList" %>   
 <%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vaalikone kaikki ehdokkaat</title>

<link rel="stylesheet" type="text/css" href="mycss.css">

</head>
<body>
    
<div class="background">
		<div class="frame" style="width: 60%;">
			<div class="heading">
				<div id="heading">
					<h2>ehdokkaat</h2>
				</div>
			</div>									
				<div class="candidatedetails">	
					<div class="candidatedetailsnavigation">	
						<a href='../index.html' class="btn-grad">Takaisin</a>
					</div>								
					<c:forEach var="candidate" items="${requestScope.candidatelist}">
						<div class="candidatedetailstext">
							<p class="candidatedetailsnumber">${candidate.id}. ${candidate.firstname} ${candidate.surname}</p>
							<p class="candidatedetailsheading">Puolue:</p>
							<p>${candidate.party}</p>
							<p class="candidatedetailsheading">Ikä:</p>
							<p>${candidate.age}</p>
							<p class="candidatedetailsheading">Kotipaikkakunta:</p>
							<p>${candidate.hometown}</p>
							<p class="candidatedetailsheading">Ammatti:</p>
							<p>${candidate.profession}</p>
							<p class="candidatedetailsheading">Miksi haet eduskuntaan?</p>
							<p>${candidate.why}</p>
							<p class="candidatedetailsheading">Mitä asioita haluaisit edistää?</p>
							<p>${candidate.what}</p>
						</div>
					</c:forEach>					
				</div>
		</div>
	</div>
		<button onclick="topFunction()" id="scrollBtn" title="Go to top"><b>&#11165;</b></button>
		<script>
			// javascript for scroll to top button
			mybutton = document.getElementById("scrollBtn");	
			
			window.onscroll = function() {
				scrollFunction()
			};
	
			function scrollFunction() {
				if (document.body.scrollTop > 50
						|| document.documentElement.scrollTop > 50) {
					mybutton.style.display = "block";
				} else {
					mybutton.style.display = "none";
				}
			}
	
			// When the user clicks on the button, scroll to the top of the document
			function topFunction() {
				document.body.scrollTop = 0; // For Safari
				document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
			}
		</script>	
	</body>
</html>