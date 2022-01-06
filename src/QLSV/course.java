
package QLSV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

public class course {
    private int id;
    private String label;
    private String teacher;
    private Integer hours;
    private Integer credits;

       
    public course(int id,String label, String teacher, Integer hours, Integer credits) {
        this.id = id;
        this.label = label;
        this.teacher = teacher;
        this.hours = hours;
        this.credits = credits;
    }

    public String getLabel() {
        return label;
    }

    public Integer getHours() {
        return hours;
    }

    public String getTeacher() {
        return teacher;
    }    
    
    public Integer getCredits() {
        return credits;
    }

    public int getId() {
        return id;
    }
       
    // Kiem tra xem co ton tai khoa hoc giong tu truoc ko
    public static boolean isCourseExist(String coursename){
        
        boolean isExist = false;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
           try
           {
               ps = con.prepareStatement("SELECT * FROM `course` WHERE `label` = ?");
               ps.setString(1, coursename);
               
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   isExist = true;
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(course.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        return isExist;
    }
    
    // lay id cua course
    private int getCourseId(String courselabel){
        int courseId = 0;
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
           try
           {
               ps = con.prepareStatement("SELECT * FROM `course` WHERE `label` = ?");
               ps.setString(1, courselabel);
               
               ResultSet rs = ps.executeQuery();
               
               if(rs.next()){
                   courseId = rs.getInt("id");
               }
           } catch (SQLException ex)
           {
               Logger.getLogger(course.class.getName()).log(Level.SEVERE, null, ex);
           }
        return courseId;
    }
    //hien thong tin o combobox
    public static void fillCourseCombo(JComboBox combo){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try
        {
            ps = con.prepareStatement("SELECT * FROM `course`");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}
