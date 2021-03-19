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
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;
    private RegisteredUserDAO registeredUserDAO;
    private InitDB initDb;
    private LoginDAO loginDAO;
   //CommentsDAO cD,FollowDAO fD,ImageTagDAO fPD,LikeDAO lD,ImageDAO iD
    private CommentsDAO commentsDAO;
    private FollowDAO followDAO;
    private ImageTagDAO imageTagDAO;
    private LikeDAO likeDAO;
    private ImageDAO imageDAO;
    

 
    public void init() {
        peopleDAO = new PeopleDAO(); 
        try {
			registeredUserDAO=new RegisteredUserDAO();
			loginDAO=new LoginDAO();
			initDb=new InitDB();
			followDAO=new FollowDAO();
			imageTagDAO=new ImageTagDAO();
			commentsDAO=new CommentsDAO();
			likeDAO=new LikeDAO();
		        imageDAO=new ImageDAO();
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
            case "/register":
            	registerUser(request,response);
            	break;
            case "/authenticate":
            	authenticate(request,response);
            	break;
            case "/login":
            	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");       
                dispatcher.forward(request, response);
                break;
            case "/initialize":
            //	initDb=new InitDB();
            	//CommentsDAO cD,FollowDAO fD,ImageTagDAO fPD,LikeDAO lD,ImageDAO iD
            	initDb.initializeDatabase(commentsDAO,followDAO,imageTagDAO, likeDAO,imageDAO);
            	 dispatcher = request.getRequestDispatcher("accountView.jsp");       
                dispatcher.forward(request, response);
            	break;
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        if (loginDAO.validate(loginBean)==true)
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("root.jsp");       
            dispatcher.forward(request, response);
        }
        else
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");       
            dispatcher.forward(request, response);
        }
    }
    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String email=request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthday = request.getParameter("birthday");
        String gender =request.getParameter("gender");
        String password=request.getParameter("password");
        String confirmation=request.getParameter("confirmation");
        RegisteredUser newUser = new RegisteredUser(firstName, lastName, email,password,gender,birthday,0,0);
        registeredUserDAO.insert(newUser);
        response.sendRedirect("login");  // The sendRedirect() method works at client side and sends a new request
    }
    
    private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<People> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        People existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
 
    }
 
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        People newPeople = new People(name, address, status);
        peopleDAO.insert(newPeople);
        response.sendRedirect("list");  // The sendRedirect() method works at client side and sends a new request
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        System.out.println(id);
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        
        System.out.println(name);
        
        People people = new People(id,name, address, status);
        peopleDAO.update(people);
        response.sendRedirect("list");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //People people = new People(id);
        peopleDAO.delete(id);
        response.sendRedirect("list"); 
    }

}