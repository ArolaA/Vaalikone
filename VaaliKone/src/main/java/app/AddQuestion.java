package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidate;
import data.Question;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao dao;	
    
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
	
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		String id=request.getParameter("id");
		String question=request.getParameter("question");	
		
		//clean up the list of questions
		ArrayList<Question> list=null;
		
		//create a new instance of candidate-class
		Question q = new Question(id, question);
		
		//connect to the database using the dao-class method and add the g 
		//to the database using another method which returns the new list of questions  
		if (dao.getConnection()) {
			list=dao.addQuestion(q);
		}
		else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/listallquestions.jsp");
		rd.forward(request, response);
		
	}

}
