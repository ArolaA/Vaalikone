package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidate;
import data.CandidateAnswer;
import data.CandidateQuestion;

/**
 * Servlet implementation class AddCandidateAnswer
 */
@WebServlet("/AddCandidateAnswer")
public class AddCandidateAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
          
    public AddCandidateAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//set the right character enconding, so that special characters print out properly
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter out = response.getWriter();				
								
		        Enumeration<String> parameter = request.getParameterNames();		        
		        
		        int count = 0;
		        int questions = 0;
		        
		        //iterate over Enum elements and count them
		        while(parameter.hasMoreElements()) 
		        {
		        	count++;
		        	parameter.nextElement();
		        }
		        
		        //define the number of questions based on the number of parameters 
		        questions = (count-1)/3;		       
		        
		        String candidateid=request.getParameter("candidateid");
		        
		        ArrayList<CandidateQuestion> list=null;
				ArrayList<Candidate> list2=null;
		        
		        //loop through all the question-parameter and add or update them to the database
		        for(int i = 1; i <= questions; i++) 
	            {
		        	
					String questionid=request.getParameter("q"+i+"id");				
					String answer=request.getParameter("q"+i+"answer");
					String comment=request.getParameter("q"+i+"comment");
					
					
					
					//create a new instance of candidate-class
					CandidateAnswer a = new CandidateAnswer(candidateid, questionid, answer, comment);
					Boolean added = null;
					
					//connect to the database using the getconnection-method from the Dao-class
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
		        
		        out.println("<script type=\"text/javascript\">"); 
				out.println("if(window.confirm('Vastaukset lisätty onnistuneesti tietokantaan. Annetaanko uudet vastaukset?')){;");
				out.println("}");			
				out.println("else{location='jsp/admin.jsp'}");			
				out.println("</script>");
				
				list=dao.readAllCandidateQuestions();
				list2=dao.readAllCandidates();
				
				request.setAttribute("questionlist", list);
				request.setAttribute("candidatelist", list2);
				
				RequestDispatcher rd=request.getRequestDispatcher("/jsp/addcandidateanswer.jsp");
				rd.forward(request, response);
	}	

}
