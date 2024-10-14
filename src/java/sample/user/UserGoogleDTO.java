/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author ADMIN
 */
public class UserGoogleDTO {

    private String id;
    private String name;
    private String roleID;
    private String password;

    public UserGoogleDTO() {
        this.id = "";
        this.name = "";
        this.roleID = "";
        this.password = "";
    }

    public UserGoogleDTO(String id, String name, String roleID, String password) {
        this.id = id;
        this.name = name;
        this.roleID = roleID;
        this.password = password;
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

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserGoogleDTO{" + "id=" + id + ", name=" + name + ", roleID=" + roleID + ", password=" + password + '}';
    }
    
}
  