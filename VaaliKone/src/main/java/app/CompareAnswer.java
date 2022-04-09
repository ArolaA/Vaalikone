package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;

/**
 * Servlet implementation class CompareAnswer
 */
@WebServlet("/CompareAnswer")
public class CompareAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao dao;
	
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CompareAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		ArrayList<Question> qlist=null;
		
		if (dao.getConnection()) {
			qlist = dao.readAllQuestions();
		}

		for(int i = 0; i < qlist.size(); i++){
			String questionid = request.getParameter("q"+qlist+"id");				
			String answer = request.getParameter("q"+qlist+"answer");
			
			UserAnswer u = new UserAnswer(questionid, answer);
			
			if (dao.getConnection()) {	
				dao.userAnswerToList(u);
			}
			else {
				System.out.println("Jokin vituillaan");
			}
		}
		
		//HashMap <String, String> userAnswer = new HashMap();
		//Collection<String> values = Map.computeIfAbsent(userAnswer, k -> new ArrayList<>());
		//TODO useranswer class getters and setters, create a method that creates a list using useranswer
		//convert given list into hash map
		
	}
	
	public class UserAnswer {
		
		private int id;
		private String answer;
		
		public UserAnswer(String id, String answer) {
			setId(id);
			this.answer = answer;

		}
		public UserAnswer() {
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setId(String id) {
			try {
				this.id = Integer.parseInt(id);
			}
			catch(NumberFormatException | NullPointerException e) {
				//Do nothing - the value of id won't be changed
			}
		}
		public String getanswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer;
		}
		public ArrayList<UserAnswer> getUserAnswer(){
			ArrayList<UserAnswer> alist = new ArrayList<UserAnswer>();

			return alist;
		}
		
	}


}
