/*
 * STAFF Class
 */
package Staff;

/**
 *
 * @author Roberto Gomez
 */
public class Staff {
    
    private int employeeID;
    private String firstName;
    private String lastName;
    private String employeeTitle;
    private String phoneNumber;

    private double salary;
    private String username;
    private String password;
    private byte[] staffPhoto;
            
    public Staff(int pemployeeid, int plocationidFK, String pfirstname, String plastname, String pemployeetitle, 
            String pphonenumber,  
            int psalary, String pusername, String ppassword, byte[] pstaffphoto){
        this.employeeID = pemployeeid;
        this.firstName = pfirstname;
        this.lastName = plastname;
        this.employeeTitle = pemployeetitle;
        this.phoneNumber = pphonenumber;

        this.salary = psalary;
        this.username = pusername;
        this.password = ppassword;
        this.staffPhoto = pstaffphoto;
    }
    
    public int getemployeeid()
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
    
    public String getemployeetitle()
    {
        return employeeTitle;
    }
    
    public String getphonenumber()
    {
        return phoneNumber;
    }
    

        
    public double getsalary()
    {
        return salary;
    }
    
    public String getusername()
    {
        return username;
    }
    
    public String getpassword()
    {
        return password;
    }
    
    public byte[] getstaffphoto()
    {
        return staffPhoto;
    }
}
