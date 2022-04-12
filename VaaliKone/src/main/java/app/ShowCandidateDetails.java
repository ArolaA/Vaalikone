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
 * Servlet implementation class ShowCandidateDetails
 */
@WebServlet("/ShowCandidateDetails")
public class ShowCandidateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Dao dao;	
    
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
       
   
    public ShowCandidateDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//set the right character enconding, so that special characters print out properly
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<Candidate> list=null;
		ArrayList<String> list2=null;
		
		if (dao.getConnection()) {
			list=dao.readAllCandidates();
		}
		else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("candidatelist", list);		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showcandidatedetails.jsp");
		rd.forward(request, response);	

	}
	

}
