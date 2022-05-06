package rest;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import dao.Dao;
import data.Candidate;
import data.Question;
import data.UserAnswer;

/**
 * Servlet implementation class CompareAnswer
 */
@WebServlet("/CompareAnswerRest")
public class CompareAnswerRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao dao;
	
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CompareAnswerRest() {
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

			alist.add(answer);
			
			if (dao.getConnection()) {	
				dao.userAnswerToList(u);
			}
			else {
				System.out.println("Jokin vituillaan");
			}
		}
			
		// Creating table and map to store scores from different candidates
		
		Table<Integer, Integer, Integer> scoreTable = HashBasedTable.create();
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();

		
		// Loop to calculate the answer differences between the user and candidates		
		//Count results from all candidates
		
		for(int z=1; z<=candidates.size();z++)
		{

			int score1=0;

			int index=1;
			int ansScore=0;
			int cand_id=candidates.get(z-1).getId();
			System.out.println("Ehdokkaan ID: " +cand_id);

			// Count scoring from received use answers
			for(int x=0; x < alist.size(); x++)
			{
				index=x+1;
				int userValue = 0;
				int candValue = 0;
				int questionId = qlist.get(x).getId();
				
				if(answerTable.get(cand_id,questionId) == null) {
					candValue = 0;
				}
				else {
					candValue = answerTable.get(cand_id,questionId);	
				}				
				
				//Check for null-answers from user and convert them to match candidate answer
				if (alist.get(x)== null)
				{					
					userValue = candValue;	
				}
				else {
					userValue = Integer.parseInt(alist.get(x));
				}
				
				//if the candidate has not answered the question, the Score is set the maximum difference value 
				if(candValue == 0) {
					ansScore = 4;
				}
				//else calculate the absolute difference between the answers
				else {
					ansScore= Math.abs(userValue - candValue);
					System.out.println("Pisteet: " +ansScore);
				}				
				score1=score1+ansScore;
				System.out.println("Kokonaispisteet: " +score1);
			}
			scoreTable.put(index, cand_id, score1);
			result.put(cand_id, score1);
			
		} // End of the counting loop
		
	

		// Arrange result in order of best score
	    Map<Integer, Integer> result1 = sortByValue(result);
	    ArrayList<Integer> scoreResult = new ArrayList<Integer>();
	    ArrayList<Integer> scoreResultPoints = new ArrayList<Integer>();
        
	    for (Map.Entry<Integer, Integer> en : result1.entrySet()) {
            
			 // add candidate scores to a list by their score
	         scoreResult.add(en.getKey());
	         // create a list of scores by standing
	         scoreResultPoints.add(en.getValue());
	         
	         // Print to console to check that everything works properly
			 System.out.println("Key = " + en.getKey() +
	                          ", Value = " + en.getValue());
            
        }
		 
		 // Create a variable for maximum difference value
		 int maxDifference = alist.size()*4;
		 //Test the outcome
		 System.out.println(maxDifference);
		 
		 // Get winning candidate info
		 String cand1 = Integer.toString(scoreResult.get(0));
		 String cand2 = Integer.toString(scoreResult.get(1));
		 String cand3 = Integer.toString(scoreResult.get(2));
		 String cand4 = Integer.toString(scoreResult.get(3));
		 String cand5 = Integer.toString(scoreResult.get(4));
		 
		 Candidate c1=null;
		 Candidate c2=null;
		 Candidate c3=null;
		 Candidate c4=null;
		 Candidate c5=null;
		 
			if (dao.getConnection()) {
				c1=dao.readOneCandidate(cand1);
				c2=dao.readOneCandidate(cand2);
				c3=dao.readOneCandidate(cand3);
				c4=dao.readOneCandidate(cand4);
				c5=dao.readOneCandidate(cand5);
			}
			
			System.out.println(c1.getId());
			System.out.println(c2.getId());
			System.out.println(c3.getId());
			System.out.println(c4.getId());
			System.out.println(c5.getId());
		
		
		request.setAttribute("cand1", c1);
		request.setAttribute("cand2", c2);
		request.setAttribute("cand3", c3);
		request.setAttribute("cand4", c4);
		request.setAttribute("cand5", c5);
		request.setAttribute("maxDif", maxDifference);
		request.setAttribute("scoreValues", scoreResultPoints);
		request.setAttribute("scoreID", scoreResult);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/showBestCandidate2.jsp");
		rd.forward(request, response);
	
		
		

	} // DoPost ends
	
	/**
	 * This method sorts a list by the Values ins ascending order.
	 * @param result received list for sorting
	 * @return returning sorted list.
	 */
	public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> result){
		
		
		//Create a list from elements of HashMap
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>> (result.entrySet());
		
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


