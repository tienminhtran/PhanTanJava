package org.example.entity;

public class Order {
//    orderID	customerID	employeeID	orderDate	requiredDate	shippedDate
private String orderID;
    private Customer customerID;
    private String employeeID;
    private String orderDate;
    private String requiredDate;
    private String shippedDate;

    public Order() {
    }

    public Order(String orderID, Customer customerID, String employeeID, String orderDate, String requiredDate, String shippedDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                ", employeeID=" + employeeID +
                ", orderDate='" + orderDate + '\'' +
                ", requiredDate='" + requiredDate + '\'' +
                ", shippedDate='" + shippedDate + '\'' +
                '}';
    }
}
