package com.jk.demo.entities;

public class Me {
    private String username;
    private String head;
    private String password;
    private String number;
    private String email;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "Me{" +
                "username='" + username + '\'' +
                ", head='" + head + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
