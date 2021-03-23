/**
 * 
 */


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	        String sql = "DELETE FROM image WHERE image_id = ?";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, imageId);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	  
	    public boolean update(String id, String desc, Timestamp t, Date d) throws SQLException {
	        String sql = "update image set description= ?, post_date= ?, time_stamp= ? where image_id = ?";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, desc);
	        preparedStatement.setDate(2, d);
	        preparedStatement.setTimestamp(3, t);
	        preparedStatement.setString(4, id);

	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowUpdated;     
	    }		
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
	    
	    public List<Image> loadFeedImages(String username) throws SQLException {
	        List<Image> feedImages = new ArrayList<Image>();  
	        connect_func();

	        PreparedStatement create=connect.prepareStatement("SELECT * FROM image WHERE post_user= ?");
	    create.setString(1,username);
	        
	        ResultSet resultSet = create.executeQuery();
	      //  int id, String url, String description,String postUser,Date postDate, Timestamp timeStamp
	        while (resultSet.next()) {
	            int id = resultSet.getInt("image_id");
	            String url = resultSet.getString("url");
	            String description = resultSet.getString("description");
	            String postUser = resultSet.getString("post_user");
	            Date postDate=resultSet.getDate("post_date");
	            Timestamp timestamp=resultSet.getTimestamp("time_stamp");
	            
	          
	            
	            
	             
	            Image i = new Image(id,url, description, postUser,postDate,timestamp);
	            feedImages.add(i);
	        }    
	        resultSet.close();
	        //Getting images from followed users for feed
	        ArrayList<String> followedUsers=new ArrayList<String>();
            create=connect.prepareStatement("SELECT * FROM follow WHERE follower_email= ? ;");
            create.setString(1,"johnd@gmail.com");
          ResultSet  resultSet2=create.executeQuery();
            while (resultSet2.next()) {
            	String followingEmail=resultSet2.getString("following_email");
            	System.out.println(followingEmail);
            	followedUsers.add(followingEmail);
            }
            resultSet2.close();

            
            for(int s=0;s< followedUsers.size();s++)
            {
            	create=connect.prepareStatement("SELECT * FROM image WHERE post_user= ?");
        	    create.setString(1,followedUsers.get(s));
        	    
        	   ResultSet resultSet4 = create.executeQuery();
      	        while (resultSet4.next()) {
      	            int id = resultSet4.getInt("image_id");
      	            String url = resultSet4.getString("url");
      	            String description = resultSet4.getString("description");
      	            String postUser = resultSet4.getString("post_user");
      	            Date postDate=resultSet4.getDate("post_date");
      	            Timestamp timestamp=resultSet4.getTimestamp("time_stamp");
      	            
      	          
      	            
      	            
      	             
      	            Image i = new Image(id,url, description, postUser,postDate,timestamp);
      	            feedImages.add(i);
      	        }    
      	      resultSet4.close();
            }
	        
            //TODO: add sorting to get image arraylist in reverse-chronological order
            
            //Getting each image's number of likes
            for (int i=0;i<feedImages.size();i++)
            {
            	 create=connect.prepareStatement("SELECT * FROM likes WHERE image_id= ? ");
                 create.setInt(1,feedImages.get(i).getId());
                 ResultSet resultSet3=create.executeQuery();
                 while (resultSet3.next()) {
                	 feedImages.get(i).setLikeCount(feedImages.get(i).getLikeCount()+1);
                 }
                 resultSet3.close();

            }
            
	       // statement.close();         
	        disconnect();        
	        return feedImages;
	    }

}
