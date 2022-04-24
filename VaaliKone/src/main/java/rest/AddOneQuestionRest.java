package rest;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.Question;

/**
 * Servlet implementation class AddOneQuestionRest
 */
@WebServlet(name = "AddQuestionRest", urlPatterns = { "/AddQuestionRest" })
public class AddOneQuestionRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddOneQuestionRest() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");	
		PrintWriter out = response.getWriter();			
		
		List<Question> list=null;
		list=addQuestion(request);
		out.println("<script type=\"text/javascript\">");
		out.println("if(window.confirm('Kysymys lisätty onnistuneesti tietokantaan. Lisätäänkö uusi kysymys?')){;");
		out.println("location='./jsp/addquestion.jsp';}");
		out.println("else{}");
		out.println("</script>");
		request.setAttribute("questionlist", list);
		RequestDispatcher rd=request.getRequestDispatcher("./jsp/listallquestions.jsp");
		rd.forward(request, response);
	}	
	
	private List<Question> addQuestion(HttpServletRequest request) {
		Question q=new Question(request.getParameter("question"));
		String uri = "http://127.0.0.1:8080/rest/restdao/addquestion";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Entity<Question> e=Entity.entity(q,MediaType.APPLICATION_JSON + ";charset=UTF-8");
		GenericType<List<Question>> genericList = new GenericType<List<Question>>() {};	
		List<Question> returnedList=b.post(e, genericList);
		return returnedList;
	}

}
