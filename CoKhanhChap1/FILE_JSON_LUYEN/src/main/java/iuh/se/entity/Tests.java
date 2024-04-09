/*
 * @(#)Tests.java 1.0  26/2/2024
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

public class Tests {
    private Date date;
    private String result;
    private int test_ID;
    private String test_type;
    private double cost;

    public Tests() {
    }

    public Tests(Date date, String result, int test_ID, String test_type, double cost) {
        this.date = date;
        this.result = result;
        this.test_ID = test_ID;
        this.test_type = test_type;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTest_ID() {
        return test_ID;
    }

    public void setTest_ID(int test_ID) {
        this.test_ID = test_ID;
    }

    public String getTest_type() {
        return test_type;
    }

    public void setTest_type(String test_type) {
        this.test_type = test_type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "date=" + date +
                ", result='" + result + '\'' +
                ", test_ID=" + test_ID +
                ", test_type=" + test_type +
                ", cost=" + cost +
                '}';
    }
}
