package data;

public class LoginInfo {
	private String username;
	private String password;
	
	
	public LoginInfo(String username, String password) {
		// TODO Auto-generated constructor stub
		this.username=username;
		this.password=password;		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasswword() {
		return password;
	}
	public void setPassword(String password) {
		this.username = password;
	}
	
}
