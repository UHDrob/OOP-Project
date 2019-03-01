/*
 * University of Houston - Downtown
 * CS 3318 OOP
 * Roberto Gomez
 *
 * FileOperations.java
 */
package FileOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created: Feb 17, 2019
 * Modified: Feb 24, 2019
 * @author Roberto Gomez
 * @version 1
 */
public class StaffFileOperations 
{
    // Feb 17, 2019 Roberto: instance variables
    public String fileName;

    
    // Feb 17, 2019 Roberto : operations or methods
    
    // Feb 18, 2019 Roberto : inputFileName ask user for filename and print out confirmation
        public void inputFileName()
        {
            Scanner keyboard = new Scanner(System.in);
                        
                    // prompts should be clear and concise
            System.out.println("Please enter the file name (Ex file.txt) :");
            fileName = keyboard.next();
            String junk = keyboard.nextLine(); // to get rid of '\n'
            // Display input entered on the console
            System.out.println("\nThe file name that you entered is: " + fileName);
        }
    
        // Feb 18, 2019 Roberto : Read the data and display on the Console
        public void readFile()
        {
            Scanner fileIn = null;  // Initialize fileIn to empty
            try
            {
                // Attempt to open the file
                fileIn = new Scanner ( new FileInputStream(fileName));
            }
            catch (FileNotFoundException e)
            {
                // this block executed if the file is not found
                // and then the program exits
                System.out.println("File not found.");
                System.exit(0);
            }
            
            // if the program gets here then
            // the file was opened successfully
            System.out.println("The file is now open.\n");            
            System.out.println();
            
            
            // Display the data
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
            fileIn.close();
                 // Close the file
            System.out.println("\nThe file is now closed.\n");
        }
}
