/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author seong
 */
public class DBConn {
    private Connection conn;
    public Connection connection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String driverName="com.mysql.jdbc.Driver";
        String dbUrl="jdbc:mysql://localhost:3306/banking";
        String userName="root";
        String password="admin";
        
        Class.forName(driverName).newInstance();
        try{
            conn = DriverManager.getConnection(dbUrl, userName, password);
            return conn;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public int endConnection(Connection c){
        try{
            if(c!=null){c.close();}
            return 1;
        }catch(SQLException e){
            return 0;
        }
    }
    
}
