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
@WebServlet("/CommunityPageDAO")
public class CommunityPageDAO extends DAO {

	public CommunityPageDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 public boolean insert(CommunityPage communityPage) throws SQLException {
	    	connect_func();         
			String sql = "insert into  community_page(name, image, search) values (?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, communityPage.name);
			preparedStatement.setString(2, communityPage.image);
			preparedStatement.setString(3, communityPage.search);
//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }     
	     
	    public boolean delete(String communityPageName) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM community_page WHERE name = '"+communityPageName+"'";        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
	     
	    public boolean update(CommunityPage communityPage) throws SQLException {
	        String sql = "update community_page set image =?,search = ? where name = ?";
	        connect_func();
	        
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	     //   preparedStatement.setString(1, communityPage.name);
	        preparedStatement.setString(1, communityPage.image);
	        preparedStatement.setString(2, communityPage.search);
	        preparedStatement.setString(3, communityPage.name);
	         
	        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowUpdated;     
	    }
		
	    public CommunityPage getCommunityPage(String name) throws SQLException {
	    	CommunityPage communityPage = null;
	        String sql = "SELECT * FROM community_page WHERE name = ?";
	         
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, name);
	         
	        ResultSet resultSet = preparedStatement.executeQuery();
	         
	        if (resultSet.next()) {
	            String sName = resultSet.getString("name");
	            String sImage = resultSet.getString("image");
	            String sSearch = resultSet.getString("search");
	             
	            communityPage = new CommunityPage( sName, sImage,sSearch );
	        }
	         
	        resultSet.close();
	        statement.close();
	         
	        return communityPage;
	    }

}
