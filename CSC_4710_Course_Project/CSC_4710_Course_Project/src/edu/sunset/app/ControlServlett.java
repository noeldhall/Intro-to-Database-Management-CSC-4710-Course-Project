/**
 * 
 */
package edu.sunset.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */

/**
 * @author Noel Hall
 *
 */
public class ControlServlett extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	    private FeedbackDAO feedbackDAO;
	    private FeedPageDAO feedPageDAO;
	    private ImageDAO imageDAO;
	    private CommunityPageDAO communityPageDAO;
	    private RegisteredUserDAO registeredUserDAO;
	 
	    public void init() {
	       
	        try {
				communityPageDAO=new CommunityPageDAO();
				 feedbackDAO = new FeedbackDAO();
			        feedPageDAO=new FeedPageDAO();
			        imageDAO=new ImageDAO();
			        registeredUserDAO=new RegisteredUserDAO();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	        try {
	            switch (action) {
	            case "/init":
	            	initializeDatabase;
	            	break;
//	            case "/new":
//	                showNewForm(request, response);
//	                break;
//	            case "/insert":
//	            	insertPeople(request, response);
//	                break;
//	            case "/delete":
//	            	deletePeople(request, response);
//	                break;
//	            case "/edit":
//	                showEditForm(request, response);
//	                break;
//	            case "/update":
//	            	updatePeople(request, response);
//	                break;
	            	default:          	
	            	listPeople(request, response);           	
	                break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
	    }
}
