
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*//////////////////////////////////////////////////////////////////////////////
// March 15: Reports class created, dates are compared and items that are late 
// can be determined. After late items are determined the records are saved to a
// file: LateItems.txt.
//
// Fee calculator has been created seperately, NOT IN OOP Project. Need to move 
// into OOP project and further the functionality by adding calculation of dates
//
// Issues: reading out the data in LateItems.txt into the textArea
// March 27th: reading to text area solved
//             To do: complete calculated fees 
//                    add more reports if needed
//////////////////////////////////////////////////////////////////////////////*/
/**
 *
 * @author Nina Lalonde
 */
public class Reports extends javax.swing.JFrame 
{

    /**
     * Creates new form Reports
     */
    public Reports() 
    {
        initComponents();
    }
    /*//////////////////////////////////////////////////////////////////////////
    //Empty file to insure that duplicates are not created of the same data   //
    *///////////////////////////////////////////////////////////////////////////
    public static void emptyFile(String file)
    {
        try 
        {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {}
   }
    /*//////////////////////////////////////////////////////////////////////////
    //Saves the records to a textfile                                         //
    *///////////////////////////////////////////////////////////////////////////
    public static void saveRecord(String fName, String lName, String inventoryID, String file) 
            throws FileNotFoundException
    {  
        try
        {   
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(fName + " " + lName + " has overdue item: " + inventoryID);
            pw.flush();
            pw.close();
        }
       
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record NOT Saved");
        }
    }
    /*//////////////////////////////////////////////////////////////////////////
    //Compares dates to determine if an item is late                          //
    *///////////////////////////////////////////////////////////////////////////
    public static void compareDates(String infile1, String infile2, String outfile) throws ParseException, FileNotFoundException
    {
        try
        {
            //==========  Scanner and delimiter for CheckInOut.txt  ==========//
            String filepath = infile1;
            Scanner x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            //================ Date formatter ================================//
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //================= Get current date =====================//
            String todaysDate = sdf.format(new Date());
            //==== Converts String dates to Date objects for comparison ======//
            Date today = sdf.parse(todaysDate);
            
            while(x.hasNext()) // check to see if there is more data in file
            {
                String customerID= x.next();
                String inventoryID = x.next();
                String checkOut = x.next();
                String dueDate= x.next(); 
                //============== convert date to string ======================//
                Date due = sdf.parse(dueDate);
                //================  Compare dates =================//
                if (today.after(due))
                {
                    //======  Scanner and delimiter for Customers.txt  ======//
                    String cfile = infile2;
                    Scanner c = new Scanner(new File(cfile));
                    c.useDelimiter("[,\n]");
                   
                    // work throught customer file to see who has late items
                    while (c.hasNext())
                    {
                        String fName = c.next();
                        String lName = c.next();
                        String acctNum = c.next();
                        if(customerID.equals(acctNum))
                        {
                           // Save records to LateItems.txt
                           saveRecord(fName, lName, inventoryID, outfile);    
                        } 
                        else {}
                    }
                }
            }  
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Error");
            
        }
    }

    public static void calculateFees()
    {   // get late items from LateItems.txt and price from inventory.txt to 
        // calculate fees
        String price = "19.99";
        double pprice = Double.parseDouble(price);
        System.out.println(pprice);
        double fee = pprice * 0.15;
        // print out after calculation
        System.out.println("Fee for late book: " + fee);
        // format with 2 decimal places
        DecimalFormat numberFormat = new DecimalFormat("###.00");
        System.out.println(numberFormat.format(fee));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea = new java.awt.TextArea();
        rchoice = new javax.swing.JComboBox<>();
        ReportLable = new javax.swing.JLabel();
        generateReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N

        rchoice.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rchoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Report Options", "Late Items", "Fees", "Another" }));
        rchoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ReportLable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ReportLable.setText("Reports");

        generateReport.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        generateReport.setText("Generate Report");
        generateReport.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        generateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 1161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ReportLable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rchoice, javax.swing.GroupLayout.Alignment.LEADING, 0, 274, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addComponent(generateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReportLable, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateReport, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(rchoice))
                .addGap(19, 19, 19)
                .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportActionPerformed
        String choice = rchoice.getSelectedItem() + ""; 
        switch (choice)
        {
            case "Late Items":
                String file = "LateItems.txt";
                emptyFile(file);
                
                try 
                {
                    String check = "CheckInOut.txt";
                    String cust = "Customers.txt";
                    
                    compareDates(check, cust, file);
                } 
                catch (ParseException | FileNotFoundException ex) 
                {
                    Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                File filepath = new File(file); // open file
                
                try (Scanner inputFile = new Scanner(filepath))
                { // Read lines from the file until no more are left
                    while (inputFile.hasNext()) 
                    {
                        // Read the line into TextArea
                        String message = inputFile.nextLine();
                        textArea.append(message);
                        textArea.append("\n");
                    }
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Fees":
                // Want to output LateFees.txt here
                textArea.setText("Calculate Fees here");
                break;
            case "Another":
                textArea.setText("Can do some other reports");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_generateReportActionPerformed

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
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reports.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reports().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ReportLable;
    private javax.swing.JButton generateReport;
    private javax.swing.JComboBox<String> rchoice;
    private java.awt.TextArea textArea;
    // End of variables declaration//GEN-END:variables
}
