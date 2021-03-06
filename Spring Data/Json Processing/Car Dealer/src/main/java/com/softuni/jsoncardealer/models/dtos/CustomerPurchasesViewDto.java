package com.softuni.jsoncardealer.models.dtos;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class CustomerPurchasesViewDto {

    @SerializedName("fullName")
    private String name;

    private Integer boughtCars;
    private BigDecimal spentMoney;

    public CustomerPurchasesViewDto() {
    }

    public CustomerPurchasesViewDto(String name, Integer boughtCars, BigDecimal spentMoney) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
