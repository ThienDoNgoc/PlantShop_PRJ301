/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import basicObject.Plant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myDAO.PlantDao;

/**
 *
 * @author Thien Do
 */
public class searchServlet extends HttpServlet {

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
            String keyword = request.getParameter("txtsearch");
            String searchby = request.getParameter("searchby");
            ArrayList<Plant> list = new ArrayList<>();
            list = PlantDao.getPlants(keyword, searchby);
//            out.print(searchby);
            
            if (list != null && !list.isEmpty()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet searchServlet</title>");
                out.print("<link rel ='stylesheet' href='mycss.css' type='text/css' /> ");
                out.println("</head>");
                out.println("<body><section>");
                ServletContext context = getServletContext();
                String tmp=context.getInitParameter("countryName");
                out.print("<p>The website is deploying in "+tmp+"</p>");
                ServletConfig servletconfig = getServletConfig();
                String tmp2=servletconfig.getInitParameter("language");
                out.print("<p>Language in this website is: "+tmp2+"</p>");
                out.println("<table class='producttable'");
                out.print("<tr><td>product id</td><td>name</td><td>price</td><td>image</td><td>detail</td><td>action</td></tr>");
                for (Plant plant : list) {
                    out.print("<tr>");
                    out.print("<td>" + plant.getId() + "</td>");
                    out.print("<td>" + plant.getName() + "</td>");
                    out.print("<td>" + plant.getPrice() + "</td>");
                    out.print("<td><img src='" + plant.getImgpath() + "' class='product'/></td>");
                    out.print("<td><a href='PlantDetail.jsp'>view detail</a></td>");
                    out.print("<td><a href='#'>add to your cart</a></td>");
                    out.print("</tr>");
                }
                out.print("</table></section>");
                out.println("</body>");
                out.println("</html>");
            }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
