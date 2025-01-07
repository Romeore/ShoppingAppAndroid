package com.example.hitshoppingapp.models;

import java.util.ArrayList;

public class User {

    private String email;
    private String phone;

    private ArrayList<Item> items;

    public User() {

    }

    public User(String email, String phone) {
        this.email = email;
        this.phone = phone;
        this.items = new ArrayList<Item>();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
