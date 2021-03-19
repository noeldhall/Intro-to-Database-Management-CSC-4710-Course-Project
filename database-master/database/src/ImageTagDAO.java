import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageTagDAO extends DAO {

	public ImageTagDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	 public boolean insert(ImageTag imageTag) throws SQLException {
	    	connect_func();         
			String sql = "insert into  image_tag(image_id, tag) values (?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, imageTag.imageId);
			preparedStatement.setString(2, imageTag.tag);
			


//			preparedStatement.executeUpdate();
			
	        boolean rowInserted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowInserted;
	    }  
	 
	    public boolean delete(String imageId, String tag) throws SQLException {
	    	//changed id param to name in sql statement
	        String sql = "DELETE FROM community_page WHERE image_id = "+imageId+" AND tag="+tag;        
	        connect_func();
	         
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setString();
	         
	        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
	        preparedStatement.close();
//	        disconnect();
	        return rowDeleted;     
	    }
}
