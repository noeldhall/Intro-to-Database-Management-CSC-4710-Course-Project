/**
 * 
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Noel Hall
 *
 */
public class InitDB extends DAO {

	//protected Connection connect = null;


	public InitDB() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
		
		connect_func();
		//drop  table once to create new format
//		PreparedStatement create=connect.prepareStatement("DROP TABLE IF EXISTS registered_user;");
//		create.executeUpdate();

		PreparedStatement create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS registered_user("
				+ " password varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255),"
				+ " gender varchar(255), birthday varchar(255), num_of_followers varchar(255), num_of_followings varchar(255),"
				+ "PRIMARY KEY(email)"
				+ ");");
		create.executeUpdate();
		
		//disconnect();
	}


	public void populateRegisteredUsers() throws SQLException{
		RegisteredUserDAO rD=new RegisteredUserDAO();
		// firstName, lastName,  email, password, gender,  birthday,  numOfFollowers,  numOfFollowings)
				RegisteredUser r=new RegisteredUser("John","Doe","johnd@gmail.com","john1234","male","1995-02-12",1,1);
				rD.insert(r);
				r=new RegisteredUser("Jane","Doe","janed@gmail.com","jane1234","female","1996-03-14",1,1);
				rD.insert(r);
				r=new RegisteredUser("Daniel","Esdee","daniele@yahoo.com","daniel1234","male","2000-05-20",1,1);
				rD.insert(r);
				r=new RegisteredUser("Walton","Wendall","waltonw@gmail.com","walton1234","male","1980-07-23",1,1);
				rD.insert(r);
				r=new RegisteredUser("Jenny","Smith","jennys@protonmail.com","jenny1234","female","1985-09-24",1,1);
				rD.insert(r);
				r=new RegisteredUser("Genesis","Topps","genesist@gmail.com","genesis1234","female","1998-10-10",1,1);
				rD.insert(r);
				r=new RegisteredUser("Darnell","Jackson","darnellj@aol.com","darnell1234","male","1970-01-12",2,0);
				rD.insert(r);
				r=new RegisteredUser("Manuel","Sanchez","manuels@protonmail.com","manuel1234","male","1989-12-09",1,1);
				rD.insert(r);
				r=new RegisteredUser("Lisa","Lu","lisal@gmail.com","lisa1234","female","1990-07-21",1,1);
				rD.insert(r);
				r=new RegisteredUser("Maria","Rosas","mariar@aol.com","maria1234","female","1976-11-07",1,1);
				rD.insert(r);
	}
	
	public void initializeDatabase(CommentsDAO cD,FollowDAO fD,ImageTagDAO fPD,LikeDAO lD,ImageDAO iD) throws SQLException, FileNotFoundException {
		connect_func();
		
		 PreparedStatement create=connect.prepareStatement("DROP TABLE IF EXISTS image_tag;");
			create.executeUpdate();
		create=connect.prepareStatement("DROP TABLE IF EXISTS comments;");
			create.executeUpdate();
			create=connect.prepareStatement("DROP TABLE IF EXISTS likes;");
			create.executeUpdate();
		 create=connect.prepareStatement("DROP TABLE IF EXISTS image;");
		create.executeUpdate();
		
		//uncomment when I figure out why it isn't working
		
		create=connect.prepareStatement("DROP TABLE IF EXISTS follow;");
		create.executeUpdate();
		
		//Creating ten initial users, run every time new registeredUser table is created
//	populateRegisteredUsers();
				
		
			create= connect.prepareStatement("CREATE TABLE IF NOT EXISTS image("
				+ "image_id int , url varchar(255), description varchar(255), post_user varchar(255) NOT NULL"
				+ ",post_date DATE, time_stamp varchar(255)"
				+ ", PRIMARY KEY(image_id), FOREIGN KEY (post_user) references registered_user(email)"
				+ ");");
			//NOT NULL AUTO_INCREMENT TODO: add to int

		create.executeUpdate();
		
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS image_tag("
				+ "image_id int, tag varchar(25),PRIMARY KEY(image_id),"
				+ "FOREIGN KEY (image_id) REFERENCES image(image_id));");
	
		//NOT NULL AUTO_INCREMENT TODO: add to int, not null to char
		create.executeUpdate();
		
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS comments("
				+ "email varchar(255),image_id int, description varchar(500),"
				+ "PRIMARY KEY(email,image_id), FOREIGN KEY (email) REFERENCES registered_user(email),"
				+ "FOREIGN KEY (image_id) REFERENCES image(image_id) );");
		//NOT NULL AUTO_INCREMENT TODO: add to int, not null to char email
create.executeUpdate();

		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS likes("
				+ "email varchar(255), image_id int,like_date DATE, PRIMARY KEY(email,image_id),"
				+ "FOREIGN KEY (email) REFERENCES registered_user(email),"
				+ "FOREIGN KEY (image_id) REFERENCES image(image_id));");
		//NOT NULL AUTO_INCREMENT TODO: add to int, char
		create.executeUpdate();
		
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS follow("
				+ "following_email varchar(255), follower_email varchar(255),"
				+ "FOREIGN KEY (following_email) REFERENCES registered_user(email),"
				+ "FOREIGN KEY (follower_email) REFERENCES registered_user(email))"
				+ ";");
		
		//NOT NULL AUTO_INCREMENT TODO: add to chars
		create.executeUpdate();
		//comment out if problematic, only run once, figure out proper syntax
//		create=connect.prepareStatement("CREATE ASSERTION at_most_five "+"\n"
//				+ " CHECK NOT EXISTS("+"\n"
//				+ "SELECT \n post_user, post_date"+"\n"
//				+ " FROM \n image "+"\n"
//				+ "GROUP BY  post_user, post_date "+"\n"
//				+ "HAVING COUNT(*) >5 "
//				+ ")");
//		create.executeUpdate();
		
//		create=connect.prepareStatement("CREATE ASSERTION at_most_three"
//				+ "CHECK NOT EXISTS (\n"
//				+ "SELECT \n email,"
//				+ "FROM \n like \n"
//				+ "GROUP BY email, like_date \n "
//				+ "HAVING COUNT(*) >3)");
//		create.executeUpdate();
		
//		//Simulating random image timestamps
//		//NOT NULL AUTO_INCREMENT TODO: add to int

		long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp postTime=new Timestamp(offset + (long)(Math.random() * diff));
		Date date=new Date(postTime.getTime());
		//Inserting 10 initial tuples per table
		// uncomment image insertions when ready
		Image i=new Image(56, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-1.jpg","none","janed@gmail.com",date,postTime);
				iD.insert(i);
		
	
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
				date=new Date(postTime.getTime());
		i=new Image(684, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none","johnd@gmail.com",date,postTime);
		iD.insert(i);
//		
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
	i=new Image(685, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-25.jpg","none","daniele@yahoo.com",date,postTime);
		iD.insert(i);
	
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(686, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-3.jpg","none","waltonw@gmail.com",date,postTime);
		iD.insert(i);

		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(687, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-4.jpg","none","jennys@protonmail.com",date,postTime);
		iD.insert(i);
		 
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(688, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-6.jpg","none","genesist@gmail.com",date,postTime);
		iD.insert(i);
		
		
		postTime=new Timestamp(offset + (long)(Math.random() * diff));		
		date=new Date(postTime.getTime());
		i=new Image(689, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-7.jpg","none","darnellj@aol.com",date,postTime);
		iD.insert(i);
		 
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(690, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-8.jpg","none","manuels@protonmail.com",date,postTime);
		iD.insert(i);
		
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(691, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-9.jpg","none","lisal@gmail.com",date,postTime);
		iD.insert(i);

		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		date=new Date(postTime.getTime());
		i=new Image(692, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-11.jpg","none","mariar@aol.com",date,postTime);
		iD.insert(i);
//		
//		postTime=new Timestamp(offset + (long)(Math.random() * diff));
//		i=new Image(693, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
//		iD.insert(i);
		 
	
		
		
		
		
		
		
		//line below causing a problem?
	//	disconnect();
	//	java.io.File file = new java.io.File("scores.txt");

//	    try (
//	      // Create a Scanner for the file
//	      Scanner input = new Scanner(file);
//	    ) {
//	      // Read data from a file
//	      while (input.hasNext()) {
//	        String firstName = input.next();
//	        String mi = input.next();
//	        String lastName = input.next();
//	        int score = input.nextInt();
//	        System.out.println(
//	          firstName + " " + mi + " " + lastName + " " + score);
//	      }
	    
	}
	
}

