package com.marian.domain;

import com.marian.entity.enumeration.Role;

public class RegisterRequest {


    //@UniqueUser(message = "This User is already exists")
    private String email;
    private String password;
    private   String   passwordConfirmation;
    private Role role;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public RegisterRequest() {
    }

}
