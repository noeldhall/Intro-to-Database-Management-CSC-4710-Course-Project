import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeDAO extends DAO {

	public LikeDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(Like like) throws SQLException {
	    	connect_func();         
			String sql = "insert ignore into  likes(email, image_id,like_date) values (?, ?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, like.email);
			preparedStatement.setInt(2, like.imageId);
			preparedStatement.setDate(3, like.date);
			


//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }  
	 
	    public boolean delete(String Email, String imageId) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM likes WHERE email = ? AND image_id= ?";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1,Email);
	        preparedStatement.setString(2, imageId);
	        
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
}
