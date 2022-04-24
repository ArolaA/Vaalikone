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
 * Servlet implementation class ListAllQuestionsRest
 */
@WebServlet("/ListAllQuestionsRest")
public class ListAllQuestionsRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllQuestionsRest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		List<Question> list=null;
		list=readQuestions(request);
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("./jsp/listallquestions.jsp");
		rd.forward(request, response);
	}
	
	private List<Question> readQuestions(HttpServletRequest request) {
		String uri = "http://127.0.0.1:8080/rest/restdao/readallquestions";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		//This will be the second parameter of post method
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};		
		List<Question> returnedList=b.get(genericList);
		return returnedList;
	}

}
