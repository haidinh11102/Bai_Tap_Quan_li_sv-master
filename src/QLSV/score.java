
package QLSV;


import java.sql.*;
import javax.swing.JOptionPane;

public class score {
    private int id;
    private String studentId;
    private String courseId;
    private float student_score;
    private String descp;

    public score(int id, String studentId, String courseId, float student_score, String descp) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.student_score = student_score;
        this.descp = descp;
    }

    public int getId() {
        return id;
    }
    
    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public float getStudent_score() {
        return student_score;
    }

    public String getDescp() {
        return descp;
    }
    
    
    
    
    
}
