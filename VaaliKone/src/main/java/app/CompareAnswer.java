package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import dao.Dao;
import data.Candidate;
import data.CandidateAnswer;
import data.Question;
import data.UserAnswer;

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
		PrintWriter out = response.getWriter();
		
		ArrayList<Question> qlist=null;
		ArrayList<Candidate> candidates=null;
		ArrayList<String> alist = new ArrayList<String>();
		Table<Integer, Integer, Integer> answerTable = HashBasedTable.create();
		if (dao.getConnection()) {
			qlist = dao.readAllQuestions();
			answerTable=dao.readAnswersCandidate2();
			candidates=dao.readAllCandidates();
		}

		for(int i = 0; i < qlist.size(); i++){
			String questionid = request.getParameter("q"+qlist.get(i).getId()+"id");				
			String answer = request.getParameter("q"+qlist.get(i).getId()+"answer");
			
			UserAnswer u = new UserAnswer(questionid, answer);
			System.out.println("Kysymys id: " + u.getId());
			System.out.println("Kysymys: " + u.getanswer());
			
//			alist.add(questionid);
			alist.add(answer);
			
			if (dao.getConnection()) {	
				dao.userAnswerToList(u);
			}
			else {
				System.out.println("Jokin vituillaan");
			}
		}
		out.println("Tama on alist " + alist);
		out.println(answerTable);
		
		
		
		
//		int answerScore1= Math.abs(Integer.parseInt(alist.get(1)) - answerTable.get(1,1));
//		int answerScore2= Math.abs(Integer.parseInt(alist.get(3)) - answerTable.get(1,2));
//		int answerScore3= Math.abs(Integer.parseInt(alist.get(5)) - answerTable.get(1,3));
//		int answerScore4= Math.abs(Integer.parseInt(alist.get(7)) - answerTable.get(1,4));
//		int answerScore5= Math.abs(Integer.parseInt(alist.get(9)) - answerTable.get(1,5));
//		int answerScore6= Math.abs(Integer.parseInt(alist.get(11)) - answerTable.get(1,6));
//		int answerScore7= Math.abs(Integer.parseInt(alist.get(13)) - answerTable.get(1,7));
//		int answerScore8= Math.abs(Integer.parseInt(alist.get(15)) - answerTable.get(1,8));
//		int answerScore9= Math.abs(Integer.parseInt(alist.get(17)) - answerTable.get(1,9));
//		int answerScore10= Math.abs(Integer.parseInt(alist.get(19)) - answerTable.get(1,10));
//		int answerScore11= Math.abs(Integer.parseInt(alist.get(21)) - answerTable.get(1,11));
//		int answerScore12= Math.abs(Integer.parseInt(alist.get(23)) - answerTable.get(1,12));
//		int answerScore13= Math.abs(Integer.parseInt(alist.get(25)) - answerTable.get(1,13));
//		int answerScore14= Math.abs(Integer.parseInt(alist.get(27)) - answerTable.get(1,14));
//		int answerScore15= Math.abs(Integer.parseInt(alist.get(29)) - answerTable.get(1,15));
//		int answerScore16= Math.abs(Integer.parseInt(alist.get(31)) - answerTable.get(1,16));
//		int answerScore17= Math.abs(Integer.parseInt(alist.get(33)) - answerTable.get(1,17));
//		int answerScore18= Math.abs(Integer.parseInt(alist.get(35)) - answerTable.get(1,18));
//		int answerScore19= Math.abs(Integer.parseInt(alist.get(37)) - answerTable.get(1,19));
		int answerScore1= Math.abs(Integer.parseInt(alist.get(0)) - answerTable.get(1,1));
		int answerScore2= Math.abs(Integer.parseInt(alist.get(1)) - answerTable.get(1,2));
		int answerScore3= Math.abs(Integer.parseInt(alist.get(2)) - answerTable.get(1,3));
		int answerScore4= Math.abs(Integer.parseInt(alist.get(3)) - answerTable.get(1,4));
		int answerScore5= Math.abs(Integer.parseInt(alist.get(4)) - answerTable.get(1,5));
		int answerScore6= Math.abs(Integer.parseInt(alist.get(5)) - answerTable.get(1,6));
		int answerScore7= Math.abs(Integer.parseInt(alist.get(6)) - answerTable.get(1,7));
		int answerScore8= Math.abs(Integer.parseInt(alist.get(7)) - answerTable.get(1,8));
		int answerScore9= Math.abs(Integer.parseInt(alist.get(8)) - answerTable.get(1,9));
		int answerScore10= Math.abs(Integer.parseInt(alist.get(9)) - answerTable.get(1,10));
		int answerScore11= Math.abs(Integer.parseInt(alist.get(10)) - answerTable.get(1,11));
		int answerScore12= Math.abs(Integer.parseInt(alist.get(11)) - answerTable.get(1,12));
		int answerScore13= Math.abs(Integer.parseInt(alist.get(12)) - answerTable.get(1,13));
		int answerScore14= Math.abs(Integer.parseInt(alist.get(13)) - answerTable.get(1,14));
		int answerScore15= Math.abs(Integer.parseInt(alist.get(14)) - answerTable.get(1,15));
		int answerScore16= Math.abs(Integer.parseInt(alist.get(15)) - answerTable.get(1,16));
		int answerScore17= Math.abs(Integer.parseInt(alist.get(16)) - answerTable.get(1,17));
		int answerScore18= Math.abs(Integer.parseInt(alist.get(17)) - answerTable.get(1,18));
		int answerScore19= Math.abs(Integer.parseInt(alist.get(18)) - answerTable.get(1,19));
		int result=answerScore1+answerScore2+answerScore3+answerScore4+answerScore5+answerScore6+answerScore7+answerScore8+answerScore9+answerScore10+answerScore11+answerScore12+answerScore13+answerScore14+answerScore15+answerScore16+answerScore17+answerScore18+answerScore19;
		out.println(". ehdokkaan tulos: "+result);
		
		
		out.println(candidates.size());
		out.println("Answertablen 1,1-paikka"+answerTable.get(1,1));
		out.println(Integer.parseInt(alist.get(0)));
		out.println(alist.size());
		Map<Integer, Integer> candAnswer1= answerTable.row(1);
		out.println(candAnswer1);
		

			
//		ArrayList<Integer> resultList = new ArrayList<Integer>();
		Table<Integer, Integer, Integer> scoreTable = HashBasedTable.create();
		
		for(int z=1; z<=candidates.size();z++)
		{
			Map<Integer, Integer> candAnswer= answerTable.row(z);
			int score1=0;
			int index=1;
			int ansScore=0;
			int cand_id=z;
			
//			for(int x=0; x <= alist.size(); x++)
//			{
//				index=x+1;
//				int userValue = Integer.parseInt(alist.get(x));
//				ansScore= Math.abs(userValue - answerTable.get(z,(x+1)));
//				score1=score1+ansScore;
//				
//			}
			out.println(z+".Ehdokkaan pisteet:"+score1);
			scoreTable.put(index, cand_id, score1);
			
		}
		
		out.println(scoreTable);
//					for(int z=1; z<=candidates.size();z++) {
//						Map<Integer, Integer> candAnswer= answerTable.row(z);
//						out.println(candAnswer);
//		//				out.println(answerTable);
//							for(Map.Entry<Integer, Integer> entry : candAnswer.entrySet()) {
//								int score=0;
//								
//								out.println(entry.getValue());
//								for(int i=0; i<=alist.size();i++) 
//									{
//									
//									int answerScore= Math.abs(Integer.parseInt(alist.get(2)) - entry.getValue());
//									int cand_id = i+1;
//									score=score+answerScore;
//									out.println(answerScore);
//									out.println("Ehdokas nro."+cand_id +"pisteet: "+score);
//									}
//									
//							}
//						
//							
//					}		
					
					
					
//					
//						out.println("Question Id: " + entry.getKey() + ", Answer: " + entry.getValue());
//				         out.println("User answer " + alist.get(1));
//			}
//			}
			
		
	

	}
}
