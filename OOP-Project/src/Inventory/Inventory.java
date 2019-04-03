/*
 * INVENTORY    STATUS IN PROGRESS
  This section will allow to add or remote items
  Note ISBN is not the same as Inventory number
*/
package Inventory;

import CheckOut.*;
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

/*//////////////////////////////////////////////////////////////////////////////
 * Created: Feb 1, 2019
 * Modified: Feb 4, 2019
 * @author Roberto Gomez
 * @version 1
 * Updated: Feb 21, 2019
 * Nina Lalonde
 * Update: Feb 24, 2019
 * Things to work on: *Display information from file to table
 *                    *Search for multiple fields not just the ID
 * Update: Feb 26, 2019
 *                    *checkInput() is not functioning properly
 * Update: Feb 28, 2019: 
 *      Completed: Display file data to table
 *                 checkInput() functioning correctly
 *      To do: Search multiple fields
 *             Now recieving error message when going to Inventory screen
 *             Verify information prior to saving data in text file
 *             Start Report Class            
 /////////////////////////////////////////////////////////////////////////////*/

public class Inventory extends javax.swing.JFrame 
{

    private String filePath;
    /*
     * Creates New Inventory item
     */
    public Inventory() 
    {
        initComponents();
        addRowToJTable();   
    }
    
    // Links to Inventory.txt file
    String filepath = "Inventory.txt";
    private static Scanner x;
    
    /*//////////////////////////////////////////////////////////////////////////
    //  This section reads data stored in the text file and displayes it in   //
    //  the InventoryTable                                                    //
    //////////////////////////////////////////////////////////////////////////*/
    public class Items // Create arrays to store values for table
    {
        public String idArray;
        public String titleArray;
        public String authorArray;
        public String isbnArray;
        public String genreArray;
        public String priceArray;
        public String mediaTypeArray;
        
        public Items(String id, String title, String author, String isbn, String
                genre, String price, String mediaType)
        {
            this.idArray = id;
            this.titleArray = title;
            this.authorArray = author;
            this.isbnArray = isbn;
            this.genreArray = genre;
            this.priceArray = price;
            this.mediaTypeArray = mediaType;
        }
    }
    
    public ArrayList ListItems()// Used to create rows for table
    {
        String itemID = ""; 
        String title = ""; 
        String author = "";
        String isbn ="";
        String genre = "";
        String price = "";
        String mediaType = "";
        
        ArrayList<Items> list = new ArrayList<Items>();
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() )
            {
                itemID = x.next();
                title = x.next();
                author = x.next();
                isbn = x.next();                
                genre = x.next();
                price = x.next();
                mediaType = x.next();
                
                Items iList = new Items(itemID, title, author, isbn, genre, 
                        price, mediaType);
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
        DefaultTableModel model = (DefaultTableModel) InventoryTable.getModel();
        ArrayList<Items> list = ListItems();
        Object rowData[] = new Object[7];
        for(int i=0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).idArray;
            rowData[1] = list.get(i).titleArray;
            rowData[2] = list.get(i).authorArray;
            rowData[3] = list.get(i).isbnArray;
            rowData[4] = list.get(i).genreArray;
            rowData[5] = list.get(i).priceArray;
            rowData[6] = list.get(i).mediaTypeArray;
            model.addRow(rowData);
        }
    }
/*//////////////////////////////////////////////////////////////////////////////
//  Handling of data being output to text file
//////////////////////////////////////////////////////////////////////////////*/    
    public boolean checkInputs()
    {// Check Input Fields, if any are null, send error message
                
        if (    // check to make sure all fields have a value  
                txt_New_InventoryNo.getText().equals("") 
                || txt_New_Title.getText().equals("")
                || txt_New_Author.getText().equals("")   
                || txt_New_ISBN.getText().equals("")      
                || txt_New_Genre.getText().equals("")     
                || txt_New_Price.getText().equals("")    
                || txt_New_Media_Type.getText().equals("")
            )
        // if a field is not complete return false
            return false;
        
        else
        // all fields are complete, proceed
            return true;    
    }
    
    public static void readRecord (String searchterm, String filepath)
    {   /* Search Inventory Records, need to fix want to search for more than 
        just the invetory ID, would like to search multiple fields*/ 
        boolean found = false;
        String id = ""; 
        String title = ""; 
        String author = "";
        String isbn ="";
        String genre = "";
        String price = "";
        String mediaType ="";
        
        try
        {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            
            while(x.hasNext() && !found )
            {
                id = x.next();
                title = x.next();
                author = x.next();
                isbn = x.next();                
                genre = x.next();
                price = x.next();
                mediaType = x.next();
                /* this statement is not true, need to access each txt field to 
                see if they match 
                this statement assumes that fields cannot be the same values
                what if there are multiple books with the same author
                want to display them all, possibly within the table*/
                if(id.equals(searchterm)||
                        title.equals(searchterm)||
                        author.equals(searchterm))
                {
                    found = true;
                }
            }
            // this statement is fine
            if (found)
            {
                JOptionPane.showMessageDialog(null, "ID: " + id 
                        + "\nTitle: " + title 
                        + "\nAuthor: " + author
                        + "\nISBN: " + isbn
                        + "\nGenre: " + genre
                        + "\nPrice: " + price
                        + "\nMedia Type: " + mediaType);
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
   
    public static void saveRecord(String ID, String title, String author, String
            isbn, String genre, String price, String mediaType, String FilePath)
    {    // read data to be saved to text file
         // works fine
        try
        {   
            FileWriter fw = new FileWriter(FilePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(ID+"," + title + "," + author + "," + isbn + "," + genre + "," + price + "," + mediaType);
            pw.flush();
            pw.close();
            
            JOptionPane.showMessageDialog(null, "Record Saved");
        }
       
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record NOT Saved");
        }
 
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
        jPanel2 = new javax.swing.JPanel();
        btn_SearchButton = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        txt_SearchID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_SearchTitle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_SearchAuthor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_New_InventoryNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_New_Title = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_New_Author = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_New_ISBN = new javax.swing.JTextField();
        txt_New_Genre = new javax.swing.JTextField();
        txt_New_Price = new javax.swing.JTextField();
        btn_Add_New_Inventory = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_New_Media_Type = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INVENTORY", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 24))); // NOI18N

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

        txt_SearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchIDActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        jLabel9.setText("Title");

        jLabel10.setText("Author");

        txt_SearchAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchAuthorActionPerformed(evt);
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
                        .addComponent(txt_SearchAuthor))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel7))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SearchID)
                            .addComponent(txt_SearchTitle))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btn_SearchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_SearchID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_SearchTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_SearchAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_clear)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel2.setText("ID");

        txt_New_InventoryNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_InventoryNoActionPerformed(evt);
            }
        });

        jLabel3.setText("Title");

        txt_New_Title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_TitleActionPerformed(evt);
            }
        });

        jLabel4.setText("Author");

        jLabel6.setText("Genre");

        jLabel5.setText("ISBN");

        jLabel8.setText("Price");

        txt_New_ISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_New_ISBNActionPerformed(evt);
            }
        });

        btn_Add_New_Inventory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Add_New_Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/add.png"))); // NOI18N
        btn_Add_New_Inventory.setText("ADD NEW");
        btn_Add_New_Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Add_New_InventoryActionPerformed(evt);
            }
        });

        jLabel11.setText("Media Type");

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
                            .addComponent(txt_New_InventoryNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_New_Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(txt_New_Author, javax.swing.GroupLayout.Alignment.TRAILING))
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
                    .addComponent(txt_New_Media_Type)
                    .addComponent(txt_New_Genre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txt_New_ISBN, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_New_Price))
                .addGap(46, 46, 46)
                .addComponent(btn_Add_New_Inventory)
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_New_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txt_New_InventoryNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Add_New_Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_Title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_New_Author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_Genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_New_Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_New_Media_Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Author", "ISBN", "Genre", "Price", "Media Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(InventoryTable);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1326, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(78, 78, 78))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
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
        txt_SearchID.setText(null);
        txt_SearchTitle.setText(null);
        txt_SearchAuthor.setText(null);
 
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchButtonActionPerformed
        /* this needs to read from multiple text fields to find the value,
          possible case to determine which text field to utilize for searching*/      
    
        String searchTerm = txt_SearchID.getText();

        readRecord(searchTerm,filepath);
    }//GEN-LAST:event_btn_SearchButtonActionPerformed

    private void txt_SearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchIDActionPerformed

    private void btn_Add_New_InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Add_New_InventoryActionPerformed
        if(checkInputs()== true)
        {/* After clicking new, add items to text file
            Works fine*/
        
            String id = txt_New_InventoryNo.getText();
            String title = txt_New_Title.getText();
            String author = txt_New_Author.getText();
            String isbn = txt_New_ISBN.getText();
            String genre = txt_New_Genre.getText();
            String price = txt_New_Price.getText();
            String mediaType = txt_New_Media_Type.getText();
            
            saveRecord(id, title, author, isbn, genre, price, mediaType, filepath);

            JOptionPane.showMessageDialog(null,"New Inventory Item has been created");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");

        }

    }//GEN-LAST:event_btn_Add_New_InventoryActionPerformed

    private void txt_New_ISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_ISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_ISBNActionPerformed

    private void txt_New_TitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_TitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_TitleActionPerformed

    private void txt_New_InventoryNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_New_InventoryNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_New_InventoryNoActionPerformed

    private void txt_SearchAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchAuthorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchAuthorActionPerformed

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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable InventoryTable;
    private javax.swing.JButton btn_Add_New_Inventory;
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
    private javax.swing.JTextField txt_New_Author;
    private javax.swing.JTextField txt_New_Genre;
    private javax.swing.JTextField txt_New_ISBN;
    private javax.swing.JTextField txt_New_InventoryNo;
    private javax.swing.JTextField txt_New_Media_Type;
    private javax.swing.JTextField txt_New_Price;
    private javax.swing.JTextField txt_New_Title;
    private javax.swing.JTextField txt_SearchAuthor;
    private javax.swing.JTextField txt_SearchID;
    private javax.swing.JTextField txt_SearchTitle;
    // End of variables declaration//GEN-END:variables
}
