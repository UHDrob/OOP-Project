/*
 * STAFF Class
 */
package Staff;

/**
 *
 * @author Roberto Gomez
 */
public class Staff {
    
    private String employeeID;
    private String firstName;
    private String lastName;
    private String title;
    private String phoneNumber;
    private String username;
    private String password;

            
    public Staff(String pemployeeid,  String pfirstname, String plastname, String pemployeetitle, 
            String pphonenumber,  String pusername, String ppassword){
        this.employeeID = pemployeeid;
        this.firstName = pfirstname;
        this.lastName = plastname;
        this.title = pemployeetitle;
        this.phoneNumber = pphonenumber;
        this.username = pusername;
        this.password = ppassword;
    }
    
    public String getemployeeid()
    {
        return employeeID;
    }
    
    public String getfirstname()
    {
        return firstName;
    }
    
    public String getlastname()
    {
        return lastName;
    }
    
    public String gettitle()
    {
        return title;
    }
    
    public String getphonenumber()
    {
        return phoneNumber;
    }
    
    public String getusername()
    {
        return username;
    }
    
    public String getpassword()
    {
        return password;
    }

}
