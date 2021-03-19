import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FollowDAO extends DAO {

	public FollowDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(Follow follow) throws SQLException {
	    	connect_func();         
			String sql = "insert into  follow(followingEmail, followerEmail) values (?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, follow.followingEmail);
			preparedStatement.setString(2, follow.followerEmail);
			


//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }  
	 
	    public boolean delete(String followerEmail, String followingEmail) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM community_page WHERE follower_email = "+followerEmail+" AND following_email="+followingEmail;        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
}
