package com.example.demo.Models;

import java.sql.Blob;

public class Profile {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String description;
    // private Blob picture;

    public Profile(int id, String name, String email, String password, String gender, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.description = description;
        // this.picture = picture;
    }

    public Profile(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "id=" + id + ", " + name + ", " + gender + ", "+ email + ", " + description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

     */
}
