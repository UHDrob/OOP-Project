/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customers;

import CheckOut.*;
import Staff.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


/* Created: March 1st
    
 *
 * @author Alan
    @version1
    Updated:
    Alan Saavedra 
    Things to work on: Read and write to text files 
    //hgghhhhHhHhhGhhh

 */
    public class Customers extends javax.swing.JFrame
    {
       private String filePath;
    /*
     * Creates New Inventory item
     */
      public void createCustomer() 
    {
        initComponents();
        addRowToJTable();   
    }
    
    // Links to Inventory.txt file
    String filepath = "Customers.txt";
    private static Scanner x;
    
    /*//////////////////////////////////////////////////////////////////////////
    //  This section reads data stored in the text file and displayes it in   //
    //  the Customers Table                                                     //
    //////////////////////////////////////////////////////////////////////////*/
    public class Items // Create arrays to store values for table
    {
        public String accountNumArray;
        public String firstNameArray;
        public String lastNameArray;
        public String phoneNumArray;
        public String emailArray;
        public String enrollmentDateArray;
        //public String mediaTypeArray; //have to look at whole code and delete all instances
        
        public Items(String accountNum, String firstName, String lastName, String phoneNum, String
                email, String enrollmentDate) //theres a String mediaType in braces
        {
            this.accountNumArray = accountNum;
            this.firstNameArray = firstName;
            this.lastNameArray = lastName;
            this.phoneNumArray = phoneNum;
            this.emailArray = email;
            this.enrollmentDateArray = enrollmentDate;
            //this.mediaTypeArray = mediaType;
        }
    }
    
    public ArrayList ListItems()// Used to create rows for table
    {
        String AccountNum = ""; 
        String firstName = ""; 
        String lastName = "";
        String phoneNum ="";
        String email = "";
        String enrollmentDate = "";
        //String mediaType = "";
        
        ArrayList<Items> list = new ArrayList<Items>();
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() )
            {
                AccountNum = x.next();
                firstName = x.next();
                lastName = x.next();
                phoneNum = x.next();                
                email = x.next();
                enrollmentDate = x.next();
               // mediaType = x.next();
                
                Items iList = new Items(AccountNum, firstName, lastName, phoneNum, email, 
                        enrollmentDate); //mediaType in braces
                list.add(iList);
            }

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error");
            
        }      
        return list;
    }
    
    public void addRowToJTable()
    {// Creates rows in JTable
        DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();
        ArrayList<Items> list = ListItems();
        Object rowData[] = new Object[7];
        for(int i=0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).accountNumArray;
            rowData[1] = list.get(i).firstNameArray;
            rowData[2] = list.get(i).lastNameArray;
            rowData[3] = list.get(i).phoneNumArray;
            rowData[4] = list.get(i).emailArray;
            rowData[5] = list.get(i).enrollmentDateArray;
            //rowData[6] = list.get(i).mediaTypeArray;
            model.addRow(rowData);
        }
    }
/*//////////////////////////////////////////////////////////////////////////////
//  Handling of data being output to text file
//////////////////////////////////////////////////////
////////////////////////*/    
    public boolean checkInputs()
    {// Check Input Fields, if any are null, send error message
                
        if (    // check to make sure all fields have a value  
                txt_New_AccountNumNo.getText().equals("") 
                || txt_New_FirstName.getText().equals("")
                || txt_New_LastName.getText().equals("")   
                || txt_New_PhoneNum.getText().equals("")      
                || txt_New_Email.getText().equals("")     
                || txt_New_EnrollmentDate.getText().equals("")    
                //|| txt_New_Media_Type.getText().equals("")
            )
        // if a field is not complete return false
            return false;
        
        else
        // all fields are complete, proceed
            return true;    
    }
    
    public static void readRecord (String searchterm, String filepath)
    {   /* Search Inventory Records, need to fix want to search for more than 
        just the invetory AccountNum, would like to search multiple fields*/ 
        boolean found = false;
        String accountNum = ""; 
        String firstName = ""; 
        String lastName = "";
        String phoneNum ="";
        String email = "";
        String enrollmentDate = "";
      //  String mediaType ="";
        
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() && !found )
            {
                accountNum = x.next();
                firstName = x.next();
                lastName = x.next();
                phoneNum = x.next();                
                email = x.next();
                enrollmentDate = x.next();
              //  mediaType = x.next();
                /* this statement is not true, need to access each txt field to 
                see if they match 
                this statement assumes that fields cannot be the same values
                what if there are multiple books with the same author
                want to display them all, possibly within the table*/
                if(accountNum.equals(searchterm)||
                        firstName.equals(searchterm)||
                        lastName.equals(searchterm))
                {
                    found = true;
                }
            }
            // this statement is fine
            if (found)
            {
                JOptionPane.showMessageDialog(null, "AccountNum: " + accountNum 
                        + "\nFirstName: " + firstName 
                        + "\nLastName: " + lastName
                        + "\nPhoneNum: " + phoneNum
                        + "\nEmail: " + email
                        + "\nEnrollmentDate: " + enrollmentDate);
                    //    + "\nMedia Type: " + mediaType);
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
   
    public static void saveRecord(String AccountNum, String firstName, String lastName, String
            phoneNum, String email, String enrollmentDate, String FilePath)//Theres a String mediaType in braces
    {    // read data to be saved to text file
         // works fine
        try
        {   
            FileWriter fw = new FileWriter(FilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(AccountNum+"," + firstName + "," + lastName + "," + phoneNum + "," + email + "," + enrollmentDate + ","); //Theres a mediaType in this 
            pw.flush();
            pw.close();
            
            JOptionPane.showMessageDialog(null, "Record Saved");
        }
       
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record NOT Saved");
        }
 
    }
  
 //
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
//hHhH
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_SearchButton = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        txt_SearchAccountNum = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_SearchFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_SearchLastName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_New_AccountNumNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_New_FirstName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_New_LastName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_New_PhoneNum = new javax.swing.JTextField();
        txt_New_Email = new javax.swing.JTextField();
        txt_New_EnrollmentDate = new javax.swing.JTextField();
        btn_Add_New_AccountNum = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
//     //  txt_New_Media_Type = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CUSTOMER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 24))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 204, 0));

        btn_SearchButton.setText("Search ");
        btn_SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchButtonActionPerformed(evt);
            }
        });

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        txt_SearchAccountNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchAccountNumActionPerformed(evt);
            }
        });

        jLabel7.setText("AccountNum");

        jLabel9.setText("FirstName");

        jLabel10.setText("LastName");

        txt_SearchLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchLastNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_SearchButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addComponent(txt_SearchLastName))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel7))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SearchAccountNum)
                            .addComponent(txt_SearchFirstName))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btn_SearchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchAccountNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_SearchFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_SearchLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_clear)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setText("AccountNum");

        txt_New_AccountNumNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_AccountNumNoActionPerformed(evt);
            }
        });

        jLabel3.setText("FirstName");

        txt_New_FirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_FirstNameActionPerformed(evt);
            }
        });

        jLabel4.setText("LastName");

        jLabel6.setText("Email");

        jLabel5.setText("PhoneNum");

        jLabel8.setText("EnrollmentDate");

        txt_New_PhoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_PhoneNumActionPerformed(evt);
            }
        });

        btn_Add_New_AccountNum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Add_New_AccountNum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/add.png"))); // NOI18N
        btn_Add_New_AccountNum.setText("ADD NEW");
        btn_Add_New_AccountNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Add_New_AccountNumActionPerformed(evt);
            }
        });

       // jLabel11.setText("Media Type");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(436, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(34, 34, 34))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_New_AccountNumNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_New_FirstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(txt_New_LastName, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 572, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   // .addComponent(txt_New_Media_Type)
                    .addComponent(txt_New_Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txt_New_PhoneNum, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_New_EnrollmentDate))
                .addGap(46, 46, 46)
                .addComponent(btn_Add_New_AccountNum)
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_New_PhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txt_New_AccountNumNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Add_New_AccountNum, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_New_LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_EnrollmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  //  .addComponent(txt_New_Media_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AccountNum", "FirstName", "LastName", "PhoneNum", "Email", "EnrollmentDate",
            }//Theres a "Media
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(CustomerTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(489, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainMenu.MenuFrame Info = new MainMenu.MenuFrame();
        Info.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        //Clears data input into the search boxes
        // Works fine
        txt_SearchAccountNum.setText(null);
        txt_SearchFirstName.setText(null);
        txt_SearchLastName.setText(null);
 
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchButtonActionPerformed
        /* this needs to read from multiple text fields to find the value,
          possible case to determine which text field to utilize for searching*/      
    
        String searchTerm = txt_SearchAccountNum.getText();

        readRecord(searchTerm,filepath);
    }//GEN-LAST:event_btn_SearchButtonActionPerformed

    private void txt_SearchAccountNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchAccountNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchAccountNumActionPerformed

    private void btn_Add_New_AccountNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Add_New_AccountNumActionPerformed
        if(checkInputs()== true)
        {/* After clicking new, add items to text file
            Works fine*/
        
            String accountNum = txt_New_AccountNumNo.getText();
            String firstName = txt_New_FirstName.getText();
            String lastName = txt_New_LastName.getText();
            String phoneNum = txt_New_PhoneNum.getText();
            String email = txt_New_Email.getText();
            String enrollmentDate = txt_New_EnrollmentDate.getText();
          //  String mediaType = txt_New_Media_Type.getText();
            
            saveRecord(accountNum, firstName, lastName, phoneNum, email, enrollmentDate, filepath); //Theres a mediaType in braces 

            JOptionPane.showMessageDialog(null,"New AccountNum Item has been created");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");

        }

    }//GEN-LAST:event_btn_Add_New_AccountNumActionPerformed

    private void txt_New_PhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_PhoneNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_PhoneNumActionPerformed

    private void txt_New_FirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_FirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_FirstNameActionPerformed

    private void txt_New_AccountNumNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_AccountNumNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_AccountNumNoActionPerformed

    private void txt_SearchLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchLastNameActionPerformed

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
            java.util.logging.Logger.getLogger(Customers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            @Override
            public void run() {
                new Customers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CustomerTable;
    private javax.swing.JButton btn_Add_New_AccountNum;
    private javax.swing.JButton btn_SearchButton;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_New_LastName;
    private javax.swing.JTextField txt_New_Email;
    private javax.swing.JTextField txt_New_PhoneNum;
    private javax.swing.JTextField txt_New_AccountNumNo;
   // private javax.swing.JTextField txt_New_Media_Type;
    private javax.swing.JTextField txt_New_EnrollmentDate;
    private javax.swing.JTextField txt_New_FirstName;
    private javax.swing.JTextField txt_SearchLastName;
    private javax.swing.JTextField txt_SearchAccountNum;
    private javax.swing.JTextField txt_SearchFirstName;
    // End of variables declaration//GEN-END:variables
    }

//h