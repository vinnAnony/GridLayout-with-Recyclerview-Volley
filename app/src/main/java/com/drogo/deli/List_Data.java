package com.drogo.deli;

public class List_Data {
    private String imageurl,productName,maskName,productPrice,description;

    public List_Data(String imageurl, String productName, String maskName, String description, String productPrice) {
        this.imageurl = imageurl;
        this.productName = productName;
        this.maskName = maskName;
        this.productPrice = productPrice;
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getProductName() {
        return productName;
    }

    public String getMaskName() {
        return maskName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getDescription() {
        return description;
    }
}
