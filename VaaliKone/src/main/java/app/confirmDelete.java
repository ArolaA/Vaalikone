package app;

import java.io.IOException;
//import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidate;

/**
 * Servlet implementation class confirmDelete
 */
@WebServlet("/confirmDelete")
public class confirmDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao=null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
	
    public confirmDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
//		ArrayList<Candidate> list=new ArrayList<>();
		Candidate c=null;
		if (dao.getConnection()) {
			c=dao.readCandidate(id);
		}
		
		request.setAttribute("candidateinfo", c);
		
		
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/confirmDelete.jsp");
		rd.forward(request, response);
	}


}
