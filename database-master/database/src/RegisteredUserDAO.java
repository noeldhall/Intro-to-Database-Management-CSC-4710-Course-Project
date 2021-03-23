

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisteredUserDAO")
public class RegisteredUserDAO extends DAO {

	public RegisteredUserDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	 public boolean insert(RegisteredUser registeredUser) throws SQLException {
	    	connect_func(); 
//	    	preparedStatement=connect.prepareStatement("CREATE TABLE IF NOT EXISTS registered_user ("
//					+ "name varchar(255) NOT NULL, password varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255),"
//					+ " gender varchar(255), birthday varchar(255),"
//					+ "PRIMARY KEY(name)"
//					+ ")");
	    	PreparedStatement create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS registered_user("
					+ " password varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255),"
					+ " gender varchar(255), birthday varchar(255), num_of_followers varchar(255), num_of_followings varchar(255),"
					+ "PRIMARY KEY(email)"
					+ ");");
			create.executeUpdate();
//			preparedStatement.executeUpdate();
			String sql = "insert ignore into  registered_user( password, email,first_name,last_name,gender,birthday,num_of_followers,num_of_followings) values (?, ?, ?,?,?,?,?,?);";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, registeredUser.password);
			preparedStatement.setString(2, registeredUser.email);
			preparedStatement.setString(3, registeredUser.firstName);
			preparedStatement.setString(4, registeredUser.lastName);
			preparedStatement.setString(5, registeredUser.gender);
			preparedStatement.setString(6, registeredUser.birthday);
			preparedStatement.setInt(7, registeredUser.numOfFollowers);
			preparedStatement.setInt(8, registeredUser.numOfFollowings);



//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }     
	     
	 	public boolean authenticate() throws SQLException{
	 		connect_func();
	 		
	 		return true;
	 		
	 	}
	 	
	 	public List<RegisteredUser> loadUsers(String currentUser) throws SQLException {
	        List<RegisteredUser> users = new ArrayList<RegisteredUser>(); 
	        String rootUser="root@wayne.edu";
	        connect_func();
	        PreparedStatement create=connect.prepareStatement("SELECT * FROM registered_user");
		        
		        ResultSet resultSet = create.executeQuery();
		      // (String firstName,String lastName, String email, String password, String gender, String birthday, int numOfFollowers, int numOfFollowings) {
		        while (resultSet.next()) {
		        	if(currentUser.compareTo(resultSet.getString("email"))!=0&&rootUser.compareTo(resultSet.getString("email"))!=0) {
		            String firstName = resultSet.getString("first_name");
		            String lastName = resultSet.getString("last_name");
		            String email = resultSet.getString("email");
		            String password=resultSet.getString("password");
		            String gender=resultSet.getString("gender");
		            String birthday=resultSet.getString("birthday");
		            int numFollowers=resultSet.getInt("num_of_followers");
		            int numFollowing=resultSet.getInt("num_of_followings");
		          
		            
		            
		             
		            RegisteredUser r = new RegisteredUser(firstName, lastName, email,password,gender,birthday,numFollowers,numFollowing);
		            users.add(r);}
		        }    
		        resultSet.close();
	        return users;
	 	}
	 
	    public boolean delete(String registeredUserName) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM registered_user WHERE first_name = '"+registeredUserName+"'";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	     //TODO: fix implementation
	    public boolean update(RegisteredUser registeredUser) throws SQLException {
	        String sql = "update registered_user set password =?,email = ?, first_name= ?, last_name= ?, gender= ?, birthday= ? where email = ?";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	     //   preparedStatement.setString(1, registeredUser.name);
	        preparedStatement.setString(2, registeredUser.password);
	        preparedStatement.setString(3, registeredUser.email);
	        preparedStatement.setString(3, registeredUser.firstName);
	        preparedStatement.setString(3, registeredUser.lastName);
	        preparedStatement.setString(3, registeredUser.gender);
	        preparedStatement.setString(3, registeredUser.birthday);

	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowUpdated;     
	    }
		
	    public RegisteredUser getRegisteredUser(String name) throws SQLException {
	    	RegisteredUser registeredUser = null;
	        String sql = "SELECT * FROM registered_user WHERE email = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, name);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	        	String sFirstName=resultSet.getString("first_name");
	        	String sLastName=resultSet.getString("last_name");
				String sPassword = resultSet.getString("password");
	            String sEmail = resultSet.getString("email");
	           
	            String sGender = resultSet.getString("gender");
	            String sBirthday = resultSet.getString("birthday");
	            int sNumOfFollowers=resultSet.getInt("num_of_followers");
	            int sNumOfFollowing=resultSet.getInt("num_of_following");


	             
	            registeredUser = new RegisteredUser( sFirstName,sLastName, sEmail,sPassword,sGender,sBirthday,sNumOfFollowers,sNumOfFollowing );
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return registeredUser;
	    }
		public void incrementFollowerCount(String followingEmail) throws SQLException {
			// TODO Auto-generated method stub
			  connect_func();
			String sql="SELECT * FROM registered_user WHERE email = ?";
			 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, followingEmail);
		        int followerCount=0;
		        ResultSet resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				  followerCount=resultSet.getInt("num_of_followers");
			 }
		        resultSet.close();
		        followerCount+=1;
		        sql = "update registered_user set num_of_followers= ? where email = ?";
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		     //   preparedStatement.setString(1, registeredUser.name);
		        preparedStatement.setInt(1, followerCount);
		        preparedStatement.setString(2, followingEmail);
		       		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

	        
	       
		}
		
		public void decrementFollowerCount(String followingEmail) throws SQLException {
			// TODO Auto-generated method stub
			  connect_func();
			String sql="SELECT * FROM registered_user WHERE email = ?";
			 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, followingEmail);
		        int followerCount=0;
		        ResultSet resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				  followerCount=resultSet.getInt("num_of_followers");
			 }
		        resultSet.close();
		        followerCount-=1;
		        sql = "update registered_user set num_of_followers= ? where email = ?";
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		     //   preparedStatement.setString(1, registeredUser.name);
		        preparedStatement.setInt(1, followerCount);
		        preparedStatement.setString(2, followingEmail);
		       		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

	        
	       
		}
		
		public void incrementFollowingCount(String followerEmail) throws SQLException {
			// TODO Auto-generated method stub
			  connect_func();
			String sql="SELECT * FROM registered_user WHERE email = ?";
			 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, followerEmail);
		        int followingCount=0;
		        ResultSet resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				  followingCount=resultSet.getInt("num_of_followings");
			 }
		        resultSet.close();
		        followingCount+=1;
		        sql = "update registered_user set num_of_followings= ? where email = ?";
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		     //   preparedStatement.setString(1, registeredUser.name);
		        preparedStatement.setInt(1, followingCount);
		        preparedStatement.setString(2, followerEmail);
		       		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

	        
	       
		}
		
		public void decrementFollowingCount(String followerEmail) throws SQLException {
			// TODO Auto-generated method stub
			  connect_func();
			String sql="SELECT * FROM registered_user WHERE email = ?";
			 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		        preparedStatement.setString(1, followerEmail);
		        int followingCount=0;
		        ResultSet resultSet = preparedStatement.executeQuery();
			 if (resultSet.next()) {
				  followingCount=resultSet.getInt("num_of_followings");
			 }
		        resultSet.close();
		        followingCount-=1;
		        sql = "update registered_user set num_of_followings= ? where email = ?";
		        
		        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		     //   preparedStatement.setString(1, registeredUser.name);
		        preparedStatement.setInt(1, followingCount);
		        preparedStatement.setString(2, followerEmail);
		       		         
		        boolean rowUpdated = preparedStatement.executeUpdate() > 0;

	        
	       
		}
}
