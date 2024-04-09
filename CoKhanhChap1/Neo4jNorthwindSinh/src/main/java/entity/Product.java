package entity;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("productID")
    private String productID;
//    @SerializedName("supplierID")
    private Supplier supplierID;
//    @SerializedName("categoryID")
    private Category categoryID;
    @SerializedName("productName")
    private String productName;
    @SerializedName("quantityPerUnit")
    private String quantityPerUnit;
    @SerializedName("unitPrice")
    private double unitPrice;
    @SerializedName("unitsInStock")
    private int unitsInStock;
    @SerializedName("unitsOnOrder")
    private int unitsOnOrder;
    @SerializedName("reorderLevel")
    private int reorderLevel;


    public Product(String productID, Supplier supplierID, Category categoryID, String productName, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.productName = productName;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", supplierID=" + supplierID +
                ", categoryID=" + categoryID +
                ", productName='" + productName + '\'' +
                ", quantityPerUnit='" + quantityPerUnit + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", reorderLevel=" + reorderLevel +
                '}';
    }
}
