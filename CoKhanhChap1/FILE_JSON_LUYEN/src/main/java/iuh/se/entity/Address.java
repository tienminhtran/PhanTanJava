/*
 * @(#)address.java 1.0  26/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     26/2/2024
 *@Version:  1.0
 */


package iuh.se.entity;

public class Address {
    private String city;
    private String district;
    private String street;
    private String ward;

    public Address () {
    }

    public Address(String city, String district, String street, String ward) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    @Override
    public String toString() {
        return "address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", ward='" + ward + '\'' +
                '}';
    }
}

