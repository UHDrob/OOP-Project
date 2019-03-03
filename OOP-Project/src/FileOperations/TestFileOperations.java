/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileOperations;

/**
 *
 * @author Rob77
 */
public class TestFileOperations {
        public static void main (String[] args)
        {
            StaffFileOperations fileNew ;  // declaration of the object of type FileOperations
            fileNew = new StaffFileOperations();   // Invoke Constructor FileOperations
        
            fileNew.inputFileName("staff.txt");  // run method inputFileName and ask for the file name
            fileNew.readFile();   //  Open and read the file, then display on the screen.
        }

}
