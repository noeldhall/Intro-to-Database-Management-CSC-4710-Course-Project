/**
 * 
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

/**
 * @author Noel Hall
 *
 */
@WebServlet("/FeedPageDAO")
public class FeedPageDAO extends DAO {

	public FeedPageDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(FeedPage feedPage) throws SQLException {
	    	connect_func();         
			String sql = "insert into  feed_page(image, id) values (?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, feedPage.image);
			preparedStatement.setInt(2, feedPage.id);
//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }     
	     
	    public boolean delete(int feedPageId) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM community_page WHERE id = "+feedPageId+"";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	     
	    public boolean update(FeedPage feedPage) throws SQLException {
	        String sql = "update community_page set image = ? where id = ?";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	     //   preparedStatement.setString(1, feedPage.name);
	        preparedStatement.setString(1, feedPage.image);
	        preparedStatement.setInt(2, feedPage.id);
	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowUpdated;     
	    }
		
	    public FeedPage getFeedPage(int id) throws SQLException {
	    	FeedPage feedPage = null;
	        String sql = "SELECT * FROM community_page WHERE id = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setInt(1, id);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            int sId = resultSet.getInt("id");
	            String sImage = resultSet.getString("image");
	             
	            feedPage = new FeedPage( sImage, sId );
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return feedPage;
	    }

}
