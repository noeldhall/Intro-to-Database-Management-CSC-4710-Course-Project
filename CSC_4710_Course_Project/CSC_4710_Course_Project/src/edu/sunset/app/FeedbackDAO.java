/**
 * 
 */
package edu.sunset.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

/**
 * @author Noel Hall
 *
 */
@WebServlet("/FeedbackDAO")
public class FeedbackDAO extends DAO {

	public FeedbackDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 public boolean insert(Feedback feedback) throws SQLException {
	    	connect_func();         
			String sql = "insert into  feedback(likes, post, rating) values (?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, feedback.likes);
			preparedStatement.setString(2, feedback.post);
			preparedStatement.setString(3, feedback.rating);
//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }     
	     
	    public boolean delete(String feedbackPost) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM feedback WHERE post = '"+feedbackPost+"'";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	     
	    public boolean update(Feedback feedback) throws SQLException {
	        String sql = "update feedback set likes =?,rating = ? where post = ?";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	     //   preparedStatement.setString(1, feedback.name);
	        preparedStatement.setInt(1, feedback.likes);
	        preparedStatement.setString(2, feedback.rating);
	        preparedStatement.setString(3, feedback.post);
	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowUpdated;     
	    }
		
	    public Feedback getFeedback(String post) throws SQLException {
	    	Feedback feedback = null;
	        String sql = "SELECT * FROM feedback WHERE post = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, post);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            String sPost = resultSet.getString("post");
	            int sLikes = resultSet.getInt("likes");
	            String sRating = resultSet.getString("rating");
	             
	            feedback = new Feedback( sLikes, sPost,sRating );
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return feedback;
	    }


	
}
