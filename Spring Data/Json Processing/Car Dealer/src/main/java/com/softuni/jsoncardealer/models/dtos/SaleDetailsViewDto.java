package com.softuni.jsoncardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SaleDetailsViewDto {

    private CarViewShortDto car;
    private String customerName;

    @SerializedName("Discount")
    private Double discount;

    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public SaleDetailsViewDto() {
    }

    public CarViewShortDto getCar() {
        return car;
    }

    public void setCar(CarViewShortDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
