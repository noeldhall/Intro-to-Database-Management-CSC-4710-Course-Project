/**
 * 
 */
package edu.sunset.app;

/**
 * @author Noel Hall
 *
 */
public class RegisteredUser {
	protected String name;
	protected String email;
	protected String password;
	protected String firstName;
	protected String lastName;
    protected String gender;
    protected String birthday;
    
    public RegisteredUser() {}
    
    public RegisteredUser(String name, String email, String password, String gender, String birthday) {
    	this.name=name;
    	this.email=email;
    	this.password=password;
    	this.gender=gender;
    	this.birthday=birthday;
    	int i=0;
    	firstName="";
    	while (name.charAt(i)!=' ')
    	{
    		firstName+=name.charAt(i);
    		i++;
    	}
    	lastName="";
    	while (i<name.length())
    	{
    		lastName+=name.charAt(i);
    	}
    	
    }   
    public void setName(String name) {
    	 this.name=name;
    }
    public String getName() {
    	return name;
    } 
    
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
    
    
}
