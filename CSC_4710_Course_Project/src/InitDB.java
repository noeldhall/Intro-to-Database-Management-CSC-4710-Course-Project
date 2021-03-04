/**
 * 
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.sql.Connection;
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
		PreparedStatement create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS registered_user("
				+ "name varchar(255) NOT NULL, password varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255),"
				+ " gender varchar(255), birthday varchar(255),"
				+ "PRIMARY KEY(name)"
				+ ")");
		create.executeUpdate();
		
	//	disconnect();
	}


	
	public void initializeDatabase(CommunityPageDAO cD,FeedbackDAO fD,FeedPageDAO fPD,ImageDAO iD) throws SQLException, FileNotFoundException {
		connect_func();
		
		PreparedStatement create=connect.prepareStatement("DROP TABLE IF EXISTS image;");
		create.executeUpdate();
		 create=connect.prepareStatement("DROP TABLE IF EXISTS community_page;");
		create.executeUpdate();
		 create=connect.prepareStatement("DROP TABLE IF EXISTS feed_page;");
		create.executeUpdate();
	 create=connect.prepareStatement("DROP TABLE IF EXISTS feedback;");
		create.executeUpdate();
			create= connect.prepareStatement("CREATE TABLE IF NOT EXISTS image("
				+ "id int , url varchar(255), description varchar(255), time_stamp varchar(255)"
				+ ",tags varchar(255), PRIMARY KEY(id)"
				+ ")");
			//NOT NULL AUTO_INCREMENT TODO: add to int

		create.executeUpdate();
		
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS community_page("
				+ "name varchar(255) NOT NULL, image varchar(255), search varchar(255)"
				
				+ ")");
		//FOREIGN KEY (name) REFERENCES registered_user(name) TODO: implement foreign key support
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS feed_page("
				+ "image varchar(255), id int "
			
				+ ")");
		//NOT NULL AUTO_INCREMENT TODO: add to int
		//	+ "FOREIGN KEY (id) REFERENCES image(id)" TODO: implement foreign key support
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS feedback("
				+ "likes int , post varchar(255) NOT NULL,"
				+ "rating int, PRIMARY KEY (post)"
				+ ")");
		create.executeUpdate();
		
		//Simulating random image timestamps
		//NOT NULL AUTO_INCREMENT TODO: add to int

		long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp postTime=new Timestamp(offset + (long)(Math.random() * diff));
		
		//Inserting 10 initial tuples per table
		Image i=new Image(56, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-1.jpg","none",postTime,"none");
				iD.insert(i);
				
		FeedPage f=new FeedPage("sunset 1",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(684, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 2",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(685, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 2",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(686, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 3",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(687, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 4",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(688, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 5",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(689, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 6",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(690, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 7",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(691, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 8",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(692, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 9",i.getId());
		fPD.insert(f);
		postTime=new Timestamp(offset + (long)(Math.random() * diff));
		i=new Image(693, "https://weneedfun.com/wp-content/uploads/2016/07/Most-Beautiful-Sunset-Pictures-2.jpg","none",postTime,"none");
		iD.insert(i);
		 f=new FeedPage("sunset 10",i.getId());
		fPD.insert(f);
	
		Feedback fb=new Feedback(12,"post1", "4.5");
		fD.insert(fb);
		fb=new Feedback(12,"post 2", "3.5");
		fD.insert(fb);
		fb=new Feedback(12,"post 3", "3.5");
		fD.insert(fb);
		fb=new Feedback(12,"post 4", "5");
		fD.insert(fb);
		fb=new Feedback(12,"post 5", "2.5");
		fD.insert(fb);
		fb=new Feedback(12,"post 6", "2.5");
		fD.insert(fb);
		fb=new Feedback(12,"post 7", "4");
		fD.insert(fb);
		fb=new Feedback(12,"post 8", "4");
		fD.insert(fb);
		fb=new Feedback(12,"post 9", "3");
		fD.insert(fb);
		fb=new Feedback(12,"post 10", "5");
		fD.insert(fb);
		
		CommunityPage c= new CommunityPage("user 1","image 1", "John");
		cD.insert(c);
		c= new CommunityPage("user 2","image 2", "Johkn");
		cD.insert(c);
		c= new CommunityPage("user 3","image 4", "Jane");
		cD.insert(c);
		c= new CommunityPage("user 4","image 3", "Jen");
		cD.insert(c);
		c= new CommunityPage("user 5","image 5", "Nate");
		cD.insert(c);
		c= new CommunityPage("user 6","image 6", "Ted");
		cD.insert(c);
		c= new CommunityPage("user 7","image 7", "Dave");
		cD.insert(c);
		c= new CommunityPage("user 8","image 8", "Samuel");
		cD.insert(c);
		c= new CommunityPage("user 9","image 9", "Millie");
		cD.insert(c);
		c= new CommunityPage("user 10","image 10", "Kate");
		cD.insert(c);
		
		
		
		
		
		disconnect();
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

