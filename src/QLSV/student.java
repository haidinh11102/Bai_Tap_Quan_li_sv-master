
package QLSV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;




public class student {
    private int id;
    private String name;
    private String gender;
    private String bdate;
    private String major;
    private String address;
    private String phonename;
    private String email;
    private byte[] picture;

    public student(int id, String name, String gender, String bdate, String major, String address, String phonename, String email, byte[] picture) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.bdate = bdate;
        this.major = major;
        this.address = address;
        this.phonename = phonename;
        this.email = email;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBdate() {
        return bdate;
    }

    public String getMajor() {
        return major;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonename() {
        return phonename;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPicture() {
        return picture;
    }
    
    private int getStudentId(String studentname){
        int studentId = 0;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
           try
           {
               ps = con.prepareStatement("SELECT * FROM `student` WHERE `name` = ?");
               ps.setString(1, studentname);
               
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   studentId = rs.getInt("id");
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
           }
        return studentId;
    }
    //lay du lieu 
    public static void fillStudentCombo(JComboBox combo){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try
        {
            ps = con.prepareStatement("SELECT * FROM `student`");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
