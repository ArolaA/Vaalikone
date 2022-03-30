package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Candidate;

@WebServlet("/AddCandidate")

public class AddCandidate extends HttpServlet {
	
	private Dao dao=null;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
    public AddCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {	
		
		PrintWriter out = response.getWriter(); 
		
		String id=request.getParameter("id");
		String age=request.getParameter("ika");
		String firstname=request.getParameter("etunimi");
		String surname=request.getParameter("sukunimi");
		String party=request.getParameter("puolue");
		String hometown=request.getParameter("kotipaikkakunta");
		String why=request.getParameter("miksi");
		String what=request.getParameter("mita");
		String prof=request.getParameter("ammatti");	
		
		//create a new instance from candidate-class
		Candidate c = new Candidate(id, age, surname, firstname, party, hometown, why, what, prof);
		
		//clean up the list of candidates
		ArrayList<Candidate> list=null;
		
		//connect to the database using the dao-class method and add the created Candidate c 
		//to the database using another method which also returns the new list of candidates  
		if (dao.getConnection()) {
			list=dao.addCandidate(c);
		}
		else {
			System.out.println("No connection to database");
		}
		
		//if the returned candidate-list is empty, then adding the new candidate has failed and a 
		//javascript pop-up message appears
		if(list==null)
		{			
			out.println("<script type=\"text/javascript\">"); 
			out.println("alert('Ehdokasta ei lis�tty tietokantaan.Tarkista ehdokasnumero!!');"); 
			out.println("location='jsp/addcandidate.jsp';"); 
			out.println("</script>"); 
		}
		
		//if the candidate-list is returned ok and the candidate is added successfully 
		//then javascript confirm message appears
		else 
		{			
			out.println("<script type=\"text/javascript\">"); 
			out.println("if(confirm('Ehdokas lis�tty onnistuneesti tietokantaan. Lis�t��nk� uusi ehdokas?')){;");
			out.println("location='jsp/addcandidate.jsp';}");
			out.println("else{");
			out.println("location='../index.html';}");
			out.println("</script>"); 
		}		
	}
}