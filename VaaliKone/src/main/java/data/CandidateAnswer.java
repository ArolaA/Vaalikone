package data;

public class CandidateAnswer {
	
	private int candidate_id;
	private int question_id ;
	private int answer;
	private String comment;	
	
	public CandidateAnswer(String candidate_id, String question_id, String answer, String comment) {
		// TODO Auto-generated constructor stub
		setCandidateId(candidate_id);
		setQuestionId(question_id);
		setAnswer(answer);
		this.comment=comment;
		
	}
	public CandidateAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCandidateId() {
		return candidate_id;
	}
	public void setCandidateId(int candidate_id) {
		this.candidate_id = candidate_id;
	}
	public void setCandidateId(String candidate_id) {
		try {
			this.candidate_id = Integer.parseInt(candidate_id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	
	public int getQuestionId() {
		return question_id;
	}
	public void setQuestionId(int question_id) {
		this.question_id = question_id;
	}
	public void setQuestionId(String question_id) {
		try {
			this.question_id = Integer.parseInt(question_id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public void setAnswer(String answer) {
		try {
			this.answer = Integer.parseInt(answer);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}	

}
