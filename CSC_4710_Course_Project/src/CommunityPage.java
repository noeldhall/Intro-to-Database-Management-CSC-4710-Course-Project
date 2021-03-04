/**
 * 
 */


/**
 * @author Noel Hall
 *
 */
public class CommunityPage  {
	protected String search;
	protected String image;
	protected String name;
	
	public CommunityPage() {}
	
	public CommunityPage(String search, String image, String name) {
		this.search=search;
		this.name=name;
		this.image=image;
	}
	
	public void setSearch(String search) {
		this.search=search;
	}
	public String getSearch() {
		return search;
	}
	
	public void setImage(String image) {
		this.image=image;
		
	}
	public String getImage() {
		return image;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {return name;}
}
