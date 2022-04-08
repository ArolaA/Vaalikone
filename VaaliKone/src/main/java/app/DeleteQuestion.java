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
 * Servlet implementation class DeleteQuestion
 */
@WebServlet("/DeleteQuestion")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao dao;
	
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
    
    public DeleteQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		
		ArrayList<Question> list=null;
		
		if (dao.getConnection()) {
			list=dao.deleteQuestion(id);
		}
		
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
