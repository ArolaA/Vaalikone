package data;

public class Candidate {
	
	private int id;
	private String age;
	private String surname;
	private String firstname;
	private String party;
	private String hometown;
	private String why;
	private String what;
	private String profession;
	
	
	public Candidate(String id, String age, String sur, String first, String party, String home, String why, String what, String prof) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.age=age;
		this.surname=sur;
		this.firstname=first;
		this.party=party;
		this.hometown=home;
		this.why=why;
		this.what=what;
		this.profession=prof;
	}
	public Candidate() {
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	
	public String getHometown() {
		return hometown;
	}
	public void setHomeTown(String hometown) {
		this.hometown = hometown;
	}
	
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	
	public String getWhat() {
		return what;
	}
	public void setWhat(String what) {
		this.what = what;
	}
	
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	

}
