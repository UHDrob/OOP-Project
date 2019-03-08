/*
 * University of Houston - Downtown
 * CS 3318 OOP
 * Roberto Gomez
 *
 * FileOperations.java
 */
package FileOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Created: Feb 17, 2019
 * Modified: Mar 4, 2019
 * @author Roberto Gomez
 * @version 1
 */
public class StaffFileOperations 
{
    // Mar 3, 2019 Roberto: instance variables
    public String fileName = "staff.txt"; // this file has all the data for Staff section
    private static Scanner x;  // This array will containg the data from the text file
    
    // Feb 17, 2019 Roberto : operations or methods
    
    // Feb 18, 2019 Roberto : inputFileName ask user for filename and print out confirmation
        public void inputFileName()
        {
            // FOR TESTING ONLY: Display input entered on the console
             Scanner keyboard = new Scanner(System.in);
             System.out.println("\nThe file name that you entered is: " + fileName);
        }
    
        // Feb 18, 2019 Roberto : Read the data and display on the Console
        public void readFile()
        {
            boolean found = false;
            String employeeID;
            String firstName;
            String lastName;
            String title;
            String phoneNumber;
            String userName;
            String passWord;
                        
            try
        (Scanner fileIn = null // Initialize fileIn to empty
        ) {
                try
                {
                    x = new Scanner(new File(fileName));
                    x.useDelimiter("[,\n]");
                    
                    while(x.hasNext() )
                    {
                        employeeID = x.next();
                        firstName = x.next();
                        lastName = x.next();
                        title = x.next();
                        phoneNumber = x.next();
                        userName = x.next();
                        passWord = x.next();
                    }
                }
                catch (FileNotFoundException e)
                {
                    // this block executed if the file is not found
                    JOptionPane.showMessageDialog(null, "Reading File Error");
                }
                // if the program gets here then
                // the file was opened successfully
                System.out.println("The file is now open.\n");
                System.out.println();
                // FOR TESTING ONLY: Display the data
                System.out.println("Here is the data:\n");
                while (fileIn.hasNext())  // keep reading if there is another line
                {
                    String line = fileIn.nextLine();  // read the line
                    String [] lineArray = line.split(","); // delimiter betweeen fields
                    for (String s : lineArray)
                    {
                        System.out.print(s + " "); // add space between fields for output
                    }
                    System.out.println();
                }
        }
                 // Close the file
            System.out.println("\nThe file is now closed.\n");
        }
        
        // Feb 19, 2019 Roberto : For the ArrayList Part B
    public ArrayList ListUsers()
    {
        String employeeID; 
        String firstName; 
        String lastName;
        String title;
        String phoneNumber;
        String userName;
        String passWord;
        
        ArrayList<User> list = new ArrayList<User>();
        
        try
        {
            x = new Scanner(new File(fileName));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() )
            {
                employeeID = x.next();
                firstName = x.next();
                lastName = x.next();
                title = x.next();                
                phoneNumber = x.next();
                userName = x.next();
                passWord = x.next();
                User uX = new User(employeeID, firstName, lastName, title, phoneNumber, userName, passWord);
                list.add(uX);
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Array Error");
            
        }
               
        return list;
    }
    
       // Mar 3, 2019 Roberto: This section search for a Record in the file by employee ID 
       // arguments username and password
    public boolean searchLogin (String searchterm)
    {

        boolean isHere = false;
        String employeeIDAr = ""; 
        String firstNameAr = ""; 
        String lastNameAr = "";
        String titleAr ="";
        String phoneNumberAr = "";
        String uNameAr ;
        String pWordAr ;
        
        try
        {
            x = new Scanner(new File(fileName));
            x.useDelimiter("[,\n]");
            
           // while(x.hasNext() && !isHere )
           while(x.hasNext())
            {
                employeeIDAr = x.next();
                firstNameAr = x.next();
                lastNameAr = x.next();
                titleAr = x.next();                
                phoneNumberAr = x.next();
                uNameAr = x.next();
                pWordAr = x.next();
                
                // FOR TEST ONLY
               
                String test2 = uNameAr+pWordAr;
                System.out.println("Array: " + test2);
                System.out.println("credentials: " + searchterm);
                System.out.println(searchterm.equals(test2));
                System.out.println("test: " + test2);
                boolean test = searchterm.equals(test2);
                
                // Compare credentials
                if(!test)     
                        isHere = true;
                System.out.println(isHere);   //FOR TEST ONLY
            }
            if (isHere)
                {
                 JOptionPane.showMessageDialog(null,"Access Granted!");
                }
            else 
                {
                 JOptionPane.showMessageDialog(null,"Access Denied!");
                }
         }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Search Error");
            
        }
        
    return(isHere);

    }
   // Feb 19, 2019 Roberto: This section search for a Record in the file by employee ID 
    public static void searchRecord (String searchterm, String filepath)
    {
        boolean found = false;
        String employeeID = ""; 
        String firstName = ""; 
        String lastName = "";
        String title ="";
        String phoneNumber = "";
        
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() && !found )
            {
                employeeID = x.next();
                firstName = x.next();
                lastName = x.next();
                title = x.next();                
                phoneNumber = x.next();

                
                if(employeeID.equals(searchterm))
                {
                    found = true;
                }
            }
            if (found)
            {
                JOptionPane.showMessageDialog(null, "ID: " + employeeID 
                        + "\nFirst Name: " + firstName 
                        + "\nLast Name: " + lastName 
                        + "\nTitle: " + title 
                        + "\nPhone Number: " + phoneNumber);
            }
            else 
            {
                JOptionPane.showMessageDialog(null,"Record NOT found");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
            
        }
    }
    
    
        // Feb 19,2019 Roberto: This section will save the new record in the file
        public void saveRecord(String IDnum, String FirstName, String LastName, String Title, String PhoneNumber, String UserName, String PassWord)
    {
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(IDnum+"," + FirstName + "," + LastName + "," + Title + "," + PhoneNumber);
            pw.flush();
            pw.close();
            
            JOptionPane.showMessageDialog(null, "Record Saved");
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record NOT Saved");
        }
    }
    
     private void addAction ()
     {
                 // Feb 19, 2019 Roberto: This seciton will add the new record to the text file
        if(checkInputs() )   // first, check all the fields
        {
            String ID = txt_employeeID.getText();
            String firstname = txt_FirstName.getText();
            String lastname = txt_LastName.getText();
            String position = txt_Title.getText();
            String phonenumber = txt_PhoneNumber.getText();
            
            saveRecord(ID, firstname, lastname, position, phonenumber, filepath);                                 
                   // Show_Employee_In_JTable();
            JOptionPane.showMessageDialog(null,"New Employee has been Created");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");

        }

  
       // addRowToJTable();
     }
  
        
        
        
        
    }

