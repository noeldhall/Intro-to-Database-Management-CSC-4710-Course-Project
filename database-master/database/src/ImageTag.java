/**
 * 
 */

/**
 * @author Noel Hall
 *
 */
public class ImageTag {
	protected int imageId;
	protected String tag;
	
	public ImageTag(int imageId, String tag) {
		this.imageId=imageId;
		this.tag=tag;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	
}
