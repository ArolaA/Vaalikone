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
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;

import org.apache.commons.codec.binary.Base64;

import data.Users;

/**
 * Servlet implementation class CheckLoginRest
 */
@WebServlet("/checkloginrest")
public class CheckLoginRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginRest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// get the session object
        HttpSession session = request.getSession();
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
		List<Users> list = null;
		
		//get the list of valid users using a proper method
		list = readValidUsers();
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String failed = "";
		
		//loop through the valid users -list and check if there is a match in user and decrypted password
		//if a match is found, user name is set as session attribute "user" and response is forwarded to the admin-page
		for(int i=0 ; i < list.size(); i++)
		{	
			if(user.equals(list.get(i).getUser().toString()) && password.contentEquals(decrypt(list.get(i).getPass().toString()))){
				session.setAttribute("user", user);	
		    	response.sendRedirect("jsp/admin.jsp?name=" + user);	
			}
			else{		    	
		    	failed = "Väärä käyttäjätunnus tai salasana!!!";		    	
		    }									
		}		
		RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
		request.setAttribute("login", failed);
		request.setAttribute("user", user);
    	rd.include(request, response);		
		out.close();		
	}
	
	// this method calls a rest-service, that reads the user names and passwords from the database
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
    
    //this method decrypts the given string using Base64-crypt
    private String decrypt(String str) {
        Base64 base64 = new Base64();		
		try {
			return new String(base64.decode(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}		    
    }    
    

}
