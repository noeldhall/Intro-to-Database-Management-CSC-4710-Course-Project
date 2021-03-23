import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
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
            case "/logout":
            	HttpSession session=request.getSession();
				session.invalidate();
				 dispatcher = request.getRequestDispatcher("login.jsp");       
                dispatcher.forward(request, response);
            	break;
            case "/initialize":
            //	initDb=new InitDB();
            	//CommentsDAO cD,FollowDAO fD,ImageTagDAO fPD,LikeDAO lD,ImageDAO iD
            	initDb.initializeDatabase(commentsDAO,followDAO,imageTagDAO, likeDAO,imageDAO);
            	 dispatcher = request.getRequestDispatcher("accountView.jsp");       
                dispatcher.forward(request, response);
            	break;
            case "/feedPage":
            	displayFeedImages(request,response);
            	break;
            case "/community":
            	displayUsers(request,response);
            	
            	break;
            case "/postForm":
            	postImage(request,response);
            	break;
            case "/like":
            	addLike(request,response);
            	break;
            case "/dislike":
            	removeLike(request,response);
            	break;
            case "/deleteImage":
            	deleteImage(request,response);
            
            	break;
            case "/updateImage":
            	updateImage(request,response);
            	break;
            case "/follow":
            	addFollowRelation(request,response);
            	break;
            case "/unfollow": 
            	removeFollowRelation(request,response);
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
            	//listPeople(request, response);           	
                break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void removeFollowRelation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();

		String followingEmail=(String)request.getParameter("followingEmail");
		String followerEmail=(String)session.getAttribute("username");
		followDAO.removeFollowerRelation(followerEmail,followingEmail);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/community");       
        dispatcher.forward(request, response);
	}

	private void addFollowRelation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();

		String followingEmail=(String) request.getParameter("followingEmail");
		String followerEmail=(String)session.getAttribute("username");
		followDAO.addFollowerRelation(followerEmail,followingEmail);
		registeredUserDAO.incrementFollowerCount(followingEmail);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/community");       
        dispatcher.forward(request, response);
	}

	private void displayUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();

    	List<RegisteredUser> users=registeredUserDAO.loadUsers((String)session.getAttribute("username"));
    	request.setAttribute("userList", users);
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("CommunityPage.jsp");       
         dispatcher.forward(request, response);
	}

	private void updateImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String description=request.getParameter("description");
		String imageId=request.getParameter("imageId");
		long millis=System.currentTimeMillis();  
		Timestamp postTime=new Timestamp(millis);
		Date date=new Date(postTime.getTime());
		imageDAO.update(imageId, description, postTime, date);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
        dispatcher.forward(request, response);
	}

	private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String imageId=request.getParameter("imageId");
			imageDAO.delete(imageId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
        dispatcher.forward(request, response);
	}

	private void postImage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	HttpSession session = request.getSession();
    	String email=(String)session.getAttribute("username");
    	System.out.println(email);
		String url=request.getParameter("url");
		String description=request.getParameter("description");
		String tags=request.getParameter("tags");
		long millis=System.currentTimeMillis();  
		Timestamp postTime=new Timestamp(millis);
		Date date=new Date(postTime.getTime());
		//Generating image id
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		Image i=new Image(n,url,description,email,date,postTime);
		imageDAO.insert(i);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
        dispatcher.forward(request, response);
	}

	private void removeLike(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	HttpSession session = request.getSession();
    	
		String likeEmail=( String) session.getAttribute("username");
		String imageId=request.getParameter("imageId");
		likeDAO.delete(likeEmail,imageId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
        dispatcher.forward(request, response);
	}

	private void addLike(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	HttpSession session = request.getSession();
    	String likeEmail=( String) session.getAttribute("username");
    	//change imageId and method applications to type int?
		String imageId=request.getParameter("imageId");
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		Like l=new Like(likeEmail,Integer.parseInt(imageId),date);
		likeDAO.insert(l);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
        dispatcher.forward(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);


        if (loginDAO.validate(loginBean)==true)
        {
        	HttpSession session = request.getSession();
        	session.setAttribute("username", username);
        	if(username.compareTo("root@wayne.edu")==0) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("root.jsp");       
            dispatcher.forward(request, response);
        	}
        	else
        	{
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/feedPage");       
              dispatcher.forward(request, response);
        	}
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
    
    private void displayFeedImages(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
    	HttpSession session = request.getSession();

    	List<Image> feedImages=imageDAO.loadFeedImages((String) session.getAttribute("username"));
    	request.setAttribute("listImages", feedImages);
    	request.setAttribute("username",session.getAttribute("username"));
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("feedPage.jsp");       
         dispatcher.forward(request, response);
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