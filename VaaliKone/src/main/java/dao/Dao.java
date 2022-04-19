package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import data.UserAnswer;
import data.Candidate;

import data.CandidateAnswer;
import data.CandidateQuestion;
import data.Question;


import java.sql.Connection;


/**
 * Date: 19.4.2022
 * This is the DAO class that is responsible for accessing the databases.
 * @author Arsi Arola, Ari-Jussi Ahonen, Oskari Ahoniemi
 * @version 1.0
 * 
 */


public class Dao {
	/**
	 * String value for URL
	 */
	private String url;
	/**
	 * String value for database user 
	 */
	private String user;
	/**
	 * String value for database user's password
	 */
	private String pass;
	/**
	 * Connection session for the database
	 */
	private Connection conn;
	
	/**
	 * This is DAO constructor
	 * @param url constructor for database url
	 * @param user constructor for database user
	 * @param pass constructor for database user password
	 */
	public Dao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	/**
	 * This method establishes connection to the database
	 * @return returns that connection was created if it is successful or not if it fails
	 */
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * This method reads all candidates from database and returns them as a list
	 * @return returns all candidate values as a list
	 */
	public ArrayList<Candidate> readAllCandidates() {
		ArrayList<Candidate> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from ehdokkaat");
			while (RS.next()){
				Candidate c=new Candidate();
				c.setId(RS.getInt("ehdokas_id"));
				c.setAge(RS.getString("ika"));
				c.setFirstname(RS.getString("etunimi"));
				c.setSurname(RS.getString("sukunimi"));
				c.setParty(RS.getString("puolue"));
				c.setHomeTown(RS.getString("kotipaikkakunta"));
				c.setWhy(RS.getString("miksi_eduskuntaan"));
				c.setWhat(RS.getString("mita_asioita_haluat_edistaa"));
				c.setProfession(RS.getString("ammatti"));
				list.add(c);
			}			
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}

	/**
	 * This method reads single candidate's data to the update class based on the id it gets
	 * @param id used to determine which candidates data is chosen
	 * @return c return chosen candidates data as an object
	 */
	public Candidate readCandidate(String id) {
		Candidate c = null;
		try {
			String sql="select * from ehdokkaat where ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				c = new Candidate();
				c.setId(RS.getInt("ehdokas_id"));
				c.setFirstname(RS.getString("etunimi"));
				c.setSurname(RS.getString("sukunimi"));
				c.setParty(RS.getString("puolue"));
				c.setHomeTown(RS.getString("kotipaikkakunta"));
				c.setAge(RS.getString("ika"));
				c.setWhy(RS.getString("miksi_eduskuntaan"));
				c.setWhat(RS.getString("mita_asioita_haluat_edistaa"));
				c.setProfession(RS.getString("ammatti"));
			}
			return c;
		}
		catch(SQLException e) {
			return null;
		}
	}

	/**
	 * This method updates single candidates data to the database and returns updated candidate list
	 * @param c is an object that contains all candidate values
	 * @return readAllCandidates
	 */
	public ArrayList<Candidate> updateCandidate(Candidate c) {
		try {
			String sql="UPDATE ehdokkaat SET sukunimi=?, etunimi=?, puolue=?, kotipaikkakunta=?, ika=?, miksi_eduskuntaan=?, mita_asioita_haluat_edistaa=?, ammatti=? WHERE ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, c.getSurname());
			pstmt.setString(2, c.getFirstname());
			pstmt.setString(3, c.getParty());
			pstmt.setString(4, c.getHometown());
			pstmt.setInt(5, c.getAge());
			pstmt.setString(6, c.getWhy());
			pstmt.setString(7, c.getWhat());
			pstmt.setString(8, c.getProfession());
			pstmt.setInt(9, c.getId());
			pstmt.executeUpdate();
			return readAllCandidates();
		}
		catch(SQLException e) {
			return null;
		}
	}
	
	/**
	 * This method adds a candidate to the database and returns updated candidate list
	 * @param c is an object that contains all candidate values
	 * @return readAllCandidates
	 */
	public ArrayList<Candidate> addCandidate(Candidate c) {
		try {
			String sql="INSERT INTO ehdokkaat (ehdokas_id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			pstmt.setString(2, c.getSurname());
			pstmt.setString(3, c.getFirstname());
			pstmt.setString(4, c.getParty());
			pstmt.setString(5, c.getHometown());
			pstmt.setInt(6, c.getAge());
			pstmt.setString(7, c.getWhy());
			pstmt.setString(8, c.getWhat());
			pstmt.setString(9, c.getProfession());			
			pstmt.executeUpdate();
			return readAllCandidates();			
		}
		catch(SQLException e) {
			return null;
		}
	}	
	
	/**
	 * This method deletes candidate from the database based on which id is given to it and returns updated candidate list
	 * @param id defines which candidate has been chosen to be deleted
	 * @return readAllCandidates
	 */
	public ArrayList<Candidate> deleteCandidate(String id) {
		try {
			String sql="DELETE FROM ehdokkaat WHERE ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
			return readAllCandidates();	
		}
		catch(SQLException e) {
			return null;
		}
	
	
	}
	
	/** 
	 * This method reads one candidate from the database according to the id and returns a candidate object
	 * @param id string value of candidate id
	 * @return returning the candidate object
	 */
	public Candidate readOneCandidate(String id) {
		Candidate c=null;

		try {
			String sql="SELECT * FROM ehdokkaat WHERE ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			
			while (RS.next()){
				c= new Candidate();
				c.setId(RS.getInt("ehdokas_id"));
				c.setAge(RS.getString("ika"));
				c.setFirstname(RS.getString("etunimi"));
				c.setSurname(RS.getString("sukunimi"));
				c.setParty(RS.getString("puolue"));
				c.setHomeTown(RS.getString("kotipaikkakunta"));
				c.setWhy(RS.getString("miksi_eduskuntaan"));
				c.setWhat(RS.getString("mita_asioita_haluat_edistaa"));
				c.setProfession(RS.getString("ammatti"));

			}
			return c;
		}
		catch(SQLException e) {
			return null;
		}
	}	
	
	/**
	 * This method adds a candidates answer to the database and returns a boolean value according if the adding was a success or not
	 * @param a CandidateAnswer -object
	 * @return returning a boolean value whether the operation was successful or not
	 */
	public Boolean addCandidateAnswer(CandidateAnswer a) {
		try {
			String sql="INSERT INTO vastaukset (ehdokas_id, kysymys_id, vastaus, kommentti) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, a.getCandidateId());
			pstmt.setInt(2, a.getQuestionId());
			pstmt.setInt(3, a.getAnswer());
			pstmt.setString(4, a.getComment());					
			pstmt.executeUpdate();
			return true;
		}		
		catch(SQLException e) {	
			return false;
		}
//		
	}	
	
		/**
		 * This method updates the given candidate answer to the database
		 * @param a CandidateAnswer -object
		 */
		public void updateCandidateAnswer(CandidateAnswer a) {
			try {
				String sql="UPDATE vastaukset SET vastaus=?, kommentti=? WHERE ehdokas_id=? AND kysymys_id=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, a.getAnswer());
				pstmt.setString(2, a.getComment());
				pstmt.setInt(3, a.getCandidateId());
				pstmt.setInt(4, a.getQuestionId());
				pstmt.executeUpdate();
			}
			catch(SQLException e) {
			
			}
		}	
	
		/**
		 * This method reads all questions from the database and returns them in an ArrayList
		 * @return returning an ArrayList of all the questions
		 */
		public ArrayList<CandidateQuestion> readAllCandidateQuestions() {
			ArrayList<CandidateQuestion> list=new ArrayList<>();
			try {
				Statement stmt=conn.createStatement();
				ResultSet RS=stmt.executeQuery("select * from kysymykset");
				while (RS.next()){
					CandidateQuestion q=new CandidateQuestion();
					q.setId(RS.getInt("kysymys_id"));
					q.setQuestion(RS.getString("kysymys"));					
					list.add(q);
				}				
				return list;
			}
			catch(SQLException e) {
				return null;
			}
		}		
		
		/**
		 * This method reads one candidates answers from the database based on the candidate-id and returns them as an CandidateAnswer -object
		 * @param id String value of candidates id
		 * @return returning an ArrayList of answers by a desired candidate
		 */
		public ArrayList<CandidateAnswer> readOneCandidatesAnswer(String id) {
			
			ArrayList<CandidateAnswer> list=new ArrayList<>();

			try {
				String sql="SELECT * FROM vastaukset WHERE ehdokas_id=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
				ResultSet RS=pstmt.executeQuery();
				
				while (RS.next()){
					CandidateAnswer a=new CandidateAnswer();
					a.setCandidateId(RS.getInt("ehdokas_id"));
					a.setQuestionId(RS.getString("kysymys_id"));
					a.setAnswer(RS.getString("vastaus"));
					a.setComment(RS.getString("kommentti"));
					list.add(a);
				}
				return list;
			}
			catch(SQLException e) {
				return null;
			}
		}	
	
	
	/**
	 * This method read all questions from the database and returns an ArrayList of Question-objects 
	 * @return returning an ArrayList of Question -objects 
	 */
	public ArrayList<Question> readAllQuestions() {
		ArrayList<Question> list = new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from kysymykset");
			while (RS.next()){
				Question q = new Question();
				q.setId(RS.getInt("kysymys_id"));
				q.setQuestion(RS.getString("kysymys"));
				list.add(q);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	 
	/**
	 * This method reads a single question from the database based on the given id and returns a Question -object
	 * @param id String value of the question id
	 * @return returning a Question -object based on the given id
	 */
	public Question readOneQuestion(String id) {
		Question q=null;
		try {
			String sql="SELECT * FROM kysymykset WHERE kysymys_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			
			while (RS.next()){
				q= new Question();
				q.setId(RS.getInt("kysymys_id"));
				q.setQuestion(RS.getString("kysymys"));
			}
			return q;
		}
		catch(SQLException e) {
			return null;
		}
	}
	

		/**
		 * This method updates question data and returns an updated list of questions.
		 * @param q Question-object which holds the updated version of the question
		 * @return returning an updated list of questions.
		 */
		public ArrayList<Question> updateQuestion(Question q) {
			
			try {
				String sql="UPDATE kysymykset SET kysymys=? WHERE kysymys_id=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, q.getQuestion());
				pstmt.setInt(2, q.getId());					
				pstmt.executeUpdate();
				return readAllQuestions();
			}
			catch(SQLException e) {
				return null;
			}
		}
	
	
	
 
	/**
	 * This method adds a question to the database and if it is successfully completed, it returns a list of all questions
	 * @param q Question-object which holds the new question to be added
	 * @return returning a list of all questions
	 */
	public ArrayList<Question> addQuestion(Question q) {
		
		try {
			String sql="INSERT INTO kysymykset (kysymys_id, kysymys) VALUES (?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, q.getId());
			pstmt.setString(2, q.getQuestion());							
			pstmt.executeUpdate();
			return readAllQuestions();			
		}
		catch(SQLException e) {
			return null;
		}
	}
	

	
	/**
	 * This method deletes a question from the database according to the given string
	 * @param id id-value (String) of the question to be deleted.
	 * @return returning an updated list of all questions
	 */
	public ArrayList<Question> deleteQuestion(String id) {
		try {
			String sql="DELETE FROM kysymykset WHERE kysymys_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
			return readAllQuestions();	
		}
		catch(SQLException e) {
			return null;
		}
	
	
	}
	
	/**
	 * This method receives all answers given by the user and puts them into a list. Then the list is returned.
	 * @param u UserAnswer-object u which holds the answers from the users.
	 * @return returning a AraayList-of answers.
	 */
	public ArrayList<UserAnswer> userAnswerToList(UserAnswer u) {
		
		ArrayList<UserAnswer> answerlist = new ArrayList<>();
		try {
			int index = 0;
			while (answerlist.size() > index){
				
				answerlist.add(u);
				index++;
			}
			System.out.println("tama on dao answer list " + answerlist);
			return answerlist;
		}
		catch(Exception e) {
			return null;
		}
	}


	/**
	 * This method was created for testing purposes. It retrieves candidate answers from database and stores them into a HashMap. Finally the HashMap is returned.
	 * @return returns map
	 */
	public HashMap<ArrayList<Object>, Integer> readAnswersCandidate() {

	try {
		Statement stmt=conn.createStatement();

		ResultSet RS=stmt.executeQuery("select ehdokas_id, kysymys_id, vastaus FROM vastaukset;");
		
		HashMap<ArrayList<Object>, Integer> map= new HashMap<>();
		ArrayList<Object> mapKey= new ArrayList<>();
		while (RS.next()){
			
			
			String cand_id = (RS.getString("ehdokas_id"));  
					
			String ques_id = (RS.getString("kysymys_id"));
			 
	
			int answer_id=(RS.getInt("vastaus"));

			mapKey.add(cand_id);
			mapKey.add(new Integer(ques_id));
			
			map.put(mapKey, answer_id);
			
			

		}
		return map;
		
	}
	catch(SQLException e) {
		return null;
	}
}
	/**
	 * This method retrieves all answers of candidates from the database. Then it stores the answers into a Guava-table and returns it. 
	 * @return returning a Guava-table with candidate_id, question_id and answer_id.
	 */
	public com.google.common.collect.Table<Integer, Integer, Integer> readAnswersCandidate2() {

	try {
		Statement stmt=conn.createStatement();

		ResultSet RS=stmt.executeQuery("select ehdokas_id, kysymys_id, vastaus FROM vastaukset;");
		

		Table<Integer, Integer, Integer> answerTable = HashBasedTable.create();
		
		while (RS.next()){
			int cand_id = (RS.getInt("ehdokas_id"));  
			int ques_id = (RS.getInt("kysymys_id"));
			int answer_id=(RS.getInt("vastaus"));
			
			answerTable.put(cand_id, ques_id, answer_id);
			
		}
		return answerTable;
		
	}
	catch(SQLException e) {
		return null;
	}
}


}
