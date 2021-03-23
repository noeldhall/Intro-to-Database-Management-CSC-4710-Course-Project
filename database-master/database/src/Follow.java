
public class Follow {
	protected String followingEmail;
	protected String followerEmail;
	
	public Follow(String followerEmail,String followingEmail) {
		this.followerEmail=followerEmail;
		this.followingEmail=followingEmail;
	}
	public String getFollowingEmail() {
		return followingEmail;
	}
	public void setFollowingEmail(String followingEmail) {
		this.followingEmail = followingEmail;
	}
	public String getFollowerEmail() {
		return followerEmail;
	}
	public void setFollowerEmail(String followerEmail) {
		this.followerEmail = followerEmail;
	}
	
}
