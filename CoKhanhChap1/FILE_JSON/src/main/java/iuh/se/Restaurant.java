/*
 * @(#)Retaurant.java 1.0  23/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/2/2024
 *@Version:  1.0
 */


package iuh.se;

import java.util.List;

public class Restaurant {
    private String restaurant_id;
    private boolean is_closed;
    private String name;
    private String borough;
    private String cuisine;
    private Address address;
    private List<Grades> grades;


    public Restaurant() {
    }
    public Restaurant(String restaurant_id, boolean is_closed, String name, String borough, String cuisine, Address address, List<Grades> grades){
        this.restaurant_id = restaurant_id;
        this.is_closed = is_closed;
        this.name = name;
        this.borough = borough;
        this.cuisine = cuisine;
        this.address = address;
        this.grades = grades;
    }


    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public Restaurant(String restaurant_id, boolean is_closed, String name, String borough, String cuisine, Address address) {
        this.restaurant_id = restaurant_id;
        this.is_closed = is_closed;
        this.name = name;
        this.borough = borough;
        this.cuisine = cuisine;
        this.address = address;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Retaurant{" +
                "restaurant_id='" + restaurant_id + '\'' +
                ", is_closed=" + is_closed +
                ", name='" + name + '\'' +
                ", borough='" + borough + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", address=" + address +
                ", grades=" + grades +
                '}';
    }
}
