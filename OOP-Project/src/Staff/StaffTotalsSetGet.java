/*
 * Movies Class
 * Movie Entity
 * Able to add, update, delete a record from the MV_MOVIES table
 */
package Staff;

/**
 *
 * @author Roberto Gomez
 */
public class StaffTotalsSetGet {
    
    private int employeeID;
    private String firstName;
    private String lastName;
    private String title;
    private String phoneNumber;
    private String username;
    private String password;

    private byte[] staffPicture;
    private int totalCount;
    
    public StaffTotalsSetGet(int ptotalcount){

        this.totalCount  = ptotalcount;

    }
    
    public int gettotalcount()
    {
        return totalCount;
    }
     
    public int getmemployeeid()
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
       
    public byte[] getstaffpicture()
    {
        return staffPicture;
    }
}
