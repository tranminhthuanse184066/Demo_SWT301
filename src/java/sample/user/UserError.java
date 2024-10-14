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
public class UserError {
    private String userIDError="";
    private String fullNameError="";
    private String roleIDIError="";
    private String passwordError="";
    private String confirmError="";
    private String error="";

    public UserError(String userIDError, String fullNameError, String roleIDIError, String passwordError, String confirmError, String error) {
        this.userIDError = userIDError;
        this.fullNameError = fullNameError;
        this.roleIDIError = roleIDIError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleIDIError() {
        return roleIDIError;
    }

    public void setRoleIDIError(String roleIDIError) {
        this.roleIDIError = roleIDIError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public UserError() {
    }
    
}
