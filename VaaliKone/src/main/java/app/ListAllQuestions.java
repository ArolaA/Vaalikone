package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;

/**
 * Servlet implementation class ListAllQuestions
 */
@WebServlet("/ListAllQuestions")
public class ListAllQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private Dao dao=null;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}      
    
    public ListAllQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<Question> list=null;
		
		//get two lists (questions and candidates) using the appropriate methods from the Dao-class
				if (dao.getConnection()) {			
					list=dao.readAllQuestions();
				}
				else {
					System.out.println("No connection to database");
				}
				
				//set both lists as attributes and forward to addcandidateanswerjsp
				request.setAttribute("questionlist", list);
				RequestDispatcher rd=request.getRequestDispatcher("/jsp/listallquestions.jsp");
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
