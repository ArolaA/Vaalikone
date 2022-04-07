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
 * Servlet implementation class ShowQuestions
 */
@WebServlet("/ShowQuestions")
public class ShowQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Dao dao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
    public ShowQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<Question> list=null;		
		
		if (dao.getConnection()) {
			list = dao.readAllQuestions();
		}
		else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("questionlist", list);		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showquestions.jsp");
		rd.forward(request, response);

	}

}
