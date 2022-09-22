package org.codeforiraq.autheticationcrud.Model;

public class Car {
    private int id;
    private String car_name,token;
    private String car_num;

    public Car() {
    }


    public Car(String token) {
        this.token = token;
    }
    public Car(int id, String car_num, String car_name) {
        this.id = id;
        this.car_num = car_num;
        this.car_name = car_name;
    }

    public Car(String car_name, String car_num) {
        this.car_name = car_name;
        this.car_num = car_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

   /* public int getCar_num() {
        return car_num;
    }

    public void setCar_num(int car_num) {
        this.car_num = car_num;
    }
*/

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
