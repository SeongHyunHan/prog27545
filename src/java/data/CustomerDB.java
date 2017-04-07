
package data;

import Model.Customer;
import data.DBUtil;
import data.DBConn;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDB {
    public static int addCustomer(Customer c) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection myConn = null;
        PreparedStatement ps = null;
        DBConn connManager;
        
        connManager = new DBConn();
        myConn = connManager.connection();
        
        try{
            
            String pstm = "INSERT INTO customerinfo(firstName, lastName, date, "
                    + "accountNumber, total, branch, manager) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            ps = myConn.prepareStatement(pstm);
            
            java.sql.Date sqlDate = new java.sql.Date(c.getDate().getTime());
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setDate(3, sqlDate);
            ps.setString(4, c.getAccountNumber());
            ps.setDouble(5, c.getTotal());
            ps.setString(6, c.getBranch());
            ps.setString(7, c.getManager());

            return ps.executeUpdate();            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }finally{
            DBUtil.closePreparedStatement(ps);
            connManager.endConnection(myConn);
        }
    }
    
    public static int updateCustomerInfo(Customer c) throws SQLException, ClassNotFoundException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection myConn = null;
        PreparedStatement ps = null;
        DBConn connManager;
        
        connManager = new DBConn();
        myConn = connManager.connection();
        
         try{
            
            String pstm = "UPDATE customerinfo SET lastName = ?, firstName = ?, date = ?, "
                    + "accountNumber = ?, total = ?, branch = ?, manager = ? "
                    + "WHERE no = ?";
            
            ps = myConn.prepareStatement(pstm);
            ps.setString(1, c.getLastName());
            ps.setString(2, c.getFirstName());
            java.sql.Date sqlDate = new java.sql.Date(c.getDate().getTime());
            ps.setDate(3, sqlDate);
            ps.setString(4, c.getAccountNumber());
            ps.setDouble(5, c.getTotal());
            ps.setString(6, c.getBranch());
            ps.setString(7, c.getManager());
            ps.setInt(8, c.getNo());
            
            return ps.executeUpdate();            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return 0;
        }finally{
            DBUtil.closePreparedStatement(ps);
            connManager.endConnection(myConn);
        }
    }
    
    public static ArrayList<Customer> show(Customer c) throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            IllegalAccessException{
        Statement myStm = null;
        Connection myConn;
        DBConn connManager;
        ResultSet res = null;
        ArrayList<Customer>  account = new ArrayList();
        int no = 0;
        Date date;
        String accountNumber = null;
        String firstName = null;
        String lastName = null;
        String branch = null;
        String manager = null;
        double total = 0;
        
        connManager = new DBConn();
        myConn = connManager.connection();
         
        try{    
            String stm = "SELECT * FROM customerinfo "
                    + "WHERE branch = '" + c.getBranch() + "' AND "
                    + "manager = '" + c.getManager() + "' AND "
                    + "date < '" + c.getDate()
                    + "' AND total > " + c.getTotal();
            
            myStm = myConn.createStatement();

            res = myStm.executeQuery(stm);
            while(res.next()){
                no = res.getInt("no");
                firstName = res.getString("firstName");
                lastName = res.getString("lastName");
                date = res.getDate("date");
                accountNumber = res.getString("accountNumber");
                total = res.getDouble("total");
                branch = res.getString("branch");
                manager = res.getString("manager");
                account.add(new Customer(no, firstName, lastName, date, accountNumber, 
                total, branch, manager));
            }
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null; 
        }finally{
            DBUtil.closeStatement(myStm);
            DBUtil.closeResultSet(res);
            connManager.endConnection(myConn);
        }
        
    }
    
    public static void delete(int no) throws SQLException, ClassNotFoundException,
            ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection myConn = null;
        PreparedStatement ps = null;
        DBConn connManager;
        
        connManager = new DBConn();
        myConn = connManager.connection();
        
         try{
            
            String pstm = "DELETE FROM customerinfo WHERE no = ?";
           
            ps = myConn.prepareStatement(pstm);
            ps.setInt(1, no);
            ps.execute();   
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            DBUtil.closePreparedStatement(ps);
            connManager.endConnection(myConn);
        }
    }
    
    public static ArrayList<Customer> showAll() throws SQLException, 
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            IllegalAccessException{
        Statement myStm = null;
        Connection myConn;
        DBConn connManager;
        ResultSet res = null;
        ArrayList<Customer>  account = new ArrayList();
        int no = 0;
        Date date;
        String accountNumber = null;
        String firstName = null;
        String lastName = null;
        String branch = null;
        String manager = null;
        double total = 0;
        
        connManager = new DBConn();
        myConn = connManager.connection();
         
        try{    
            String stm = "SELECT * FROM customerinfo";
            myStm = myConn.createStatement();
            res = myStm.executeQuery(stm);
            while(res.next()){
                no = res.getInt("no");
                firstName = res.getString("firstName");
                lastName = res.getString("lastName");
                date = res.getDate("date");
                accountNumber = res.getString("accountNumber");
                total = res.getDouble("total");
                branch = res.getString("branch");
                manager = res.getString("manager");
                account.add(new Customer(no, firstName, lastName, date, accountNumber, 
                total, branch, manager));
            }
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null; 
        }finally{
            DBUtil.closeStatement(myStm);
            DBUtil.closeResultSet(res);
            connManager.endConnection(myConn);
        }
        
    }
    
    public static Customer retrieve(int n) throws SQLException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IllegalAccessException{
        Statement myStm = null;
        Connection myConn;
        DBConn connManager;
        ResultSet res = null;
        Customer c = null;
        int no;
        Date date;
        String accountNumber;
        String firstName;
        String lastName;
        String branch;
        String manager;
        double total = 0;
        
        connManager = new DBConn();
        myConn = connManager.connection();
         
        try{    
            String stm = "SELECT * FROM customerinfo "
                    + "WHERE no = " + n;
            
            myStm = myConn.createStatement();

            res = myStm.executeQuery(stm);
            while(res.next()){
                no = res.getInt("no");
                firstName = res.getString("firstName");
                lastName = res.getString("lastName");
                date = res.getDate("date");
                accountNumber = res.getString("accountNumber");
                total = res.getDouble("total");
                branch = res.getString("branch");
                manager = res.getString("manager");
                c = new Customer(no, firstName, lastName, date, accountNumber, 
                total, branch, manager);
            }
            return c;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null; 
        }finally{
            DBUtil.closeStatement(myStm);
            DBUtil.closeResultSet(res);
            connManager.endConnection(myConn);
        }
    }
    
}
