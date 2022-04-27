package rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.Question;

/**
 * Servlet implementation class UpdateQuestionRest
 */
@WebServlet("/UpdateQuestionRest")
public class UpdateQuestionRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestionRest() {
        super();
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
		List<Question> list = null;
		list=updateQuestion(request);
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("./jsp/listallquestions.jsp");
		rd.forward(request, response);
	}

	private List<Question> updateQuestion(HttpServletRequest request) {
		Question q=new Question(request.getParameter("id"), request.getParameter("question"));
		System.out.println(q);
		String uri = "http://127.0.0.1:8080/rest/restdao/updatequestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Entity<Question> e=Entity.entity(q,MediaType.APPLICATION_JSON);
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};
		List<Question> returnedList=b.put(e, genericList);
		return returnedList;
	}
}
