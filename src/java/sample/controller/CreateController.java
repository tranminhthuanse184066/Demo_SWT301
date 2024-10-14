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
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR="create.jsp";
    private static final String SUCCESS="login.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        UserError userError= new UserError();
        try {
            UserDAO dao= new UserDAO();
            boolean checkValidation= true;
            String userID= request.getParameter("userID");
            String fullName= request.getParameter("fullName");
            String roleID= request.getParameter("roleID");
            String password= request.getParameter("password");
            String confirm= request.getParameter("confirm");
//            Validation
            if(userID.length()<2 || userID.length()>30){
                checkValidation= false;
                userError.setUserIDError("UserID nên dài khoảng [2,30]");
            }
            if(fullName.length()<5 || fullName.length()>50){
                checkValidation= false;
                userError.setFullNameError("Full Name nên dài khoảng [5,50]");
            }
            if(!confirm.equals(password)){
                checkValidation= false;
                userError.setConfirmError("Hai password không giống nhau !");
            }
//            boolean checkDuplicate= dao.checkDuplicate(userID);
//            if(checkDuplicate){
//                userError.setUserIDError("Duplicate userID!");
//                checkValidation= false;
//            }
            if(checkValidation){
                UserDTO user= new UserDTO(userID, fullName, roleID, password);
                boolean checkInsert= dao.insert(user);
                if(checkInsert){
                    url= SUCCESS;
                }else{
                    userError.setError("Unknow Error!");
                    request.setAttribute("USER_ERROR", userError);
                }
            }else{
                request.setAttribute("USER_ERROR", userError);
            }
            
        } catch (Exception e) {
            log("Error at CreateController: "+ e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserIDError("UserID đã tồn tại");
                request.setAttribute("USER_ERROR", userError);
            }
        }finally{
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
