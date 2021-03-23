import java.sql.Date;

public class Like {
protected String email;
protected int imageId;
protected Date date;

public Like(String email, int imageId, Date date) {
	this.email=email;
	this.imageId=imageId;
	this.date=date;
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


}
