package com.example.demo.Models;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;

public class Profile {

    private int id;
    private String name;
    private String gender;
    private String email;
    private String description;
    private Blob picture;

    public Profile(int id, String name, String gender, String email, String description, Blob picture) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.description = description;
        this.picture = picture;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
