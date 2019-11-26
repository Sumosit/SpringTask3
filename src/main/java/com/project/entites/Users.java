package com.project.entites;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Users {
    private String email;
    private String password;
    private String repassword;
    private String login;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String re_password) {
        this.repassword = re_password;
    }

    public Users() {}

    public Users(String email, String password, String repassword, String login) {
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.login = login;
    }
}
