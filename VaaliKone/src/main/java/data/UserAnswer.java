package data;

import java.util.ArrayList;

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