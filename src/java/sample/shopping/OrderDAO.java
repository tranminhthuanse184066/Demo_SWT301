package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import sample.user.UserDTO;
import sample.utils.DBUtils;

public class OrderDAO {

    private static final String SHOW_ORDER = "SELECT orderID, userID, total, date FROM tblOrders WHERE userID = ? ORDER BY date ASC";
    private static final String SHOW_FULL_ORDER = "SELECT orderID, userID, total, date FROM tblOrders ORDER BY date ASC";
    private static final String SHOW_ORDER_DETAIL = "SELECT orderID, productID, price, quantity FROM tblOrderDetail WHERE orderID = ?";

    public List<OrderDTO> showOrder(UserDTO loginUser) throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_ORDER);
                ptm.setString(1, loginUser.getUserID());
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String productID = rs.getString("userID");
                    double total = rs.getDouble("total");
                    Timestamp date = rs.getTimestamp("date");
                    list.add(new OrderDTO(orderID, productID, total, date));
                }
            }
        } catch (Exception e) {
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

    public List<OrderDTO> showOrder() throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_FULL_ORDER);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    String productID = rs.getString("userID");
                    double total = rs.getDouble("total");
                    Timestamp date = rs.getTimestamp("date");
                    list.add(new OrderDTO(orderID, productID, total, date));
                }
            }
        } catch (Exception e) {
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

    public List<OrderDTO> showOrderDetail(String orderID) throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW_ORDER_DETAIL);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new OrderDTO(orderID, productID, price, quantity));
                }
            }
        } catch (Exception e) {
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
}
