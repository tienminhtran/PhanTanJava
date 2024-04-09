/*
 * @(#)BenhNhan.java 1.0  26/2/2024
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

import java.util.List;

public class BenhNhan {
    private String _id;
    private String first_Name;
    private String last_Name;
    private String blood_type;
    private String gender;
    private Address address;

    private List<String> telephone;

    private List<Tests> tests;

    private int year_of_birth;

    public BenhNhan() {
    }

    public BenhNhan(String _id, String first_Name, String last_Name, String blood_type, String gender, Address address, List<String> telephone, List<Tests> tests, int year_of_birth) {
        this._id = _id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.blood_type = blood_type;
        this.gender = gender;
        this.address = address;
        this.telephone = telephone;
        this.tests = tests;
        this.year_of_birth = year_of_birth;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<String> telephone) {
        this.telephone = telephone;
    }

    public List<Tests> getTests() {
        return tests;
    }

    public void setTests(List<Tests> tests) {
        this.tests = tests;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    @Override
    public String toString() {
        return "BenhNhan{" +
                "_id='" + _id + '\'' +
                ", first_Name='" + first_Name + '\'' +
                ", last_Name='" + last_Name + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                ", telephone=" + telephone +
                ", tests=" + tests +
                ", year_of_birth=" + year_of_birth +
                '}';
    }
}
