package com.example.models;

public class ItemModel {

    int id;
    String name;
    String quantity;

    public ItemModel() {
        super();
    }

    public ItemModel(int id, String name, String quantity) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public ItemModel(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Constructor class

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
