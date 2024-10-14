/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

public class ProductDTO {

    private String id;
    private String name;
    private Double price;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(String id, Double price, int quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(Double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

}