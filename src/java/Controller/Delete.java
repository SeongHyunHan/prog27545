/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import data.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Delete extends HttpServlet {

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
            String noStr = request.getParameter("no");
            if(noStr.equals("") || noStr == null){
                String error = "No Field can not be Empty";
                request.setAttribute("error", "To Delete Data: <br> " + error);
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }else{
                int no = Integer.parseInt(noStr);
                CustomerDB.delete(no);
                result = CustomerDB.showAll();
                request.setAttribute("customers", result);
                request.setAttribute("result", "Delete No." + no + " Successful");

                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } catch (NullPointerException ex){
            request.setAttribute("result", "Delete No.4 or No.9 Failed <br>"
                        + "Data Not Exist");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
