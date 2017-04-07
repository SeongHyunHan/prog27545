/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import data.CustomerDB;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author seong
 */
public class Update extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher rd;
            ArrayList<Customer> result = new ArrayList();
            Date date = new Date();
            String error = "";
            double total = 0;
            int no = 0;
            String noStr = request.getParameter("no");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dateStr = request.getParameter("date");
            String totalStr = request.getParameter("total");
            String branch = request.getParameter("branch");
            String manager = request.getParameter("manager");
            String accountNumber = request.getParameter("accountNumber");

            if(firstName.equals("") || firstName == null)
                error += "No field can not be Empty <br>";
            else
               no = Integer.parseInt(noStr);
            if(firstName.equals("") || firstName == null)
                error += "First Name field can not be Empty <br>";
            if(lastName.equals("") || lastName == null)
                error += "Last Name field can not be Empty <br>";
            if(accountNumber.equals("") || accountNumber == null)
                error += "Account Number field can not be Empty <br>";
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
                Customer customerInfo = new Customer(no, firstName, lastName, date, 
                        accountNumber, total, branch, manager); 
                int res = CustomerDB.updateCustomerInfo(customerInfo);

                if(res == 0)
                    request.setAttribute("result", "Update Failed");
                else{
                    result = CustomerDB.showAll();
                    request.setAttribute("customers", result);
                    request.setAttribute("result", "Update Successful");
                }
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("error", "For Update: <br> " + error);
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
