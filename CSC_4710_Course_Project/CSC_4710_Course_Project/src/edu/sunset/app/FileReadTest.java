package edu.sunset.app;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadTest {
		public static void main(String args[]) throws FileNotFoundException{
			java.io.File file = new java.io.File("C:\\Users\\Noel Hall\\eclipse-workspace\\CSC_4710_Course_Project\\src\\image_tuples.txt");

		    try (
		      // Create a Scanner for the file
		      Scanner input = new Scanner(file);
		    ) {
		      // Read data from a file
		      while (input.hasNext()) {
		        int id = input.nextInt();
		        String url = input.nextLine();
		        String description = input.nextLine();
		       // int score = input.nextInt();
		        System.out.println(
		          id + " " + url + " " + description );
		      }
		}
}
}
