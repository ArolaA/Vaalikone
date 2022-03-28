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

}
