package rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;

import data.Users;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/registeruser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
		List<Users> list = null;		
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String failed = null;
		String encpass = encrypt(password);
		Boolean userOK = true;
		
		//check if the password is typed the same way 2 times
		if(!password.equals(password2))
		{			
			failed = "Salasana on syötetty väärin.";
			userOK = false;
			request.setAttribute("addfailed", failed);
			request.setAttribute("user", user);		
			RequestDispatcher rd = request.getRequestDispatcher("jsp/adduser.jsp");			
			rd.forward(request, response);
		}
		
		//get the list of valid users using a proper method
		list = readValidUsers();
		
		//loop through the valid users -list and check if there is a already a same username in the database		
		for(int i=0 ; i < list.size(); i++)
		{	
			if(user.equalsIgnoreCase(list.get(i).getUser().toString())){
				failed = "Käyttäjänimi " +user+" on jo käytössä.";
				userOK = false;
			}					
		}		
		
		//if a match is found, an error-message is shown and user is redirected back to adduser.jsp
		//if there is no user of the same name, user name and encrypted password are added to database 
		if(userOK == true) {
			Users newUser = new Users(user, encpass);
			addUser(newUser);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Käyttäjä "+user+ " on lisätty onnistuneesti tietokantaan.');");	
			out.println("location='jsp/adduser.jsp';");
			out.println("</script>");			
			RequestDispatcher rd = request.getRequestDispatcher("jsp/adduser.jsp");	    	
	    	rd.forward(request, response);
		}
		else{
			request.setAttribute("addfailed", failed);
			request.setAttribute("user", user);
			request.setAttribute("pwd", password);	
			RequestDispatcher rd = request.getRequestDispatcher("jsp/adduser.jsp");			
			rd.forward(request, response);
		}			 
		
		out.close();
	}
	
	// this method calls a rest-service, that reads the users and their passwords from database
	//it returns a list of Users-objects  
    private List<Users> readValidUsers() {
		String uri = "http://127.0.0.1:8080/rest/restdao/readusers";		
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Create a GenericType to be able to get List of objects
		GenericType<List<Users>> genericList = new GenericType<List<Users>>() {};		
		List<Users> returnedList=b.get(genericList);
		return returnedList;
	}
    
    //this method creates an entity from a given object and passes it on using POST to adduser rest-service 
    private void addUser(Users user) {		
    	String uri = "http://127.0.0.1:8080/rest/restdao/adduser";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		//Here we create an Entity of a Fish object as JSON string format
		Entity<Users> e=Entity.entity(user,MediaType.APPLICATION_JSON);
		//Create a GenericType to be able to get List of objects		
		//Posting data (Entity<ArrayList<DogBreed>> e) to the given address
		b.post(e);		
	}
    
	//this method encrypts a given string using Base64-crypt
	private String encrypt(String str) {
	    try {
	        return Base64.encodeBase64String(str.getBytes("utf-8"));
	    } catch (UnsupportedEncodingException e) {
	        return "";
	    }
	}

}
