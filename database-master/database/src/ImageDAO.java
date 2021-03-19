/**
 * 
 */


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;

/**
 * @author Noel Hall
 *
 */
@WebServlet("/ImageDAO")
public class ImageDAO extends DAO {

	public ImageDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(Image image) throws SQLException {
	    	connect_func();         
			String sql = "insert into  image(image_id, url,description,post_user,post_date, time_stamp) values (?,?, ?, ?,?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, image.id);
			preparedStatement.setString(2, image.url);
			preparedStatement.setString(3, image.description);
			preparedStatement.setString(4, image.postUser);
			preparedStatement.setDate(5, image.postDate);

			preparedStatement.setTimestamp(6, image.timeStamp);


//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }     
	     
	    public boolean delete(String imageId) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM community_page WHERE id = "+imageId;        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	  
	    //TODO: update for image
//	    public boolean update(Image image) throws SQLException {
//	        String sql = "update community_page set url =?,description = ?, time_stamp= ?, tags= ? where id = ?";
//	        connect_func();
//	        
//	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
//	     //   preparedStatement.setString(1, image.name);
//	        preparedStatement.setString(1, image.url);
//	        preparedStatement.setString(2, image.description);
//	        preparedStatement.setTimestamp(3, image.timeStamp);
//
//	         
//	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
//	        preparedStatement.close();
////	        disconnect();
//	        return rowUpdated;     
//	    }
		
	    public Image getImage(int id) throws SQLException {
	    	Image image = null;
	        String sql = "SELECT * FROM community_page WHERE id = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setInt(1, id);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            int sId = resultSet.getInt("id");
	            String sUrl = resultSet.getString("url");
	            String sDescription = resultSet.getString("description");
	            String sPostUser = resultSet.getString("postUser");
	            Date sPostDate = resultSet.getDate("postDate");

	            Timestamp sTimeStamp=resultSet.getTimestamp("time_stamp");
	             
	            image = new Image(sId, sUrl,sDescription,sPostUser,sPostDate,sTimeStamp );
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return image;
	    }

}
