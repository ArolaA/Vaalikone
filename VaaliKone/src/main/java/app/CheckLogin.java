package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import data.LoginInfo;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/checklogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the session object
        HttpSession session = request.getSession();
        
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("user");
	    String password = request.getParameter("password");
	    
	    //create a new instance of logininfo-class using the given parameters
	    LoginInfo logininfo = new LoginInfo(user, password);
	    
	    //check if the login information is correct using the check-method from the LoginDao-class
	    //if the login information is correct, set the given user name as a session attribute user
	    //and redirect user to a page called welcome.jsp
	    if(LoginDao.check(logininfo)==true)
	    {
	    	session.setAttribute("user", user);	
	    	response.sendRedirect("jsp/admin.jsp?name=" + user);	    		    	
	    }
	    else
	    {
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
	    	String failed = "V‰‰r‰ k‰ytt‰j‰tunnus tai salasana!";
	    	request.setAttribute("login", failed);
	    	rd.include(request, response);
	    }
	    
	    out.close();
	    
	}

}
