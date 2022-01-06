
package QLSV;

import java.sql.Connection;
import java.sql.DriverManager;

//kết nối với data
public class MyConnection {
    public static Connection getConnection(){
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/appqlsv","root","");
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
