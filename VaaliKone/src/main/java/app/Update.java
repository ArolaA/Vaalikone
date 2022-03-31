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

@WebServlet(
	name = "Update",
	urlPatterns = {"/update"}
)
public class Update extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:8080/vaalikone", "admin", "admin21m");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		response.sendRedirect("index.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		String id=request.getParameter("id");
		String sname=request.getParameter("sukunimi");
		String fname=request.getParameter("etunimi");
		String party=request.getParameter("puolue");
		String dom=request.getParameter("kotipaikkakunta");
		String age=request.getParameter("ika");
		String why=request.getParameter("miksi");
		String what=request.getParameter("mita");
		String prof=request.getParameter("ammatti");

		Candidate c=new Candidate(id, sname, fname, party, dom, age, why, what, prof);

		ArrayList<Candidate> list=null;
		if (dao.getConnection()) {
			list=dao.updateCandidate(c);
		}

		request.setAttribute("candidatelist", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showcandidates.jsp");
		rd.forward(request, response);
	}
}