/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package google;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserGoogleDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    public static final String ERROR = "create.jsp";
    public static final String GOOGLE_CLIENT_ID = "295697103075-tadgilba9vmvhodcfrl520e52arhdstc.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET = "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";
    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8084/PRJ301-T4S4-JSP_V2_JSTL/LoginGoogleController";
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";
    public static final String SUCCESS = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDTO temp = getUserInfo(accessToken);
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO(temp.getId(), temp.getName(), "US", temp.getPassword());
            boolean check = dao.checkDuplicate(user.getUserID());
            if(check){    
                session.setAttribute("LOGIN_USER", user);
                url = SUCCESS;
            }else{
               request.setAttribute("ID_GOOGLE", user);
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
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
