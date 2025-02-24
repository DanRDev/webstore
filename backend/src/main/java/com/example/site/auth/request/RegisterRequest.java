package com.example.site.auth.request;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructor (optional, can be used for convenience)
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Default constructor (optional, in case you prefer the default one)
    public RegisterRequest() {
    }
}
