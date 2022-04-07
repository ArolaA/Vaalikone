package data;

public class CandidateQuestion {
	
	private int id;
	private String question;	
	
	public CandidateQuestion(String id, String question) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.question=question;
		
	}
	public CandidateQuestion() {
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
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "CandidateQuestion [id=" + id + ", question=" + question + "]";
	}	

}
