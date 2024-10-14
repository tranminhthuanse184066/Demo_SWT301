/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Cart;
import sample.shopping.ProductDAO;
import sample.shopping.ProductDTO;
import sample.user.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR_LOGIN = "login.jsp";
    private static final String ERROR_CART = "SearchProductController";
    private static final String ERROR_CHECKOUT = "viewCart.jsp";
    private static final String SUCCESS = "SearchProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_LOGIN;
        double total = 0;
        try {
            HttpSession session = request.getSession();
                String temp =request.getParameter("total");
            if(!temp.equals("")){
                total = Double.parseDouble(temp);
            }
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            if (loginUser == null ) {
                request.setAttribute("ERROR", "Ban chưa đăng nhập");
            } else {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    request.setAttribute("MESSAGE", "Bạn chưa chọn bất kì món hàng nào");
                    url = ERROR_CART;
                } else {
                    ProductDAO dao = new ProductDAO();
                    for (ProductDTO product : cart.getCart().values()) {
                        int checkStock = dao.checkStock(product);
                        if (checkStock < product.getQuantity()) {
                            request.setAttribute("MESSAGE", "Không đủ hàng trong kho");
                            url = ERROR_CHECKOUT;
                        }
                    }
                    if (!ERROR_CHECKOUT.equals(url)) {
                        url = SUCCESS;
                        request.setAttribute("MESSAGE", "Thanh toán thành công");
                        dao.setOrder(loginUser, total);
                        for (ProductDTO product : cart.getCart().values()) {
                            dao.updateStock(product);
                            dao.setOrderDetail(loginUser,product);
                        }
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckOutController:" + e.toString());
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
