package com.lntu.digitalhuman.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private String message;
    private boolean success;
    
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public LoginResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.success = true;
        this.message = "登录成功";
    }
}