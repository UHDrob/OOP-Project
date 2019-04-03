
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static void saveLateItems(String fName, String lName, String inventoryID, String file) 
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
                           saveLateItems(fName, lName, inventoryID, outfile);    
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
    /**
     *
     * @param id
     * @param fees
     * @param file
     * @throws FileNotFoundException
     */
    /*//////////////////////////////////////////////////////////////////////////
    //                         Save fees to LateFees.txt                      //
    *///////////////////////////////////////////////////////////////////////////
    public static void saveFees(String id, double fees, String file) throws FileNotFoundException
    {
        try
        { 
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(id+","+ fees);
            pw.flush();
            pw.close();
        }
       
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(null, "Record NOT Saved");
        }
    }
    /**
     *
     * @throws FileNotFoundException
     * @throws ParseException
     */
    /*//////////////////////////////////////////////////////////////////////////
    //Determines if a book is late and what level of fee should be associated //
    // with the product being late.                                           //
    *///////////////////////////////////////////////////////////////////////////
    public static void calcDates() throws FileNotFoundException, ParseException
    {
        String dates = "CheckInOut.txt";
        Scanner d = new Scanner(new File(dates));
        d.useDelimiter("[,\n]");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(); // todays date
        Calendar c = Calendar.getInstance(); // makes calendar instance
        c.setTime(currentDate); // converts date to calendar
        
        System.out.println("Todays Date Calendar: " + currentDate);
       
        //============== subtract days from the current date =================//
        c.add(Calendar.DATE, -1);//day prior to current
        Date currentDateMin1 = c.getTime();
        c.add(Calendar.DATE, -15); // 2 weeks before current date
        Date currentDateMin15 = c.getTime();
        Date currentDateMin29 = c.getTime();
        c.add(Calendar.DATE, -14);// 6 weeks before current date
        Date currentDateMin43 = c.getTime();
     
        while (d.hasNext())
        {
            String custID = d.next();
            String inID = d.next();
            String outDate = d.next();
            String dueDate = d.next();
            //============== convert date to string ======================//
            Date due = sdf.parse(dueDate);
            Calendar dueD = Calendar.getInstance();
            dueD.setTime(due);
            System.out.println("Due date Calendar: " + due);
            
            if (due.before(currentDateMin1)&& due.after(currentDateMin15))
            {
                calculateFees(1, inID); 
            }
            else if (due.before(currentDateMin15)&& due.after(currentDateMin29))
            {
                calculateFees(2, inID);
            }
            else if (due.before(currentDateMin29)&& due.after(currentDateMin43))
            {
                calculateFees(3, inID);
            }
            else if (due.before(currentDateMin43))
            {
                calculateFees(4, inID);
            }
        }
    }
    /*//////////////////////////////////////////////////////////////////////////
    //              Calculate fees                                            //
    *///////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param option
     * @param pprice
     * @return
     */
    public static double fees(int option, double pprice)
    {
        double fee = 0; 
        switch(option)
        {
            case 1: 
                fee = pprice * 0.05;
                break;
            case 2: 
                fee = pprice * 0.15;
               break; 
            case 3:
                fee = pprice * 0.25;
                break; 
            case 4:
                fee = pprice;
                break;
            default:
                System.out.println("Invalid input.");
        }
        return fee;
    }
    
    public static void calculateFees(int option, String inID) throws FileNotFoundException, ParseException
    {
        String file = "Inventory.txt";
        Scanner i = new Scanner(new File(file));
        i.useDelimiter("[,\n]");
        double fee; 
        String outfile = "LateFees.txt";
        while (i.hasNext())
        {//ID,Title,Author,ISBN,Genre,Price,Media Type
            String id = i.next();
            String title = i.next();
            String author = i.next();
            String isbn = i.next();
            String genre = i.next();
            String price = i.next();
            String media = i.next();  
            double pprice = Double.parseDouble(price);
            
            if (id.equals(inID)) 
            {
                fee = fees(option, pprice);
                saveFees(id, fee, outfile);
            }
        }
    }
    public static void feeReport() throws FileNotFoundException
    {
        String feeFile = "LateFees.txt";
        Scanner f = new Scanner(new File(feeFile));
        f.useDelimiter("[,\n]");
        
        while(f.hasNext())
        {
            String fId = f.next();
            String fee = f.next();
            
            String file = "Inventory.txt";
            Scanner i = new Scanner(new File(file));
            i.useDelimiter("[,\n]");
        
            while (i.hasNext())
            {
                //ID,Title,Author,ISBN,Genre,Price,Media Type
                String id = i.next();
                String title = i.next();
                String author = i.next();
                String isbn = i.next();
                String genre = i.next();
                String price = i.next();
                String media = i.next();
                
                if(fId.equals(id))
                {
                    String lFile = "LateItems.txt";
                    Scanner l = new Scanner(new File(lFile));
        
                    while(l.hasNext())
                    {
                        String fname = l.next();
                        String lname = l.next();
                        String k = l.next();
                        String t = l.next();
                        String x = l.next();
                        String num = l.next();
                        
                        if(fId.equals(num))
                        {
                            String rfile = "FeeReport.txt";
                            saveFeeReport(fname, lname, title, num, fee, rfile);
                        }
                    }
                }
            }
        }
    }
     public static void saveFeeReport(String fname, String lname, String title,
             String num, String fee, String rfile) throws FileNotFoundException
    {
        try
        { 
            FileWriter fw = new FileWriter(rfile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(fname + " "+lname+ " has overdue item: "+ title + " ");
            float x = Float.valueOf(fee);
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            DecimalFormat format = new DecimalFormat("###.00");
            //format.setDecimalFormatSymbols();
            String sf2=String.format("%6.2f",x);  
            pw.println("\tFees owed: "+ sf2);       
            pw.flush();
            pw.close();
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
                textArea.setText("");
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
                String fFile = "FeeReport.txt";
                emptyFile(fFile);
                String fees = "LateFees.txt";
                emptyFile(fees);
                textArea.setText("");
                try 
                {
                    calcDates();
                    feeReport();
                } 
                catch (FileNotFoundException | ParseException ex) 
                {
                    Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                File path = new File(fFile); // open file
                
                try (Scanner inputFile = new Scanner(path))
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
