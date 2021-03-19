import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public  class LoginDAO extends DAO{
	public LoginDAO() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;


        try {

        	connect_func();
            PreparedStatement preparedStatement =
            connect.prepareStatement("select * from registered_user where email = ? and password = ? ");
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

           // System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
        }
        return status;
    }
}
