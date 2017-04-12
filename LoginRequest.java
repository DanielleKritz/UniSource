/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisource;

/**
 *
 * @author dakritz
 */
public class LoginRequest {
    private String User;
    private String Password;
    
    public LoginRequest(String username, String password){
        
    }
    public String getUser() {
        return User;
    }
    
    public String getPassword() {
        return Password;
    }
}
