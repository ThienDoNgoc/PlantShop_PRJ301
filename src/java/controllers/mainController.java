/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thien Do
 */
public class mainController extends HttpServlet {

    private String url = "errorpage.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if (action == null || action.equals("") || action.equals("search")) {
                url = "index.jsp";
            } else if (action.equals("login")) {
                url = "loginServlet";
            } else if (action.equals("register")) {
                url = "registerServlet";
            } else if (action.equals("logout")) {
                url = "logoutServlet";
            } else if (action.equals("addtocart")) {
                url = "addToCartServlet";
            } else if (action.equals("viewcart")) {
                url = "viewCart.jsp";
            } else if (action.equals("deleteCart")) {
                url = "deleteCartServlet";
            } else if (action.equals("saveOrder")) {
                url = "saveShoppingCartServlet";
            } else if (action.equals("manageAccounts")) {
                url = "manageAccountServlet";
            } else if (action.equals("updateStatusAccount")) {
                url = "updateStatusAccountServlet";
            } else if (action.equals("cancelOrder")) {
                url = "cancelOrderServlet";
            } else if (action.equals("update")) {
                url = "updateProfileServlet";
            } else if (action.equals("updatePlant")) {
                url = "updatePlantServlet";
            } else if (action.equals("updateCart")) {
                url = "updateCartServlet";
            } else if (action.equals("updateOrder")) {
                url = "updateOrderServlet";
            } else if (action.equals("createPlant")) {
                url = "createPlant.jsp";
            } else if (action.equals("createNewPlant")) {
                url = "createPlantServlet";
            } else if (action.equals("createCate")) {
                url = "createCate.jsp";
            } else if (action.equals("createNewCate")) {
                url = "createCateServlet";
            } else if (action.equals("ordStatus")) {
                url = "orderByStatusServlet";
            }else if (action.equals("updateCate")) {
                url = "updateCateServlet";
            }else if(action.equals("search Account")){
                url = "searchAccountServlet";
            }else if(action.equals("search Order by AccountID")){
                url = "searchOrderServlet";
            }
                    
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
