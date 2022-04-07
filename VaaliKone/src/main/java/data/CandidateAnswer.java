package data;

public class CandidateAnswer {
	
	private int candidateId;
	private int questionId;
	private int answer;
	private String comment;	
	
	public CandidateAnswer(String candidateId, String questionId, String answer, String comment) {
		// TODO Auto-generated constructor stub
		setCandidateId(candidateId);
		setQuestionId(questionId);
		setAnswer(answer);
		this.comment=comment;
		
	}
	public CandidateAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public void setCandidateId(String candidateId) {
		try {
			this.candidateId = Integer.parseInt(candidateId);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public void setQuestionId(String questionId) {
		try {
			this.questionId = Integer.parseInt(questionId);
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
	
	@Override
	public String toString() {
		return "CandidateAnswer [candidateId=" + candidateId + ", questionId=" + questionId + ", answer=" + answer + " comment=" + comment+ "]";
	}	

}
