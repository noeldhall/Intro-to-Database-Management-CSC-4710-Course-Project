import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FollowDAO extends DAO {

	public FollowDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(Follow follow) throws SQLException {
	    	connect_func();         
			String sql = "replace into follow(following_email, follower_email) values (?, ?)";
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
	        String sql = "DELETE FROM follow WHERE follower_email= ? AND following_email= ?";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         preparedStatement.setString(1, followerEmail);
	         preparedStatement.setString(2, followingEmail);
	        
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }

		public void addFollowerRelation(String  follower, String followed) throws SQLException {
			// TODO Auto-generated method stub
			Follow f=new Follow(follower,followed);
			boolean b=insert(f);
			if (b==true)
			 {
				 RegisteredUserDAO rD=new RegisteredUserDAO();
				 rD.incrementFollowerCount(followed);
				 rD.incrementFollowingCount(follower);
			 }
		}

		public void removeFollowerRelation(String follower, String followed) throws SQLException {
			// TODO Auto-generated method stub
			boolean b=delete(follower,followed);
			if(b==true) {
				RegisteredUserDAO rD=new RegisteredUserDAO();
				rD.decrementFollowerCount(followed);
				rD.decrementFollowingCount(follower);
			}
		}
}
