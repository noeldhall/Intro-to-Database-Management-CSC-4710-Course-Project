/**
 * 
 */

/**
 * @author Noel Hall
 *
 */
public class Comments {

	protected String email;
	protected int imageId ;
	protected String description;
	
	public Comments(String email, int imageId, String description) {
		this.email=email;
		this.imageId=imageId;
		this.description=description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
