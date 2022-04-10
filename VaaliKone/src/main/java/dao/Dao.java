package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import data.UserAnswer;
import data.Candidate;

import data.CandidateAnswer;
import data.CandidateQuestion;
import data.Question;


import java.sql.Connection;

public class Dao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public Dao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
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
	//read all candidates from database
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
	//read single candidate to the update based on the id it gets
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
	//update
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
	
	//adds a candidate to the database and if operation is a success it returns a list of all candidates 
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
	
	//adds a candidates answer to the database and return a boolean value according if the adding was a success or not
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
	
	//update candidates answers to the database
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
	
	//read all questions for the candidate from the database and return them in an arraylist
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
		
		//read one candidates answers from the database based on the candidate-id and 
		//return them as an CandidateAnswer -object
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
		

	//kysymyksiin liittyvät tähän
	
	//READ Kysymykset
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
	
	// read a single question from the database based on the given id
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
	
	//update Question data to the database and return a updated list of questions
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
	
	
	
	//adds a question to the database and if operation is a success it returns a list of all questions 
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
	
	//deletes a question from the database according to the given string
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


}
