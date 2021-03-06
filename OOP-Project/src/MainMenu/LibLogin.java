/*
 * LibLogin Window
 *
Description: This will serve to authenticate credentials from the user
             comparing username and password with the ones on staff.txt
             It will request user input and then search on the text file
             if found, then it will grant access to the program
Dependencies: None

Variables:
txt_username: to hold username
txt_password: to hold password

Programming Strategy:
    Create a java frame to hold the user interface
    Select code to run actionable items

Modules:
    Clear   Delete all the txt fields
    Exit    Close the program
    Login   Compare data entered to credentials in staff.txt
    CloseMe Close the current wingow



 */

/**
 * Created: Feb 8,2019
 * Modified Feb 28, 2019
 * @author ROBERTO GOMEZ
 * @version 2
 */
package MainMenu;

import FileOperations.StaffFileOperations;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class LibLogin extends javax.swing.JFrame {
//staffarray = null;
boolean found;

private JFrame frame;
    /**
     * Creates new form Login
     */
    public LibLogin() {
        initComponents();
    }

    /**
     *  Feb 20, 2019 Roberto
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        btn_login = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 51, 204))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(153, 204, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel3.setText("Password");
        jLabel3.setToolTipText("");

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txt_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        btn_login.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn_login.setText("Login");
        btn_login.setToolTipText("");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        btn_reset.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_exit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn_exit.setText("Exit");
        btn_exit.setToolTipText("");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btn_login)
                            .addGap(17, 17, 17))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btn_reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(btn_exit)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_username, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(txt_password))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_login)
                    .addComponent(btn_reset)
                    .addComponent(btn_exit))
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 172, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel1.setText("LIBRARY MANAGEMENT SYSTEM");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(251, 96, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\Rob77\\Pictures\\UHD01.JPG")); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
       // this button will exit the class
        System.exit(0);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
      // this button will clear the field boxes in the form
        txt_username.setText(null);
        txt_password.setText(null);
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
      // this buttnon process the credentials
        String password = txt_password.getText();
        String username = txt_username.getText();
        String credentials = username+password;
        
// READ STAFF.TXT
            StaffFileOperations fileNew ;  // declaration of the object of type FileOperations
            fileNew = new StaffFileOperations();   // Invoke Constructor FileOperations
        
            //fileNew.inputFileName();  // run method inputFileName and ask for the file name
            //fileNew.readFile();   //  Open and read the file, then display on the screen.
            
            // Return asearch
           found = fileNew.searchLogin(credentials);

// Feb 28, 2019 Roberto: Verify credentials
           //if (password.contains("one") && (username.contains("king")))  // THIS IS A TEST
            if (found)    
            {
                txt_username.setText(null);
                txt_password.setText(null);
                systemExit();
                
                MenuFrame Info = new MenuFrame();
                Info.setVisible(true);
         
            }
                   else
            {
                JOptionPane.showMessageDialog(null,"Invalid Login Details", "ACCESS DENIED", JOptionPane.ERROR_MESSAGE);
                txt_password.setText(null);
                txt_username.setText(null);
                txt_username.grabFocus();
            }

    }//GEN-LAST:event_btn_loginActionPerformed
    
    // Feb 28, 2019 Roberto : Close the current window
    private void CloseMe(){
        WindowEvent meClose = new WindowEvent (this, WindowEvent.WINDOW_CLOSED);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(meClose);
    }
    /**************************************************
     *  THIS IS THE MAIN SECTION                      *
     * @param args the command line arguments         *
     ************************************************** 
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
            java.util.logging.Logger.getLogger(LibLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form Main Menu */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

    private void systemExit()
    {
    WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    }
}
