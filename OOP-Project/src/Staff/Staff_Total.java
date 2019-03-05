/*
 * Library Management System
 * Staff Section
 * Operations Level
 */
package Staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Roberto Gomez
 */
public class Staff_Total extends javax.swing.JFrame {

    /**
     * Creates new form Staff_Window
     */
    public Staff_Total() {
        initComponents();
        Show_Staff_In_JTable();
        Show_StaffTotals_In_JTable();
    }

    String ImgPath = null;
    int pos = 0;
    
  // Connect to JavaDB Database  
    public Connection getConnection()
    {
        Connection con = null;
        try {
            String urlDB = "jdbc:derby://localhost:1527/cinema";
            String usernameDB = "cinema";
            String passwordDB = "cinemalogin";
            //con = DriverManager.getConnection("jdbc:derby://localhost:1527/cinema","cinema","cinemalogin");
            con = DriverManager.getConnection(urlDB, usernameDB, passwordDB);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
            return null;                       
        }                           
    }
     
    // Check Input Fields
    public boolean checkInputs()
    {
        if(        txt_firstname.getText() == null
                || txt_releasedate.getDate()== null
                || txt_lastname.getText() == null
                || txt_title.getText() == null)

        {
        return false;
        }
        else
        {
            try{
                //Float.parseFloat(txt_.getText());                
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }        
    }
      

     
    // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    public ArrayList<Staff> getMoviesList()
    {
            ArrayList<Staff> moviesList  = new ArrayList<Staff>();
            Connection con = getConnection();
            String sql = "SELECT * FROM mv_movies ORDER by movietitle";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Staff staff;
            
            while(rs.next())
            {
  //              staff = new Staff (rs.getInt("employeeid"),rs.getString("movietitle"),
   //                     rs.getString("releasedate"),rs.getString("rating"),
  //                      rs.getString("category"), rs.getString("runningtime"),
  //                      rs.getString("director"), rs.getString("moviecast"));
                staffList.add(staff);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return staffList;                 
    }   
        public ArrayList<Staff> getStaffTotalsList()
    {
            ArrayList<Staff> stafftotalsList  = new ArrayList<Staff>();
            Connection con = getConnection();
            String sql = "SELECT COUNT (movieid) As Total_Movies\n" +
                         "FROM cinema.mv_movies";
            
            Statement st;
            ResultSet rs;
            
        try {          
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Staff stafftotals;
            
            while(rs.next())
            {
                stafftotals = new Staff(rs.getInt("Total_Employees"));
 //               JOptionPane.message()
                stafftotalsList.add(stafftotals);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stafftotalsList;                 
    }   
    //      2 - Populate The JTable    
    public void Show_Staff_In_JTable()
    {
        ArrayList<Staff> list = getMoviesList();
        DefaultTableModel model = (DefaultTableModel)JTable_Movies.getModel();
        // clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getemployeeid();
            row[1] = list.get(i).getfirstname();
            row[2] = list.get(i).getlastname();
            row[3] = list.get(i).gettitle();
            
            model.addRow(row);
        }    
    }
        public void Show_StaffTotals_In_JTable()
    {
        ArrayList<StaffTotals> list = getStaffTotalsList();
        DefaultTableModel model2 = (DefaultTableModel)JTable_StaffTotals.getModel();
        // clear jtable content
        model2.setRowCount(0);
        Object[] row = new Object[1];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).gettotalcount();
 //           row[1] = list.get(i).getmovietitle();
 //           row[2] = list.get(i).getreleasedate();
 //           row[3] = list.get(i).getrating();
            
            model2.addRow(row);
        }    
    }
    
    
    // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_employeeid.setText(Integer.toString(getMoviesList().get(index).getemployeeid()));
            txt_firstname.setText(getMoviesList().get(index).getfirstname());
            

            txt_lastname.setText(getMoviesList().get(index).getrating());
            txt_title.setText(getMoviesList().get(index).getcategory());
            txt_phonenumber.setText(getMoviesList().get(index).getrunningtime());
            txt_username.setText(getMoviesList().get(index).getdirector());
            txt_password.setText(getMoviesList().get(index).getmoviecast());
            

    }       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog3 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog4 = new datechooser.beans.DateChooserDialog();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_firstname = new javax.swing.JTextField();
        txt_lastname = new javax.swing.JTextField();
        txt_phonenumber = new javax.swing.JTextField();
        txt_title = new javax.swing.JTextField();
        txt_password = new javax.swing.JTextField();
        txt_employeeid = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        JScrollPanel = new javax.swing.JScrollPane();
        JTable_Movies = new javax.swing.JTable();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txt_releasedate = new com.toedter.calendar.JDateChooser();
        btn_first = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_previous = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_StaffTotals = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Employee ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Release Date:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Last Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Phone Number");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Title:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Username:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Password:");

        txt_firstname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_firstname.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_lastname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_lastname.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_phonenumber.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_phonenumber.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_title.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_password.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_employeeid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_employeeid.setPreferredSize(new java.awt.Dimension(160, 50));

        txt_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_username.setPreferredSize(new java.awt.Dimension(160, 50));

        JTable_Movies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "First Name", "Last Name", "Title"
            }
        ));
        JTable_Movies.setGridColor(new java.awt.Color(204, 0, 0));
        JTable_Movies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_MoviesMouseClicked(evt);
            }
        });
        JScrollPanel.setViewportView(JTable_Movies);
        if (JTable_Movies.getColumnModel().getColumnCount() > 0) {
            JTable_Movies.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        btn_insert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/add.png"))); // NOI18N
        btn_insert.setText("ADD NEW");
        btn_insert.setIconTextGap(15);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/update.png"))); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.setIconTextGap(15);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_icons/minus.png"))); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.setIconTextGap(15);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        txt_releasedate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btn_first.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_first.setText("First");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_previous.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_previous.setText("Previous");
        btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_previousActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_last.setText("Last");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        btn_new.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_new.setText("Clear");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btn_back.setText("Back to Main Menu");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel10.setText("CHECK IN - OUT");

        JTable_StaffTotals.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        JTable_StaffTotals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "TOTAL EMPLOYEES"
            }
        ));
        jScrollPane1.setViewportView(JTable_StaffTotals);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("ENTER EMPLOYEE ID: ");

        jButton1.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135)
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_title, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(111, 111, 111)
                                .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_employeeid, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_new))
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_releasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(251, 251, 251)
                                        .addComponent(btn_first)
                                        .addGap(71, 71, 71)
                                        .addComponent(btn_next)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(btn_previous)
                                        .addGap(100, 100, 100)
                                        .addComponent(btn_last))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(btn_back))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(497, 497, 497)
                                .addComponent(btn_insert)
                                .addGap(111, 111, 111)
                                .addComponent(btn_update)
                                .addGap(120, 120, 120)
                                .addComponent(btn_delete)))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_new)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txt_employeeid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txt_firstname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))))
                    .addComponent(btn_back))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_releasedate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_first)
                            .addComponent(btn_next)
                            .addComponent(btn_previous)
                            .addComponent(btn_last))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insert)
                            .addComponent(btn_update)
                            .addComponent(btn_delete))))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1506, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// Button Insert Data Into JavaDB Database
// 1 - Check If the imgPath is Not NUll and the inouts are not empty
// 2- Insert the Data        
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        if(checkInputs()  &&  ImgPath !=null)
        {
            try{
                    Connection con = getConnection();      
                    String sqlInsert = "INSERT INTO mv_movies" 
                            + "(movietitle, releasedate, rating, category, runningtime, director, moviecast, poster)" 
                        + "values(?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(sqlInsert);
                    
                    ps.setString(1, txt_firstname.getText());
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_releasedate.getDate());
                    ps.setString(2, addDate);
                   
                    ps.setString(3, txt_lastname.getText());
                    ps.setString(4, txt_phonenumber.getText());
                    ps.setString(5, txt_title.getText());
                    ps.setString(6, txt_username.getText());
                    ps.setString(7, txt_password.getText());
                    
                    InputStream img = new FileInputStream(new File(ImgPath));
                    ps.setBlob (8, img);
                                    
                    ps.executeUpdate();
                    Show_Staff_In_JTable();
                    Show_StaffTotals_In_JTable();
         
                    JOptionPane.showMessageDialog(null,"New Movie has been Created");
                    
                 }
                catch(Exception ex)  
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } 
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
            //Logger.getLogger(Movie_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //ONLY FOR TESTING:
            System.out.println("Employee ID =>" + txt_employeeid.getText());
            System.out.println("First Name => " + txt_firstname.getText());

            System.out.println("Last Name => " + txt_lastname.getText());
            System.out.println("Phone Number => " + txt_phonenumber.getText());
            System.out.println("Title => " + txt_title.getText());
            System.out.println("Username => " + txt_username.getText());
            System.out.println("Password => " + txt_password.getText());

        
    }//GEN-LAST:event_btn_insertActionPerformed

// Button Update Data   
// 1 - Check if inputs are not null
// else do not update the image
// 2 - Update the data    
    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
      
        if(checkInputs()  &&  txt_employeeid.getText()!=null)
        {
            String sqlUpdate = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            // Update without image
            if(ImgPath == null)
                    {
                        try {
                            sqlUpdate = "UPDATE mv_movies SET movietitle = ?, releasedate = ?, rating = ?, runningtime = ?, category = ?, director =?, moviecast = ?"
                                            + "WHERE movieid = ?";
                            ps = con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_firstname.getText());
                            ps.setString(3, txt_lastname.getText());
                            ps.setString(4, txt_phonenumber.getText());
                            ps.setString(5, txt_title.getText());
                            ps.setString(6, txt_username.getText());
                            ps.setString(7, txt_password.getText());
                            
                            ps.setInt(8, Integer.parseInt(txt_employeeid.getText()));
                            
                            ps.executeUpdate();
                            Show_Staff_In_JTable();
                            
                            JOptionPane.showMessageDialog(null, "Employee has been Updated");
                            
                        } catch (SQLException ex) {
                            Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
            
                    // Update with Image
            else{
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    
                            sqlUpdate = "UPDATE mv_movies SET movietitle = ?, releasedate = ?, rating = ?, runningtime = ?, category = ?, director =?, moviecast = ?"
                                            + ", poster = ? WHERE movieid = ?";
                            
                            ps=con.prepareStatement(sqlUpdate);
                            
                            ps.setString(1, txt_firstname.getText());
                            
                            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
                            String addDate = dateFormat.format(txt_releasedate.getDate());
                            ps.setString(2, addDate);
                           
                            ps.setString(3, txt_lastname.getText());
                            ps.setString(4, txt_phonenumber.getText());
                            ps.setString(5, txt_title.getText());
                            ps.setString(6, txt_username.getText());
                            ps.setString(7, txt_password.getText());
                            ps.setBlob(8, img);
                            
                            ps.setInt(9, Integer.parseInt(txt_employeeid.getText()));
                            ps.executeUpdate();
                            Show_Staff_In_JTable();
                            
                            JOptionPane.showMessageDialog(null,"Employee has been updated");
                            
  
                        } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null,ex.getMessage());
                                Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            } else {
                      JOptionPane.showMessageDialog(null, "One or More Fields Are Empty or Wrong");
        }
    }//GEN-LAST:event_btn_updateActionPerformed

// Button Delete the data from JavaDB database    
    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
       
        if(!txt_employeeid.getText().equals(""))
        {
            try{
                Connection con = getConnection();
                String sqlDelete = "DELETE FROM mv_movies WHERE movieid=?";
                PreparedStatement ps = con.prepareStatement(sqlDelete);
                
                int movieid = Integer.parseInt(txt_employeeid.getText());
                ps.setInt(1, movieid);
                ps.executeUpdate();
                Show_Staff_In_JTable();
                Show_StaffTotals_In_JTable();
                
                JOptionPane.showMessageDialog(null, "Product Deleted");
                
            }catch (SQLException ex){
                Logger.getLogger(Staff_Total.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Product Not Deleted");
            }
        }else{
        JOptionPane.showMessageDialog(null,"Product Not Deleted: No Movie ID to Delete");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

// JTable Mouse Clicked
// Display the selected row data into JTextFields
// and the image into JLabel       
    private void JTable_MoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_MoviesMouseClicked
  
        int index = JTable_Movies.getSelectedRow();
        ShowItem(index);
       
    }//GEN-LAST:event_JTable_MoviesMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getMoviesList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;
        if( pos >= getMoviesList().size())
        {
            pos = getMoviesList().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_previousActionPerformed
        pos--;
        if(pos<0)
        {
            pos=0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_previousActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        txt_employeeid.setText(null);
        txt_firstname.setText(null);
        txt_releasedate.setDate(null);
        txt_lastname.setText(null);
        txt_phonenumber.setText(null);
        txt_title.setText(null);
        txt_username.setText(null);
        txt_password.setText(null);
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        MainMenu.MenuFrame Info = new MainMenu.MenuFrame();
        Info.setVisible(true);    
    }//GEN-LAST:event_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(Staff_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff_Total.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff_Total().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel;
    private javax.swing.JTable JTable_Movies;
    private javax.swing.JTable JTable_StaffTotals;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_previous;
    private javax.swing.JButton btn_update;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private datechooser.beans.DateChooserDialog dateChooserDialog4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txt_employeeid;
    private javax.swing.JTextField txt_firstname;
    private javax.swing.JTextField txt_lastname;
    private javax.swing.JTextField txt_password;
    private javax.swing.JTextField txt_phonenumber;
    private com.toedter.calendar.JDateChooser txt_releasedate;
    private javax.swing.JTextField txt_title;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
