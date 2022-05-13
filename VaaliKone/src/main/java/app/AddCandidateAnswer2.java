package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.CandidateAnswer;
import data.Question;


/**
 * Servlet implementation class AddCandidateAnswer2
 */
@WebServlet("/AddCandidateAnswer2")
public class AddCandidateAnswer2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao dao;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
    public AddCandidateAnswer2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//set the right character enconding, so that special characters print out properly		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");			
		
		//read the JSON-DATA from request and convert it in to a string variable 
		String requestData = request.getReader().lines().collect(Collectors.joining());		
		
		//create a Map-variable where the key-value-pairs are stored
		HashMap<String, String> answers = new HashMap<String, String>();
		
		//remove useless characters from the requestData-string and put a rarely used symbol as a "divider" of substrings
		requestData = requestData.replace("{", "");
		requestData = requestData.replace("}", "");
		requestData = requestData.replaceAll("\",\"", "£");
		requestData = requestData.replaceAll("\":\"", "£");
		requestData = requestData.replaceAll("\"", "");		
		
		//create an array of string-type variables from requestData by splitting it when ever a £-sign is found
		String[] answer = requestData.split("£");		
		
		//loop through answer-array so that every other value is placed as key to the answers-Map
		//and every other value is placed as a value of a key-value-pair
		for(int i=0 ; i< answer.length-1 ; i=i+2 ) {
			answers.put(answer[i], answer[i+1]);					
		}        
        
		ArrayList<Question> qlist=null;
		
		//read all current question-objects from the database to a list		
		if (dao.getConnection()) {
			qlist = dao.readAllQuestions();
		}
		
		String candidateId = answers.get("candidateid");		
		
        //loop through all the questions and get the corresponding keys and their values from the HashMap 
		//and add or update them to the database		            
        for(int i =0 ; i<qlist.size() ; i++) {
        	
        	String answerKey = "qid"+qlist.get(i).getId();
        	String commentKey = "com"+qlist.get(i).getId();
        	String questionid = "" +qlist.get(i).getId();				
			String answerValue = answers.get(answerKey);	
			String comment = answers.get(commentKey);			
			
			//create a new instance of CandidateAnswer-class
			CandidateAnswer a = new CandidateAnswer(candidateId, questionid, answerValue, comment);
			
			//reset the boolean value
			Boolean added = null;
			
			//connect to the database using the getConnection-method from the Dao-class
			if (dao.getConnection()) {					
				
				//call the method addCadidateAnswer from the Dao-class and place the returned boolean value 
				//to a variable
				added=dao.addCandidateAnswer(a);
				
				//if addition was not successful and addCandidateAnswer -method returns false
				//then try updating the candidates answer 
				if(added == false)
				{							
					dao.updateCandidateAnswer(a);
				}
				else {														
				}						
			}
			else {
				System.out.println("No connection to database");
			}			
        }       
		
		
		        
		        
	}
		       	
}