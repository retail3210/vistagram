package com.vista.vistagram.models;

/**
 * User
 */
public class User {
    public String id, name, profileUrl; // profiles
    public String email, pw; // personal

    public User(String email, String pw, String name) {
        this.email = email;
        this.pw = pw;
        this.name = name;
    }
}
