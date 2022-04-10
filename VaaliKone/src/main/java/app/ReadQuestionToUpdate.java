package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;

/**
 * Servlet implementation class ReadQuestionToUpdate
 */
@WebServlet("/ReadQuestionToUpdate")
public class ReadQuestionToUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}       
   
    public ReadQuestionToUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		Question q = null;
		
		if (dao.getConnection())	{
			q = dao.readOneQuestion(id);
		}
		
		request.setAttribute("question", q);		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showquestiontoedit.jsp");
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
