package com.softuni.springjson.models.dtos.taskdtos;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserSoldOneDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    List<ProductsSoldDto> soldProducts;

    public UserSoldOneDto() {
        this.soldProducts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
