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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import data.Question;

/**
 * Servlet implementation class DeleteQuestionRest
 */
@WebServlet("/DeleteQuestionRest")
public class DeleteQuestionRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestionRest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			List<Question> list=null;
			list=remainingQuestions(request);
			request.setAttribute("questionlist", list);
			RequestDispatcher rd=request.getRequestDispatcher("./jsp/listallquestions.jsp");
			rd.forward(request, response);
		}
		
		private List<Question> remainingQuestions(HttpServletRequest request) {
			String id = request.getParameter("id");
			String uri = "http://127.0.0.1:8080/rest/restdao/deleteQuestion/"+id;
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			
			GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};		
			List<Question> returnedList=b.delete(genericList);
			return returnedList;
		}

		
	


}
