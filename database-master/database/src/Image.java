/**
 * 
 */

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Noel Hall
 *
 */
public class Image {

	protected Timestamp timeStamp;
	protected int id;
	protected String url;
	protected String description;
	protected String postUser;
	protected Date postDate;
	
	
	public Image() {
		
	}
	
	public Image(int id, String url, String description,String postUser,Date postDate, Timestamp timeStamp) {
		this.id=id;
		this.url=url;
		this.description=description;
		this.timeStamp=timeStamp;
		this.postUser=postUser;
		this.postDate=postDate;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id=i;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPostUser() {
		return postUser;
	}

	public void setPostUser(String postUser) {
		this.postUser = postUser;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	

}
