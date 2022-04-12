package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Ordering;
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
		
//		out.println("Tama on alist " + alist);
//		out.println(answerTable);
//		
//		out.println(candidates.size());
//		out.println("Answertablen 1,1-paikka"+answerTable.get(1,1));
//		out.println(Integer.parseInt(alist.get(0)));
//		out.println(alist.size());
//		Map<Integer, Integer> candAnswer1= answerTable.row(1);
//		out.println(candAnswer1);
		

			
		// Creating table to store scores from different candidates
		
		Table<Integer, Integer, Integer> scoreTable = HashBasedTable.create();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
//		Map<Integer, Integer> scoresTable= new ap<Integer, Integer>();
		
		// Loop to calculate the answer differences between the user and candidates
	
		
		
		for(int z=1; z<=candidates.size();z++)
		{
//			Map<Integer, Integer> candAnswer= answerTable.row(z);
			int score1=0;
//			int ans;
			int index=1;
			int ansScore=0;
			int cand_id=z;
			
//			out.println("Ulompi looppi: "+z);
			
			
			for(int x=0; x < alist.size(); x++)
			{
				index=x+1;
//				out.println("X arvo: "+x);
//				out.println("Z arvo: "+z);
				int userValue = Integer.parseInt(alist.get(x));
//				int userValue = 0;
				int candValue = answerTable.get(z,x+1);
//				int candValue = 0;
//				out.println("Uservaluen arvo: " +userValue);
//				out.println("Candvaluen arvo: " +candValue);
				ansScore= Math.abs(userValue - candValue);
				score1=score1+ansScore;
//				out.println("Tämän kierroksen pistetilanne: "+score1);
//				out.println("Sisempi looppi: "+(x+1));
			}
			scoreTable.put(index, cand_id, score1);
			hm.put(cand_id, score1);
//			out.println(z+".Ehdokkaan pisteet:"+score1);
			
			
		} // End of the counting loop
		
		out.println(scoreTable);
		out.println(hm);

		
        Map<Integer, Integer> hm1 = sortByValue(hm);
		 for (Map.Entry<Integer, Integer> en : hm1.entrySet()) {
	            System.out.println("Key = " + en.getKey() +
	                          ", Value = " + en.getValue());
	        }
		 
		out.println(hm1);

		
		request.setAttribute("scoretable", scoreTable);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showBestCandidate.jsp");
		rd.forward(request, response);
		
		
		request.setAttribute("candidatelist", candidates);
		RequestDispatcher rd2=request.getRequestDispatcher("/jsp/showBestCandidate.jsp");
		rd2.forward(request, response);
	

	}
	
	public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm){
		
		
		//Create a list from elements of HashMap
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>> (hm.entrySet());
		
		// Sorting of the List
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2)
 {
     return (o1.getValue()).compareTo(o2.getValue());
 }
});
		// Insert sorted list to hashmap
		  HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
	        for (Map.Entry<Integer, Integer> aa : list) {
	            temp.put(aa.getKey(), aa.getValue());
	        }
		return temp;
	}
}


