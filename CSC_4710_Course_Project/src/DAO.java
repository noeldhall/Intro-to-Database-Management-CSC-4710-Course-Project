/**
 * 
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noel Hall
 *
 */
public class DAO {
	protected static final long serialVersionUID = 1L;
	protected Connection connect = null;
	protected Statement statement = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;
	
	//Creating tables if not already present in the database
	public DAO() throws SQLException {
		
	 }
	protected  void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
            		 .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
            				    + "user=john&password=pass1234");
  			     // .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			       //   + "useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
	}
	 protected void disconnect() throws SQLException {
	        if (connect != null && !connect.isClosed()) {
	        	connect.close();
	        }
	    }
	 
	
}
