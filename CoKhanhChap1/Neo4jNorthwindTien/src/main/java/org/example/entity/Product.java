package org.example.entity;

public class Product {

//    productID
//    productName
//    supplierID
//    categoryID
//    quantityPerUnit
//    unitPrice
    private String productID;
    private String productName;
    private Supplier supplierID;
    private Category categoryID;
    private String quantityPerUnit;
    private double unitPrice;

    public Product() {
    }

    public Product(String productID, String productName, Supplier supplierID, Category categoryID, String quantityPerUnit, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Supplier getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Supplier supplierID) {
        this.supplierID = supplierID;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", supplierID=" + supplierID.getSupplierID() +
                ", categoryID=" + categoryID +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
