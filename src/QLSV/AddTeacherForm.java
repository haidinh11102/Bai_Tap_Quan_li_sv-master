/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLSV;

import static QLSV.ManageTeacherForm.tbl_teacher;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class AddTeacherForm extends javax.swing.JFrame {

    /**
     * Creates new form AddTeacherForm
     */
    public AddTeacherForm() {
        initComponents();
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbd_female);
        bg.add(rbd_male);
        rbd_male.setSelected(true);
    }

    String ImgPath = null; // Duong dan de chon anh
    
    // Resize Image 
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
   
   // Choose Image 
    private void btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {                                                 

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
    }
    
     // Check Input Fields
   
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
    
   // Display Data in JTable
   // 1 - Fill ArrayList with the data
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
   
   
   
   // 2 - Populate the JTable
   public void Show_Teachers_In_JTable(){
       ArrayList<teacher> list = getTeacherList();
       DefaultTableModel model = (DefaultTableModel)tbl_teacher.getModel();
       // Clear JTable content
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
        btn_add = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("New Teacher");

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

        btn_add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/plus-5-24 (1).png"))); // NOI18N
        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/x-mark-24.png"))); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_name, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_phone_number, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txt_address, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbd_male)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbd_female))
                                    .addComponent(DateChooser_Birthdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_cancel)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_Choose_image, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbd_male)
                    .addComponent(rbd_female))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(DateChooser_Birthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_phone_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_Choose_image)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_add))
                .addContainerGap(64, Short.MAX_VALUE))
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

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg","png","jpeg");
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

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        if(checkInputs() && ImgPath != null){
            try
            {
                Connection con = MyConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO teacher(name, gender, Birthdate, Email, Address, Phone_number, Image) VALUES (?,?,?,?,?,?,?)");
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
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(7, img);
                ps.executeUpdate();
                Show_Teachers_In_JTable();
                JOptionPane.showMessageDialog(null, "New Teacher Added !!!");
                MainFormForAdmin.lbl_teacher_count.setText("Teachers count = " + Integer.toString(MyFunction.countData("teacher")));
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "One or More Fields are empty");
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AddTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AddTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AddTeacherForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddTeacherForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateChooser_Birthdate;
    private javax.swing.JButton btn_Choose_image;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JRadioButton rbd_female;
    private javax.swing.JRadioButton rbd_male;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone_number;
    // End of variables declaration//GEN-END:variables
}
