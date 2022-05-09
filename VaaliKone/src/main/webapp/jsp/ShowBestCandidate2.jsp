<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>  
<link rel="stylesheet" type="text/css" href="mycss.css">
<meta charset="ISO-8859-1">
<title>Sopivimmat Ehdokkaat</title>
</head>

<body>
<h1></h1>

<c:set var="scores" value="${scoreValues}"/>
<c:set var="difference" value="${maxDif}"/>

<div class="background">	  	  	

  	<div class="frame" style="padding-bottom: 20px; width:55%;">  		
	  	<div class="navigation">	
  			<div class="bestcandidatedetails">
  			<p>Vastauksiesi pohjalta sopivin ehdokas:</p>						
				<div class="bestcandidatedetailstext">					
					<div class="bestcandidatetext">
						<c:out value="Ehdokas numero: ${cand1.id}"/><br>
						<c:out value="Nimi: ${cand1.surname}"/> 
						<c:out value=" ${cand1.firstname}"/><br>
						<c:out value="Puolue: ${cand1.party}"/>
					</div>				
					<div class="bestcandidatevalue">	
						<c:out value=""/><b class="countPercentage1"><span>0</span>%</b>						
					</div>
				</div>																	  
			</div>		
			<p id="test"></p>
		</div>			


			<div class="navigation">
				<div class="bestcandidatedetails">
	  				<p>Vastauksiesi pohjalta toiseksi sopivin ehdokas:</p>						
					<div class="bestcandidatedetailstext">					
						<div class="bestcandidatetext">
							<c:out value="Ehdokas numero: ${cand2.id}"/><br>
							<c:out value="Nimi: ${cand2.surname}"/> 
							<c:out value=" ${cand2.firstname}"/><br>
							<c:out value="Puolue: ${cand2.party}"/>
						</div>				
						<div class="bestcandidatevalue">	 
							<c:out value=""/><b class="countPercentage2"><span>0</span>%</b>						
						</div>		
					</div>
				</div>
			</div>	
			<div class="navigation">
				<div class="bestcandidatedetails">
					<p>Vastauksiesi pohjalta kolmanneksi sopivin ehdokas:</p>
					<div class="bestcandidatedetailstext">					
						<div class="bestcandidatetext">					
						<c:out value="Ehdokas numero: ${cand3.id}"/><br>
						<c:out value="Nimi: ${cand3.surname}"/> 
						<c:out value=" ${cand3.firstname}"/><br>
						<c:out value="Puolue: ${cand3.party}"/>
						</div>
						<div class="bestcandidatevalue">						
							<c:out value=""/><b class="countPercentage3"><span>0</span>%</b>										
						</div>
					</div>
				</div>
			</div>	
			<div class="navigation">
				<div class="bestcandidatedetails">
					<p>Vastauksiesi pohjalta neljänneksi sopivin ehdokas:</p>
					<div class="bestcandidatedetailstext">					
						<div class="bestcandidatetext">	
							<c:out value="Ehdokas numero: ${cand4.id}"/><br>
							<c:out value="Nimi: ${cand4.surname}"/> 
							<c:out value=" ${cand4.firstname}"/><br>
							<c:out value="Puolue: ${cand4.party}"/>
						</div>
						<div class="bestcandidatevalue">
							<c:out value=""/><b class="countPercentage4"><span>0</span>%</b>
						</div>										
					</div>		
				</div>
			</div>				
			<div class="navigation">
				<div class="bestcandidatedetails">
					<p>Vastauksiesi pohjalta viidenneksi sopivin ehdokas:</p>

					<div class="bestcandidatedetailstext">
						<div class="bestcandidatetext">	
							<c:out value="Ehdokas numero: ${cand5.id}"/><br>
							<c:out value="Nimi: ${cand5.surname}"/> 
							<c:out value=" ${cand5.firstname}"/><br>
							<c:out value="Puolue: ${cand5.party}"/>
						</div>
						<div class="bestcandidatevalue">
							<c:out value=""/><b class="countPercentage5"><span>0</span>%</b>
						</div>					
					</div>		
				</div>
			</div>  
				<div class="navigation">
				<ul>
					<li><a href='/ShowQuestions' class="btn-grad" style="width:125px;">Vastaa uudestaan</a></li>
					<li><a href='/ShowCandidateDetails' class="btn-grad" style="width:125px;">Tarkastele ehdokkaita</a></li>
					<li><a href='index.html' class="btn-grad" style="width:125px;">Palaa etusivulle</a></li>
				</ul>				
			</div>
	</div>
</div>

	<script type="text/javascript">
					
		animatedResult("${scoreValues[0]}", ".countPercentage1");
		animatedResult("${scoreValues[1]}", ".countPercentage2");
		animatedResult("${scoreValues[2]}", ".countPercentage3");
		animatedResult("${scoreValues[3]}", ".countPercentage4");
		animatedResult("${scoreValues[4]}", ".countPercentage5");
		
		function animatedResult(a, target){
		    var display = $(target +' > span');
		    
		    var score = a;
			var maxDifference = ${maxDif};
			var result = (1-(score/maxDifference))*100;
			var rounded = Math.round(result);
			
	   		var currentValue = parseInt(display.text());
	    	var nextValue    = rounded;

	    	var diff         = nextValue - currentValue;
	    	var step         = ( 0 < diff ? 1 : -1 ); 

	    for (var i = 0; i < Math.abs(diff); ++i) {
	        setTimeout(function() {
	            currentValue += step
	            display.text(currentValue);
	        }, 30 * i)   
	    }
		}							
		
		function countPercent(){
			var score = ${scoreValues[0]};
			var maxDifference = ${maxDif};
			var result = (1-(score/maxDifference))*100;
			var rounded = Math.round(result);
			return document.write(rounded+"%");
		}
		
	 </script>
	 
</body>
</html>

			