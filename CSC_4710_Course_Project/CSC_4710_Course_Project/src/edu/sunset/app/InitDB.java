/**
 * 
 */
package edu.sunset.app;

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

	public InitDB() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	protected Connection connect = null;

	
	public void initializeDatabase() throws SQLException, FileNotFoundException {
		connect_func();
		PreparedStatement create= connect.prepareStatement("CREATE TABLE IF NOT EXISTS image("
				+ "id int NOT NULL AUTO_INCREMENT, url varchar(255), description varchar(255), time_stamp varchar(255)"
				+ "tags varchar(255), PRIMARY KEY(id)"
				+ ")");
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS registered_user("
				+ "name varchar(255) NOT NULL, password varchar(255), email varchar(255), first_name varchar(255), last_name varchar(255),"
				+ ", gender varchar(255), birthday varchar(255)"
				+ "PRIMARY KEY(name)"
				+ ")");
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS community_page("
				+ "name varchar(255) NOT NULL, image varchar(255), search varchar(255),"
				+ "FOREIGN KEY (name) REFERENCES registered_user(name)"
				+ ")");
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS feed_page("
				+ "image varchar(255), id int NOT NULL AUTO_INCREMENT,"
				+ "FOREIGN KEY (id) REFERENCES image(id)"
				+ ")");
		create.executeUpdate();
		create=connect.prepareStatement("CREATE TABLE IF NOT EXISTS feedback("
				+ "likes int NOT NULL AUTO_INCREMENT, post varchar(255) NOT NULL,"
				+ "rating int, PRIMARY KEY (post)"
				+ ")");
		
		java.io.File file = new java.io.File("scores.txt");

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
}
