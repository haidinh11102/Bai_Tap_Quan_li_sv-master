/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLSV;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.*;
//đếm data
public class MyFunction {
    public static int countData(String tableName){
        int total = 0;
        Connection con = MyConnection.getConnection();
        Statement st;
        try
        {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS 'total' FROM "+tableName);
            while(rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException ex)
        {
           java.util.logging.Logger.getLogger(MyFunction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return total;
    }
}
