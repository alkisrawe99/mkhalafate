package org.codeforiraq.autheticationcrud.Model;

public class Type {
    private int id;
    private  String name_vio,price;

    public Type() {
    }

    public Type(String name_vio, String price) {
        this.name_vio = name_vio;
        this.price = price;
    }

    public Type(int id, String name_vio, String price) {
        this.id = id;
        this.name_vio = name_vio;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_vio() {
        return name_vio;
    }

    public void setName_vio(String name_vio) {
        this.name_vio = name_vio;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
