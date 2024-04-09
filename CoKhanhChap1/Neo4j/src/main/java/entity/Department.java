/*
 * @(#)Department.java 1.0  21/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     21/2/2024
 *@Version:  1.0
 */


package entity;

public class Department {
    private String departmentID;
    private String name;
    private String dean;
    private String building;
    private String room;

    public Department() {
    }

    public Department(String departmentID, String name, String dean, String building, String room) {
        this.departmentID = departmentID;
        this.name = name;
        this.dean = dean;
        this.building = building;
        this.room = room;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID='" + departmentID + '\'' +
                ", name='" + name + '\'' +
                ", dean='" + dean + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
