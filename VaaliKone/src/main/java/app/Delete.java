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
import data.Candidate;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")

public class Delete extends HttpServlet {
	private Dao dao;
	private static final long serialVersionUID = 1L;
	
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
	public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
//	@Override

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		String id = request.getParameter("id");

		
		ArrayList<Candidate> list=null;
		if (dao.getConnection()) {
			list=dao.deleteCandidate(id);
		}
		request.setAttribute("candidatelist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/deletecandidate.jsp");
		rd.forward(request, response);
		
	}
	
	


}
