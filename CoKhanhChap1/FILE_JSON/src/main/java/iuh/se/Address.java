/*
 * @(#)Address.java 1.0  23/2/2024
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

public class Address {

    private String building;
    private List<Double> coord;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String building, List<Double> coord, String street, String zipcode) {
        this.building = building;
        this.coord = coord;
        this.street = street;
        this.zipcode = zipcode;
    }



    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<Double> getCoord() {
        return coord;
    }

    public void setCoord(List<Double> coord) {
        this.coord = coord;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    @Override
    public String toString() {
        return "Address{" +
                "building='" + building + '\'' +
                ", coord=" + coord +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
