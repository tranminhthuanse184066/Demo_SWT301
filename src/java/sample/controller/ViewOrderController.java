/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.OrderDAO;
import sample.shopping.OrderDTO;
import sample.user.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ViewOrderController", urlPatterns = {"/ViewOrderController"})
public class ViewOrderController extends HttpServlet {

    private static final String ERROR_LOGIN = "login.jsp";
    private static final String ERROR_ORDER = "order.jsp";
    private static final String SUCCESS = "order.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_ORDER;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            OrderDAO dao = new OrderDAO();
            if (loginUser == null) {
                request.setAttribute("ERROR", "Bạn chưa đăng nhập");
                url = ERROR_LOGIN;
            } else if (loginUser.getRoleID().equals("AD")) {
                List<OrderDTO> listOrder = dao.showOrder();
                if (listOrder.size() > 0) {
                    request.setAttribute("LIST_ORDER", listOrder);
                    url = SUCCESS;
                }else{
                    request.setAttribute("MESSAGE", "Bạn chưa có bất cứ đơn hàng nào");
                }
            } else {
                List<OrderDTO> listOrder = dao.showOrder(loginUser);
                if (listOrder.size() > 0) {
                    request.setAttribute("LIST_ORDER", listOrder);
                    url = SUCCESS;
                }else{
                    request.setAttribute("MESSAGE", "Bạn chưa có bất cứ đơn hàng nào");
                }
            }
        } catch (Exception e) {
            log("Error at ViewOrderController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
