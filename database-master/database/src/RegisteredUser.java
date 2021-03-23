/**
 * 
 */


/**
 * @author Noel Hall
 *
 */
public class RegisteredUser {
	//get rid of name
	protected String email;
	protected String password;
	protected String firstName;
	protected String lastName;
    protected String gender;
    protected String birthday;
    protected int numOfFollowers;
    protected int numOfFollowings;
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RegisteredUser() {}
    
    public RegisteredUser(String firstName,String lastName, String email, String password, String gender, String birthday, int numOfFollowers, int numOfFollowings) {
    	this.firstName=firstName;
    	this.lastName=lastName;
    	this.email=email;
    	this.password=password;
    	this.gender=gender;
    	this.birthday=birthday;
    	this.numOfFollowings=numOfFollowings;
    	this.numOfFollowers=numOfFollowers;
    	int i=0;
    
    	
    	
    }  
    //fix to consider first and last name separate
  
    
    public void setEmail(String email) {
    	this.email=email;
    }
    public String getEmail() {
    	return email;
    }
    
    public void setPassword (String password) {
    	this.password=password;
    }
    public String getPassword() {
    	return password;
    }
    
    public void setGender(String gender) {
    	this.gender=gender;
    }
    public String getGender() {
    	return gender;
    	
    }
    
    public void setBirthday(String birthday) {
    	
    }
    public String getBirthday() {
    	return birthday;
    }
    
    public void setNumOfFollowers(int numOfFollowers) {
    	this.numOfFollowers=numOfFollowers;
    }
    public int getNumOfFollowers() {
    	return numOfFollowers;
    }
    
    public void setNumOfFolllowings(int numOfFollowings) {
    	this.numOfFollowings=numOfFollowings;
    }
    public int getNumOfFollowings() {
    	return numOfFollowings;
    }
}
