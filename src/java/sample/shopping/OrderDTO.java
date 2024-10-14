/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class OrderDTO extends ProductDTO{
    private String orderID;
    private String userID;
    private double total;
    private Timestamp date;

    public OrderDTO() {
        orderID ="";
        userID = "";
        total = 0;
        date = null;
    }

    public OrderDTO(String orderID, String userID, double total, Timestamp date) {
        this.orderID = orderID;
        this.userID = userID;
        this.total = total;
        this.date = date;
    }

    public OrderDTO(String orderID, String id, Double price, int quantity) {
        super(id, price, quantity);
        this.orderID = orderID;
    }
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setProductID(String userID) {
        this.userID = userID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
}
