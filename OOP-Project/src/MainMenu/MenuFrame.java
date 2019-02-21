/*
   MENUFRAME.java    IN PROGRESS
 * This is the main MENU
   First screen on the program
   you can select daily tasks
   or management options
 */
package MainMenu;

/**
 * Craeated Feb 1, 2019
 * @author Rob77
 */
public class MenuFrame extends javax.swing.JFrame {

    /**
     * Creates new form MenuFrame
     */
    public MenuFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_CheckOut = new javax.swing.JButton();
        btn_CheckIn = new javax.swing.JButton();
        btn_NewAccount = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_Management = new javax.swing.JLabel();
        btn_Accounts = new javax.swing.JButton();
        btn_Staff = new javax.swing.JButton();
        btn_Inventory = new javax.swing.JButton();
        btn_ContactInfo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lbl_UHD = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_Title.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        lbl_Title.setForeground(new java.awt.Color(0, 0, 204));
        lbl_Title.setText("LIBRARY MANAGEMENT SYSTEM");

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Main Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 18), new java.awt.Color(0, 0, 204))); // NOI18N

        btn_CheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/CheckOut.PNG"))); // NOI18N
        btn_CheckOut.setText("Check OUT");
        btn_CheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CheckOutActionPerformed(evt);
            }
        });

        btn_CheckIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/CheckIN.PNG"))); // NOI18N
        btn_CheckIn.setText("Check IN");
        btn_CheckIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CheckInActionPerformed(evt);
            }
        });

        btn_NewAccount.setText("New Account");

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        lbl_Management.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lbl_Management.setText("MANAGEMENT");

        btn_Accounts.setText("Accounts");

        btn_Staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/Staff.PNG"))); // NOI18N
        btn_Staff.setText("Staff");
        btn_Staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StaffActionPerformed(evt);
            }
        });

        btn_Inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/Inventory.PNG"))); // NOI18N
        btn_Inventory.setText("Inventory");
        btn_Inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InventoryActionPerformed(evt);
            }
        });

        btn_ContactInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/ContactInfo.PNG"))); // NOI18N
        btn_ContactInfo.setText("Contact Info");
        btn_ContactInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ContactInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btn_Accounts)
                        .addGap(75, 75, 75)
                        .addComponent(btn_Staff)
                        .addGap(144, 144, 144)
                        .addComponent(btn_Inventory)
                        .addGap(95, 95, 95)
                        .addComponent(btn_ContactInfo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(640, 640, 640)
                        .addComponent(lbl_Management, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbl_Management)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Accounts)
                    .addComponent(btn_Staff)
                    .addComponent(btn_Inventory)
                    .addComponent(btn_ContactInfo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btn_CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130)
                .addComponent(btn_CheckIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_NewAccount)
                .addGap(158, 158, 158))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btn_NewAccount))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_CheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_CheckIn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(204, 0, 51));

        lbl_UHD.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl_UHD.setForeground(new java.awt.Color(255, 255, 255));
        lbl_UHD.setText("UHD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(lbl_UHD)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbl_UHD)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(350, 350, 350)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ContactInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ContactInfoActionPerformed
        ContactInfo.Contact Info = new ContactInfo.Contact();
        Info.setVisible(true);
    }//GEN-LAST:event_btn_ContactInfoActionPerformed

    private void btn_StaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StaffActionPerformed
        Staff.Employeesv2 Info = new Staff.Employeesv2();
        Info.setVisible(true);        
    }//GEN-LAST:event_btn_StaffActionPerformed

    private void btn_CheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CheckInActionPerformed
        CheckIn.CheckIn Info = new CheckIn.CheckIn();
        Info.setVisible(true);
    }//GEN-LAST:event_btn_CheckInActionPerformed

    private void btn_CheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CheckOutActionPerformed
        CheckOut.CheckOut Info = new CheckOut.CheckOut();
        Info.setVisible(true);
    }//GEN-LAST:event_btn_CheckOutActionPerformed

    private void btn_InventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InventoryActionPerformed
        Inventory.Inventory Info = new Inventory.Inventory();
        Info.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btn_InventoryActionPerformed

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
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Accounts;
    private javax.swing.JButton btn_CheckIn;
    private javax.swing.JButton btn_CheckOut;
    private javax.swing.JButton btn_ContactInfo;
    private javax.swing.JButton btn_Inventory;
    private javax.swing.JButton btn_NewAccount;
    private javax.swing.JButton btn_Staff;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_Management;
    private javax.swing.JLabel lbl_Title;
    private javax.swing.JLabel lbl_UHD;
    // End of variables declaration//GEN-END:variables
}
