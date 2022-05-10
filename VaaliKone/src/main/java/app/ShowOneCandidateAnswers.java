package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.CandidateAnswer;
import data.Question;
import data.Candidate;
import dao.Dao;

/**
 * Servlet implementation class ShowOneCandidateAnswers
 */
@WebServlet("/showonecandidateanswers")
public class ShowOneCandidateAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao=null;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
         
    public ShowOneCandidateAnswers() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ArrayList<CandidateAnswer> list = null;
		ArrayList<Question> list2 = null;
		Candidate c = null;
		
		if (dao.getConnection())	{
			list = dao.readOneCandidatesAnswer(id);
			list2 = dao.readAllQuestions();
			c = dao.readCandidate(id);
		}		
		
		request.setAttribute("answerlist", list);
		request.setAttribute("questionlist", list2);
		request.setAttribute("candidate", c);
		request.setAttribute("id", id);			
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showcandidateanswers.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
