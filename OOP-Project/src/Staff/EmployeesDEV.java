/*
 * EMPLOYEES DEV.java     IN PROGRESS
   This section will manage staff information from a file

staff.txt file
FIELDS:  Employee ID, First Name, Last Name, Title, Phone Number, Username, Password

Notes: This is the current version as of Feb 19, 2019

Progress Report:
Add New Button working BUG duplicate entries
Select from list and fill out the fields  IN PROGRESS


 */



package Staff;
/**
 * Created: Feb 1, 2019
 * Modified: Mar 1, 2019
 * @author Roberto Gomez
 * @version 3
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class EmployeesDEV extends javax.swing.JFrame {

    /**
     * Feb 19, 2019 Roberto : Creates new form Employees
     */
    public EmployeesDEV() {
        initComponents();
        addRowToJTable();  // update the list
    }
    
    String filepath = "staff.txt";  // this file has all the data for this section
    private static Scanner x;
    
// Feb 19, 2019 Roberto : For the ArrayList Part A
    public class  User{
        public String idArray;
        public String fnameArray;
        public String lnameArray;
        public String titleArray;
        public String phoneNumberArray;
        public String usernameArray;
        
        public User(String Id, String FName, String LName, String Title, String PhoneNumber, String Username)
        {
            this.idArray = Id;
            this.fnameArray = FName;
            this.lnameArray = LName;
            this.titleArray = Title;
            this.phoneNumberArray = PhoneNumber; 
            this.usernameArray = Username;
        }
    }   
    
// Feb 19, 2019 Roberto : For the ArrayList Part B
    public ArrayList ListUsers()
    {
        String employeeID = ""; 
        String firstName = ""; 
        String lastName = "";
        String title ="";
        String phoneNumber = "";
        String userName = "";
        
        ArrayList<User> list = new ArrayList<User>();
        
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() )
            {
                employeeID = x.next();
                firstName = x.next();
                lastName = x.next();
                title = x.next();                
                phoneNumber = x.next();
                userName = x.next();
                User uX = new User(employeeID, firstName, lastName, title, phoneNumber, userName);
                list.add(uX);
            }
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Error");            
        }             
        return list;
    }

// Feb 19, 2019 Roberto : For the ArrayList Part C
        public void addRowToJTable()
    {
        DefaultTableModel model = (DefaultTableModel) jTable_Employees.getModel();
        ArrayList<User> list = ListUsers();
        Object rowData[] = new Object[7];
        for(int i=0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).idArray;
            rowData[1] = list.get(i).fnameArray;
            rowData[2] = list.get(i).lnameArray;
            rowData[3] = list.get(i).titleArray;
            rowData[4] = list.get(i).phoneNumberArray;
            rowData[5] = list.get(i).usernameArray;
            // add row to the model
            model.addRow(rowData);
        }
    }
 
        // Feb 19, 2019 Roberto : To Read the records from staff.txt
        public static void readRecord (String filepath)
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
            
            while(x.hasNext() )
            {
                employeeID = x.next();
                firstName = x.next();
                lastName = x.next();
                title = x.next();                
                phoneNumber = x.next();
            }
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Error");            
        }
    }
    
        // Feb 19, 2019 Roberto : Check Input Fields
        // to verify they are not empty
    public boolean checkInputs()
    {
        if (        txt_employeeID.getText() == null
                || txt_FirstName.getText()== null
                || txt_LastName.getText() == null
                || txt_Title.getText() == null
                || txt_PhoneNumber.getText() == null)
        {
        return false;
        }
        else
        {
            return true;
        }
    }
    
    // Feb 19, 2019 Roberto : This class define the Staff Fields
    public class  Staff{
        public int id;
        public String fname;
        public String lname;
        public int age;
        
        public Staff(int Id, String FName, String LName, int Age)
        {
            this.id = Id;
            this.fname = FName;
            this.lname = LName;
            this.age = Age;
            
        }
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
        public static void saveRecord(String IDnum, String FirstName, String LastName, String Title, String PhoneNumber, String FilePath)
    {
        try
        {
            FileWriter fw = new FileWriter(FilePath, true);
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
    
            // Show Data In Inputs
    public void ShowItem(int index)
    {
         //   txt_employeeID.setText(Integer.toString(ListUsers().get(index).getemployeeid()));                  
         //   txt_FirstName.setText(ListUsers().get(index).getfirstname());
         //   txt_LastName.setText(ListUsers().get(index).getlastname()); 
         //   txt_Title.setText(ListUsers().get(index).getemployeetitle());
         //   txt_PhoneNumber.setText(ListUsers().get(index).getphonenumber());
    }  
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_employeeID = new javax.swing.JTextField();
        txt_FirstName = new javax.swing.JTextField();
        txt_LastName = new javax.swing.JTextField();
        txt_Title = new javax.swing.JTextField();
        txt_PhoneNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Employees = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        txt_SearchEmployeeID = new javax.swing.JTextField();
        btn_searchEmployeeID = new javax.swing.JButton();
        lbl_Username = new javax.swing.JLabel();
        txt_Username = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Password = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("LIBRARY MANAGEMENT SYSTEM");

        jButton1.setText("Back to Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employees", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 24))); // NOI18N

        jLabel2.setText("Employee ID");

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("Title");

        jLabel6.setText("Phone Number");

        txt_employeeID.setText(" ");

        jTable_Employees.setBackground(new java.awt.Color(51, 255, 255));
        jTable_Employees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Title", "Phone Number"
            }
        ));
        jTable_Employees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_EmployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Employees);

        btn_Add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/add.png"))); // NOI18N
        btn_Add.setText("ADD NEW");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Delete.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/minus.png"))); // NOI18N
        btn_Delete.setText("DELETE");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Update.setFont(new java.awt.Font("Lucida Grande", 1, 11)); // NOI18N
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/update.png"))); // NOI18N
        btn_Update.setText("UPDATE");

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_searchEmployeeID.setText("Search by Employee ID");
        btn_searchEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchEmployeeIDActionPerformed(evt);
            }
        });

        lbl_Username.setText("Username");

        jLabel7.setText("Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(lbl_Username)
                            .addComponent(jLabel7))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_Title)
                            .addComponent(txt_LastName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_FirstName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_employeeID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(txt_PhoneNumber)
                            .addComponent(txt_Username)
                            .addComponent(txt_Password))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_clear)
                        .addGap(78, 78, 78)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_searchEmployeeID)
                        .addGap(31, 31, 31)
                        .addComponent(txt_SearchEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btn_Add)
                .addGap(63, 63, 63)
                .addComponent(btn_Delete)
                .addGap(65, 65, 65)
                .addComponent(btn_Update)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clear)
                    .addComponent(txt_SearchEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_searchEmployeeID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_employeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Username)
                            .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Add)
                    .addComponent(btn_Delete)
                    .addComponent(btn_Update))
                .addGap(84, 84, 84))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton1)))
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainMenu.MenuFrame Info = new MainMenu.MenuFrame();
        Info.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
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
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
       // Feb 19, 2019 Roberto: This will clear all the fields in the form
        
        txt_employeeID.setText(null);
        txt_FirstName.setText(null);
        txt_LastName.setText(null);
        txt_Title.setText(null);
        txt_PhoneNumber.setText(null);

    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_searchEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchEmployeeIDActionPerformed
      // Feb 19, 2019 Roberto: This section will search by employee ID
        String searchTerm = txt_SearchEmployeeID.getText();
        
        searchRecord(searchTerm,filepath);
    }//GEN-LAST:event_btn_searchEmployeeIDActionPerformed

    private void jTable_EmployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_EmployeesMouseClicked
        int row = jTable_Employees.getSelectedRow();
        String tableClick = jTable_Employees.getModel().getValueAt(row, 2).toString();

        ShowItem(row); 
    }//GEN-LAST:event_jTable_EmployeesMouseClicked

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        int row =jTable_Employees.getSelectedRow();
        if (row >= 0)
        {
            // remove a row from table
            
        }
        
    }//GEN-LAST:event_btn_DeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeesDEV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesDEV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesDEV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesDEV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeesDEV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_searchEmployeeID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Employees;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTextField txt_FirstName;
    private javax.swing.JTextField txt_LastName;
    private javax.swing.JTextField txt_Password;
    private javax.swing.JTextField txt_PhoneNumber;
    private javax.swing.JTextField txt_SearchEmployeeID;
    private javax.swing.JTextField txt_Title;
    private javax.swing.JTextField txt_Username;
    private javax.swing.JTextField txt_employeeID;
    // End of variables declaration//GEN-END:variables
}
