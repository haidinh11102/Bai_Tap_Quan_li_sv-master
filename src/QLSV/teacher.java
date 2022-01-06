
package QLSV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;


public class teacher {
    private int id;
    private String name;
    private String gender;
    private String bdate;
    private String email;
    private String address;
    private String phonenumber;
    private byte[] picture;

    public teacher(int id, String name, String gender, String bdate, String email, String address, String phonenumber, byte[] picture) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.bdate = bdate;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
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

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public byte[] getPicture() {
        return picture;
    }
    //viết ra id 
    private int getStudentId(String teachername){
        int teacherId = 0;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
           try
           {
               ps = con.prepareStatement("SELECT * FROM `teacher` WHERE `name` = ?");
               ps.setString(1, teachername);
               
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   teacherId = rs.getInt("id");
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(teacher.class.getName()).log(Level.SEVERE, null, ex);
           }
        return teacherId;
    }
    //hiện ra ở combobox
    public static void fillTeacherCombo(JComboBox combo){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try
        {
            ps = con.prepareStatement("SELECT * FROM `teacher`");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
