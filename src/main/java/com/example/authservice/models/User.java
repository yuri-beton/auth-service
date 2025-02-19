package com.example.authservice.models;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String token;
    private Long role_id;
    private boolean isConfirmed;

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
    public Long getRole_id() {
        return role_id;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setConfirmed() {
        isConfirmed = true;
    }
    public boolean isConfirmed() {
        return isConfirmed;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
