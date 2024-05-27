package com.example.basic;

import java.sql.Date;

public class customerData
{
    private Integer customerId;
    private String productType;
    private String productBrand;
    private String productModel;
    private Integer quantity;
    private Double price;
    private Date date;

    public customerData(
                        String productType,
                        String productBrand,
                        String productModel,
                        Integer quantity,
                        Double price,
                        Date date)
    {
        this.customerId = customerId;
        this.productType = productType;
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
