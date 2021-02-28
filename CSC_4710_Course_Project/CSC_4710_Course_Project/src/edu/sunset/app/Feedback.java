/**
 * 
 */
package edu.sunset.app;

/**
 * @author Noel Hall
 *
 */
public class Feedback  {
 protected int likes;
 protected String post;
 protected String rating;
 
 public Feedback() {
	 
 }
 
 public Feedback(int likes, String post, String rating) {
	 this.likes=likes;
	 this.post=post;
	 this.rating=rating;
 }
 
 public int getLikes() {
	 return likes;
 }
 public void setLikes(int likes) {
	 this.likes=likes;
 }
 
 public String getRating() {
	 return rating;
 }
 public void setLikes(String rating) {
	 this.rating=rating;
 }

}
