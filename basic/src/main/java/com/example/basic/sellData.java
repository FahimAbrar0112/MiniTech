package com.example.basic;

import java.sql.Date;

public class sellData
{
    private String productType;
    private String productBrand;
    private String productModel;
    private Integer quantity;
    private Double price;
    private Double total;
    private Date date;

    public sellData(String productType, String productBrand, String productModel, Integer productQuantity, Double productPrice, Double total, Date date)
    {
        this.productType = productType;
        this.productBrand = productBrand;
        this.productModel = productModel;
        this.price = productPrice;
        this.quantity = productQuantity;
        this.total = total;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
