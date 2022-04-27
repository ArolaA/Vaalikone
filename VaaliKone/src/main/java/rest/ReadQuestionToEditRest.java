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

import data.Question;

/**
 * Servlet implementation class ShowQuestionToEditRest
 */
@WebServlet("/ReadQuestionToEditRest")
public class ReadQuestionToEditRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadQuestionToEditRest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<Question> list = null;
		Question q=readQuestion(request);
		request.setAttribute("question", q);
		RequestDispatcher rd=request.getRequestDispatcher("./jsp/showquestiontoedit.jsp");
		rd.forward(request, response);
	}
	
	private Question readQuestion(HttpServletRequest request) {
		String id = request.getParameter("id");
		String uri = "http://127.0.0.1:8080/rest/restdao/readtoupdatequestion/"+id;
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Question question = b.get(Question.class);
		return question;
	}
	

}
