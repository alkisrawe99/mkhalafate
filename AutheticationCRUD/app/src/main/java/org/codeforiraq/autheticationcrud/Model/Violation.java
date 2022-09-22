package org.codeforiraq.autheticationcrud.Model;

import java.util.Date;

public class Violation {
    private int id;
    private  String name_vio,location,nam_car,name_driver,num_car,price;
    private Date updated_at;

    public Violation() {
    }

    public Violation(int id, String name_vio, String location, String nam_car, String name_driver, String num_car, String price, Date updated_at) {
        this.id = id;
        this.name_vio = name_vio;
        this.location = location;
        this.nam_car = nam_car;
        this.name_driver = name_driver;
        this.num_car = num_car;
        this.price = price;
        this.updated_at = updated_at;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNam_car() {
        return nam_car;
    }

    public void setNam_car(String nam_car) {
        this.nam_car = nam_car;
    }

    public String getName_driver() {
        return name_driver;
    }

    public void setName_driver(String name_driver) {
        this.name_driver = name_driver;
    }

    public String getNum_car() {
        return num_car;
    }

    public void setNum_car(String num_car) {
        this.num_car = num_car;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
