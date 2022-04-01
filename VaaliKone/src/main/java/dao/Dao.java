package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Candidate;

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
	//read candidates from database
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
	//update
	public ArrayList<Candidate> updateCandidate(Candidate c) {
		try {
			String sql="UPDATE ehdokkaat SET ika=?, sukunimi=?, etunimi=?, puolue=?, kotipaikkakunta=?, miksi_eduskuntaan=?, mita_asioita_haluat_edistaa=?, ammatti=?, WHERE ehdokas_id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c.getAge());
			pstmt.setString(3, c.getSurname());
			pstmt.setString(2, c.getFirstname());
			pstmt.setString(4, c.getParty());
			pstmt.setString(5, c.getHometown());
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
	
	public Candidate readCandidate(String id) {
		Candidate c=null;
//		ArrayList<Candidate> list=new ArrayList<>();
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
//				list.add(c);
			}
			return c;
		}
		catch(SQLException e) {
			return null;
		}
	}

}
