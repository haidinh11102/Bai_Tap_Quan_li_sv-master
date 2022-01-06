
package QLSV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

public class major {
    
    private int id;
    private String name;
    private Integer student_number;

    public major(int id, String name, Integer student_number) {
        this.id = id;
        this.name = name;
        this.student_number = student_number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStudent_number() {
        return student_number;
    }
    
    // Kiem tra xem co ton tai khoa hoc giong tu truoc ko
    public static boolean isMajorExist(String majorname){
        
        boolean isExist = false;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
           try
           {
               ps = con.prepareStatement("SELECT * FROM `course` WHERE `label` = ?");
               ps.setString(1, majorname);
               
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   isExist = true;
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(major.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        return isExist;
    }
    // lấy ra id
    private int getMajorId(String majorname){
        int MajorId = 0;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try
        {
            ps = con.prepareStatement("SELECT * FROM `major` WHERE `name` = ?");
            ps.setString(1, majorname);
            
            ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   MajorId = rs.getInt("id");
               }
        } catch (SQLException ex)
        {
            Logger.getLogger(major.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MajorId;
    }
    
    public static void fillMajorCombo(JComboBox combo){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try
        {
            ps = con.prepareStatement("SELECT * FROM `major`");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(major.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
