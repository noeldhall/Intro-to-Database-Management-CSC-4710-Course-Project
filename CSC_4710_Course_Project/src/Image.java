/**
 * 
 */

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
	protected String tags;
	
	public Image() {
		
	}
	
	public Image(int id, String url, String description, Timestamp timeStamp) {
		this.id=id;
		this.url=url;
		this.description=description;
		this.timeStamp=timeStamp;
	}
	
	public Image(int id, String url, String description, Timestamp timeStamp, String tags)
	{
	this(id,url,description,timeStamp);
	this.tags=tags;
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
	
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags=tags;
	}
}
