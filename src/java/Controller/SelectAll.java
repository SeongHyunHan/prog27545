/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Customer;
import data.CustomerDB;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author seong
 */
public class SelectAll extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Customer> res;
        RequestDispatcher rd;
        try {
            String error = "";
            double total = 0;
            Date date = new Date();
            String dateStr = request.getParameter("date");
            String firstName = "";
            String lastName = "";
            String totalStr = request.getParameter("total");
            String branch = request.getParameter("branch");
            String manager = request.getParameter("manager");
            String accountNumber = "";
            
            if(dateStr.equals("") || dateStr == null){
                error += "Date field can not be Empty <br>";
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(dateStr);
            }
            if(totalStr.equals("") || totalStr == null){
                error += "Total field can not be Empty <br>";
            }else{
                total = Double.parseDouble(totalStr);
            }
            if(branch.equals("") || branch == null)
                    error += "Branch field can not be Empty <br>";
            if(manager.equals("") || manager == null)
                error += "Manager field can not be Empty <br>";

            if(error.equals("") || error == null){
                Customer customerInfo = new Customer(1, firstName, lastName, date, 
                        accountNumber, total, branch, manager); 
                res = CustomerDB.show(customerInfo);
                request.setAttribute("customers", res);
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("error", "For Select(4 Field Filter): <br> " + error);
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SelectAll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SelectAll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SelectAll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SelectAll.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SelectAll.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

}
