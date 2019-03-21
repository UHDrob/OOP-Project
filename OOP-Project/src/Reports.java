
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


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
    public static void emptyFile()
    {
        try 
        {
            PrintWriter writer = new PrintWriter("LateItems.txt");
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
    public static void saveRecord(String fName, String lName, String inventoryID) throws FileNotFoundException
    {  
        try
        {   
            
            FileWriter fw = new FileWriter("LateItems.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(fName+" " + lName + " has overdue inventory item: " + inventoryID);
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
    public static String compareDates() throws ParseException
    {
        String message = "";
        
        try
        {
            // dates scanner
            String filepath = "CheckInOut.txt";
            Scanner x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            // customer file scanner
            String cfile = "Customer.txt";
            Scanner c = new Scanner(new File(cfile));
            c.useDelimiter("[,\n]");
            
            while(x.hasNext())
            {
                String customerID= x.next();
                String inventoryID = x.next();
                String checkOut = x.next();
                String dueDate= x.next(); 
                // need to figure out how to pull in current date
                String todaysDate = "2019-03-15";
                // convert dates from stings to Date object and format  year-month-day
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date today = sdf.parse(todaysDate);
                Date due = sdf.parse(dueDate);
                if (today.equals(due) || today.before(due))
                {
                    System.out.println("Item due today");
                }
                else if (today.after(due))
                {
                    while (c.hasNext())
                    {
                        String fName = c.next();
                        String lName = c.next();
                        String acctNum = c.next();
                        if(customerID.equals(acctNum))
                        {
                            saveRecord(fName, lName, inventoryID);
                            break;
                        }
                    }
                }  
            }
            
        }
        catch(FileNotFoundException e)
        {
            message = "File not found";
            
        }
        return message;
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
        reportChoice = new javax.swing.JComboBox<>();
        ReportLable = new javax.swing.JLabel();
        generateReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N

        reportChoice.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        reportChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Report Options", "Late Items", "Fees", "Another" }));
        reportChoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                            .addComponent(reportChoice, javax.swing.GroupLayout.Alignment.LEADING, 0, 274, Short.MAX_VALUE))
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
                    .addComponent(reportChoice))
                .addGap(19, 19, 19)
                .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportActionPerformed
        // TODO add your handling code here:
        
        String choice = reportChoice.getSelectedItem() + "";
        //textArea.setText(choice);
        switch (choice) 
        {
            case "Late Items":
                try
                {   // empty file as to not concantinate, prints Late items to file
                    emptyFile();
                    compareDates();
                    // need to load the textfile into the reports textArea
                    // this is not working properly
                } 
                catch (ParseException ex) 
                {
                    Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Fees":
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
    private javax.swing.JComboBox<String> reportChoice;
    private java.awt.TextArea textArea;
    // End of variables declaration//GEN-END:variables
}
