package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.user.UserDTO;
import sample.utils.DBUtils;

public class ProductDAO {

    private static final String SEARCH = "SELECT productID ,name, price,quantity FROM tblProducts WHERE name LIKE ?";
    private static final String CHECK_STOCK = "SELECT quantity FROM tblProducts WHERE productID = ?";
    private static final String UPDATE_STOCK = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";
    private static final String SET_ORDER = "INSERT INTO tblOrders(orderID, userID, total, date) VALUES(? + CAST((SELECT COUNT(orderID) FROM tblOrders) + 1 AS VARCHAR),?,?,(SELECT CURRENT_TIMESTAMP))";
    private static final String SET_ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID, productID, price, quantity) VALUES (? + CAST((SELECT COUNT(orderID) FROM tblOrders) AS VARCHAR),?,?,?) ";


    public List<ProductDTO> getListProduct(String search) throws SQLException, ClassNotFoundException, NamingException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new ProductDTO(productID, productName, price, quantity));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int checkStock(ProductDTO product) throws SQLException, ClassNotFoundException, NamingException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STOCK);
                ptm.setString(1, product.getId());
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = rs.getInt("quantity");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public void updateStock(ProductDTO product) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STOCK);
                ptm.setInt(1, checkStock(product) - product.getQuantity());
                ptm.setString(2, product.getId());
                ptm.executeUpdate();
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void setOrder(UserDTO loginUser, double total) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_ORDER);
                ptm.setString(1, loginUser.getUserID() + "_O");
                ptm.setString(2, loginUser.getUserID());
                ptm.setDouble(3, total);
                ptm.executeUpdate();
            }

        } catch (Exception e) {

        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void setOrderDetail(UserDTO loginUser, ProductDTO product) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_ORDER_DETAIL);
                ptm.setString(1, loginUser.getUserID() + "_O");
                ptm.setString(2, product.getId());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.executeUpdate();
            }

        } catch (Exception e) {

        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

}
