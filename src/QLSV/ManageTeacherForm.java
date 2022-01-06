/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLSV;


import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author DELL
 */
public class ManageTeacherForm extends javax.swing.JFrame {

    /**
     * Creates new form ManageTeacherForm
     */
    public ManageTeacherForm() {
        initComponents();
        setLocationRelativeTo(null);
        Show_Teachers_In_JTable();
        tbl_teacher.setRowHeight(40);
        tbl_teacher.setShowGrid(true);
        tbl_teacher.setGridColor(Color.gray);
    }

    String ImgPath = null;
    int pos = 0;
    // Resize Image (kích thước ảnh)
   public ImageIcon ResizeImage(String imagePath, byte[] pic){
       ImageIcon myimage = null;
       
       if(imagePath != null)
       {
           myimage = new ImageIcon(imagePath);          
       }else{
           myimage = new ImageIcon(pic);
       }
       
       Image img = myimage.getImage();
       Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
       ImageIcon image = new ImageIcon(img2);
       
       return image;
   }
   
   // Check Input Fields(kiểm tra đầu vào)
   
   public boolean checkInputs(){
       if(txt_name.getText() == null
          || DateChooser_Birthdate.getDate() == null          
          || txt_email.getText() == null
          || txt_address.getText() == null
          || txt_phone_number.getText() == null
          )
       {
           return false;
       }
       // Khong cho chon ngay lon hon hien tai
       else if(DateChooser_Birthdate.getDate().compareTo(new Date()) > 0){
           JOptionPane.showMessageDialog(null, "No teacher from future are allowed !!!");
           return false;
       }
       else{
           try
           {
               return true;
           } catch (Exception e)
           {
               return false;
           }
       }
   }

   // Display Data in JTable(hiện data)
   // 1 - Fill ArrayList with the data(nhập dữ liệu)
   public ArrayList<teacher> getTeacherList(){
            ArrayList <teacher> teacherList = new ArrayList<teacher>();
            Connection con = MyConnection.getConnection();
            String query = "SELECT * FROM teacher";
            
            Statement st;
            ResultSet rs;
        try
        {                       
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            teacher tch;
            
            while(rs.next()){
                tch = new teacher(rs.getInt("id"), rs.getString("name"), rs.getString("gender"),
                         rs.getString("Birthdate"), rs.getString("Email"),
                        rs.getString("Address"), rs.getString("Phone_number"), rs.getBytes("Image"));
                teacherList.add(tch);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ManageTeacherForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return teacherList;
   }
   
   
   
   // 2 - Populate the JTable(điền dữ liệu)
   public void Show_Teachers_In_JTable(){
       ArrayList<teacher> list = getTeacherList();
       DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
       // Clear JTable content(Xóa dữ liệu)
       model.setRowCount(0);
       Object[] row = new Object[7];
       for(int i = 0; i < list.size(); i++){
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getName();
           row[2] = list.get(i).getGender();
           row[3] = list.get(i).getBdate();
           row[4] = list.get(i).getEmail();
           row[5] = list.get(i).getAddress();
           row[6] = list.get(i).getPhonenumber();
           
           model.addRow(row);
       }
   }
   
   // Show Item(hiện thị mục)
   public void ShowTeacher(int rowIndex){
       DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
        txt_id.setText(model.getValueAt(rowIndex, 0).toString());
        txt_name.setText(model.getValueAt(rowIndex, 1).toString());
        if(model.getValueAt(rowIndex, 2).toString().equals("male")){
            rbd_male.setSelected(true);
            rbd_female.setSelected(false);
        }else{
            rbd_female.setSelected(true);
            rbd_male.setSelected(false);
        }
        try
        {
            
            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getTeacherList().get(rowIndex).getBdate());
            DateChooser_Birthdate.setDate(addDate);
        } catch (ParseException ex)
        {
            Logger.getLogger(ManageTeacherForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_email.setText(model.getValueAt(rowIndex, 4).toString());
        txt_address.setText(model.getValueAt(rowIndex, 5).toString());
        txt_phone_number.setText(model.getValueAt(rowIndex, 6).toString());
        lbl_image.setIcon(ResizeImage(null, getTeacherList().get(rowIndex).getPicture()));
   }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        btn_Choose_image = new javax.swing.JButton();
        txt_name = new javax.swing.JTextField();
        txt_phone_number = new javax.swing.JTextField();
        txt_address = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        rbd_male = new javax.swing.JRadioButton();
        rbd_female = new javax.swing.JRadioButton();
        DateChooser_Birthdate = new com.toedter.calendar.JDateChooser();
        btn_edit = new javax.swing.JButton();
        btn_remove = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_teacher = new javax.swing.JTable();
        btn_first = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_val_find = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Manage Teachers");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Gender :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Birth date :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Address :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Phone :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Email :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Image :");

        lbl_image.setBackground(new java.awt.Color(255, 204, 102));
        lbl_image.setOpaque(true);

        btn_Choose_image.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Choose_image.setText("Choose Image ");
        btn_Choose_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Choose_imageActionPerformed(evt);
            }
        });

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_phone_number.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_phone_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_phone_numberKeyTyped(evt);
            }
        });

        txt_address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        rbd_male.setText("Male ");

        rbd_female.setText("Female");

        DateChooser_Birthdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_edit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/updated.png"))); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_remove.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/x-mark-24.png"))); // NOI18N
        btn_remove.setText("Remove");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Id :");

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus-5-24 (1).png"))); // NOI18N
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        tbl_teacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Gender", "Birth date", "Email", "Address", "Phone "
            }
        ));
        tbl_teacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_teacherMouseClicked(evt);
            }
        });
        tbl_teacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_teacherKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_teacher);

        btn_first.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/first-24.png"))); // NOI18N
        btn_first.setText("First");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_prev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/previous-24.png"))); // NOI18N
        btn_prev.setText("Previous");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/next-24.png"))); // NOI18N
        btn_next.setText("Next");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_next.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/last-24.png"))); // NOI18N
        btn_last.setText("Last");
        btn_last.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Enter value to search :");

        txt_val_find.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_val_find.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_val_findKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_val_findKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel10)
                        .addGap(30, 30, 30)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txt_val_find, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(rbd_male, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(rbd_female))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30)
                                .addComponent(DateChooser_Birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6)
                                .addGap(30, 30, 30)
                                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30)
                                .addComponent(txt_phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(btn_Choose_image, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(btn_prev)
                        .addGap(17, 17, 17)
                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(jLabel1)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_val_find, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel3))
                            .addComponent(rbd_male)
                            .addComponent(rbd_female))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(DateChooser_Birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8))
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))
                            .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(txt_phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Choose_image))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_remove)
                    .addComponent(btn_edit)
                    .addComponent(btn_add)
                    .addComponent(btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_prev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Choose_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Choose_imageActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path,null));
            ImgPath = path;
        }
        else{
            System.out.println("No file Selected !!!");
        }
    }//GEN-LAST:event_btn_Choose_imageActionPerformed

    private void txt_phone_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_phone_numberKeyTyped
        // Allow only number
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txt_phone_numberKeyTyped

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        if(checkInputs() && txt_id.getText() != null){
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = MyConnection.getConnection();

            // Update without Image
            if(ImgPath == null){
                try {
                    UpdateQuery = "UPDATE teacher SET name = ?, gender = ?, Birthdate = ?, Email = ?, Address = ?"
                    + ", Phone_number = ? WHERE id = ?";

                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    // Dua gioi tinh vao trong co so du lieu
                    String sex = "male";
                    if(rbd_female.isSelected()){
                        sex = "female";
                    }
                    ps.setString(2, sex);
                    // Them ngay thang (Date)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(DateChooser_Birthdate.getDate());
                    ps.setString(3, addDate);
                    // them cac du kien khac
                    ps.setString(4, txt_email.getText());
                    ps.setString(5, txt_address.getText());
                    ps.setString(6, txt_phone_number.getText());
                    ps.setInt(7, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();

                    Show_Teachers_In_JTable();

                    JOptionPane.showMessageDialog(null, "Teacher Updated !!!");
                    MainFormForAdmin.lbl_teacher_count.setText("Teachers count = " + Integer.toString(MyFunction.countData("teacher")));
                } catch (SQLException ex) {
                    Logger.getLogger(ManageTeacherForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            // Update with Image
            else{

                try
                {
                    InputStream img = new FileInputStream(new File(ImgPath));

                    UpdateQuery = "UPDATE teacher SET name = ?, gender = ?, Birthdate = ?, Email = ?, Address = ?"
                    + ", Phone_number = ?, Image = ? WHERE id = ?";

                    ps = con.prepareStatement(UpdateQuery);

                    ps.setString(1, txt_name.getText());
                    // Dua gioi tinh vao trong co so du lieu
                    String sex = "male";
                    if(rbd_female.isSelected()){
                        sex = "female";
                    }
                    ps.setString(2, sex);
                    // Them ngay thang (Date)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(DateChooser_Birthdate.getDate());
                    ps.setString(3, addDate);
                    // them cac du kien khac
                    ps.setString(4, txt_email.getText());
                    ps.setString(5, txt_address.getText());
                    ps.setString(6, txt_phone_number.getText());
                    // Them anh vao databases
                    ps.setBlob(7, img);
                    ps.setInt(8, Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();

                    Show_Teachers_In_JTable();

                    JOptionPane.showMessageDialog(null, "Teacher Updated !!!");
                    MainFormForAdmin.lbl_teacher_count.setText("Teachers count = " + Integer.toString(MyFunction.countData("teacher")));
                } catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        }else{
            JOptionPane.showMessageDialog(null, "One or More Fields are empty or wrong !!!");
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        if(!txt_id.getText().equals("")){
            Connection con = MyConnection.getConnection();
            try
            {
                PreparedStatement ps = con.prepareStatement("DELETE FROM teacher WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Teachers_In_JTable();
                JOptionPane.showMessageDialog(null, "Teacher deleted !!!");
                MainFormForAdmin.lbl_teacher_count.setText("Teachers count = " + Integer.toString(MyFunction.countData("teacher")));
            } catch (SQLException ex)
            {
                Logger.getLogger(ManageTeacherForm.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Teacher not deleted !!!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Teacher not deleted : No id to delete !!!");
        }
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        AddTeacherForm atf = new AddTeacherForm();
        atf.setVisible(true);
        atf.pack();
        atf.setLocationRelativeTo(null);
        atf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Show_Teachers_In_JTable();
    }//GEN-LAST:event_btn_addActionPerformed

    private void tbl_teacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_teacherMouseClicked

        int rowIndex = tbl_teacher.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
        txt_id.setText(model.getValueAt(rowIndex, 0).toString());
        txt_name.setText(model.getValueAt(rowIndex, 1).toString());
        if(model.getValueAt(rowIndex, 2).toString().equals("male")){
            rbd_male.setSelected(true);
            rbd_female.setSelected(false);
        }else{
            rbd_female.setSelected(true);
            rbd_male.setSelected(false);
        }
        try
        {

            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getTeacherList().get(rowIndex).getBdate());
            DateChooser_Birthdate.setDate(addDate);
        } catch (ParseException ex)
        {
            Logger.getLogger(ManageStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_email.setText(model.getValueAt(rowIndex, 4).toString());
        txt_address.setText(model.getValueAt(rowIndex, 5).toString());
        txt_phone_number.setText(model.getValueAt(rowIndex, 6).toString());
        lbl_image.setIcon(ResizeImage(null, getTeacherList().get(rowIndex).getPicture()));
    }//GEN-LAST:event_tbl_teacherMouseClicked

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        pos = 0;
        ShowTeacher(pos);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        pos--;

        if(pos < 0){
            pos = 0;
        }
        ShowTeacher(pos);
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        pos++;

        if(pos >= getTeacherList().size()){
            pos = getTeacherList().size() - 1;
        }
        ShowTeacher(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        pos = getTeacherList().size() -1;
        ShowTeacher(pos);
    }//GEN-LAST:event_btn_lastActionPerformed

    private void txt_val_findKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_val_findKeyReleased
        DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        tbl_teacher.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txt_val_find.getText().trim()));
    }//GEN-LAST:event_txt_val_findKeyReleased

    private void txt_val_findKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_val_findKeyTyped

    }//GEN-LAST:event_txt_val_findKeyTyped

    private void tbl_teacherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_teacherKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN){
            int rowIndex = tbl_teacher.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
        txt_id.setText(model.getValueAt(rowIndex, 0).toString());
        txt_name.setText(model.getValueAt(rowIndex, 1).toString());
        if(model.getValueAt(rowIndex, 2).toString().equals("male")){
            rbd_male.setSelected(true);
            rbd_female.setSelected(false);
        }else{
            rbd_female.setSelected(true);
            rbd_male.setSelected(false);
        }
        try
        {

            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getTeacherList().get(rowIndex).getBdate());
            DateChooser_Birthdate.setDate(addDate);
        } catch (ParseException ex)
        {
            Logger.getLogger(ManageStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_email.setText(model.getValueAt(rowIndex, 4).toString());
        txt_address.setText(model.getValueAt(rowIndex, 5).toString());
        txt_phone_number.setText(model.getValueAt(rowIndex, 6).toString());
        lbl_image.setIcon(ResizeImage(null, getTeacherList().get(rowIndex).getPicture()));
        }
    }//GEN-LAST:event_tbl_teacherKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ManageTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageTeacherForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser_Birthdate;
    private javax.swing.JButton btn_Choose_image;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_remove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JRadioButton rbd_female;
    private javax.swing.JRadioButton rbd_male;
    public static javax.swing.JTable tbl_teacher;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone_number;
    private javax.swing.JTextField txt_val_find;
    // End of variables declaration//GEN-END:variables
}
