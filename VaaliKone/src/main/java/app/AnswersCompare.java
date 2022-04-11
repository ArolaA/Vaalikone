package app;

import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import dao.Dao;
import data.Question;

/**
 * Servlet implementation class AnswersCompare
 */
@WebServlet("/AnswersCompare")
public class AnswersCompare extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao=null;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "admin21m");
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswersCompare() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int scoreCand1=0;
		
		
//		HashMap<ArrayList<Object>, Integer> candMap= new HashMap<>();
		Table<Integer, Integer, Integer> answerTable = HashBasedTable.create();
		ArrayList<Question> list=null;
		if (dao.getConnection())	{
			
			list = dao.readAllQuestions();
//			candMap=dao.readAnswersCandidate();
			answerTable=dao.readAnswersCandidate2();
		}
			
		int scores[] = null ;
		
		for (int i=0, a=1; i<=list.size() && a<=answerTable.size();i++, a++) {
			
			scores[i]=answerTable.get(a, i);
		}
			
		
//			for (ArrayList<Object> key : candMap.keySet()) {
//				out.println(key);
//			}
//			for (int value : candMap.values()) {
//				out.println(value);
//			}
//			for (int i=1; i <= list.size(); i++)
//			{
//			
//			out.print(answerTable);
//			
//			}
//			int score1=candMap);
//			out.println(candMap);
			
//			for(Integer value : candMap.values())
//			{
//				out.println("Value = " + value);
//			}
//			for (int i=0; i <=list.size(); i++)
//				{
//				
//				candMap=dao.readAnswersCandidate();
////				out.println(candMap.get(i));
//				
////					int answer=candMap.get(i);
//					score=score+answer;
//					out.println(score);
//				}
			
//		System.out.println(Arrays.asList(candMap));
//		out.println(candMap);
//		System.out.println(score);
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		HashMap<ArrayList<Object>, Integer> candMap= new HashMap<>();
//		
//		if (dao.getConnection())	{
//			candMap = dao.readAnswersCandidate();
//		System.out.println(Arrays.asList(candMap));
//		out.println(candMap);
//		}	
//		
//		
	}

}
