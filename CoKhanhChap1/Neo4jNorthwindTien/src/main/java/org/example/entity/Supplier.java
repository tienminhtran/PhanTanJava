package org.example.entity;

import org.neo4j.driver.internal.Neo4jBookmarkManager;

public class Supplier {
//    supplierID	companyName	contactName	contactTitle	address	city
    private String supplierID;

    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;

    public Supplier() {
    }

    public Supplier(String supplierID, String companyName, String contactName, String contactTitle, String address, String city) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
    }

    public Supplier(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    LOAD CSV WITH HEADERS FROM 'file:///Album.csv' AS row
    MATCH (a:Album {id: row.id})
    MATCH (g:Genre {id: row.genre_id})
    MERGE (a)-[:BELONG_TO]->(g)




    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID=" + supplierID +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
